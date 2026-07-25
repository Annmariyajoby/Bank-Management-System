package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Signupthree extends JFrame implements ActionListener {

    JRadioButton saving,current,fixed,recurring;

    JCheckBox atmCard,internet,mobile,
            email,cheque,statement;

    JButton submit,cancel;

    String formno;

    Signupthree(String formno){

        this.formno=formno;

        setTitle("Account Details");

        JLabel heading=
                new JLabel("Page 3 : Account Details");

        heading.setFont(
                new Font("Arial",
                        Font.BOLD,24));

        heading.setBounds(180,40,400,30);

        add(heading);

        JLabel account=
                new JLabel("Account Type:");

        account.setBounds(80,120,150,30);

        add(account);

        saving=
                new JRadioButton("Saving Account");

        saving.setBounds(250,120,150,30);

        add(saving);

        current=
                new JRadioButton("Current Account");

        current.setBounds(450,120,180,30);

        add(current);

        fixed=
                new JRadioButton("Fixed Deposit");

        fixed.setBounds(250,170,180,30);

        add(fixed);

        recurring=
                new JRadioButton("Recurring Deposit");

        recurring.setBounds(450,170,180,30);

        add(recurring);

        ButtonGroup bg=
                new ButtonGroup();

        bg.add(saving);
        bg.add(current);
        bg.add(fixed);
        bg.add(recurring);

        JLabel services=
                new JLabel("Services Required:");

        services.setBounds(80,260,200,30);

        add(services);

        atmCard=
                new JCheckBox("ATM CARD");

        atmCard.setBounds(250,260,150,30);

        add(atmCard);

        internet=
                new JCheckBox("Internet Banking");

        internet.setBounds(450,260,180,30);

        add(internet);

        mobile=
                new JCheckBox("Mobile Banking");

        mobile.setBounds(250,310,180,30);

        add(mobile);

        email=
                new JCheckBox("Email Alerts");

        email.setBounds(450,310,180,30);

        add(email);

        cheque=
                new JCheckBox("Cheque Book");

        cheque.setBounds(250,360,180,30);

        add(cheque);

        statement=
                new JCheckBox("E-Statement");

        statement.setBounds(450,360,180,30);

        add(statement);

        submit=
                new JButton("SUBMIT");

        submit.setBounds(250,500,120,35);

        submit.addActionListener(this);

        add(submit);

        cancel=
                new JButton("CANCEL");

        cancel.setBounds(420,500,120,35);

        add(cancel);

        setLayout(null);

        getContentPane()
                .setBackground(Color.WHITE);

        setSize(800,650);

        setLocation(250,30);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        String accountType = "";

        if(saving.isSelected())
            accountType = "Saving";

        else if(current.isSelected())
            accountType = "Current";

        else if(fixed.isSelected())
            accountType = "Fixed Deposit";

        else if(recurring.isSelected())
            accountType = "Recurring Deposit";

        String facility = "";

        if(atmCard.isSelected())
            facility += " ATM Card";

        if(internet.isSelected())
            facility += " Internet Banking";

        if(mobile.isSelected())
            facility += " Mobile Banking";

        if(email.isSelected())
            facility += " Email Alerts";

        if(cheque.isSelected())
            facility += " Cheque Book";

        if(statement.isSelected())
            facility += " E-Statement";

        // Validation

        if(accountType.equals("")){

            JOptionPane.showMessageDialog(
                    null,
                    "Please Select Account Type");

            return;
        }

        if(facility.trim().equals("")){

            JOptionPane.showMessageDialog(
                    null,
                    "Please Select At Least One Service");

            return;
        }

        try{

            Random random = new Random();

            // Better Card Number
            String cardNumber =
                    "5040" +
                    (10000000 +
                    random.nextInt(90000000));

            // Better PIN
            String pin =
                    String.valueOf(
                    1000 +
                    random.nextInt(9000));

            Conn c = new Conn();

            String query =
            "insert into signupthree values('"+
            formno+"','"+
            accountType+"','"+
            cardNumber+"','"+
            pin+"','"+
            facility+"')";

            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(
                    null,
                    "ACCOUNT CREATED SUCCESSFULLY\n\n" +
                    "Card Number : " + cardNumber +
                    "\nPIN : " + pin +
                    "\n\nPlease save these details.");

            setVisible(false);

            new login();

        }catch(Exception e){

            e.printStackTrace();
        }
    }}