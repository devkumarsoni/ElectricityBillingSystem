package com.javadev.electricitybillingsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class BillDetails extends JFrame {

    String meter;
    JTable table = new JTable();

    public BillDetails(String meter) {
        super("Bill Details");
        this.meter = meter;
        createFrame();
    }

    private void createFrame() {
        setBounds(480,100,550,600);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        try {
            DataBaseConnectivityMySQL connect = new DataBaseConnectivityMySQL();
            ResultSet resultSet = connect.getStatement().executeQuery("select * from bill where meter_no = '"+meter+"'");

            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,550,600);

        add(scrollPane);
    }
}
