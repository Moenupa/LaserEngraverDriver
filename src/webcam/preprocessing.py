import cv2
import os
from display import Display, save
from src.config import Config
import numpy as np

PATTERNS_PATH = os.path.join('res', 'patterns')
PREPROCESSING_CALLBACK = {
    ord('p'): lambda id, img: save(PATTERNS_PATH, id, img, 'bmp'),
}

os.makedirs(PATTERNS_PATH, exist_ok=True)

def yield_contours(img: np.ndarray, thresh: int = 100):
    img_gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    # this thresh=100 may vary, with respect to different inputs
    _, img_thresh = cv2.threshold(img_gray, thresh, 255, cv2.THRESH_BINARY)
    img_inverted = cv2.bitwise_not(img_thresh)
    contours, _ = cv2.findContours(image=img_inverted, mode=cv2.RETR_EXTERNAL, method=cv2.CHAIN_APPROX_SIMPLE)
    # cv2.drawContours(img, contours, 1, (0, 255, 0), 1)
    for _, c in enumerate(contours):
        area = cv2.contourArea(c)
        if area > 100:
            x, y, w, h = cv2.boundingRect(cv2.approxPolyDP(c, 3, True))
            yield crop(img, (x, y), (w, h))

def preprocessing(src: np.ndarray, actual_size: tuple = (880, 880), thresh: int = 100):
    # resize the pattern to pixel size
    resized = cv2.resize(src, actual_size)
    _, binary = cv2.threshold(resized, thresh, 255, cv2.THRESH_BINARY)
    smooth = cv2.medianBlur(binary, 13)
    final = cv2.medianBlur(smooth, 7)
    Display.display(PREPROCESSING_CALLBACK, resized, binary, smooth, final, source=src)

def crop(src: np.ndarray, offset: tuple, size: tuple) -> np.ndarray:
    x, y = offset
    w, h = size
    return src[y:y+h, x:x+w] # this is not bug, cv2.imread() uses y,x

if __name__ == '__main__':
    config = Config(stdout=True)
    original = cv2.imread('res/captures/capture1.jpg')
    cropped = crop(original, (0, 56), (500, 238))
    imgs_contour = list(yield_contours(cropped))
    # Display.display(PREPROCESSING_CALLBACK, *imgs_contour, source=cropped)
    
    # preprocessing(imgs_contour[1])
