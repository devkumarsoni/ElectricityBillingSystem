package com.javadev.electricitybillingsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {

    private JTable table = new JTable();
    private JButton print = new JButton("Print");

    public CustomerDetails() {
        super("Customer Details");
        createCustomerDetailFrame();
    }

    private void createCustomerDetailFrame() {
        setSize(1200,650);
        setLocation(200,100);

        print.addActionListener(this);

        try {
            DataBaseConnectivityMySQL connectivityMySQL = new DataBaseConnectivityMySQL();
            ResultSet resultSet = connectivityMySQL.getStatement().executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);

        add(print, "South");
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

}
