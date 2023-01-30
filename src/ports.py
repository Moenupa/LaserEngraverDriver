import serial.tools.list_ports as ls

def print_all_com_ports():
    ports = ls.comports()

    for p, des, hwid in sorted(ports):
        print(f'{p}: {des} [{hwid}]')