import serial
import logging
import time

from enum import IntEnum
from itertools import chain

from config import Config


class OPCode(IntEnum):
    VERSION = 0xff
    START_RESET = 6
    STOP_RESET = 7
    CONNECT = 10
    HEARTBEAT = 11
    STOP_PRINT = 22
    PAUSE_PRINT = 24
    RESUME_PRINT = 25
    START_PREVIEW = 32
    STOP_PREVIEW = 33
    SEND_PRINT_CHUNK = 34
    SEND_PRINT_METADATA = 35
    START_PRINT = 36
    ADJUST_POWER_DEPTH = 37
    SET_PREVIEWPOWER_RESOLUTION = 40


class ACKCode(IntEnum):
    VERSION = 4
    OK = 9


class ByteList():
    def _list(arr: list[int]) -> list[int]:
        return list(map(lambda x: x & 0xff, arr))

    def _endian_swap(arr: list[int], BE: bool) -> list[int]:
        return list(reversed(arr)) if BE else arr

    def _quadruple_bytes(i: int, BE: bool = False) -> list[int]:
        ret = [(i & 0xFF000000) >> 24, (i & 0xFF0000)
               >> 16, (i & 0xFF00) >> 8, i & 0xFF]
        return ByteList._endian_swap(ret, BE)

    def _double_bytes(i: int, BE: bool = False) -> list[int]:
        ret = [(i & 0xFF00) >> 8, i & 0xFF]
        return ByteList._endian_swap(ret, BE)

    def _double_bytes_arr(arr: list[int], BE: bool = False) -> list[int]:
        ret = list(chain.from_iterable(
            map(lambda x: ByteList._double_bytes(x, BE), arr)))
        return ByteList._endian_swap(ret, BE)


class Connection():
    CHECKSUM_YES = True
    CHECKSUM_NO = False
    MAXIMUM_READ_BYTES = 4
    HEARTBEAT_INTERVAL_SECONDS = 7

    def _ack(data: bytes):
        try:
            return (int(data[0]), ACKCode(data[0]).name)
        except ValueError:
            return (0, f"unknown_{data[0]}")

    def _checksum(data) -> bytes:
        s = sum(data)
        if (s > 0xff):
            s = ~s + 1
        return (s & 0xff).to_bytes(1, 'big')

    def _packet(data: list[int], op_type: OPCode, checksum: bool) -> bytes:
        # form a packet, [op_type, pkt_len, pkt_len, ..data, checksum]
        pkt_len = len(data) + 4
        ret = [int(op_type)] + ByteList._double_bytes(pkt_len) + data + [0]
        if checksum:
            ret[-1] = [Connection._checksum(ret)]
        return bytes(ret)

    def __init__(self) -> None:
        self.ser = serial.Serial(
            port='COM7', baudrate=115200, xonxoff=True,
            timeout=3.0, write_timeout=2.0,
            stopbits=serial.STOPBITS_ONE
        )
        self.config = Config(stdout=False)
        self.timestep = 0.01
        logging.info(f'disconnected\n{"-"*80}')
        logging.info(
            f'Connected to {self.ser.name} at port{self.ser.port}')
        logging.info(f'Check if serial port opened: [{self.open()}]')

    def open(self) -> bool:
        if not self.ser.is_open:
            self.ser.open()
        return self.ser.is_open

    def send(self, data: list, timeout: float) -> None:
        if not self.open():
            logging.log(
                logging.CRITICAL, f'[{self.ser.port}] -> [port not opened] 0x{bytes(data).hex()}')
            return
        len_sent, len_expected = 0, len(bytes(data))
        count = 0
        while len_sent != len_expected and count < timeout / self.timestep:
            res = self.ser.write(bytes(data))
            self.ser.flush()
            logging.info(
                f'[{self.ser.port}] -> ({res}/{len_expected}) 0x{bytes(data).hex()}')
            len_sent += res
            time.sleep(self.timestep)
            count += 1
        if (len_sent != len_expected):
            logging.error(
                f'[{self.ser.port}] -> ({len_sent}/{len_expected}) 0x{bytes(data).hex()}')

    def receive(self, timeout: float) -> None:
        if not self.open():
            logging.log(
                logging.CRITICAL, f'[{self.ser.port}] <- [port not opened]')
            return
        count = 0
        while self.ser.in_waiting > 0 and count < timeout / self.timestep:
            data = self.ser.read_all()
            if Connection._ack(data)[0] < 0:
                level = logging.ERROR
            elif Connection._ack(data)[0] == 0:
                level = logging.WARNING
            else:
                level = logging.INFO
            logging.log(level,
                        f'[{self.ser.port}] <- {Connection._ack(data)} 0x{data.hex()}')
            time.sleep(self.timestep)
            count += 1
        if (self.ser.in_waiting > 0):
            logging.error(
                f'[{self.ser.port}] -> (timeout {timeout}s insufficient) port still has {self.ser.in_waiting} bytes')

    def sendWithACK(self, arr: list, send_timeout: float = 1.0, ack_timeout: float = 2.0, ack_interval: float = 0.1, instr_interval: float = 0.2) -> None:
        """send data to serial port and get ACK message

        Args:
            `arr` (list): data to send
            `send_timeout` (float, optional): timeout for send. Defaults to 1.0.
            `ack_timeout` (float, optional): timeout for ack. Defaults to 2.0.
            `ack_interval` (float, optional): wait time between send & ack. Defaults to 0.1.
            `instr_interval` (float, optional): wait time between each instructions. Defaults to 0.2.
        """
        self.send(arr, timeout=send_timeout)
        time.sleep(ack_interval)
        self.receive(timeout=ack_timeout)
        time.sleep(instr_interval)

    def close(self) -> None:
        self.ser.close()


class Engraver(Connection):
    def connect(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet([], OPCode.CONNECT, False))

    def version(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet([], OPCode.VERSION, False))
        
    def preview(self, w: int, h: int, x: int, y: int) -> None:
        logging.info(f'(x={x}, y={y}, x2={x+w}, y2={y+h})')
        data = ByteList._double_bytes_arr(
            [w, h, (int) (x + 67 + w/2), (int) (y + 67 + h/2)]
        )
        self.sendWithACK(Connection._packet(data, OPCode.START_PREVIEW, False))
    
    def stop_preview(self) -> None:
        logging.info('')
        self.sendWithACK(Connection._packet([], OPCode.STOP_PREVIEW, False))
    
    def move_to(self, x: int, y: int) -> None:
        logging.info('(x={x}, y={y})')
        self.preview(x, y, 0, 0)
        self.stop_preview()


if __name__ == '__main__':
    engraver = Engraver()
    engraver.connect()
    engraver.version()
    engraver.move_to(200, 200)
    engraver.close()
