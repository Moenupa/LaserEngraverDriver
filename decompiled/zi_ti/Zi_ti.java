/*
 * Decompiled with CFR 0.150.
 */
package zi_ti;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Zi_ti
extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JComboBox fontNameBox = null;
    private JComboBox fontStyleBox = null;
    private JComboBox fontSizeBox = null;
    private JTextArea txtrHereIs = null;
    private String fontName;
    private String fontStyle;
    private String fontSize;
    private int fontSty;
    private int fontSiz;

    public static void main(String[] args) {
        try {
            Zi_ti dialog = new Zi_ti();
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Zi_ti() {
        this.setTitle("\u4f60\u597d\u6211\u6765\u4e86");
        this.setBounds(100, 100, 483, 234);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add((Component)this.contentPanel, "Center");
        this.contentPanel.setLayout(null);
        JLabel lblf = new JLabel("\u5b57\u4f53(F):");
        lblf.setBounds(0, 10, 54, 15);
        this.contentPanel.add(lblf);
        JLabel lbly = new JLabel("\u5b57\u5f62(Y):");
        lbly.setBounds(182, 10, 54, 15);
        this.contentPanel.add(lbly);
        JLabel lbls = new JLabel("\u5927\u5c0f(S):");
        lbls.setBounds(315, 10, 54, 15);
        this.contentPanel.add(lbls);
        JLabel label = new JLabel("\u663e\u793a\u6548\u679c:");
        label.setBounds(126, 82, 64, 15);
        this.contentPanel.add(label);
        Panel panel = new Panel();
        panel.setBounds(196, 40, 228, 113);
        this.contentPanel.add(panel);
        panel.setLayout(null);
        this.txtrHereIs = new JTextArea();
        this.txtrHereIs.setBounds(39, 38, 177, 44);
        this.txtrHereIs.setText("\u8fd9\u91cc\u663e\u793a\u9884\u89c8\r\nHere is the preview");
        panel.add(this.txtrHereIs);
        this.fontNameBox = new JComboBox();
        this.fontNameBox.setBounds(49, 7, 123, 21);
        this.contentPanel.add(this.fontNameBox);
        this.fontNameBox.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent itemevent) {
                Zi_ti.this.fontName = (String)itemevent.getItem();
                System.out.println(Zi_ti.this.fontName);
                Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
                Zi_ti.this.txtrHereIs.setFont(f);
            }
        });
        this.fontStyleBox = new JComboBox();
        this.fontStyleBox.setBounds(232, 7, 73, 21);
        this.contentPanel.add(this.fontStyleBox);
        this.fontStyleBox.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent itemevent) {
                Zi_ti.this.fontStyle = (String)itemevent.getItem();
                Zi_ti.this.fontSty = Zi_ti.getFontStyleByCnName(Zi_ti.this.fontStyle);
                Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
                Zi_ti.this.txtrHereIs.setFont(f);
            }
        });
        this.fontSizeBox = new JComboBox();
        this.fontSizeBox.setBounds(379, 7, 78, 21);
        this.contentPanel.add(this.fontSizeBox);
        this.fontSizeBox.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent itemevent) {
                Zi_ti.this.fontSize = (String)itemevent.getItem();
                Zi_ti.this.fontSiz = Integer.parseInt(Zi_ti.this.fontSize);
                Font f = new Font(Zi_ti.this.fontName, Zi_ti.this.fontSty, Zi_ti.this.fontSiz);
                Zi_ti.this.txtrHereIs.setFont(f);
            }
        });
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(2));
        this.getContentPane().add((Component)buttonPane, "South");
        JButton okButton = new JButton("\u786e\u5b9a");
        okButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionevent) {
                int fontSty = Zi_ti.getFontStyleByCnName(Zi_ti.this.fontStyle);
                int fontSiz = Integer.parseInt(Zi_ti.this.fontSize);
                JOptionPane.showMessageDialog(Zi_ti.this, "\u4f60\u9009\u62e9\u7684\u5b57\u4f53\u540d\u79f0\uff1a" + Zi_ti.this.fontName + ",\u5b57\u4f53\u6837\u5f0f\uff1a" + Zi_ti.this.fontStyle + ",\u5b57\u4f53\u5927\u5c0f\uff1a" + fontSiz, "\u63d0\u793a", -1);
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        this.getRootPane().setDefaultButton(okButton);
        JButton cancelButton = new JButton("\u53d6\u6d88");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
        cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionevent) {
                Zi_ti.this.dispose();
            }
        });
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        this.fontNameBox.setModel(new DefaultComboBoxModel<String>(fontNames));
        String[] fontStyles = new String[]{"\u5e38\u89c4", "\u659c\u4f53", "\u7c97\u4f53", "\u7c97\u659c\u4f53"};
        this.fontStyleBox.setModel(new DefaultComboBoxModel<String>(fontStyles));
        String[] fontSizes = new String[]{"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};
        this.fontSizeBox.setModel(new DefaultComboBoxModel<String>(fontSizes));
        System.out.println("finish.");
        this.fontSizeBox.setSelectedIndex(4);
        this.fontStyle = (String)this.fontStyleBox.getSelectedItem();
        this.fontSize = (String)this.fontSizeBox.getSelectedItem();
        this.fontSty = Zi_ti.getFontStyleByCnName(this.fontStyle);
        this.fontSiz = Integer.parseInt(this.fontSize);
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
}

