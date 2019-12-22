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


public class AdminMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane anchorPane;


    @FXML
    void addBook(ActionEvent event) {
        openWindow("/view/book/addBook.fxml", "Add book");
    }

    @FXML
    void addLibrarian(ActionEvent event) {
        openWindow("/view/librarian/addLibrarian.fxml", "Add librarian");
    }

    @FXML
    void addStudent(ActionEvent event) {
        openWindow("/view/student/addStudent.fxml", "Add student");
    }

    @FXML
    void allBooks(ActionEvent event) {
        openWindow("/view/book/showBooks.fxml", "All books");
    }

    @FXML
    void allLibrarians(ActionEvent event) {
        openWindow("/view/librarian/showLibrarians.fxml", "All librarian");
    }

    @FXML
    void allStudents(ActionEvent event) {
        openWindow("/view/student/showStudents.fxml", "All Students");
    }

    @FXML
    void deleteBook(ActionEvent event) {
        openWindow("/view/book/deleteBook.fxml", "Delete book");
    }

    @FXML
    void deleteLibrarian(ActionEvent event) {
        openWindow("/view/librarian/deleteLibrarian.fxml", "Delete librarian");
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        openWindow("/view/student/deleteStudent.fxml", "Delete student");
    }

    @FXML
    void logout(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
        openWindow("/view/login/loginPage.fxml", "Login");
    }

    @FXML
    void modifyBook(ActionEvent event) {
    }

    @FXML
    void modifyLibrarian(ActionEvent event) {
    }

    @FXML
    void modifyStudent(ActionEvent event) {
    }

    @FXML
    void searchBook(ActionEvent event) {
    }

    @FXML
    void searchLibrarian(ActionEvent event) {
    }

    @FXML
    void searchStudent(ActionEvent event) {
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
