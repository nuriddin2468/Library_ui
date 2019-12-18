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

    public UsersModel(int id, String username, String password, int role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UsersModel(String username, String password, int role) {
        super();
        this.username = username;
        this.password = password;
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
            Statement stmnt = Connect().createStatement();
            ResultSet result = stmnt.executeQuery(String.format("SELECT * FROM Users WHERE %s = '%s' ", key, val));
            if(result.next()) {
                username = result.getString("username");
                password = result.getString("password");
                role = result.getInt("role");
                id = result.getInt("id");
                name = result.getString("name");
            }
        }catch(Exception except){
            except.printStackTrace();
        }

    }

    @Override
    public void getOne(String s) {

    }


    @Override
    public ArrayList<UsersModel> getAll() throws SQLException {
            Statement stmnt = Connect().createStatement();
            ResultSet result = stmnt.executeQuery("SELECT * FROM Users");
            ArrayList<UsersModel> objects = new ArrayList<>();
            while(result.next()){
                UsersModel object = new UsersModel(result.getInt("id"), result.getString("username"), result.getString("password"), result.getInt("role"));
                objects.add(object);
            }
            return objects;

    }

    @Override
    public void insert() {
        try {
            Statement stmnt = Connect().createStatement();
            stmnt.execute(String.format("INSERT INTO Users (username , password , name , role) VALUES ('%s', '%s', '%s' , %d)", username , password , name, role));
            System.out.println("User Inserted");

        }catch(Exception except){
            System.out.println("Error while inserting the user!");
            except.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            Statement stmnt = Connect().createStatement();

        }catch(Exception except){
            except.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            Statement stmnt = Connect().createStatement();

        }catch(Exception except){
            except.printStackTrace();
        }
    }
}
