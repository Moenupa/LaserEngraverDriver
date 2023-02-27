import logging
import sys
from datetime import date

class Config():
    def __init__(self, stdout: bool, log:str = f'./log/{date.today()}.log', dry_run = False) -> None:
        if stdout:
            logging.basicConfig(
                stream=sys.stdout, level=logging.DEBUG,
                format='%(asctime)s.%(msecs)03d %(levelname)s %(module)s - %(funcName)s: %(message)s',
                datefmt='%Y-%m-%d %H:%M:%S',
            )
        else:
            logging.basicConfig(
                filename=log, level=logging.DEBUG,
                format='%(asctime)s.%(msecs)03d %(levelname)s %(module)s - %(funcName)s: %(message)s',
                datefmt='%Y-%m-%d %H:%M:%S',
            )

        self.dry_run = dry_run