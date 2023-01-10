/*
 * Decompiled with CFR 0.150.
 */
import examples.Tu_yuan;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NewClass2
extends JFrame {
    TextureFillPanel panel = null;

    public NewClass2() {
        this.setTitle("\u7eb9\u7406\u586b\u5145\u7279\u6548");
        this.setBounds(100, 100, 400, 300);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.panel = new TextureFillPanel();
        this.add(this.panel);
    }

    public static void main(String[] args) {
        NewClass2 frame = new NewClass2();
        frame.setVisible(true);
    }

    class TextureFillPanel
    extends JPanel {
        TextureFillPanel() {
        }

        @Override
        public void paint(Graphics g) {
            Image image2 = new ImageIcon(this.getClass().getResource("/tu/3.png")).getImage();
            int kk = image2.getWidth(null);
            int gg = image2.getHeight(null);
            BufferedImage image = new BufferedImage(kk, gg, 1);
            Graphics2D g2 = (Graphics2D)image.getGraphics();
            g2.drawImage(image2, 0, 0, kk, gg, null);
            Rectangle2D.Float rect = new Rectangle2D.Float(0.0f, 0.0f, kk, gg);
            TexturePaint textPaint = new TexturePaint(image, rect);
            Graphics2D graphics2 = (Graphics2D)g;
            graphics2.setPaint(textPaint);
            Rectangle2D.Float ellipse2 = new Rectangle2D.Float(45.0f, 25.0f, 200.0f, 200.0f);
            Tu_yuan ty = new Tu_yuan();
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
            graphics2.fill(ty.lu_jing);
        }
    }
}

