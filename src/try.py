import serial

def main():
    ser = serial.Serial('COM7', timeout=2.0)
    print(f'Name: {ser.name}, Port: {ser.port}')

    if not ser.isOpen():
        ser.open()
    
    print(f'Serial Port Opened: {ser.is_open}')

def recv(ser: serial.Serial):
    data = ''
    while ser.in_waiting() > 0:
        data += str(ser.read_all())

    print(f'recv: {data}')

if __name__ == '__main__':
    main()
