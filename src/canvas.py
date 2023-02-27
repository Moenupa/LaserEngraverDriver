from itertools import chain
from pprint import pprint

class Point():
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y
    def __coords__(self):
        return (self.x, self.y)
    def __str__(self):
        return f'({self.x}, {self.y})'

class Shape():
    @staticmethod
    def _drawEdge(src: Point, dest: Point) -> list[Point]:
        diff = (dest.x - src.x, dest.y - src.y)
        if diff[0] == 0 and diff[1] == 0:
            return [src]
        elif diff[0] == 0:
            # xDiff is 0, the line is `|`, 
            return Shape._drawVerticalLine(src.x, src.y, diff[1])
        elif diff[1] == 0:
            # yDiff is 0, the line is `-`
            return Shape._drawHorizontalLine(src.x, src.y, diff[0])
        
        # else plot a curve
        points = []
        slope = diff[1] / diff[0]
        for xid in range(0, diff[0], 1 if diff[0] > 0 else -1):
            points.append(Point(src.x + xid, round(xid * slope + src.y)))
        return points
    
    @staticmethod
    def _drawVerticalLine(x: int, y0: int, yDiff: int) -> list[Point]:
        points = []
        for yid in range(0, yDiff, 1 if yDiff > 0 else -1):
            points.append(Point(x, y0 + yid))
        return points
    
    @staticmethod
    def _drawHorizontalLine(x0: int, y: int, xDiff: int) -> list[Point]:
        points = []
        for xid in range(0, xDiff, 1 if xDiff > 0 else -1):
            points.append(Point(x0 + xid, y))
        return points
    
    @staticmethod
    def _drawRect(x0, y0, w, h) -> list[Point]:
        points = []
        p1 = Point(x0, y0)
        p2 = Point(x0 + w, y0)
        p3 = Point(x0 + w, y0 + h)
        p4 = Point(x0, y0 + h)
        points += Shape._drawEdge(p1, p2)
        points += Shape._drawEdge(p2, p3)
        points += Shape._drawEdge(p3, p4)
        points += Shape._drawEdge(p4, p1)
        return points

    @staticmethod
    def _toString(points: list[Point]) -> str:
        return ", ".join(str(p) for p in points)
    
    @staticmethod
    def _toFlatList(points: list[Point]) -> list[int]:
        return list(chain.from_iterable([p.__coords__() for p in points]))

    @staticmethod
    def _getBoundingBox(points: list[Point]):
        if len(points) == 0:
            return (0, 0, 0, 0)
        x0, y0 = points[0].x, points[0].y
        x1, y1 = x0, y0
        for p in points:
            x0, y0 = min(x0, p.x), min(y0, p.y)
            x1, y1 = max(x1, p.x), max(y1, p.y)
        return (x0, y0, x1, y1)
    
    @staticmethod
    def _getMetaData(points: list[Point]):
        x0, y0, x1, y1 = Shape._getBoundingBox(points)
        center = Point((x0 + x1) // 2, (y0 + y1) // 2)
        return center, x1-x0, y1-y0

class Canvas():
    def __init__(self, points = []) -> None:
        self.points : list[Point] = points

    def __len__(self) -> None:
        return self.points.__len__()
    
    def __str__(self) -> str:
        return Shape._toString(self.points)

    def add(self, points: list[Point]):
        self.points += points

    def toList(self) -> list[int]:
        return Shape._toFlatList(self.points)
    
    def getMetaData(self):
        return Shape._getMetaData(self.points)

if __name__ == "__main__":
    pass