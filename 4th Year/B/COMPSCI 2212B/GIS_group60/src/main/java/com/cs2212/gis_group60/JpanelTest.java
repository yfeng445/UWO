/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs2212.gis_group60;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author dongxm
 */
public class JpanelTest extends javax.swing.JFrame {

    public JpanelTest() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("src\\main\\java\\image\\Middlesex College Floor G.png");
        ImagePanel p1 = new ImagePanel(img.getImage());
        p1.setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
        
        JPanel UpperJp = new JPanel();
        UpperJp.setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
        UpperJp.setLayout(null);
        UpperJp.setOpaque(false);
        JButton jb = new JButton("aaaaa");
        UpperJp.add(jb);
        jb.setBounds(392, 355, 70, 70);
        
        p1.add(UpperJp);
        
        JScrollPane scroll = new JScrollPane(p1);
        this.setPreferredSize(new Dimension(1200, 600));
        this.add(scroll);
        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JpanelTest().setVisible(true);
            }
        });
    }

    class ImagePanel extends JPanel {

        Image im;
        //构造函数制定Jpanel的大小

        public ImagePanel(Image im) {
            this.im = im;
            //希望该Panel的大小事自适应的   Toolkit.getDefaultToolkit().getScreenSize()
            
            int width = im.getWidth(rootPane);
            int height = im.getHeight(rootPane);
            this.setSize(width, height);
        }

        //画出背景
        @Override
        protected void paintComponent(Graphics g) {
            // 清屏
            super.paintComponent(g);
            g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    
    
//    class UpperPanel extends JPanel {
//        
//        JButton jb = new JButton("aaa");
//    
//       
//    }
    
}
