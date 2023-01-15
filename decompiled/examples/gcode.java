/*
 * Decompiled with CFR 0.150.
 */
package examples;

class gcode {
    static double fen_bian_lv = 0.05;
    static double x_double = 0.0;
    static double y_double = 0.0;
    static int bu = 8;
    static Point[] zhixian = new Point[0];

    gcode() {
    }

    static double[] qu_dian_double(String str) {
        String ss;
        int f;
        double ddd;
        double[] dou = new double[]{0.0, 0.0};
        int xw = str.indexOf("X");
        int kw = str.indexOf(" ");
        dou[0] = ddd = Double.valueOf(str.substring(xw + 1, kw - xw - 1)).doubleValue();
        xw = str.indexOf("Y", 0);
        kw = str.indexOf(" ", xw);
        if (kw <= 0) {
            kw = str.length();
        }
        if ((f = (ss = str.substring(xw + 1, kw - xw - 1)).indexOf("F", 0)) >= 1) {
            ss = str.substring(xw + 1, f - 1);
        }
        dou[1] = Double.valueOf(ss);
        return dou;
    }

    static Point[] jiaru(Point[] sz, Point cy) {
        Point[] fanhui = new Point[sz.length + 1];
        for (int i = 0; i < sz.length; ++i) {
            fanhui[i] = sz[i];
        }
        fanhui[sz.length] = cy;
        return fanhui;
    }

    class Point {
        int x = 0;
        int y = 0;

        Point() {
        }
    }
}

