package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class StudentMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane anchorPane;


    @FXML
    void allBooks(ActionEvent event) {
        openWindow("/view/book/showBooks.fxml", "All books");
    }

    @FXML
    void borrowedBooks(ActionEvent event) {
    }

    @FXML
    void logout(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
        openWindow("/view/login/loginPage.fxml", "Login");
    }

    @FXML
    void returnBook(ActionEvent event) {
    }

    @FXML
    void searchBook(ActionEvent event) {
    }

    @FXML
    void takeBook(ActionEvent event) {
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

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'studentMain.fxml'.";


    }

}
