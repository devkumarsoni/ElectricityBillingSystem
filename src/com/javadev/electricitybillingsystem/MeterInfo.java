package com.javadev.electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener{

    private JLabel  meterNo, days, note;
    private Choice location, meterType, phaseCode, billtype;
    private JButton submit;
    private String meterNumber;

    public MeterInfo(String meterNumber) {
        super("Meter Information");
        this.meterNumber = meterNumber;
        createMeterInfoFrame();
    }

    private void createMeterInfoFrame() {
        setBounds(400,150,700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(173,216,230));
        panel.setLayout(null);

        JLabel heading = new JLabel("Meter Information");

        JLabel enterMeter = new JLabel("Meter Number");
        meterNo = new JLabel(meterNumber);

        JLabel meterLocation = new JLabel("Meter Location");
        location = new Choice();

        JLabel enterMeterType = new JLabel("Meter Type");
        meterType = new Choice();

        JLabel enterPhaseCode = new JLabel("Phase Code");
        phaseCode = new Choice();

        JLabel enterBillType = new JLabel("Bill Type");
        billtype = new Choice();

        JLabel enterDays = new JLabel("Days");
        days = new JLabel("30 Days");

        JLabel enterNote = new JLabel("Note");
        note = new JLabel("By Default Bill Is Calculated For 30 Days");


        submit = new JButton("Submit");

        Image img = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/hicon1.jpg")).getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(img));

        heading.setBounds(200,20,200,25);
        heading.setFont(new Font("tahoma", Font.PLAIN, 24));

        enterMeter.setBounds(100,80,100,20);
        meterNo.setBounds(250,80,200,20);

        meterLocation.setBounds(100,120,100,20);
        location.setBounds(250,120,200,20);
        location.add("Outdoor");
        location.add("Indoor");

        enterMeterType.setBounds(100,160,100,20);
        meterType.setBounds(250,160,200,20);
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Smart Meter");

        enterPhaseCode.setBounds(100,200,100,20);
        phaseCode.setBounds(250,200,200,20);
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");


        enterBillType.setBounds(100,240,100,20);
        billtype.setBounds(250,240,200,20);
        billtype.add("Household");
        billtype.add("Industrial");

        enterDays.setBounds(100,280,100,20);
        days.setBounds(250,280,200,20);

        enterNote.setBounds(100,320,100,20);
        note.setBounds(250,320,200,20);


        submit.setBounds(220, 380, 100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);

        panel.add(heading);
        panel.add(enterMeter);
        panel.add(meterNo);
        panel.add(meterLocation);
        panel.add(location);
        panel.add(enterMeterType);
        panel.add(meterType);
        panel.add(enterPhaseCode);
        panel.add(phaseCode);
        panel.add(enterBillType);
        panel.add(billtype);
        panel.add(enterDays);
        panel.add(days);
        panel.add(enterNote);
        panel.add(note);
        panel.add(submit);

        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        add(panel, "Center");
        add(image, "West");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String query = "insert into meter_info values('"+meterNo.getText()+"','"+location.getSelectedItem()+"','"+meterType.getSelectedItem()+"','"+phaseCode.getSelectedItem()+"','"+billtype.getSelectedItem()+"','"+"30 Days"+"')";
            try {
                DataBaseConnectivityMySQL connectivityMySQL = new DataBaseConnectivityMySQL();
                connectivityMySQL.getStatement().executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Meter Information Added Successfully !!");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }
}
