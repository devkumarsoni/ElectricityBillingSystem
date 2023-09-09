package com.javadev.electricitybillingsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {

    JLabel searchMeterNo = new JLabel("Search By Meter Number");
    Choice meterNo = new Choice();
    JLabel searchMonth = new JLabel("Search By Month");
    Choice month = new Choice();
    JTable table = new JTable();
    JButton search = new JButton("Search");
    JButton print = new JButton("Print");

    public DepositDetails() {
        super("Deposit Details");
        createDepositDetailFrame();
    }

    private void createDepositDetailFrame() {
        setSize(700,700);
        setLocation(400,100);
        getContentPane().setBackground(new Color(255,204,204));
        setLayout(null);

        searchMeterNo.setBounds(20,20,150,20);
        meterNo.setBounds(180,20,150,20);

        try {
            DataBaseConnectivityMySQL conn = new DataBaseConnectivityMySQL();
            ResultSet rs = conn.getStatement().executeQuery("select * from customer");
            while (rs.next()) {
                meterNo.add(rs.getString("meter_no"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        searchMonth.setBounds(350,20,100,20);
        month.setBounds(480,20,150,20);
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

        search.setBounds(80,70,100,25);
        search.setForeground(Color.WHITE);
        search.setBackground(Color.BLACK);
        search.addActionListener(this);

        print.setBounds(210,70,100,25);
        print.setForeground(Color.WHITE);
        print.setBackground(Color.BLACK);
        print.addActionListener(this);

        try {
            DataBaseConnectivityMySQL connectivityMySQL = new DataBaseConnectivityMySQL();
            ResultSet resultSet = connectivityMySQL.getStatement().executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,600);

        add(searchMeterNo);
        add(meterNo);
        add(searchMonth);
        add(month);
        add(search);
        add(print);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String query = "select * from bill where meter_no = '"+meterNo.getSelectedItem()+"' and month = '"+month.getSelectedItem()+"'";
            try {
                DataBaseConnectivityMySQL connectMySQL = new DataBaseConnectivityMySQL();
                ResultSet resultSet = connectMySQL.getStatement().executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
