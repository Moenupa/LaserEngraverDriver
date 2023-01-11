# LaserPrinterDriver

[Progressive Lab Notes](https://moenupa.notion.site/Laser-Printer-0b9cb1b63b3e4126bc053467e02061a8)

## Emulate Driver Instructions

Use Wireshark to capture USB traffic and python lib `usbrply` to emulate instructions for USB printer. See [usbrply Pypl Page](https://pypi.org/project/usbrply/)

### Capture USB traffic

1. Capture use pack using Wireshark
1. Save the instructions into a `.pcap` file
1. Install Python lib `usbrply` using the following:

    ```sh
    python -m venv env
    source env/bin/activate
    pip install usbrply
    ```
1. Reconstruct Python code to emulate the same instructions using `usbrply`, as the following demonstrates:

    ```sh
    python -m env/Scripts/usbrply src/my.pcapng > out/replay.py
    ```
1. Complete.

### Emulate Instructions

```sh
python out/replay.py
```

## Use Decompiled Jar to execute instructions on VCP Drivers

## Switch to GRBL open source 

Official documentation is available at [GRBL](docs/User%20Manual/UserManual_GRBL/GRBL-UserManual(v01-220115).pdf)
