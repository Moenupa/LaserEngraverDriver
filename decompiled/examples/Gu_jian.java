/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gnu.io.SerialPort
 */
package examples;

import examples.Com;
import examples.mainJFrame;
import gnu.io.SerialPort;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Gu_jian
extends JFrame {
    private JButton jButton1;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JProgressBar jProgressBar1;

    public Gu_jian() {
        this.initComponents();
    }

    private void initComponents() {
        this.jButton1 = new JButton();
        this.jComboBox1 = new JComboBox();
        this.jLabel1 = new JLabel();
        this.jProgressBar1 = new JProgressBar();
        this.setDefaultCloseOperation(3);
        this.setBackground(SystemColor.controlHighlight);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowOpened(WindowEvent evt) {
                Gu_jian.this.formWindowOpened(evt);
            }
        });
        this.jButton1.setText("\u66f4\u65b0");
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Gu_jian.this.jButton1ActionPerformed(evt);
            }
        });
        this.jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]{"K6", "JL1", "JL1_S", "JL2", "JL3", "JL3_S", "JL4", "JL4_S", "L1", "L1_S", " "}));
        this.jLabel1.setText("\u8bbe\u5907\u578b\u53f7");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(66, 66, 66).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 72, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jButton1, -1, -1, 32767).addComponent(this.jComboBox1, -2, 164, -2)).addGap(50, 50, 50)).addComponent(this.jProgressBar1, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(42, 42, 42).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jComboBox1, -2, -1, -2).addComponent(this.jLabel1)).addGap(33, 33, 33).addComponent(this.jButton1, -2, 37, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, 32767).addComponent(this.jProgressBar1, -2, -1, -2)));
        this.pack();
    }

    private void formWindowOpened(WindowEvent evt) {
        SerialPort com = null;
        mainJFrame.com2 = new Com(com);
        this.jLabel1.setText(mainJFrame.str_xing_hao);
        this.jButton1.setText(mainJFrame.str_kai_shi_geng_xin);
        this.setBounds(500, 300, this.getWidth(), this.getHeight());
        this.setIconImage(new ImageIcon(this.getClass().getResource("/tu/tu_biao.png")).getImage());
    }

    public boolean downloadNet(String di_zhi) throws MalformedURLException {
        int bytesum = 0;
        int byteread = 0;
        URL url = new URL(di_zhi);
        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            String filepath = System.getProperty("user.dir");
            FileOutputStream fs = new FileOutputStream(filepath + "/bin.bin");
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                System.out.println(bytesum += byteread);
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
            fs.close();
            return true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    void fu_wei() {
        mainJFrame.com2.fa_song(new byte[]{-2, 0, 4, 0}, 1);
        try {
            Thread.sleep(600L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] getContent(String filePath) throws IOException {
        int offset;
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int)fileSize];
        int numRead = 0;
        for (offset = 0; offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0; offset += numRead) {
        }
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        return buffer;
    }

    int jiao_yan(List<Byte> m) {
        int jiao = 0;
        for (int i = 0; i < m.size(); ++i) {
            jiao += m.get(i).byteValue();
        }
        if (jiao > 255) {
            jiao ^= 0xFFFFFFFF;
            ++jiao;
        }
        return jiao &= 0xFF;
    }

    private static byte[] listTobyte1(List<Byte> list) {
        if (list == null || list.size() < 0) {
            return null;
        }
        byte[] bytes = new byte[list.size()];
        int i = 0;
        Iterator<Byte> iterator = list.iterator();
        while (iterator.hasNext()) {
            bytes[i] = iterator.next();
            ++i;
        }
        return bytes;
    }

    void sheng() {
        byte[] byData = null;
        mainJFrame.com2.fa_song(new byte[]{2, 0, 5, 0, 115}, 1);
        try {
            Thread.sleep(6000L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
        }
        String filepath = System.getProperty("user.dir");
        try {
            byData = this.getContent(filepath + "/bin.bin");
        }
        catch (IOException ex) {
            Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (byData.equals(null)) {
            return;
        }
        ArrayList<Byte> bao = new ArrayList<Byte>();
        int l = 0;
        l = byData.length % 1024 > 0 ? byData.length / 1024 + 1 : byData.length / 1024;
        for (int j = 0; j < l; ++j) {
            for (int i = 0; i < 1024; ++i) {
                if (j * 1024 + i < byData.length) {
                    bao.add(byData[j * 1024 + i]);
                    continue;
                }
                bao.add((byte)-1);
            }
            bao.add(0, (byte)4);
            bao.add(0, (byte)4);
            bao.add(0, (byte)3);
            bao.add((byte)this.jiao_yan(bao));
            do {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (!mainJFrame.com2.fa_song(Gu_jian.listTobyte1(bao), 1));
            bao.clear();
            Dimension d = this.jProgressBar1.getSize();
            Rectangle rect = new Rectangle(0, 0, d.width, d.height);
            this.jProgressBar1.setValue((int)((double)(j / l) * 100.0));
            this.jProgressBar1.paintImmediately(rect);
        }
    }

    void geng_xin(final String di_zhi) {
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                try {
                    if (Gu_jian.this.downloadNet(di_zhi)) {
                        Gu_jian.this.fu_wei();
                        Gu_jian.this.sheng();
                        mainJFrame.com2.fa_song(new byte[]{4, 0, 4, 0}, 1);
                    } else {
                        JOptionPane.showMessageDialog(null, mainJFrame.str_xia_zai_shi_bai);
                    }
                }
                catch (MalformedURLException ex) {
                    Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        switch (this.jComboBox1.getSelectedIndex()) {
            case 0: {
                this.geng_xin("http://jiakuo25.0594.bftii.com/K6.bin");
                break;
            }
            case 1: {
                System.out.print(1);
                break;
            }
            case 2: {
                System.out.print(2);
                break;
            }
            case 3: {
                System.out.print(3);
                break;
            }
            case 4: {
                System.out.print(4);
                break;
            }
            case 5: {
                System.out.print(4);
                break;
            }
            case 6: {
                System.out.print(4);
                break;
            }
            case 7: {
                System.out.print(4);
                break;
            }
            case 8: {
                System.out.print(4);
                break;
            }
            case 9: {
                System.out.print(4);
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (!"Nimbus".equals(info.getName())) continue;
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Gu_jian.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new Gu_jian().setVisible(true);
            }
        });
    }
}

