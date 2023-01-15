# import serial
# ser = serial.Serial('COM7')

BYTEMAX = 256
def int2Bints(i):
    '''
    Convert an integer to a list of two bytes.
    Overflow (>= 2^16) is ignored.
    '''
    return (i // BYTEMAX) % BYTEMAX, i % BYTEMAX
def parse_4(w, h, x, y):
    return int2Bints(w) + int2Bints(h) + int2Bints(x + 67 + h //2) + int2Bints(y + w//2)
def adj_settings(sd, gl):
    return int2Bints(sd) + int2Bints(gl) + [0, 0, 0, 0]
def p():
    return []

msgs = {
    'version': [[0, 0, 0, 0]],
    'read_version': [[-1, 0, 4, 0]],
    'handshake': [[10, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0], p],
    'rect': [[32, 0, 11], parse_4],
    'settings': [[32, 0, 11], adj_settings],
    'settings_qg': [[37, 0, 11], adj_settings],
    'unknown1': [[7, 0, 4, 0]],
    'unknown2': [[6, 0, 4, 0]],
    'tuoji': [[35, 0, 38, ]], # suspected main engraving function mainJFrame line 1914
    'unknown3': [[10, 0, 4, 0]],
    'unknown4': [[36, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0], p],
    'keep-alive': [[11, 0, 4, 0]],
    'firmware_reset': [[-2, 0, 4, 0]],
    
}

def parse_msg(msg, **kwargs):
    return msg[0] + msg[-1](**kwargs) if len(msg) > 1 else msg[0]

def send_msg(msg, **kwargs):
    # ser.write(bytes(parse_msg(msgs[msg], **kwargs)))
    print(bytes(parse_msg(msgs[msg], **kwargs)))
    
if __name__ == '__main__':
    send_msg('unknown3')