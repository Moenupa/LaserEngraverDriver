import serial
import logging

from utils import int2bytes, Config

config = Config(stdout=False)
logging.basicConfig(filename='./log/serial.log', level=logging.DEBUG)

def send(ser: serial.Serial, b: bytes) -> None:
    res = ser.write(b)
    ser.flush()
    logging.info(f'Send[{ser.port}] -> {b} ret{res}')
    return

def recv(ser: serial.Serial) -> str:
    data = ''
    while ser.in_waiting > 0:
        data += str(ser.read_all)
    logging.info(f'Recv[{ser.port}] <- {data}')
    return data

def main():
    with serial.Serial('COM7', timeout=2.0) as ser:
        logging.info(f'[Status]Connected to {ser.name} COM at port{ser.port}')
        if not ser.isOpen():
            ser.open()
        logging.info(f'[Status]Check if serial port opened: [{ser.is_open}]')

        send(ser, bytes([]))
        recv(ser)

        ser.close()
    
    print('Terminated...')
    
if __name__ == '__main__':
    main()
    