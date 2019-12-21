package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import main.ConnectDb;
import models.BooksConfig;


public class ShowBooks {

    ObservableList<Book> BookList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private TableColumn<Book, String> subjectCol;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> isbnCol;

    @FXML
    private TableColumn<Book, String> amountCol;

    @FXML
    private TableColumn<Book, String> publishCol;




    @FXML
    void initialize() {
        assert amountCol != null : "fx:id=\"amountCol\" was not injected: check your FXML file 'showBooks.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'showBooks.fxml'.";
        assert authorCol != null : "fx:id=\"authorCol\" was not injected: check your FXML file 'showBooks.fxml'.";
        assert isbnCol != null : "fx:id=\"isbnCol\" was not injected: check your FXML file 'showBooks.fxml'.";
        assert publishCol != null : "fx:id=\"publishCol\" was not injected: check your FXML file 'showBooks.fxml'.";
        assert subjectCol != null : "fx:id=\"subjectCol\" was not injected: check your FXML file 'showBooks.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'showBooks.fxml'.";
        assert titleCol != null : "fx:id=\"titleCol\" was not injected: check your FXML file 'showBooks.fxml'.";
        column();
        getData();

    }

    private void column(){
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        publishCol.setCellValueFactory(new PropertyValueFactory<>("publish_date"));
    }

    private void getData(){
        ConnectDb con = new ConnectDb();
        con.connect();
        BooksConfig book = new BooksConfig(con);
        ResultSet result = book.selectAll();
        try {
            while (result.next()){
                String title = result.getString("title");
                String subject = result.getString("subject");
                String author = result.getString("author");
                int isbn = result.getInt("isbn");
                int amount = result.getInt("amount");
                String publish_date = result.getString("publish_date");
                BookList.add(new Book(title, subject, author, isbn, amount, publish_date));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        tableView.getItems().setAll(BookList);
    }

    public static class Book{
        private final SimpleStringProperty title;
        private final SimpleStringProperty subject;
        private final SimpleStringProperty author;
        private final SimpleIntegerProperty isbn;
        private final SimpleIntegerProperty amount;
        private final SimpleStringProperty publish_date;

        Book(String title, String subject, String author, int isbn, int amount, String publish_date){
            this.title = new SimpleStringProperty(title);
            this.subject = new SimpleStringProperty(subject);
            this.author = new SimpleStringProperty(author);
            this.isbn = new SimpleIntegerProperty(isbn);
            this.amount = new SimpleIntegerProperty(amount);
            this.publish_date = new SimpleStringProperty(publish_date);
        }

        public String getTitle() {
            return title.get();
        }

        public String getSubject() {
            return subject.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public int getIsbn() {
            return isbn.get();
        }

        public int getAmount() {
            return amount.get();
        }

        public String getPublish_date() {
            return publish_date.get();
        }
    }

}
