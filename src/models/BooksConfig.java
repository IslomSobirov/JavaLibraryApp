package models;

import javafx.scene.control.Alert;
import main.ConnectDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BooksConfig {
    ConnectDb con;
    Statement stmt;

    public BooksConfig(ConnectDb con)
    {
        this.con = con;
    }


    public void create(String title, String subject, String author, int isbn, int amount, String published)
    {
        final String BOOK_CREATE = "INSERT INTO library ( title, subject, author, ISBN, AMOUNT, publish_date)" +
                " VALUES ('" + title + "', '" + subject + "' , '" + author + "', " + isbn + ", " + amount + ", '" + published + "')";
        try{
            PreparedStatement stmt = con.conn.prepareStatement(BOOK_CREATE);
            stmt.executeUpdate();
            stmt.close();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("Book is added");
//            alert.setContentText("Book has been created successfully");
//            alert.showAndWait();
//            System.out.println("Book is added!");
//            return;
        }catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ISBN has to be number date should be written ex: 2012-12-12");
            alert.showAndWait();
            return;
        }
    }


    public ResultSet selectAll()
    {
        final String GET_ALL_STUDENT ="SELECT * " +
                "FROM library";
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
                "FROM library WHERE ID = " + id + " ";
        try{
            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_STUDENT);
            return resultSet;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(int id)
    {
        final String DELETE_BOOK = "DELETE " +
                "FROM library WHERE ISBN = " + id +"";
        try{
            PreparedStatement stmt  = con.conn.prepareStatement(DELETE_BOOK);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Successfully deleted!");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    public void update(int id, String title, String subject, String author, int isbn, int amount, String publish_date)
    {
        final String UPDATE_STUDENT = "UPDATE library SET " +
                "title = '" + title + "'," +
                "subject =  '" + subject + "'," +
                "author = '" + author +"' ,"+
                "ISBN = " + isbn +" ,"+
                "AMOUNT =" + amount + ", " +
                "publish_date = '" + publish_date +"' "+
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
