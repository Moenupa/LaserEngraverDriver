/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Tu_yuan;
import examples.mainJFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Hua_ban
extends JPanel {
    public static int kuan = 80;
    public static int gao = 80;
    public static double fen_bian_lv = 0.075;
    public static int quan_x = 0;
    public static int quan_y = 0;
    public static double quan_beishu = 1.0;
    public static int quan_x2 = 0;
    public static int quan_y2 = 0;
    public static double quan_beishu2 = 1.0;
    public static boolean kuang = false;
    public static List<Tu_yuan> ty_shuzu = new ArrayList<Tu_yuan>();
    public static int dang_qian2 = -1;
    public AffineTransform Tx = new AffineTransform();
    public static boolean suo = true;
    public JPanel win;
    public JPanel jp;
    public JPanel jp2;
    public JLabel wb;
    public JTextField wb1;
    public JTextField wb2;
    public JTextField wb3;
    public JTextField wb4;
    public mainJFrame win2 = null;
    public int x = 0;
    public int y = 0;
    public int ww = 0;
    public int hh = 0;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D)g;
        boolean xuan_zhong = false;
        for (int i = 0; i < ty_shuzu.size(); ++i) {
            GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
            lu_jing2.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
            Rectangle rect = lu_jing2.getBounds();
            double mm = (double)kuan / 10.0 / (double)rect.width;
            int w = (int)(mm / 0.02);
            if (w < 1) {
                w = 1;
            }
            if (i == 0) {
                int j;
                g2D.setColor(Color.LIGHT_GRAY);
                g2D.draw(lu_jing2);
                for (j = 0; j < kuan / 10 + 1; ++j) {
                    if ((double)j % (double)w != 0.0) continue;
                    g2D.drawString(String.valueOf(j), (int)((double)rect.x + (double)j * ((double)rect.width / ((double)kuan / 10.0))), rect.y);
                }
                for (j = 0; j < gao / 10 + 1; ++j) {
                    if ((double)j % (double)w != 0.0) continue;
                    g2D.drawString(String.valueOf(j), rect.x - 16, (int)((double)rect.y + (double)j * ((double)rect.height / ((double)gao / 10.0)) + 10.0));
                }
                g2D.setColor(Color.BLACK);
            } else {
                g2D.setPaint(Color.BLACK);
                lu_jing2.setWindingRule(0);
                if (Hua_ban.ty_shuzu.get((int)i).tian_chong) {
                    g2D.fill(lu_jing2);
                }
                g2D.setColor(Color.BLUE);
                g2D.draw(lu_jing2);
                g2D.setColor(Color.BLUE);
                if (Tu_yuan.tuo) {
                    g2D.draw(Tu_yuan.shu_biao);
                }
                g2D.setColor(Color.RED);
                g2D.setColor(Color.BLACK);
            }
            if (Hua_ban.ty_shuzu.get((int)i).lei_xing == 1) {
                g2D.drawImage(Hua_ban.ty_shuzu.get((int)i).wei_tu, Hua_ban.ty_shuzu.get((int)i).Tx, null);
            }
            if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
            xuan_zhong = true;
        }
        if (xuan_zhong && !Tu_yuan.tuo) {
            Rectangle rect = Tu_yuan.qu_jv_xing(ty_shuzu);
            g2D.setColor(Color.GREEN);
            g2D.draw(rect);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/ic_icon_delete.png")).getImage(), rect.x - 15, rect.y - 15, 30, 30, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/ic_icon_rotate.png")).getImage(), rect.x + rect.width - 15, rect.y - 15, 30, 30, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/ic_icon_scale.png")).getImage(), rect.x + rect.width - 15, rect.y + rect.height - 15, 30, 30, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/zhong_xin.png")).getImage(), rect.x + rect.width - 15, rect.y + rect.height + 20, 30, 30, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/hei_bai.png")).getImage(), rect.x + rect.width + 25, rect.y - 20, 60, 65, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/hui_du.png")).getImage(), rect.x + rect.width + 25, rect.y + 45, 60, 65, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/lun_kuo.png")).getImage(), rect.x + rect.width + 25, rect.y + 110, 60, 65, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/su_miao.png")).getImage(), rect.x + rect.width + 25, rect.y + 175, 60, 65, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/jing_xiang_y.png")).getImage(), rect.x + rect.width - 50, rect.y + rect.height + 20, 30, 30, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/jing_xiang_x.png")).getImage(), rect.x + rect.width - 85, rect.y + rect.height + 20, 30, 30, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/fan_se.png")).getImage(), rect.x + rect.width - 120, rect.y + rect.height + 20, 30, 30, null);
            g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/tian_chong.png")).getImage(), rect.x + rect.width - 155, rect.y + rect.height + 20, 30, 30, null);
            if (suo) {
                g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/suo1.png")).getImage(), rect.x - 15, rect.y + rect.height - 15, 30, 30, null);
            } else {
                g2D.drawImage(new ImageIcon(this.getClass().getResource("/tu/suo2.png")).getImage(), rect.x - 15, rect.y + rect.height - 15, 30, 30, null);
            }
            g2D.setColor(Color.BLUE);
            g2D.setFont(new Font(g2D.getFont().getName(), g2D.getFont().getStyle(), 16));
            Point2D d = this.zhuan_huan(rect.getLocation());
            this.x = (int)Math.round(d.getX() * fen_bian_lv);
            this.y = (int)Math.round(d.getY() * fen_bian_lv);
            d = this.zhuan_huan(new Point(rect.x + rect.width, rect.y + rect.height));
            this.ww = (int)Math.round(d.getX() * fen_bian_lv) - this.x;
            this.hh = (int)Math.round(d.getY() * fen_bian_lv) - this.y;
            this.jp.setLocation(rect.x - 18, rect.y - 60);
            this.jp.setSize(245, 35);
            this.wb1.setText(String.valueOf(this.x));
            this.wb2.setText(String.valueOf(this.y));
            this.wb3.setText(String.valueOf(this.ww));
            this.wb4.setText(String.valueOf(this.hh));
            g2D.setColor(Color.BLACK);
        }
        if (this.jp != null && !xuan_zhong) {
            this.jp.setLocation(2, -this.jp.getHeight());
        }
    }

    Point2D zhuan_huan(Point2D m) {
        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
        Rectangle rect = lu_jing2.getBounds();
        AffineTransform sf = AffineTransform.getTranslateInstance(0 - rect.x, 0 - rect.y);
        sf.transform(m, m);
        sf = AffineTransform.getScaleInstance(1.0 / quan_beishu, 1.0 / quan_beishu);
        sf.transform(m, m);
        return m;
    }

    public void di_tu() {
        int j;
        Tu_yuan.hui_fu();
        Tu_yuan ty = new Tu_yuan();
        ty.lei_xing = 0;
        quan_beishu = 1.0;
        quan_x = 0;
        quan_y = 0;
        for (j = 0; j < gao + 1; ++j) {
            if (j != 0 && j % 5 != 0 && j != gao) continue;
            ty.lu_jing.moveTo(0.0, (double)j / fen_bian_lv);
            ty.lu_jing.lineTo((double)kuan / fen_bian_lv, (double)j / fen_bian_lv);
        }
        for (j = 0; j < kuan + 1; ++j) {
            if (j != 0 && j % 5 != 0 && j != kuan) continue;
            ty.lu_jing.moveTo((double)j / fen_bian_lv, 0.0);
            ty.lu_jing.lineTo((double)j / fen_bian_lv, (double)gao / fen_bian_lv);
        }
        ty_shuzu.set(0, ty);
        this.repaint();
        Tu_yuan.hui_fu_xian_chang();
    }

    public void ban_ben(byte[] fh, int jing_du) {
        switch (fh[2]) {
            case 4: 
            case 6: {
                kuan = 80;
                gao = 75;
                fen_bian_lv = 0.05;
                this.di_tu();
                break;
            }
            case 21: 
            case 22: {
                kuan = 145;
                gao = 175;
                if (jing_du == 0) {
                    fen_bian_lv = 0.05;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.065;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.075;
                }
                this.di_tu();
                break;
            }
            case 23: {
                kuan = 145;
                gao = 145;
                if (jing_du == 0) {
                    fen_bian_lv = 0.05;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.065;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.075;
                }
                this.di_tu();
                break;
            }
            case 31: {
                kuan = 115;
                gao = 225;
                if (jing_du == 0) {
                    fen_bian_lv = 0.05;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.065;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.075;
                }
                this.di_tu();
                break;
            }
            case 32: {
                kuan = 185;
                gao = 295;
                if (jing_du == 0) {
                    fen_bian_lv = 0.05;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.065;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.075;
                }
                this.di_tu();
                break;
            }
            case 33: {
                kuan = 185;
                gao = 245;
                if (jing_du == 0) {
                    fen_bian_lv = 0.05;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.065;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.075;
                }
                this.di_tu();
                break;
            }
            case 34: {
                kuan = 140;
                gao = 130;
                if (jing_du == 0) {
                    fen_bian_lv = 0.05;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.065;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.075;
                }
                this.di_tu();
                break;
            }
            case 35: {
                kuan = 370;
                gao = 410;
                if (jing_du == 0) {
                    fen_bian_lv = 0.05;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.065;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.075;
                }
                this.di_tu();
                break;
            }
            case 36: {
                kuan = 370;
                gao = 370;
                if (jing_du == 0) {
                    fen_bian_lv = 0.05;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.065;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.075;
                }
                this.di_tu();
                break;
            }
            case 37: {
                kuan = 190;
                gao = 215;
                if (jing_du == 0) {
                    fen_bian_lv = 0.064;
                } else if (jing_du == 1) {
                    fen_bian_lv = 0.08;
                } else if (jing_du == 2) {
                    fen_bian_lv = 0.096;
                }
                this.di_tu();
            }
        }
    }
}

