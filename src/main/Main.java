package main;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/view/book/addBook.fxml"));
        this.primaryStage = primaryStage;

        Scene scene = new Scene(loader);
        primaryStage.setScene(scene);

        primaryStage.show();
        
    }

    public void bookCreate()
    {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/book/addBook.fxml"));
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

        //Statement stmt;
//        ConnectDb con = new ConnectDb();
//        con.connect();




        //BooksConfig book = new BooksConfig(con);
        //book.createBook("The selfish gene", "Biology", "Richard Dawkins", 222, "2015-6-8");














    }

}
