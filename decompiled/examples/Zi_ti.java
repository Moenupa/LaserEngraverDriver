/*
 * Decompiled with CFR 0.150.
 */
package examples;

import examples.Tu_yuan;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class Zi_ti
extends JFrame {
    public static Font ziti = null;
    static int box1 = 0;
    static int box2 = 0;
    static int daxiao = 14;
    private JSlider da_xiao;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JTextArea wen_zi;
    private JComboBox<String> zi_ti_Box;
    private JComboBox<String> zi_xing_Box;

    public Zi_ti() {
        this.initComponents();
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.zi_ti_Box = new JComboBox();
        this.zi_xing_Box = new JComboBox();
        this.da_xiao = new JSlider();
        this.jScrollPane1 = new JScrollPane();
        this.wen_zi = new JTextArea();
        this.jButton1 = new JButton();
        this.setDefaultCloseOperation(3);
        this.setTitle("\u5b57\u4f53\u8bbe\u7f6e");
        this.setLocation(new Point(300, 300));
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowOpened(WindowEvent evt) {
                Zi_ti.this.formWindowOpened(evt);
            }
        });
        this.jLabel1.setText("\u5b57\u4f53\uff1a");
        this.jLabel2.setText("\u5b57\u578b\uff1a");
        this.jLabel3.setText("\u5927\u5c0f\uff1a");
        this.zi_ti_Box.setModel(new DefaultComboBoxModel<String>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        this.zi_ti_Box.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent evt) {
                Zi_ti.this.zi_ti_BoxItemStateChanged(evt);
            }
        });
        this.zi_xing_Box.setModel(new DefaultComboBoxModel<String>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        this.zi_xing_Box.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent evt) {
                Zi_ti.this.zi_xing_BoxItemStateChanged(evt);
            }
        });
        this.da_xiao.setMaximum(200);
        this.da_xiao.setValue(14);
        this.da_xiao.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent evt) {
                Zi_ti.this.da_xiaoStateChanged(evt);
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
                Zi_ti.this.jButton1ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addComponent(this.jLabel1)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.zi_ti_Box, -2, 165, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.zi_xing_Box, -2, 149, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.da_xiao, -2, -1, -2).addComponent(this.jLabel3)).addGap(0, 142, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton1, -2, 92, -2))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.zi_ti_Box, -2, -1, -2).addComponent(this.da_xiao, -2, 0, 32767))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jLabel3)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.zi_xing_Box, -2, -1, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 387, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 34, -2).addContainerGap()));
        this.pack();
    }

    private void formWindowOpened(WindowEvent evt) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        this.zi_ti_Box.setModel(new DefaultComboBoxModel<String>(fontNames));
        String[] fontStyles = new String[]{"\u5e38\u89c4", "\u659c\u4f53", "\u7c97\u4f53", "\u7c97\u659c\u4f53"};
        this.zi_xing_Box.setModel(new DefaultComboBoxModel<String>(fontStyles));
        if (ziti == null) {
            ziti = new Font(this.zi_ti_Box.getItemAt(this.zi_ti_Box.getSelectedIndex()), 0, 14);
            this.wen_zi.setFont(ziti);
        } else {
            this.zi_ti_Box.setSelectedIndex(box1);
            this.zi_xing_Box.setSelectedIndex(box2);
            this.da_xiao.setValue(daxiao);
        }
    }

    public static int getFontStyleByCnName(String fontStyle) {
        if (fontStyle.equals("\u5e38\u89c4")) {
            return 0;
        }
        if (fontStyle.equals("\u659c\u4f53")) {
            return 2;
        }
        if (fontStyle.equals("\u7c97\u4f53")) {
            return 1;
        }
        if (fontStyle.equals("\u7c97\u659c\u4f53")) {
            return 3;
        }
        return -1;
    }

    private void zi_ti_BoxItemStateChanged(ItemEvent evt) {
        String fontName = (String)evt.getItem();
        System.out.println(fontName);
        ziti = new Font(fontName, ziti.getStyle(), ziti.getSize());
        this.wen_zi.setFont(ziti);
        box1 = this.zi_ti_Box.getSelectedIndex();
    }

    private void zi_xing_BoxItemStateChanged(ItemEvent evt) {
        String fontStyle = (String)evt.getItem();
        System.out.println(fontStyle);
        ziti = new Font(ziti.getName(), Zi_ti.getFontStyleByCnName(fontStyle), ziti.getSize());
        this.wen_zi.setFont(ziti);
        box2 = this.zi_xing_Box.getSelectedIndex();
    }

    private void da_xiaoStateChanged(ChangeEvent evt) {
        ziti = new Font(ziti.getName(), ziti.getStyle(), this.da_xiao.getValue());
        this.wen_zi.setFont(ziti);
        daxiao = this.da_xiao.getValue();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        Tu_yuan.chuang_jian_wen_zi(this.wen_zi.getText(), ziti, false);
        this.setVisible(false);
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
            Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Zi_ti.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new Zi_ti().setVisible(true);
            }
        });
    }
}

