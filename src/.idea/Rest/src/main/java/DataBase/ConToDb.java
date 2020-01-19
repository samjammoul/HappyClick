package DataBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConToDb {

 //   public static void main(String[] args) throws SQLException { test(); }

    private static FileInputStream input = null;

    private static String drivers;
    private static String url;

    private static String username;
    private static String password;
    private static Connection connection;



    public   static void loadDBFile(){


        try {
            input = new FileInputStream("Db.properties");
            // load a properties file
            Properties prop = new Properties();
            try {
                prop.load(input);
            } catch (IOException ex) {
                Logger.getLogger(ConToDb.class.getName()).log(Level.SEVERE, null, ex);
            }

            drivers = prop.getProperty("jdbc.drivers");
            url = prop.getProperty("jdbc.url");

            username = prop.getProperty("jdbc.username");
            password = prop.getProperty("jdbc.password");


            // load the sqlite-JDBC driver using the current class loader
            Class.forName(drivers);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public   static   java.sql.Connection conncteToDb () {

        try {
            // create a database connection
            connection = DriverManager.getConnection(url, username, password);


        } catch (SQLException ex) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            Logger.getLogger(ConToDb.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }


    public static void  closeConnection() throws SQLException {
        connection.close();
    }


    public static void  test() throws SQLException {

        loadDBFile();
        Statement statement = conncteToDb().createStatement();


        // execute query: get all persons
        ResultSet rs = statement.executeQuery("select * from user ");
        while (rs.next()) {
            // read the result set
            System.out.println("name = " + rs.getString("Name"));
            System.out.println("id = " + rs.getInt("id"));

        }

        try {
            if (conncteToDb() != null) {
                closeConnection();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e);
            System.err.println("Erro");
        }

    }



}
