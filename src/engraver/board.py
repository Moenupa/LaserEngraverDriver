from PIL import Image, ImageDraw, ImageChops
import numpy as np
import matplotlib.pyplot as plt
import os.path
import random

class Pixel():

    def __init__(self, x: int, y: int) -> None:
        self.x = x
        self.y = y

    def __coords__(self):
        return (self.x, self.y)

    def __str__(self):
        return f'({self.x}, {self.y})'


class Element():

    def __init__(self) -> None:
        # self.TX = tx
        pass


class Shape(Element):

    def __init__(self, center: Pixel) -> None:
        super().__init__()
        self.center = center


class Line(Element):

    def __init__(self, src: Pixel, dest: Pixel) -> None:
        super().__init__()
        self.src = src
        self.dest = dest


class Rectangle(Shape):

    def __init__(self, center: Pixel, width: int, height: int) -> None:
        super().__init__(center)
        self.width = width
        self.height = height


class Circle(Shape):

    def __init__(self, center: Pixel, radius: int) -> None:
        super().__init__(center)
        self.radius = radius
        

class PositioningRectangle(Rectangle):
    
    def __init__(self, center: Pixel, width: int, height: int) -> None:
        super().__init__(center, width, height)
    


class Board():
    EMPTY = 0
    FILLED = 1

    def __init__(self,
                 width: float = 370.0,
                 height: float = 370.0,
                 resolution: float = 0.05,
                 image: list = []) -> None:
        """width and height of the board, measured in mm.

        Args:
            width (float, optional): width. Defaults to 370.0.
            height (float, optional): height. Defaults to 370.0.
            resolution (float, optional): resolution of the board. Defaults to 0.05.
        """
        self.width = int(width / resolution)
        self.height = int(height / resolution)
        self.elements: list[Element] = []
        self.image = Image.new('1', self.size(), Board.EMPTY)
        self.pattern = Image.new('1', self.size(), Board.EMPTY)
        self.pattern_pixels = np.array([])
        for x, y in image:
            self.image.putpixel((x, y), Board.FILLED)

    def size(self) -> tuple[int, int]:
        return self.width, self.height

    def drawLine(self, x0: int, y0: int, x1: int, y1: int) -> None:
        draw = ImageDraw.Draw(self.image)
        draw.line((x0, y0, x1, y1), fill=Board.FILLED)

    def drawRect(self, x0: int, y0: int, w: int, h: int) -> None:
        x1, y1 = x0 + w, y0 + h
        self.drawLine(x0, y0, x1, y0)
        self.drawLine(x1, y0, x1, y1)
        self.drawLine(x1, y1, x0, y1)
        self.drawLine(x0, y1, x0, y0)
    
    def drawPosRect(self, x0: int, y0: int, w: int, h: int) -> None:
        x1, y1 = x0 + w, y0 + h
        l = min(w, h) // 10
        self.drawLine(x0, y0, x0 + l, y0)
        self.drawLine(x0, y0, x0, y0 + l)
        
        self.drawLine(x1, y0, x1 - l, y0)
        self.drawLine(x1, y0, x1, y0 + l)
        
        self.drawLine(x0, y1, x0 + l, y1)
        self.drawLine(x0, y1, x0, y1 - l)
        
        self.drawLine(x1, y1, x1 - l, y1)
        self.drawLine(x1, y1, x1, y1 - l)

    def drawCircle(self, x, y, r) -> None:
        draw = ImageDraw.Draw(self.image)
        draw.ellipse((x - r, y - r, x + r, y + r), outline=Board.FILLED)
        return

    def import_pattern(self, path, preview: bool = True) -> None:
        if not os.path.exists(path):
            print(f'{path} does not exist')
            return
        
        image = Image.open(path).convert('L')
        points = Board._get_engrave_points(image, ordered=False)
        for p in points:
            self.pattern.putpixel(p, Board.FILLED)
        if preview:
            self.pattern.show()
        self.pattern_pixels = points
    
    def generate_on_pattern(self, show_diff: bool = True) -> None:
        x0, y0 = random.choice(self.pattern_pixels)
        # change here if you want to change the random pattern
        self.drawCircle(x0, y0, 10)
        diff = ImageChops.logical_xor(self.image, self.pattern)
        if show_diff:
            Board._preview(diff, "diff")

    def preview(self, *args, **kwargs) -> None:
        Board._preview(self.image, *args, **kwargs)
    
    @staticmethod
    def _get_engrave_points(image: Image, ordered: bool = True) -> list:
        pixels = np.array(image)
        coords = np.flip(np.column_stack(np.where(pixels != Board.EMPTY)), axis=1)
        if ordered:
            return Board._order_points(coords.tolist())
        return coords.tolist()

    def get_engrave_points(self, ordered: bool = True) -> list:
        return Board._get_engrave_points(self.image, ordered)
    
    def get_bounding_box(self) -> list:
        points = self.get_engrave_points()
        if len(points) == 0:
            return (0, 0, 0, 0)
        x0, y0 = points[0]
        x1, y1 = x0, y0
        for x, y in points:
            x0, y0 = min(x0, x), min(y0, y)
            x1, y1 = max(x1, x), max(y1, y)
        return (x0, y0, x1, y1)
    
    @staticmethod
    def _preview(image: Image, crop: tuple | None = (0, 0, 1000, 1000), *args, **kwargs) -> None:
        if crop:
            image.crop(crop).show(*args, **kwargs)
        else:
            image.show(*args, **kwargs)

    @staticmethod
    def _order_points(points: list, ind: int = 0):
        # ref: https://stackoverflow.com/questions/37742358/sorting-points-to-form-a-continuous-line
        if not points:
            return points

        points_new = [points.pop(ind)]
        pcurr = points_new[-1]
        while len(points) > 0:
            d = np.linalg.norm(np.array(points) - np.array(pcurr), axis=1)
            ind = d.argmin()
            points_new.append(points.pop(ind))
            pcurr = points_new[-1]
        return points_new

    @staticmethod
    def _animate_pixels(points: list,
                        width: int = 1000,
                        height: int = 1000,
                        padding: int = 0,
                        animation_delay: float = 1e-5,
                        precision: int = 2) -> None:
        plt.gca().set_aspect('equal')
        plt.ylim(height + padding, - padding)
        plt.xlim(-padding, width + padding)
        for x, y in points:
            if x % precision == 0 and y % precision == 0:
                plt.plot(x, y, 'go', markersize=1)
                plt.pause(animation_delay)
        plt.show()


if __name__ == '__main__':
    board = Board()
    board.import_pattern('./res/patterns/final.bmp', preview=False)
    board.generate_on_pattern(show_diff=True)
    board.drawPosRect(0, 0, 880, 880)
    board.preview()
    Board._animate_pixels(board.get_engrave_points())
    exit(0)
