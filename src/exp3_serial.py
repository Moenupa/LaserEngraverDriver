import serial
import logging
import time
from typing import Tuple, Callable

from utils import Config
from utils import arr2bytes, remapping

config = Config(stdout=False)

class Protocols():
    def ack(data: bytes) -> Tuple[int, str]:
        """
        Get execution status based on args. 

        Args:
            data (bytes): last received bytes

        Returns:
            Tuple[int, str]: status code and status message

        Status Lookup Table:
            `0` if `LAST_RCV=9: success`
            `-1` if `LAST_RCV=8: failure`
            `1` if `else: unknown`   
        """
        lookup = {
            9: (0, "success"),
            8: (-1, "failure")
        }
        return lookup.get(data[0], (1, "unknown"))


class EngraverConnection():
    '''
    Instruction Class that contains all decrypted protocols

    Pass a send function and a recieve function if required
    '''

    busy = False

    def __init__(self) -> None:
        self.ser = serial.Serial(
            port='COM7', baudrate=115200, xonxoff=True,
            timeout=3.0, write_timeout=2.0,
            stopbits=serial.STOPBITS_ONE
        )
        logging.info(
            f'Connected to {self.ser.name} at port{self.ser.port}')
        if not self.ser.is_open:
            self.ser.open()
        logging.info(f'Check if serial port opened: [{self.ser.is_open}]')

    def send(self, data: list, timeout: float = 1.0) -> None:
        """send a list of bytes to serial port

        Args:
            data (list): data to send
        """
        if not self.ser.is_open:
            self.ser.open()
        sum = 0
        count = 0
        while sum != len(bytes(data)) and count < timeout * 10:
            res = self.ser.write(bytes(data))
            self.ser.flush()
            logging.info(f'[{self.ser.port}] -> ({res}) 0x{bytes(data).hex()}')
            time.sleep(0.1)
            sum += res
            count += 1

    def receive(self, timeout: float = 2.0) -> None:
        if not self.ser.is_open:
            self.ser.open()
        count = 0
        while self.ser.in_waiting > 0 and count < timeout * 10:
            data = self.ser.read_all()
            level = logging.ERROR if Protocols.ack(data)[0] < 0 else logging.INFO
            logging.log(level, 
                f'[{self.ser.port}] <- {Protocols.ack(data)} 0x{data.hex()}')
            time.sleep(0.1)
            count += 1

    def sendWithACK(self, arr: list, timeout: float = 2.0, wait: float = 0.1, interval: float = 0.2) -> None:
        self.send(arr)
        time.sleep(wait)
        self.receive(timeout=timeout)
        time.sleep(interval)

    def close(self) -> None:
        self.ser.close()
        logging.info(f'disconnected\n{"-"*60}')

    def connect(self) -> None:
        logging.info('')
        self.sendWithACK([10, 0, 4, 0])

    def read_firmware_version(self) -> None:
        logging.info('')
        self.sendWithACK([0xff, 0, 4, 0])
        
    def start_firmware_update(self) -> None:
        logging.info('')
        self.sendWithACK([0xfe, 0, 4, 0])

    def end_firmware_update(self) -> None:
        logging.info('')
        self.sendWithACK([4, 0, 4, 0])

    def preview_location(self, w: int, h: int, x: int, y: int) -> None:
        logging.info(f'(x={x}, y={y}, x2={x+w}, y2={y+h})')
        self.sendWithACK(
            [32, 0, 11] + arr2bytes(remapping(w=w, h=h, x=x, y=y)))

    def stop_preview(self) -> None:
        logging.info('')
        self.sendWithACK([33, 0, 4, 0])

    def move_to(self, x: int, y: int) -> None:
        logging.info(f'(x={x}, y={y})')
        self.preview_location(w=0, h=0, x=x, y=y)
        time.sleep(1)
        self.stop_preview()

    def engrave(self) -> None:
        logging.info('')
        if (eval(input("Starting ![Engraving]! Input for Confirmation: "))):
            logging.log(logging.WARN, 'Engraving Confirmed')
            self.sendWithACK([22, 0, 4, 0])
            self.send



if __name__ == '__main__':
    connection = EngraverConnection()

    connection.connect()
    connection.read_firmware_version()
    connection.preview_location(*[2000, 2000, 2000, 2000])
    time.sleep(5)
    connection.stop_preview()
    time.sleep(1)
    connection.move_to(0, 0)
    connection.close()
