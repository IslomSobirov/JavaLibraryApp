package main;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.BooksConfig;
import models.StudentConfig;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/view/book/showBooks.fxml"));
        this.primaryStage = primaryStage;

        Scene scene = new Scene(loader);
        primaryStage.setScene(scene);

        primaryStage.show();
        
    }

    public void bookCreate()
    {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/student/addStudent.fxml"));
            AnchorPane pane = loader.load();
            Controller controller = loader.getController();
            controller.setMain(this);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public static void main(String[] args) {

        launch(args);

//        Statement stmt;
//        ConnectDb con = new ConnectDb();
//        con.connect();
//
//        BooksConfig book = new BooksConfig(con);
        //book.create("The selfish gene", "Biology", "Richard Dawkins", 242, 44,"2015-6-8");

//        StudentConfig student = new StudentConfig(con);
//        System.out.println(student.checkIfExist("atomm262@gmail.com"));



//        student.selectAll();
//        ResultSet result = student.selectById(201);
//        try{
//            while (result.next()) {
//                String name = result.getString("name");
//                String email1 = result.getString("email");
//                int id = result.getInt("id");
//                System.out.println("Student name: " +name+ "\n" + "Email " + email1 + " Id: " + id);
//
//
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }




        //BooksConfig book = new BooksConfig(con);














    }

}
