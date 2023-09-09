package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    Thread t = new Thread(this);

    public Splash() {
        super("Welcome");
        createFrame();
    }

    private void createFrame() {
        int x = 1;
        addImage();
        setVisible(true);
        for (int i = 0; i < 600; i+=4, x++) {
            setSize(i + x,i);
            setLocation(700 - ((i+x)/2), 400 - (i/2));
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.start();
    }

    private void addImage() {
        Image img = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/elect.jpg")).getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(img);
        JLabel label1 = new JLabel();
        label1.setIcon(icon1);
        add(label1);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            setVisible(false);

            new Login();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
