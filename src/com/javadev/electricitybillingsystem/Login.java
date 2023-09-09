package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    private JButton login, cancel, signup;
    private JTextField enterUsername, enterPassword;
    private Choice enterChoice;

    public Login() {
        super("Login");
        createLoginFrame();
    }

    public void createLoginFrame() {
        JLabel userName = new JLabel("Username");
        enterUsername = new JTextField();

        JLabel password = new JLabel("Password");
        enterPassword = new JTextField();

        JLabel logginAs = new JLabel("Loggin In As");
        enterChoice = new Choice();

        Image loginIcon = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/login.png")).getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(loginIcon));

        Image cancelIcon = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/cancel.jpg")).getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel", new ImageIcon(cancelIcon));

        Image signupIcon = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/signup.png")).getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        signup = new JButton("Signup", new ImageIcon(signupIcon));

        Image img = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/second.jpg")).getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        JLabel mainImg = new JLabel(new ImageIcon(img));

        setSize(650,300);
        setLocation(400,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        setLayout(null);

        userName.setBounds(300,20,100,20);
        enterUsername.setBounds(400, 20, 150, 20);

        password.setBounds(300,60,100,20);
        enterPassword.setBounds(400,60,150,20);

        logginAs.setBounds(300,100,100,20);
        enterChoice.setBounds(400,100,150,20);
        enterChoice.add("Admin");
        enterChoice.add("Customer");

        login.setBounds(330,140,100,20);
        login.addActionListener(this);

        cancel.setBounds(450,140,100,20);
        cancel.addActionListener(this);

        signup.setBounds(380,180,100,20);
        signup.addActionListener(this);

        mainImg.setBounds(0,0,250,250);

        add(userName);
        add(enterUsername);
        add(password);
        add(enterPassword);
        add(logginAs);
        add(enterChoice);
        add(login);
        add(cancel);
        add(signup);
        add(mainImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            DataBaseConnectivityMySQL connectivityMySQL = new DataBaseConnectivityMySQL();
            String aType = enterChoice.getSelectedItem();
            String query = "select * " +
                    "from login " +
                    "where account_type =" + "'" + aType + "'" +
                    "and username =" + "'" + enterUsername.getText() + "'" +
                    "and password =" + "'" + enterPassword.getText() + "'";
            try {
                ResultSet resultSet = connectivityMySQL.getStatement().executeQuery(query);
                String meter;

                if (resultSet.next()) {
                    setVisible(false);
                    meter = resultSet.getString("meter_no");
                    new Project(aType,meter);
                }

                else {
                    JOptionPane.showMessageDialog(null,"Incorrect Details !!");
                    enterUsername.setText("");
                    enterPassword.setText("");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == cancel) {
            setVisible(false);
        } else if (e.getSource() == signup) {
            setVisible(false);
            new Signup();
        }
    }
}
