package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {

    JTextField cardText;
    JPasswordField pinText;

    JButton signin, clear, signup, forgotPin;

    login() {

        setTitle("Bank Management System");

        JLabel heading = new JLabel("WELCOME TO ATM");
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setBounds(180, 50, 400, 40);
        add(heading);

        JLabel card = new JLabel("Card No:");
        card.setFont(new Font("Arial", Font.BOLD, 20));
        card.setBounds(100, 150, 150, 30);
        add(card);

        cardText = new JTextField();
        cardText.setBounds(250, 150, 250, 30);
        add(cardText);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Arial", Font.BOLD, 20));
        pin.setBounds(100, 220, 150, 30);
        add(pin);

        pinText = new JPasswordField();
        pinText.setBounds(250, 220, 250, 30);
        add(pinText);

        signin = new JButton("SIGN IN");
        signin.setBounds(250, 300, 100, 30);
        signin.addActionListener(this);
        add(signin);

        clear = new JButton("CLEAR");
        clear.setBounds(400, 300, 100, 30);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(250, 350, 250, 30);
        signup.addActionListener(this);
        add(signup);

        forgotPin = new JButton("FORGOT PIN");
        forgotPin.setBounds(250, 400, 250, 30);
        forgotPin.addActionListener(this);
        add(forgotPin);

        setLayout(null);

        setSize(700, 550);
        setLocation(300, 100);

        getContentPane().setBackground(Color.WHITE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        try {

            if(ae.getSource() == signin) {

                String card =
                        cardText.getText().trim();

                String pin =
                        pinText.getText().trim();

                if(card.isEmpty() ||
                   pin.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please Enter Card Number and PIN");

                    return;
                }

                Conn c = new Conn();

                String query =
                        "select * from signupthree where cardnumber='"
                        + card +
                        "' and pin='"
                        + pin + "'";

                ResultSet rs =
                        c.s.executeQuery(query);

                if(rs.next()) {

                    setVisible(false);

                    new Transactions(pin);

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Incorrect Card Number or PIN");
                }

            }

            else if(ae.getSource() == clear) {

                cardText.setText("");
                pinText.setText("");
            }

            else if(ae.getSource() == signup) {

                setVisible(false);

                new signup();
            }

            else if(ae.getSource() == forgotPin) {

                String card =
                        JOptionPane.showInputDialog(
                                null,
                                "Enter Card Number");

                String email =
                        JOptionPane.showInputDialog(
                                null,
                                "Enter Registered Email");

                if(card == null ||
                   email == null)
                    return;

                Conn c = new Conn();

                String query =
                        "select s.email, st.pin " +
                        "from signup s " +
                        "join signupthree st " +
                        "on s.formno = st.formno " +
                        "where st.cardnumber='"
                        + card + "'";

                ResultSet rs =
                        c.s.executeQuery(query);

                if(rs.next()) {

                    if(email.equalsIgnoreCase(
                            rs.getString("email"))) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Your PIN is : "
                                + rs.getString("pin"));

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "Email does not match");
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Card Number not found");
                }
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new login();
    }
}