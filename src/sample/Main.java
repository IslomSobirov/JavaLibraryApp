package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.derby.client.am.SqlException;
import sample.configs.AdminConfig;
import sample.configs.LibrarianConfig;
import sample.configs.LibraryConfig;
import sample.configs.StudentConfig;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {





    public static void main(String[] args) {


        Statement stmt;
        ConnectDb con = new ConnectDb();
        con.connect();






        StudentConfig student = new StudentConfig(con);
        //student.createStudent("Islom", "islomsobirov@gmail.com", "324");
        //student.createStudent("Islomaewea2", "atomm262@gmail.com", "33244");
//        student.selectAllStudents();
//        LibraryConfig book = new LibraryConfig(con);











    }

}
