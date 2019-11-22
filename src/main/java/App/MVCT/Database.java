package App.MVCT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.apache.xml.internal.serializer.Serializer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


public class Database {
    String json;

    //
//    public static void migrate(Object obj){
//        Field[] data = obj.getClass().getDeclaredFields();
//        for(int i = 0; i <  data.length; i++){
//            System.out.println(data[i].getName());
////            System.out.println(data[i].getType().getSimpleName());
//        }

    private static Connection connect() throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            return connection;
//            try {
////                if (connection != null)
////                    connection.close();
//            } catch (SQLException e) {
//                // connection close failed.
//                System.err.println(e);
//            }
        }
    }
}
