
package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pin extends JFrame implements ActionListener{
    
    JPasswordField t1,t2,t0;
    JButton b1,b2;                               
    JLabel l1,l2,l3,l0;
    String cardno;
    Pin(String cardno){
        this.cardno = cardno;
       
        JLabel l4 = new JLabel();
        l4.setBounds(0, 0, 960, 1080);
        add(l4);
        
        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 16));
//      l1.setForeground(Color.WHITE);


        // Old pin details
        l0 = new JLabel("Old PIN:");
        l0.setFont(new Font("System", Font.BOLD, 16));
//      l0.setForeground(Color.WHITE);



        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));
//      l2.setForeground(Color.WHITE);
        
        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));
//      l3.setForeground(Color.WHITE);
        
        t0 = new JPasswordField();
        t0.setFont(new Font("Raleway", Font.BOLD, 25));
        
        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        
        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));
        
        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setLayout(null);
        
        l1.setBounds(280,330,800,35);
        l4.add(l1);
        
        l2.setBounds(180,390,150,35);
        l4.add(l2);
        
        l3.setBounds(180,440,200,35);
        l4.add(l3);
        
        t1.setBounds(350,390,180,25);
        l4.add(t1);
        
        t2.setBounds(350,440,180,25);
        l4.add(t2);
        
        b1.setBounds(390,588,150,35);
        l4.add(b1);
        
        b2.setBounds(390,633,150,35);
        l4.add(b2);
        
        setSize(850,750);
        setLocation(500,0);
//        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(3);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String npin = t1.getText();
            String rpin = t2.getText();
            
            
            // checking if the entered pins are equal
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }

            if(ae.getSource()==b1){
                if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                }
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                }

                Conn c1 = new Conn();

                // updating the pin in the db
                String q2 = "update login set pin = '"+rpin+"' where cardno = '"+cardno+"' ";
                String q3 = "update signup3 set pin = '"+rpin+"' where cardno = '"+cardno+"' ";

                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(cardno).setVisible(true);
            
            }else if(ae.getSource()==b2){
                new Transactions(cardno).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Pin("").setVisible(true);
    }
}