import cv2
import os
from display import Display, PREPROCESSING_CALLBACK
from filters import Filters
from src.config import Config

PATTERNS_PATH = os.path.join('res', 'patterns')


def preprocessing(path: str, offset: tuple, size: tuple):
    os.makedirs(PATTERNS_PATH, exist_ok=True)
    img = cv2.imread(path)
    # range: first y, then x
    x, y = offset
    w, h = size
    cropped = img[y:y+h, x:x+w]
    resized = cv2.resize(cropped, (880, 880))
    _, binary = cv2.threshold(resized, 100, 255, cv2.THRESH_BINARY)
    restored = binary
    for i in range(5):
        blurred = cv2.GaussianBlur(restored, (3, 3), 0)
        _, restored = cv2.threshold(blurred, 100, 255, cv2.THRESH_BINARY)
    inverted = cv2.bitwise_not(restored)
    Display.display(PREPROCESSING_CALLBACK, img, cropped, resized, binary, restored, inverted)


if __name__ == '__main__':
    config = Config(stdout=True)
    preprocessing('res/captures/capture1.jpg', (69, 56), (239, 238))
