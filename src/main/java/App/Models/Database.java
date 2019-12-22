package App.Models;

import java.sql.*;
import java.util.ArrayList;

public abstract class Database {

    public Connection Connect(){
        Connection conn = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection("jdbc:derby:db;create=True");
        }
        catch(Exception exception) {
            exception.getStackTrace();
        }
        return conn;
    }

    public abstract void getOne(String key, String val);
    public abstract ResultSet getAll() throws SQLException;
    public abstract void insert();
    public abstract ResultSet search(String key, String val) throws SQLException;
    public abstract void update();
    public abstract void delete(int id);
}
