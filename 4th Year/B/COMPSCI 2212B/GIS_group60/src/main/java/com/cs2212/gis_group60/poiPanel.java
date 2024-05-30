/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cs2212.gis_group60;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hanley
 */
public class poiPanel extends JPanel {
    private Room[] rooms = new Room[400];
    private ImageIcon img = new ImageIcon("src\\main\\java\\image\\poi.png");
    private String path_storageString;
    
    
    public poiPanel(String path){
        this.setPreferredSize(new Dimension(1800, 1164));
        this.setLayout(null);
        try{
                BufferedReader buffer = new BufferedReader(new FileReader(path));
                path_storageString = path;

                String s = "";
                int i = 0;
                while((s = buffer.readLine()) != null){
                    String value[] = s.split(";");
                    rooms[i] = new Room(value[0], value[1], Integer.parseInt(value[2]), Integer.parseInt(value[3]), value[4]);
                    i++;
                }
                buffer.close();
            }catch(IOException e){
            }
        
        int i = 0;
        while(rooms[i] != null){
            this.add(rooms[i]);
            rooms[i].setBounds(rooms[i].getRoomXaxis(), rooms[i].getRoomYaxis(), 50, 50);
            i++;
        }
        
        this.addMouseListener(new java.awt.event.MouseAdapter() {
                /*public void mouseEntered(java.awt.event.MouseEvent evt) {
                    MouseEntered(evt);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    MouseExited(evt);
                }*/
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    MouseClicked(evt);
                }
            });
        
    }
    
    
    private void MouseClicked(MouseEvent e) {
        // author haoran wang
            System.out.println("com.cs2212.gis_group60.poiPanel.MouseClicked()");
            int x = e.getX();
            int y = e.getY();
            System.out.println("Mouse clicked at (" + x + ", " + y + ")");
            
            
            // 创建一个新的 JLabel，将图标添加到其中，并将其位置设置为鼠标点击的位置
            JLabel label = new JLabel(img);
            label.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
            this.add(label);
            this.repaint();
            
            String new_name = "new_name";
            String new_type = "new_type";
            String new_detail = "new_detail";
            
            
            Room create_rooms = new Room(new_name, new_type, x, y, new_detail);
            
            // 目前想写入 txt文档遭遇问题

             try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path_storageString, true)));
                out.printf("%d;%s;%d;%d;%s\n", create_rooms.getRoomName(), create_rooms.getRoomType(), 
                        create_rooms.getX(), create_rooms.getY(), create_rooms.getRoomDetail());
                out.close();
                System.out.printf("Data is saved in %s\n", path_storageString);
            } catch (IOException ex) {
                ex.printStackTrace();
    }
            
        }
    
    
    
    
}
