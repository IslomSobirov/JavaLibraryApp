package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ConnectDb;
import models.LibrarianConfig;
import models.StudentConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteLibrarian {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField librarianEmail;


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
        String email = librarianEmail.getText();

        if(email.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
        }

        //Create student student object
        LibrarianConfig librarian = new LibrarianConfig(con);
        boolean YesOrNo = librarian.delete(email);
        if (YesOrNo){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Librarian was deleted successfully");
            alert.showAndWait();
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        }

        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Librarian with this email might not exist");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'deleteStudent.fxml'.";
        assert librarianEmail != null : "fx:id=\"studentEmail\" was not injected: check your FXML file 'deleteStudent.fxml'.";


    }

}
