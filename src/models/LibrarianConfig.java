package models;

import javafx.scene.control.Alert;
import main.ConnectDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class LibrarianConfig {
    ConnectDb con;
    Statement stmt;

    public LibrarianConfig(ConnectDb con)
    {
        this.con = con;
    }





    public void createLibrarian(String name, String email, String password){
        java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        final String LIBRARIAN_CREATE = "INSERT INTO users ( NAME, CREATED_AT, EMAIL, PASSWORD, ROLE)" +
                " VALUES ('" + name + "' , ? ,'" + email + "' , '" + password + "', 'librarian')";
        try{

            stmt = con.conn.createStatement();
            PreparedStatement stmt = con.conn.prepareStatement(LIBRARIAN_CREATE);
            stmt.setDate(1, ourJavaDateObject);
            stmt.executeUpdate();
            stmt.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Librarian has been added successfully");
            alert.showAndWait();
            System.out.println("Librarian created");
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

    public void selectAllAdmins()
    {
        final String GET_ALL_LIBRARIANS ="SELECT * " +
                "FROM users WHERE ROLE = 'librarian'";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_ALL_LIBRARIANS);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String created_at = resultSet.getString("created_at");

                System.out.println("Librarian name: " +name+ "\n" + "Created at " + created_at);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void selectById(int id)
    {
        final String GET_LIBRARIAN ="SELECT * " +
                "FROM users WHERE ID = "+ id + " ";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_LIBRARIAN);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String created_at = resultSet.getString("created_at");

                System.out.println("Librarian name: " +name+ "\n" + "Created at " + created_at);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void deleteLibrarian(int id)
    {
        final String DELETE_LIBRARIAN = "DELETE " +
                "FROM users WHERE ID = "+ id + " AND ROLE = 'librarian'";
        try{
            PreparedStatement stmt  = con.conn.prepareStatement(DELETE_LIBRARIAN);
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Successfully deleted!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateLibrarian(int id, String name, String email, String password)
    {
        final String UPDATE_LIBRARIAN = "UPDATE users SET " +
                "name = '" + name + "'," +
                "email =  '" + email + "'," +
                "password = '" + password +"' "+
                "WHERE ID = " + id + " AND ROLE = 'librarian'";
        try{
            PreparedStatement stmt = con.conn.prepareStatement(UPDATE_LIBRARIAN);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Updated");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
