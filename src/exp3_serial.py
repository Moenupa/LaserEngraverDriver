import serial
import logging
import time
from typing import Tuple, Callable

from utils import Config
from utils import arr2bytes, remapping

config = Config(stdout=False)

class Instructions():
    '''
    Instruction Class that contains all decrypted protocols

    Pass a send function and a recieve function if required
    '''
    busy = False
    log = logging.info
    def connect(f: Callable, l: Callable = log) -> None :
        l('')
        f([10, 0, 4, 0])
    def read_firmware_version(f: Callable, l: Callable = log) -> None:
        l('')
        f([0xff, 0, 4, 0])
    def start_firmware_update(f: Callable, l: Callable = log) -> None:
        l('')
        f([0xfe, 0, 4, 0])
    def end_firmware_update(f: Callable, l: Callable = log) -> None:
        l('')
        f([4, 0, 4, 0])
    def preview_location(f: Callable, w: int, h: int, x: int, y: int, l: Callable = log) -> None:
        l(f'(x={x}, y={y}, x2={x+w}, y2={y+h})')
        f([32, 0, 11] + arr2bytes(remapping(w=w, h=h, x=x, y=y)))
    def stop_preview(f: Callable, l: Callable = log) -> None:
        l('')
        f([33, 0, 4, 0])
    def move_to(f: Callable, x: int, y: int, l: Callable = log) -> None:
        l(f'(x={x}, y={y})')
        Instructions.preview_location(f, w=0, h=0, x=x, y=y, l=l)
        Instructions.stop_preview(f, l=l)
    def engrave(f: Callable, l: Callable = log) -> None:
        l('')
        if (eval(input("Starting [Engraving] Input for Confirmation: "))):
            logging.warn('Engraving Confirmed')
            f([22, 0, 4, 0])
        

def main():
    ser = serial.Serial(
        port='COM7', baudrate=115200, xonxoff=True, 
        timeout=3.0, write_timeout=2.0,
        stopbits=serial.STOPBITS_ONE
    )
    def getSendStatus(b: bytes) -> Tuple[int, str]:
        '''
        Get whether the last sent message is received based on
        the last received bytes. Engraver preset receive codes are `9`
        success, `8` fail, `NA` lost.

        Returns:
          `0` if `LAST_RCV=9: success`
          `-1` if `LAST_RCV=8: fail`
          `1` if `else: unknown`   
        '''
        d = {
            9: (0, "succ"),
            8: (-1, "fail")
        }
        return d.get(b[0], (1, "unkn"))
    def send(l: list) -> None:
        res = ser.write(bytes(l))
        ser.flush()
        logging.info(f'[{ser.port}] -> ({res}) 0x{bytes(l).hex()}')
        return
    def recv(timeout:int = 2) -> None:
        while ser.in_waiting > 0:
            data = ser.read_all()
            level = logging.ERROR if getSendStatus(data)[0] < 0 else logging.INFO
            logging.log(level, f'[{ser.port}] <- {getSendStatus(data)} 0x{data.hex()}')
            time.sleep(timeout / 100.0)
    def sendWithRet(arr: list, timeout:int = 2, wait:float=0.1, interval:float = 0.5) -> None:
        send(arr)
        time.sleep(wait)
        recv(timeout=timeout)
        time.sleep(interval)
    logging.info(f'Connected to {ser.name} at port{ser.port}')
    if not ser.isOpen():
        ser.open()
    logging.info(f'Check if serial port opened: [{ser.is_open}]')

    Instructions.connect(sendWithRet)
    Instructions.read_firmware_version(sendWithRet)
    # Instructions.preview_location(sendWithRet, *[2000, 2000, 2000, 2000])
    # time.sleep(5)
    # Instructions.stop_preview(sendWithRet)
    Instructions.move_to(sendWithRet, 1000, 1000)

    ser.close()
    logging.info(f'disconnected\n{"-"*60}')
    
if __name__ == '__main__':
    main()
    