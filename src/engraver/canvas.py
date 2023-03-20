from itertools import chain
import pygame
import math


class Pixel():

    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y

    def __coords__(self):
        return (self.x, self.y)

    def __str__(self):
        return f'({self.x}, {self.y})'


class Shape():

    @staticmethod
    def _drawEdge(src: Pixel, dest: Pixel) -> list[Pixel]:
        deltas = (dest.x - src.x, dest.y - src.y)
        if deltas[0] == 0 and deltas[1] == 0:
            return [src]
        elif deltas[0] == 0:
            # xDiff is 0, the line is `|`,
            return Shape._drawVerticalLine(src.x, src.y, deltas[1])
        elif deltas[1] == 0:
            # yDiff is 0, the line is `-`
            return Shape._drawHorizontalLine(src.x, src.y, deltas[0])

        # else plot a curve
        points = []
        slope = deltas[1] / deltas[0]
        for xid in range(0, deltas[0], 1 if deltas[0] > 0 else -1):
            points.append(Pixel(src.x + xid, round(xid * slope + src.y)))
        return points

    @staticmethod
    def _drawVerticalLine(x: int, y0: int, dy: int) -> list[Pixel]:
        points = []
        for yid in range(0, dy, 1 if dy > 0 else -1):
            points.append(Pixel(x, y0 + yid))
        return points

    @staticmethod
    def _drawHorizontalLine(x0: int, y: int, dx: int) -> list[Pixel]:
        points = []
        for xid in range(0, dx, 1 if dx > 0 else -1):
            points.append(Pixel(x0 + xid, y))
        return points

    @staticmethod
    def _drawRect(x0, y0, w, h) -> list[Pixel]:
        points = []
        p1 = Pixel(x0, y0)
        p2 = Pixel(x0 + w, y0)
        p3 = Pixel(x0 + w, y0 + h)
        p4 = Pixel(x0, y0 + h)
        points += Shape._drawEdge(p1, p2)
        points += Shape._drawEdge(p2, p3)
        points += Shape._drawEdge(p3, p4)
        points += Shape._drawEdge(p4, p1)
        return points

    @staticmethod
    def _drawCircle(x0, y0, r) -> list[Pixel]:
        points = []
        theta = 0
        while theta < 2 * math.pi:
            x, y = round(x0 + r * math.cos(theta)), round(y0 +
                                                          r * math.sin(theta))
            if len(points) == 0 or (x != points[-1].x or y != points[-1].y):
                points.append(Pixel(x, y))
            theta += 0.01 / r
        return points

    @staticmethod
    def _toString(points: list[Pixel]) -> str:
        return ", ".join(str(p) for p in points)

    @staticmethod
    def _toFlatList(points: list[Pixel]) -> list[int]:
        return list(chain.from_iterable([p.__coords__() for p in points]))

    @staticmethod
    def _getBoundingBox(points: list[Pixel]):
        if len(points) == 0:
            return (0, 0, 0, 0)
        x0, y0 = points[0].x, points[0].y
        x1, y1 = x0, y0
        for p in points:
            x0, y0 = min(x0, p.x), min(y0, p.y)
            x1, y1 = max(x1, p.x), max(y1, p.y)
        return (x0, y0, x1, y1)


class Canvas():

    def __init__(self, points=[]) -> None:
        self.points: list[Pixel] = points

    def __len__(self) -> None:
        return self.points.__len__()

    def __str__(self) -> str:
        return Shape._toString(self.points)

    def add(self, points: list[Pixel]):
        self.points += points

    def get_engrave_points(self) -> list[int]:
        return self.points
    
    def get_bounding_box(self) -> list:
        return Shape._getBoundingBox(self.points)

    def preview(self, **kwargs) -> bool:
        CROP_REGION = 999
        DEFAULT_SIZE = 370
        CANVAS_SIZE_INITIAL = min(CROP_REGION, DEFAULT_SIZE) * 20

        ZOOM = 10
        CANVAS_SIZE = CANVAS_SIZE_INITIAL // ZOOM
        PADDING = 10
        WINDOW_SIZE = CANVAS_SIZE + PADDING * 2

        WHITE = [255] * 3
        GRAY = [50] * 3

        def drawGrid(window, color, blockSize=20):
            for x in range(0, CANVAS_SIZE, blockSize):
                for y in range(0, CANVAS_SIZE, blockSize):
                    rect = pygame.Rect(x + PADDING, y + PADDING, blockSize,
                                       blockSize)
                    pygame.draw.rect(window, color, rect, 1)

        pygame.init()
        window = pygame.display.set_mode((WINDOW_SIZE, WINDOW_SIZE),
                                         pygame.DOUBLEBUF | pygame.RESIZABLE)
        pygame.display.set_caption('Preview of Canvas')

        run = True
        engrave = False
        while run:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    run = False
                elif event.type == pygame.KEYDOWN:
                    engrave = True

            window.fill(GRAY)
            window.fill(0, (PADDING, PADDING, CANVAS_SIZE, CANVAS_SIZE))

            drawGrid(window, GRAY)
            for point in self.points:
                window.set_at(
                    (point.x // ZOOM + PADDING, point.y // ZOOM + PADDING),
                    WHITE)

            pygame.display.flip()

        print(len(self.points))
        pygame.quit()
        return engrave


if __name__ == "__main__":
    print(Canvas(Shape._drawCircle(200, 200, 100)).preview())
