package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.ConnectDb;
import models.LibrarianConfig;



public class ShowLibrarians {

    ObservableList<ShowLibrarians.Librarian> LibrarianList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Librarian> librariansTableView;

    @FXML
    private TableColumn<Librarian, String> librarianName;

    @FXML
    private TableColumn<Librarian, String> librarianEmail;

    @FXML
    private TableColumn<Librarian, String> librarianPassword;

    @FXML
    private TableColumn<Librarian, String> librarianCreated_at;

    @FXML
    private Button quitButton;

    @FXML
    private HBox quitHbox;


    @FXML
    void quitPage(ActionEvent event) {
    }


    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'showLibrarians.fxml'.";
        assert librarianCreated_at != null : "fx:id=\"librarianCreated_at\" was not injected: check your FXML file 'showLibrarians.fxml'.";
        assert librarianEmail != null : "fx:id=\"librarianEmail\" was not injected: check your FXML file 'showLibrarians.fxml'.";
        assert librarianName != null : "fx:id=\"librarianName\" was not injected: check your FXML file 'showLibrarians.fxml'.";
        assert librarianPassword != null : "fx:id=\"librarianPassword\" was not injected: check your FXML file 'showLibrarians.fxml'.";
        assert librariansTableView != null : "fx:id=\"librariansTableView\" was not injected: check your FXML file 'showLibrarians.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'showLibrarians.fxml'.";
        assert quitHbox != null : "fx:id=\"quitHbox\" was not injected: check your FXML file 'showLibrarians.fxml'.";

        column();
        getData();

    }

    private void column(){
        librarianName.setCellValueFactory(new PropertyValueFactory<>("name"));
        librarianName.setCellValueFactory(new PropertyValueFactory<>("email"));
        librarianPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        librarianCreated_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
    }


    public void getData(){
        ConnectDb con = new ConnectDb();
        con.connect();
        LibrarianConfig librarian = new LibrarianConfig(con);
        ResultSet result = librarian.selectAll();
        try{
            while (result.next()){
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                String created_at = result.getString("created_at");
                LibrarianList.add(new ShowLibrarians.Librarian(name, email, password, created_at));
            }

            librariansTableView.getItems().setAll(LibrarianList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static class Librarian {
        private final SimpleStringProperty name;
        private final SimpleStringProperty email;
        private final SimpleStringProperty password;
        private final SimpleStringProperty created_at;

        Librarian(String name, String email, String password, String created_at) {
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
