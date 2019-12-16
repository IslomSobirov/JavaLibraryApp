package sample.configs;

import sample.ConnectDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class LibraryConfig {
    ConnectDb con;
    Statement stmt;

    public LibraryConfig(ConnectDb con)
    {
        this.con = con;
    }


    public void createLibrary(String title, String subject, String author, int isbn, String publish_date)
    {
        java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        final String STUDENT_CREATE = "INSERT INTO library ( title, subject, author, ISBN, publish_date)" +
                " VALUES ('" + title + "', '" + subject + "' , '" + author + "', " + isbn + ",'" + publish_date + "')";
        try{


            PreparedStatement stmt = con.conn.prepareStatement(STUDENT_CREATE);

            stmt.executeUpdate();
            System.out.println("Book is added!");
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
                String id = resultSet.getString("id");

                System.out.println("Student name: " +name+ "\n" + "Created at " + created_at + "ID: " + id);
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
}
