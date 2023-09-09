package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCustomer extends JFrame implements ActionListener {

    private JTextField name, address, city, state, email, phoneNo;
    private JLabel meterNo;
    private JButton next, cancel;

    public NewCustomer() {
        super("Create New Customer");
        createNewCustomerFrame();
    }

    private void createNewCustomerFrame() {
        setBounds(400,150,700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(173,216,230));
        panel.setLayout(null);

        JLabel heading = new JLabel("New Customer");

        JLabel enterName = new JLabel("Customer Name");
        name = new JTextField();

        JLabel enterMeterNo = new JLabel("Meter No.");
        meterNo = new JLabel();

        JLabel enterAddress = new JLabel("Address");
        address = new JTextField();

        JLabel enterCity = new JLabel("City");
        city = new JTextField();

        JLabel enterState = new JLabel("State");
        state = new JTextField();

        JLabel enterEmail = new JLabel("Email");
        email = new JTextField();

        JLabel enterPhoneNo = new JLabel("Phone No.");
        phoneNo = new JTextField();

        next = new JButton("Next");
        cancel = new JButton("Cancel");

        Image img = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/hicon1.jpg")).getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(img));

        heading.setBounds(200,20,200,25);
        heading.setFont(new Font("tahoma", Font.PLAIN, 24));

        enterName.setBounds(100,80,100,20);
        name.setBounds(250,80,200,20);

        enterMeterNo.setBounds(100,120,100,20);
        meterNo.setBounds(250,120,200,20);
        meterNo.setText("" + (int) Math.abs(Math.random() * 1000000));

        enterAddress.setBounds(100,160,100,20);
        address.setBounds(250,160,200,20);

        enterCity.setBounds(100,200,100,20);
        city.setBounds(250,200,200,20);

        enterState.setBounds(100,240,100,20);
        state.setBounds(250,240,200,20);

        enterEmail.setBounds(100,280,100,20);
        email.setBounds(250,280,200,20);

        enterPhoneNo.setBounds(100,320,100,20);
        phoneNo.setBounds(250,320,200,20);

        next.setBounds(120, 380, 100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);

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
        panel.add(enterCity);
        panel.add(city);
        panel.add(enterState);
        panel.add(state);
        panel.add(enterEmail);
        panel.add(email);
        panel.add(enterPhoneNo);
        panel.add(phoneNo);
        panel.add(next);
        panel.add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        add(panel, "Center");
        add(image, "West");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            String queryOne = "insert into customer values('"+name.getText()+"','"+meterNo.getText()+"','"+address.getText()+"','"+city.getText()+"','"+state.getText()+"','"+email.getText()+"','"+phoneNo.getText()+"')";
            String queryTwo = "insert into login values('"+meterNo.getText()+"','','"+name.getText()+"','','')";
            try {
                DataBaseConnectivityMySQL connectivityMySQL = new DataBaseConnectivityMySQL();
                connectivityMySQL.getStatement().executeUpdate(queryOne);
                connectivityMySQL.getStatement().executeUpdate(queryTwo);

                JOptionPane.showMessageDialog(null,"User Registered Sucessfuly !!");
                new MeterInfo(meterNo.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            setVisible(false);
            setVisible(false);
        }
    }
}
