/*
 * Decompiled with CFR 0.150.
 */
package net.sf.image4j.io;

public class EndianUtils {
    public static short swapShort(short value) {
        return (short)((value & 0xFF00) >> 8 | (value & 0xFF) << 8);
    }

    public static int swapInteger(int value) {
        return (value & 0xFF000000) >> 24 | (value & 0xFF0000) >> 8 | (value & 0xFF00) << 8 | (value & 0xFF) << 24;
    }

    public static long swapLong(long value) {
        return (value & 0xFF00000000000000L) >> 56 | (value & 0xFF000000000000L) >> 40 | (value & 0xFF0000000000L) >> 24 | (value & 0xFF00000000L) >> 8 | (value & 0xFF000000L) << 8 | (value & 0xFF0000L) << 24 | (value & 0xFF00L) << 40 | (value & 0xFFL) << 56;
    }

    public static float swapFloat(float value) {
        int i = Float.floatToIntBits(value);
        i = EndianUtils.swapInteger(i);
        return Float.intBitsToFloat(i);
    }

    public static double swapDouble(double value) {
        long l = Double.doubleToLongBits(value);
        l = EndianUtils.swapLong(l);
        return Double.longBitsToDouble(l);
    }

    public static String toHexString(int i, boolean littleEndian, int bytes) {
        if (littleEndian) {
            i = EndianUtils.swapInteger(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(i));
        if (sb.length() % 2 != 0) {
            sb.insert(0, '0');
        }
        while (sb.length() < bytes * 2) {
            sb.insert(0, "00");
        }
        return sb.toString();
    }

    public static StringBuilder toCharString(StringBuilder sb, int i, int bytes, char def) {
        int shift = 24;
        for (int j = 0; j < bytes; ++j) {
            int b = i >> shift & 0xFF;
            char c = b < 32 ? def : (char)b;
            sb.append(c);
            shift -= 8;
        }
        return sb;
    }

    public static String toInfoString(int info) {
        StringBuilder sb = new StringBuilder();
        sb.append("Decimal: ").append(info);
        sb.append(", Hex BE: ").append(EndianUtils.toHexString(info, false, 4));
        sb.append(", Hex LE: ").append(EndianUtils.toHexString(info, true, 4));
        sb.append(", String BE: [");
        sb = EndianUtils.toCharString(sb, info, 4, '.');
        sb.append(']');
        sb.append(", String LE: [");
        sb = EndianUtils.toCharString(sb, EndianUtils.swapInteger(info), 4, '.');
        sb.append(']');
        return sb.toString();
    }
}

