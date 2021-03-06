
package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    String cardno;
    Deposit(String cardno){
        this.cardno = cardno;

        JLabel l3 = new JLabel();
        l3.setBounds(0, 0, 960, 1080);
        add(l3);
        
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
//        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");
        
        setLayout(null);
        
        l1.setBounds(190,350,400,35);
        l3.add(l1);
        
        t1.setBounds(190,420,320,25);
        l3.add(t1);
        
        b1.setBounds(390,588,150,35);
        l3.add(b1);
        
        b2.setBounds(390,633,150,35);
        l3.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setSize(850,750);
//        setUndecorated(true);
        setLocation(500,0);
        setVisible(true);
        setDefaultCloseOperation(3);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String amount = t1.getText();
            Date date = new Date();

            if(ae.getSource()==b1){
                if(t1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                }

                else
                {
                    Conn c1 = new Conn();
                    
                    // insert the transaction details to bank table
                    c1.s.executeUpdate("insert into bank values('"+cardno+"', '"+date+"', 'Deposit', '"+amount+"')");

                    // update query for balance in login table
                    c1.s.executeUpdate("update login set balance = balance + "+amount+" where cardno = '"+cardno+"'");

                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Deposited Successfully");
                    setVisible(false);
                    new Transactions(cardno).setVisible(true);   
                }


            }
            else if(ae.getSource()==b2)
            {
                setVisible(false);
                new Transactions(cardno).setVisible(true);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            
    }
    
    public static void main(String[] args){
        new Deposit("").setVisible(true);
    }
}