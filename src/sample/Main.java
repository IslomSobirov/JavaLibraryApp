package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.derby.client.am.SqlException;
import sample.configs.AdminConfig;
import sample.configs.StudentConfig;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {


    private static final String CREATE_TABLE_SQL="CREATE TABLE students ("
            + "ID INT GENERATED ALWAYS AS IDENTITY not null,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "DATE DATE NOT NULL,"
            + "EMAIL VARCHAR(45) NOT NULL,"
            + "PASSWORD VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY (ID))";


    public static void main(String[] args) {


        Statement stmt;
        ConnectDb con = new ConnectDb();
        con.connect();
//        con.createUsersTable();



//        AdminConfig admin = new AdminConfig(con);
//        admin.CreateAdmin("Aziz", "arer", "334343");

//        tables.SelectId("admins");

        StudentConfig student = new StudentConfig(con);
//        student.createStudent("Islom", "arrar", "324");
//        student.createStudent("Islomaewea2", "arrar", "33244");

//        student.selectAllStudents();
//        student.selectById(1);
//        student.deleteStudent(2);
//        student.selectAllStudents();
        student.selectById(101);
//        student.updateStudent(101, "AZAer", "erere", "3434343");







    }

}
