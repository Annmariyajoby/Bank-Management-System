package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin1,pin2;

    JButton change,back;

    String pin;

    PinChange(String pin){

        this.pin = pin;

        JLabel heading =
                new JLabel("CHANGE PIN");

        heading.setFont(
                new Font("Arial",
                        Font.BOLD,24));

        heading.setBounds(
                150,40,250,30);

        add(heading);

        JLabel np =
                new JLabel("New PIN:");

        np.setBounds(
                80,120,100,30);

        add(np);

        pin1 =
                new JPasswordField();

        pin1.setBounds(
                220,120,150,30);

        add(pin1);

        JLabel rp =
                new JLabel("Re-Enter PIN:");

        rp.setBounds(
                80,190,120,30);

        add(rp);

        pin2 =
                new JPasswordField();

        pin2.setBounds(
                220,190,150,30);

        add(pin2);

        change =
                new JButton("Change");

        change.setBounds(
                80,280,120,35);

        add(change);

        back =
                new JButton("Back");

        back.setBounds(
                250,280,120,35);

        add(back);

        change.addActionListener(this);
        back.addActionListener(this);

        setLayout(null);

        getContentPane()
                .setBackground(Color.WHITE);

        setSize(500,400);

        setLocation(400,150);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==back){

            dispose();

            new Transactions(pin);

            return;
        }

        String newPin =
                pin1.getText();

        String rePin =
                pin2.getText();

        if(!newPin.equals(rePin)){

            JOptionPane.showMessageDialog(
                    null,
                    "PIN does not match");

            return;
        }

        try{

            Conn c =
                    new Conn();

            String q1 =
                    "update signupthree set pin='"
                    +newPin+
                    "' where pin='"
                    +pin+"'";

            String q2 =
                    "update bank set pin='"
                    +newPin+
                    "' where pin='"
                    +pin+"'";

            c.s.executeUpdate(q1);
            c.s.executeUpdate(q2);

            JOptionPane.showMessageDialog(
                    null,
                    "PIN Changed Successfully");

            dispose();

            new login();

        }catch(Exception e){

            e.printStackTrace();
        }
    }
}