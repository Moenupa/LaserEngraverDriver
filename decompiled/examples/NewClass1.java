/*
 * Decompiled with CFR 0.150.
 */
package examples;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class NewClass1 {
    Frame fr = new Frame("MenuDemo");
    MenuBar mb = new MenuBar();
    Menu m1 = new Menu("\u6587\u4ef6");
    MenuItem open = new MenuItem("\u6253\u5f00");
    MenuItem close = new MenuItem("\u5173\u95ed");
    MenuItem exit = new MenuItem("\u9000\u51fa");

    NewClass1() {
        this.fr.setSize(350, 200);
        this.m1.add(this.open);
        this.m1.add(this.close);
        this.m1.addSeparator();
        this.m1.add(this.exit);
        this.mb.add(this.m1);
        this.fr.setMenuBar(this.mb);
        this.fr.setVisible(true);
    }

    public static void main(String[] args) {
        new NewClass1();
    }
}
