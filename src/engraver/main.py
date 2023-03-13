from engraver import Engraver
from canvas import Canvas, Shape, Pixel

if __name__ == '__main__':
    engraver = Engraver(stdout=False, dry_run=True)
    engraver.hello()
    engraver.version()
    engraver.move_to(200, 200)
    engraver.close()