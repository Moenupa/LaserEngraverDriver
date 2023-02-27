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
            for xid in range(diff[0]):
                self.points.append(Point(src.x + xid, round(xid * slope + src.y)))
        elif diff[1] != 0:
            slope_r = diff[0] / diff[1]
            for yid in range(diff[1]):
                self.points.append(Point(round(yid * slope_r + src.x), src.y + yid))
        else:
            raise ZeroDivisionError

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
            # two horizontal edges
            self.points += [Point(x0 + i, y0) for i in range(w)]
            self.points += [Point(x0 + i, y0 + h) for i in range(w)]
            # two vertical edges
            self.points += [Point(x0, y0 + i) for i in range(h)]
            self.points += [Point(x0 + w, y0 + i) for i in range(h)]
    
    def getPoints(self):
        return self.points

        
region_100x100 = [Point(i, i) for i in range(100)]

if __name__ == "__main__":
    # rect = Rect(0, 0, 200, 200)
    # print(rect.toList())
    # print(len(rect))
    # pprint(str(rect))
    # print(Region._toList(region_100x100))
    tl = Point(0, 0)
    tr = Point(200, 0)
    br = Point(200, 200)
    bl = Point(0, 200)
    edge1 = Edge(tl, tr)
    edge2 = Edge(tr, br)
    edge3 = Edge(br, bl)
    edge4 = Edge(bl, tl)
    print("e1", Region._toString(edge1.getPoints()))
    print("e2", Region._toString(edge2.getPoints()))
    print("e3", Region._toString(edge3.getPoints()))
    print("e4", Region._toString(edge4.getPoints()))