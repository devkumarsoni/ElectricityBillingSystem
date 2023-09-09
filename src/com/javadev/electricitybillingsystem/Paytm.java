package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paytm extends JFrame implements ActionListener {

    JEditorPane editorPane = new JEditorPane();
    JScrollPane scrollPane;
    JButton back = new JButton("Back");

    String meter;
    public Paytm(String meter) {
        super("Paytm");
        this.meter = meter;
        createFrame();
    }

    private void createFrame() {
//        setLayout(null);

        editorPane.setEditable(false);

        try {
            editorPane.setPage("https://paytm.com/online-payments");
        } catch (Exception ex) {
            editorPane.setContentType("text/html");
            editorPane.setText("<html>could not load...</html>");
        }

        scrollPane = new JScrollPane(editorPane);

        back.setBounds(660,20,80,30);
        back.addActionListener(this);

        add(scrollPane);
        editorPane.add(back);

        setBounds(350,150,800,600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PayBill(meter);
    }
}
