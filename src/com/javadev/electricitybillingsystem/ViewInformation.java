package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewInformation extends JFrame implements ActionListener {

    JLabel heading = new JLabel("View Customer Information");
    JLabel name = new JLabel("Name");
    JLabel showName = new JLabel();
    JLabel meter_no = new JLabel("Meter Number");
    JLabel showMeter_no = new JLabel();
    JLabel address = new JLabel("Address");
    JLabel showAddress = new JLabel();
    JLabel city = new JLabel("City");
    JLabel showCity = new JLabel();
    JLabel state = new JLabel("State");
    JLabel showState = new JLabel();
    JLabel email = new JLabel("Email");
    JLabel showEmail = new JLabel();
    JLabel phone = new JLabel("Phone No.");
    JLabel showPhone = new JLabel();

    JButton cancel = new JButton("Cancel");

    Image img = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/viewcustomer.jpg")).getImage().getScaledInstance(600,280,Image.SCALE_DEFAULT);
    JLabel image = new JLabel(new ImageIcon(img));

    String meter;

    public ViewInformation(String meter) {
        super("View Information");
        this.meter = meter;
        createFrame();
    }

    private void createFrame() {
        setBounds(450,100,700,600);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        heading.setBounds(230,20,400,25);
        heading.setFont(new Font("Tahoma", Font.PLAIN,20));

        name.setBounds(70,100,100,20);
        showName.setBounds(200,100,100,20);

        meter_no.setBounds(70,150,100,20);
        showMeter_no.setBounds(200,150,100,20);

        address.setBounds(70,200,100,20);
        showAddress.setBounds(200,200,100,20);

        city.setBounds(70,250,100,20);
        showCity.setBounds(200,250,100,20);

        state.setBounds(400,100,100,20);
        showState.setBounds(530,100,100,20);

        email.setBounds(400,150,100,20);
        showEmail.setBounds(530,150,150,20);

        phone.setBounds(400,200,100,20);
        showPhone.setBounds(530,200,100,20);

        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(300,320,100,25);
        cancel.addActionListener(this);

        image.setBounds(10,300,650,280);


        add(heading);
        add(name);
        add(showName);
        add(meter_no);
        add(showMeter_no);
        add(address);
        add(showAddress);
        add(city);
        add(showCity);
        add(state);
        add(showState);
        add(email);
        add(showEmail);
        add(phone);
        add(showPhone);
        add(cancel);
        add(image);

        try {
            DataBaseConnectivityMySQL connectMYSQl = new DataBaseConnectivityMySQL();
            ResultSet resultSet = connectMYSQl.getStatement().executeQuery("select * from customer where meter_no = '"+meter+"'");

            while (resultSet.next()) {
                showName.setText(resultSet.getString("name"));
                showMeter_no.setText(resultSet.getString("meter_no"));
                showAddress.setText(resultSet.getString("address"));
                showCity.setText(resultSet.getString("city"));
                showState.setText(resultSet.getString("state"));
                showEmail.setText(resultSet.getString("email"));
                showPhone.setText(resultSet.getString("phone_no"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}
