/*
Esta clase es para manejar la creación de la Base de Datos.
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
        boolean createDir = new File("database").mkdir();  // Creamos la carpeta donde estará la Base de Datos.
        if (createDir) System.out.println("Carpeta de la base de datos creada");
        c = DriverManager.getConnection(String.format("jdbc:sqlite:database/%s.db", name));  // Creamos la Base de Datos.
        assert c != null;
        c.setAutoCommit(false);
        createTables();
    }

    private static void createTables() throws SQLException {  // Esto contiene toda la creación de tablas.
        createUserTable();
    }

    private static void createUserTable() throws SQLException {  // Creamos la tabla Usuarios.
        Statement stmt = c.createStatement();

        String userTable = "CREATE TABLE IF NOT EXISTS `usuarios`(" +
                "`usuario` TEXT PRIMARY KEY NOT NULL," +
                "`pass` TEXT NOT NULL);";
        stmt.executeUpdate(userTable);
        stmt.close();
        c.commit();
    }
}
