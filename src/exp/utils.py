import serial.tools.list_ports as ls
import inspect

def print_all_com_ports():
    ports = ls.comports()
    for p, des, hwid in sorted(ports):
        print(f'{p}: {des} [{hwid}]')

def get_object_properties(obj: object, start="_") -> list:
    attr = inspect.getmembers(obj, lambda a:not(inspect.isroutine(a)))
    return [a for a in attr if not(a[0].startswith(start))]