from itertools import chain

class Point():
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y
    def __coords__(self):
        return (self.x, self.y)
    def __str__(self):
        return f'({self.x}, {self.y})'

class Region():
    def _toList(points: list[Point]) -> list[int]:
        return list(chain.from_iterable([p.__coords__() for p in points]))
        
region_100x100 = [Point(i, i) for i in range(100)]

if __name__ == "__main__":
    print(Region._toList(region_100x100))