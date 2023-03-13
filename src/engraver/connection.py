import os
import serial
import logging
import time

from enum import IntEnum
from itertools import chain

from src.config import Config

class MODEL_ID(IntEnum):
    JL3 = 36
class MODEL_META():
    def __init__(self, w, h, hi, mid, lo) -> None:
        # canvas (grid) width and height
        self.w, self.h = w, h
        # accuracy defaults, high, mid and low
        self.hi, self.mid, self.lo = hi, mid, lo

MODEL_META_LOOKUP = {
    # Medium Accuracy Question: 0.065
    'JL3': MODEL_META(w=370, h=370, hi=0.05, mid=0.065, lo=0.075)
}

class OPCode(IntEnum):
    VERSION = 0xff
    NA = 0
    RESET_START = 6
    RESET_STOP = 7
    CONNECT = 10
    HEARTBEAT = 11
    ENGRAVE_STOP = 22
    ENGRAVE_PAUSE = 24
    ENGRAVE_RESUME = 25
    PREVIEW_START = 32
    PREVIEW_STOP = 33
    SEND_ENGRAVE_CHUNK = 34
    SEND_ENGRAVE_METADATA = 35
    ENGRAVE_START = 36
    ADJUST_POWER_DEPTH = 37
    SET_PREVIEWPOWER_RESOLUTION = 40


class ACKCode(IntEnum):
    VERSION = 4
    ERROR = 8
    OK = 9
    METADATA_OK = 255

class ByteList():
    """convert integers to byte lists
    
    Args: `list[int]` or `int`
    Returns: `list[int]`
    """
    
    def _bytes(arr: list[int]) -> list[int]:
        return list(map(lambda x: x & 0xff, arr))

    def _endian_swap(arr: list[int], BE: bool) -> list[int]:
        return list(reversed(arr)) if BE else arr

    def _quadruple_bytes(i: int, BE: bool = False) -> list[int]:
        ret = [(i & 0xFF000000) >> 24, (i & 0xFF0000)
               >> 16, (i & 0xFF00) >> 8, i & 0xFF]
        return ByteList._endian_swap(ret, BE)
    
    def _single_byte(i: int) -> list[int]:
        return [i & 0xFF]

    def _double_bytes(i: int, BE: bool = False) -> list[int]:
        ret = [(i & 0xFF00) >> 8, i & 0xFF]
        return ByteList._endian_swap(ret, BE)

    def _double_bytes_arr(arr: list[int], BE: bool = False) -> list[int]:
        ret = list(chain.from_iterable(
            map(lambda x: ByteList._double_bytes(x, BE), arr)))
        return ret


class Connection():
    MAXIMUM_READ_BYTES = 4
    MAX_CHUNK_SIZE = 1900

    @staticmethod
    def _ack(data: bytes):
        try:
            return (int(data[0]), ACKCode(data[0]).name)
        except ValueError:
            return (0, f"unknown_{data[0]}")

    @staticmethod
    def _checksum(data: list) -> bytes:
        s = sum(data)
        if (s > 0xff):
            s = ~s + 1
        return s & 0xff

    @staticmethod
    def _packet(op_type: OPCode, data: list[int] = [], checksum: bool = False) -> bytes:
        """form a packet, [op_type, pkt_len, pkt_len, ..data, checksum]

        Args:
            op_type (OPCode): Instruction code
            data (list[int], optional): data. Defaults to [].
            checksum (bool, optional): include a checksum. Defaults to False.

        Returns:
            bytes: formatted packet, ready to send directly to the engraver
        """
        pkt_len = len(data) + 4
        ret = [int(op_type)] + ByteList._double_bytes(pkt_len) + data + [0]
        if checksum:
            ret[-1] = Connection._checksum(ret)
        return bytes(ByteList._bytes(ret))

    @staticmethod
    def _packet_decrypt(hex_string: str) -> list:
        data = bytes.fromhex(hex_string)
        try:
            op = OPCode(data[0]).name
        except ValueError:
            op = OPCode.NA.name
        return (op, list(data[3:-1]))
    
    @staticmethod
    def _chunk(_list: list[int], n: int = MAX_CHUNK_SIZE):
        for i in range(0, len(_list), n):
            yield _list[i : i+n]

    def __init__(self, stdout: bool, dry_run: bool, verbose: bool = True) -> None:
        self.config = Config(stdout=stdout, dry_run=dry_run, verbose=verbose)
        if self.config.dry_run:
            return

        if 'nt' in os.name:
            self.ser = serial.Serial(
                port='COM7', baudrate=115200, xonxoff=True,
                timeout=3.0, write_timeout=2.0,
                stopbits=serial.STOPBITS_ONE
            )
        else:
            self.ser = serial.Serial(
                port='/dev/ttyUSB0', baudrate=115200, xonxoff=True,
                timeout=3.0, write_timeout=2.0,
                stopbits=serial.STOPBITS_ONE
            )

        self.timestep = 0.01
        logging.info(
            f'Connected to {self.ser.name} at port{self.ser.port}')
        logging.info(f'Check if serial port opened: [{self.open()}]')

    def open(self) -> bool:
        if self.config.dry_run:
            return True

        if not self.ser.is_open:
            self.ser.open()
        return self.ser.is_open

    def send(self, data: bytes, timeout: float) -> None:
        if self.config.dry_run:
            logging.info(
                f'[DRY_RUN] -> ({len(data)}/{len(data)}) {list(data)}')
            return

        if not self.open():
            logging.log(
                logging.CRITICAL, f'[{self.ser.port} NOT OPENED] -> 0x{data.hex()}')
            return
        len_sent, len_expected = 0, len(data)
        start = time.time()
        
        # expects to send more, and within timeout
        while len_sent != len_expected and time.time() - start < timeout:
            res = self.ser.write(data)
            self.ser.flush()
            logging.info(
                f'[{self.ser.port}] -> ({res}/{len_expected}) 0x{data.hex()}')
            len_sent = res
            time.sleep(self.timestep)
        if (len_sent != len_expected):
            logging.error(
                f'[{self.ser.port}] -> ({len_sent}/{len_expected}) 0x{data.hex()}')

    def receive(self, timeout: float) -> bool:
        if self.config.dry_run:
            logging.info(f'[DRY_RUN] <- (0, 0) NA')
            return True

        if not self.open():
            logging.log(
                logging.CRITICAL, f'[{self.ser.port} NOT OPENED] <- ERROR')
            return
        start = time.time()
        level = logging.INFO
        
        # expects to receive more, and within timeout
        while self.ser.in_waiting > 0 and time.time() - start < timeout:
            data = self.ser.read_all()
            ack_code, ack_name = Connection._ack(data)

            if ack_code == 0:
                level = logging.WARNING
            if 'ERROR' in ack_name:
                level = logging.ERROR

            logging.log(level,
                        f'[{self.ser.port}] <- ({ack_code}, {ack_name}) 0x{data.hex()}')
            time.sleep(self.timestep)
        if (self.ser.in_waiting > 0):
            logging.error(
                f'[{self.ser.port}] -> (timeout {timeout}s insufficient) port still has {self.ser.in_waiting} bytes')
        
        return level < logging.ERROR

    def sendWithACK(self, arr: bytes, send_timeout: float = 1.0, ack_timeout: float = 2.0, ack_interval: float = 0.1, instr_interval: float = 0.2) -> bool:
        """send data to serial port and get ACK message

        Args:
            `arr` (list): data to send
            `send_timeout` (float, optional): timeout for send. Defaults to 1.0.
            `ack_timeout` (float, optional): timeout for ack. Defaults to 2.0.
            `ack_interval` (float, optional): wait time between send & ack. Defaults to 0.1.
            `instr_interval` (float, optional): wait time between each instructions. Defaults to 0.2.

        Returns:
            True if ACK, else False
        """
        self.send(arr, timeout=send_timeout)
        time.sleep(ack_interval)
        ret = self.receive(timeout=ack_timeout)
        time.sleep(instr_interval)
        return ret

    def close(self) -> None:
        logging.info(f'closing serial port')

        if self.config.dry_run:
            return
        self.ser.close()
