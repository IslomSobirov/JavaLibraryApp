package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ConnectDb;
import models.Login;


public class LoginController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> role;
//    private ChoiceBox<String> role;


    @FXML
    void loginButton(ActionEvent event) {
        String role1 = role.getValue();
        String email1 =  email.getText();
        String password1 = password.getText();
        if(role1.isEmpty() || email1.isEmpty() || password1.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please choose your role and fill in all the fields");
            alert.showAndWait();
        }
        System.out.println(role1);
        ConnectDb con = new ConnectDb();
        con.connect();
        Login login = new Login(con);
        boolean log = login.login(role1, email1, password1);
        if (log == true){
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
            if(role1.equals("student")){
                openWindow("/view/student/studentMain.fxml", "Student dashboard");
            }
            else if(role1.equals("librarian")){
                openWindow("/view/librarian/librarianMain.fxml", "Librarian Dashboard");
            }

            else if(role1.equals("admin")){
                openWindow("/view/admin/adminMain.fxml", "Admin Dashboard");
            }

        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Email or password is wrong");
            alert.showAndWait();
        }


    }

    @FXML
    void logoutButton(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert role != null : "fx:id=\"role\" was not injected: check your FXML file 'loginPage.fxml'.";

        loadData();
    }

    void openWindow(String link, String title){
        try{
            Parent parent = FXMLLoader.load(getClass().getResource(link));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadData(){
        list.removeAll();
        String admin = "admin";
        String librarian = "librarian";
        String student = "student";
        list.addAll(admin, librarian, student);
        role.getItems().addAll(list);
    }

}
