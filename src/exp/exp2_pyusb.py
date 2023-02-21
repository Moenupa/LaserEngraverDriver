''''
attempt to use pyusb lib 1.2.1
failed on Jan. 31 2023

problem: pyusb only works on certain drivers,
such as WinUSB. The engraver uses a special driver called
CH340 (which is not supported by pyusb v1.x).

it cannot even find any devices as of the moment.
the following code will generate a no-element list.
'''

import usb.core
import usb.backend.libusb1
import usb.util

idVender = 0x1a86
idProduct = 0x7523

def list_dev() -> None:
    devs = usb.core.find(find_all=True)
    for d in devs:
        print(usb.util.get_string(d,128,d.iManufacturer))
        print(usb.util.get_string(d,128,d.iProduct))
        print(d.idProduct,d.idVendor)

def find_dev():
    dev = usb.core.find(find_all = True, idVendor=0x1A86)
    if dev is None:
        raise ValueError('Our device is not connected')
    else:
        print(f'Success! {dev}')

if __name__ == '__main__':
    print(list(usb.core.find(find_all=True)))
    list_dev()
    # find_dev()