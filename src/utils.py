import serial.tools.list_ports as ls
import inspect
import logging
import sys
from datetime import date
from itertools import chain

class Config():
    def __init__(self, stdout: bool, log:str = f'./log/{date.today()}.log') -> None:
        if stdout:
            logging.basicConfig(
                stream=sys.stdout, level=logging.DEBUG,
                format='%(asctime)s.%(msecs)03d %(levelname)s %(module)s - %(funcName)s: %(message)s',
                datefmt='%Y-%m-%d %H:%M:%S',
            )
        else:
            logging.basicConfig(
                filename=log, level=logging.DEBUG,
                format='%(asctime)s.%(msecs)03d %(levelname)s %(module)s - %(funcName)s: %(message)s',
                datefmt='%Y-%m-%d %H:%M:%S',
            )

def remapping(w: int, h: int, x: int, y: int) -> list[int]:
    """remap x-y coordinates to machine coordinates
    format: [width, height, x, y]

    Args:
        w (int): width
        h (int): height
        x (int): x (top left)
        y (int): y (top left)

    Returns:
        list[int]: machine coordinates [width, height, x, y]
    """
    return [w, h, (int) (x + 67 + w/2), (int) (y + 67 + h/2)]

def arrInBytes(arr: list[int]) -> list[int]:
    """ensure the list of integers are in byte range
    Overflow (>0xFF) is ignored.

    Args:
        `arr` (list[int]): list of ints

    Returns:
        `list`: list of ints
    """
    return list(map(lambda x: x & 0xff, arr))

def arr2DbBytes(arr: list[int]) -> list[int]:
    """Convert a list of integers to a list of double-byte ints.
    Overflow (>0xFFFF) is ignored.

    Args:
        arr (list[int]): list of ints

    Returns:
        list[int]: list of double-byte words
    """
    return list(chain.from_iterable(map(int2DbBytes, arr)))

def int2DbBytes(i: int) -> list[int]:
    """Convert an integer to a two-byte.
    Overflow (>0xFFFF) is ignored.

    Args:
        i (int): an int

    Returns:
        list[int]: a two-byte word in list
    """
    return [(i & 0xFF00) >> 8, i & 0xFF]
def print_all_com_ports():
    ports = ls.comports()
    for p, des, hwid in sorted(ports):
        print(f'{p}: {des} [{hwid}]')
        
def checksum(data: bytes) -> int:
    '''
    Get the checksum of the current function
    '''
    s = sum(data)
    if (s > 0xff):
        s = ~s + 1
    return (s & 0xff).to_bytes(1, 'big')

def data2packet(data: bytes, instr_type: bytes = b'\x22') -> bytes:
    """form a packet that contains instruction type, length, data and checksum

    Args:
        data (bytes): data
        instr_type (bytes, optional): instructino type. Defaults to 34 (0x22).

    Returns:
        bytes: packet in bytes
    """
    packet_len = len(data) + 4
    return instr_type[:1] + bytes(int2DbBytes(packet_len)) + data + checksum(data)

def get_object_properties(obj: object, start="_") -> list:
    attr = inspect.getmembers(obj, lambda a:not(inspect.isroutine(a)))
    return [a for a in attr if not(a[0].startswith(start))]

if __name__ == '__main__':
    arr = [16, 16, 256, 256]
    print(remapping(*arr))
    print(arr2DbBytes(arr))
    
    print(checksum(b'\xff\xff\xff\xff').hex())
    print(data2packet(b'\xff\xff\xff\xff').hex())
    
    print(arrInBytes(arr))