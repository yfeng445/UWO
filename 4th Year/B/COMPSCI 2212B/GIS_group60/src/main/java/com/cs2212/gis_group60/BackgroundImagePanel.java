/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cs2212.gis_group60;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Hanley
 */
public class BackgroundImagePanel extends JPanel {
    private ImageIcon icon;
    private Image img;
    
    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }
    
    public BackgroundImagePanel(){
        icon = new ImageIcon(this.getClass().getResource("src\\main\\java\\image\\Middlesex College Floor G.png"));
        img = icon.getImage();
    }
    
    public void paintComponet(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    public static void main(String[] args){
        new BackgroundImagePanel();
    }
}
