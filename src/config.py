import logging
import sys
import os
from datetime import date
import random
import string

CHAR_POOL = string.ascii_lowercase + string.digits

def random_str(length: int = 6):
    return ''.join(random.choice(CHAR_POOL) for _ in range(length))

class Config():
    DEFAULT_LEVEL = logging.INFO
    DEFAULT_LOG_FORMAT = '%(asctime)s.%(msecs)03d %(levelname)s %(module)s - %(funcName)s: %(message)s'
    DEFAULT_TIME_FORMAT = '%Y-%m-%d %H:%M:%S'
    
    def _set_stdout(verbose: bool = True, prompt: str = 'setup complete [log -> stdout]'):
        logging.basicConfig(
            stream=sys.stdout, 
            level=Config.DEFAULT_LEVEL,
            format=Config.DEFAULT_LOG_FORMAT,
            datefmt=Config.DEFAULT_TIME_FORMAT
        )
        if verbose:
            logging.info(f'{prompt}')
    
    def _set_logger(log_path: str, log_name: str, verbose: bool = True, prompt: str = 'setup complete [log -> logger]'):
        os.makedirs(log_path, exist_ok=True)
        logging.basicConfig(
            filename=os.path.join(log_path, f'{log_name}.log'), 
            level=Config.DEFAULT_LEVEL,
            format=Config.DEFAULT_LOG_FORMAT,
            datefmt=Config.DEFAULT_TIME_FORMAT
        )
        if verbose:
            logging.info(f'{prompt} [log_path={log_path}], [log_name={log_name}]')
        
    def __set(self, stdout: bool, dry_run: bool, prompt: str = 'setup complete'):
        if stdout:
            Config._set_stdout(verbose=self.verbose)
        else:
            Config._set_logger(self.log_path, self.log_name, verbose=self.verbose)
        self.dry_run = dry_run
        if self.verbose:
            logging.info(f'{prompt} [stdout={stdout}], [dry_run={dry_run}]')
    
    def __init__(self, stdout: bool, log_path: str = 'log', log_name: str = f'{date.today()}', dry_run = False, verbose = True) -> None:
        self.log_path = log_path
        self.log_name = log_name
        self.verbose = verbose
        self.__set(stdout, dry_run, 'logging initialized')
    
    def reset(self, stdout: bool, dry_run: bool) -> None:
        self.__set(stdout, dry_run, 'reset complete')