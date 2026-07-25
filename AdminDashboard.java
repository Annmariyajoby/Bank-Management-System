package myproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
public class AdminDashboard extends JFrame implements ActionListener {

    JButton customers, transactions, logout,deleteAccount;

    AdminDashboard(){

        setTitle("Admin Dashboard");

        JLabel heading =
                new JLabel("WELCOME ADMIN");

        heading.setFont(
                new Font("Arial",
                Font.BOLD,26));

        heading.setBounds(
                100,40,300,40);

        add(heading);

        customers =
                new JButton("VIEW CUSTOMERS");

        customers.setBounds(
                120,120,220,40);

        customers.addActionListener(this);

        add(customers);

        transactions =
                new JButton("VIEW TRANSACTIONS");

        transactions.setBounds(
                120,190,220,40);

        transactions.addActionListener(this);

        add(transactions);

        logout =
                new JButton("LOGOUT");

        logout.setBounds(
                120,260,220,40);

        logout.addActionListener(this);

        add(logout);
        deleteAccount =
                new JButton("DELETE ACCOUNT");

        deleteAccount.setBounds(
                120,260,220,40);

        deleteAccount.addActionListener(this);

        add(deleteAccount);
        
        setLayout(null);
        customers.setBounds(120,120,220,40);

        transactions.setBounds(120,190,220,40);

        deleteAccount.setBounds(120,260,220,40);

        logout.setBounds(120,330,220,40);

        setSize(500,550);

        setSize(500,450);

        setLocation(400,150);

        getContentPane()
                .setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

    	if(ae.getSource()==customers){

    	    try{

    	        Conn c = new Conn();

    	        ResultSet rs =
    	        c.s.executeQuery(
    	        "select * from signup");

    	        String data = "";

    	        while(rs.next()){

    	            data +=
    	            "Form No: "
    	            + rs.getString("formno")
    	            + " | Name: "
    	            + rs.getString("name")
    	            + "\n";
    	        }

    	        JTextArea area =
    	                new JTextArea(data);

    	        JOptionPane.showMessageDialog(
    	                null,
    	                new JScrollPane(area),
    	                "Customers",
    	                JOptionPane.INFORMATION_MESSAGE);

    	    }catch(Exception e){

    	        e.printStackTrace();
    	    }
    	}
    	else if(ae.getSource()==transactions){

    	    try{

    	        Conn c = new Conn();

    	        ResultSet rs =
    	        c.s.executeQuery(
    	        "select * from bank");

    	        String data = "";

    	        while(rs.next()){

    	            data +=
    	            "PIN: "
    	            + rs.getString("pin")
    	            + " | "
    	            + rs.getString("type")
    	            + " | Rs."
    	            + rs.getString("amount")
    	            + "\n";
    	        }

    	        JTextArea area =
    	                new JTextArea(data);

    	        JOptionPane.showMessageDialog(
    	                null,
    	                new JScrollPane(area),
    	                "Transactions",
    	                JOptionPane.INFORMATION_MESSAGE);

    	    }catch(Exception e){

    	        e.printStackTrace();
    	    }
    	}    
       
    else if(ae.getSource()==deleteAccount){

        String formno =
                JOptionPane.showInputDialog(
                        null,
                        "Enter Form Number");

        if(formno == null ||
           formno.trim().isEmpty()){

            return;
        }

        try{

            Conn c = new Conn();

            c.s.executeUpdate(
            "delete from signup where formno='"
            + formno + "'");

            c.s.executeUpdate(
            "delete from signuptwo where formno='"
            + formno + "'");

            c.s.executeUpdate(
            "delete from signupthree where formno='"
            + formno + "'");

            JOptionPane.showMessageDialog(
                    null,
                    "Account Deleted Successfully");

        }catch(Exception e){

            e.printStackTrace();
        }
    }else if(ae.getSource()==logout){

        setVisible(false);
        new login();
    }}

    public static void main(String[] args){
  

        new AdminDashboard();
    }}