package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class signup extends JFrame implements ActionListener {

    JTextField nameText, fnameText, emailText,
            addressText, cityText, pinText, stateText;

    JRadioButton male, female, married, unmarried;

    JButton next;

    String formno;

    signup() {

        setTitle("NEW ACCOUNT APPLICATION FORM");

        Random random = new Random();
        formno = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

        JLabel form = new JLabel("APPLICATION FORM NO. " + formno);
        form.setFont(new Font("Arial", Font.BOLD, 28));
        form.setBounds(140, 20, 600, 40);
        add(form);

        JLabel page = new JLabel("Page 1 : Personal Details");
        page.setFont(new Font("Arial", Font.BOLD, 20));
        page.setBounds(250, 70, 300, 30);
        add(page);

        JLabel name = new JLabel("Name:");
        name.setBounds(100, 130, 150, 30);
        add(name);

        nameText = new JTextField();
        nameText.setBounds(300,130,300,30);
        add(nameText);

        JLabel fname = new JLabel("Father's Name:");
        fname.setBounds(100,180,150,30);
        add(fname);

        fnameText = new JTextField();
        fnameText.setBounds(300,180,300,30);
        add(fnameText);

        JLabel gender = new JLabel("Gender:");
        gender.setBounds(100,230,150,30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300,230,100,30);

        female = new JRadioButton("Female");
        female.setBounds(420,230,100,30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        add(male);
        add(female);

        JLabel email = new JLabel("Email:");
        email.setBounds(100,280,150,30);
        add(email);

        emailText = new JTextField();
        emailText.setBounds(300,280,300,30);
        add(emailText);

        JLabel marital = new JLabel("Marital Status:");
        marital.setBounds(100,330,150,30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(300,330,100,30);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(420,330,120,30);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(married);
        bg2.add(unmarried);

        add(married);
        add(unmarried);

        JLabel address = new JLabel("Address:");
        address.setBounds(100,380,150,30);
        add(address);

        addressText = new JTextField();
        addressText.setBounds(300,380,300,30);
        add(addressText);

        JLabel city = new JLabel("City:");
        city.setBounds(100,430,150,30);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(300,430,300,30);
        add(cityText);

        JLabel pin = new JLabel("Pin Code:");
        pin.setBounds(100,480,150,30);
        add(pin);

        pinText = new JTextField();
        pinText.setBounds(300,480,300,30);
        add(pinText);

        JLabel state = new JLabel("State:");
        state.setBounds(100,530,150,30);
        add(state);

        stateText = new JTextField();
        stateText.setBounds(300,530,300,30);
        add(stateText);

        next = new JButton("NEXT");
        next.setBounds(500,600,100,35);
        next.addActionListener(this);
        add(next);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        setSize(800,700);
        setLocation(250,20);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {

    	
    	String name = nameText.getText().trim();
    	String fname = fnameText.getText().trim();

    	String gender = "";
    	if(male.isSelected())
    	    gender = "Male";
    	else if(female.isSelected())
    	    gender = "Female";

    	String email = emailText.getText().trim();

    	String marital = "";
    	if(married.isSelected())
    	    marital = "Married";
    	else if(unmarried.isSelected())
    	    marital = "Unmarried";

    	String address = addressText.getText().trim();
    	String city = cityText.getText().trim();
    	String pincode = pinText.getText().trim();
    	String state = stateText.getText().trim();

    	// Validation

    	if(name.isEmpty() ||
    	   fname.isEmpty() ||
    	   email.isEmpty() ||
    	   address.isEmpty() ||
    	   city.isEmpty() ||
    	   pincode.isEmpty() ||
    	   state.isEmpty()) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "All Fields Are Required");

    	    return;
    	}

    	if(gender.equals("")) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Please Select Gender");

    	    return;
    	}

    	if(marital.equals("")) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Please Select Marital Status");

    	    return;
    	}

    	if(!email.matches(
    	        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Invalid Email Address");

    	    return;
    	}

    	if(!pincode.matches("\\d{6}")) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Pin Code Must Be 6 Digits");

    	    return;
    	}

    	try {

    	    Conn c = new Conn();

    	    String query =
    	            "insert into signup values('"+
    	                    formno+"','"+
    	                    name+"','"+
    	                    fname+"','DOB','"+
    	                    gender+"','"+
    	                    email+"','"+
    	                    marital+"','"+
    	                    address+"','"+
    	                    city+"','"+
    	                    pincode+"','"+
    	                    state+"')";

    	    c.s.executeUpdate(query);

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Page 1 Saved Successfully");

    	    setVisible(false);

    	    new SignupTwo(formno);

    	} catch(Exception e) {

    	    e.printStackTrace();
    	}
    

    	}

    public static void main(String[] args) {
        new signup();
    }
}