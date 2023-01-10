/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Hua_ban;
import examples.Tu_yuan;
import java.util.ArrayList;
import java.util.List;

public class Che_xiao {
    static List<List<Tu_yuan>> ty_shuzu_cx = new ArrayList<List<Tu_yuan>>();
    static int dang_qian = 0;
    static int bu_shu = 20;

    public static void tian_jia() {
        if (dang_qian < bu_shu) {
            if (ty_shuzu_cx.size() > dang_qian) {
                for (int i = 0; i < ty_shuzu_cx.size() - dang_qian + 1; ++i) {
                    ty_shuzu_cx.remove(ty_shuzu_cx.size() - 1);
                }
            }
            ty_shuzu_cx.add(Tu_yuan.fu_zhi(Hua_ban.ty_shuzu));
            dang_qian = ty_shuzu_cx.size();
        } else {
            ty_shuzu_cx.remove(0);
            ty_shuzu_cx.add(Tu_yuan.fu_zhi(Hua_ban.ty_shuzu));
            dang_qian = ty_shuzu_cx.size();
        }
    }

    public static void che_xiao() {
        if (dang_qian > 1) {
            Hua_ban.ty_shuzu = Tu_yuan.fu_zhi(ty_shuzu_cx.get(dang_qian - 1 - 1));
            --dang_qian;
        }
    }

    public static void chong_zuo() {
        if (dang_qian < bu_shu) {
            Hua_ban.ty_shuzu = Tu_yuan.fu_zhi(ty_shuzu_cx.get(dang_qian + 1 - 1));
            ++dang_qian;
        }
    }
}

