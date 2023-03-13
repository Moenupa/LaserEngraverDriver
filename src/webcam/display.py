import cv2
import os
import logging
import numpy as np
from typing import Callable
from filters import Filters
from src.config import Config, random_str
import matplotlib.pyplot as plt

ESC = 27
RESOURCE_PATH = 'res'
CAPTURE_PATH = os.path.join(RESOURCE_PATH, 'captures')
PATTERNS_PATH = os.path.join(RESOURCE_PATH, 'patterns')
LOGGING_PATH = os.path.join('log', 'img', random_str())

PATTERN_IMG = os.path.join(RESOURCE_PATH, 'template0.png')

def save(path: str, id: str | int, img: cv2.Mat, ext: str = 'png'):
    os.makedirs(path, exist_ok=True)
    logging.info(f'saving to {path}/{id}.{ext}')
    cv2.imwrite(f'{path}/{id}.{ext}', img)

PREPROCESSING_CALLBACK = {
    ord('p'): lambda id, img: save(PATTERNS_PATH, id, img),
}
CAPTURE_CALLBACK = {
    ord('c'): lambda id, img: save(CAPTURE_PATH, id, img),
}
SAVE_CALLBACK = {
    ord('s'): lambda id, img: save(RESOURCE_PATH, id, img),
}
LOGGING_CALLBACK = {
    ord('l'): lambda id, img: save(LOGGING_PATH, id, img)
}

class Display():
    @staticmethod
    def display(_callback: dict = SAVE_CALLBACK, *args, **kwargs):
        logging.info(f'displaying images for {len(args)}+{len(kwargs)} displays')
        while True:
            for k, v in enumerate(args):
                cv2.imshow(f'img_{k:02d}', v)
            for k, v in kwargs.items():
                cv2.imshow(f'{k}', v)
            
            key = cv2.waitKey(30)
            # execute callback functions once triggered
            if key in _callback:
                _callback_func = _callback[key]
                logging.info(f'callback {chr(key)} triggered for {len(args)}+{len(kwargs)} displayes')
                for k, v in enumerate(args):
                    _callback_func(k, v)
                for k, v in kwargs.items():
                    _callback_func(k, v)
            elif key == ESC:
                break
        cv2.destroyAllWindows()
        
    @staticmethod
    def video_display(apply_filters: Callable, _callback: dict = LOGGING_CALLBACK, descriptor: int = 4):
        cap = cv2.VideoCapture(descriptor)
        while True:
            _, frame = cap.read()
            frames = {k:v for k, v in apply_filters(frame)}
            for k, v in frames.items():
                cv2.imshow(k, v)
            
            key = cv2.waitKey(30)
            # execute callback functions once triggered
            if key in _callback:
                _callback_func = _callback[key]
                logging.info(f'callback {chr(key)} triggered for {len(frames)} displayes')
                for k, v in frames.items():
                    _callback_func(k, v)
            elif key == ESC:
                break
                
        
        cap.release()
        cv2.destroyAllWindows()

def get_plane(x : tuple, y: tuple):
    if len(x) == 4:
        x1, x2, x3, x4 = x
    elif len(x) == 2:
        x1, x3 = x2, x4 = x
    y1, y2 = y
    return np.array([[x1, y1], [x2, y2], [x3, y2], [x4, y1]], dtype='float32')

if __name__ == '__main__':
    config = Config(stdout=False)
    
    pattern = cv2.imread(PATTERN_IMG, cv2.CV_8U)
    w, h = pattern.shape[::-1]
    
    def apply_filters(frame):
        yield 'original', frame
        dest_xy = (400, 400)
        src_plane = get_plane((374, 349, 483, 471), (336, 396))
        dest_plane = get_plane((0, dest_xy[0]), (0, dest_xy[1]))
        tx = cv2.getPerspectiveTransform(src_plane, dest_plane)
        img_warpped = cv2.warpPerspective(frame, tx, dest_xy)
        # img_warpped = cv2.perspectiveTransform(frame, tx)
        yield 'warpped', img_warpped
        img_gray = Filters.gray(img_warpped)
        img_gray = Filters.scale(img_gray, 5, -255 * 4)
        yield 'gray', img_gray
        img_bin = Filters.binary(img_gray, 180)
        yield 'black and white', img_bin
        matched = cv2.matchTemplate(img_bin, pattern, cv2.TM_CCOEFF_NORMED)
        loc = np.where(matched >= 0.8)
        for pt in zip(*loc[::-1]):
            cv2.rectangle(img_warpped, pt, (pt[0] + w, pt[1] + h), (0,0,255), 2)
    
    Display.video_display(apply_filters)