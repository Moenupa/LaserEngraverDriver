/*
 * Decompiled with CFR 0.150.
 */
package net.sf.image4j.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.IndexColorModel;

public class ConvertUtil {
    public static BufferedImage convert1(BufferedImage src) {
        IndexColorModel icm = new IndexColorModel(1, 2, new byte[]{0, -1}, new byte[]{0, -1}, new byte[]{0, -1});
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 12, icm);
        ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
        cco.filter(src, dest);
        return dest;
    }

    public static BufferedImage convert4(BufferedImage src) {
        int[] cmap = new int[]{0, 0x800000, 32768, 0x808000, 128, 0x800080, 32896, 0x808080, 0xC0C0C0, 0xFF0000, 65280, 0xFFFF00, 255, 0xFF00FF, 65535, 0xFFFFFF};
        return ConvertUtil.convert4(src, cmap);
    }

    public static BufferedImage convert4(BufferedImage src, int[] cmap) {
        IndexColorModel icm = new IndexColorModel(4, cmap.length, cmap, 0, false, 1, 0);
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 12, icm);
        ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
        cco.filter(src, dest);
        return dest;
    }

    public static BufferedImage convert8(BufferedImage src) {
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 13);
        ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
        cco.filter(src, dest);
        return dest;
    }

    public static BufferedImage convert24(BufferedImage src) {
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 1);
        ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
        cco.filter(src, dest);
        return dest;
    }

    public static BufferedImage convert32(BufferedImage src) {
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 2);
        ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), null);
        cco.filter(src, dest);
        return dest;
    }
}

