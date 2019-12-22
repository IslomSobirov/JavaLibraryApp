package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class EditStudent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField studentEmail;

    @FXML
    private TextField studentName;

    @FXML
    private TextField studentPassword;

    @FXML
    private TextField studentRepeatPassword;


    @FXML
    void cancelButton(ActionEvent event) {
    }

    @FXML
    void saveButton(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert studentEmail != null : "fx:id=\"studentEmail\" was not injected: check your FXML file 'editStudent.fxml'.";
        assert studentName != null : "fx:id=\"studentName\" was not injected: check your FXML file 'editStudent.fxml'.";
        assert studentPassword != null : "fx:id=\"studentPassword\" was not injected: check your FXML file 'editStudent.fxml'.";
        assert studentRepeatPassword != null : "fx:id=\"studentRepeatPassword\" was not injected: check your FXML file 'editStudent.fxml'.";


    }

}
