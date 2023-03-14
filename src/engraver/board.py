from PIL import Image, ImageDraw
import math


class Point():
    """A point in real-world 2D engraving space, in a 370mm x 370mm board."""

    def __init__(self, x: float, y: float) -> None:
        self.x = x
        self.y = y

    def __coords__(self):
        return (self.x, self.y)

    def __str__(self):
        return f'({self.x}, {self.y})'


class TX():

    def __init__(self) -> None:
        pass


class Element():

    def __init__(self, transform: TX = None) -> None:
        self.transform = transform


class Shape(Element):

    def __init__(self, center: Point) -> None:
        super().__init__()
        self.center = center


class Line(Element):

    def __init__(self, src: Point, dest: Point) -> None:
        super().__init__()
        self.src = src
        self.dest = dest


class Rectangle(Shape):

    def __init__(self, center: Point, width: float, height: float) -> None:
        super().__init__(center)
        self.width = width
        self.height = height


class Circle(Shape):

    def __init__(self, center: Point, radius: float) -> None:
        super().__init__(center)
        self.radius = radius


class Board():
    EMPTY = 0
    FILLED = 1

    def __init__(self,
                 width: float = 370.0,
                 height: float = 370.0,
                 resolution: float = 0.05) -> None:
        """width and height of the board, measured in mm.

        Args:
            width (float, optional): width. Defaults to 370.0.
            height (float, optional): height. Defaults to 370.0.
            resolution (float, optional): resolution of the board. Defaults to 0.05.
        """
        self.width = width
        self.height = height
        self.resolution = resolution
        self.elements: list[Element] = []
        self.image = Image.new('1', self.size(), Board.EMPTY)

    def size(self) -> tuple[int, int]:
        return (int(self.width / self.resolution),
                int(self.height / self.resolution))

    def pixel(self, point: Point) -> tuple[int, int]:
        return (int(point.x / self.resolution), int(point.y / self.resolution))

    def addElement(self, element: Element) -> None:
        self.elements.append(element)

    def draw(self, element: Element) -> None:
        try:
            if (isinstance(element, Line)):
                self.drawLine(element)
            elif (isinstance(element, Rectangle)):
                self.drawRect(element)
            elif (isinstance(element, Circle)):
                self.drawCircle(element)
            else:
                raise Exception(f'Unknown element type: {type(element)}')
        except Exception as e:
            print(f'Error drawing element: {e}')

    def drawLine(self, line: 'Line') -> None:
        # draw a line onto self.image
        x_src, y_src = self.pixel(line.src)
        x_dest, y_dest = self.pixel(line.dest)
        dx, dy = x_dest - x_src, y_dest - y_src
        if dx == 0 and dy == 0:
            return
        elif dx == 0:
            # xDiff is 0, the line is `|`,
            self.drawVerticalLine(x_src, y_src, dy)
        elif dy == 0:
            # yDiff is 0, the line is `-`
            self.drawHorizontalLine(x_src, y_src, dx)
        else:
            slope = dy / dx
            for xid in range(0, dx, 1 if dx > 0 else -1):
                self.image.putpixel((x_src + xid, round(xid * slope + x_src)),
                                    Board.FILLED)

    def drawVerticalLine(self, x: int, y: int, length: int) -> None:
        for i in range(0, length, 1 if length > 0 else -1):
            self.image.putpixel((x, y + i), Board.FILLED)

    def drawHorizontalLine(self, x: int, y: int, length: int) -> None:
        for i in range(0, length, 1 if length > 0 else -1):
            self.image.putpixel((x + i, y), Board.FILLED)

    def drawRect(self, rect: Rectangle) -> None:
        x0, y0 = self.pixel(
            Point(rect.center.x - rect.width / 2,
                  rect.center.y - rect.height / 2))
        x1, y1 = self.pixel(
            Point(rect.center.x + rect.width / 2,
                  rect.center.y + rect.height / 2))
        self.drawHorizontalLine(x0, y0, x1 - x0)
        self.drawVerticalLine(x1, y0, y1 - y0)
        self.drawHorizontalLine(x1, y1, x0 - x1)
        self.drawVerticalLine(x0, y1, y0 - y1)

    def drawCircle(self, circle: Circle) -> None:
        draw = ImageDraw.Draw(self.image)
        x0, y0 = self.pixel(circle.center)
        r = round(circle.radius / self.resolution)
        draw.ellipse((x0 - r, y0 - r, x0 + r, y0 + r), outline=Board.FILLED)
        return

    def preview(self) -> None:
        self.image = Image.new('1', self.size(), Board.EMPTY)
        for element in self.elements:
            self.draw(element)
        print(f'size: {self.image.size}')
        print(sum(list(self.image.getdata())))
        self.image.show()


if __name__ == '__main__':
    board = Board()
    # board.addElement(Line(Point(0, 0), Point(10, 10)))
    # board.addElement(Rectangle(Point(100, 100), 10, 10))
    board.addElement(Circle(Point(200, 200), 100))
    board.preview()
