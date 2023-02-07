import serial.tools.list_ports as ls
import inspect
import serial

BYTEMAX = 0x0100
TWOBYTEMAX = 0xffff

class Config():
    stdout = False
    def __init__(self, stdout: bool) -> None:
        self.stdout = stdout

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
def parse_4(w, h, x, y):
    return int2Bints(w) + int2Bints(h) + int2Bints(x + 67 + h //2) + int2Bints(y + w//2)
def adj_settings(sd, gl):
    return int2Bints(sd) + int2Bints(gl) + [0, 0, 0, 0]

msgs = {
    'version': [[0, 0, 0, 0]],
    'read_version': [[-1, 0, 4, 0]],
    'handshake': [[10, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0]],
    'rect': [[32, 0, 11], parse_4],
    'settings': [[32, 0, 11], adj_settings],
    'settings_qg': [[37, 0, 11], adj_settings],
    'unknown1': [[7, 0, 4, 0]],
    'unknown2': [[6, 0, 4, 0]],
    'tuoji': [[35, 0, 38, ]], # suspected main engraving function mainJFrame line 1914
    'unknown3': [[10, 0, 4, 0]],
    'unknown4': [[36, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0]],
    'keep-alive': [[11, 0, 4, 0]],
    'firmware_reset': [[-2, 0, 4, 0]],
}

def parse_msg(msg : str, *args) -> str:
    return msg[0] + msg[-1](*args) if len(msg) > 1 else msg[0]

def send_msg(ser : serial.Serial, msg : str, *args) -> None:
    if config.stdout:
        print("raw:   ", parse_msg(msgs[msg], *args))
        print("stdout:", bytes(parse_msg(msgs[msg], *args)))
    else:
        ser.write(bytes(parse_msg(msgs[msg], *args)))

if __name__ == '__main__':
    for i in [17, 255, 256]:
        print(f'int {i} to bytes: {int2bytes(i)}')
    print_all_com_ports()
    