package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {

    JLabel heading = new JLabel("Update Customer Information");
    JLabel name = new JLabel("Name");
    JLabel enterName = new JLabel("Dev");
    JLabel meter_no = new JLabel("Meter Number");
    JLabel enterMeter_no = new JLabel("987964");
    JLabel address = new JLabel("Address");
    JLabel city = new JLabel("City");
    JLabel state = new JLabel("State");
    JLabel email = new JLabel("Email");
    JLabel phone = new JLabel("Phone");

    JTextField enterAddress = new JTextField();
    JTextField enterCity = new JTextField();
    JTextField enterState = new JTextField();
    JTextField enterEmail = new JTextField();
    JTextField enterPhone = new JTextField();

    JButton update = new JButton("Update");
    JButton cancel = new JButton("Cancel");

    Image img = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/update.jpg")).getImage().getScaledInstance(280,250,Image.SCALE_DEFAULT);
    JLabel image = new JLabel(new ImageIcon(img));

    String meter;

    public UpdateInformation(String meter) {
        super("Update Information");
        this.meter = meter;
        createFrame();
    }

    private void createFrame() {
        setBounds(400,200,750,400);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        heading.setBounds(100,20,280,25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));

        name.setBounds(30,80,100,20);
        enterName.setBounds(180,80,100,20);

        meter_no.setBounds(30,110,100,20);
        enterMeter_no.setBounds(180,110,100,20);

        address.setBounds(30,140,100,20);
        enterAddress.setBounds(180,140,200,20);

        city.setBounds(30,170,100,20);
        enterCity.setBounds(180,170,200,20);

        state.setBounds(30,200,100,20);
        enterState.setBounds(180,200,200,20);

        email.setBounds(30,230,100,20);
        enterEmail.setBounds(180,230,200,20);

        phone.setBounds(30,260,100,20);
        enterPhone.setBounds(180,260,200,20);

        image.setBounds(410,50,300,250);

        update.setBounds(70,310,100,25);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);

        cancel.setBounds(230,310,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);

        try {
            DataBaseConnectivityMySQL connect = new DataBaseConnectivityMySQL();
            ResultSet resultSet = connect.getStatement().executeQuery("select * from customer where meter_no = '"+meter+"'");

            while (resultSet.next()) {
                enterName.setText(resultSet.getString("name"));
                enterAddress.setText(resultSet.getString("address"));
                enterCity.setText(resultSet.getString("city"));
                enterState.setText(resultSet.getString("state"));
                enterEmail.setText(resultSet.getString("email"));
                enterPhone.setText(resultSet.getString("phone_no"));
                enterMeter_no.setText(resultSet.getString("meter_no"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        add(heading);
        add(name);
        add(enterName);
        add(meter_no);
        add(enterMeter_no);
        add(address);
        add(enterAddress);
        add(city);
        add(enterCity);
        add(state);
        add(enterState);
        add(email);
        add(enterEmail);
        add(phone);
        add(enterPhone);
        add(update);
        add(image);
        add(cancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String query = "update customer" +
                    " set address = '"+enterAddress.getText()+"', city = '"+enterCity.getText()+"', state = '"+enterState.getText()+"', email = '"+enterEmail.getText()+"', phone_no = '"+enterPhone.getText()+"'" +
                    " where meter_no = '"+meter+"'";
            try {
                DataBaseConnectivityMySQL connect = new DataBaseConnectivityMySQL();
                connect.getStatement().executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Information Updated Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }
}
