package models;

import main.ConnectDb;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class BorrowedBooks {
    Statement stmt;
    ConnectDb con;

    public BorrowedBooks(ConnectDb con){
        this.con = con;
    }
    public boolean create(String email, int isbn, int days)
    {

        java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        final String CREATE = "INSERT INTO borrowedBooks ( student_email, TAKEN_AT, isbn, days)" +
                " VALUES ('" + email + "', ? , " + isbn + " , " + days + ")";
        try{

            stmt = con.conn.createStatement();
            PreparedStatement stmt = con.conn.prepareStatement(CREATE);
            stmt.setDate(1, ourJavaDateObject);
            stmt.executeUpdate();
            System.out.println("Borrowed book created");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
