package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class CalculateBill extends JFrame implements ActionListener {

    private JLabel enterMeterNo, enterName, name, enterAddress, address, enterUnitConsumed, enterMonth;
    private Choice meterNo, month;
    private JTextField unitConsumed;
    private JButton submit, cancel;
    String bill = "";

    public CalculateBill() {
        createNewCustomerFrame();
    }

    private void createNewCustomerFrame() {
        setBounds(400,150,700,500);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(173,216,230));
        panel.setLayout(null);

        JLabel heading = new JLabel("Calculate Electricity Bill");

        enterName = new JLabel("Name");
        name = new JLabel();

        enterMeterNo = new JLabel("Meter No.");
        meterNo = new Choice();

        enterAddress = new JLabel("Address");
        address = new JLabel();

        enterUnitConsumed = new JLabel("Unit Consumed");
        unitConsumed = new JTextField();

        enterMonth = new JLabel("Month");
        month = new Choice();

        submit = new JButton("Submit");
        cancel = new JButton("Cancel");

        Image img = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/hicon2.jpg")).getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(img));

        heading.setBounds(150,20,350,25);
        heading.setFont(new Font("tahoma", Font.PLAIN, 24));

        enterMeterNo.setBounds(100,80,100,20);
        meterNo.setBounds(250,80,200,20);

        try {
            String query = "select * from login";
            DataBaseConnectivityMySQL connectMySQL = new DataBaseConnectivityMySQL();
            ResultSet resultSet = connectMySQL.getStatement().executeQuery(query);
            while (resultSet.next()) {
                meterNo.add(resultSet.getString("meter_no"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        enterName.setBounds(100,140,100,20);
        name.setBounds(250,140,200,20);

        enterAddress.setBounds(100,200,100,20);
        address.setBounds(250,200,200,20);

        meterNo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    String query = "select * from customer where meter_no = '"+meterNo.getSelectedItem()+"'";
                    DataBaseConnectivityMySQL connectMySQL = new DataBaseConnectivityMySQL();
                    ResultSet resultSet = connectMySQL.getStatement().executeQuery(query);
                    while (resultSet.next()) {
                        name.setText(resultSet.getString("name"));
                        address.setText(resultSet.getString("address"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        enterUnitConsumed.setBounds(100,260,100,20);
        unitConsumed.setBounds(250,260,200,20);

        enterMonth.setBounds(100,320,100,20);
        month.setBounds(250,320,200,20);
        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");

        submit.setBounds(120, 380, 100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);

        cancel.setBounds(280, 380, 100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);

        panel.add(heading);
        panel.add(enterName);
        panel.add(name);
        panel.add(enterMeterNo);
        panel.add(meterNo);
        panel.add(enterAddress);
        panel.add(address);
        panel.add(enterUnitConsumed);
        panel.add(unitConsumed);
        panel.add(enterMonth);
        panel.add(month);
        panel.add(submit);
        panel.add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        add(panel, "Center");
        add(image, "West");

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            float total_bill = (float)(Integer.parseInt(unitConsumed.getText()) * 1.37);
            String queryOne = "insert into bill values('"+meterNo.getSelectedItem()+"','"+month.getSelectedItem()+"','"+unitConsumed.getText()+"','"+total_bill+"','not paid')";
            try {
                DataBaseConnectivityMySQL connectivityMySQL = new DataBaseConnectivityMySQL();
                connectivityMySQL.getStatement().executeUpdate(queryOne);
                ResultSet resultSet = connectivityMySQL.getStatement().executeQuery("select * from bill where meter_no = '"+meterNo.getSelectedItem()+"'");
                while (resultSet.next())
                    bill = resultSet.getString("total_bill");


                JOptionPane.showMessageDialog(null,"Bill Generated Successfully for "+bill+" Rs.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }
}
