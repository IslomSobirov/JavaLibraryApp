package models;


import javafx.scene.control.Alert;
import main.ConnectDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class StudentConfig {
    ConnectDb con;
    Statement stmt;

    public StudentConfig(ConnectDb con)
    {
        this.con = con;
    }



    public void createStudent(String name, String email, String password){
        java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        final String STUDENT_CREATE = "INSERT INTO users ( NAME, CREATED_AT, EMAIL, PASSWORD, ROLE)" +
                " VALUES ('" + name + "' , ? ,'" + email + "' , '" + password + "', 'student')";
        try{

            stmt = con.conn.createStatement();
            PreparedStatement stmt = con.conn.prepareStatement(STUDENT_CREATE);
            stmt.setDate(1, ourJavaDateObject);
            stmt.executeUpdate();
            stmt.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Student has been added successfully");
            alert.showAndWait();
            System.out.println("Student created");
            return;
        }catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("You have typed something wrong");
            alert.showAndWait();
            return;
        }
    }


    public void selectAllStudents()
    {
        final String GET_ALL_STUDENT ="SELECT * " +
                "FROM users WHERE ROLE = 'student'";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_ALL_STUDENT);
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String created_at = resultSet.getString("created_at");
//                String id = resultSet.getString("id");
//
//                System.out.println("Student name: " +name+ "\n" + "Created at " + created_at + "ID: " + id);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
//                String created_at = resultSet.getString("created_at");
                String email1 = resultSet.getString("email");
                int id = resultSet.getInt("id");


                System.out.println("Student name: " +name+ "\n" + "Email " + email1 + " Id: " + id);

            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void selectById(int id)
    {
        final String GET_STUDENT ="SELECT * " +
                "FROM users WHERE ID = "+ id + " ";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_STUDENT);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String created_at = resultSet.getString("created_at");


                System.out.println("Student name: " +name+ "\n" + "Created at " + created_at);
            }
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id)
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


    public void updateStudent(int id, String name, String email, String password)
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
