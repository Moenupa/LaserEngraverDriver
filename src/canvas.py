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

class Edge():
    def __init__(self, src: Point, dest: Point) -> None:
        self.points = []
        diff = (dest.x - src.x, dest.y - src.y)
        if diff[0] != 0:
            slope = diff[1] / diff[0]
            for xid in range(0, diff[0], 1 if diff[0] > 0 else -1):
                self.points.append(Point(src.x + xid, round(xid * slope + src.y)))
        elif diff[1] != 0:
            slope_r = diff[0] / diff[1]
            for yid in range(0, diff[1], 1 if diff[1] > 0 else -1):
                self.points.append(Point(round(yid * slope_r + src.x), src.y + yid))
        else:
            raise ZeroDivisionError
        return

    def getPoints(self) -> list[Point]:
        return self.points

class Region():
    def __init__(self) -> None:
        self.points : list[Point] = []

    def __len__(self) -> None:
        return self.points.__len__()
    
    def __str__(self) -> str:
        return Region._toString(self.points)

    def toList(self) -> list[int]:
        return Region._toList(self.points)

    @staticmethod
    def _toString(points: list[Point]) -> str:
        return ", ".join(str(p) for p in points)
    
    @staticmethod
    def _toList(points: list[Point]) -> list[int]:
        return list(chain.from_iterable([p.__coords__() for p in points]))

class Rect(Region):
    def __init__(self, x0, y0, w, h, filled = False) -> None:
        super().__init__()
        if filled:
            for hid in range(h):
                self.points += [Point(x0 + wid, y0 + hid) for wid in range(w)]
        else:
            p1 = Point(x0, y0)
            p2 = Point(x0 + w, y0)
            p3 = Point(x0 + w, y0 + h)
            p4 = Point(x0, y0 + h)
            self.points += Edge(p1, p2).getPoints()
            self.points += Edge(p2, p3).getPoints()
            self.points += Edge(p3, p4).getPoints()
            self.points += Edge(p4, p1).getPoints()
    
    def getPoints(self):
        return self.points

        
region_100x100 = [Point(i, i) for i in range(100)]

if __name__ == "__main__":
    # rect = Rect(0, 0, 200, 200)
    # print(rect.toList())
    # print(len(rect))
    # pprint(str(rect))
    # print(Region._toList(region_100x100))
    pass