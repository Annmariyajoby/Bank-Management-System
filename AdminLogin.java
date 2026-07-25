package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class AdminLogin extends JFrame implements ActionListener {

    JTextField usernameText;
    JPasswordField passwordText;
    JButton signin, clear, signup, forgotPin, adminLogin;
    JButton login, back;
    AdminLogin(){

        setTitle("Admin Login");

        JLabel heading =
                new JLabel("ADMIN LOGIN");

        heading.setFont(
                new Font("Arial",
                Font.BOLD,24));

        heading.setBounds(
                150,30,250,40);

        add(heading);

        JLabel user =
                new JLabel("Username:");

        user.setBounds(
                70,100,100,30);

        add(user);

        usernameText =
                new JTextField();

        usernameText.setBounds(
                180,100,180,30);

        add(usernameText);

        JLabel pass =
                new JLabel("Password:");

        pass.setBounds(
                70,160,100,30);

        add(pass);

        passwordText =
                new JPasswordField();

        passwordText.setBounds(
                180,160,180,30);

        add(passwordText);

        login =
                new JButton("LOGIN");

        login.setBounds(
                100,240,100,35);

        login.addActionListener(this);

        add(login);

        back =
                new JButton("BACK");

        back.setBounds(
                240,240,100,35);

        back.addActionListener(this);

        add(back);

        setLayout(null);

        setSize(450,380);

        setLocation(450,150);

        getContentPane()
                .setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==login){

            try{

                String username =
                        usernameText.getText();

                String password =
                        passwordText.getText();

                Conn c =
                        new Conn();

                String query =
                "select * from admin " +
                "where username='"
                + username +
                "' and password='"
                + password + "'";

                ResultSet rs =
                        c.s.executeQuery(query);

                if(rs.next()){

                    JOptionPane.showMessageDialog(
                            null,
                            "Admin Login Successful");

                    setVisible(false);

                    new AdminDashboard();

                }
                else if(ae.getSource() == adminLogin){

                    setVisible(false);

                    new AdminLogin();
                }else{

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid Username or Password");
                }

            }catch(Exception e){

                e.printStackTrace();
            }
        }

     else if(ae.getSource()==back){

            setVisible(false);

            new login();
        }
    }

    public static void main(String[] args){

        new AdminLogin();
    }
}