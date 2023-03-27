import logging
from math import ceil

from connection import Connection, OPCode, ByteList, flatten
from board import *
import time


class MetaData():

    def __init__(self,
                 hint: str,
                 w: int,
                 h: int,
                 power: int = 1000,
                 depth: int = 10) -> None:
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

    def _parse_from(name: str, board: Board) -> tuple:
        x0, y0, x1, y1 = board.get_bounding_box()
        center = ((x0 + x1) // 2, (y0 + y1) // 2)
        meta_data = MetaData(name, x1 - x0, y1 - y0)
        return center, meta_data


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
        data = ByteList._double_bytes_arr([w, h, x + w // 2, y + h // 2])
        self.sendWithACK(Connection._packet(OPCode.PREVIEW_START, data, False))

    def preview_stop(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet(OPCode.PREVIEW_STOP))

    def move_to(self, x: int, y: int) -> None:
        logging.info(f'(x={x}, y={y})')
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

    def engrave_metadata(self,
                         carve: MetaData,
                         cut: MetaData,
                         nCutPoints: int,
                         center: tuple,
                         repeats: int = 1,
                         version: int = 1) -> None:
        logging.info('')
        CHUNK_LENGTH = [33, carve.w * ceil(carve.h / 8.0), nCutPoints * 4]
        CHUNK_BEGINS_AT = [
            int(sum(CHUNK_LENGTH[:i])) for i in range(len(CHUNK_LENGTH))
        ]
        data = []
        data += ByteList._double_bytes(int(sum(CHUNK_LENGTH) / 4094 + 1))
        data += ByteList._single_byte(version)
        data += carve.toByteList(ByteList._double_bytes(CHUNK_BEGINS_AT[1]))
        data += cut.toByteList(ByteList._quadruple_bytes(CHUNK_BEGINS_AT[2]))
        data += ByteList._quadruple_bytes(nCutPoints)
        data += ByteList._double_bytes_arr(center)
        data += ByteList._single_byte(repeats)
        self.sendWithACK(
            Connection._packet(OPCode.SEND_ENGRAVE_METADATA, data, False))

    def engrave_data_chunk(self, data: list[int]) -> None:
        logging.info('')
        for chunk in Connection._chunk(data):
            self.sendWithACK(
                Connection._packet(OPCode.SEND_ENGRAVE_CHUNK, chunk, True))

    def engrave(self,
                repeats: int,
                cut: Board,
                carve: MetaData = MetaData('carve', 0, 0),
                carvePoints=[],
                require_confirm=False) -> None:
        # array length is [widthInByte * height + len(cutPoints) * 4]
        # carve picture: widthInByte * height
        # cut points: cutPoints, (x, y) in double byte
        logging.info(f'preparing engraving... configured repeats: {repeats}')
        logging.info(f'preparing carving meta: {carve}')
        
        center, cutMetaData = MetaData._parse_from('cut', cut)
        logging.info(
            f'preparing cutting meta: {cutMetaData}, center: {center}')

        cut.preview()
        if require_confirm:
            if len(input('confirmation message: ')) == 0:
                logging.warning(
                    'engraving terminated by user: no confirmation during preview')
                return

        logging.info(f'sending metadata')
        cutPoints = cut.get_engrave_points()
        self.engrave_metadata(carve, cutMetaData, len(cutPoints), center,
                              repeats)
        self.hello()
        self.hello()
        data = []
        # sequence: x, x>>8, y, y>>8
        data += ByteList._double_bytes_arr(flatten(cutPoints), BE=True)
        self.engrave_data_chunk(data)
        self.hello()

        self.engrave_start()


if __name__ == '__main__':
    engraver = Engraver(stdout=False, dry_run=False)
    engraver.hello()
    engraver.version()

    board = Board()
    board.import_pattern('./res/patterns/final.bmp', preview=False)
    board.generate_noise_on_pattern(n=2, x_offset=100)
    board.generate_positioning_on_pattern(n=2, x_offset=100)
    engraver.engrave(1, board, require_confirm=True)

    engraver.close()
