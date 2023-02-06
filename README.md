# LaserPrinterDriver

[Progressive Lab Notes](https://moenupa.notion.site/Laser-Printer-0b9cb1b63b3e4126bc053467e02061a8)

This project aims to control a laser engraver using Python codes. There are 3 approaches in total that will achieve this goal.

1. Emulate Driver Traffic
2. Use decompiled JAR to find protocols

## Emulate Driver Traffic

Use Wireshark to capture USB traffic and python lib `usbrply` to emulate instructions for USB printer. See [usbrply pypl page](https://pypi.org/project/usbrply/)

### Capture USB traffic

1. Capture use pack using Wireshark
1. Save the instructions into a `.pcapng` file
1. Install Python lib `usbrply` using the following:

    ```sh
    python -m venv env
    source env/Scripts/activate
    pip install usbrply
    ```
1. Reconstruct Python code to emulate the same instructions using `usbrply`, as the following demonstrates:

    ```sh
    python env/Scripts/usbrply usbcap/my.pcapng > src/replay.py
    ```
1. Complete.

### Emulate Traffic

```sh
python src/replay.py
```

Note that there is a minor error, which due to the missing `bulkWrite()` function. Manual fix is required. Refer to [this sample](src/exp1.py) for the fix.

## Use Decompiled Jar to get the code

**Ongoing**. And most promising option.

It is known that the laser engravor uses driver [CH340](https://www.wch.cn/products/CH340.html) to provide a virtual COM port for usb connection.

The manufacturer provides a [compiled java program](docs/MACSetup/software/Installation%20step%203/Laser%20engraving%20machine.jar) for Mac OS. The driver is a `.jar` file, and currently decompiled into `decompiled` folder. As the source code is not very readable, the next step is to find the protocol used for communication by enumerating the instructions.

A part of the protocol is interpreted and verified, see [this file](src/sim.py).

## Switch to GRBL open source firmware

Manufacturer documentation is available at [GRBL User Manual](docs/User%20Manual/UserManual_GRBL/GRBL-UserManual(v01-220115).pdf).

Github repos: [GRBL](https://github.com/grbl/grbl), [LaserGRBL (GUI app)](https://github.com/arkypita/LaserGRBL)

This requires that GRBL is installed on the Arduino board, which will **replace the original firmware**, causing **existing software** to be **unusable**. The following steps are expected:

1. Install GRBL firmware
2. Install Arduino IDE
3. Download GRBL source code (in C)
4. Write own code to control the laser engraver (may not be Python)

At the same time, [https://github.com/arkypita/LaserGRBL](https://github.com/arkypita/LaserGRBL) is a good GUI application to control the machine.
