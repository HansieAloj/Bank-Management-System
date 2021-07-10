package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

class BalanceEnquiry extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

        JLabel l3 = new JLabel();
        l3.setBounds(0, 0, 960, 1080);
        add(l3);

        l1 = new JLabel();
//        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(190, 350, 400, 35);
        l3.add(l1);

        b1.setBounds(390, 633, 150, 35);
        l3.add(b1);
        
        // int balance = 0;
        // try{
        //     Conn c1 = new Conn();
        //     ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
        //     while (rs.next()) {
        //         if (rs.getString("mode").equals("Deposit")) {
        //             balance += Integer.parseInt(rs.getString("amount"));
        //         } else {
        //             balance -= Integer.parseInt(rs.getString("amount"));
        //         }
        //     }
        // }


        int balance;
        try
        {
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select balance from login where pin = '"+pin+"'");
            if(rs.next())
            {
                balance = Integer.parseInt(rs.getString("balance"));
            }
        }


        catch(Exception e){}
        
        l1.setText("Your Current Account Balance is Rs "+balance);

        b1.addActionListener(this);

        setSize(960, 1080);
//        setUndecorated(true);
        setLocation(500, 0);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}