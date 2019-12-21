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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.ConnectDb;
import models.StudentConfig;


public class ShowStudents {

    ObservableList<ShowStudents.Student> StudentList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button quitButton;

    @FXML
    private HBox quitHbox;

    @FXML
    private TableView<Student> studentsTableView;

    @FXML
    private TableColumn<Student, String> studentName;

    @FXML
    private TableColumn<Student, String> studentEmail;

    @FXML
    private TableColumn<Student, String> studentPassword;

    @FXML
    private TableColumn<Student, String> studentCreated_at;




    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'showStudents.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'showStudents.fxml'.";
        assert quitHbox != null : "fx:id=\"quitHbox\" was not injected: check your FXML file 'showStudents.fxml'.";
        assert studentCreated_at != null : "fx:id=\"studentCreated_at\" was not injected: check your FXML file 'showStudents.fxml'.";
        assert studentEmail != null : "fx:id=\"studentEmail\" was not injected: check your FXML file 'showStudents.fxml'.";
        assert studentName != null : "fx:id=\"studentName\" was not injected: check your FXML file 'showStudents.fxml'.";
        assert studentPassword != null : "fx:id=\"studentPassword\" was not injected: check your FXML file 'showStudents.fxml'.";
        assert studentsTableView != null : "fx:id=\"studentsTableView\" was not injected: check your FXML file 'showStudents.fxml'.";
        column();
        getData();
    }

    private void column(){
        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        studentCreated_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
    }

    public void getData(){
        ConnectDb con = new ConnectDb();
        con.connect();
        StudentConfig student = new StudentConfig(con);
        ResultSet result = student.selectAll();
        try{
            while (result.next()){
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                String created_at = result.getString("created_at");


                StudentList.add(new ShowStudents.Student(name, email, password, created_at));
            }


            studentsTableView.getItems().setAll(StudentList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static class Student {
        private final SimpleStringProperty name;
        private final SimpleStringProperty email;
        private final SimpleStringProperty password;
        private final SimpleStringProperty created_at;

        Student(String name, String email, String password, String created_at) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
            this.password = new SimpleStringProperty(password);
            this.created_at = new SimpleStringProperty(created_at);
        }

        public String getName() {
            return name.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getPassword() {
            return password.get();
        }

        public String getCreated_at() {
            return created_at.get();
        }
    }

}
