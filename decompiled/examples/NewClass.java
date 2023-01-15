/*
 * Decompiled with CFR 0.150.
 */
package examples;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class NewClass {
    public static void main(String[] args) throws Exception {
        int width = 150;
        int height = 30;
        String out = new String("\u4eca\u5915\u662f\u4f55\u5e74\uff1f");
        double rate = 1.9;
        BufferedImage image = new BufferedImage(width, height, 4);
        Graphics g = image.getGraphics();
        g.setColor(new Color(200, 192, 184));
        g.fill3DRect(0, 0, width, height, true);
        g.setColor(Color.BLACK);
        g.setFont(new Font("\u5b8b\u4f53", 1, 20));
        int x = (int)((double)(width / 2) - rate * (double)g.getFontMetrics().stringWidth(out) / 2.0);
        int y = height / 2 + g.getFontMetrics().getHeight() / 3;
        NewClass.MyDrawString(out, x, y, rate, g);
        ImageIO.write((RenderedImage)image, "png", new File("d:/2.png"));
    }

    public static void MyDrawString(String str, int x, int y, double rate, Graphics g) {
        String tempStr = new String();
        int orgStringWight = g.getFontMetrics().stringWidth(str);
        int orgStringLength = str.length();
        int tempx = x;
        int tempy = y;
        while (str.length() > 0) {
            tempStr = str.substring(0, 1);
            str = str.substring(1, str.length());
            g.drawString(tempStr, tempx, tempy);
            tempx = (int)((double)tempx + (double)orgStringWight / (double)orgStringLength * rate);
        }
    }
}

