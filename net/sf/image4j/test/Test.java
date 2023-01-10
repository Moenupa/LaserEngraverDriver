/*
 * Decompiled with CFR 0.150.
 */
package net.sf.image4j.test;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import net.sf.image4j.codec.bmp.BMPDecoder;
import net.sf.image4j.codec.bmp.BMPEncoder;
import net.sf.image4j.codec.ico.ICODecoder;
import net.sf.image4j.codec.ico.ICOEncoder;

public class Test {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage:\n\tTest <inputfile> <outputfile>");
            System.exit(1);
        }
        String strInFile = args[0];
        String strOutFile = args[1];
        InputStream in = null;
        try {
            List<Object> images;
            in = strInFile.startsWith("http:") ? new URL(strInFile).openStream() : new FileInputStream(strInFile);
            if (!strInFile.endsWith(".ico")) {
                images = new ArrayList<BufferedImage>(1);
                images.add(ImageIO.read(in));
                System.out.println("Read image " + strInFile + "...OK");
            } else {
                System.out.println("Decoding ICO file '" + strInFile + "'.");
                images = ICODecoder.read(in);
                System.out.println("ICO decoding...OK");
                System.out.println("  image count = " + images.size());
                System.out.println("  image summary:");
                for (int i = 0; i < images.size(); ++i) {
                    BufferedImage img = (BufferedImage)images.get(i);
                    int bpp = img.getColorModel().getPixelSize();
                    int width = img.getWidth();
                    int height = img.getHeight();
                    System.out.println("    # " + i + ": size=" + width + "x" + height + "; colour depth=" + bpp + " bpp");
                }
                System.out.println("  saving separate images:");
                String format = "png";
                for (int j = 0; j < images.size(); ++j) {
                    BufferedImage img = (BufferedImage)images.get(j);
                    String name = strOutFile + "-" + j;
                    File bmpFile = new File(name + ".bmp");
                    File pngFile = new File(name + ".png");
                    System.out.println("    writing '" + name + ".bmp'");
                    BMPEncoder.write(img, bmpFile);
                    System.out.println("    writing '" + name + ".png'");
                    ImageIO.write((RenderedImage)img, format, pngFile);
                }
                System.out.println("BMP encoding...OK");
                System.out.println("  reloading BMP files:");
                ArrayList<BufferedImage> images2 = new ArrayList<BufferedImage>(images.size());
                for (int k = 0; k < images.size(); ++k) {
                    String name = strOutFile + "-" + k + ".bmp";
                    File file = new File(name);
                    System.out.println("    reading '" + name + "'");
                    BufferedImage image = BMPDecoder.read(file);
                    images2.add(image);
                }
                System.out.println("BMP decoding...OK");
            }
            System.out.println("Encoding ICO file '" + strOutFile + "'.");
            File outFile = new File(strOutFile);
            ICOEncoder.write(images, outFile);
            System.out.println("ICO encoding...OK");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                in.close();
            }
            catch (IOException iOException) {}
        }
    }

    private static void usage() {
    }
}

