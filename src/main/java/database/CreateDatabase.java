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
        boolean createDir = new File("db").mkdir();  // Creamos la carpeta donde estará la Base de Datos.
        if (createDir) System.out.println("Carpeta de la base de datos creada");
        c = DriverManager.getConnection(String.format("jdbc:sqlite:db/%s.db", name));  // Creamos la Base de Datos.
        assert c != null;
        c.setAutoCommit(false);
        createTables();
    }

    private static void createTables() throws SQLException {  // Esto contiene toda la creación de tablas.
        createUserTable();
        createPartidaTable();
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
    
    private static void createPartidaTable() throws SQLException {  // Creamos la tabla Partidas.
        Statement stmt = c.createStatement();

        String partidaTable = "CREATE TABLE IF NOT EXISTS `partidas`(" +
                "`numPartida` INT PRIMARY KEY NOT NULL," +
                "`fechaAlta` TEXT NOT NULL," +
                "`tipo` TEXT NOT NULL," +
                "`centroVenta` TEXT NOT NULL," +
                "`numMatadero` INT NOT NULL," +
                "`proveedor` TEXT NOT NULL," +
                "`numExplotacion` INT NOT NULL," +
                "`paisNacido` TEXT NOT NULL," +
                "`paisSacrificado` TEXT NOT NULL," +
                "`tipoAnimal` TEXT NOT NULL," +
                "`totalAnimales` INT NOT NULL," +  
                "`delNum` INT NOT NULL," +  
                "`alNum` INT NOT NULL," +  
                "`totalKgBrutos` INT NOT NULL," +
                "`porcenOreo` INT NOT NULL," +
                "`totalkgNetos` INT NOT NULL," +
                "`importeTotalCosto` INT NOT NULL," +
                "`notas` TEXT NULL);";
        stmt.executeUpdate(partidaTable);
        stmt.close();
        c.commit();
    }
}
