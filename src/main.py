from engraver import Engraver
from canvas import Canvas, Shape, Pixel

if __name__ == '__main__':
    engraver = Engraver()
    engraver.hello()
    engraver.version()
    engraver.move_to(200, 200)
    engraver.close()