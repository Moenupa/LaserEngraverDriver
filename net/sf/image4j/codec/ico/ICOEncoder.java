/*
 * Decompiled with CFR 0.150.
 */
package net.sf.image4j.codec.ico;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import net.sf.image4j.codec.bmp.BMPEncoder;
import net.sf.image4j.codec.bmp.InfoHeader;
import net.sf.image4j.codec.ico.IconEntry;
import net.sf.image4j.io.LittleEndianOutputStream;
import net.sf.image4j.util.ConvertUtil;

public class ICOEncoder {
    private ICOEncoder() {
    }

    public static void write(BufferedImage image, File file) throws IOException {
        ICOEncoder.write(image, -1, file);
    }

    public static void write(BufferedImage image, OutputStream os) throws IOException {
        ICOEncoder.write(image, -1, os);
    }

    public static void write(List<BufferedImage> images, OutputStream os) throws IOException {
        ICOEncoder.write(images, null, null, os);
    }

    public static void write(List<BufferedImage> images, File file) throws IOException {
        ICOEncoder.write(images, null, file);
    }

    public static void write(List<BufferedImage> images, int[] bpp, File file) throws IOException {
        ICOEncoder.write(images, bpp, (OutputStream)new FileOutputStream(file));
    }

    public static void write(List<BufferedImage> images, int[] bpp, boolean[] compress, File file) throws IOException {
        ICOEncoder.write(images, bpp, compress, new FileOutputStream(file));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void write(BufferedImage image, int bpp, File file) throws IOException {
        FileOutputStream fout = new FileOutputStream(file);
        try {
            BufferedOutputStream out = new BufferedOutputStream(fout);
            ICOEncoder.write(image, bpp, (OutputStream)out);
            out.flush();
        }
        finally {
            try {
                fout.close();
            }
            catch (IOException iOException) {}
        }
    }

    public static void write(BufferedImage image, int bpp, OutputStream os) throws IOException {
        ArrayList<BufferedImage> list = new ArrayList<BufferedImage>(1);
        list.add(image);
        ICOEncoder.write(list, new int[]{bpp}, new boolean[]{false}, os);
    }

    public static void write(List<BufferedImage> images, int[] bpp, OutputStream os) throws IOException {
        ICOEncoder.write(images, bpp, null, os);
    }

    public static void write(List<BufferedImage> images, int[] bpp, boolean[] compress, OutputStream os) throws IOException {
        BufferedImage img;
        int i;
        LittleEndianOutputStream out = new LittleEndianOutputStream(os);
        int count = images.size();
        ICOEncoder.writeFileHeader(count, 1, out);
        int fileOffset = 6 + count * 16;
        ArrayList<InfoHeader> infoHeaders = new ArrayList<InfoHeader>(count);
        ArrayList<BufferedImage> converted = new ArrayList<BufferedImage>(count);
        ArrayList<byte[]> compressedImages = null;
        if (compress != null) {
            compressedImages = new ArrayList<byte[]>(count);
        }
        ImageWriter pngWriter = null;
        for (i = 0; i < count; ++i) {
            img = images.get(i);
            int b = bpp == null ? -1 : bpp[i];
            BufferedImage imgc = b == -1 ? img : ICOEncoder.convert(img, b);
            converted.add(imgc);
            InfoHeader ih = BMPEncoder.createInfoHeader(imgc);
            IconEntry e = ICOEncoder.createIconEntry(ih);
            if (compress != null) {
                if (compress[i]) {
                    if (pngWriter == null) {
                        pngWriter = ICOEncoder.getPNGImageWriter();
                    }
                    byte[] compressedImage = ICOEncoder.encodePNG(pngWriter, imgc);
                    compressedImages.add(compressedImage);
                    e.iSizeInBytes = compressedImage.length;
                } else {
                    compressedImages.add(null);
                }
            }
            ih.iHeight *= 2;
            e.iFileOffset = fileOffset;
            fileOffset += e.iSizeInBytes;
            e.write(out);
            infoHeaders.add(ih);
        }
        for (i = 0; i < count; ++i) {
            img = images.get(i);
            BufferedImage imgc = (BufferedImage)converted.get(i);
            if (compress == null || !compress[i]) {
                InfoHeader ih = (InfoHeader)infoHeaders.get(i);
                ih.write(out);
                if (ih.sBitCount <= 8) {
                    IndexColorModel icm = (IndexColorModel)imgc.getColorModel();
                    BMPEncoder.writeColorMap(icm, out);
                }
                ICOEncoder.writeXorBitmap(imgc, ih, out);
                ICOEncoder.writeAndBitmap(img, out);
                continue;
            }
            byte[] compressedImage = (byte[])compressedImages.get(i);
            out.write(compressedImage);
        }
    }

    public static void writeFileHeader(int count, int type, LittleEndianOutputStream out) throws IOException {
        out.writeShortLE((short)0);
        out.writeShortLE((short)type);
        out.writeShortLE((short)count);
    }

    public static IconEntry createIconEntry(InfoHeader ih) {
        int size;
        IconEntry ret = new IconEntry();
        ret.bWidth = ih.iWidth == 256 ? 0 : ih.iWidth;
        ret.bHeight = ih.iHeight == 256 ? 0 : ih.iHeight;
        ret.bColorCount = ih.iNumColors >= 256 ? 0 : ih.iNumColors;
        ret.bReserved = 0;
        ret.sPlanes = 1;
        ret.sBitCount = ih.sBitCount;
        int cmapSize = BMPEncoder.getColorMapSize(ih.sBitCount);
        int xorSize = BMPEncoder.getBitmapSize(ih.iWidth, ih.iHeight, ih.sBitCount);
        int andSize = BMPEncoder.getBitmapSize(ih.iWidth, ih.iHeight, 1);
        ret.iSizeInBytes = size = ih.iSize + cmapSize + xorSize + andSize;
        ret.iFileOffset = 0;
        return ret;
    }

    public static void writeAndBitmap(BufferedImage img, LittleEndianOutputStream out) throws IOException {
        WritableRaster alpha = img.getAlphaRaster();
        if (img.getColorModel() instanceof IndexColorModel && img.getColorModel().hasAlpha()) {
            int w = img.getWidth();
            int h = img.getHeight();
            int bytesPerLine = BMPEncoder.getBytesPerLine1(w);
            byte[] line = new byte[bytesPerLine];
            IndexColorModel icm = (IndexColorModel)img.getColorModel();
            WritableRaster raster = img.getRaster();
            for (int y = h - 1; y >= 0; --y) {
                for (int x = 0; x < w; ++x) {
                    int bi = x / 8;
                    int i = x % 8;
                    int p = raster.getSample(x, y, 0);
                    int a = icm.getAlpha(p);
                    int b = ~a & 1;
                    line[bi] = ICOEncoder.setBit(line[bi], i, b);
                }
                out.write(line);
            }
        } else if (alpha == null) {
            int h = img.getHeight();
            int w = img.getWidth();
            int bytesPerLine = BMPEncoder.getBytesPerLine1(w);
            byte[] line = new byte[bytesPerLine];
            for (int i = 0; i < bytesPerLine; ++i) {
                line[i] = 0;
            }
            for (int y = h - 1; y >= 0; --y) {
                out.write(line);
            }
        } else {
            int w = img.getWidth();
            int h = img.getHeight();
            int bytesPerLine = BMPEncoder.getBytesPerLine1(w);
            byte[] line = new byte[bytesPerLine];
            for (int y = h - 1; y >= 0; --y) {
                for (int x = 0; x < w; ++x) {
                    int bi = x / 8;
                    int i = x % 8;
                    int a = alpha.getSample(x, y, 0);
                    int b = ~a & 1;
                    line[bi] = ICOEncoder.setBit(line[bi], i, b);
                }
                out.write(line);
            }
        }
    }

    private static byte setBit(byte bits, int index, int bit) {
        int mask = 1 << 7 - index;
        bits = (byte)(bits & ~mask);
        bits = (byte)(bits | bit << 7 - index);
        return bits;
    }

    private static void writeXorBitmap(BufferedImage img, InfoHeader ih, LittleEndianOutputStream out) throws IOException {
        WritableRaster raster = img.getRaster();
        switch (ih.sBitCount) {
            case 1: {
                BMPEncoder.write1(raster, out);
                break;
            }
            case 4: {
                BMPEncoder.write4(raster, out);
                break;
            }
            case 8: {
                BMPEncoder.write8(raster, out);
                break;
            }
            case 24: {
                BMPEncoder.write24(raster, out);
                break;
            }
            case 32: {
                WritableRaster alpha = img.getAlphaRaster();
                BMPEncoder.write32(raster, alpha, out);
            }
        }
    }

    public static BufferedImage convert(BufferedImage img, int bpp) {
        BufferedImage ret = null;
        switch (bpp) {
            case 1: {
                ret = ConvertUtil.convert1(img);
                break;
            }
            case 4: {
                ret = ConvertUtil.convert4(img);
                break;
            }
            case 8: {
                ret = ConvertUtil.convert8(img);
                break;
            }
            case 24: {
                int b = img.getColorModel().getPixelSize();
                if (b == 24 || b == 32) {
                    ret = img;
                    break;
                }
                ret = ConvertUtil.convert24(img);
                break;
            }
            case 32: {
                int b2 = img.getColorModel().getPixelSize();
                ret = b2 == 24 || b2 == 32 ? img : ConvertUtil.convert32(img);
            }
        }
        return ret;
    }

    private static ImageWriter getPNGImageWriter() {
        ImageWriter ret = null;
        Iterator<ImageWriter> itr = ImageIO.getImageWritersByFormatName("png");
        if (itr.hasNext()) {
            ret = itr.next();
        }
        return ret;
    }

    private static byte[] encodePNG(ImageWriter pngWriter, BufferedImage img) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ImageOutputStream output = ImageIO.createImageOutputStream(bout);
        pngWriter.setOutput(output);
        pngWriter.write(img);
        bout.flush();
        byte[] ret = bout.toByteArray();
        return ret;
    }
}

