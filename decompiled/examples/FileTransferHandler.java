/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Hua_ban;
import examples.Tu_yuan;
import examples.jie_xi_PLT;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

class FileTransferHandler
extends TransferHandler {
    public static Hua_ban hb = null;

    FileTransferHandler() {
    }

    @Override
    public boolean importData(JComponent comp, Transferable t) {
        try {
            List list = (List)t.getTransferData(DataFlavor.javaFileListFlavor);
            for (File f : list) {
                System.out.println(f.getAbsolutePath());
                String fileName = f.getAbsolutePath();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                suffix = suffix.toUpperCase();
                if (suffix.equals("BMP") || suffix.equals("JPG") || suffix.equals("PNG") || suffix.equals("JPEG") || suffix.equals("GIF")) {
                    try {
                        BufferedImage bmp = ImageIO.read(new File(fileName));
                        Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, bmp));
                        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = false;
                        }
                        Hua_ban.ty_shuzu.get((int)(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
                        Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
                        if (hb == null) continue;
                        hb.repaint();
                    }
                    catch (IOException bmp) {}
                    continue;
                }
                if (suffix.equals("XJ")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));){
                        Hua_ban.ty_shuzu = (List)ois.readObject();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                        if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                        Hua_ban.ty_shuzu.get((int)i).wei_tu = new BufferedImage(Hua_ban.ty_shuzu.get((int)i).wt_w, Hua_ban.ty_shuzu.get((int)i).wt_g, 2);
                        Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan = new BufferedImage(Hua_ban.ty_shuzu.get((int)i).wty_w, Hua_ban.ty_shuzu.get((int)i).wty_g, 2);
                        Hua_ban.ty_shuzu.get((int)i).wei_tu.setRGB(0, 0, Hua_ban.ty_shuzu.get((int)i).wt_w, Hua_ban.ty_shuzu.get((int)i).wt_g, Hua_ban.ty_shuzu.get((int)i).wei_tu_, 0, Hua_ban.ty_shuzu.get((int)i).wt_w);
                        Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.setRGB(0, 0, Hua_ban.ty_shuzu.get((int)i).wty_w, Hua_ban.ty_shuzu.get((int)i).wty_g, Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan_, 0, Hua_ban.ty_shuzu.get((int)i).wty_w);
                    }
                    if (hb == null) continue;
                    hb.repaint();
                    continue;
                }
                if (!suffix.equals("PLT")) continue;
                jie_xi_PLT plt = new jie_xi_PLT();
                plt.jie_xi_PLT(f);
                plt = null;
                hb.updateUI();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        return true;
    }
}

