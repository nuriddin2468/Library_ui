package App.Models;

import App.Models.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersModel extends Database {
    private int id;
    private String  username;
    private String password;
    private String name;
    private int role;

    public UsersModel(){
        super();
    }

    public UsersModel(int id, String username, String password, String name ,int role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


    @Override
    public void getOne(String key, String val) {
        try {
            Statement stmnt;
            ResultSet result = null;
            stmnt = Connect().createStatement();
            if(key == "id"){
                result = stmnt.executeQuery(String.format("SELECT * FROM Users WHERE %s = %s", key, val));
            }else {
                result = stmnt.executeQuery(String.format("SELECT * FROM Users WHERE %s = '%s'", key, val));
            }
            if (result.next()) {
                    username = result.getString("username");
                    password = result.getString("password");
                    role = result.getInt("role");
                    id = result.getInt("id");
                    name = result.getString("name");
            }
            stmnt.close();
        }catch(Exception except){
            except.printStackTrace();
        }

    }



    @Override
    public ResultSet getAll() throws SQLException {
            Statement stmnt = Connect().createStatement();
        return stmnt.executeQuery("SELECT * FROM Users");

    }

    @Override
    public ResultSet search(String key, String val) throws SQLException {
        Statement stmnt = Connect().createStatement();
        return stmnt.executeQuery(String.format("SELECT * FROM Users WHERE %s = '%s'", key, val));


    }

    @Override
    public void insert() {
        try {
            Statement stmnt = Connect().createStatement();
            stmnt.execute(String.format("INSERT INTO Users (username , password , name , role) VALUES ('%s', '%s', '%s' , %d)", username , password , name, role));
            stmnt.close();
        }catch(Exception except){
            except.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            Statement stmnt = Connect().createStatement();
            stmnt.executeUpdate(String.format("UPDATE Users SET password = '%s', username = '%s', name = '%s', role = %d WHERE id = %d",password,username,name,role, id));
            stmnt.close();
        }catch(Exception except){
            System.out.println("hello");
            except.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            Statement stmnt = Connect().createStatement();
            stmnt.executeUpdate(String.format("DELETE FROM Users WHERE id = %d",id));
            stmnt.close();
        }catch(Exception except){
            except.printStackTrace();
        }
    }
}
