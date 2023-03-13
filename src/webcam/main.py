import cv2
import numpy as np
# import matplotlib.pyplot as plt

from filters import Filters


class PatternDetection():

    def __init__(self, dev_n: int = 4) -> None:
        self.cap = cv2.VideoCapture(dev_n)

    def close(self) -> None:
        self.cap.release()
        cv2.destroyAllWindows()

    @staticmethod
    def test_vals(frame, lo_vals, hi_vals):
        combinations = [(i, j) for i in lo_vals for j in hi_vals if i < j]
        for id, (lo, hi) in enumerate(combinations):
            cv2.imshow(f'canny {lo}:{hi}',
                       cv2.Canny(image=frame, threshold1=lo, threshold2=hi))

    def match(self, pattern='res/pattern.jpg', threshold=0.8):
        template = cv2.imread(pattern, 0)
        if template is None:
            raise ValueError
        w, h = template.shape[::-1]

        while True:
            ret, frame = self.cap.read()
            img_gray = Filters.gray(frame)
            img_scale = Filters.scale(img_gray, 5, -1000)
            img_inverted = Filters.invert(img_scale)
            res = cv2.matchTemplate(img_inverted, template,
                                    cv2.TM_CCOEFF_NORMED)
            loc = np.where(res >= threshold)
            for pt in zip(*loc[::-1]):
                cv2.rectangle(frame, pt, (pt[0] + w, pt[1] + h), (0, 0, 255),
                              2)
            cv2.imshow('bounding box', frame)
            cv2.imshow('scale', img_inverted)
            if (cv2.waitKey(30) == 27):
                return

    @staticmethod
    def filter(frame, params=None):
        frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        frame = cv2.GaussianBlur(frame, (3, 3), 0)
        frame = cv2.convertScaleAbs(frame, frame, 2, -300.0)
        cv2.imshow('scale', frame)
        # _ = cv2.threshold(frame, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU, frame)
        # cv2.imshow('threshold', frame)
        # frame = cv2.Sobel(src=frame, ddepth=cv2.CV_64F, dx=1, dy=1, ksize=3)
        # PatternDetection.test_vals(frame, [20 * i for i in range(1, 4)], [80, 100])
        frame = cv2.Canny(image=frame, threshold1=40, threshold2=70)
        return frame

    def edge_detection(self, thresholds):
        lo, hi = thresholds

        while True:
            ret, frame = self.cap.read()
            img_gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
            img_blur = cv2.GaussianBlur(img_gray, (3, 3), 0)

            img_filter = PatternDetection.filter(frame)
            img_edge = np.uint8(img_filter)

            contours, hierarchy = cv2.findContours(img_edge, cv2.RETR_EXTERNAL,
                                                   cv2.CHAIN_APPROX_SIMPLE)
            width = max([0] + [cv2.boundingRect(c)[2] for c in contours])
            height = max([0] + [cv2.boundingRect(c)[3] for c in contours])
            num = sum(
                1 for c in contours
                if cv2.boundingRect(c)[2] > 0 and cv2.boundingRect(c)[3] > 0)
            cv2.drawContours(frame, contours, -1, (0, 0, 255), 3)
            cv2.putText(frame, f'w={width}, h={height}, num={num}', (0, 50),
                        cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 255, 0))

            # cv2.imshow('edges', img_filter)
            cv2.imshow('original', frame)
            # press escape to exit
            if (cv2.waitKey(30) == 27):
                return


if __name__ == '__main__':
    # press esc to exit from cv2 window
    detection = PatternDetection()
    # detection.edge_detection((0, 50))
    detection.match()
    detection.close()
