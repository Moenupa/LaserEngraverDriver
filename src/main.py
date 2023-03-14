from engraver import Engraver, Canvas, Shape, Pixel
from time import sleep

if __name__ == '__main__':
    engraver = Engraver(stdout=False, dry_run=False)
    engraver.hello()
    engraver.version()
    # engraver.preview(100, 400, 0, 0)
    # engraver.preview_stop()
    # engraver.move_to(200, 200)
    
    canvas = Canvas(Shape._drawRect(0, 0, 100, 400))
    engraver.engrave(1, canvas)
    
    engraver.close()