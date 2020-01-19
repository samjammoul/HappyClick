package DataBase;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlite:identifier.sqlite" + System.getProperty("user.dir") + File.separator + "RestDB";

    public static Connection conn() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initialize() {
        try {
            // create a database connection
            Statement statement = DBConnection.conn().createStatement();

            // set timeout to 30 sec.
            statement.setQueryTimeout(30);
/*
            // create new tables "user" and "animal"
            String sqlQuery = "CREATE TABLE IF NOT EXISTS player (\n" +
                    "    player_id INTEGER PRIMARY KEY,\n" +
                    "    player_name TEXT NOT NULL UNIQUE,\n" +
                    "    password TEXT NOT NULL" +
                    ");";
            statement.execute(sqlQuery);

 */

        } catch (SQLException ex) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            DBConnection.getLogger().log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (DBConnection.conn() != null) {
                    DBConnection.conn().close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    public static Logger getLogger() {
        return Logger.getLogger(DBConnection.class.getName());
    }
}
