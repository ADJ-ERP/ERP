package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static Connection c = null;

    public static void create(String name) throws SQLException, ClassNotFoundException {
        boolean createDir = new File("database").mkdir();
        if (createDir) System.out.println("Carpeta de la base de datos creada");
        c = DriverManager.getConnection(String.format("jdbc:sqlite:database/%s.db", name));
        assert c != null;
        c.setAutoCommit(false);
        createTables();
    }

    private static void createTables() throws SQLException {
        createUserTable();
    }

    private static void createUserTable() throws SQLException {
        Statement stmt = c.createStatement();

        String userTable = "CREATE TABLE IF NOT EXISTS `usuarios`(" +
                "`usuario` TEXT PRIMARY KEY NOT NULL," +
                "`pass` TEXT NOT NULL);";
        stmt.executeUpdate(userTable);
        stmt.close();
        c.commit();
    }
}
