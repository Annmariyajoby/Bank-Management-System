package myproject;

import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    String pin;

    MiniStatement(String pin){

        this.pin = pin;

        JTextArea area = new JTextArea();
        area.setBounds(20,20,450,300);

        try{

            Conn c = new Conn();

            ResultSet rs =
                    c.s.executeQuery(
                    "select * from bank where pin='"
                    + pin + "'");

            while(rs.next()){

                area.append(
                        rs.getString("date")
                        + "    "
                        + rs.getString("type")
                        + "    Rs."
                        + rs.getString("amount")
                        + "\n");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        add(area);

        setLayout(null);
        setSize(500,400);
        setLocation(400,150);
        setVisible(true);
    }
}