package models;


import javafx.scene.control.Alert;
import main.ConnectDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class StudentConfig implements Person{
    ConnectDb con;
    Statement stmt;

    public StudentConfig(ConnectDb con)
    {
        this.con = con;
    }



    public void create(String name, String email, String password){
        java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        final String STUDENT_CREATE = "INSERT INTO users ( NAME, CREATED_AT, EMAIL, PASSWORD, ROLE)" +
                " VALUES ('" + name + "' , ? ,'" + email + "' , '" + password + "', 'student')";
        try{
            stmt = con.conn.createStatement();
            PreparedStatement stmt = con.conn.prepareStatement(STUDENT_CREATE);
            stmt.setDate(1, ourJavaDateObject);
            stmt.executeUpdate();
            stmt.close();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText(null);
//            alert.setContentText("Student has been added successfully");
//            alert.showAndWait();
//            System.out.println("Student created");
//            return;
        }catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("You have typed something wrong");
            alert.showAndWait();
            return;
        }
    }


    public ResultSet selectAll()
    {
        final String GET_ALL_STUDENT ="SELECT * " +
                "FROM users WHERE ROLE = 'student'";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_ALL_STUDENT);
            return resultSet;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    public ResultSet selectById(int id)
    {
        final String GET_STUDENT ="SELECT * " +
                "FROM users WHERE ID = "+ id + " AND ROLE = 'student' ";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_STUDENT);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String created_at = resultSet.getString("created_at");
                System.out.println("Student name: " +name+ "\n" + "Created at " + created_at);
            }


            return resultSet;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int id)
    {
        final String DELETE_STUDENT = "DELETE " +
                "FROM users WHERE ID = "+ id + " AND ROLE = 'student'";
        try{
            PreparedStatement stmt  = con.conn.prepareStatement(DELETE_STUDENT);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Successfully deleted!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void update(int id, String name, String email, String password)
    {
        final String UPDATE_STUDENT = "UPDATE users SET " +
                "name = '" + name + "'," +
                "email =  '" + email + "'," +
                "password = '" + password +"' "+
            "WHERE ID = " + id + " AND ROLE = 'student'";
        try{
            PreparedStatement stmt = con.conn.prepareStatement(UPDATE_STUDENT);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Updated");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean checkIfExist(String email)
    {
        final String GET_STUDENT ="SELECT * " +
                "FROM users WHERE EMAIL = '" + email + "' AND ROLE = 'student' ";
        try{
        stmt = con.conn.createStatement();
        ResultSet resultSet;
        resultSet = stmt.executeQuery(GET_STUDENT);

        while (resultSet.next()) {
            String email1 = resultSet.getString("email");
            if (email.equals(email1))
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
