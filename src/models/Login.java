package models;

import main.ConnectDb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {

    ConnectDb con;
    Statement stmt;
    public Login(ConnectDb con){
        this.con = con;
    }

    public boolean login(String role, String email, String password){
        final String LOGIN ="SELECT * " +
                "FROM users WHERE EMAIL = '" + email + "' AND ROLE = '"+ role + "' AND PASSWORD = '" + password + "' ";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(LOGIN);


            while (resultSet.next()) {
                String email1 = resultSet.getString("email");
                String role1 = resultSet.getString("role");
                String password1 = resultSet.getString("password");

                System.out.println(email1);
                System.out.println(role1);
                System.out.println(password1);
                if (email.equals(email1) && role.equals(role1) && password.equals(password1))
                {
                    stmt.close();
                    return true;
                }
            }
            stmt.close();
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return true;
        }
    }
}
