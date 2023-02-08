import serial.tools.list_ports as ls
import inspect
import logging
import sys
from datetime import date
from itertools import chain

BYTEMAX = 0x0100
TWOBYTEMAX = 0xffff

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
def remapping(w, h, x, y) -> list:
    '''
    remap x-y coordinates to machine coordinates
    format: [width, height, x, y]

    `@param arr`: x-y coordinates
    `@return`: machine coordinates
    '''
    return [w, h, (int) (x + 67 + w/2), (int) (y + 67 + h/2)]
def arr2bytes(arr: list[int]) -> list:
    '''
    Convert a list of integers to a list of two-byte words.
    Overflow (>0xFFFF) is ignored.
    '''
    return list(chain.from_iterable(map(int2bytes, arr)))
def int2bytes(i: int) -> list:
    '''
    Convert an integer to a list of two-byte words.
    Overflow (>0xFFFF) is ignored.
    '''
    return [(i // BYTEMAX) % BYTEMAX, i % BYTEMAX]
def emptyList() -> list:
    '''
    Get an empty list
    '''
    return []
def print_all_com_ports():
    ports = ls.comports()
    for p, des, hwid in sorted(ports):
        print(f'{p}: {des} [{hwid}]')
def get_object_properties(obj: object, start="_") -> list:
    attr = inspect.getmembers(obj, lambda a:not(inspect.isroutine(a)))
    return [a for a in attr if not(a[0].startswith(start))]

if __name__ == '__main__':
    arr = [16, 16, 10, 10]
    print(remapping(*arr))
    print(arr2bytes(arr))
    if (eval(input("Starting [Engraving] Input for Confirmation: "))): 
        print("Confirmed")