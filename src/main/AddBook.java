package main;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.BooksConfig;


public class AddBook {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField bookAuthor;

    @FXML
    private Button addBook;

    @FXML
    private Button cancel;

    @FXML
    private TextField bookSubject;

    @FXML
    private TextField bookTitle;

    @FXML
    private TextField isbn;

    @FXML
    private TextField publish_date;





    @FXML
    void initialize(URL location, ResourceBundle resources) {

        assert bookAuthor != null : "fx:id=\"bookAuthor\" was not injected: check your FXML file 'addBook.fxml'.";
        assert bookSubject != null : "fx:id=\"bookSubject\" was not injected: check your FXML file 'addBook.fxml'.";
        assert bookTitle != null : "fx:id=\"bookTitle\" was not injected: check your FXML file 'addBook.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'addBook.fxml'.";
        assert publish_date != null : "fx:id=\"publish_date\" was not injected: check your FXML file 'addBook.fxml'.";


    }

    @FXML
    void addBook(ActionEvent event) {
        ConnectDb con = new ConnectDb();
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

        int isbn = Integer.parseInt(ISBN);

        book.createBook(title, subject, author, isbn, published);
        System.out.println("created");
    }

    @FXML
    void cancel(ActionEvent event) {
    }


}
