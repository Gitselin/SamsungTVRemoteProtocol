package Controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

class DbAdapter {

    public static void connect(String dbName) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/db/" + dbName + ".db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static void createNewDatabase(String dbName) { //Creates a new Database

        //Setting up directories
        String directory = "C:/sqlite/db/";
        String dbFileDirectory = "jdbc:sqlite:" + directory + dbName;

        //Converting directories to Path objects
        Path path = Paths.get(directory);
        Path dbFile = Paths.get(directory + dbName);

        //Checking if Directory exists, if not, make it
        if(!Files.exists(path)) {
            try {
                System.out.println("Created new directories at " + path.toString());
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Checking if Database exists, if not, make it
        if(!Files.exists(dbFile)) {
            try (Connection conn = DriverManager.getConnection(dbFileDirectory)) {
                if (conn != null) {
                    System.out.println("A new database has been created: " + dbFile.toString());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("File already exists: " + dbFile.toString());
        }
    }

    public void createTable(String tableName){
        //[ID][Name][DateTime]  [Status][Uptime]

    }


}
