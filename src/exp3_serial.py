import serial
import logging
import time
from typing import Tuple, Callable

from utils import int2bytes, Config, print_all_com_ports

config = Config(stdout=False)

class Instructions():
    '''
    Instruction Class that contains all decrypted protocols

    Pass a send function and a recieve function if required
    '''
    def connect(f: Callable):
        logging.info('')
        f([10, 0, 4, 0])
    def read_firmware_version(f: Callable):
        logging.info('')
        f([0xff, 0, 4, 0])

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
        # sleep for 0.1 to wait for response
        time.sleep(0.1)
        return
    def recv(timeout:int = 2) -> None:
        while ser.in_waiting > 0:
            data = ser.read_all()
            logging.info(f'[{ser.port}] <- {getSendStatus(data)} 0x{data.hex()}')
            time.sleep(timeout / 100.0)
    def sendWithRet(arr: list, timeout:int = 2) -> None:
        send(arr)
        recv(timeout=timeout)
    logging.info(f'Connected to {ser.name} at port{ser.port}')
    if not ser.isOpen():
        ser.open()
    logging.info(f'Check if serial port opened: [{ser.is_open}]')

    Instructions.connect(sendWithRet)
    Instructions.read_firmware_version(sendWithRet)

    ser.close()
    
if __name__ == '__main__':
    main()
    