/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Che_xiao;
import examples.Hua_ban;
import examples.Tu_yuan;
import examples.mainJFrame;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Zi_ti2
extends JDialog {
    public static Font ziti = null;
    static int box1 = 0;
    static int box2 = 0;
    static int daxiao = 10;
    Hua_ban fu;
    private JSlider da_xiao;
    private JButton jButton1;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JTextArea wen_zi;
    private JComboBox<String> zi_ti_Box;
    private JComboBox<String> zi_xing_Box;

    public Zi_ti2(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
    }

    public Zi_ti2(Hua_ban parent, boolean modal) {
        this.setTitle("\u8f93\u5165\u6587\u5b57");
        this.setLocation(new Point(200, 100));
        this.fu = parent;
        this.initComponents();
    }

    private void initComponents() {
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.zi_ti_Box = new JComboBox();
        this.zi_xing_Box = new JComboBox();
        this.da_xiao = new JSlider();
        this.jScrollPane1 = new JScrollPane();
        this.wen_zi = new JTextArea();
        this.jButton1 = new JButton();
        this.jLabel1 = new JLabel();
        this.jCheckBox1 = new JCheckBox();
        this.jCheckBox2 = new JCheckBox();
        this.setDefaultCloseOperation(2);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowOpened(WindowEvent evt) {
                Zi_ti2.this.formWindowOpened(evt);
            }
        });
        this.jLabel2.setText("\u5b57\u578b\uff1a");
        this.jLabel3.setText("\u5c3a\u5bf8:10");
        this.zi_ti_Box.setModel(new DefaultComboBoxModel<String>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        this.zi_ti_Box.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent evt) {
                Zi_ti2.this.zi_ti_BoxItemStateChanged(evt);
            }
        });
        this.zi_xing_Box.setModel(new DefaultComboBoxModel<String>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        this.zi_xing_Box.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent evt) {
                Zi_ti2.this.zi_xing_BoxItemStateChanged(evt);
            }
        });
        this.da_xiao.setMaximum(200);
        this.da_xiao.setValue(10);
        this.da_xiao.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                Zi_ti2.this.da_xiaoStateChanged(evt);
            }
        });
        this.wen_zi.setColumns(20);
        this.wen_zi.setRows(5);
        this.wen_zi.setText("ABCD");
        this.jScrollPane1.setViewportView(this.wen_zi);
        this.jButton1.setText("OK");
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Zi_ti2.this.jButton1ActionPerformed(evt);
            }
        });
        this.jLabel1.setText("\u5b57\u4f53\uff1a");
        this.jCheckBox1.setText("\u7ad6\u5411");
        this.jCheckBox2.setText("\u77e2\u91cf\u56fe5");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addComponent(this.jLabel1)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.zi_ti_Box, -2, 165, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.zi_xing_Box, -2, 149, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.da_xiao, -2, -1, -2).addComponent(this.jLabel3)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jCheckBox2).addComponent(this.jCheckBox1)).addGap(0, 198, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton1, -2, 92, -2))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.zi_ti_Box, -2, -1, -2).addComponent(this.da_xiao, -2, 0, 32767))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.jCheckBox2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.zi_xing_Box, -2, -1, -2))).addComponent(this.jCheckBox1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 517, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 34, -2).addContainerGap()));
        this.pack();
    }

    private void zi_ti_BoxItemStateChanged(ItemEvent evt) {
        String fontName = (String)evt.getItem();
        System.out.println(fontName);
        ziti = new Font(fontName, ziti.getStyle(), ziti.getSize());
        this.wen_zi.setFont(ziti);
        box1 = this.zi_ti_Box.getSelectedIndex();
    }

    public static int getFontStyleByCnName(String fontStyle) {
        if (fontStyle.equals(mainJFrame.str_chang_gui)) {
            return 0;
        }
        if (fontStyle.equals(mainJFrame.str_xie_ti)) {
            return 2;
        }
        if (fontStyle.equals(mainJFrame.str_cu_ti)) {
            return 1;
        }
        if (fontStyle.equals(mainJFrame.str_cu_xie)) {
            return 3;
        }
        return -1;
    }

    private void zi_xing_BoxItemStateChanged(ItemEvent evt) {
        String fontStyle = (String)evt.getItem();
        System.out.println(fontStyle);
        ziti = new Font(ziti.getName(), Zi_ti2.getFontStyleByCnName(fontStyle), ziti.getSize());
        this.wen_zi.setFont(ziti);
        box2 = this.zi_xing_Box.getSelectedIndex();
    }

    private void da_xiaoStateChanged(ChangeEvent evt) {
        this.jLabel3.setText(mainJFrame.bundle.getString("str_chi_cun") + this.da_xiao.getValue());
        daxiao = 100 + this.da_xiao.getValue() * 2;
        ziti = new Font(ziti.getName(), ziti.getStyle(), daxiao);
        this.wen_zi.setFont(ziti);
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        if (this.jCheckBox1.isSelected()) {
            Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian_wen_zi_shu(this.wen_zi.getText(), ziti, 0, this.jCheckBox2.isSelected()));
        } else {
            Hua_ban.ty_shuzu.add(Tu_yuan.chuang_jian_wen_zi(this.wen_zi.getText(), ziti, this.jCheckBox2.isSelected()));
        }
        for (int i = 0; i < Hua_ban.ty_shuzu.size(); ++i) {
            Hua_ban.ty_shuzu.get((int)i).xuan_zhong = false;
        }
        Hua_ban.ty_shuzu.get((int)(Hua_ban.ty_shuzu.size() - 1)).xuan_zhong = true;
        Tu_yuan.zhong_xin(Hua_ban.ty_shuzu);
        Che_xiao.tian_jia();
        this.fu.repaint();
        this.setVisible(false);
    }

    private void formWindowOpened(WindowEvent evt) {
        this.jLabel1.setText(mainJFrame.str_zi_ti);
        this.jLabel2.setText(mainJFrame.str_zi_xing);
        this.jLabel3.setText(mainJFrame.str_chi_cun + "10");
        this.jCheckBox1.setText(mainJFrame.str_shu);
        this.jCheckBox2.setText(mainJFrame.str_shi_liang);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/tu/tu_biao.png")).getImage());
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        String[] fontNames2 = new String[fontNames.length];
        for (int i = 0; i < fontNames.length; ++i) {
            fontNames2[i] = fontNames[fontNames.length - i - 1];
        }
        this.zi_ti_Box.setModel(new DefaultComboBoxModel<String>(fontNames2));
        String[] fontStyles = new String[]{mainJFrame.str_chang_gui, mainJFrame.str_xie_ti, mainJFrame.str_cu_ti, mainJFrame.str_cu_xie};
        this.zi_xing_Box.setModel(new DefaultComboBoxModel<String>(fontStyles));
        if (ziti == null) {
            daxiao = 120;
            ziti = new Font(this.zi_ti_Box.getItemAt(this.zi_ti_Box.getSelectedIndex()), 0, daxiao);
            this.wen_zi.setFont(ziti);
        } else {
            this.zi_ti_Box.setSelectedIndex(box1);
            this.zi_xing_Box.setSelectedIndex(box2);
            this.da_xiao.setValue((daxiao - 80) / 2);
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
            Logger.getLogger(Zi_ti2.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(Zi_ti2.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(Zi_ti2.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Zi_ti2.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                Zi_ti2 dialog = new Zi_ti2((Frame)new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter(){

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}

