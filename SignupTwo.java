package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    JComboBox religion, category, income, education, occupation;
    JTextField panText, aadharText;
    JRadioButton syes, sno, eyes, eno;
    JButton next;

    String formno;

    SignupTwo(String formno) {

        this.formno = formno;

        setTitle("APPLICATION FORM - PAGE 2");

        JLabel heading = new JLabel("Page 2 : Additional Details");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(220,40,350,30);
        add(heading);

        JLabel rel = new JLabel("Religion:");
        rel.setBounds(100,100,150,30);
        add(rel);

        String religions[] = {
                "Christian","Muslim","Hindu",
                "Sikh","Other"
        };

        religion = new JComboBox(religions);
        religion.setBounds(300,100,200,30);
        add(religion);

        JLabel cat = new JLabel("Category:");
        cat.setBounds(100,150,150,30);
        add(cat);

        String categories[] = {
                "General","OBC","SC","ST","Other"
        };

        category = new JComboBox(categories);
        category.setBounds(300,150,200,30);
        add(category);

        JLabel inc = new JLabel("Income:");
        inc.setBounds(100,200,150,30);
        add(inc);

        String incomes[] = {
                "< 1 Lakh",
                "1-5 Lakh",
                "5-10 Lakh",
                "> 10 Lakh"
        };

        income = new JComboBox(incomes);
        income.setBounds(300,200,200,30);
        add(income);

        JLabel edu = new JLabel("Education:");
        edu.setBounds(100,250,150,30);
        add(edu);

        String educations[] = {
                "Graduate",
                "Post Graduate",
                "Diploma",
                "Other"
        };

        education = new JComboBox(educations);
        education.setBounds(300,250,200,30);
        add(education);

        JLabel occ = new JLabel("Occupation:");
        occ.setBounds(100,300,150,30);
        add(occ);

        String occupations[] = {
                "Student",
                "Business",
                "Salaried",
                "Self Employed"
        };

        occupation = new JComboBox(occupations);
        occupation.setBounds(300,300,200,30);
        add(occupation);

        JLabel pan = new JLabel("PAN Number:");
        pan.setBounds(100,350,150,30);
        add(pan);

        panText = new JTextField();
        panText.setBounds(300,350,200,30);
        add(panText);

        JLabel aadhar = new JLabel("Aadhar Number:");
        aadhar.setBounds(100,400,150,30);
        add(aadhar);

        aadharText = new JTextField();
        aadharText.setBounds(300,400,200,30);
        add(aadharText);

        JLabel senior = new JLabel("Senior Citizen:");
        senior.setBounds(100,450,150,30);
        add(senior);

        syes = new JRadioButton("Yes");
        sno = new JRadioButton("No");

        syes.setBounds(300,450,60,30);
        sno.setBounds(380,450,60,30);

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(syes);
        bg1.add(sno);

        add(syes);
        add(sno);

        JLabel exist = new JLabel("Existing Account:");
        exist.setBounds(100,500,150,30);
        add(exist);

        eyes = new JRadioButton("Yes");
        eno = new JRadioButton("No");

        eyes.setBounds(300,500,60,30);
        eno.setBounds(380,500,60,30);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(eyes);
        bg2.add(eno);

        add(eyes);
        add(eno);

        next = new JButton("NEXT");
        next.setBounds(450,570,100,35);
        next.addActionListener(this);
        add(next);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        setSize(700,700);
        setLocation(300,20);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {

    
    	String religionValue =
    	        (String) religion.getSelectedItem();

    	String categoryValue =
    	        (String) category.getSelectedItem();

    	String incomeValue =
    	        (String) income.getSelectedItem();

    	String educationValue =
    	        (String) education.getSelectedItem();

    	String occupationValue =
    	        (String) occupation.getSelectedItem();

    	String pan = panText.getText().trim();
    	String aadhar = aadharText.getText().trim();

    	String senior = "";
    	if(syes.isSelected())
    	    senior = "Yes";
    	else if(sno.isSelected())
    	    senior = "No";

    	String existing = "";
    	if(eyes.isSelected())
    	    existing = "Yes";
    	else if(eno.isSelected())
    	    existing = "No";


    	if(pan.isEmpty() || aadhar.isEmpty()) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "PAN and Aadhaar are required");

    	    return;
    	}

    	if(!pan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Invalid PAN Number\nExample: ABCDE1234F");

    	    return;
    	}

    	if(!aadhar.matches("\\d{12}")) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Aadhaar Number must be 12 digits");

    	    return;
    	}

    	if(senior.equals("")) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Please Select Senior Citizen Option");

    	    return;
    	}

    	if(existing.equals("")) {

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Please Select Existing Account Option");

    	    return;
    	}

    	try {

    	    Conn c = new Conn();

    	    String query =
    	    "insert into signuptwo values('"+
    	    formno+"','"+
    	    religionValue+"','"+
    	    categoryValue+"','"+
    	    incomeValue+"','"+
    	    educationValue+"','"+
    	    occupationValue+"','"+
    	    pan+"','"+
    	    aadhar+"','"+
    	    senior+"','"+
    	    existing+"')";

    	    c.s.executeUpdate(query);

    	    JOptionPane.showMessageDialog(
    	            null,
    	            "Page 2 Saved Successfully");

    	    setVisible(false);

    	    new Signupthree(formno);

    	} catch(Exception e) {

    	    e.printStackTrace();
    	}
    }}
