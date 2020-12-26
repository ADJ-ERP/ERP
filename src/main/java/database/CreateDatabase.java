/*
Esta clase es para manejar la creacion de la Base de Datos 
*/

package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static Connection c = null;

    public static void create(String name) throws SQLException {
        boolean createDir = new File("database").mkdir();// creamos la carpeta donde estara la Base de Datos
        if (createDir) System.out.println("Carpeta de la base de datos creada");
        c = DriverManager.getConnection(String.format("jdbc:sqlite:database/%s.db", name));// creamos la Base de Datos
        assert c != null;
        c.setAutoCommit(false);
        createTables();
    }

    private static void createTables() throws SQLException {// estoa albergara toda la creacion de tablas
        createUserTable();
    }

    private static void createUserTable() throws SQLException {// creamos la tabla Usuarios
        Statement stmt = c.createStatement();

        String userTable = "CREATE TABLE IF NOT EXISTS `usuarios`(" +
                "`usuario` TEXT PRIMARY KEY NOT NULL," +
                "`pass` TEXT NOT NULL);";
        stmt.executeUpdate(userTable);
        stmt.close();
        c.commit();
    }
}
