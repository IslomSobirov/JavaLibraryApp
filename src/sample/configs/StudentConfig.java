package sample.configs;


import sample.ConnectDb;

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
            System.out.println("Student created");
        }catch (SQLException e){
            e.printStackTrace();
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
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String created_at = resultSet.getString("created_at");

                System.out.println("Student name: " +name+ "\n" + "Created at " + created_at);
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

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
