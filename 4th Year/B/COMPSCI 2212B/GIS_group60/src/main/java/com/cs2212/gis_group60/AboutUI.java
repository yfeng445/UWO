package com.cs2212.gis_group60;
/**
 *
 * @author hwan372
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AboutUI extends JFrame {
    private JLabel appVersionLabel;
    private JLabel dateLabel;
    private JLabel appNameLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JButton closeBtn;

    public AboutUI(String appVersion, String date, String appName, String name, String email) {
        super("About page   " + appName);

        // 创建 UI 组件
        appVersionLabel = new JLabel("Version: " + appVersion);
        dateLabel = new JLabel("Release Date: " + date);
        appNameLabel = new JLabel(appName);
        nameLabel = new JLabel("Author Name: " + name);
        emailLabel = new JLabel("Author Email: " + email);
        closeBtn = new JButton("close");

        // 设置 UI 组件的布局和位置
        setLayout(null);
        appNameLabel.setBounds(120, 10, 100, 20);
        appVersionLabel.setBounds(20, 50, 200, 20);
        dateLabel.setBounds(20, 80, 200, 20);
        nameLabel.setBounds(20, 110, 600, 20);
        emailLabel.setBounds(20, 140, 600, 20);
        closeBtn.setBounds(600, 420, 80, 30);

        // 将 UI 组件添加到 JFrame 中
        add(appNameLabel);
        add(appVersionLabel);
        add(dateLabel);
        add(nameLabel);
        add(emailLabel);
        add(closeBtn);

        // 设置 JFrame 的大小、位置和关闭操作
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        closeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
    }
}
