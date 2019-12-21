package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDb {

    public Connection conn;
    Statement stmt;

    private static final String TABLE_SQL="CREATE TABLE users ("
            + "ID INT GENERATED ALWAYS AS IDENTITY not null PRIMARY KEY,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "CREATED_AT date NOT NULL,"
            + "EMAIL VARCHAR(45) NOT NULL,"
            + "PASSWORD VARCHAR(45) NOT NULL,"
                + "ROLE VARCHAR(15) NOT NULL)";


    private static final String LIBRARY_TABLE_SQL="CREATE TABLE library ("
            + "ID INT GENERATED ALWAYS AS IDENTITY not null PRIMARY KEY,"
            + "title VARCHAR(45) NOT NULL,"
            + "subject VARCHAR(45) NOT NULL,"
            + "author VARCHAR(45) NOT NULL,"
            + "ISBN INT NOT NULL,"
            + "AMOUNT INT NOT NULL,"
            + "publish_date DATE NOT NULL)";


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

    public void createLibraryTable()
    {
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(LIBRARY_TABLE_SQL);
            System.out.println("Table is created");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void close(ConnectDb con)
    {
        try{
            con.conn.close();
            con.stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }





}
