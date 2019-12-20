package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ConnectDb;
import models.LibrarianConfig;
import models.StudentConfig;


public class AddLibrarian {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField librarianEmail;

    @FXML
    private TextField librarianName;

    @FXML
    private PasswordField librarianPassword;

    @FXML
    private PasswordField librarianPasswordRepeat;


    @FXML
    void addLibrarian(ActionEvent event) {

        //Create connection obj
        ConnectDb con = new ConnectDb();
        //Connect to database
        con.connect();

        //Convert data into string
        String name = librarianName.getText();
        String email = librarianEmail.getText();
        String password = librarianPassword.getText();
        String passwordRepeat = librarianPasswordRepeat.getText();

        //Create librarian model and provide connection
        LibrarianConfig librarian = new LibrarianConfig(con);
        //Check weather all fields are filled
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        else if(librarian.checkIfExist(email)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Password fields has to be same");
            alert.showAndWait();
            return;
        }

        else if(!password.equals(passwordRepeat)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Password fields has to be same");
            alert.showAndWait();
            return;
        }

        //Create librarian
        librarian.createLibrarian(name, email, password);
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'addLibrarian.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'addLibrarian.fxml'.";
        assert librarianEmail != null : "fx:id=\"librarianEmail\" was not injected: check your FXML file 'addLibrarian.fxml'.";
        assert librarianName != null : "fx:id=\"librarianName\" was not injected: check your FXML file 'addLibrarian.fxml'.";
        assert librarianPassword != null : "fx:id=\"librarianPassword\" was not injected: check your FXML file 'addLibrarian.fxml'.";
        assert librarianPasswordRepeat != null : "fx:id=\"librarianPasswordRepeat\" was not injected: check your FXML file 'addLibrarian.fxml'.";


    }

}
