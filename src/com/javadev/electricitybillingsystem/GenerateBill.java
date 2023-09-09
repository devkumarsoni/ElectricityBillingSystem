package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GenerateBill extends JFrame implements ActionListener {

    JButton generateBill;
    JTextArea area;
    Choice enterMonth;
    String meter;

    public GenerateBill(String meter) {
        super("Generate Bill");
        this.meter = meter;
        createFrame();
    }

    private void createFrame() {
        setBounds(550, 10, 500,800);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JLabel heading = new JLabel("Generate Bill");
        JLabel meter_no = new JLabel(meter);

        enterMonth = new Choice();
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

        area = new JTextArea(50,15);
        area.setText("\n\n\t----------Click On The-----------\n\t Generate Bill Button To Get\n\t The Bill Of The Selected Month");
        area.setFont(new Font("senserif", Font.ITALIC, 18));

        JScrollPane pane = new JScrollPane(area);

        generateBill = new JButton("Generate Bill");
        generateBill.addActionListener(this);

        panel.add(heading);
        panel.add(meter_no);
        panel.add(enterMonth);
        add(panel, "North");

        add(pane, "Center");
        add(generateBill, "South");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            DataBaseConnectivityMySQL connect = new DataBaseConnectivityMySQL();
            String month = enterMonth.getSelectedItem();
            area.setText("\tRealiance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\t OF "+month+", 2023\n\n\n");

            ResultSet resultSet = connect.getStatement().executeQuery("select * from customer where meter_no = '"+meter+"'");
            while (resultSet.next()) {
                area.append("\n Customer Name: " + resultSet.getString("name"));
                area.append("\n Meter Number   : " + resultSet.getString("meter_no"));
                area.append("\n Address              : " + resultSet.getString("address"));
                area.append("\n City                     : " + resultSet.getString("city"));
                area.append("\n State                   : " + resultSet.getString("state"));
                area.append("\n Email                  : " + resultSet.getString("email"));
                area.append("\n Phone Number : " + resultSet.getString("phone_no"));
                area.append("\n----------------------------------------------------------");
                area.append("\n");
            }

            resultSet = connect.getStatement().executeQuery("select * from meter_info where meter_no = '"+meter+"'");
            while (resultSet.next()) {
                area.append("\n Meter Location : " + resultSet.getString("meter_location"));
                area.append("\n Meter Number  : " + resultSet.getString("meter_no"));
                area.append("\n Meter Type        : " + resultSet.getString("meter_type"));
                area.append("\n Phase Code      : " + resultSet.getString("phase_code"));
                area.append("\n Bill Type             : " + resultSet.getString("bill_type"));
                area.append("\n Days                  : " + resultSet.getString("days"));
            }
                area.append("\n----------------------------------------------------------");
                area.append("\n");
                area.append("\n Cost Per Unit             : 9.00 ₹");
                area.append("\n Meter Rent                : 56.00 ₹");
                area.append("\n Service Charge        : 22%");
                area.append("\n Service Tax              : 47%");
                area.append("\n Swach Bharat Cess: 49.00 ₹");
                area.append("\n Fixed Tax                 : 18%");

            resultSet = connect.getStatement().executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+month+"'");
            while (resultSet.next()) {
                area.append("\n");
                area.append("\n Current Month         : " + month);
                area.append("\n Units Consumed    : " + resultSet.getString("unit"));
                area.append("\n Total Charges        : " + resultSet.getString("total_bill"));
                area.append("\n----------------------------------------------------------");
                area.append("\n Total Payable        : " + resultSet.getString("total_bill"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
