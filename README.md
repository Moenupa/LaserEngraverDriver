# USBPrinter

Use python package `usbrply` to emulate instructions for USB printer.

See [usbrply Pypl Page](https://pypi.org/project/usbrply/)

## Procedures

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
    usbrply --device-hi -p my.pcap > replay.py
    ```
1. Complete.
