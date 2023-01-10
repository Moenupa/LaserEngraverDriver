/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gnu.io.SerialPort
 *  gnu.io.SerialPortEventListener
 */
package examples;

import examples.SerialPortUtil;
import examples.mainJFrame;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

public class Com {
    SerialPort com = null;
    boolean fan_hui_ma = false;
    public byte[] fan_hui_shu = new byte[]{0, 0, 0, 0};
    public JProgressBar jdt;
    int fh_shu = 0;
    public int jie_shou_lei_xing = 0;
    public int jie_shou_ji_shu = 0;
    public List<Byte> jie_shou_huan_cun = new ArrayList<Byte>();
    SerialPortEventListener jie_shou_win = event -> {
        if (event.getEventType() == 1) {
            Com com = this;
            synchronized (com) {
                try {
                    if (this.com == null) {
                        return;
                    }
                    byte[] fh = SerialPortUtil.readData(this.com);
                    switch (this.jie_shou_lei_xing) {
                        case 0: {
                            this.fan_hui_ma = true;
                            this.fan_hui_shu = fh;
                            break;
                        }
                        case 1: {
                            break;
                        }
                        case 2: {
                            int i;
                            this.jie_shou_ji_shu += fh.length;
                            for (i = 0; i < fh.length; ++i) {
                                this.jie_shou_huan_cun.add(fh[i]);
                            }
                            if (this.jie_shou_ji_shu < 3) break;
                            this.fan_hui_shu = new byte[3];
                            for (i = 0; i < 3; ++i) {
                                this.fan_hui_shu[i] = this.jie_shou_huan_cun.get(i);
                            }
                            this.fan_hui_ma = true;
                            break;
                        }
                        case 3: {
                            for (int i = 0; i < fh.length; ++i) {
                                this.jie_shou_huan_cun.add(fh[i]);
                            }
                            if (this.jie_shou_huan_cun.size() > 3 && this.jie_shou_huan_cun.get(this.jie_shou_huan_cun.size() - 4) == -1 && this.jie_shou_huan_cun.get(this.jie_shou_huan_cun.size() - 3) == -1 && this.jie_shou_huan_cun.get(this.jie_shou_huan_cun.size() - 2) == 0) {
                                this.jdt.setValue(this.jie_shou_huan_cun.get(this.jie_shou_huan_cun.size() - 1).byteValue());
                                this.jdt.setVisible(true);
                                mainJFrame.kai_shi = true;
                                mainJFrame.kai_shi2 = true;
                                this.jie_shou_huan_cun.clear();
                            }
                            System.out.println(fh.length);
                            mainJFrame.chaoshi = 0;
                        }
                    }
                }
                catch (Exception e1) {
                    Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
        }
    };
    SerialPortEventListener jie_shou = event -> {
        if (event.getEventType() == 1) {
            Com com = this;
            synchronized (com) {
                try {
                    if (this.com == null) {
                        return;
                    }
                    byte[] fh2 = SerialPortUtil.readData(this.com);
                    byte[] fh = new byte[this.fh_shu];
                    for (int i = 0; i < fh.length; ++i) {
                        fh[i] = fh2[i];
                    }
                    if (fh.length == 4) {
                        if (fh[0] == -1 && fh[1] == -1 && fh[2] == 0) {
                            this.jdt.setValue(fh[3]);
                            this.jdt.setVisible(true);
                            mainJFrame.kai_shi = true;
                            mainJFrame.kai_shi2 = true;
                            mainJFrame.chaoshi = 0;
                        } else if (fh[0] == -1 && fh[1] == -1 && fh[2] == -1 && fh[3] == -1) {
                            this.jdt.setValue(0);
                            this.jdt.setVisible(false);
                            mainJFrame.kai_shi = false;
                            mainJFrame.kai_shi2 = false;
                            mainJFrame.chaoshi = 0;
                        } else {
                            this.fan_hui_ma = true;
                            this.fan_hui_shu = fh;
                        }
                    } else {
                        this.fan_hui_ma = true;
                        this.fan_hui_shu = fh;
                    }
                }
                catch (Exception e1) {
                    Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
        }
    };

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Com(SerialPort c) {
        this.jie_shou_huan_cun.add((byte)1);
        Com com = this;
        synchronized (com) {
            this.com = c;
            try {
                Properties props = System.getProperties();
                String osName = props.getProperty("os.name");
                if (osName.contains("Win")) {
                    SerialPortUtil.setListenerToSerialPort(this.com, this.jie_shou_win);
                } else {
                    SerialPortUtil.setListenerToSerialPort(this.com, this.jie_shou_win);
                }
            }
            catch (TooManyListenersException ex) {
                Logger.getLogger(Com.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void guan_bi() {
        if (!this.com.equals(null)) {
            this.com.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean du_banben() {
        final Object suo_ = new Object();
        this.fh_shu = 3;
        this.jie_shou_ji_shu = 0;
        this.jie_shou_lei_xing = 2;
        this.jie_shou_huan_cun.clear();
        Com com = this;
        synchronized (com) {
        }
        this.fan_hui_ma = false;
        SerialPortUtil.sendData(this.com, new byte[]{-1, 0, 4, 0});
        Runnable runnable2 = new Runnable(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                Object object = suo_;
                synchronized (object) {
                    for (int i = 200; i > 0; --i) {
                        if (Com.this.fan_hui_ma) {
                            if (Com.this.fan_hui_shu.length == 3) break;
                            Com.this.fan_hui_ma = false;
                            break;
                        }
                        try {
                            Thread.sleep(10L);
                            continue;
                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object object = suo_;
        synchronized (object) {
            return this.fan_hui_ma;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean fa_song(byte[] shu, final int chao_shi) {
        final Object suo_ = new Object();
        this.fh_shu = 1;
        this.jie_shou_ji_shu = 0;
        this.jie_shou_lei_xing = 0;
        this.jie_shou_huan_cun.clear();
        Com com = this;
        synchronized (com) {
        }
        this.fan_hui_ma = false;
        SerialPortUtil.sendData(this.com, shu);
        Runnable runnable2 = new Runnable(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                Object object = suo_;
                synchronized (object) {
                    for (int i = chao_shi * 100; i > 0; --i) {
                        if (Com.this.fan_hui_ma) {
                            if (Com.this.fan_hui_shu[0] == 9) break;
                            if (Com.this.fan_hui_shu[0] == 8) {
                                Com.this.fan_hui_ma = false;
                                break;
                            }
                        }
                        try {
                            Thread.sleep(10L);
                            continue;
                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object object = suo_;
        synchronized (object) {
            return this.fan_hui_ma;
        }
    }

    public boolean fa_song_fe(byte[] shu, int chao_shi) {
        this.fh_shu = 4;
        this.jie_shou_ji_shu = 0;
        this.jie_shou_lei_xing = 3;
        this.jie_shou_huan_cun.clear();
        this.fan_hui_ma = false;
        SerialPortUtil.sendData(this.com, shu);
        return true;
    }

    public boolean fa_song_she_zhi(byte[] shu, int chao_shi) {
        this.fh_shu = 4;
        this.jie_shou_ji_shu = 0;
        this.jie_shou_lei_xing = 3;
        this.jie_shou_huan_cun.clear();
        this.fan_hui_ma = false;
        SerialPortUtil.sendData(this.com, shu);
        return true;
    }
}

