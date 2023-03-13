import cv2

class Filters():
    @staticmethod
    def gray(frame):
        frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        frame = cv2.GaussianBlur(frame, (3, 3), 0)
        return frame

    @staticmethod
    def scale(frame, alpha, beta):
        cv2.convertScaleAbs(frame, frame, alpha, beta)
        return frame
    
    @staticmethod
    def binary(frame, threshold):
        return cv2.threshold(frame, threshold, 255, cv2.THRESH_BINARY)[1]
    
    @staticmethod
    def invert(frame):        
        return cv2.bitwise_not(frame)
    
    @staticmethod
    def filter(frame, params = None):
        frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        frame = cv2.convertScaleAbs(frame, frame, 2, -300.0)
        frame = cv2.GaussianBlur(frame, (3, 3), 0)
        cv2.imshow('scale', frame)
        # _ = cv2.threshold(frame, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU, frame)
        # cv2.imshow('threshold', frame)
        # frame = cv2.Sobel(src=frame, ddepth=cv2.CV_64F, dx=1, dy=1, ksize=3)
        # PatternDetection.test_vals(frame, [20 * i for i in range(1, 4)], [80, 100])
        frame = cv2.Canny(image=frame, threshold1=40, threshold2=70)
        return frame