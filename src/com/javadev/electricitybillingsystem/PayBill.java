package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;

public class PayBill extends JFrame implements ActionListener {

    JLabel heading = new JLabel("Electricity Bill");
    JLabel name = new JLabel("Name");
    JLabel enterName = new JLabel("Dev");
    JLabel meter_no = new JLabel("Meter Number");
    JLabel enterMeter_no = new JLabel("997535");
    JLabel month = new JLabel("Month");
    JLabel units = new JLabel("Units");
    JLabel enterUnits = new JLabel("989");
    JLabel totalBill = new JLabel("Total Bill");
    JLabel enterTotalBill = new JLabel("98998");
    JLabel status = new JLabel("Status");
    JLabel enterStatus = new JLabel("Paid");

    Choice enterMonth = new Choice();

    JButton pay = new JButton("Pay Bill");
    JButton back = new JButton("Back");

    Image img = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/bill.png")).getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
    JLabel image = new JLabel(new ImageIcon(img));

    String meter;

    public PayBill(String meter) {
        super("Pay Bill");
        this.meter = meter;
        createFrame();
    }

    private void createFrame() {
        setBounds(350,75,900,600);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        heading.setBounds(100,5,300,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,30));

        meter_no.setBounds(50,80,100,25);
        enterMeter_no.setBounds(200,80,200,25);

        name.setBounds(50,140,100,25);
        enterName.setBounds(200,140,200,25);

        month.setBounds(50,200,100,25);
        enterMonth.setBounds(200,200,200,25);
        enterMonth.add("January");
        enterMonth.add("February");
        enterMonth.add("March");
        enterMonth.add("April");
        enterMonth.add("May");
        enterMonth.add("June");
        enterMonth.add("July");
        enterMonth.add("August");
        enterMonth.add("September");
        enterMonth.add("October");
        enterMonth.add("November");
        enterMonth.add("December");

        units.setBounds(50,260,100,25);
        enterUnits.setBounds(200,260,200,25);

        totalBill.setBounds(50,320,100,25);
        enterTotalBill.setBounds(200,320,200,25);

        status.setBounds(50,380,100,25);
        enterStatus.setBounds(200,380,200,25);
        enterStatus.setForeground(Color.RED);

        pay.setBounds(100,440,100,25);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);

        back.setBounds(260,440,100,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);

        image.setBounds(320,100,600,300);

        try {
            DataBaseConnectivityMySQL connect = new DataBaseConnectivityMySQL();
            ResultSet resultSet = connect.getStatement().executeQuery("select * from customer where meter_no = '"+meter+"'");
            while (resultSet.next()) {
                enterMeter_no.setText(meter);
                enterName.setText(resultSet.getString("name"));
            }

            resultSet = connect.getStatement().executeQuery("select * from bill where meter_no = '"+meter+"' and month = 'January'");
            while (resultSet.next()) {
                enterUnits.setText(resultSet.getString("unit"));
                enterTotalBill.setText(resultSet.getString("total_bill"));
                enterStatus.setText(resultSet.getString("status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        enterMonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    DataBaseConnectivityMySQL connect = new DataBaseConnectivityMySQL();
                    ResultSet resultSet = connect.getStatement().executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+enterMonth.getSelectedItem()+"'");
                    while (resultSet.next()) {
                        enterUnits.setText(resultSet.getString("unit"));
                        enterTotalBill.setText(resultSet.getString("total_bill"));
                        enterStatus.setText(resultSet.getString("status"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(heading);
        add(meter_no);
        add(enterMeter_no);
        add(name);
        add(enterName);
        add(month);
        add(enterMonth);
        add(units);
        add(enterUnits);
        add(totalBill);
        add(enterTotalBill);
        add(status);
        add(enterStatus);
        add(pay);
        add(back);
        add(image);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            try {
                DataBaseConnectivityMySQL connect = new DataBaseConnectivityMySQL();
                connect.getStatement().executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+enterMonth.getSelectedItem()+"'");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        }
        else {
            setVisible(false);
        }
    }
}
