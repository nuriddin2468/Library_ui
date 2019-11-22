package App.MVCT;
import App.MVCT.Database;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.serializer.Serializer;

import java.lang.reflect.Field;
import java.util.Arrays;

public class test extends Database{
    private String tableName = "Student";
    private String name;
    private String surname;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public  void Serialize(test student) throws JsonProcessingException {
        ObjectMapper Obj = new ObjectMapper();
        student.json = Obj.writeValueAsString(student);
        System.out.println(student.json);
    }

    public static void main(String[] args) throws JsonProcessingException {
        test obj = new test();
        obj.name = "David";
        obj.surname = "Hellboy";
        obj.Serialize(obj);
    }
}
