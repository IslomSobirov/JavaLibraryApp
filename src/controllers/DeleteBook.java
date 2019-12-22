package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ConnectDb;
import models.BooksConfig;
import models.LibrarianConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteBook {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField bookId;


    @FXML
    void cancelButton(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteButton(ActionEvent event) {
        //Create connection obj
        ConnectDb con = new ConnectDb();
        //Connect to database
        con.connect();
        String bookid = bookId.getText();


        if(bookid.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
        }
        int id = Integer.parseInt(bookid);

        //Create student student object
        BooksConfig book = new BooksConfig(con);
        boolean YesOrNo = book.delete(id);
        if (YesOrNo){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Book was deleted successfully");
            alert.showAndWait();
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        }

        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Book with this ISBN id might not exist");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'deleteStudent.fxml'.";
        assert bookId != null : "fx:id=\"studentEmail\" was not injected: check your FXML file 'deleteStudent.fxml'.";


    }
}
