/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gnu.io.NoSuchPortException
 *  gnu.io.PortInUseException
 *  gnu.io.SerialPort
 *  gnu.io.UnsupportedCommOperationException
 *  org.kabeja.dxf.DXFDocument
 *  org.kabeja.dxf.DXFEntity
 *  org.kabeja.dxf.DXFLayer
 *  org.kabeja.parser.ParseException
 *  org.kabeja.parser.Parser
 *  org.kabeja.parser.ParserBuilder
 *  org.netbeans.lib.awtextra.AbsoluteConstraints
 *  org.netbeans.lib.awtextra.AbsoluteLayout
 */
package examples;

import examples.Che_xiao;
import examples.Com;
import examples.Dian;
import examples.FileTransferHandler;
import examples.Geng_xin;
import examples.Gu_jian;
import examples.Hua_ban;
import examples.ImagePreviewPanel;
import examples.SerialPortUtil;
import examples.Tu_yuan;
import examples.Wang;
import examples.Zi_ti2;
import examples.jie_xi_PLT;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.image4j.codec.bmp.BMPEncoder;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFEntity;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class mainJFrame
extends JFrame
implements KeyListener {
    JMenuBar jmb;
    JMenu menu1;
    JMenuItem item1;
    public byte[] banben = new byte[]{0, 0, 0, 0};
    public static String ce_shi = "";
    public Wang wang = null;
    public static String ban_ben = "v1.1.1";
    boolean an_xia = false;
    int anxia_x = 0;
    int anxia_y = 0;
    int anxia_x_1 = 0;
    int anxia_y_1 = 0;
    int an = 0;
    int an_niu = 0;
    SerialPort com = null;
    public static Com com2 = null;
    boolean com_dakai = false;
    private Object suo_fhm = new Object();
    private boolean kuang = false;
    boolean fan_hui_ma = false;
    public static boolean kai_shi = false;
    boolean zan_ting = false;
    boolean wifi_moshi = false;
    boolean shi_zi = false;
    mainJFrame win = null;
    public static int chaoshi = 0;
    public static boolean kai_shi2 = false;
    public static ResourceBundle bundle = null;
    int miao = 0;
    boolean fu_zhi = false;
    List<Tu_yuan> ty_fu_zhi = new ArrayList<Tu_yuan>();
    boolean tuo_dong = false;
    String str_da_kai = "";
    String str_wen_zi = "";
    String str_yuan = "";
    String str_fang = "";
    String str_xin = "";
    String str_xing = "";
    String str_bao_cun = "";
    String str_shi_zi = "";
    String str_yu_lan = "";
    String str_kai_shi = "";
    String str_ting_zhi = "";
    String str_lian_jie = "";
    String str_ruo_guang = "";
    String str_gong_lv = "";
    String str_shen_du = "";
    String str_gong_lv_sl = "";
    String str_shen_du_sl = "";
    String str_ci_shu = "";
    String str_jing_du = "";
    String str_gao = "";
    String str_zhong = "";
    String str_di = "";
    public static String str_zi_ti = "";
    public static String str_zi_xing = "";
    public static String str_chi_cun = "";
    public static String str_chang_gui = "";
    public static String str_xie_ti = "";
    public static String str_cu_ti = "";
    public static String str_cu_xie = "";
    public static String str_shu = "";
    public static String str_shi_liang = "";
    public static String str_geng_xin = "";
    public static String str_shi_zhi = "";
    public static String str_gu_jian = "";
    public static String str_xing_hao = "";
    public static String str_kai_shi_geng_xin = "";
    public static String str_xia_zai_shi_bai = "";
    private Hua_ban hua_ban1;
    private JButton jButton1;
    private JButton jButton10;
    private JButton jButton11;
    private JButton jButton12;
    private JButton jButton13;
    private JButton jButton14;
    private JButton jButton15;
    private JButton jButton16;
    private JButton jButton17;
    private JButton jButton18;
    private JButton jButton19;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JComboBox<String> jComboBox1;
    private JComboBox<String> jComboBox2;
    private JDialog jDialog1;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JSlider jSlider1;
    private JSlider jSlider2;
    private JSlider jSlider3;
    private JSlider jSlider4;
    private JSlider jSlider6;
    private JSlider jSlider7;
    private JSlider jSlider9;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JProgressBar jdt;

    public mainJFrame() {
        this.initComponents();
    }

    private void initComponents() {
        this.jDialog1 = new JDialog();
        this.jButton2 = new JButton();
        this.jButton1 = new JButton();
        this.jButton7 = new JButton();
        this.jButton8 = new JButton();
        this.jButton9 = new JButton();
        this.jButton10 = new JButton();
        this.jButton11 = new JButton();
        this.jButton14 = new JButton();
        this.jButton15 = new JButton();
        this.jButton17 = new JButton();
        this.hua_ban1 = new Hua_ban();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jTextField1 = new JTextField();
        this.jLabel2 = new JLabel();
        this.jTextField2 = new JTextField();
        this.jLabel3 = new JLabel();
        this.jTextField3 = new JTextField();
        this.jLabel4 = new JLabel();
        this.jTextField4 = new JTextField();
        this.jButton18 = new JButton();
        this.jButton19 = new JButton();
        this.jPanel2 = new JPanel();
        this.jSlider1 = new JSlider();
        this.jSlider2 = new JSlider();
        this.jLabel5 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jComboBox1 = new JComboBox();
        this.jLabel8 = new JLabel();
        this.jComboBox2 = new JComboBox();
        this.jSlider3 = new JSlider();
        this.jSlider4 = new JSlider();
        this.jLabel9 = new JLabel();
        this.jLabel10 = new JLabel();
        this.jSlider9 = new JSlider();
        this.jLabel15 = new JLabel();
        this.jLabel11 = new JLabel();
        this.jTextField5 = new JTextField();
        this.jLabel12 = new JLabel();
        this.jTextField6 = new JTextField();
        this.jButton3 = new JButton();
        this.jLabel14 = new JLabel();
        this.jSlider6 = new JSlider();
        this.jLabel13 = new JLabel();
        this.jSlider7 = new JSlider();
        this.jLabel16 = new JLabel();
        this.jdt = new JProgressBar();
        this.jButton16 = new JButton();
        this.jButton12 = new JButton();
        this.jButton13 = new JButton();
        this.jLabel6 = new JLabel();
        GroupLayout jDialog1Layout = new GroupLayout(this.jDialog1.getContentPane());
        this.jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(jDialog1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));
        jDialog1Layout.setVerticalGroup(jDialog1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));
        this.jButton2.setText("jButton2");
        this.setDefaultCloseOperation(3);
        this.setTitle("\u6fc0\u5149\u96d5\u523b\u673a");
        this.setBackground(new Color(204, 204, 204));
        this.setLocation(new Point(400, 200));
        this.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent evt) {
                mainJFrame.this.formComponentResized(evt);
            }
        });
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowOpened(WindowEvent evt) {
                mainJFrame.this.formWindowOpened(evt);
            }
        });
        this.jButton1.setIcon(new ImageIcon(this.getClass().getResource("/tu/tupian.png")));
        this.jButton1.setToolTipText("\u6253\u5f00\u56fe\u7247");
        this.jButton1.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent evt) {
                mainJFrame.this.jButton1MouseClicked(evt);
            }
        });
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton7.setIcon(new ImageIcon(this.getClass().getResource("/tu/wenzi.png")));
        this.jButton7.setToolTipText("\u8f93\u5165\u6587\u5b57");
        this.jButton7.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent evt) {
                mainJFrame.this.jButton7MouseClicked(evt);
            }
        });
        this.jButton7.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton7ActionPerformed(evt);
            }
        });
        this.jButton8.setIcon(new ImageIcon(this.getClass().getResource("/tu/yuan.png")));
        this.jButton8.setToolTipText("\u5706\u5f62");
        this.jButton8.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton8ActionPerformed(evt);
            }
        });
        this.jButton9.setIcon(new ImageIcon(this.getClass().getResource("/tu/fang.png")));
        this.jButton9.setToolTipText("\u6b63\u65b9\u5f62");
        this.jButton9.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton9ActionPerformed(evt);
            }
        });
        this.jButton10.setIcon(new ImageIcon(this.getClass().getResource("/tu/xin.png")));
        this.jButton10.setToolTipText("\u5fc3\u5f62");
        this.jButton10.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton10ActionPerformed(evt);
            }
        });
        this.jButton11.setIcon(new ImageIcon(this.getClass().getResource("/tu/5xing.png")));
        this.jButton11.setToolTipText("\u4e94\u89d2\u661f");
        this.jButton11.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton11ActionPerformed(evt);
            }
        });
        this.jButton14.setIcon(new ImageIcon(this.getClass().getResource("/tu/ding_wei.png")));
        this.jButton14.setToolTipText("\u9884\u89c8\u4f4d\u7f6e");
        this.jButton14.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton14ActionPerformed(evt);
            }
        });
        this.jButton15.setIcon(new ImageIcon(this.getClass().getResource("/tu/diaoke.png")));
        this.jButton15.setToolTipText("\u5f00\u59cb/\u6682\u505c");
        this.jButton15.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton15ActionPerformed(evt);
            }
        });
        this.jButton17.setIcon(new ImageIcon(this.getClass().getResource("/tu/tingzhi.png")));
        this.jButton17.setToolTipText("\u505c\u6b62");
        this.jButton17.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton17ActionPerformed(evt);
            }
        });
        this.hua_ban1.setBackground(new Color(255, 255, 255));
        this.hua_ban1.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 255)));
        this.hua_ban1.addMouseMotionListener(new MouseMotionAdapter(){

            @Override
            public void mouseDragged(MouseEvent evt) {
                mainJFrame.this.hua_ban1MouseDragged(evt);
            }
        });
        this.hua_ban1.addMouseWheelListener(new MouseWheelListener(){

            @Override
            public void mouseWheelMoved(MouseWheelEvent evt) {
                mainJFrame.this.hua_ban1MouseWheelMoved(evt);
            }
        });
        this.hua_ban1.addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent evt) {
                mainJFrame.this.hua_ban1MousePressed(evt);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                mainJFrame.this.hua_ban1MouseReleased(evt);
            }
        });
        this.hua_ban1.setLayout((LayoutManager)new AbsoluteLayout());
        this.jPanel1.setBackground(new Color(255, 255, 255));
        this.jPanel1.setPreferredSize(new Dimension(3, 3));
        this.jPanel1.setLayout((LayoutManager)new AbsoluteLayout());
        this.jLabel1.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel1.setText(" X:");
        this.jPanel1.add((Component)this.jLabel1, (Object)new AbsoluteConstraints(10, 10, 24, 23));
        this.jTextField1.setText("0");
        this.jTextField1.setMinimumSize(new Dimension(6, 20));
        this.jTextField1.setPreferredSize(new Dimension(12, 20));
        this.jPanel1.add((Component)this.jTextField1, (Object)new AbsoluteConstraints(38, 10, 30, 25));
        this.jLabel2.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel2.setText("Y:");
        this.jPanel1.add((Component)this.jLabel2, (Object)new AbsoluteConstraints(75, 10, -1, 23));
        this.jTextField2.setText("0");
        this.jTextField2.setMinimumSize(new Dimension(6, 20));
        this.jTextField2.setPreferredSize(new Dimension(12, 20));
        this.jPanel1.add((Component)this.jTextField2, (Object)new AbsoluteConstraints(93, 10, 30, 25));
        this.jLabel3.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel3.setText("W:");
        this.jPanel1.add((Component)this.jLabel3, (Object)new AbsoluteConstraints(130, 10, -1, 23));
        this.jTextField3.setText("0");
        this.jTextField3.setMinimumSize(new Dimension(6, 20));
        this.jTextField3.setPreferredSize(new Dimension(12, 20));
        this.jPanel1.add((Component)this.jTextField3, (Object)new AbsoluteConstraints(150, 10, 30, 25));
        this.jLabel4.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel4.setText("H:");
        this.jPanel1.add((Component)this.jLabel4, (Object)new AbsoluteConstraints(185, 10, -1, 20));
        this.jTextField4.setText("0");
        this.jTextField4.setMinimumSize(new Dimension(6, 20));
        this.jTextField4.setPreferredSize(new Dimension(12, 20));
        this.jPanel1.add((Component)this.jTextField4, (Object)new AbsoluteConstraints(200, 10, 30, 25));
        this.hua_ban1.add((Component)this.jPanel1, (Object)new AbsoluteConstraints(30, 20, 20, 10));
        this.jButton18.setIcon(new ImageIcon(this.getClass().getResource("/tu/usb2.png")));
        this.jButton18.setToolTipText("\u8fde\u63a5\u8bbe\u5907");
        this.jButton18.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton18ActionPerformed(evt);
            }
        });
        this.jButton19.setIcon(new ImageIcon(this.getClass().getResource("/tu/wifi2.png")));
        this.jButton19.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton19ActionPerformed(evt);
            }
        });
        this.jPanel2.setBackground(new Color(204, 204, 204));
        this.jPanel2.setLayout((LayoutManager)new AbsoluteLayout());
        this.jSlider1.setValue(100);
        this.jSlider1.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                mainJFrame.this.jSlider1StateChanged(evt);
            }
        });
        this.jSlider1.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent evt) {
                mainJFrame.this.jSlider1MouseReleased(evt);
            }
        });
        this.jPanel2.add((Component)this.jSlider1, (Object)new AbsoluteConstraints(0, 60, 190, -1));
        this.jSlider2.setValue(10);
        this.jSlider2.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                mainJFrame.this.jSlider2StateChanged(evt);
            }
        });
        this.jSlider2.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent evt) {
                mainJFrame.this.jSlider2MouseReleased(evt);
            }
        });
        this.jPanel2.add((Component)this.jSlider2, (Object)new AbsoluteConstraints(0, 110, 190, -1));
        this.jLabel5.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel5.setText("\u529f\u7387\uff1a100%");
        this.jPanel2.add((Component)this.jLabel5, (Object)new AbsoluteConstraints(10, 40, -1, -1));
        this.jLabel7.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel7.setText("\u6b21\u6570\uff1a");
        this.jPanel2.add((Component)this.jLabel7, (Object)new AbsoluteConstraints(10, 340, -1, -1));
        this.jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        this.jPanel2.add(this.jComboBox1, (Object)new AbsoluteConstraints(120, 340, 70, -1));
        this.jLabel8.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel8.setText("\u7cbe\u5ea6\uff1a");
        this.jPanel2.add((Component)this.jLabel8, (Object)new AbsoluteConstraints(10, 370, -1, -1));
        this.jComboBox2.setModel(new DefaultComboBoxModel<String>(new String[]{"\u9ad8", "\u4e2d", "\u4f4e"}));
        this.jComboBox2.setSelectedIndex(2);
        this.jComboBox2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jComboBox2ActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.jComboBox2, (Object)new AbsoluteConstraints(120, 370, 70, -1));
        this.jSlider3.setValue(100);
        this.jSlider3.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                mainJFrame.this.jSlider3StateChanged(evt);
            }
        });
        this.jSlider3.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent evt) {
                mainJFrame.this.jSlider3MouseReleased(evt);
            }
        });
        this.jPanel2.add((Component)this.jSlider3, (Object)new AbsoluteConstraints(0, 160, 190, -1));
        this.jSlider4.setValue(10);
        this.jSlider4.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                mainJFrame.this.jSlider4StateChanged(evt);
            }
        });
        this.jSlider4.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent evt) {
                mainJFrame.this.jSlider4MouseReleased(evt);
            }
        });
        this.jPanel2.add((Component)this.jSlider4, (Object)new AbsoluteConstraints(0, 210, 190, -1));
        this.jLabel9.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel9.setText("\u5207\u5272\u529f\u7387\uff1a100%");
        this.jPanel2.add((Component)this.jLabel9, (Object)new AbsoluteConstraints(10, 140, -1, -1));
        this.jLabel10.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel10.setText("WIFI\uff1a");
        this.jLabel10.setToolTipText("");
        this.jPanel2.add((Component)this.jLabel10, (Object)new AbsoluteConstraints(10, 400, -1, -1));
        this.jSlider9.setValue(10);
        this.jSlider9.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                mainJFrame.this.jSlider9StateChanged(evt);
            }
        });
        this.jSlider9.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent evt) {
                mainJFrame.this.jSlider9MouseReleased(evt);
            }
        });
        this.jPanel2.add((Component)this.jSlider9, (Object)new AbsoluteConstraints(0, 20, 190, -1));
        this.jLabel15.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel15.setText("\u5f31\u5149\u529f\u7387\uff1a10%");
        this.jPanel2.add((Component)this.jLabel15, (Object)new AbsoluteConstraints(10, 0, -1, -1));
        this.jLabel11.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel11.setText("\u5207\u5272\u6df1\u5ea6\uff1a10%");
        this.jPanel2.add((Component)this.jLabel11, (Object)new AbsoluteConstraints(10, 190, -1, -1));
        this.jPanel2.add((Component)this.jTextField5, (Object)new AbsoluteConstraints(10, 420, 180, -1));
        this.jLabel12.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel12.setText("\u5bc6\u7801\uff1a");
        this.jPanel2.add((Component)this.jLabel12, (Object)new AbsoluteConstraints(10, 450, -1, -1));
        this.jPanel2.add((Component)this.jTextField6, (Object)new AbsoluteConstraints(10, 470, 180, -1));
        this.jButton3.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jButton3.setText("\u5199\u5165");
        this.jPanel2.add((Component)this.jButton3, (Object)new AbsoluteConstraints(70, 500, -1, 30));
        this.jLabel14.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel14.setText("\u5bf9\u6bd4\u5ea6\uff1a50%");
        this.jPanel2.add((Component)this.jLabel14, (Object)new AbsoluteConstraints(10, 240, -1, -1));
        this.jSlider6.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                mainJFrame.this.jSlider6StateChanged(evt);
            }
        });
        this.jPanel2.add((Component)this.jSlider6, (Object)new AbsoluteConstraints(0, 260, 190, -1));
        this.jLabel13.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel13.setText("\u6df1\u5ea6\uff1a%10");
        this.jPanel2.add((Component)this.jLabel13, (Object)new AbsoluteConstraints(10, 90, -1, -1));
        this.jSlider7.setMaximum(10);
        this.jSlider7.setToolTipText("");
        this.jSlider7.setValue(5);
        this.jSlider7.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                mainJFrame.this.jSlider7StateChanged(evt);
            }
        });
        this.jPanel2.add((Component)this.jSlider7, (Object)new AbsoluteConstraints(0, 310, 190, -1));
        this.jLabel16.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel16.setText("\u586b\u5145\u5bc6\u5ea6\uff1a5");
        this.jPanel2.add((Component)this.jLabel16, (Object)new AbsoluteConstraints(10, 290, -1, -1));
        this.jdt.setRequestFocusEnabled(false);
        this.jdt.setStringPainted(true);
        this.jButton16.setIcon(new ImageIcon(this.getClass().getResource("/tu/shi_zi.png")));
        this.jButton16.setToolTipText("\u5341\u5b57\u5b9a\u4f4d");
        this.jButton16.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton16ActionPerformed(evt);
            }
        });
        this.jButton12.setIcon(new ImageIcon(this.getClass().getResource("/tu/baocun.png")));
        this.jButton12.setToolTipText("\u4e94\u89d2\u661f");
        this.jButton12.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton12ActionPerformed(evt);
            }
        });
        this.jButton13.setIcon(new ImageIcon(this.getClass().getResource("/tu/bmp.png")));
        this.jButton13.setToolTipText("to bmp");
        this.jButton13.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                mainJFrame.this.jButton13ActionPerformed(evt);
            }
        });
        this.jLabel6.setFont(new Font("\u5b8b\u4f53", 0, 14));
        this.jLabel6.setHorizontalAlignment(4);
        this.jLabel6.setText("0.0");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jButton1, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton7, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton8, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton9, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton10, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton11, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton12, -2, 60, -2).addGap(7, 7, 7).addComponent(this.jButton13, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton16, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton14, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton15, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton17, -2, 60, -2).addGap(54, 54, 54).addComponent(this.jButton18, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton19, -2, 60, -2).addGap(0, 0, 32767)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jdt, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.hua_ban1, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -2, -1, -2).addComponent(this.jLabel6, GroupLayout.Alignment.TRAILING, -2, 110, -2)))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton1, -2, 60, -2).addComponent(this.jButton7, -2, 60, -2).addComponent(this.jButton8, -2, 60, -2).addComponent(this.jButton9, -2, 60, -2).addComponent(this.jButton10, -2, 60, -2).addComponent(this.jButton11, -2, 60, -2).addComponent(this.jButton14, -2, 60, -2).addComponent(this.jButton15, -2, 60, -2).addComponent(this.jButton17, -2, 60, -2).addComponent(this.jButton18, -2, 60, -2).addComponent(this.jButton19, -2, 60, -2).addComponent(this.jButton16, -2, 60, -2).addComponent(this.jButton12, -2, 60, -2).addComponent(this.jButton13, -2, 60, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.hua_ban1, -1, 580, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jdt, -2, -1, -2).addComponent(this.jLabel6))));
        this.pack();
    }

    public void up() {
        this.hua_ban1.repaint();
    }

    void yu_yan() {
        Locale lo = Locale.getDefault();
        bundle = lo == Locale.CHINA ? ResourceBundle.getBundle("examples.diao_zh_CN") : (lo == Locale.US ? ResourceBundle.getBundle("examples.diao_en_US") : ResourceBundle.getBundle("examples.diao_"));
        this.str_da_kai = bundle.getString("str_da_kai");
        this.str_wen_zi = bundle.getString("str_wen_zi");
        this.str_yuan = bundle.getString("str_yuan");
        this.str_fang = bundle.getString("str_fang");
        this.str_xin = bundle.getString("str_xin");
        this.str_xing = bundle.getString("str_xing");
        this.str_bao_cun = bundle.getString("str_bao_cun");
        this.str_shi_zi = bundle.getString("str_shi_zi");
        this.str_yu_lan = bundle.getString("str_yu_lan");
        this.str_kai_shi = bundle.getString("str_kai_shi");
        this.str_ting_zhi = bundle.getString("str_ting_zhi");
        this.str_lian_jie = bundle.getString("str_lian_jie");
        this.str_ruo_guang = bundle.getString("str_ruo_guang");
        this.str_gong_lv = bundle.getString("str_gong_lv");
        this.str_shen_du = bundle.getString("str_shen_du");
        this.str_gong_lv_sl = bundle.getString("str_gong_lv_sl");
        this.str_shen_du_sl = bundle.getString("str_shen_du_sl");
        this.str_ci_shu = bundle.getString("str_ci_shu");
        this.str_jing_du = bundle.getString("str_jing_du");
        this.str_gao = bundle.getString("str_gao");
        this.str_zhong = bundle.getString("str_zhong");
        this.str_di = bundle.getString("str_di");
        str_zi_ti = bundle.getString("str_zi_ti");
        str_zi_xing = bundle.getString("str_zi_xing");
        str_chi_cun = bundle.getString("str_chi_cun");
        str_chang_gui = bundle.getString("str_chang_gui");
        str_xie_ti = bundle.getString("str_xie_ti");
        str_cu_ti = bundle.getString("str_cu_ti");
        str_cu_xie = bundle.getString("str_cu_xie");
        str_shu = bundle.getString("str_shu");
        str_shi_liang = bundle.getString("str_shi_liang");
        str_geng_xin = bundle.getString("str_geng_xin");
        str_shi_zhi = bundle.getString("str_shi_zhi");
        str_gu_jian = bundle.getString("str_gu_jian");
        str_xing_hao = bundle.getString("str_xing_hao");
        str_kai_shi_geng_xin = bundle.getString("str_kai_shi_geng_xin");
        str_xia_zai_shi_bai = bundle.getString("str_xia_zai_shi_bai");
        this.jButton1.setToolTipText(this.str_da_kai);
        this.jButton7.setToolTipText(this.str_wen_zi);
        this.jButton8.setToolTipText(this.str_yuan);
        this.jButton9.setToolTipText(this.str_fang);
        this.jButton10.setToolTipText(this.str_xin);
        this.jButton11.setToolTipText(this.str_xing);
        this.jButton12.setToolTipText(this.str_bao_cun);
        this.jButton16.setToolTipText(this.str_shi_zi);
        this.jButton14.setToolTipText(this.str_yu_lan);
        this.jButton15.setToolTipText(this.str_kai_shi);
        this.jButton17.setToolTipText(this.str_ting_zhi);
        this.jButton18.setToolTipText(this.str_lian_jie);
        this.jButton13.setToolTipText(bundle.getString("str_bmp"));
        this.jLabel15.setText(this.str_ruo_guang + "10%");
        this.jLabel5.setText(this.str_gong_lv + "100%");
        this.jLabel13.setText(this.str_shen_du + "10%");
        this.jLabel9.setText(this.str_gong_lv_sl + "100%");
        this.jLabel11.setText(this.str_shen_du_sl + "10%");
        this.jLabel7.setText(this.str_ci_shu);
        this.jLabel8.setText(this.str_jing_du);
        this.jComboBox2.removeAllItems();
        this.jComboBox2.addItem(this.str_gao);
        this.jComboBox2.addItem(this.str_zhong);
        this.jComboBox2.addItem(this.str_di);
        this.jLabel14.setText(bundle.getString("str_dui_bi") + "50%");
        this.jLabel16.setText(bundle.getString("str_tian_chong") + "5");
        this.setTitle(bundle.getString("str_ji_guang") + " " + ban_ben);
    }

    void cai_dan() {
        this.jmb = new JMenuBar();
        this.menu1 = new JMenu(str_shi_zhi + "(S)");
        this.menu1.setMnemonic('s');
        this.item1 = new JMenuItem(str_gu_jian);
        this.item1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Gu_jian gj = new Gu_jian();
                gj.setVisible(true);
                gj.setDefaultCloseOperation(2);
            }
        });
        this.menu1.add(this.item1);
        this.jmb.add(this.menu1);
        this.setJMenuBar(this.jmb);
    }

    private void formWindowOpened(WindowEvent evt) {
        try {
            this.jButton19.setVisible(false);
            Geng_xin.geng_xin();
            FileTransferHandler ft = new FileTransferHandler();
            FileTransferHandler.hb = this.hua_ban1;
            this.hua_ban1.setTransferHandler(ft);
            this.yu_yan();
            Tu_yuan ty = new Tu_yuan();
            ty.lei_xing = 0;
            int i = 0;
            while (true) {
                if (i >= Hua_ban.gao / 10 + 1) break;
                ty.lu_jing.moveTo(0.0, (double)i / Hua_ban.fen_bian_lv * 10.0);
                ty.lu_jing.lineTo((double)Hua_ban.kuan / Hua_ban.fen_bian_lv, (double)i / Hua_ban.fen_bian_lv * 10.0);
                ++i;
            }
            i = 0;
            while (true) {
                if (i >= Hua_ban.kuan / 10 + 1) break;
                ty.lu_jing.moveTo((double)i / Hua_ban.fen_bian_lv * 10.0, 0.0);
                ty.lu_jing.lineTo((double)i / Hua_ban.fen_bian_lv * 10.0, (double)Hua_ban.gao / Hua_ban.fen_bian_lv);
                ++i;
            }
            Hua_ban.ty_shuzu.add(ty);
            this.up();
            this.setIconImage(new ImageIcon(this.getClass().getResource("/tu/tu_biao.png")).getImage());
            this.jLabel10.setVisible(false);
            this.jLabel12.setVisible(false);
            this.jTextField5.setVisible(false);
            this.jTextField6.setVisible(false);
            this.jButton3.setVisible(false);
            this.hua_ban1.win2 = this;
            this.hua_ban1.jp = this.jPanel1;
            this.hua_ban1.win = this.jPanel2;
            this.hua_ban1.wb1 = this.jTextField1;
            this.hua_ban1.wb2 = this.jTextField2;
            this.hua_ban1.wb3 = this.jTextField3;
            this.hua_ban1.wb4 = this.jTextField4;
            this.jdt.setVisible(false);
            this.jTextField1.addKeyListener(this);
            this.jTextField2.addKeyListener(this);
            this.jTextField3.addKeyListener(this);
            this.jTextField4.addKeyListener(this);
            this.jTextField1.requestFocus();
            this.getContentPane().setBackground(new Color(240, 240, 240));
            this.jPanel2.setBackground(new Color(240, 240, 240));
            this.win = this;
            Runnable runnable2 = new Runnable(){

                @Override
                public void run() {
                    while (true) {
                        if (mainJFrame.this.wang == null) {
                            mainJFrame.this.wang = new Wang();
                            mainJFrame.this.wang.bt = mainJFrame.this.jButton19;
                            mainJFrame.this.wang.hb = mainJFrame.this.hua_ban1;
                            mainJFrame.this.wang.fbl = mainJFrame.this.jComboBox2;
                            mainJFrame.this.wang.rg = mainJFrame.this.jSlider9;
                            mainJFrame.this.wang.jdt = mainJFrame.this.jdt;
                            mainJFrame.this.wang.win = mainJFrame.this.win;
                        }
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (!kai_shi2 || mainJFrame.this.zan_ting) continue;
                        ++mainJFrame.this.miao;
                        mainJFrame.this.jLabel6.setText(mainJFrame.this.miao / 60 + "." + mainJFrame.this.miao % 60);
                        if (chaoshi++ <= 3 || chaoshi == 0) continue;
                        System.out.println("&&&");
                        mainJFrame.this.jdt.setValue(0);
                        mainJFrame.this.jdt.setVisible(false);
                        kai_shi = false;
                        chaoshi = 0;
                        kai_shi2 = false;
                    }
                }
            };
            Thread thread2 = new Thread(runnable2);
            thread2.start();
            this.qu_yu();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void ni() {
    }

    private void jButton1MouseClicked(MouseEvent evt) {
    }

    private void jButton7MouseClicked(MouseEvent evt) {
    }

    int qu_anniu(int x, int y) {
        Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
        if (x > rect.x - 15 && x < rect.x + 15 && y > rect.y - 15 && y < rect.y + 15) {
            return 1;
        }
        if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y - 15 && y < rect.y + 15) {
            return 2;
        }
        if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y + rect.height - 15 && y < rect.y + rect.height + 15) {
            return 3;
        }
        if (x > rect.x - 15 && x < rect.x + 15 && y > rect.y + rect.height - 15 && y < rect.y + rect.height + 15) {
            return 4;
        }
        if (x > rect.x + rect.width - 15 && x < rect.x + rect.width + 15 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
            return 5;
        }
        if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y - 20 && y < rect.y - 20 + 65) {
            System.out.println(6);
            return 6;
        }
        if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 45 && y < rect.y + 45 + 65) {
            System.out.println(7);
            return 7;
        }
        if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 110 && y < rect.y + 110 + 65) {
            System.out.println(8);
            return 8;
        }
        if (x > rect.x + rect.width + 25 && x < rect.x + rect.width + 25 + 60 && y > rect.y + 175 && y < rect.y + 175 + 65) {
            System.out.println(9);
            return 9;
        }
        if (x > rect.x + rect.width - 50 && x < rect.x + rect.width + 50 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
            return 10;
        }
        if (x > rect.x + rect.width - 85 && x < rect.x + rect.width + 85 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
            return 11;
        }
        if (x > rect.x + rect.width - 120 && x < rect.x + rect.width + 120 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
            return 12;
        }
        if (x > rect.x + rect.width - 155 && x < rect.x + rect.width + 155 && y > rect.y + rect.height + 20 && y < rect.y + rect.height + 50) {
            return 13;
        }
        return 0;
    }

    void shu_an_xia2(MouseEvent evt) {
        this.an_xia = true;
        this.anxia_x = evt.getX();
        this.anxia_y = evt.getY();
        this.anxia_x_1 = this.anxia_x;
        this.anxia_y_1 = this.anxia_y;
        this.an_niu = evt.getButton();
        if (this.an_niu == 1) {
            this.an = this.qu_anniu(this.anxia_x, this.anxia_y);
            if (this.an == 1) {
                ArrayList<Tu_yuan> sz = new ArrayList<Tu_yuan>();
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                    sz.add(Hua_ban.ty_shuzu.get(i));
                }
                Hua_ban.ty_shuzu = sz;
                this.up();
                Che_xiao.tian_jia();
                return;
            }
            if (this.an == 4) {
                Hua_ban.suo = !Hua_ban.suo;
                this.up();
                return;
            }
            if (this.an == 5) {
                Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
                this.up();
                Che_xiao.tian_jia();
                return;
            }
            if (this.an == 6) {
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                    Hua_ban.ty_shuzu.get((int)i).chuli_fs = 1;
                    Hua_ban.ty_shuzu.get(i).chu_li();
                    this.up();
                    Che_xiao.tian_jia();
                    return;
                }
                return;
            }
            if (this.an == 7) {
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                    Hua_ban.ty_shuzu.get((int)i).chuli_fs = 2;
                    Hua_ban.ty_shuzu.get(i).chu_li();
                    this.up();
                    Che_xiao.tian_jia();
                    return;
                }
                return;
            }
            if (this.an == 8) {
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                    Hua_ban.ty_shuzu.get((int)i).chuli_fs = 3;
                    Hua_ban.ty_shuzu.get(i).chu_li();
                    this.up();
                    Che_xiao.tian_jia();
                    return;
                }
                return;
            }
            if (this.an == 9) {
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                    Hua_ban.ty_shuzu.get((int)i).chuli_fs = 4;
                    Hua_ban.ty_shuzu.get(i).chu_li();
                    this.up();
                    Che_xiao.tian_jia();
                    return;
                }
                return;
            }
            if (this.an == 2) {
                return;
            }
            if (this.an == 3) {
                return;
            }
            if (this.an == 10) {
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                    Hua_ban.ty_shuzu.get((int)i).chuli_jxy = !Hua_ban.ty_shuzu.get((int)i).chuli_jxy;
                    Hua_ban.ty_shuzu.get(i).chu_li();
                    this.up();
                    Che_xiao.tian_jia();
                    return;
                }
                return;
            }
            if (this.an == 11) {
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                    Hua_ban.ty_shuzu.get((int)i).chuli_jxx = !Hua_ban.ty_shuzu.get((int)i).chuli_jxx;
                    Hua_ban.ty_shuzu.get(i).chu_li();
                    this.up();
                    Che_xiao.tian_jia();
                    return;
                }
                return;
            }
            if (this.an == 12) {
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                    Hua_ban.ty_shuzu.get((int)i).chuli_fan = !Hua_ban.ty_shuzu.get((int)i).chuli_fan;
                    Hua_ban.ty_shuzu.get(i).chu_li();
                    this.up();
                    Che_xiao.tian_jia();
                    return;
                }
                return;
            }
            if (this.an == 13) {
                Rectangle rect_q = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
                Tu_yuan ty = Tu_yuan.chuang_jian(0, null);
                ty.lu_jing = new GeneralPath();
                int ge_shu = 0;
                int wei_zhi = 0;
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 0) continue;
                    GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
                    lu_jing2.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
                    ty.lu_jing.append(lu_jing2, false);
                    ++ge_shu;
                    wei_zhi = i;
                }
                ty.tian_chong = ge_shu == 1 ? !Hua_ban.ty_shuzu.get((int)wei_zhi).tian_chong : true;
                ArrayList<Tu_yuan> sz = new ArrayList<Tu_yuan>();
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (Hua_ban.ty_shuzu.get((int)i).xuan_zhong && Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                    sz.add(Hua_ban.ty_shuzu.get(i));
                }
                Hua_ban.ty_shuzu = sz;
                ty.xuan_zhong = true;
                Rectangle r = Tu_yuan.qu_jv_xing(ty);
                AffineTransform sf1 = AffineTransform.getTranslateInstance(-r.x, -r.y);
                AffineTransform fb = new AffineTransform(sf1);
                fb.concatenate(ty.Tx);
                ty.Tx = fb;
                AffineTransform sf3 = AffineTransform.getScaleInstance((double)rect_q.width / (double)r.width, (double)rect_q.height / (double)r.height);
                AffineTransform fb3 = new AffineTransform(sf3);
                fb3.concatenate(ty.Tx);
                ty.Tx = fb3;
                AffineTransform sf2 = AffineTransform.getTranslateInstance(rect_q.x, rect_q.y);
                AffineTransform fb2 = new AffineTransform(sf2);
                fb2.concatenate(ty.Tx);
                ty.Tx = fb2;
                Hua_ban.ty_shuzu.add(ty);
                Che_xiao.tian_jia();
                return;
            }
            Rectangle rect_q = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
            if (this.anxia_x > rect_q.x && this.anxia_x < rect_q.x + rect_q.width && this.anxia_y > rect_q.y && this.anxia_y < rect_q.y + rect_q.height) {
                this.kuang = false;
                this.up();
            } else {
                for (int i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
                    if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) {
                        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)i).lu_jing);
                        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)i).Tx);
                        Rectangle rect = lu_jing2.getBounds();
                        if (this.anxia_x > rect.x && this.anxia_x < rect.x + rect.width && this.anxia_y > rect.y && this.anxia_y < rect.y + rect.height) {
                            for (int ii = 0; ii < Hua_ban.ty_shuzu.size(); ++ii) {
                                Hua_ban.ty_shuzu.get((int)ii).xuan_zhong = false;
                            }
                            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = true;
                            Tu_yuan ty = Hua_ban.ty_shuzu.get(i);
                            Hua_ban.ty_shuzu.remove(i);
                            Hua_ban.ty_shuzu.add(1, ty);
                            this.jSlider6.setValue(ty.yu_zhi);
                            this.up();
                            this.kuang = false;
                            return;
                        }
                    }
                    for (int ii = 0; ii < Hua_ban.ty_shuzu.size(); ++ii) {
                        Hua_ban.ty_shuzu.get((int)ii).xuan_zhong = false;
                    }
                    this.kuang = true;
                    this.up();
                }
            }
        } else if (this.an_niu == 3) {
            // empty if block
        }
    }

    private void hua_ban1MousePressed(MouseEvent evt) {
        this.shu_an_xia2(evt);
        this.jTextField1.requestFocus(true);
    }

    private void hua_ban1MouseReleased(MouseEvent evt) {
        this.an_xia = false;
        Tu_yuan.tuo = false;
        if (this.tuo_dong) {
            this.tuo_dong = false;
            Che_xiao.tian_jia();
        }
        this.up();
    }

    void shu_yi_dong2(MouseEvent evt) {
        if (this.an_xia) {
            int dx = evt.getX();
            int dy = evt.getY();
            if (this.an_niu == 1) {
                int i;
                Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
                if (this.an == 0) {
                    if (this.kuang) {
                        int g;
                        int y;
                        int w;
                        int x;
                        if (dx < this.anxia_x_1) {
                            x = dx;
                            w = this.anxia_x_1 - dx;
                        } else {
                            x = this.anxia_x_1;
                            w = dx - this.anxia_x_1;
                        }
                        if (dy < this.anxia_y_1) {
                            y = dy;
                            g = this.anxia_y_1 - dy;
                        } else {
                            y = this.anxia_y_1;
                            g = dy - this.anxia_y_1;
                        }
                        Tu_yuan.tuo = true;
                        Tu_yuan.shu_biao = new Rectangle(x, y, w, g);
                        Tu_yuan.kuang_xuan(Hua_ban.ty_shuzu, Tu_yuan.shu_biao);
                    } else {
                        for (int i2 = 0; i2 < Hua_ban.ty_shuzu.size(); ++i2) {
                            if (!Hua_ban.ty_shuzu.get((int)i2).xuan_zhong) continue;
                            Hua_ban.ty_shuzu.get(i2).ping_yi(dx - this.anxia_x, dy - this.anxia_y);
                        }
                    }
                    this.anxia_x = dx;
                    this.anxia_y = dy;
                    this.up();
                } else if (this.an == 2) {
                    float zhong_xin_x = 0.0f;
                    float zhong_xin_y = 0.0f;
                    float jiao1 = 0.0f;
                    float jiao2 = 0.0f;
                    zhong_xin_x = rect.x + rect.width / 2;
                    zhong_xin_y = rect.y + rect.height / 2;
                    if ((float)this.anxia_x > zhong_xin_x && (float)this.anxia_y < zhong_xin_y) {
                        jiao1 = 360.0f - (float)Math.toDegrees(Math.atan((zhong_xin_y - (float)this.anxia_y) / ((float)this.anxia_x - zhong_xin_x)));
                    } else if ((float)this.anxia_x < zhong_xin_x && (float)this.anxia_y < zhong_xin_y) {
                        jiao1 = 270.0f - (float)Math.toDegrees(Math.atan((zhong_xin_x - (float)this.anxia_x) / (zhong_xin_y - (float)this.anxia_y)));
                    } else if ((float)this.anxia_x < zhong_xin_x && (float)this.anxia_y > zhong_xin_y) {
                        jiao1 = 90.0f + (float)Math.toDegrees(Math.atan((zhong_xin_x - (float)this.anxia_x) / ((float)this.anxia_y - zhong_xin_y)));
                    } else if ((float)this.anxia_x > zhong_xin_x && (float)this.anxia_y > zhong_xin_y) {
                        jiao1 = (float)Math.toDegrees(Math.atan(((float)this.anxia_y - zhong_xin_y) / ((float)this.anxia_x - zhong_xin_x)));
                    }
                    if ((float)dx > zhong_xin_x && (float)dy < zhong_xin_y) {
                        jiao2 = 360.0f - (float)Math.toDegrees(Math.atan((zhong_xin_y - (float)dy) / ((float)dx - zhong_xin_x)));
                        for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                            if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                            Hua_ban.ty_shuzu.get(i).xuan_zhuan(jiao2 - jiao1, zhong_xin_x, zhong_xin_y);
                        }
                        this.anxia_x = dx;
                        this.anxia_y = dy;
                    } else if ((float)dx < zhong_xin_x && (float)dy < zhong_xin_y) {
                        jiao2 = 270.0f - (float)Math.toDegrees(Math.atan((zhong_xin_x - (float)dx) / (zhong_xin_y - (float)dy)));
                        for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                            if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                            Hua_ban.ty_shuzu.get(i).xuan_zhuan(jiao2 - jiao1, zhong_xin_x, zhong_xin_y);
                        }
                        this.anxia_x = dx;
                        this.anxia_y = dy;
                    } else if ((float)dx < zhong_xin_x && (float)dy > zhong_xin_y) {
                        jiao2 = 90.0f + (float)Math.toDegrees(Math.atan((zhong_xin_x - (float)dx) / ((float)dy - zhong_xin_y)));
                        for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                            if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                            Hua_ban.ty_shuzu.get(i).xuan_zhuan(jiao2 - jiao1, zhong_xin_x, zhong_xin_y);
                        }
                        this.anxia_x = dx;
                        this.anxia_y = dy;
                    } else if ((float)dx > zhong_xin_x && (float)dy > zhong_xin_y) {
                        jiao2 = (float)Math.toDegrees(Math.atan(((float)dy - zhong_xin_y) / ((float)dx - zhong_xin_x)));
                        for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                            if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                            Hua_ban.ty_shuzu.get(i).xuan_zhuan(jiao2 - jiao1, zhong_xin_x, zhong_xin_y);
                        }
                        this.anxia_x = dx;
                        this.anxia_y = dy;
                    }
                    this.up();
                }
                if (this.an == 3) {
                    if (Hua_ban.suo) {
                        double sf = (double)(this.anxia_x - rect.x) / (double)rect.width;
                        if (sf > 0.0) {
                            for (int i3 = 0; i3 < Hua_ban.ty_shuzu.size(); ++i3) {
                                if (!Hua_ban.ty_shuzu.get((int)i3).xuan_zhong) continue;
                                Hua_ban.ty_shuzu.get(i3).ping_yi(-rect.x, -rect.y);
                                Hua_ban.ty_shuzu.get(i3).suo_fang(sf, sf);
                                Hua_ban.ty_shuzu.get(i3).ping_yi(rect.x, rect.y);
                            }
                        }
                        this.anxia_x = dx;
                    } else {
                        double sf_x = (double)(this.anxia_x - rect.x) / (double)rect.width;
                        double sf_y = (double)(this.anxia_y - rect.y) / (double)rect.height;
                        if (sf_x > 0.0 && sf_y > 0.0) {
                            for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                                if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                                Hua_ban.ty_shuzu.get(i).ping_yi(-rect.x, -rect.y);
                                Hua_ban.ty_shuzu.get(i).suo_fang(sf_x, sf_y);
                                Hua_ban.ty_shuzu.get(i).ping_yi(rect.x, rect.y);
                            }
                        }
                        this.anxia_x = dx;
                        this.anxia_y = dy;
                    }
                    this.up();
                }
                this.tuo_dong = true;
            } else if (this.an_niu == 3) {
                AffineTransform py = AffineTransform.getTranslateInstance(dx - this.anxia_x, dy - this.anxia_y);
                Hua_ban.quan_x = (int)((double)(Hua_ban.quan_x + (dx - this.anxia_x)) / Hua_ban.quan_beishu);
                Hua_ban.quan_y = (int)((double)(Hua_ban.quan_y + (dy - this.anxia_y)) / Hua_ban.quan_beishu);
                for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                    AffineTransform fb = new AffineTransform(py);
                    fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                    Hua_ban.ty_shuzu.get((int)i).Tx = fb;
                }
                this.anxia_x = dx;
                this.anxia_y = dy;
                this.up();
            }
        }
    }

    private void hua_ban1MouseDragged(MouseEvent evt) {
        this.shu_yi_dong2(evt);
    }

    public static BufferedImage convertToBufferedImage(Image image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        fc.setAccessory(preview);
        fc.addPropertyChangeListener(preview);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files (.nc,.bmp,.jpg,.png,.jpeg,.gif,.xj,plt)", "nc", "bmp", "jpg", "png", "jpeg", "gif", "xj", "plt");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);
        if (fc.getSelectedFile() != null && returnVal == 0) {
            File gcodeFile = fc.getSelectedFile();
            String gcodeFilePath = fc.getSelectedFile().getPath();
            String fileName = gcodeFile.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if ((suffix = suffix.toUpperCase()).equals("BMP") || suffix.equals("JPG") || suffix.equals("PNG") || suffix.equals("JPEG") || suffix.equals("GIF")) {
                try {
                    BufferedImage bmp = ImageIO.read(gcodeFile);
                    Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(1, bmp));
                    for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                        Hua_ban.ty_shuzu.get((int)i).xuan_zhong = false;
                    }
                    Hua_ban.ty_shuzu.get((int)(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
                    Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
                    Che_xiao.tian_jia();
                    this.up();
                }
                catch (IOException bmp) {}
            } else if (suffix.equals("XJ")) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(gcodeFilePath));){
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
                Che_xiao.tian_jia();
                this.up();
            } else if (suffix.equals("PLT")) {
                jie_xi_PLT plt = new jie_xi_PLT();
                plt.jie_xi_PLT(gcodeFile);
                plt = null;
                Che_xiao.tian_jia();
                this.up();
            }
        }
    }

    private void hua_ban1MouseWheelMoved(MouseWheelEvent evt) {
        int dx = evt.getX();
        int dy = evt.getY();
        if (evt.getPreciseWheelRotation() < 0.0) {
            AffineTransform sf1 = AffineTransform.getTranslateInstance(-dx, -dy);
            for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                AffineTransform fb = new AffineTransform(sf1);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
            Hua_ban.quan_beishu *= 1.1;
            AffineTransform sf = AffineTransform.getScaleInstance(1.1, 1.1);
            for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                AffineTransform fb = new AffineTransform(sf);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
            AffineTransform sf2 = AffineTransform.getTranslateInstance(dx, dy);
            for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                AffineTransform fb = new AffineTransform(sf2);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
        } else {
            GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
            lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
            Rectangle rect = lu_jing2.getBounds();
            AffineTransform sf1 = AffineTransform.getTranslateInstance(-dx, -dy);
            for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                AffineTransform fb = new AffineTransform(sf1);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
            Hua_ban.quan_beishu *= 0.9;
            AffineTransform sf = AffineTransform.getScaleInstance(0.9, 0.9);
            for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                AffineTransform fb = new AffineTransform(sf);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
            AffineTransform sf2 = AffineTransform.getTranslateInstance(dx, dy);
            for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                AffineTransform fb = new AffineTransform(sf2);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
        }
        this.up();
    }

    private void jButton17ActionPerformed(ActionEvent evt) {
        kai_shi = false;
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                if (mainJFrame.this.com_dakai) {
                    com2.fa_song(new byte[]{22, 0, 4, 0}, 2);
                } else if (mainJFrame.this.wang.lian_jie) {
                    mainJFrame.this.wang.xie2(new byte[]{22, 0, 4, 0}, 200);
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    private void jButton18ActionPerformed(ActionEvent evt) {
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                mainJFrame.this.lian_jie2();
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    public void jie_xi_dxf() throws ParseException {
        try {
            Parser dxfParser = ParserBuilder.createDefaultParser();
            dxfParser.parse((InputStream)new FileInputStream("C:\\Users\\Administrator\\Desktop\\dxf.dxf"), "UTF-8");
            DXFDocument doc = dxfParser.getDocument();
            Iterator kuai_list = doc.getDXFLayerIterator();
            while (kuai_list.hasNext()) {
                DXFLayer kuai = (DXFLayer)kuai_list.next();
                Iterator shi_ti = kuai.getDXFEntityTypeIterator();
                while (shi_ti.hasNext()) {
                    String shi_ti2 = (String)shi_ti.next();
                    System.out.println(shi_ti2);
                    List st = kuai.getDXFEntities(shi_ti2);
                    for (int i = 0; i < st.size(); ++i) {
                        System.out.println(((DXFEntity)st.get(i)).getType());
                    }
                }
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton19ActionPerformed(ActionEvent evt) {
    }

    private void jButton15ActionPerformed(ActionEvent evt) {
        if (!this.com_dakai && !this.wang.lian_jie) {
            return;
        }
        if (!kai_shi) {
            if (Hua_ban.kuang) {
                com2.fa_song(new byte[]{33, 0, 4, 0}, 3);
                Hua_ban.kuang = false;
            }
            this.xie_ru(new byte[]{22, 0, 4, 0}, 2);
            Runnable runnable2 = new Runnable(){

                @Override
                public void run() {
                    try {
                        mainJFrame.this.jButton15.setEnabled(false);
                        mainJFrame.this.tuo_ji2();
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                        Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            Thread thread2 = new Thread(runnable2);
            thread2.start();
            this.miao = 0;
            chaoshi = 0;
        }
    }

    private void jButton14ActionPerformed(ActionEvent evt) {
        if (kai_shi) {
            return;
        }
        if (this.com_dakai) {
            if (Hua_ban.kuang) {
                Runnable runnable2 = new Runnable(){

                    @Override
                    public void run() {
                        if (com2.fa_song(new byte[]{33, 0, 4, 0}, 3)) {
                            // empty if block
                        }
                        Hua_ban.kuang = false;
                        mainJFrame.this.up();
                    }
                };
                Thread thread2 = new Thread(runnable2);
                thread2.start();
            } else {
                Runnable runnable2 = new Runnable(){

                    @Override
                    public void run() {
                        Tu_yuan.qu_jvxing(Hua_ban.ty_shuzu);
                        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
                        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
                        Rectangle rect = lu_jing2.getBounds();
                        Rectangle zui_zhong_wjx2 = new Rectangle(Tu_yuan.zui_zhong_wjx);
                        AffineTransform sf = AffineTransform.getTranslateInstance(0 - rect.x, 0 - rect.y);
                        zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
                        sf = AffineTransform.getScaleInstance(1.0 / Hua_ban.quan_beishu, 1.0 / Hua_ban.quan_beishu);
                        zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
                        if (zui_zhong_wjx2.width < 2 && zui_zhong_wjx2.height < 2) {
                            return;
                        }
                        byte kg = (byte)(zui_zhong_wjx2.width >> 8);
                        byte kd = (byte)zui_zhong_wjx2.width;
                        byte gg = (byte)(zui_zhong_wjx2.height >> 8);
                        byte gd = (byte)zui_zhong_wjx2.height;
                        byte xg = (byte)(zui_zhong_wjx2.x + 67 + zui_zhong_wjx2.width / 2 >> 8);
                        byte xd = (byte)(zui_zhong_wjx2.x + 67 + zui_zhong_wjx2.width / 2);
                        byte yg = (byte)(zui_zhong_wjx2.y + zui_zhong_wjx2.height / 2 >> 8);
                        byte yd = (byte)(zui_zhong_wjx2.y + zui_zhong_wjx2.height / 2);
                        if (com2.fa_song(new byte[]{32, 0, 11, kg, kd, gg, gd, xg, xd, yg, yd}, 1)) {
                            // empty if block
                        }
                        Hua_ban.kuang = true;
                        mainJFrame.this.up();
                    }
                };
                Thread thread2 = new Thread(runnable2);
                thread2.start();
            }
        } else if (this.wang.lian_jie) {
            if (Hua_ban.kuang) {
                Runnable runnable2 = new Runnable(){

                    @Override
                    public void run() {
                        mainJFrame.this.wang.xie2(new byte[]{33, 0, 4, 0}, 300);
                        Hua_ban.kuang = false;
                        mainJFrame.this.up();
                    }
                };
                Thread thread2 = new Thread(runnable2);
                thread2.start();
            } else {
                Runnable runnable2 = new Runnable(){

                    @Override
                    public void run() {
                        Tu_yuan.qu_jvxing(Hua_ban.ty_shuzu);
                        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
                        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
                        Rectangle rect = lu_jing2.getBounds();
                        Rectangle zui_zhong_wjx2 = new Rectangle(Tu_yuan.zui_zhong_wjx);
                        AffineTransform sf = AffineTransform.getTranslateInstance(0 - rect.x, 0 - rect.y);
                        zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
                        sf = AffineTransform.getScaleInstance(1.0 / Hua_ban.quan_beishu, 1.0 / Hua_ban.quan_beishu);
                        zui_zhong_wjx2 = sf.createTransformedShape(zui_zhong_wjx2).getBounds();
                        byte kg = (byte)(zui_zhong_wjx2.width >> 8);
                        byte kd = (byte)zui_zhong_wjx2.width;
                        byte gg = (byte)(zui_zhong_wjx2.height >> 8);
                        byte gd = (byte)zui_zhong_wjx2.height;
                        byte xg = (byte)(zui_zhong_wjx2.x + 67 + zui_zhong_wjx2.width / 2 >> 8);
                        byte xd = (byte)(zui_zhong_wjx2.x + 67 + zui_zhong_wjx2.width / 2);
                        byte yg = (byte)(zui_zhong_wjx2.y + zui_zhong_wjx2.height / 2 >> 8);
                        byte yd = (byte)(zui_zhong_wjx2.y + zui_zhong_wjx2.height / 2);
                        mainJFrame.this.wang.xie2(new byte[]{32, 0, 11, kg, kd, gg, gd, xg, xd, yg, yd}, 100);
                        Hua_ban.kuang = true;
                        mainJFrame.this.up();
                    }
                };
                Thread thread2 = new Thread(runnable2);
                thread2.start();
            }
        }
    }

    private void jButton8ActionPerformed(ActionEvent evt) {
        Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(2, null));
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = false;
        }
        Hua_ban.ty_shuzu.get((int)(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
        Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
        Che_xiao.tian_jia();
        this.up();
    }

    private void jButton10ActionPerformed(ActionEvent evt) {
        Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(3, null));
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = false;
        }
        Hua_ban.ty_shuzu.get((int)(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
        Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
        Che_xiao.tian_jia();
        this.up();
    }

    private void jButton11ActionPerformed(ActionEvent evt) {
        Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(4, null));
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = false;
        }
        Hua_ban.ty_shuzu.get((int)(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
        Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
        Che_xiao.tian_jia();
        this.up();
    }

    private void jButton7ActionPerformed(ActionEvent evt) {
        Zi_ti2 dialog = new Zi_ti2(this.hua_ban1, true);
        dialog.setDefaultCloseOperation(2);
        dialog.setVisible(true);
    }

    private void jButton9ActionPerformed(ActionEvent evt) {
        Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian(0, null));
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = false;
        }
        Hua_ban.ty_shuzu.get((int)(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
        Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
        Che_xiao.tian_jia();
        this.up();
    }

    private void formComponentResized(ComponentEvent evt) {
    }

    private void jSlider1StateChanged(ChangeEvent evt) {
        this.jLabel5.setText(this.str_gong_lv + this.jSlider1.getValue() + "%");
    }

    void she_zhi() {
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                int sd = mainJFrame.this.jSlider2.getValue();
                int gl = mainJFrame.this.jSlider1.getValue() * 10;
                if (mainJFrame.this.com_dakai) {
                    com2.fa_song_she_zhi(new byte[]{37, 0, 11, (byte)(sd >> 8), (byte)sd, (byte)(gl >> 8), (byte)gl, 0, 0, 0, 0}, 2);
                } else if (mainJFrame.this.wang.lian_jie) {
                    mainJFrame.this.wang.xie2(new byte[]{37, 0, 11, (byte)(sd >> 8), (byte)sd, (byte)(gl >> 8), (byte)gl, 0, 0, 0, 0}, 200);
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    void she_zhi_qg() {
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                int sd = mainJFrame.this.jSlider4.getValue();
                int gl = mainJFrame.this.jSlider3.getValue() * 10;
                if (mainJFrame.this.com_dakai) {
                    com2.fa_song_she_zhi(new byte[]{37, 0, 11, (byte)(sd >> 8), (byte)sd, (byte)(gl >> 8), (byte)gl, 0, 0, 0, 0}, 2);
                } else if (mainJFrame.this.wang.lian_jie) {
                    mainJFrame.this.wang.xie2(new byte[]{37, 0, 11, (byte)(sd >> 8), (byte)sd, (byte)(gl >> 8), (byte)gl, 0, 0, 0, 0}, 200);
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    private void jSlider1MouseReleased(MouseEvent evt) {
        if (!kai_shi) {
            return;
        }
        this.she_zhi();
    }

    private void jSlider2StateChanged(ChangeEvent evt) {
        this.jLabel13.setText(this.str_shen_du + this.jSlider2.getValue() + "%");
    }

    private void jSlider2MouseReleased(MouseEvent evt) {
        if (!kai_shi) {
            return;
        }
        this.she_zhi();
    }

    private void jSlider9StateChanged(ChangeEvent evt) {
        this.jLabel15.setText(this.str_ruo_guang + this.jSlider9.getValue() + "%");
    }

    void she_zhi_can_shu() {
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                int rg = mainJFrame.this.jSlider9.getValue() * 2;
                int jd = mainJFrame.this.jComboBox2.getSelectedIndex();
                if (mainJFrame.this.com_dakai) {
                    com2.fa_song(new byte[]{40, 0, 11, (byte)rg, (byte)jd, 0, 0, 0, 0, 0, 0}, 2);
                } else if (mainJFrame.this.wang.lian_jie) {
                    mainJFrame.this.wang.xie2(new byte[]{40, 0, 11, (byte)rg, (byte)jd, 0, 0, 0, 0, 0, 0}, 200);
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    private void jSlider9MouseReleased(MouseEvent evt) {
        if (this.com_dakai) {
            this.she_zhi_can_shu();
        } else if (this.wang.lian_jie) {
            Runnable runnable2 = new Runnable(){

                @Override
                public void run() {
                    int rg = mainJFrame.this.jSlider9.getValue() * 2;
                    int jd = mainJFrame.this.jComboBox2.getSelectedIndex();
                    mainJFrame.this.wang.xie2(new byte[]{40, 0, 11, (byte)rg, (byte)jd, 0, 0, 0, 0, 0, 0}, 200);
                }
            };
            Thread thread2 = new Thread(runnable2);
            thread2.start();
        }
    }

    private void jComboBox2ActionPerformed(ActionEvent evt) {
        if (this.wang == null) {
            return;
        }
        if (this.com_dakai || this.wang.lian_jie) {
            if (this.banben[2] == 37) {
                if (this.jComboBox2.getSelectedIndex() == 0) {
                    Hua_ban.fen_bian_lv = 0.064;
                } else if (this.jComboBox2.getSelectedIndex() == 1) {
                    Hua_ban.fen_bian_lv = 0.08;
                } else if (this.jComboBox2.getSelectedIndex() == 2) {
                    Hua_ban.fen_bian_lv = 0.096;
                }
            } else if (this.jComboBox2.getSelectedIndex() == 0) {
                Hua_ban.fen_bian_lv = 0.05;
            } else if (this.jComboBox2.getSelectedIndex() == 1) {
                Hua_ban.fen_bian_lv = 0.0625;
            } else if (this.jComboBox2.getSelectedIndex() == 2) {
                Hua_ban.fen_bian_lv = 0.075;
            }
            this.hua_ban1.di_tu();
            this.she_zhi_can_shu();
        }
    }

    private void jSlider3StateChanged(ChangeEvent evt) {
        this.jLabel9.setText(this.str_gong_lv_sl + this.jSlider3.getValue() + "%");
    }

    private void jSlider4StateChanged(ChangeEvent evt) {
        this.jLabel11.setText(this.str_shen_du_sl + this.jSlider4.getValue() + "%");
    }

    private void jButton16ActionPerformed(ActionEvent evt) {
        Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                if (mainJFrame.this.com_dakai) {
                    if (mainJFrame.this.shi_zi) {
                        com2.fa_song(new byte[]{7, 0, 4, 0}, 2);
                    } else {
                        com2.fa_song(new byte[]{6, 0, 4, 0}, 2);
                    }
                    mainJFrame.this.shi_zi = !mainJFrame.this.shi_zi;
                } else if (mainJFrame.this.wang.lian_jie) {
                    if (mainJFrame.this.shi_zi) {
                        mainJFrame.this.wang.xie2(new byte[]{7, 0, 4, 0}, 200);
                    } else {
                        mainJFrame.this.wang.xie2(new byte[]{6, 0, 4, 0}, 200);
                    }
                    mainJFrame.this.shi_zi = !mainJFrame.this.shi_zi;
                }
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    private void jButton12ActionPerformed(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files (.xj)", "xj");
        chooser.setFileFilter(filter);
        int option = chooser.showSaveDialog(this);
        if (option == 0) {
            for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                if (Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
                Hua_ban.ty_shuzu.get((int)i).wt_w = Hua_ban.ty_shuzu.get((int)i).wei_tu.getWidth();
                Hua_ban.ty_shuzu.get((int)i).wt_g = Hua_ban.ty_shuzu.get((int)i).wei_tu.getHeight();
                Hua_ban.ty_shuzu.get((int)i).wty_w = Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.getWidth();
                Hua_ban.ty_shuzu.get((int)i).wty_g = Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.getHeight();
                Hua_ban.ty_shuzu.get((int)i).wei_tu_ = new int[Hua_ban.ty_shuzu.get((int)i).wei_tu.getWidth() * Hua_ban.ty_shuzu.get((int)i).wei_tu.getHeight()];
                Hua_ban.ty_shuzu.get((int)i).wei_tu.getRGB(0, 0, Hua_ban.ty_shuzu.get((int)i).wei_tu.getWidth(), Hua_ban.ty_shuzu.get((int)i).wei_tu.getHeight(), Hua_ban.ty_shuzu.get((int)i).wei_tu_, 0, Hua_ban.ty_shuzu.get((int)i).wei_tu.getWidth());
                Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan_ = new int[Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.getWidth() * Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.getHeight()];
                Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.getRGB(0, 0, Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.getWidth(), Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.getHeight(), Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan_, 0, Hua_ban.ty_shuzu.get((int)i).wei_tu_yuan.getWidth());
            }
            Tu_yuan.hui_fu();
            BufferedImage tu_diaoke2 = Tu_yuan.qu_tu(Hua_ban.ty_shuzu);
            Tu_yuan.hui_fu_xian_chang();
            String ss = chooser.getSelectedFile().getPath();
            ss = ss.toLowerCase();
            if (!ss.substring(ss.length() - 3).equals(".xj")) {
                ss = ss + ".xj";
            }
            if (tu_diaoke2 != null) {
                File outputfile = new File(ss + ".bmp");
                try {
                    BMPEncoder.write(tu_diaoke2, outputfile);
                }
                catch (IOException ex) {
                    Logger.getLogger(Tu_yuan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            File f = new File(ss);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));){
                oos.writeObject(Hua_ban.ty_shuzu);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void jButton13ActionPerformed(ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        fc.setAccessory(preview);
        fc.addPropertyChangeListener(preview);
        fc.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files (.bmp,.jpg,.png,.jpeg,.gif)", "bmp", "jpg", "png", "jpeg", "gif");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);
        if (fc.getSelectedFile() != null && returnVal == 0) {
            File[] files;
            for (File file : files = fc.getSelectedFiles()) {
                try {
                    BufferedImage image = ImageIO.read(file);
                    BufferedImage mBitmap = new BufferedImage(image.getWidth(), image.getHeight(), 2);
                    mBitmap.createGraphics().drawImage((Image)image, 0, 0, null);
                    File output = new File(file.getPath() + ".bmp");
                    BMPEncoder.write(mBitmap, output);
                }
                catch (IOException ex) {
                    Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void jSlider6StateChanged(ChangeEvent evt) {
        this.jLabel14.setText(bundle.getString("str_dui_bi") + this.jSlider6.getValue() + "%");
        if (Hua_ban.ty_shuzu.size() < 2) {
            return;
        }
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong || Hua_ban.ty_shuzu.get((int)i).lei_xing != 1) continue;
            Hua_ban.ty_shuzu.get((int)i).yu_zhi = this.jSlider6.getValue();
            Hua_ban.ty_shuzu.get(i).chu_li();
        }
        this.up();
    }

    private void jSlider7StateChanged(ChangeEvent evt) {
        this.jLabel16.setText(bundle.getString("str_tian_chong") + this.jSlider7.getValue());
        Tu_yuan.tian_chong_md = this.jSlider7.getValue();
        this.up();
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        this.qu_yu();
    }

    private void jSlider3MouseReleased(MouseEvent evt) {
        if (!kai_shi) {
            return;
        }
        this.she_zhi_qg();
    }

    private void jSlider4MouseReleased(MouseEvent evt) {
        if (!kai_shi) {
            return;
        }
        this.she_zhi_qg();
    }

    void qu_yu() {
        GeneralPath lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
        Rectangle rect = lu_jing2.getBounds();
        System.out.print(rect);
        if (rect.width > this.hua_ban1.getWidth() || rect.height > this.hua_ban1.getHeight()) {
            double b = rect.width - this.hua_ban1.getWidth() > rect.height - this.hua_ban1.getHeight() ? (double)this.hua_ban1.getWidth() / (double)rect.width : (double)this.hua_ban1.getHeight() / (double)rect.height;
            Hua_ban.quan_beishu *= b;
            AffineTransform sf = AffineTransform.getScaleInstance(b, b);
            for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                AffineTransform fb = new AffineTransform(sf);
                fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
                Hua_ban.ty_shuzu.get((int)i).Tx = fb;
            }
        }
        lu_jing2 = new GeneralPath(Hua_ban.ty_shuzu.get((int)0).lu_jing);
        lu_jing2.transform(Hua_ban.ty_shuzu.get((int)0).Tx);
        rect = lu_jing2.getBounds();
        int x1 = rect.x + rect.width / 2;
        int y1 = rect.y + rect.height / 2;
        int x2 = this.hua_ban1.getWidth() / 2;
        int y2 = this.hua_ban1.getHeight() / 2;
        AffineTransform sf2 = AffineTransform.getTranslateInstance(x2 - x1, y2 - y1);
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            AffineTransform fb = new AffineTransform(sf2);
            fb.concatenate(Hua_ban.ty_shuzu.get((int)i).Tx);
            Hua_ban.ty_shuzu.get((int)i).Tx = fb;
        }
        this.up();
    }

    boolean kai_shi_tuo_ji(int shan_qu, int ban_ben, int kuan_wt, int gao_wt, int weizhi_wt, int gonglv_wt, int shendu_wt, int kuan_sl, int gao_sl, int weizhi_sl, int gonglv_sl, int shendu_sl, int dianshu_sl, int z, int s, int ci_shu, int z_sl, int s_sl) {
        byte[] sz = new byte[]{35, 0, 38, (byte)(shan_qu >> 8), (byte)shan_qu, (byte)ban_ben, (byte)(kuan_wt >> 8), (byte)kuan_wt, (byte)(gao_wt >> 8), (byte)gao_wt, (byte)(weizhi_wt >> 8), (byte)weizhi_wt, (byte)(gonglv_wt >> 8), (byte)gonglv_wt, (byte)(shendu_wt >> 8), (byte)shendu_wt, (byte)(kuan_sl >> 8), (byte)kuan_sl, (byte)(gao_sl >> 8), (byte)gao_sl, (byte)(weizhi_sl >> 24), (byte)(weizhi_sl >> 16), (byte)(weizhi_sl >> 8), (byte)weizhi_sl, (byte)(gonglv_sl >> 8), (byte)gonglv_sl, (byte)(shendu_sl >> 8), (byte)shendu_sl, (byte)(dianshu_sl >> 24), (byte)(dianshu_sl >> 16), (byte)(dianshu_sl >> 8), (byte)dianshu_sl, (byte)(z >> 8), (byte)z, (byte)(s >> 8), (byte)s, (byte)ci_shu, 0};
        if (this.com_dakai) {
            return com2.fa_song_fe(sz, 2);
        }
        if (this.wang.lian_jie) {
            return this.wang.kaishi(sz, 22);
        }
        return false;
    }

    boolean xie_ru(byte[] m, int chao_shi) {
        if (!this.com_dakai) {
            return this.wang.xie_shuju(m, chao_shi * 100);
        }
        return com2.fa_song(m, chao_shi);
    }

    byte jiao_yan(byte[] bao) {
        int sum = 0;
        for (int i = 0; i < bao.length - 1; ++i) {
            int a = 0xFF & bao[i];
            sum += a;
        }
        if (sum > 255) {
            sum ^= 0xFFFFFFFF;
            ++sum;
        }
        return (byte)(sum &= 0xFF);
    }

    int jiao_yan2(byte[] m) {
        int jiao = 0;
        for (int i = 0; i < m.length; ++i) {
            jiao += m[i];
        }
        if (jiao > 255) {
            jiao ^= 0xFFFFFFFF;
            ++jiao;
        }
        return jiao &= 0xFF;
    }

    void tuo_ji2() {
        boolean chong_fa;
        int i = 0;
        int j = 0;
        int jishu = 0;
        int k = 0;
        int g = 0;
        int k_sl = 0;
        int g_sl = 0;
        int wz_sl = 33;
        int k_len = 0;
        boolean wt_ = false;
        boolean sl_ = false;
        boolean cuo = true;
        byte bl = 0;
        byte[] bao = null;
        int len = 0;
        Tu_yuan.hui_fu();
        BufferedImage tu_diaoke2 = Tu_yuan.qu_tu(Hua_ban.ty_shuzu);
        List<Dian> dian = Tu_yuan.qu_dian(Hua_ban.ty_shuzu);
        Tu_yuan.hui_fu_xian_chang();
        if (tu_diaoke2 == null && dian == null) {
            this.jButton15.setEnabled(true);
            return;
        }
        jishu = 0;
        if (tu_diaoke2 != null) {
            g = tu_diaoke2.getHeight();
            k = tu_diaoke2.getWidth();
            if (tu_diaoke2.getWidth() % 8 > 0) {
                bao = new byte[tu_diaoke2.getWidth() / 8 + 1];
                k_len = tu_diaoke2.getWidth() / 8 + 1;
            } else {
                bao = new byte[tu_diaoke2.getWidth() / 8];
                k_len = tu_diaoke2.getWidth() / 8;
            }
            wz_sl = 33 + g * bao.length;
            wt_ = true;
            len = bao.length;
        } else {
            wz_sl = 33;
        }
        if (dian != null) {
            k_sl = Tu_yuan.zui_zhong_wjx.width;
            g_sl = Tu_yuan.zui_zhong_wjx.height;
            sl_ = true;
        } else {
            dian = new ArrayList<Dian>();
        }
        int z = Tu_yuan.zui_zhong_wjx.x + Tu_yuan.zui_zhong_wjx.width / 2 + 67;
        int s = Tu_yuan.zui_zhong_wjx.y + Tu_yuan.zui_zhong_wjx.height / 2;
        int z_sl = Tu_yuan.shi_liang_wjx.x + Tu_yuan.shi_liang_wjx.width / 2 + 67;
        int s_sl = Tu_yuan.shi_liang_wjx.y + Tu_yuan.shi_liang_wjx.height / 2;
        bao = new byte[wz_sl - 33 + dian.size() * 4];
        kai_shi = true;
        this.kai_shi_tuo_ji((33 + len * g + dian.size() * 4) / 4094 + 1, 1, k, g, 33, this.jSlider1.getValue() * 10, this.jSlider2.getValue(), k_sl, g_sl, wz_sl, this.jSlider3.getValue() * 10, this.jSlider4.getValue(), dian.size(), z, s, this.jComboBox1.getSelectedIndex() + 1, z_sl, s_sl);
        try {
            Thread.sleep(40 * ((33 + len * g + dian.size() * 4) / 4094 + 1));
        }
        catch (InterruptedException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.xie_ru(new byte[]{10, 0, 4, 0}, 1);
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.xie_ru(new byte[]{10, 0, 4, 0}, 1);
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (wt_) {
            int[] pixels = new int[k * g];
            tu_diaoke2.getRGB(0, 0, k, g, pixels, 0, k);
            byte[] yi = new byte[]{-128, 64, 32, 16, 8, 4, 2, 1};
            for (i = 0; i < g; ++i) {
                for (j = 0; j < k_len; ++j) {
                    int ba = 0;
                    bl = 0;
                    for (ba = 0; ba < 8; ++ba) {
                        if (i * k + j * 8 + ba >= pixels.length) continue;
                        int clr = pixels[i * k + j * 8 + ba];
                        if ((clr = (clr & 0xFF0000) >> 16) >= 10) continue;
                        bl = (byte)(bl | yi[ba]);
                    }
                    bao[jishu] = bl;
                    ++jishu;
                }
            }
        }
        if (sl_) {
            for (j = 0; j < dian.size(); ++j) {
                bao[jishu++] = (byte)dian.get((int)j).x;
                bao[jishu++] = (byte)(dian.get((int)j).x >> 8);
                bao[jishu++] = (byte)dian.get((int)j).y;
                bao[jishu++] = (byte)(dian.get((int)j).y >> 8);
            }
        }
        int bao_chicuo = 1900;
        byte[] bao2 = new byte[bao_chicuo + 4];
        for (i = 0; i < bao.length / bao_chicuo; ++i) {
            for (j = 0; j < bao_chicuo; ++j) {
                bao2[j + 3] = bao[i * bao_chicuo + j];
            }
            chong_fa = false;
            do {
                bao2[0] = 34;
                bao2[1] = (byte)(bao2.length >> 8);
                bao2[2] = (byte)bao2.length;
                bao2[bao2.length - 1] = this.jiao_yan(bao2);
            } while (chong_fa = !this.xie_ru(bao2, 2));
            this.jdt.setVisible(true);
            this.jdt.setValue((int)((float)i / (float)(bao.length / bao_chicuo) * 100.0f));
        }
        bao2 = new byte[bao.length % bao_chicuo + 4];
        if (bao.length % bao_chicuo > 0) {
            for (i = 0; i < bao.length % bao_chicuo; ++i) {
                bao2[i + 3] = bao[bao.length / bao_chicuo * bao_chicuo + i];
            }
            chong_fa = false;
            do {
                bao2[0] = 34;
                bao2[1] = (byte)(bao2.length >> 8);
                bao2[2] = (byte)bao2.length;
                bao2[bao2.length - 1] = this.jiao_yan(bao2);
            } while (chong_fa = !this.xie_ru(bao2, 2));
        }
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.xie_ru(new byte[]{36, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0}, 1);
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.xie_ru(new byte[]{36, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0}, 1);
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        kai_shi2 = true;
        if (com2 != null) {
            mainJFrame.com2.jie_shou_ji_shu = 0;
            mainJFrame.com2.jie_shou_lei_xing = 3;
            mainJFrame.com2.jie_shou_huan_cun.clear();
        }
        this.jdt.setVisible(false);
        this.jButton15.setEnabled(true);
    }

    void lian_jie2() {
        if (!this.com_dakai) {
            int i;
            List<String> p = SerialPortUtil.getSerialPortList();
            String ss = "";
            for (i = 0; i < p.size(); ++i) {
                System.out.println(p.get(i));
                ss = ss + p.get(i) + "\r\n";
            }
            for (i = 0; i < p.size(); ++i) {
                if (-1 != p.get(i).indexOf("Bluetooth")) continue;
                try {
                    this.com = SerialPortUtil.openSerialPort(p.get(i), 115200);
                    com2 = new Com(this.com);
                    if (com2.fa_song(new byte[]{10, 0, 4, 0}, 2)) {
                        mainJFrame.com2.jdt = this.jdt;
                        this.com_dakai = true;
                        this.jButton18.setIcon(new ImageIcon(this.getClass().getResource("/tu/usb.png")));
                        if (com2.du_banben()) {
                            this.banben = new byte[]{mainJFrame.com2.fan_hui_shu[0], mainJFrame.com2.fan_hui_shu[1], mainJFrame.com2.fan_hui_shu[2]};
                            this.hua_ban1.ban_ben(this.banben, 2);
                            if (this.banben[2] == 37) {
                                if (Hua_ban.fen_bian_lv == 0.096) {
                                    this.jComboBox2.setSelectedIndex(2);
                                } else if (Hua_ban.fen_bian_lv == 0.08) {
                                    this.jComboBox2.setSelectedIndex(1);
                                } else if (Hua_ban.fen_bian_lv == 0.064) {
                                    this.jComboBox2.setSelectedIndex(0);
                                }
                            } else if (Hua_ban.fen_bian_lv == 0.075) {
                                this.jComboBox2.setSelectedIndex(2);
                            } else if (Hua_ban.fen_bian_lv == 0.0625) {
                                this.jComboBox2.setSelectedIndex(1);
                            } else if (Hua_ban.fen_bian_lv == 0.05) {
                                this.jComboBox2.setSelectedIndex(0);
                            }
                            this.qu_yu();
                        }
                        break;
                    }
                    com2.guan_bi();
                    continue;
                }
                catch (NoSuchPortException ex) {
                    Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    continue;
                }
                catch (PortInUseException ex) {
                    Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    continue;
                }
                catch (UnsupportedCommOperationException ex) {
                    Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            this.com_dakai = false;
            com2.guan_bi();
            com2 = null;
            this.jButton18.setIcon(new ImageIcon(this.getClass().getResource("/tu/usb2.png")));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    boolean du_banben() {
        final Object suo_ = new Object();
        Object object = this.suo_fhm;
        synchronized (object) {
            this.fan_hui_ma = false;
            SerialPortUtil.sendData(this.com, new byte[]{-1, 0, 4, 0});
        }
        Runnable runnable2 = new Runnable(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                Object object = suo_;
                synchronized (object) {
                    for (int i = 200; i > 0; --i) {
                        Object object2 = mainJFrame.this.suo_fhm;
                        synchronized (object2) {
                            if (mainJFrame.this.fan_hui_ma) {
                                if (mainJFrame.com2.fan_hui_shu.length == 3) {
                                    break;
                                }
                                mainJFrame.this.fan_hui_ma = false;
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
        Object object2 = suo_;
        synchronized (object2) {
            return this.fan_hui_ma;
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
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(mainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new mainJFrame().setVisible(true);
            }
        });
    }

    void set() {
        int i;
        int i2;
        double x;
        if (this.jTextField1.getText() != "") {
            x = Integer.valueOf(this.jTextField1.getText()).intValue();
            x -= (double)this.hua_ban1.x;
            x /= Hua_ban.fen_bian_lv;
            x *= Hua_ban.quan_beishu;
            for (i2 = 0; i2 < Hua_ban.ty_shuzu.size(); ++i2) {
                if (!Hua_ban.ty_shuzu.get((int)i2).xuan_zhong) continue;
                Hua_ban.ty_shuzu.get(i2).ping_yi((int)x, Hua_ban.ty_shuzu.get((int)i2).lu_jing.getBounds().y);
            }
            this.up();
        }
        if (this.jTextField2.getText() != "") {
            x = Integer.valueOf(this.jTextField2.getText()).intValue();
            x -= (double)this.hua_ban1.y;
            x /= Hua_ban.fen_bian_lv;
            x *= Hua_ban.quan_beishu;
            for (i2 = 0; i2 < Hua_ban.ty_shuzu.size(); ++i2) {
                if (!Hua_ban.ty_shuzu.get((int)i2).xuan_zhong) continue;
                Hua_ban.ty_shuzu.get(i2).ping_yi(Hua_ban.ty_shuzu.get((int)i2).lu_jing.getBounds().x, (int)x);
            }
            this.up();
        }
        if (this.jTextField3.getText() != "") {
            x = Integer.valueOf(this.jTextField3.getText()).intValue();
            x /= (double)this.hua_ban1.ww;
            Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
            for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                Hua_ban.ty_shuzu.get(i).ping_yi(-rect.x, -rect.y);
                if (Hua_ban.suo) {
                    Hua_ban.ty_shuzu.get(i).suo_fang(x, x);
                } else {
                    Hua_ban.ty_shuzu.get(i).suo_fang(x, 1.0);
                }
                Hua_ban.ty_shuzu.get(i).ping_yi(rect.x, rect.y);
            }
            this.up();
        }
        if (this.jTextField4.getText() != "") {
            x = Integer.valueOf(this.jTextField4.getText()).intValue();
            x /= (double)this.hua_ban1.hh;
            Rectangle rect = Tu_yuan.qu_jv_xing(Hua_ban.ty_shuzu);
            for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                Hua_ban.ty_shuzu.get(i).ping_yi(-rect.x, -rect.y);
                if (Hua_ban.suo) {
                    Hua_ban.ty_shuzu.get(i).suo_fang(x, x);
                } else {
                    Hua_ban.ty_shuzu.get(i).suo_fang(1.0, x);
                }
                Hua_ban.ty_shuzu.get(i).ping_yi(rect.x, rect.y);
            }
            this.up();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i;
        int c = e.getKeyCode();
        if (e.isControlDown() && e.getKeyCode() == 67) {
            this.ty_fu_zhi.clear();
            for (i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
                if (!Hua_ban.ty_shuzu.get((int)i).xuan_zhong) continue;
                this.ty_fu_zhi.add(Tu_yuan.fu_zhi(Hua_ban.ty_shuzu.get(i)));
            }
            this.fu_zhi = true;
        } else if (e.isControlDown() && e.getKeyCode() == 86) {
            if (this.fu_zhi) {
                for (i = 0; i < this.ty_fu_zhi.size(); ++i) {
                    Hua_ban.ty_shuzu.add(Tu_yuan.fu_zhi(this.ty_fu_zhi.get(i)));
                }
                this.up();
                Che_xiao.tian_jia();
            }
        } else if (e.isControlDown() && e.getKeyCode() == 65) {
            for (i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
                Hua_ban.ty_shuzu.get((int)i).xuan_zhong = true;
            }
            this.up();
        } else if (e.isControlDown() && e.getKeyCode() == 90) {
            Che_xiao.che_xiao();
            this.up();
        } else if (e.isControlDown() && e.getKeyCode() == 88) {
            for (i = 1; i < Hua_ban.ty_shuzu.size(); ++i) {
                Hua_ban.ty_shuzu.get((int)i).xuan_zhong = true;
            }
            Che_xiao.chong_zuo();
            this.up();
        }
        if (c == 10) {
            this.set();
        } else if (c == 127) {
            ArrayList<Tu_yuan> sz = new ArrayList<Tu_yuan>();
            for (int i2 = 0; i2 < Hua_ban.ty_shuzu.size(); ++i2) {
                if (Hua_ban.ty_shuzu.get((int)i2).xuan_zhong) continue;
                sz.add(Hua_ban.ty_shuzu.get(i2));
            }
            Hua_ban.ty_shuzu = sz;
            this.up();
            Che_xiao.tian_jia();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

