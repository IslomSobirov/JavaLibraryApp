package models;


import main.ConnectDb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class AdminConfig implements Person{

    ConnectDb con;
    Statement stmt;
    public AdminConfig(ConnectDb con)
    {
        this.con = con;
    }



    public void create(String name, String email, String password)
    {

        java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        final String ADMIN_CREATE = "INSERT INTO admins ( NAME, CREATED_AT, EMAIL, PASSWORD, ROLE)" +
                " VALUES ('" + name + "' , ? ,'" + email + "' , '" + password + "', 'admin')";
        try{

            stmt = con.conn.createStatement();
            PreparedStatement stmt = con.conn.prepareStatement(ADMIN_CREATE);
            stmt.setDate(1, ourJavaDateObject);
            stmt.executeUpdate();
            System.out.println("Admin created");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public ResultSet selectAll()
    {
        final String GET_ALL_ADMINS ="SELECT * " +
                "FROM users WHERE ROLE = 'admin'";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_ALL_ADMINS);
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String created_at = resultSet.getString("created_at");
//
//                System.out.println("Admin name: " +name+ "\n" + "Created at " + created_at);
//            }
            return resultSet;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    public ResultSet selectById(int id)
    {
        final String GET_ADMIN ="SELECT * " +
                "FROM users WHERE ID = "+ id + " ";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_ADMIN);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String created_at = resultSet.getString("created_at");
                System.out.println("Admin name: " +name+ "\n" + "Created at " + created_at);
            }
            return resultSet;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    public boolean checkIfExist(String email) {
        return false;
    }


    public void delete(int id)
    {
        final String DELETE_ADMIN = "DELETE " +
                "FROM users WHERE ID = "+ id + " AND ROLE = 'admin'";
        try{
            PreparedStatement stmt  = con.conn.prepareStatement(DELETE_ADMIN);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Successfully deleted!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void update(int id, String name, String email, String password)
    {
        final String UPDATE_ADMIN = "UPDATE users SET " +
                "name = '" + name + "'," +
                "email =  '" + email + "'," +
                "password = '" + password +"' "+
                "WHERE ID = " + id + " AND ROLE = 'admin'";
        try{
            PreparedStatement stmt = con.conn.prepareStatement(UPDATE_ADMIN);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Updated");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
