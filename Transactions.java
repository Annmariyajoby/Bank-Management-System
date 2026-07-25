package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit, withdraw,
            fastcash, ministatement,
            pinchange, balance, exit;

    String pin;

    Transactions(String pin){

        this.pin = pin;

        setTitle("ATM Transactions");

        JLabel heading = new JLabel("ATM MACHINE");
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setBounds(220, 40, 300, 40);
        add(heading);

        deposit = new JButton("Deposit");
        deposit.setBounds(100,150,150,40);
        add(deposit);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(350,150,150,40);
        add(withdraw);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(100,250,150,40);
        add(fastcash);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(350,250,150,40);
        add(ministatement);

        pinchange = new JButton("PIN Change");
        pinchange.setBounds(100,350,150,40);
        add(pinchange);

        balance = new JButton("Balance Enquiry");
        balance.setBounds(350,350,150,40);
        add(balance);

        exit = new JButton("Exit");
        exit.setBounds(225,450,150,40);
        add(exit);

        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        fastcash.addActionListener(this);
        ministatement.addActionListener(this);
        pinchange.addActionListener(this);
        balance.addActionListener(this);
        exit.addActionListener(this);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        setSize(650,600);
        setLocation(300,50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == deposit){

            setVisible(false);
            new Deposit(pin);

        }

        else if(ae.getSource() == withdraw){

            setVisible(false);
            new Withdrawal(pin);

        }

        else if(ae.getSource() == fastcash){

            setVisible(false);
            new FastCash(pin);

        }

        else if(ae.getSource() == ministatement){

            new MiniStatement(pin);

        }

        else if(ae.getSource() == pinchange){

            setVisible(false);
            new PinChange(pin);

        }

        else if(ae.getSource() == balance){

            setVisible(false);
            new BalanceEnquiry(pin);

        }

        else if(ae.getSource() == exit){

            System.exit(0);

        }
    }

    public static void main(String[] args){

        new Transactions("1234");

    }
}