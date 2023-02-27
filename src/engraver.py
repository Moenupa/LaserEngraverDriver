import logging
from math import ceil
import time

from connection import Connection, OPCode, ByteList
from canvas import Point, Canvas, Shape

class MetaData():
    def __init__(self, hint: str, w: int, h: int, power: int = 1000, depth: int = 10) -> None:
        self.hint = hint
        self.w = w
        self.h = h
        self.power = power
        self.depth = depth
    def __str__(self):
        return f'{self.hint}: w={self.w}, h={self.h}, power={self.power}, depth={self.depth}'
    def toByteList(self, dataBeginsAt: list[int]) -> list[int]:
        ret = []
        ret += ByteList._double_bytes_arr([self.w, self.h])
        ret += dataBeginsAt
        ret += ByteList._double_bytes_arr([self.power, self.depth])
        return ret

class Engraver(Connection):
    HEARTBEAT_INTERVAL_SECONDS = 7
    
    def hello(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.CONNECT))

    def version(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.VERSION))
        
    def preview(self, w: int, h: int, x: int, y: int) -> None:
        logging.info(f'(x={x}, y={y}, x2={x+w}, y2={y+h})')
        data = ByteList._double_bytes_arr(
            [w, h, x + w//2, y + h//2]
        )
        self.sendWithACK(Connection._packet(OPCode.PREVIEW_START, data, False))
    
    def preview_stop(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.PREVIEW_STOP))
    
    def move_to(self, x: int, y: int) -> None:
        logging.info('(x={x}, y={y})')
        self.preview(0, 0, x, y)
        self.preview_stop()
        
    def engrave_start(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.ENGRAVE_START))
        
    def engrave_resume(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.ENGRAVE_RESUME))
        
    def engrave_pause(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.ENGRAVE_PAUSE))
        
    def engrave_stop(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.ENGRAVE_STOP))
        
    def heartbeat(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.HEARTBEAT))
    
    def engrave_metadata(self, carve: MetaData, cut: MetaData, nCutPoints: int, center: Point, repeats: int = 1, version: int = 1) -> None:
        logging.info('')
        CHUNK_LENGTH = [33, carve.w * ceil(carve.h / 8.0), nCutPoints * 4]
        CHUNK_BEGINS_AT = [int(sum(CHUNK_LENGTH[:i])) for i in range(len(CHUNK_LENGTH))]
        data = []
        data += ByteList._double_bytes(int(sum(CHUNK_LENGTH) / 4094 + 1))
        data += ByteList._single_byte(version)
        data += carve.toByteList(ByteList._double_bytes(CHUNK_BEGINS_AT[1]))
        data += cut.toByteList(ByteList._quadruple_bytes(CHUNK_BEGINS_AT[2]))
        data += ByteList._quadruple_bytes(nCutPoints)
        data += ByteList._double_bytes_arr([center.x, center.y])
        data += ByteList._single_byte(repeats)
        self.sendWithACK(Connection._packet(OPCode.SEND_ENGRAVE_METADATA, data, False))
    
    def engrave_data_chunk(self, data: list[int]) -> None:
        logging.info('')
        for chunk in Connection._chunk(data):
            self.sendWithACK(Connection._packet(OPCode.SEND_ENGRAVE_CHUNK, chunk, True))
        
    def engrave(self, carve: MetaData, cut: MetaData, center: Point, repeats: int, carvePoints, cutPoints: Canvas) -> None:
        # array length is [widthInByte * height + len(cutPoints) * 4]
        # carve picture: widthInByte * height
        # cut points: cutPoints, (x, y) in double byte
        logging.info(f'carving meta: {carve}')
        logging.info(f'cutting meta: {cut}')
        
        logging.info(f'center: {center}, repeats: {repeats}')
        
        self.engrave_metadata(carve, cut, len(cutPoints), center, repeats)
        self.hello()
        time.sleep(0.3)
        self.hello()
        time.sleep(0.3)
        data = []
        data += carvePoints
        # sequence: x, x>>8, y, y>>8
        data += ByteList._double_bytes_arr(cutPoints.toList(), True)
        self.engrave_data_chunk(data)
        self.hello()
        
        self.engrave_start()


if __name__ == '__main__':
    engraver = Engraver()
    engraver.hello()
    engraver.version()
    # engraver.move_to(0, 0)
    # engraver.move_to(370*20, 370*20)
    
    carve = MetaData('carve', 0, 0)

    canvas = Canvas(Shape._drawRect(0, 0, 200, 200))
    # canvas = Canvas(Shape._drawEdge(Point(0, 0), Point(200, 100)))
    center, w, h = canvas.getMetaData()
    cut = MetaData('cut', w, h)

    engraver.engrave(carve, cut, center, 1, [], canvas)
    
    engraver.close()