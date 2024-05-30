/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cs2212.gis_group60;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JViewport;

/**
 *
 * @author Hanley
 */
public class XY extends JFrame{
    public XY(){
        initComponents();
    }
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XY().setVisible(true);
            }
        });
    }
    
    private void initComponents(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("src\\main\\java\\image\\Middlesex College Floor G.png");
        XY.ImagePane p1 = new XY.ImagePane(img.getImage());
        p1.setPreferredSize(new Dimension(img.getIconWidth() / 2, img.getIconHeight() / 2));
        
        p1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + ", " + y);
            }
        });
        
        this.setPreferredSize(new Dimension(900, 582));
        this.add(p1);
        pack();
    }
    
    class ImagePane extends JPanel {

        Image im;
        //构造函数制定Jpanel的大小

        public ImagePane(Image im) {
            this.im = im;
            //希望该Panel的大小事自适应的
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            this.setSize(width, height);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            // 清屏
            super.paintComponent(g);
            g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    
    
}
