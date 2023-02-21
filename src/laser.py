import serial
import logging
import time
from typing import Tuple, Callable

from utils import Config
from utils import arr2DbBytes, remapping

config = Config(stdout=False)


class Protocols():
    def ack(data: bytes) -> Tuple[int, str]:
        """
        Get execution status based on args. 

        Args:
            `data` (bytes): last received bytes

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

    def __init__(self) -> None:
        self.ser = serial.Serial(
            port='COM7', baudrate=115200, xonxoff=True,
            timeout=3.0, write_timeout=2.0,
            stopbits=serial.STOPBITS_ONE
        )
        self.timestep = 0.01
        logging.info(
            f'Connected to {self.ser.name} at port{self.ser.port}')
        logging.info(f'Check if serial port opened: [{self.open()}]')
    
    def open(self) -> bool:
        if not self.ser.is_open:
            self.ser.open()
        return self.ser.is_open

    def send(self, data: list, timeout: float) -> None:
        """send a list of bytes to serial port

        Args:
            `data` (list): data to send
            `timeout` (float): timeout in seconds
        """
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
            level = logging.ERROR if Protocols.ack(
                data)[0] < 0 else logging.INFO
            logging.log(level,
                        f'[{self.ser.port}] <- {Protocols.ack(data)} 0x{data.hex()}')
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
        logging.info(f'disconnected\n{"-"*80}')

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
        
    def auxilary_positioning(self, status : bool) -> None:
        """Toggle auxilary positioning mode

        Args:
            status (bool): true for on, false for off
        """
        logging.info(f'status={status}')
        if status:
            self.sendWithACK([6, 0, 4, 0], send_timeout=2)
        else:
            self.sendWithACK([7, 0, 4, 0], send_timeout=2)
            
    def update_carve_settings(self, depth : int, power:  int) -> None:
        logging.info(f'depth={depth}, power={power}')
        self.sendWithACK([37, 0, 11] + arr2DbBytes([depth, power]) + [0, 0, 0, 0])
    
    def update_accuracy_settings(self, weak_light : int, accuracy:  int) -> None:
        logging.info(f'weak_light={weak_light}, accuracy={accuracy}')
        self.sendWithACK([37, 0, 11] + [weak_light, accuracy] + [0, 0, 0, 0])

    def preview_location(self, w: int, h: int, x: int, y: int) -> None:
        logging.info(f'(x={x}, y={y}, x2={x+w}, y2={y+h})')
        self.sendWithACK(
            [32, 0, 11] + arr2DbBytes(remapping(w=w, h=h, x=x, y=y)))

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
            # start engraving
            self.sendWithACK([22, 0, 4, 0])
            self.sendWithACK([10, 0, 4, 0])
            # end engraving
            self.sendWithACK([36, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0], ack_timeout=0.2, instr_interval=0.5)


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
