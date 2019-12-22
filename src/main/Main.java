package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.*;


import java.io.IOException;
import java.sql.Statement;


public class Main extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent loader = FXMLLoader.load(getClass().getResource("/view/login/loginPage.fxml"));
        Parent loader = FXMLLoader.load(getClass().getResource("/view/admin/adminMain.fxml"));
        this.primaryStage = primaryStage;
        Scene scene = new Scene(loader);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }







    public static void main(String[] args) {

//        launch(args);

        Statement stmt;
        ConnectDb con = new ConnectDb();
        con.connect();
        con.createBorrowedBooksTable();
        BorrowedBooks login = new BorrowedBooks(con);
        boolean lg = login.create("adwada@gmail.com", 343, 6);
        System.out.println(lg);
//
////
//        LibrarianConfig librarian = new LibrarianConfig(con);
//        librarian.create("Aziz", "isada@gmial.com", "3434");















    }

}
