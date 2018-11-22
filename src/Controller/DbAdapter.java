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
    static void createNewDatabase(String fileName) { //Creates a new Database

        //Setting up directories
        String directory = "C:/sqlite/db/";
        String dbFileDirectory = "jdbc:sqlite:" + directory + fileName;

        //Converting directories to Path objects
        Path path = Paths.get(directory);
        Path dbFile = Paths.get(directory + fileName);

        //Checking if Directory exists, if not, make it
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Checking if Database exists, if not, make it
        if(!Files.exists(dbFile)) {
            try (Connection conn = DriverManager.getConnection(dbFileDirectory)) {
                if (conn != null) {
                    System.out.println("A new database has been created.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Located DB file: " + dbFile.toString());
        }
    }
}
