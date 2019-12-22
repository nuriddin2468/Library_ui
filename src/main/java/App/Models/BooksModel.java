package App.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BooksModel extends Database {
    private int id;
    private String title;
    private String description;
    private String author;
    private String category;
    private String isbn;
    private int count = 0;

    public BooksModel(int id, String title, String description, String author, String category, String isbn, int count) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.count = count;
    }
    public BooksModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }



    @Override
    public void getOne(String key, String val) {
        try {
            Statement stmnt;
            ResultSet result = null;
            stmnt = Connect().createStatement();
            if(key.equals("id")){
                result = stmnt.executeQuery(String.format("SELECT * FROM Books WHERE %s = %s", key, val));
            }else {
                result = stmnt.executeQuery(String.format("SELECT * FROM Books WHERE %s = '%s'", key, val));
            }
            if (result.next()) {
                id = result.getInt("id");
                title = result.getString("title");
                description = result.getString("description");
                author = result.getString("author");
                category = result.getString("category");
                isbn = result.getString("isbn");
                count = result.getInt("count");
            }
            stmnt.close();
        }catch(Exception except){
            except.printStackTrace();
        }

    }



    @Override
    public ResultSet getAll() throws SQLException {
        Statement stmnt = Connect().createStatement();
        return stmnt.executeQuery("SELECT * FROM Books");

    }

    @Override
    public ResultSet search(String key, String val) throws SQLException {
        Statement stmnt = Connect().createStatement();
        return stmnt.executeQuery(String.format("SELECT * FROM Books WHERE %s = '%s'", key, val));


    }

    @Override
    public void insert() {
        try {
            Statement stmnt = Connect().createStatement();
            stmnt.execute(String.format("INSERT INTO Books (title , description , author , category, isbn, count) VALUES ('%s', '%s', '%s', '%s', '%s' , %d)", title , description , author, category, isbn, count));
            stmnt.close();
        }catch(Exception except){
            except.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            Statement stmnt = Connect().createStatement();
            stmnt.executeUpdate(String.format("UPDATE Books SET title = '%s', description = '%s', author = '%s', category = '%s', isbn = '%s', count = %d  WHERE id = %d",title , description , author, category, isbn, count, id));
            stmnt.close();
        }catch(Exception except){
            except.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            Statement stmnt = Connect().createStatement();
            stmnt.executeUpdate(String.format("DELETE FROM Books WHERE id = %d",id));
            stmnt.close();
        }catch(Exception except){
            except.printStackTrace();
        }
    }
}
