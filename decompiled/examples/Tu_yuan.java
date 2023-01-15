/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Dian;
import examples.Hua_ban;
import examples.Tu_pian;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Tu_yuan
implements Serializable {
    public static final int lei_xing_tupian = 1;
    public static final int lei_xing_lujing = 0;
    public int lei_xing = 0;
    public transient BufferedImage wei_tu = null;
    public transient BufferedImage wei_tu_yuan = null;
    public int[] wei_tu_ = null;
    public int[] wei_tu_yuan_ = null;
    public int wt_w = 0;
    public int wt_g = 0;
    public int wty_w = 0;
    public int wty_g = 0;
    public int chuli_fs = 1;
    public boolean chuli_fan = false;
    public boolean chuli_jxx = false;
    public boolean chuli_jxy = false;
    public boolean tian_chong = false;
    public static int tian_chong_md = 5;
    public int yu_zhi = 50;
    public GeneralPath lu_jing = new GeneralPath();
    public boolean xuan_zhong = false;
    public AffineTransform Tx = new AffineTransform();
    public static Rectangle zui_zhong_wjx = new Rectangle();
    public static Rectangle wei_tu_wjx = new Rectangle();
    public static Rectangle shi_liang_wjx = new Rectangle();
    public static Rectangle shu_biao = new Rectangle();
    public static boolean tuo = false;
    public double jiao_du = 0.0;
    public static int dk_gonglv = 100;
    public static int dk_shendu = 10;
    public static int dk_gonglv_sl = 100;
    public static int dk_shendu_sl = 10;
    public static int dk_cishu = 1;
    public static List<Dian> dian = null;
    static int tu_kuan = 0;
    static int tu_gao = 0;
    static int da;

    public static List<Tu_yuan> fu_zhi(List<Tu_yuan> ty) {
        ArrayList<Tu_yuan> fh = new ArrayList<Tu_yuan>();
        for (int i = 0; i < ty.size(); ++i) {
            fh.add(Tu_yuan.fu_zhi(ty.get(i)));
        }
        return fh;
    }

    public static Tu_yuan fu_zhi(Tu_yuan ty) {
        Tu_yuan fh = Tu_yuan.chuang_jian(ty.lei_xing, ty.wei_tu);
        fh.Tx = new AffineTransform(ty.Tx);
        fh.xuan_zhong = ty.xuan_zhong;
        fh.lu_jing = new GeneralPath(ty.lu_jing);
        fh.chuli_fan = ty.chuli_fan;
        fh.chuli_fs = ty.chuli_fs;
        fh.chuli_jxx = ty.chuli_jxx;
        fh.chuli_jxy = ty.chuli_jxy;
        fh.tian_chong = ty.tian_chong;
        fh.yu_zhi = ty.yu_zhi;
        return fh;
    }

    void ping_yi(int x, int y) {
        AffineTransform Tx2 = AffineTransform.getTranslateInstance(x, y);
        Tx2.concatenate(this.Tx);
        this.Tx = Tx2;
    }

    void xuan_zhuan(double jiao, double zx, double zy) {
        this.jiao_du += jiao;
        jiao = jiao * 3.14 / 180.0;
        AffineTransform Tx2 = AffineTransform.getRotateInstance(jiao, zx, zy);
        Tx2.concatenate(this.Tx);
        this.Tx = Tx2;
    }

    void suo_fang(double beishu_x, double beishu_y) {
        AffineTransform Tx2 = AffineTransform.getScaleInstance(beishu_x, beishu_y);
        Tx2.concatenate(this.Tx);
        this.Tx = Tx2;
    }

    static void zhong_xin(List<Tu_yuan> sz) {
        Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
        Rectangle rect2 = lu_jing2.getBounds();
        double x = rect2.x + rect2.width / 2;
        double y = rect2.y + rect2.height / 2;
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
            Hua_ban.ty_shuzu.get(i).ping_yi((int)(x - (double)(rect.x + rect.width / 2)), (int)(y - (double)(rect.y + rect.height / 2)));
        }
    }

    public static Rectangle qu_jv_xing(Tu_yuan ty) {
        GeneralPath lu_jing2 = new GeneralPath(ty.lu_jing);
        lu_jing2.transform(ty.Tx);
        Rectangle rect = lu_jing2.getBounds();
        return rect;
    }

    public static Rectangle qu_jv_xing(List<Tu_yuan> sz) {
        GeneralPath lu_jing2 = new GeneralPath();
        for (int i = 0; i < sz.size(); ++i) {
            if (!sz.get((int)i).xuan_zhong) continue;
            GeneralPath lu_jing3 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing3.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            lu_jing2.append(lu_jing3, true);
        }
        Rectangle rect = lu_jing2.getBounds();
        return rect;
    }

    public static void kuang_xuan(List<Tu_yuan> sz, Rectangle rect) {
        for (int i = 1; i < sz.size(); ++i) {
            GeneralPath lu_jing3 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing3.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = rect.contains(lu_jing3.getBounds());
        }
    }

    public static Tu_yuan chuang_jian_wen_zi(String ss, Font zt, boolean sl) {
        BufferedImage bimg = new BufferedImage(10, 10, 2);
        Graphics2D g2d = (Graphics2D)bimg.getGraphics();
        Font font = new Font(zt.getName(), zt.getStyle(), zt.getSize());
        int g = g2d.getFontMetrics().getHeight();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics(font);
        LineMetrics line = fm.getLineMetrics(ss, g2d);
        String[] sz = ss.split("\n");
        int da = 0;
        for (int i = 0; i < sz.length; ++i) {
            int dq = fm.stringWidth(sz[i]);
            if (dq <= da) continue;
            da = dq;
        }
        BufferedImage bimg2 = new BufferedImage(da, (int)(line.getAscent() + line.getDescent()) * sz.length, 2);
        Graphics2D g2d2 = (Graphics2D)bimg2.getGraphics();
        g2d2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d2.setBackground(Color.WHITE);
        g2d2.setFont(font);
        g2d2.clearRect(0, 0, bimg2.getWidth(), bimg2.getHeight());
        g2d2.setColor(Color.BLACK);
        for (int i = 0; i < sz.length; ++i) {
            g2d2.drawString(sz[i], 0.0f, line.getAscent() + (line.getAscent() + (float)(g + Hua_ban.gao)) * (float)i);
        }
        if (!sl) {
            Tu_yuan fh = Tu_yuan.chuang_jian(1, bimg2);
            return fh;
        }
        GeneralPath lj = Tu_yuan.to_ls(bimg2);
        Tu_yuan fh = Tu_yuan.chuang_jian(0, null);
        fh.lu_jing = new GeneralPath(lj);
        return fh;
    }

    public static Tu_yuan chuang_jian_wen_zi_shu(String ss, Font zt, int gao, boolean sl) {
        BufferedImage bimg = new BufferedImage(10, 10, 2);
        Graphics2D g2d = (Graphics2D)bimg.getGraphics();
        Font font = new Font(zt.getName(), zt.getStyle(), zt.getSize());
        g2d.setFont(font);
        int k = g2d.getFontMetrics().stringWidth("\u4fe1");
        int g = g2d.getFontMetrics().getHeight();
        FontMetrics fm = g2d.getFontMetrics(font);
        LineMetrics line = fm.getLineMetrics(ss, g2d);
        BufferedImage bimg2 = new BufferedImage(k, ss.length() * (g + gao), 2);
        Graphics2D g2d2 = (Graphics2D)bimg2.getGraphics();
        g2d2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d2.setBackground(Color.WHITE);
        g2d2.setFont(font);
        g2d2.clearRect(0, 0, bimg2.getWidth(), bimg2.getHeight());
        g2d2.setColor(Color.BLACK);
        String tempStr = new String();
        for (int i = 0; i < ss.length(); ++i) {
            tempStr = ss.substring(i, i + 1);
            g2d2.drawString(tempStr, 0.0f, line.getAscent() + (float)((g + gao) * i));
        }
        if (!sl) {
            Tu_yuan fh = Tu_yuan.chuang_jian(1, bimg2);
            return fh;
        }
        GeneralPath lj = Tu_yuan.to_ls(bimg2);
        Tu_yuan fh = Tu_yuan.chuang_jian(0, null);
        fh.lu_jing = new GeneralPath(lj);
        return fh;
    }

    public static Tu_yuan chuang_jian(int leixing, BufferedImage wt) {
        Tu_yuan ty = new Tu_yuan();
        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
        Rectangle rect = lu_jing2.getBounds();
        ty.Tx.translate(rect.x, rect.y);
        AffineTransform sf = AffineTransform.getScaleInstance(Hua_ban.quan_beishu, Hua_ban.quan_beishu);
        ty.Tx.concatenate(sf);
        switch (leixing) {
            case 0: {
                ty.lei_xing = 0;
                ty.lu_jing.moveTo(0.0f, 0.0f);
                ty.lu_jing.lineTo(400.0f, 0.0f);
                ty.lu_jing.lineTo(400.0f, 400.0f);
                ty.lu_jing.lineTo(0.0f, 400.0f);
                ty.lu_jing.closePath();
                break;
            }
            case 1: {
                double bi = 0.0;
                if (wt.getWidth() > wt.getHeight()) {
                    if (wt.getWidth() > 1600) {
                        bi = 1600.0 / (double)wt.getWidth();
                        wt = Tu_pian.zoomImage(wt, bi);
                    }
                } else if (wt.getHeight() > 1600) {
                    bi = 1600.0 / (double)wt.getHeight();
                    wt = Tu_pian.zoomImage(wt, bi);
                }
                ty.wei_tu_yuan = wt;
                ty.lei_xing = 1;
                ty.chuli_fs = 1;
                ty.wei_tu = Tu_pian.hui_du(wt);
                ty.wei_tu = Tu_pian.heibai(ty.wei_tu, 128);
                ty.lu_jing.moveTo(0.0f, 0.0f);
                ty.lu_jing.lineTo(wt.getWidth(), 0.0f);
                ty.lu_jing.lineTo(wt.getWidth(), wt.getHeight());
                ty.lu_jing.lineTo(0.0f, wt.getHeight());
                ty.lu_jing.closePath();
                break;
            }
            case 2: {
                ty.lei_xing = 0;
                Ellipse2D.Float d = new Ellipse2D.Float(1.0f, 1.0f, 400.0f, 400.0f);
                ty.lu_jing.append(d, false);
                break;
            }
            case 3: {
                ty.lei_xing = 0;
                ty.lu_jing.moveTo(197.0f, 102.0f);
                ty.lu_jing.lineTo(212.0f, 69.0f);
                ty.lu_jing.lineTo(224.0f, 48.0f);
                ty.lu_jing.lineTo(242.0f, 27.0f);
                ty.lu_jing.lineTo(266.0f, 10.0f);
                ty.lu_jing.lineTo(304.0f, 0.0f);
                ty.lu_jing.lineTo(343.0f, 10.0f);
                ty.lu_jing.lineTo(363.0f, 27.0f);
                ty.lu_jing.lineTo(378.0f, 48.0f);
                ty.lu_jing.lineTo(387.0f, 69.0f);
                ty.lu_jing.lineTo(393.0f, 102.0f);
                ty.lu_jing.lineTo(390.0f, 150.0f);
                ty.lu_jing.lineTo(372.0f, 208.0f);
                ty.lu_jing.lineTo(343.0f, 264.0f);
                ty.lu_jing.lineTo(295.0f, 322.0f);
                ty.lu_jing.lineTo(197.0f, 394.0f);
                ty.lu_jing.lineTo(98.0f, 322.0f);
                ty.lu_jing.lineTo(50.0f, 264.0f);
                ty.lu_jing.lineTo(20.0f, 208.0f);
                ty.lu_jing.lineTo(3.0f, 150.0f);
                ty.lu_jing.lineTo(0.0f, 102.0f);
                ty.lu_jing.lineTo(6.0f, 69.0f);
                ty.lu_jing.lineTo(15.0f, 48.0f);
                ty.lu_jing.lineTo(29.0f, 27.0f);
                ty.lu_jing.lineTo(50.0f, 10.0f);
                ty.lu_jing.lineTo(88.0f, 0.0f);
                ty.lu_jing.lineTo(128.0f, 10.0f);
                ty.lu_jing.lineTo(151.0f, 27.0f);
                ty.lu_jing.lineTo(170.0f, 48.0f);
                ty.lu_jing.lineTo(183.0f, 69.0f);
                ty.lu_jing.closePath();
                break;
            }
            case 4: {
                ty.lei_xing = 0;
                ty.lu_jing.moveTo(121.0f, 0.0f);
                ty.lu_jing.lineTo(149.0f, 93.0f);
                ty.lu_jing.lineTo(241.0f, 94.0f);
                ty.lu_jing.lineTo(169.0f, 149.0f);
                ty.lu_jing.lineTo(196.0f, 241.0f);
                ty.lu_jing.lineTo(122.0f, 188.0f);
                ty.lu_jing.lineTo(46.0f, 241.0f);
                ty.lu_jing.lineTo(72.0f, 149.0f);
                ty.lu_jing.lineTo(0.0f, 94.0f);
                ty.lu_jing.lineTo(92.0f, 93.0f);
                ty.lu_jing.closePath();
            }
        }
        return ty;
    }

    public BufferedImage qu_tu() {
        GeneralPath lu_jing2 = new GeneralPath(this.lu_jing);
        lu_jing2.transform(this.Tx);
        Rectangle jx = lu_jing2.getBounds();
        System.out.println(jx);
        System.out.println(this.Tx);
        AffineTransform tx2 = new AffineTransform(this.Tx.getScaleX(), this.Tx.getShearY(), this.Tx.getShearX(), this.Tx.getScaleY(), this.Tx.getTranslateX() - (double)jx.x, this.Tx.getTranslateY() - (double)jx.y);
        System.out.println(tx2);
        BufferedImage fh = new BufferedImage(jx.width, jx.height, 2);
        Graphics2D g2D = fh.createGraphics();
        g2D.setBackground(Color.WHITE);
        g2D.clearRect(0, 0, jx.width, jx.height);
        g2D.drawImage(this.wei_tu_yuan, tx2, null);
        switch (this.chuli_fs) {
            case 1: {
                fh = Tu_pian.hui_du(fh);
                fh = Tu_pian.heibai(fh, (int)((double)this.yu_zhi * 2.56));
                if (this.chuli_fan) {
                    fh = Tu_pian.fanse(fh);
                }
                if (this.chuli_jxx) {
                    fh = Tu_pian.jing_xiang_x(fh);
                }
                if (!this.chuli_jxy) break;
                fh = Tu_pian.jing_xiang_y(fh);
                break;
            }
            case 2: {
                fh = Tu_pian.hui_du(fh);
                fh = Tu_pian.convertGreyImgByFloyd(fh, (int)((double)this.yu_zhi * 2.56));
                if (this.chuli_fan) {
                    fh = Tu_pian.fanse(fh);
                }
                if (this.chuli_jxx) {
                    fh = Tu_pian.jing_xiang_x(fh);
                }
                if (!this.chuli_jxy) break;
                fh = Tu_pian.jing_xiang_y(fh);
                break;
            }
            case 3: {
                fh = Tu_pian.hui_du(fh);
                fh = Tu_pian.heibai(fh, 128);
                fh = Tu_pian.qu_lunkuo(fh, (int)((double)this.yu_zhi * 2.56));
                break;
            }
            case 4: {
                fh = Tu_pian.su_miao(fh);
                fh = Tu_pian.heibai(fh, 50 + (int)((double)this.yu_zhi * 2.56));
                if (this.chuli_fan) {
                    fh = Tu_pian.fanse(fh);
                }
                if (this.chuli_jxx) {
                    fh = Tu_pian.jing_xiang_x(fh);
                }
                if (!this.chuli_jxy) break;
                fh = Tu_pian.jing_xiang_y(fh);
            }
        }
        return fh;
    }

    public void chu_li() {
        switch (this.chuli_fs) {
            case 1: {
                this.wei_tu = Tu_pian.hui_du(this.wei_tu_yuan);
                this.wei_tu = Tu_pian.heibai(this.wei_tu, (int)((double)this.yu_zhi * 2.56));
                if (this.chuli_fan) {
                    this.wei_tu = Tu_pian.fanse(this.wei_tu);
                }
                if (this.chuli_jxx) {
                    this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
                }
                if (!this.chuli_jxy) break;
                this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
                break;
            }
            case 2: {
                this.wei_tu = Tu_pian.hui_du(this.wei_tu_yuan);
                this.wei_tu = Tu_pian.convertGreyImgByFloyd(this.wei_tu, (int)((double)this.yu_zhi * 2.56));
                if (this.chuli_fan) {
                    this.wei_tu = Tu_pian.fanse(this.wei_tu);
                }
                if (this.chuli_jxx) {
                    this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
                }
                if (!this.chuli_jxy) break;
                this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
                break;
            }
            case 3: {
                this.wei_tu = Tu_pian.qu_lunkuo(this.wei_tu_yuan, (int)((double)this.yu_zhi * 2.56));
                break;
            }
            case 4: {
                this.wei_tu = Tu_pian.su_miao2(this.wei_tu_yuan);
                this.wei_tu = Tu_pian.heibai(this.wei_tu, 50 + (int)((double)this.yu_zhi * 2.56));
                if (this.chuli_fan) {
                    this.wei_tu = Tu_pian.fanse(this.wei_tu);
                }
                if (this.chuli_jxx) {
                    this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
                }
                if (!this.chuli_jxy) break;
                this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
            }
        }
    }

    public static void qu_jvxing(List<Tu_yuan> sz) {
        Rectangle jx;
        GeneralPath lu_jing3;
        int i;
        int z = 0;
        int d = 0;
        int y = 0;
        int x = 0;
        for (int i2 = 1; i2 < Hua_ban.ty_shuzu.size(); ++i2) {
            GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i2).lu_jing);
            lu_jing2.transform(Hua_ban.ty_shuzu.get((int)i2).Tx);
            Rectangle jx2 = lu_jing2.getBounds();
            if (i2 == 1) {
                z = jx2.x;
                d = jx2.y;
                y = jx2.x + jx2.width;
                x = jx2.y + jx2.height;
                continue;
            }
            if (jx2.x < z) {
                z = jx2.x;
            }
            if (jx2.y < d) {
                d = jx2.y;
            }
            if (jx2.x + jx2.width > y) {
                y = jx2.x + jx2.width;
            }
            if (jx2.y + jx2.height <= x) continue;
            x = jx2.y + jx2.height;
        }
        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
        Rectangle jx2 = lu_jing2.getBounds();
        jx2.createIntersection(new Rectangle(z, d, y - z, x - d));
        zui_zhong_wjx = new Rectangle(z, d, y - z, x - d).createIntersection(jx2).getBounds();
        zui_zhong_wjx = Tu_yuan.zui_zhong_wjx.width > 0 && Tu_yuan.zui_zhong_wjx.height > 0 ? new Rectangle(z, d, y - z, x - d).createIntersection(jx2).getBounds() : new Rectangle();
        for (i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 0) continue;
            lu_jing3 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing3.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            jx = lu_jing3.getBounds();
            if (i == 1) {
                z = jx.x;
                d = jx.y;
                y = jx.x + jx.width;
                x = jx.y + jx.height;
                continue;
            }
            if (jx.x < z) {
                z = jx.x;
            }
            if (jx.y < d) {
                d = jx.y;
            }
            if (jx.x + jx.width > y) {
                y = jx.x + jx.width;
            }
            if (jx.y + jx.height <= x) continue;
            x = jx.y + jx.height;
        }
        shi_liang_wjx = new Rectangle(z, d, y - z, x - d);
        for (i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
            lu_jing3 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing3.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            jx = lu_jing3.getBounds();
            if (i == 1) {
                z = jx.x;
                d = jx.y;
                y = jx.x + jx.width;
                x = jx.y + jx.height;
                continue;
            }
            if (jx.x < z) {
                z = jx.x;
            }
            if (jx.y < d) {
                d = jx.y;
            }
            if (jx.x + jx.width > y) {
                y = jx.x + jx.width;
            }
            if (jx.y + jx.height <= x) continue;
            x = jx.y + jx.height;
        }
        wei_tu_wjx = new Rectangle(z, d, y - z, x - d);
    }

    public static void qu_jvxing3(List<Tu_yuan> sz) {
        Rectangle jx;
        GeneralPath lu_jing3;
        int i;
        int z = 0;
        int d = 0;
        int y = 0;
        int x = 0;
        for (int i2 = 1; i2 < Hua_ban.ty_shuzu.size(); ++i2) {
            GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i2).lu_jing);
            lu_jing2.transform(Hua_ban.ty_shuzu.get((int)i2).Tx);
            Rectangle jx2 = lu_jing2.getBounds();
            if (i2 == 1) {
                z = jx2.x;
                d = jx2.y;
                y = jx2.x + jx2.width;
                x = jx2.y + jx2.height;
                continue;
            }
            if (jx2.x < z) {
                z = jx2.x;
            }
            if (jx2.y < d) {
                d = jx2.y;
            }
            if (jx2.x + jx2.width > y) {
                y = jx2.x + jx2.width;
            }
            if (jx2.y + jx2.height <= x) continue;
            x = jx2.y + jx2.height;
        }
        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
        Rectangle jx2 = lu_jing2.getBounds();
        if (jx2.x > z) {
            z = jx2.x;
        }
        if (jx2.y > d) {
            d = jx2.y;
        }
        if (jx2.x + jx2.width < y) {
            y = jx2.x + jx2.width;
        }
        if (jx2.y + jx2.height < x) {
            x = jx2.y + jx2.height;
        }
        zui_zhong_wjx = new Rectangle(z, d, y - z, x - d);
        for (i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 0) continue;
            lu_jing3 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing3.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            jx = lu_jing3.getBounds();
            if (i == 1) {
                z = jx.x;
                d = jx.y;
                y = jx.x + jx.width;
                x = jx.y + jx.height;
                continue;
            }
            if (jx.x < z) {
                z = jx.x;
            }
            if (jx.y < d) {
                d = jx.y;
            }
            if (jx.x + jx.width > y) {
                y = jx.x + jx.width;
            }
            if (jx.y + jx.height <= x) continue;
            x = jx.y + jx.height;
        }
        shi_liang_wjx = new Rectangle(z, d, y - z, x - d);
        for (i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
            lu_jing3 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing3.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            jx = lu_jing3.getBounds();
            if (i == 1) {
                z = jx.x;
                d = jx.y;
                y = jx.x + jx.width;
                x = jx.y + jx.height;
                continue;
            }
            if (jx.x < z) {
                z = jx.x;
            }
            if (jx.y < d) {
                d = jx.y;
            }
            if (jx.x + jx.width > y) {
                y = jx.x + jx.width;
            }
            if (jx.y + jx.height <= x) continue;
            x = jx.y + jx.height;
        }
        wei_tu_wjx = new Rectangle(z, d, y - z, x - d);
    }

    public static void hui_fu() {
        GeneralPath lu_jing3 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
        lu_jing3.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
        Rectangle rect3 = lu_jing3.getBounds();
        Hua_ban.quan_x2 = rect3.x;
        Hua_ban.quan_y2 = rect3.y;
        Hua_ban.quan_beishu2 = Hua_ban.quan_beishu;
        for (int j = 0; j < 2; ++j) {
            AffineTransform fb;
            int i;
            GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
            lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
            Rectangle rect = lu_jing2.getBounds();
            AffineTransform sf = AffineTransform.getTranslateInstance(0 - rect.x, 0 - rect.y);
            Hua_ban.quan_x = 0;
            Hua_ban.quan_y = 0;
            for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                fb = new AffineTransform(sf);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
            sf = AffineTransform.getScaleInstance(1.0 / Hua_ban.quan_beishu, 1.0 / Hua_ban.quan_beishu);
            Hua_ban.quan_beishu = 1.0;
            for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                fb = new AffineTransform(sf);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
        }
    }

    public static void hui_fu_xian_chang() {
        AffineTransform fb;
        int i;
        Hua_ban.quan_x = Hua_ban.quan_x2;
        Hua_ban.quan_y = Hua_ban.quan_y2;
        Hua_ban.quan_beishu = Hua_ban.quan_beishu2;
        AffineTransform sf = AffineTransform.getScaleInstance(Hua_ban.quan_beishu, Hua_ban.quan_beishu);
        for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            fb = new AffineTransform(sf);
            fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
            Hua_ban.ty_shuzu.get((int)i).Tx = fb;
        }
        sf = AffineTransform.getTranslateInstance(Hua_ban.quan_x, Hua_ban.quan_y);
        for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            fb = new AffineTransform(sf);
            fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
            Hua_ban.ty_shuzu.get((int)i).Tx = fb;
        }
    }

    public static void shu_chu(BufferedImage bb, String ss) {
        File outputfile = new File(ss);
        try {
            ImageIO.write((RenderedImage)bb, "png", outputfile);
        }
        catch (IOException ex) {
            Logger.getLogger(Tu_yuan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static BufferedImage qu_tu(List<Tu_yuan> sz) {
        BufferedImage fh = new BufferedImage((int)((double)Hua_ban.kuan / Hua_ban.fen_bian_lv) - 2, (int)((double)Hua_ban.gao / Hua_ban.fen_bian_lv) - 2, 2);
        Graphics2D g2D = fh.createGraphics();
        g2D.setBackground(Color.WHITE);
        g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
        boolean you = false;
        for (int i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 1 || Hua_ban.ty_shuzu.get((int)i).chuli_fs == 3) continue;
            GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing2.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            Rectangle jx = lu_jing2.getBounds();
            g2D.drawImage((Image)Hua_ban.ty_shuzu.get(i).qu_tu(), jx.x, jx.y, null);
            you = true;
        }
        if (you) {
            BufferedImage fh2 = new BufferedImage((int)((double)Hua_ban.kuan / Hua_ban.fen_bian_lv), (int)((double)Hua_ban.gao / Hua_ban.fen_bian_lv), 2);
            Graphics2D g2D2 = fh2.createGraphics();
            g2D2.setBackground(Color.WHITE);
            int w = fh2.getWidth();
            int h = fh2.getHeight();
            g2D2.clearRect(0, 0, w, h);
            g2D2.drawImage((Image)fh, 1, 1, null);
            Tu_yuan.qu_jvxing(Hua_ban.ty_shuzu);
            boolean xx = false;
            boolean yy = false;
            boolean ww = false;
            boolean hh = false;
            Rectangle jx = new Rectangle(0, 0, w, h);
            Rectangle jx2 = new Rectangle();
            if (Tu_yuan.zui_zhong_wjx.x == 0 && Tu_yuan.zui_zhong_wjx.y == 0 && Tu_yuan.zui_zhong_wjx.width == 0 && Tu_yuan.zui_zhong_wjx.height == 0) {
                return null;
            }
            if (!jx.contains(Tu_yuan.zui_zhong_wjx.x, Tu_yuan.zui_zhong_wjx.y) && !jx.contains(Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width, Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height)) {
                return null;
            }
            jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
            w = jx2.x + jx2.width + 5 >= fh2.getWidth() ? jx2.width : jx2.width + 5;
            h = jx2.y + jx2.height + 5 >= fh2.getHeight() ? jx2.height : jx2.height + 5;
            return fh2.getSubimage(jx2.x, jx2.y, w, h);
        }
        return null;
    }

    public static BufferedImage qu_tu_sl(List<Tu_yuan> sz) {
        BufferedImage fh = new BufferedImage((int)((double)Hua_ban.kuan / Hua_ban.fen_bian_lv), (int)((double)Hua_ban.gao / Hua_ban.fen_bian_lv), 2);
        Graphics2D g2D = fh.createGraphics();
        g2D.setBackground(Color.WHITE);
        g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
        boolean you = false;
        for (int i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
            GeneralPath lu_jing4 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing4.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing == 0) {
                g2D.setColor(Color.BLACK);
                g2D.draw(lu_jing4);
                you = true;
            }
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 1 || Hua_ban.ty_shuzu.get((int)i).chuli_fs != 3) continue;
            GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing2.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            Rectangle jx = lu_jing2.getBounds();
            g2D.setColor(Color.BLACK);
            g2D.drawImage((Image)Hua_ban.ty_shuzu.get(i).qu_tu(), jx.x, jx.y, null);
            you = true;
        }
        if (you) {
            try {
                BufferedImage fh2 = new BufferedImage((int)((double)Hua_ban.kuan / Hua_ban.fen_bian_lv) + 4, (int)((double)Hua_ban.gao / Hua_ban.fen_bian_lv) + 4, 2);
                Graphics2D g2D2 = fh2.createGraphics();
                g2D2.setBackground(Color.WHITE);
                int w = fh2.getWidth();
                int h = fh2.getHeight();
                g2D2.clearRect(0, 0, w, h);
                g2D2.drawImage((Image)fh, 2, 2, null);
                Tu_yuan.qu_jvxing(Hua_ban.ty_shuzu);
                boolean xx = false;
                boolean yy = false;
                boolean ww = false;
                boolean hh = false;
                Rectangle jx = new Rectangle(0, 0, w, h);
                Rectangle jx2 = new Rectangle();
                if (Tu_yuan.zui_zhong_wjx.x == 0 && Tu_yuan.zui_zhong_wjx.y == 0 && Tu_yuan.zui_zhong_wjx.width == 0 && Tu_yuan.zui_zhong_wjx.height == 0) {
                    return null;
                }
                if (!jx.contains(Tu_yuan.zui_zhong_wjx.x, Tu_yuan.zui_zhong_wjx.y) && !jx.contains(Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width, Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height)) {
                    return null;
                }
                jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
                w = jx2.x + jx2.width + 5 >= fh2.getWidth() ? jx2.width : jx2.width + 5;
                h = jx2.y + jx2.height + 5 >= fh2.getHeight() ? jx2.height : jx2.height + 5;
                try {
                    return fh2.getSubimage(jx2.x, jx2.y, w, h);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    return null;
                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
                return null;
            }
        }
        return null;
    }

    public static BufferedImage qu_tu_sl_tc(List<Tu_yuan> sz) {
        BufferedImage fh = new BufferedImage((int)((double)Hua_ban.kuan / Hua_ban.fen_bian_lv), (int)((double)Hua_ban.gao / Hua_ban.fen_bian_lv), 2);
        Graphics2D g2D = fh.createGraphics();
        g2D.setBackground(Color.WHITE);
        g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
        boolean you = false;
        for (int i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
            GeneralPath lu_jing4 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing4.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 0 || !Hua_ban.ty_shuzu.get((int)i).tian_chong) continue;
            g2D.setColor(Color.BLACK);
            lu_jing4.setWindingRule(0);
            g2D.fill(lu_jing4);
            you = true;
        }
        if (you) {
            BufferedImage fh2 = new BufferedImage((int)((double)Hua_ban.kuan / Hua_ban.fen_bian_lv) + 4, (int)((double)Hua_ban.gao / Hua_ban.fen_bian_lv) + 4, 2);
            Graphics2D g2D2 = fh2.createGraphics();
            g2D2.setBackground(Color.WHITE);
            int w = fh2.getWidth();
            int h = fh2.getHeight();
            g2D2.clearRect(0, 0, w, h);
            g2D2.drawImage((Image)fh, 2, 2, null);
            Tu_yuan.qu_jvxing(Hua_ban.ty_shuzu);
            boolean xx = false;
            boolean yy = false;
            boolean ww = false;
            boolean hh = false;
            Rectangle jx = new Rectangle(0, 0, w, h);
            Rectangle jx2 = new Rectangle();
            if (Tu_yuan.zui_zhong_wjx.x == 0 && Tu_yuan.zui_zhong_wjx.y == 0 && Tu_yuan.zui_zhong_wjx.width == 0 && Tu_yuan.zui_zhong_wjx.height == 0) {
                return null;
            }
            if (!jx.contains(Tu_yuan.zui_zhong_wjx.x, Tu_yuan.zui_zhong_wjx.y) && !jx.contains(Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width, Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height)) {
                return null;
            }
            jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
            w = jx2.x + jx2.width + 5 >= fh2.getWidth() ? jx2.width : jx2.width + 5;
            h = jx2.y + jx2.height + 5 >= fh2.getHeight() ? jx2.height : jx2.height + 5;
            return fh2.getSubimage(jx2.x, jx2.y, w, h);
        }
        return null;
    }

    static Dian qu_xiao(Dian d1, Dian d2, Dian d3) {
        int lxiao2;
        if (d1.x == 242 && d1.y == 85) {
            d1.x = 242;
        }
        int lxiao = Math.abs(d1.x - d2.x) > Math.abs(d1.y - d2.y) ? Math.abs(d1.y - d2.y) : Math.abs(d1.x - d2.x);
        int n = lxiao2 = Math.abs(d1.x - d3.x) > Math.abs(d1.y - d3.y) ? Math.abs(d1.y - d3.y) : Math.abs(d1.x - d3.x);
        if (lxiao < lxiao2) {
            return d2;
        }
        return d3;
    }

    static Dian qu_jindian(Dian d, BufferedImage bb) {
        Dian fh = new Dian(50000, 0);
        ArrayList<Dian> zd = new ArrayList<Dian>();
        for (int c = 1; c < da; ++c) {
            int b;
            int ls = d.y - c;
            for (b = d.x - c; b < d.x + c; ++b) {
                if (b <= 0 || b >= tu_kuan || ls <= 0 || ls >= tu_gao || new Color(bb.getRGB(b, ls)).getRed() != 0) continue;
                zd.add(new Dian(b, ls));
            }
            ls = d.x + c;
            for (b = d.y - c; b < d.y + c; ++b) {
                if (b <= 0 || b >= tu_gao || ls <= 0 || ls >= tu_kuan || new Color(bb.getRGB(ls, b)).getRed() != 0) continue;
                zd.add(new Dian(ls, b));
            }
            ls = d.y + c;
            for (b = d.x + c; b > d.x - c; --b) {
                if (b <= 0 || b >= tu_kuan || ls <= 0 || ls >= tu_gao || new Color(bb.getRGB(b, ls)).getRed() != 0) continue;
                zd.add(new Dian(b, ls));
            }
            ls = d.x - c;
            for (b = d.y + c; b > d.y - c; --b) {
                if (b <= 0 || b >= tu_gao || ls <= 0 || ls >= tu_kuan || new Color(bb.getRGB(ls, b)).getRed() != 0) continue;
                zd.add(new Dian(ls, b));
            }
            Dian fh2 = new Dian(0, 0);
            if (zd.size() <= 0) continue;
            for (int i = 0; i < zd.size(); ++i) {
                fh2 = i == 0 ? (Dian)zd.get(i) : Tu_yuan.qu_xiao(d, fh2, (Dian)zd.get(i));
            }
            return fh2;
        }
        return fh;
    }

    static List<Dian> pai_xu(BufferedImage tu_diaoke2) {
        BufferedImage bb = tu_diaoke2.getSubimage(0, 0, tu_diaoke2.getWidth(), tu_diaoke2.getHeight());
        ArrayList<Dian> fh = new ArrayList<Dian>();
        tu_kuan = bb.getWidth();
        tu_gao = bb.getHeight();
        da = bb.getWidth() > bb.getHeight() ? bb.getWidth() : bb.getHeight();
        for (int i = 0; i < dian.size(); ++i) {
            if (i == 0) {
                fh.add(dian.get(i));
                fh.add(new Dian(30000, 30000));
                bb.setRGB(Tu_yuan.dian.get((int)i).x, Tu_yuan.dian.get((int)i).y, Color.WHITE.getRGB());
                continue;
            }
            Dian d2 = new Dian(0, 0);
            d2 = ((Dian)fh.get((int)(fh.size() - 1))).x == 30000 || ((Dian)fh.get((int)(fh.size() - 1))).x == 50000 ? (Dian)fh.get(fh.size() - 2) : (Dian)fh.get(fh.size() - 1);
            Dian d = Tu_yuan.qu_jindian(d2, bb);
            if (d.x == 50000) break;
            if (!Tu_yuan.xiang_lian(d, d2)) {
                fh.add(new Dian(50000, 50000));
                fh.add(d);
                fh.add(new Dian(30000, 30000));
            } else {
                fh.add(d);
            }
            bb.setRGB(d.x, d.y, Color.WHITE.getRGB());
        }
        fh.add(new Dian(50000, 50000));
        return fh;
    }

    static List<Dian> pai_xu2(BufferedImage tu_diaoke2) {
        BufferedImage bb = tu_diaoke2.getSubimage(0, 0, tu_diaoke2.getWidth(), tu_diaoke2.getHeight());
        ArrayList<Dian> fh = new ArrayList<Dian>();
        tu_kuan = bb.getWidth();
        tu_gao = bb.getHeight();
        da = bb.getWidth() > bb.getHeight() ? bb.getWidth() : bb.getHeight();
        for (int i = 0; i < dian.size(); ++i) {
            if (i == 0) {
                fh.add(dian.get(i));
                bb.setRGB(Tu_yuan.dian.get((int)i).x, Tu_yuan.dian.get((int)i).y, Color.WHITE.getRGB());
                continue;
            }
            if (((Dian)fh.get((int)(fh.size() - 1))).x == 242 && ((Dian)fh.get((int)(fh.size() - 1))).y == 87) {
                ((Dian)fh.get((int)(fh.size() - 1))).x = 242;
            }
            Dian d = Tu_yuan.qu_jindian((Dian)fh.get(fh.size() - 1), bb);
            if (d.x == 50000) break;
            fh.add(d);
            bb.setRGB(d.x, d.y, Color.WHITE.getRGB());
        }
        return fh;
    }

    static List<Dian> qu_tian_chong(BufferedImage img, int jian_ge) {
        ArrayList<Dian> fh = new ArrayList<Dian>();
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = new int[width * height];
        img.getRGB(0, 0, width, height, pixels, 0, width);
        for (int i = 1; i < height; i += jian_ge) {
            for (int j = 1; j < width; ++j) {
                if (new Color(pixels[width * i + j]).getRed() != 0) continue;
                fh.add(new Dian(j, i));
            }
        }
        return fh;
    }

    static List<Dian> qudian(BufferedImage img) {
        ArrayList<Dian> fh = new ArrayList<Dian>();
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = new int[width * height];
        img.getRGB(0, 0, width, height, pixels, 0, width);
        for (int i = 1; i < height; ++i) {
            for (int j = 1; j < width; ++j) {
                if (new Color(pixels[width * i + j]).getRed() != 0) continue;
                fh.add(new Dian(j, i));
            }
        }
        return fh;
    }

    static int qu_fang_xiang(Dian d1, Dian d2) {
        if (d2.x - d1.x == 1 && d2.y - d1.y == 1) {
            return 4;
        }
        if (d2.x - d1.x == -1 && d2.y - d1.y == -1) {
            return 8;
        }
        if (d2.x - d1.x == 1 && d2.y - d1.y == -1) {
            return 2;
        }
        if (d2.x - d1.x == -1 && d2.y - d1.y == 1) {
            return 6;
        }
        if (d2.x - d1.x == -1 && d2.y - d1.y == 0) {
            return 7;
        }
        if (d2.x - d1.x == 1 && d2.y - d1.y == 0) {
            return 3;
        }
        if (d2.x - d1.x == 0 && d2.y - d1.y == -1) {
            return 1;
        }
        if (d2.x - d1.x == 0 && d2.y - d1.y == 1) {
            return 5;
        }
        if (Math.abs(d2.x - d1.x) > 1 && Math.abs(d2.y - d1.y) > 1) {
            return 9;
        }
        return 9;
    }

    public static List<Dian> you_hua(List<Dian> dd) {
        ArrayList<Dian> fh = new ArrayList<Dian>();
        int fx = 0;
        for (int i = 0; i < dd.size(); ++i) {
            if (i == 0) {
                fh.add(dd.get(i));
                continue;
            }
            if (i == 1) {
                fh.add(dd.get(i));
                fx = Tu_yuan.qu_fang_xiang((Dian)fh.get(fh.size() - 2), (Dian)fh.get(fh.size() - 2));
                continue;
            }
            int fx2 = Tu_yuan.qu_fang_xiang((Dian)fh.get(fh.size() - 1), dd.get(i));
            if (fx == fx2 && fx != 9) {
                fh.remove(fh.size() - 1);
                fh.add(dd.get(i));
                continue;
            }
            fh.add(dd.get(i));
            fx = fx2;
        }
        return fh;
    }

    static boolean xiang_lian(Dian a, Dian b) {
        return Math.abs(a.x - b.x) <= 2 && Math.abs(a.y - b.y) <= 2;
    }

    public static double getDistance(int x1, int x2, int y1, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static double qu_gao(double a, double b, double c) {
        double p = (a + b + c) / 2.0;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return 2.0 * s / b;
    }

    public static double qu_gao_da(List<Dian> dd, int d1, int d2) {
        double da = 0.0;
        for (int i = 0; i < d2 - d1 - 1; ++i) {
            double c;
            double b;
            double a;
            double d;
            if (dd.get((int)(i + d1)).x == 30000 || dd.get((int)(i + d1)).x == 50000 || !((d = Tu_yuan.qu_gao(a = Tu_yuan.getDistance(dd.get((int)d1).x, dd.get((int)(i + d1)).x, dd.get((int)d1).y, dd.get((int)(i + d1)).y), b = Tu_yuan.getDistance(dd.get((int)d1).x, dd.get((int)d2).x, dd.get((int)d1).y, dd.get((int)d2).y), c = Tu_yuan.getDistance(dd.get((int)(i + d1)).x, dd.get((int)d2).x, dd.get((int)(i + d1)).y, dd.get((int)d2).y))) > da)) continue;
            da = d;
        }
        return da;
    }

    public static List<Dian> you_hua2(List<Dian> dd) {
        ArrayList<Dian> fh = new ArrayList<Dian>();
        int yi = 0;
        for (int i = 0; i < dd.size(); ++i) {
            double da;
            int ge_shu;
            if (dd.get((int)i).x == 30000) {
                fh.add(dd.get(i - 1));
                fh.add(dd.get(i));
                yi = i - 1;
                continue;
            }
            if (dd.get((int)i).x == 50000) {
                fh.add(dd.get(i - 1));
                fh.add(dd.get(i));
                yi = i + 1;
                continue;
            }
            if (i == 0 || (ge_shu = i - yi) <= 1 || !((da = Tu_yuan.qu_gao_da(dd, yi, i)) > 0.7)) continue;
            fh.add(dd.get(i - 1));
            yi = i - 1;
        }
        return fh;
    }

    public static GeneralPath to_ls(BufferedImage bb) {
        List<Dian> fh = null;
        GeneralPath lj = new GeneralPath();
        if ((bb = Tu_pian.qu_lunkuo(bb, 128)) != null) {
            dian = Tu_yuan.qudian(bb);
            fh = Tu_yuan.pai_xu(bb);
            fh = Tu_yuan.you_hua2(fh);
        }
        Dian qian = new Dian(0, 0);
        Dian qi = new Dian(0, 0);
        boolean kai_jg = false;
        for (int i = 0; i < fh.size(); ++i) {
            Dian ls = fh.get(i);
            if (i == 0) {
                lj.moveTo(ls.x, ls.y);
                qian = new Dian(ls.x, ls.y);
                continue;
            }
            if (fh.get((int)i).x == 30000) {
                kai_jg = true;
                qi = new Dian(fh.get((int)(i - 1)).x, fh.get((int)(i - 1)).y);
                continue;
            }
            if (fh.get((int)i).x == 50000) {
                kai_jg = false;
                if (!Tu_yuan.xiang_lian(qi, fh.get(i - 1))) continue;
                lj.closePath();
                continue;
            }
            if (kai_jg) {
                lj.lineTo(ls.x, ls.y);
                qian = new Dian(ls.x, ls.y);
                continue;
            }
            lj.moveTo(ls.x, ls.y);
            qian = new Dian(ls.x, ls.y);
        }
        if (Tu_yuan.xiang_lian(qi, fh.get(fh.size() - 1))) {
            lj.closePath();
        }
        return lj;
    }

    public static List<Dian> qu_dian(List<Tu_yuan> sz) {
        BufferedImage bb2;
        List<Dian> fh = null;
        BufferedImage bb = Tu_yuan.qu_tu_sl(Hua_ban.ty_shuzu);
        if (bb != null) {
            dian = Tu_yuan.qudian(bb);
            fh = Tu_yuan.pai_xu2(bb);
        }
        if ((bb2 = Tu_yuan.qu_tu_sl_tc(Hua_ban.ty_shuzu)) != null) {
            fh.addAll(Tu_yuan.qu_tian_chong(bb2, 1 + tian_chong_md));
        }
        return fh;
    }
}

