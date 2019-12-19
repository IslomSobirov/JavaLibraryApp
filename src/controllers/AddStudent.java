package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.ConnectDb;
import models.StudentConfig;


public class AddStudent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createButton;

    @FXML
    private PasswordField studentPassword;

    @FXML
    private PasswordField studentPasswordRepeat;

    @FXML
    private TextField studentEmail;

    @FXML
    private TextField studentName;


    @FXML
    void addStudent(ActionEvent event) {

        //Create connection obj
        ConnectDb con = new ConnectDb();
        //Connect to database
        con.connect();

        //Convert data into string
        String name = studentName.getText();
        String email = studentEmail.getText();
        String password = studentPassword.getText();
        String passwordRepeat = studentPasswordRepeat.getText();


        //Check weather all fields are filled
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
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
        //Create student model and provide connection
        StudentConfig student = new StudentConfig(con);
        //Create student
        student.createStudent(name, email, password);

    }

    @FXML
    void cancel(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'addStudent.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'addStudent.fxml'.";
        assert studentPassword != null : "fx:id=\"password\" was not injected: check your FXML file 'addStudent.fxml'.";
        assert studentPasswordRepeat != null : "fx:id=\"passwordRepeat\" was not injected: check your FXML file 'addStudent.fxml'.";
        assert studentEmail != null : "fx:id=\"studentEmail\" was not injected: check your FXML file 'addStudent.fxml'.";
        assert studentName != null : "fx:id=\"studentName\" was not injected: check your FXML file 'addStudent.fxml'.";


    }

}
