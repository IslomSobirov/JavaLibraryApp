package sample.configs;


import sample.ConnectDb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class AdminConfig {

    ConnectDb con;
    Statement stmt;
    public AdminConfig(ConnectDb con)
    {
        this.con = con;
    }



    public void CreateAdmin(String name, String email, String password)
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



    public void selectAllAdmins()
    {
        final String GET_ALL_ADMINS ="SELECT * " +
                "FROM users WHERE ROLE = 'admin'";
        try{

            stmt = con.conn.createStatement();
            ResultSet resultSet;
            resultSet = stmt.executeQuery(GET_ALL_ADMINS);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String created_at = resultSet.getString("created_at");

                System.out.println("Admin name: " +name+ "\n" + "Created at " + created_at);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void selectById(int id)
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

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
