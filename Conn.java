package myproject;
import java.sql.*;

public class Conn {

    Connection c;
    Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankmanagementsystem",
                "root",
                "your_password"
                );

            s = c.createStatement();

            System.out.println("Connected Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}