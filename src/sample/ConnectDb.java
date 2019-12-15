package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDb {

    public Connection conn = null;
    Statement stmt = null;

    private static final String TABLE_SQL="CREATE TABLE users ("
            + "ID INT GENERATED ALWAYS AS IDENTITY not null PRIMARY KEY,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "CREATED_AT date NOT NULL,"
            + "EMAIL VARCHAR(45) NOT NULL,"
            + "PASSWORD VARCHAR(45) NOT NULL,"
            + "ROLE VARCHAR(15) NOT NULL)";


    public void connect(){
        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String dbURL = "jdbc:derby:library;create=true";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(dbURL, user, password);

            System.out.println("Connected");


        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }


    public void createUsersTable()
    {
        try{

            stmt = conn.createStatement();
            stmt.executeUpdate(TABLE_SQL);
            System.out.println("Table is created");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }





}
