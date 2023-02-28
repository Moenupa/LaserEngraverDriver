# LaserPrinterDriver

[Progressive Lab Notes](https://moenupa.notion.site/Laser-Printer-0b9cb1b63b3e4126bc053467e02061a8)

This project aims to control a laser engraver using Python. Target engraver is [WAINLUX JL3](https://www.wainlux.com/products/jl3-laser-engraving-machine?variant=40467843776704). The code mimics protocol used by the manufacturer's software written in Java, downloadable from [their website](https://web.archive.org/web/20221201132749/http://dkjxz.com/).

## Communication Protocols

Laser engraver uses driver [CH34X](https://www.wch.cn/products/CH340.html) to communicate via a virtual serial port which is physically a USB port. The port is shown as `COM7` for windows and `/dev/ttyUSB0` for linux. Supported features of the original software are listed below.

## List of Features

- [x] Connect
- [x] Preview location (a rectangle region)
- [x] Move to location (a point)
- [x] Engrave a simple shape
- [ ] Config settings for engraving
- [ ] Transform shape

## Usage

Make sure you have Python 3.8+ installed. Then, install the dependencies using the following:

```sh
python --version
python -m venv env
source env/Scripts/activate
pip install -r requirements.txt
python src/main.py
```

<details>
  <summary>[Alternative Approaches]</summary>

  ## Switch to GRBL open source firmware

  Manufacturer documentation is available at [GRBL User Manual](docs/User%20Manual/UserManual_GRBL/GRBL-UserManual(v01-220115).pdf).

  Github repos: [GRBL](https://github.com/grbl/grbl), [LaserGRBL (GUI app)](https://github.com/arkypita/LaserGRBL)

  This requires that GRBL is installed on the Arduino board, which will **replace the original firmware**, causing **existing software** to be **unusable**. The following steps are expected:

  1. Install GRBL firmware
  2. Install Arduino IDE
  3. Download GRBL source code (in C)
  4. Write own code to control the laser engraver (may not be Python)

  At the same time, [https://github.com/arkypita/LaserGRBL](https://github.com/arkypita/LaserGRBL) is a good GUI application to control the machine.
</details>

<details>
  <summary>[Deprecated Approaches]</summary>

  ## Emulate Driver Traffic

  Use Wireshark to capture USB traffic and python lib `usbrply` to emulate instructions for USB printer. See [usbrply pypl page](https://pypi.org/project/usbrply/)

  [Deprecated] Reason: 

  ### Capture USB traffic

  1. Capture use pack using Wireshark
  1. Save the instructions into a `.pcapng` file
  1. Install Python lib `usbrply` using the following:
      ```sh
      python -m venv env
      source env/Scripts/activate
      pip install usbrply
      ```
  2. Reconstruct Python code to emulate the same instructions using `usbrply`, as the following demonstrates:
      ```sh
      python env/Scripts/usbrply usbcap/my.pcapng > src/replay.py
      ```
  3. Complete.

  ### Emulate Traffic

  ```sh
  python src/replay.py
  ```

  Note that there is a minor error, which due to the missing `bulkWrite()` function. Manual fix is required. Refer to [this sample](src/exp/exp1_libusb.py) for the fix.
</details>
