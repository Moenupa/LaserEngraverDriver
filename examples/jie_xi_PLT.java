/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Hua_ban;
import examples.Tu_yuan;
import java.awt.geom.GeneralPath;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class jie_xi_PLT {
    void add_lj(GeneralPath lj) {
        lj.setWindingRule(0);
        Tu_yuan ty = new Tu_yuan();
        ty = Tu_yuan.chuang_jian(0, null);
        ty.lu_jing = new GeneralPath(lj);
        Hua_ban.ty_shuzu.add(ty);
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = false;
        }
        Hua_ban.ty_shuzu.get((int)(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void jie_xi_PLT(File file) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            String tempStr;
            reader = new BufferedReader(new FileReader(file));
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        String plt = sbf.toString();
        plt.replaceAll("\r|\n", "");
        String[] strArr = plt.split(";");
        GeneralPath lj = new GeneralPath();
        boolean yi = true;
        boolean jue_dui = true;
        double d_x = 0.0;
        double d_y = 0.0;
        double q_x = 0.0;
        double q_y = 0.0;
        for (int i = 0; i < strArr.length; ++i) {
            double y;
            double x;
            String[] zb2;
            String ml = strArr[i].toUpperCase();
            String zb = "";
            ml = strArr[i].substring(0, 2);
            if (ml.equals("PU")) {
                zb = strArr[i].substring(2, strArr[i].length());
                zb2 = zb.split(" ");
                if (zb2.length != 2) continue;
                x = Double.valueOf(zb2[0]) / 40.0 / Hua_ban.fen_bian_lv;
                y = Double.valueOf(zb2[1]) / 40.0 / Hua_ban.fen_bian_lv;
                y = 0.0 - y;
                if (jue_dui) {
                    d_x = x;
                    d_y = y;
                } else {
                    d_x += x;
                    d_y += y;
                }
                if (yi) {
                    lj.moveTo(d_x, d_y);
                    yi = false;
                } else {
                    if (q_x != d_x || q_y == d_y) {
                        // empty if block
                    }
                    this.add_lj(lj);
                    lj = new GeneralPath();
                    lj.moveTo(d_x, d_y);
                }
                q_x = d_x;
                q_y = d_y;
                continue;
            }
            if (ml.equals("PD")) {
                zb = strArr[i].substring(2, strArr[i].length());
                zb2 = zb.split(" ");
                if (zb2.length != 2) continue;
                x = Double.valueOf(zb2[0]) / 40.0 / Hua_ban.fen_bian_lv;
                y = Double.valueOf(zb2[1]) / 40.0 / Hua_ban.fen_bian_lv;
                y = 0.0 - y;
                if (jue_dui) {
                    d_x = x;
                    d_y = y;
                } else {
                    d_x += x;
                    d_y += y;
                }
                lj.lineTo(d_x, d_y);
                continue;
            }
            if (ml.equals("PA")) {
                zb = strArr[i].substring(2, strArr[i].length());
                zb2 = zb.split(" ");
                if (zb2.length != 2) continue;
                x = Double.valueOf(zb2[0]) / 40.0 / Hua_ban.fen_bian_lv;
                y = Double.valueOf(zb2[1]) / 40.0 / Hua_ban.fen_bian_lv;
                y = 0.0 - y;
                jue_dui = true;
                if (jue_dui) {
                    d_x = x;
                    d_y = y;
                } else {
                    d_x += x;
                    d_y += y;
                }
                lj.lineTo(d_x, d_y);
                continue;
            }
            if (!ml.equals("PR") || (zb2 = (zb = strArr[i].substring(2, strArr[i].length())).split(" ")).length != 2) continue;
            x = Double.valueOf(zb2[0]) / 40.0 / Hua_ban.fen_bian_lv;
            y = Double.valueOf(zb2[1]) / 40.0 / Hua_ban.fen_bian_lv;
            y = 0.0 - y;
            jue_dui = false;
            if (jue_dui) {
                d_x = x;
                d_y = y;
            } else {
                d_x += x;
                d_y += y;
            }
            lj.lineTo(d_x, d_y);
        }
        this.add_lj(lj);
    }
}

