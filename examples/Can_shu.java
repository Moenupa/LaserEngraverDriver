/*
 * Decompiled with CFR 0.150.
 */
package examples;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Can_shu
extends JDialog {
    private Canvas canvas1;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;

    public Can_shu(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
    }

    public Can_shu() {
        this.setUndecorated(true);
        this.initComponents();
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jTextField1 = new JTextField();
        this.jLabel2 = new JLabel();
        this.jTextField2 = new JTextField();
        this.jLabel3 = new JLabel();
        this.jTextField3 = new JTextField();
        this.jLabel4 = new JLabel();
        this.jTextField4 = new JTextField();
        this.jButton1 = new JButton();
        this.canvas1 = new Canvas();
        this.setDefaultCloseOperation(2);
        this.setAlwaysOnTop(true);
        this.setBackground(new Color(255, 255, 255));
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowOpened(WindowEvent evt) {
                Can_shu.this.formWindowOpened(evt);
            }
        });
        this.jLabel1.setText("X:");
        this.jTextField1.setText("0");
        this.jLabel2.setText("Y:");
        this.jTextField2.setText("0");
        this.jLabel3.setText("H:");
        this.jTextField3.setText("100");
        this.jLabel4.setText("W:");
        this.jTextField4.setText("100");
        this.jButton1.setText("jButton1");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jTextField1, -2, 42, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField2, -2, 42, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField3, -2, 42, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1, -2, 23, -2).addGap(29, 29, 29).addComponent(this.jLabel2).addGap(32, 32, 32).addComponent(this.jLabel4))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.jTextField4, -2, 42, -2))).addGroup(layout.createSequentialGroup().addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.canvas1, -2, 419, -2))).addContainerGap(119, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.jLabel3)).addComponent(this.jLabel2).addComponent(this.jLabel1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField1, -2, -1, -2).addComponent(this.jTextField2, -2, -1, -2).addComponent(this.jTextField3, -2, -1, -2).addComponent(this.jTextField4, -2, -1, -2)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton1, -1, 292, 32767).addContainerGap()).addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.canvas1, -2, 236, -2).addContainerGap(-1, 32767)))));
        this.pack();
    }

    private void formWindowOpened(WindowEvent evt) {
        this.setBackground(Color.WHITE);
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
            Logger.getLogger(Can_shu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(Can_shu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(Can_shu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Can_shu.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                Can_shu dialog = new Can_shu((Frame)new JFrame(), true);
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

