package main;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.BooksConfig;


public class Controller {

    private Main main;
    public void setMain(Main main){
        this.main = main;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBook;

    @FXML
    private TextField bookAuthor;

    @FXML
    private TextField bookSubject;

    @FXML
    private TextField bookTitle;

    @FXML
    private Button cancel;

    @FXML
    private TextField isbn;

    @FXML
    private TextField publish_date;


    @FXML
    void addBook(ActionEvent event) {

        ConnectDb con = new ConnectDb();
        con.connect();
        BooksConfig book = new BooksConfig(con);
        String title = bookTitle.getText();
        String subject = bookSubject.getText();
        String author = bookAuthor.getText();
        String ISBN = isbn.getText();
        String published = publish_date.getText();
        if (title.isEmpty() || subject.isEmpty() || author.isEmpty() || ISBN.isEmpty() || published.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        System.out.println("DOne");

        int isbn = Integer.parseInt(ISBN);

        final String BOOK_CREATE = "INSERT INTO library ( title, subject, author, ISBN, publish_date)" +
                " VALUES ('" + title + "', '" + subject + "' , '" + author + "', " + isbn + ",'" + published + "')";
        try{


            PreparedStatement stmt = con.conn.prepareStatement(BOOK_CREATE);

            stmt.executeUpdate();
            System.out.println("Book is added!");
        }catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ISBN has to be number date should be written ex: 2012-12-12");
            alert.showAndWait();
            return;
        }


    }

    @FXML
    void cancel(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert addBook != null : "fx:id=\"addBook\" was not injected: check your FXML file 'addBook.fxml'.";
        assert bookAuthor != null : "fx:id=\"bookAuthor\" was not injected: check your FXML file 'addBook.fxml'.";
        assert bookSubject != null : "fx:id=\"bookSubject\" was not injected: check your FXML file 'addBook.fxml'.";
        assert bookTitle != null : "fx:id=\"bookTitle\" was not injected: check your FXML file 'addBook.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'addBook.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'addBook.fxml'.";
        assert publish_date != null : "fx:id=\"publish_date\" was not injected: check your FXML file 'addBook.fxml'.";



    }

}
