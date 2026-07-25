package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {

    JTextField amountField;
    JButton withdraw, back;

    String pin;

    Withdrawal(String pin){

        this.pin = pin;

        JLabel heading =
                new JLabel("Withdraw Amount");

        heading.setFont(
                new Font("Arial",
                        Font.BOLD,24));

        heading.setBounds(150,50,300,30);

        add(heading);

        amountField =
                new JTextField();

        amountField.setBounds(
                150,150,200,30);

        add(amountField);

        withdraw =
                new JButton("Withdraw");

        withdraw.setBounds(
                100,250,120,35);

        withdraw.addActionListener(this);

        add(withdraw);

        back =
                new JButton("Back");

        back.setBounds(
                250,250,120,35);

        back.addActionListener(this);

        add(back);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        setSize(500,400);

        setLocation(400,150);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==withdraw){

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

                int amount =
                        Integer.parseInt(
                                amountField.getText());

                if(balance < amount){

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
                        "Rs. " + amount +
                                " Withdrawn Successfully");

                setVisible(false);

                new Transactions(pin);

            }catch(Exception e){

                e.printStackTrace();
            }
        }

        else{

            setVisible(false);

            new Transactions(pin);
        }
    }
}