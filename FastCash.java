package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton b100,b500,b1000,b2000,b5000,b10000,back;

    String pin;

    FastCash(String pin){

        this.pin = pin;

        JLabel heading =
                new JLabel("FAST CASH");

        heading.setFont(
                new Font("Arial",
                        Font.BOLD,24));

        heading.setBounds(
                180,50,250,30);

        add(heading);

        b100 = new JButton("100");
        b500 = new JButton("500");
        b1000 = new JButton("1000");
        b2000 = new JButton("2000");
        b5000 = new JButton("5000");
        b10000 = new JButton("10000");
        back = new JButton("Back");

        b100.setBounds(80,120,120,35);
        b500.setBounds(250,120,120,35);

        b1000.setBounds(80,190,120,35);
        b2000.setBounds(250,190,120,35);

        b5000.setBounds(80,260,120,35);
        b10000.setBounds(250,260,120,35);

        back.setBounds(160,330,120,35);

        add(b100);
        add(b500);
        add(b1000);
        add(b2000);
        add(b5000);
        add(b10000);
        add(back);

        b100.addActionListener(this);
        b500.addActionListener(this);
        b1000.addActionListener(this);
        b2000.addActionListener(this);
        b5000.addActionListener(this);
        b10000.addActionListener(this);
        back.addActionListener(this);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        setSize(500,450);

        setLocation(400,120);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==back){

            dispose();

            new Transactions(pin);

            return;
        }

        String amount =
                ((JButton)ae.getSource()).getText();

        try{

            int balance = 0;

            Conn c = new Conn();

            ResultSet rs =
                    c.s.executeQuery(
                            "select * from bank where pin='"
                                    + pin + "'");

            while(rs.next()){

                if(rs.getString("type")
                        .equals("Deposit")){

                    balance += Integer.parseInt(
                            rs.getString("amount"));

                }else{

                    balance -= Integer.parseInt(
                            rs.getString("amount"));
                }
            }

            if(balance < Integer.parseInt(amount)){

                JOptionPane.showMessageDialog(
                        null,
                        "Insufficient Balance");

                return;
            }

            Date date = new Date();

            String query =
                    "insert into bank values('"
                            + pin + "','"
                            + date + "','Withdrawal','"
                            + amount + "')";

            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(
                    null,
                    "Rs. "+amount+
                    " Withdrawn Successfully");

            dispose();

            new Transactions(pin);

        }catch(Exception e){

            e.printStackTrace();
        }
    }
}