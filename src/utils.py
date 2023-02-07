import serial.tools.list_ports as ls
import inspect
import logging
import sys
from datetime import date

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
        

def int2bytes(i: int) -> list:
    '''
    Convert an integer to a list of two bytes.
    Overflow (>= 2^16) is ignored.
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

msgs = {
    'version': [[0, 0, 0, 0]],
    'read_version': [[-1, 0, 4, 0]],
    'handshake': [[10, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0]],
    'rect': [[32, 0, 11], ],
    'settings': [[32, 0, 11], ],
    'settings_qg': [[37, 0, 11], ],
    'unknown1': [[7, 0, 4, 0]],
    'unknown2': [[6, 0, 4, 0]],
    'tuoji': [[35, 0, 38, ]], # suspected main engraving function mainJFrame line 1914
    'unknown3': [[10, 0, 4, 0]],
    'unknown4': [[36, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0]],
    'keep-alive': [[11, 0, 4, 0]],
    'firmware_reset': [[-2, 0, 4, 0]],
}

if __name__ == '__main__':
    for i in [17, 255, 256]:
        print(f'int {i} to bytes: {int2bytes(i)}')
    print_all_com_ports()
    