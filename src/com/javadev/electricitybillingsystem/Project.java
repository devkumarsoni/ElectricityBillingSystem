package com.javadev.electricitybillingsystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class Project extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu master, information, user, report, utility, exit;
    private JMenuItem viewCustomer, customerDetails, depositDetail, calculateBill;
    private JMenuItem updateInfo, viewInfo;
    private JMenuItem payBill, billDetail;
    private JMenuItem generateBill;
    private JMenuItem notepad, calculator, exit0;
    private String aType, meter;

    public Project(String aType, String meter) {
        super("Main");
        this.aType = aType;
        this.meter = meter;
        createProjectFrame();
    }

    private void createProjectFrame() {
        Image mainImg = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/elect1.jpg")).getImage().getScaledInstance(1550,850,Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(mainImg));

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        master = new JMenu("Master");

        viewCustomer = new JMenuItem("New Customer");
        customerDetails = new JMenuItem("Customer Details");
        depositDetail = new JMenuItem("Deposit Details");
        calculateBill = new JMenuItem("Calculate Bill");

        information = new JMenu("Information");

        updateInfo = new JMenuItem("Update Information");
        viewInfo = new JMenuItem("View Information");

        user = new JMenu("User");

        payBill = new JMenuItem("Pay Bill");
        billDetail = new JMenuItem("Bill Details");

        report = new JMenu("Report");

        generateBill = new JMenuItem("Generate Bill");

        utility = new JMenu("Utility");

        notepad = new JMenuItem("Notepad");
        calculator = new JMenuItem("Calculator");

        exit = new JMenu("Exit");

        exit0 = new JMenuItem("Exit 0");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        master.setForeground(Color.BLUE);

        viewCustomer.setBackground(Color.WHITE);
        viewCustomer.setFont(new Font("monospaced",Font.ITALIC,12));
        Image img1 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon1.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        viewCustomer.setIcon(new ImageIcon(img1));
        viewCustomer.setMnemonic('D');
        viewCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        viewCustomer.addActionListener(this);

        customerDetails.setBackground(Color.WHITE);
        customerDetails.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img2 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon2.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(img2));
        customerDetails.setMnemonic('M');
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        customerDetails.addActionListener(this);

        depositDetail.setBackground(Color.WHITE);
        depositDetail.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img3 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon3.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        depositDetail.setIcon(new ImageIcon(img3));
        depositDetail.setMnemonic('N');
        depositDetail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        depositDetail.addActionListener(this);

        calculateBill.setBackground(Color.WHITE);
        calculateBill.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img4 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon5.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(img4));
        calculateBill.setMnemonic('B');
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        calculateBill.addActionListener(this);

        master.add(viewCustomer);
        master.add(customerDetails);
        master.add(depositDetail);
        master.add(calculateBill);

        information.setForeground(Color.RED);

        updateInfo.setBackground(Color.WHITE);
        updateInfo.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img5 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon3.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(img5));
        updateInfo.addActionListener(this);
        updateInfo.setMnemonic('P');
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        viewInfo.setBackground(Color.WHITE);
        viewInfo.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img6 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon6.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(img6));
        viewInfo.addActionListener(this);
        viewInfo.setMnemonic('L');
        viewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));

        information.add(updateInfo);
        information.add(viewInfo);

        user.setForeground(Color.BLUE);

        payBill.setBackground(Color.WHITE);
        payBill.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img7 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon4.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(img7));
        payBill.addActionListener(this);
        payBill.setMnemonic('R');
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

        billDetail.setBackground(Color.WHITE);
        billDetail.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img8 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon5.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        billDetail.setIcon(new ImageIcon(img8));
        billDetail.addActionListener(this);
        billDetail.setMnemonic('I');
        billDetail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

        user.add(payBill);
        user.add(billDetail);

        report.setForeground(Color.RED);

        generateBill.setBackground(Color.WHITE);
        generateBill.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img9 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon7.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(img9));
        generateBill.addActionListener(this);
        generateBill.setMnemonic('G');
        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));

        report.add(generateBill);

        utility.setForeground(Color.BLUE);

        notepad.setBackground(Color.WHITE);
        notepad.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img10 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon7.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(img10));
        notepad.addActionListener(this);
        notepad.setMnemonic('F');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

        calculator.setBackground(Color.WHITE);
        calculator.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img11 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon9.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(img11));
        calculator.addActionListener(this);
        calculator.setMnemonic('C');
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        utility.add(notepad);
        utility.add(calculator);

        exit.setForeground(Color.RED);

        exit0.setBackground(Color.WHITE);
        exit0.setFont(new Font("monospaced", Font.ITALIC, 12));
        Image img12 = new ImageIcon(ClassLoader.getSystemResource("com/javadev/Assets/icon/icon11.png")).getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        exit0.setIcon(new ImageIcon(img12));
        exit0.addActionListener(this);
        exit0.setMnemonic('E');
        exit0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        exit.add(exit0);

        if (aType.equals("Admin")) {
            menuBar.add(master);
        }
        else {
            menuBar.add(information);
            menuBar.add(user);
            menuBar.add(report);
        }

        menuBar.add(utility);
        menuBar.add(exit);




        add(img);

        setLayout(new FlowLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewCustomer) {
            new NewCustomer();
        }
        else if (e.getSource() == depositDetail) {
            new DepositDetails();
        }
        else if (e.getSource() == customerDetails) {
            new CustomerDetails();
        }
        else if (e.getSource() == calculateBill) {
            new CalculateBill();
        }
        else if (e.getSource() == viewInfo) {
            new ViewInformation(meter);
        }
        else if (e.getSource() == updateInfo) {
            new UpdateInformation(meter);
        }
        else if (e.getSource() == billDetail) {
            new BillDetails(meter);
        }
        else if (e.getSource() == notepad) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == calculator) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == exit0) {
            setVisible(false);
            new Login();
        }
        else if (e.getSource() == payBill) {
            new PayBill(meter);
        }
        else if (e.getSource() == generateBill) {
            new GenerateBill(meter);
        }
    }
}
