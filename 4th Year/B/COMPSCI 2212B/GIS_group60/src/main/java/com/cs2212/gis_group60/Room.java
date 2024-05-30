package com.cs2212.gis_group60;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Room extends JLabel{

        private String name;
        private String type;
        private int highlight;
        private int favourite;
        private String detail;
        private int xAxis;
        private int yAxis;
        private ImageIcon img = new ImageIcon("src\\main\\java\\image\\poi.png");
        private ImageIcon imgH = new ImageIcon("src\\main\\java\\image\\poiHighlight.png");
        
        public Room(String roomName,String typeName, int x, int y, String details) {
            this.setIcon(img);
            name = roomName;
            detail = details;
            xAxis = x;
            yAxis = y;
            type = typeName;
            favourite = 0;
            highlight = 0;
            
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
        
        /*private void MouseEntered(MouseEvent e){
            this.setIcon(imgH);
        }
        
        private void MouseExited(MouseEvent e){
            this.setIcon(img);
        }*/
        
        private void MouseClicked(MouseEvent e) {
            if(this.getIcon() == imgH){
                this.setIcon(img);
                highlight = 0;
            }else{
                this.setIcon(imgH);
                highlight = 1;
                popupInfo();
            } 
        }
        
        public JFrame popupInfo(){
            JFrame frame = new JFrame("新窗口");
                // 创建一个标签，并将其添加到窗口中
                
                String temp_name = this.name;
                String temp_detail = this.detail;
                String temp_type = this.type;
                JLabel label = new JLabel(
                        String.format("room name: %s,  room detail: %s, room type: %s  .  ", temp_name, temp_detail, temp_type));
                frame.getContentPane().add(label);
                // 设置窗口大小并使其可见
                frame.setSize(600, 400);
                frame.setVisible(true);
                //让窗口  屏幕居中显示
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
                
                return frame;
        }
        
        public void highlight(){
            this.setIcon(imgH);
            highlight = 1;
            popupInfo();
        }
        
        public void unHighlight(){
            this.setIcon(img);
            highlight = 0;
        }
        
        public boolean isHighlight(){
            if(highlight == 1) return true;
            return false;
        }
        //Lots of sets
        public void setRoomName(String roomName) {
            name = roomName;
        }
        public void setRoomDetail(String details) {
            detail = details;
        }
        public void setRoomPosition(int x, int y) {
            xAxis = x;
            yAxis = y;
        }
        public void setRoomType(String typeName){
            type = typeName;
        }
        public void setRoomFavourite(){
            favourite = 1;
        }
        //Lots of gets
        public String getRoomName() {
            return name;
        }
        
        public String getRoomDetail() {
            return detail;
        }
        
        public int getRoomXaxis() {
            return xAxis;
        }
        
        public int getRoomYaxis() {
            return yAxis;
        }
        
        public String getRoomType(){
            return type;
        }
        
        public int getRoomFavourite(){
            return favourite;
        }
 
}
