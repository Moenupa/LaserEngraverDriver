/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Hua_ban;
import examples.mainJFrame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

public class Wang2 {
    BufferedInputStream bis;
    BufferedOutputStream bos;
    ReadThread readThread;
    DatagramSocket udp;
    InetAddress serverAddress;
    public byte[] data_r = new byte[100];
    public byte[] data_w = new byte[2000];
    public byte[] fanhui = null;
    public byte[] fanhui2 = null;
    public int fan_hui_ma = 0;
    public boolean lian_jie = false;
    public boolean mang = false;
    public ServerSocket fu_wu = null;
    Socket ke_hu;
    public int xin_tiao = 0;
    public JButton bt = null;
    public Hua_ban hb = null;
    public JComboBox fbl = null;
    public JSlider rg = null;
    public JProgressBar jdt = null;
    public mainJFrame win = null;

    public Wang2() {
        this.fw_kai();
    }

    public void xin_tiao() {
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                while (true) {
                    if (!Hua_ban.kuang && !mainJFrame.kai_shi) {
                        if (!Wang2.this.xie2(new byte[]{11, 0, 4, 0}, 100)) {
                            try {
                                Thread.sleep(100L);
                            }
                            catch (InterruptedException ex) {
                                Logger.getLogger(Wang2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (!Wang2.this.xie2(new byte[]{11, 0, 4, 0}, 100)) {
                                try {
                                    Thread.sleep(100L);
                                }
                                catch (InterruptedException ex) {
                                    Logger.getLogger(Wang2.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (!Wang2.this.xie2(new byte[]{11, 0, 4, 0}, 100)) break;
                            }
                        }
                    }
                    try {
                        Thread.sleep(6000L);
                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Wang2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Wang2.this.guan_bi();
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    public void xie2323(byte[] data) {
        if (this.lian_jie) {
            this.data_w = data;
            WriteRead writeRead = new WriteRead();
            writeRead.start();
        }
        this.xin_tiao = 0;
    }

    public boolean lianjie(byte[] data, int chao) {
        this.fanhui = null;
        int m = 0;
        if (!this.lian_jie) {
            return false;
        }
        this.data_w = data;
        WriteRead writeRead = new WriteRead();
        writeRead.start();
        while (this.fanhui == null && m++ <= chao) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.fanhui == null) {
            return false;
        }
        return this.fanhui[0] == 9;
    }

    public boolean xie2(byte[] data, int chao) {
        this.fanhui = null;
        int m = 0;
        if (!this.lian_jie) {
            return false;
        }
        this.data_w = data;
        WriteRead writeRead = new WriteRead();
        writeRead.start();
        while (this.fanhui == null && m++ <= chao) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.fanhui != null;
    }

    public byte[] ban_ben(byte[] data, int chao) {
        this.fanhui = null;
        int m = 0;
        if (!this.lian_jie) {
            return null;
        }
        this.data_w = data;
        WriteRead writeRead = new WriteRead();
        writeRead.start();
        while (this.fanhui == null && m++ <= chao) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.fanhui;
    }

    public boolean kaishi(byte[] data, int chao) {
        byte[] fh = new byte[4];
        int ge_shu = 0;
        this.fanhui = null;
        int m = 0;
        if (!this.lian_jie) {
            return false;
        }
        this.data_w = data;
        WriteRead writeRead = new WriteRead();
        writeRead.start();
        boolean bl = false;
        while (m++ <= chao) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            bl = false;
            if (this.fanhui == null) {
                bl = true;
            } else if (this.fanhui.length < 4) {
                int i;
                for (i = 0; i < this.fanhui.length; ++i) {
                    System.out.println(this.fanhui[i]);
                }
                for (i = 0; i < this.fanhui.length; ++i) {
                    fh[ge_shu++] = this.fanhui[i];
                    if (ge_shu != 4) continue;
                    if (fh[0] == -1 && fh[1] == -1 && fh[2] == -1 && fh[3] == -2) {
                        return true;
                    }
                    ge_shu = 0;
                }
                bl = true;
            } else if (this.fanhui.length >= 4) {
                if (this.fanhui[0] == -1 && this.fanhui[1] == -1 && this.fanhui[2] == -1 && this.fanhui[3] == -2) {
                    return true;
                }
                bl = true;
            }
            if (bl) continue;
        }
        if (this.fanhui == null) {
            return false;
        }
        if (this.fanhui.length > 3) {
            return this.fanhui[0] == -1 && this.fanhui[1] == -1 && this.fanhui[2] == -1 && this.fanhui[3] == -2;
        }
        return false;
    }

    public boolean xie_shuju(byte[] data, int chao) {
        this.fanhui = null;
        int m = 0;
        if (!this.lian_jie) {
            return false;
        }
        this.data_w = data;
        WriteRead writeRead = new WriteRead();
        writeRead.start();
        while (this.fanhui == null && m++ <= chao) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.fanhui == null) {
            return false;
        }
        return this.fanhui.length == 1 && this.fanhui[0] == 9;
    }

    private void fw_kai() {
        new Thread(new Runnable(){

            @Override
            public void run() {
                block6: {
                    try {
                        Wang2.this.fu_wu = new ServerSocket(12346);
                        Wang2.this.ke_hu = Wang2.this.fu_wu.accept();
                        Wang2.this.bis = new BufferedInputStream(Wang2.this.ke_hu.getInputStream());
                        Wang2.this.bos = new BufferedOutputStream(Wang2.this.ke_hu.getOutputStream());
                        Wang2.this.readThread = new ReadThread();
                        Wang2.this.readThread.start();
                        Wang2.this.lian_jie = true;
                        if (Wang2.this.bt != null) {
                            byte[] fh = null;
                            int j = 0;
                            do {
                                if (j++ > 3) {
                                    Wang2.this.guan_bi();
                                    return;
                                }
                                Thread.sleep(500L);
                            } while (!Wang2.this.lianjie(new byte[]{10, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0}, 100));
                            fh = Wang2.this.ban_ben(new byte[]{-1, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0}, 100);
                            if (fh != null) {
                                Wang2.this.hb.ban_ben(fh, 2);
                            }
                            Thread.sleep(500L);
                            Wang2.this.bt.setIcon(new ImageIcon(this.getClass().getResource("/tu/wifi.png")));
                            int rg2 = Wang2.this.rg.getValue() * 2;
                            int jd = Wang2.this.fbl.getSelectedIndex();
                            Hua_ban.fen_bian_lv = 0.05 + (double)Wang2.this.fbl.getSelectedIndex() * 0.0125;
                            Wang2.this.hb.di_tu();
                            boolean bl = Wang2.this.lianjie(new byte[]{40, 0, 11, (byte)rg2, (byte)jd, 0, 0, 0, 0, 0, 0}, 200);
                        }
                        Wang2.this.xin_tiao();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Wang2.this.lian_jie = false;
                        if (Wang2.this.bt == null) break block6;
                        Wang2.this.bt.setIcon(new ImageIcon(this.getClass().getResource("/tu/wifi2.png")));
                    }
                }
            }
        }).start();
        new Thread(new Runnable(){

            @Override
            public void run() {
                while (!Wang2.this.lian_jie) {
                    try {
                        Wang2.this.udp = new DatagramSocket();
                        Wang2.this.serverAddress = InetAddress.getByName("255.255.255.255");
                        byte[] data = ("IPjiakuo\"" + Wang2.this.qu_ip() + "\",12346\r\n").getBytes();
                        byte[] data2 = new byte[data.length + 3];
                        data2[0] = 2;
                        data2[1] = (byte)(data2.length >> 8);
                        data2[2] = (byte)data2.length;
                        for (int i = 0; i < data.length; ++i) {
                            data2[i + 3] = data[i];
                        }
                        DatagramPacket packet = new DatagramPacket(data2, data2.length);
                        packet.setSocketAddress(new InetSocketAddress("255.255.255.255", 12345));
                        Wang2.this.udp.setBroadcast(true);
                        Wang2.this.udp.send(packet);
                    }
                    catch (SocketException e) {
                        e.printStackTrace();
                    }
                    catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(2000L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    String qu_ip() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(Wang2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip;
    }

    private String intToIp(int i) {
        return (i & 0xFF) + "." + (i >> 8 & 0xFF) + "." + (i >> 16 & 0xFF) + "." + (i >> 24 & 0xFF);
    }

    public void guan_bi() {
        this.bt.setIcon(new ImageIcon(this.getClass().getResource("/tu/wifi2.png")));
        this.lian_jie = false;
        if (this.bis != null) {
            this.bis = null;
        }
        if (this.bos != null) {
            this.bos = null;
        }
        if (this.readThread != null) {
            this.readThread = null;
        }
        if (this.udp != null) {
            this.udp = null;
        }
        if (this.fu_wu != null) {
            try {
                this.fu_wu.close();
            }
            catch (IOException ex) {
                Logger.getLogger(Wang2.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.fu_wu = null;
        }
        if (this.ke_hu != null) {
            try {
                this.ke_hu.close();
            }
            catch (IOException ex) {
                Logger.getLogger(Wang2.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.ke_hu = null;
        }
        if (this.win.wang != null) {
            this.win.wang = null;
        }
    }

    private class WriteRead
    extends Thread {
        private WriteRead() {
        }

        @Override
        public void run() {
            if (!Wang2.this.mang) {
                Wang2.this.mang = true;
                Wang2.this.xin_tiao = 0;
                try {
                    Wang2.this.bos.write(Wang2.this.data_w);
                    Wang2.this.bos.flush();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                Wang2.this.mang = false;
            } else {
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ReadThread
    extends Thread {
        private ReadThread() {
        }

        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        int count = 0;
                        if (Wang2.this.bis != null) {
                            count = Wang2.this.bis.available();
                        }
                        if (count > 0) {
                            int f = Wang2.this.bis.read(Wang2.this.data_r);
                            Wang2.this.fanhui = new byte[f];
                            for (int i = 0; i < f; ++i) {
                            }
                        }
                        Thread.sleep(10L);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }

    private class ReadThread2
    extends Thread {
        private ReadThread2() {
        }

        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        int count = 0;
                        if (Wang2.this.bis != null) {
                            count = Wang2.this.bis.available();
                        }
                        if (count > 0) {
                            int f = Wang2.this.bis.read(Wang2.this.data_r);
                            Wang2.this.fanhui = new byte[f];
                            Wang2.this.fanhui2 = new byte[f];
                            for (int i = 0; i < f; ++i) {
                                Wang2.this.fanhui[i] = Wang2.this.data_r[i];
                                Wang2.this.fanhui2[i] = Wang2.this.data_r[i];
                            }
                            if (Wang2.this.fanhui.length == 4) {
                                if (Wang2.this.fanhui[0] == -1 && Wang2.this.fanhui[1] == -1 && Wang2.this.fanhui[2] == 0) {
                                    if (Wang2.this.win != null && !Wang2.this.win.com_dakai && Wang2.this.lian_jie) {
                                        // empty if block
                                    }
                                } else if (Wang2.this.fanhui[0] == -1 && Wang2.this.fanhui[1] == -1 && Wang2.this.fanhui[2] == -1 && Wang2.this.fanhui[3] == -1) {
                                    Wang2.this.jdt.setValue(0);
                                    Wang2.this.jdt.setVisible(false);
                                    mainJFrame.kai_shi = false;
                                }
                            } else {
                                Wang2.this.fan_hui_ma = f;
                                Wang2.this.xin_tiao = 0;
                            }
                        }
                        Thread.sleep(10L);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }
}

