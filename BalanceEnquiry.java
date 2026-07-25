package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pin;

    BalanceEnquiry(String pin){

        this.pin = pin;

        int balance = 0;

        try{

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

        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel text =
                new JLabel(
                        "Current Balance : Rs. " + balance);

        text.setFont(
                new Font("Arial",
                        Font.BOLD,22));

        text.setBounds(
                100,100,350,40);

        add(text);

        back =
                new JButton("Back");

        back.setBounds(
                180,220,120,35);

        back.addActionListener(this);

        add(back);

        setLayout(null);

        getContentPane()
                .setBackground(Color.WHITE);

        setSize(500,400);

        setLocation(400,150);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        setVisible(false);

        new Transactions(pin);
    }
}