package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

class BalanceEnquiry extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String cardno;

    BalanceEnquiry(String cardno) {
        this.cardno = cardno;

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
        

//      logic for getting balance
        int balance = 0;
        try
        {
            Conn c1 = new Conn();

            // getting balance
            ResultSet rs = c1.s.executeQuery("select balance from login where cardno = '"+cardno+"'");
            if(rs.next())
            {
                balance = Integer.parseInt(rs.getString("balance"));
            }

            // printing your balance
            l1.setText("Your Current Account Balance is Rs "+balance);
        }


        catch(Exception e){}
        
        

        b1.addActionListener(this);

        setSize(960, 1080);
//        setUndecorated(true);
        setLocation(500, 0);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(cardno).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}