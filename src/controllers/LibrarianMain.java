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
import main.Main;


public class LibrarianMain {

//    private Main main;
//    public void setMain(Main main){
//        this.main = main;
//    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private StackPane anchorPane;

    @FXML
    void deleteBook(ActionEvent event) {
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        openWindow("/view/student/deleteStudent.fxml", "Delete student");
    }



    @FXML
    void overdueStudents(ActionEvent event) {
    }

    @FXML
    void searchBook(ActionEvent event) {
    }

    @FXML
    void searchStudent(ActionEvent event) {
    }


    @FXML
    void AllBooks(ActionEvent event) {
        openWindow("/view/book/showBooks.fxml", "All books");
    }

    @FXML
    void addBook(ActionEvent event) {
        openWindow("/view/book/addBook.fxml", "Add book");
    }

    @FXML
    void addStudent(ActionEvent event) {
        openWindow("/view/student/addStudent.fxml", "Add student");
    }

    @FXML
    void allStudents(ActionEvent event) {
        openWindow("/view/student/showStudents.fxml", "All students");
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


    }

}
