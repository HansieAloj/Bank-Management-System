package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String pin;

    FastCash(String pin) {
        this.pin = pin;

        JLabel l3 = new JLabel();
        l3.setBounds(0, 0, 960, 1080);
        add(l3);

        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
//        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        b7 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(235, 400, 700, 35);
        l3.add(l1);

        b1.setBounds(170, 499, 150, 35);
        l3.add(b1);

        b2.setBounds(390, 499, 150, 35);
        l3.add(b2);

        b3.setBounds(170, 543, 150, 35);
        l3.add(b3);

        b4.setBounds(390, 543, 150, 35);
        l3.add(b4);

        b5.setBounds(170, 588, 150, 35);
        l3.add(b5);

        b6.setBounds(390, 588, 150, 35);
        l3.add(b6);

        b7.setBounds(390, 633, 150, 35);
        l3.add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(850, 750);
        setLocation(500, 0);
//        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(3);

    }

    public void actionPerformed(ActionEvent ae) {

    	int balance=0;
        try 
        {
            String amount = ((JButton)ae.getSource()).getText().substring(3); //k
            Conn c1 = new Conn();

            // ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pin+"'");
            // while (rs.next()) {
            //     if (rs.getString("mode").equals("Deposit")) {
            //         balance += Integer.parseInt(rs.getString("amount"));
            //     } else {
            //         balance -= Integer.parseInt(rs.getString("amount"));
            //     }



            // } String num = "17";


            ResultSet rs = c1.s.executeQuery("select balance from login where pin = '"+pin+"'");
            if(rs.next())
            {
                balance = Integer.parseInt(rs.getString("balance"));
                System.out.println("Running");
            }



            if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) 
            {
                JOptionPane.showMessageDialog(null, "Insuffient Balance"+amount+balance);
                return;
            }

            if (ae.getSource() == b7) 
            {
                this.setVisible(false);
                new Transactions(pin).setVisible(true);
            }
            else
            {
                Date date = new Date();
                c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                c1.s.executeUpdate("update login set balance = balance - "+amount+" where pin = '"+pin+"'");
                
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}