/*
 * Decompiled with CFR 0.150.
 */
package examples;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.Arrays;

public class Tu_pian {
    public static BufferedImage zoomImage(BufferedImage im, double resizeTimes) {
        BufferedImage result = null;
        try {
            int width = im.getWidth();
            int height = im.getHeight();
            int toWidth = (int)((double)width * resizeTimes);
            int toHeight = (int)((double)height * resizeTimes);
            result = new BufferedImage(toWidth, toHeight, 2);
            result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, 4), 0, 0, null);
        }
        catch (Exception e) {
            System.out.println("\u521b\u5efa\u7f29\u7565\u56fe\u53d1\u751f\u5f02\u5e38" + e.getMessage());
        }
        return result;
    }

    private static int colorToRGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel <<= 8;
        newPixel += red;
        newPixel <<= 8;
        newPixel += green;
        newPixel <<= 8;
        return newPixel += blue;
    }

    public static BufferedImage hui_du(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayImage = new BufferedImage(width, height, image.getType());
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int color = image.getRGB(i, j);
                int r = color >> 16 & 0xFF;
                int g = color >> 8 & 0xFF;
                int b = color & 0xFF;
                int gray = 0;
                gray = (int)((double)(r + g + b) / 3.0);
                grayImage.setRGB(i, j, Tu_pian.colorToRGB(0, gray, gray, gray));
            }
        }
        return grayImage;
    }

    public static BufferedImage convertGreyImgByFloyd(BufferedImage img, int zhi) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = new int[width * height];
        img.getRGB(0, 0, width, height, pixels, 0, width);
        int[] gray = new int[height * width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int grey = pixels[width * i + j];
                int red = (grey & 0xFF0000) >> 16;
                if ((red += 128 - zhi) > 255) {
                    red = 255;
                }
                if (red < 0) {
                    red = 0;
                }
                gray[width * i + j] = red;
            }
        }
        int e = 0;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int g = gray[width * i + j];
                if (g >= 128) {
                    pixels[width * i + j] = 0x1FFFFFF;
                    e = g - 255;
                } else {
                    pixels[width * i + j] = -16777216;
                    e = g - 0;
                }
                if (j < width - 1 && i < height - 1) {
                    int n = width * i + j + 1;
                    gray[n] = gray[n] + 3 * e / 8;
                    int n2 = width * (i + 1) + j;
                    gray[n2] = gray[n2] + 3 * e / 8;
                    int n3 = width * (i + 1) + j + 1;
                    gray[n3] = gray[n3] + e / 4;
                    continue;
                }
                if (j == width - 1 && i < height - 1) {
                    int n = width * (i + 1) + j;
                    gray[n] = gray[n] + 3 * e / 8;
                    continue;
                }
                if (j >= width - 1 || i != height - 1) continue;
                int n = width * i + j + 1;
                gray[n] = gray[n] + e / 4;
            }
        }
        BufferedImage mBitmap = new BufferedImage(width, height, 2);
        mBitmap.setRGB(0, 0, width, height, pixels, 0, width);
        return mBitmap;
    }

    public static BufferedImage heibai(BufferedImage bb, int zhi) {
        BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
        int[] pixels = new int[bb.getWidth() * bb.getHeight()];
        nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
        for (int i = 0; i < pixels.length; ++i) {
            int clr = pixels[i];
            int red = (clr & 0xFF0000) >> 16;
            pixels[i] = red < zhi ? -16777216 : 0x1FFFFFF;
        }
        BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
        mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
        return mBitmap;
    }

    public static BufferedImage fanse(BufferedImage bb) {
        BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
        int[] pixels = new int[bb.getWidth() * bb.getHeight()];
        nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
        for (int i = 0; i < pixels.length; ++i) {
            int clr = pixels[i];
            int red = (clr & 0xFF0000) >> 16;
            pixels[i] = red == 255 ? -16777216 : 0x1FFFFFF;
        }
        BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
        mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
        return mBitmap;
    }

    public static BufferedImage jing_xiang_x(BufferedImage bb) {
        BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
        int[] pixels = new int[bb.getWidth() * bb.getHeight()];
        int[] pixels2 = new int[bb.getWidth() * bb.getHeight()];
        nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
        int k = nb.getWidth();
        int g = nb.getHeight();
        for (int i = 0; i < g; ++i) {
            for (int j = 0; j < k; ++j) {
                pixels2[i * k + j] = pixels[i * k + (k - j - 1)];
            }
        }
        BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
        mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels2, 0, nb.getWidth());
        return mBitmap;
    }

    public static BufferedImage jing_xiang_y(BufferedImage bb) {
        BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
        int[] pixels = new int[bb.getWidth() * bb.getHeight()];
        int[] pixels2 = new int[bb.getWidth() * bb.getHeight()];
        nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
        int k = nb.getWidth();
        int g = nb.getHeight();
        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < g; ++j) {
                pixels2[j * k + i] = pixels[(g - j - 1) * k + i];
            }
        }
        BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
        mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels2, 0, nb.getWidth());
        return mBitmap;
    }

    public static BufferedImage qu_lunkuo(BufferedImage img, int zhi) {
        int j;
        int i;
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = new int[width * height];
        int[] pixels2 = new int[(width + 2) * (height + 2)];
        Arrays.fill(pixels2, 0xFFFFFF);
        img.getRGB(0, 0, width, height, pixels, 0, width);
        int[] gray = new int[(height + 2) * (width + 2)];
        for (i = 0; i < height + 2; ++i) {
            for (j = 0; j < width + 2; ++j) {
                int grey;
                int red;
                gray[(width + 2) * i + j] = i == 0 || j == 0 || j == width + 1 || i == height + 1 ? 0xFFFFFF : ((red = ((grey = pixels[width * (i - 1) + j - 1]) & 0xFF0000) >> 16) > zhi ? 0xFFFFFF : -16777216);
            }
        }
        width += 2;
        for (i = 1; i < (height += 2); ++i) {
            for (j = 1; j < width; ++j) {
                if (gray[width * i + j] != gray[width * i + (j - 1)]) {
                    if (gray[width * i + j] == -16777216) {
                        pixels2[width * i + j] = -16777216;
                    } else {
                        pixels2[width * i + (j - 1)] = -16777216;
                    }
                }
                if (gray[width * i + j] == gray[width * (i - 1) + j]) continue;
                if (gray[width * i + j] == -16777216) {
                    pixels2[width * i + j] = -16777216;
                    continue;
                }
                pixels2[width * (i - 1) + j] = -16777216;
            }
        }
        BufferedImage mBitmap = new BufferedImage(width, height, 2);
        mBitmap.setRGB(0, 0, width, height, pixels2, 0, width);
        return mBitmap;
    }

    static int[] getGray(int[] pixels, int width, int height) {
        int[] gray = new int[width * height];
        for (int i = 0; i < width - 1; ++i) {
            for (int j = 0; j < height - 1; ++j) {
                int index = width * j + i;
                int rgba = pixels[index];
                int g = ((rgba & 0xFF0000) >> 16) * 3 + ((rgba & 0xFF00) >> 8) * 6 + (rgba & 0xFF) * 1;
                gray[index] = g / 10;
            }
        }
        return gray;
    }

    static int[] getInverse(int[] gray) {
        int[] inverse = new int[gray.length];
        int size = gray.length;
        for (int i = 0; i < size; ++i) {
            inverse[i] = 255 - gray[i];
        }
        return inverse;
    }

    static int[] guassBlur(int[] inverse, int width, int height) {
        int[] guassBlur = new int[inverse.length];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int temp = width * j + i;
                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    guassBlur[temp] = 0;
                    continue;
                }
                int i0 = width * (j - 1) + (i - 1);
                int i1 = width * (j - 1) + i;
                int i2 = width * (j - 1) + (i + 1);
                int i3 = width * j + (i - 1);
                int i4 = width * j + i;
                int i5 = width * j + (i + 1);
                int i6 = width * (j + 1) + (i - 1);
                int i7 = width * (j + 1) + i;
                int i8 = width * (j + 1) + (i + 1);
                int sum = inverse[i0] + 2 * inverse[i1] + inverse[i2] + 2 * inverse[i3] + 4 * inverse[i4] + 2 * inverse[i5] + inverse[i6] + 2 * inverse[i7] + inverse[i8];
                guassBlur[temp] = sum /= 16;
            }
        }
        return guassBlur;
    }

    static int[] deceasecolorCompound(int[] guassBlur, int[] gray, int width, int height) {
        int[] output = new int[guassBlur.length];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int index = j * width + i;
                int b = guassBlur[index];
                int a = gray[index];
                int temp = a + a * b / (256 - b);
                float ex = (float)(temp * temp) * 1.0f / 255.0f / 255.0f;
                temp = (int)((float)temp * ex);
                output[index] = a = Math.min(temp, 255);
            }
        }
        return output;
    }

    static BufferedImage create(int[] pixels, int[] output, int width, int height) {
        int size = pixels.length;
        for (int i = 0; i < size; ++i) {
            int pixel;
            int gray = output[i];
            output[i] = pixel = pixels[i] & 0xFF000000 | gray << 16 | gray << 8 | gray;
        }
        BufferedImage mBitmap = new BufferedImage(width, height, 2);
        mBitmap.setRGB(0, 0, width, height, output, 0, width);
        return mBitmap;
    }

    public static BufferedImage su_miao(BufferedImage bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getRGB(0, 0, width, height, pixels, 0, width);
        int[] gray = Tu_pian.getGray(pixels, width, height);
        int[] inverse = Tu_pian.getInverse(gray);
        int[] guassBlur = Tu_pian.guassBlur(inverse, width, height);
        int[] output = Tu_pian.deceasecolorCompound(guassBlur, gray, width, height);
        return Tu_pian.create(pixels, output, width, height);
    }

    public static BufferedImage su_miao2(BufferedImage old) {
        BufferedImage b1 = Tu_pian.discolor(old);
        b1 = Tu_pian.invert(b1);
        float[][] matric = Tu_pian.gaussian2DKernel(3, 3.0f);
        b1 = Tu_pian.convolution(b1, matric);
        b1 = Tu_pian.deceaseColorCompound(old, b1);
        ColorSpace cs1 = ColorSpace.getInstance(1003);
        ColorConvertOp op1 = new ColorConvertOp(cs1, null);
        BufferedImage b2 = new BufferedImage(old.getWidth(), old.getHeight(), 1);
        op1.filter(b1, b2);
        return b2;
    }

    public static BufferedImage discolor(BufferedImage sourceImage) {
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage retImage = new BufferedImage(width, height, 2);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int color1 = sourceImage.getRGB(i, j);
                int a1 = color1 >> 24 & 0xFF;
                int r1 = color1 >> 16 & 0xFF;
                int g1 = color1 >> 8 & 0xFF;
                int b1 = color1 & 0xFF;
                double sumA = a1;
                double sumR = 0.0;
                double sumG = 0.0;
                double sumB = 0.0;
                sumG = sumB = (double)r1 * 0.299 + (double)g1 * 0.587 + (double)b1 * 0.114;
                sumR = sumB;
                int result = (int)sumA << 24 | (int)sumR << 16 | (int)sumG << 8 | (int)sumB;
                retImage.setRGB(i, j, result);
            }
        }
        return retImage;
    }

    public static BufferedImage invert(BufferedImage sourceImage) {
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage retImage = new BufferedImage(width, height, 2);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int b1;
                int b;
                int g1;
                int g;
                int r1;
                int r;
                int color1 = sourceImage.getRGB(i, j);
                int a1 = color1 >> 24 & 0xFF;
                int a = a1;
                int result = a << 24 | (r = 255 - (r1 = color1 >> 16 & 0xFF)) << 16 | (g = 255 - (g1 = color1 >> 8 & 0xFF)) << 8 | (b = 255 - (b1 = color1 & 0xFF));
                if (result > 255) {
                    result = 255;
                }
                retImage.setRGB(i, j, result);
            }
        }
        return retImage;
    }

    public static BufferedImage deceaseColorCompound(BufferedImage sourceImage, BufferedImage targetImage) {
        int width = sourceImage.getWidth() > targetImage.getWidth() ? sourceImage.getWidth() : targetImage.getWidth();
        int height = sourceImage.getHeight() > targetImage.getHeight() ? sourceImage.getHeight() : targetImage.getHeight();
        BufferedImage retImage = new BufferedImage(width, height, 2);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (i >= sourceImage.getWidth() || j >= sourceImage.getHeight()) {
                    if (i >= targetImage.getWidth() || j >= targetImage.getHeight()) {
                        retImage.setRGB(i, j, 0);
                        continue;
                    }
                    retImage.setRGB(i, j, targetImage.getRGB(i, j));
                    continue;
                }
                if (i >= targetImage.getWidth() || j >= targetImage.getHeight()) {
                    retImage.setRGB(i, j, sourceImage.getRGB(i, j));
                    continue;
                }
                int color1 = sourceImage.getRGB(i, j);
                int color2 = targetImage.getRGB(i, j);
                int a1 = color1 >> 24 & 0xFF;
                int r1 = color1 >> 16 & 0xFF;
                int g1 = color1 >> 8 & 0xFF;
                int b1 = color1 & 0xFF;
                int a2 = color2 >> 24 & 0xFF;
                int r2 = color2 >> 16 & 0xFF;
                int g2 = color2 >> 8 & 0xFF;
                int b2 = color2 & 0xFF;
                int a = Tu_pian.deceaseColorChannel(a1, a2);
                int r = Tu_pian.deceaseColorChannel(r1, r2);
                int g = Tu_pian.deceaseColorChannel(g1, g2);
                int b = Tu_pian.deceaseColorChannel(b1, b2);
                int result = a << 24 | r << 16 | g << 8 | b;
                retImage.setRGB(i, j, result);
            }
        }
        return retImage;
    }

    private static int deceaseColorChannel(int source, int target) {
        int result = source + source * target / (256 - target);
        return result > 255 ? 255 : result;
    }

    public static BufferedImage convolution(BufferedImage image, float[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();
        int radius = kernel.length / 2;
        BufferedImage retImage = new BufferedImage(width, height, 2);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                double sumA = 0.0;
                double sumR = 0.0;
                double sumG = 0.0;
                double sumB = 0.0;
                for (int x = i - radius; x <= i + radius; ++x) {
                    for (int y = j - radius; y <= j + radius; ++y) {
                        int posX;
                        int n = x < 0 ? 0 : (posX = x >= width ? width - 1 : x);
                        int posY = y < 0 ? 0 : (y >= height ? height - 1 : y);
                        int color = image.getRGB(posX, posY);
                        int a = color >> 24 & 0xFF;
                        int r = color >> 16 & 0xFF;
                        int g = color >> 8 & 0xFF;
                        int b = color & 0xFF;
                        int kelX = x - i + radius;
                        int kelY = y - j + radius;
                        sumA += (double)(kernel[kelX][kelY] * (float)a);
                        sumR += (double)(kernel[kelX][kelY] * (float)r);
                        sumG += (double)(kernel[kelX][kelY] * (float)g);
                        sumB += (double)(kernel[kelX][kelY] * (float)b);
                    }
                }
                int blurColor = (int)sumA << 24 | (int)sumR << 16 | (int)sumG << 8 | (int)sumB;
                retImage.setRGB(i, j, blurColor);
            }
        }
        return retImage;
    }

    public static float[][] gaussian2DKernel(int radius, float sigma) {
        int y;
        int x;
        int length = 2 * radius;
        float[][] matric = new float[length + 1][length + 1];
        float sigmaSquare2 = 2.0f * sigma * sigma;
        float sum = 0.0f;
        for (x = -radius; x <= radius; ++x) {
            for (y = -radius; y <= radius; ++y) {
                matric[radius + x][radius + y] = (float)(Math.pow(Math.E, (float)(-(x * x + y * y)) / sigmaSquare2) / (Math.PI * (double)sigmaSquare2));
                sum += matric[radius + x][radius + y];
            }
        }
        for (x = 0; x < length; ++x) {
            y = 0;
            while (y < length) {
                float[] arrf = matric[x];
                int n = y++;
                arrf[n] = arrf[n] / sum;
            }
        }
        return matric;
    }
}

