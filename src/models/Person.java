package models;

import java.sql.ResultSet;

public interface Person {
    public void create(String name, String email, String password);
    public ResultSet selectAll();
    public void delete(int id);
    public void update(int id, String name, String email, String password);
    public ResultSet selectById(int id);
    public boolean checkIfExist(String email);
}
