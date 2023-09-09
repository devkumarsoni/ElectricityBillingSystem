package com.javadev.electricitybillingsystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {

    private JButton create, back;
    private Choice createAccountAsChoice;
    private JTextField enterMeter, enterUsername, enterName, enterPassword;

    public Signup() {
        super("Signup");
        createSignupFrame();
    }

    private void createSignupFrame() {
        JPanel panel = new JPanel();

        JLabel createAccountAs = new JLabel("Create Account As");
        createAccountAsChoice = new Choice();

        JLabel meter = new JLabel("Meter Number");
        meter.setVisible(false);
        enterMeter = new JTextField();
        enterMeter.setVisible(false);

        JLabel username = new JLabel("Username");
        enterUsername = new JTextField();

        JLabel name = new JLabel("Name");
        enterName = new JTextField();

        enterMeter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    DataBaseConnectivityMySQL connectMySQL = new DataBaseConnectivityMySQL();
                    ResultSet resultSet = connectMySQL.getStatement().executeQuery("select * from login where meter_no = '"+enterMeter.getText()+"'");
                    while (resultSet.next())
                        enterName.setText(resultSet.getString("name"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        createAccountAsChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = createAccountAsChoice.getSelectedItem();
                if (user.equals("Customer")) {
                    meter.setVisible(true);
                    enterMeter.setVisible(true);
                    enterName.setEditable(false);
                }
                else {
                    meter.setVisible(false);
                    enterMeter.setVisible(false);
                    enterName.setEditable(true);
                }
            }
        });

        JLabel password = new JLabel("Password");
        enterPassword = new JTextField();

        create = new JButton("Create");
        back = new JButton("Back");

        Image signupIcon = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/signupImage.png")).getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        JLabel signupImg = new JLabel(new ImageIcon(signupIcon));

        setBounds(450,150,700,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        panel.setLayout(null);
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230)));
        panel.setBackground(Color.WHITE);

        createAccountAs.setBounds(100,50,140,20);
        createAccountAs.setForeground(Color.GRAY);
        createAccountAs.setFont(new Font("Tahoma", Font.BOLD, 14));

        createAccountAsChoice.setBounds(260, 50, 150, 20);
        createAccountAsChoice.add("Admin");
        createAccountAsChoice.add("Customer");

        meter.setBounds(100, 90, 140, 20);
        meter.setForeground(Color.GRAY);
        meter.setFont(new Font("Tahoma", Font.BOLD, 14));

        enterMeter.setBounds(260,90,150,20);

        username.setBounds(100, 130, 140, 20);
        username.setForeground(Color.GRAY);
        username.setFont(new Font("Tahoma", Font.BOLD, 14));

        enterUsername.setBounds(260,130,150,20);

        name.setBounds(100, 170, 140, 20);
        name.setForeground(Color.GRAY);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));

        enterName.setBounds(260,170,150,20);

        password.setBounds(100, 210, 140, 20);
        password.setForeground(Color.GRAY);
        password.setFont(new Font("Tahoma", Font.BOLD, 14));

        enterPassword.setBounds(260,210,150,20);

        create.setBounds(130,250,140,25);
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.addActionListener(this);

        back.setBounds(300, 250,140,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);

        signupImg.setBounds(420,30,250,250);

        add(panel);

//        ADDING ELEMENTS TO THE PANEL
        panel.add(createAccountAs);
        panel.add(createAccountAsChoice);
        panel.add(meter);
        panel.add(enterMeter);
        panel.add(username);
        panel.add(enterUsername);
        panel.add(name);
        panel.add(enterName);
        panel.add(password);
        panel.add(enterPassword);
        panel.add(create);
        panel.add(back);
        panel.add(signupImg);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String query = null;
            String account_type = createAccountAsChoice.getSelectedItem();
            if (account_type.equals("Admin")) {
                query = "insert into login values("
                        +"'"+enterMeter.getText()+"'"+","
                        +"'"+createAccountAsChoice.getSelectedItem()+"'"+","
                        +"'"+enterName.getText()+"'"+","
                        +"'"+enterUsername.getText()+"'"+","
                        +"'"+enterPassword.getText()+"'"
                        +")";
            }
            else {
                query = "update login" +
                        " set username = '"+enterUsername.getText()+"', password = '"+enterPassword.getText()+"', account_type = '"+account_type+"'" +
                        " where meter_no = '"+enterMeter.getText()+"'";
            }
            try {
                DataBaseConnectivityMySQL connectivityMySQL = new DataBaseConnectivityMySQL();
                connectivityMySQL.getStatement().executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Account Created Sucessfully !!!");

                setVisible(false);
                new Login();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }
}
