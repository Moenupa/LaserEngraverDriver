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
    cropped = img[offset[1]:offset[1] + size[1], offset[0]:offset[0] + size[0]]
    _, binary = cv2.threshold(cropped, 60, 255, cv2.THRESH_BINARY)
    blurred = cv2.GaussianBlur(binary, (3, 3), 0)
    Display.display(PREPROCESSING_CALLBACK, img, cropped, binary, blurred)
    

if __name__ == '__main__':
    config = Config(stdout=True)
    preprocessing('res/captures/capture1.jpg', (64, 50), (250, 250))