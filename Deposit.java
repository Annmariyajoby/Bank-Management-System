package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField amountField;
    JButton deposit, back;

    String pin;

    Deposit(String pin){

        this.pin = pin;

        JLabel heading =
                new JLabel("Deposit Amount");

        heading.setBounds(180,50,300,30);

        heading.setFont(
                new Font("Arial",
                        Font.BOLD,24));

        add(heading);

        amountField =
                new JTextField();

        amountField.setBounds(
                150,150,200,30);

        add(amountField);

        deposit =
                new JButton("Deposit");

        deposit.setBounds(
                100,250,120,35);

        deposit.addActionListener(this);

        add(deposit);

        back =
                new JButton("Back");

        back.setBounds(
                250,250,120,35);

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

        if(ae.getSource()==deposit){

            String amount =
                    amountField.getText();

            Date date =
                    new Date();

            try{

                Conn c =
                        new Conn();

                String query =
                        "insert into bank values('"
                        +pin+"','"
                        +date+"','Deposit','"
                        +amount+"')";

                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(
                        null,
                        "Rs. "+amount+
                                " Deposited Successfully");

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