/*
Esta clase es para manejar las Queries realizadas en la Base de Datos
*/

package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    public static void register(String userName, String pass) throws SQLException {  // Registra usuarios.
        String query = "INSERT OR IGNORE INTO usuarios(usuario,pass) VALUES (?, ?);";
        if (CreateDatabase.c != null) {  // Comprueba que la Base de Datos este creada.
            PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);  // Evitar que pongan inputs no deseados para acceder a información privada o tirarla.
            stmt.setString(1, userName);
            stmt.setString(2, pass);

            stmt.executeUpdate();  // Añade el usuario.
            stmt.close();
            CreateDatabase.c.commit();
        }
    }
    
    public static boolean isEmpty() throws SQLException {  // Comprueba si esta vacía la Base de Datos.
        if (CreateDatabase.c != null) {
            Statement stmt = CreateDatabase.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS usuario FROM usuarios;");  // Le pedimos la cantidad de usuarios que hay.
            int user = rs.getInt("usuario");
            rs.close();
            stmt.close();
            return user == 0;
        }
        return false;
    }

    public static boolean userExists(String user) throws SQLException {  // Comprobar si el usuario existe.
        String query = "SELECT usuario FROM usuarios WHERE usuario LIKE ?;";
        if (CreateDatabase.c != null) {
            PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
            stmt.setString(1, user);

            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            rs.close();
            stmt.close();
            return exists;
        }
        return false;
    }

    public static String getHash(String user) throws SQLException {  // Sacar el hash de la base de datos.
        String query = "SELECT pass FROM usuarios WHERE usuario LIKE ?;";
        if (CreateDatabase.c != null) {
            PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
            stmt.setString(1, user);

            ResultSet rs = stmt.executeQuery();
            String pass = rs.getString("pass");
            rs.close();
            stmt.close();
            return pass;
        }
        return null;
    }
    
    public static void registerPartida(int numPartida, String fechaAlta, String tipo, String centroVenta, int numMatadero,
            String proveedor, int numExplotacion, String paisNacido, String paisSacrificado, String tipoAnimal, int totalAnimales, int delNum, 
            int alNum, int totalKgBrutos, int porcenOreo, int totalKgNetos, int importeTotalCosto, String notas) throws SQLException {  // Registra usuarios.
        String query = "INSERT OR IGNORE INTO partidas(numPartida, fechaAlta, tipo, centroVenta, numMatadero, "
                + "proveedor, numExplotacion, paisNacido, paisSacrificado, tipoAnimal, totalAnimales, "
                + "delNum, alNum, totalKgBrutos, porcenOreo, totalkgNetos, importeTotalCosto, notas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        if (CreateDatabase.c != null) {  // Comprueba que la Base de Datos este creada.
            PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);  // Evitar que pongan inputs no deseados para acceder a información privada o tirarla.
            stmt.setInt(1, numPartida);
            stmt.setString(2, fechaAlta);
            stmt.setString(3, tipo);
            stmt.setString(4, centroVenta);
            stmt.setInt(5, numMatadero);
            stmt.setString(6, proveedor);
            stmt.setInt(7, numExplotacion);
            stmt.setString(8, paisNacido);
            stmt.setString(9, paisSacrificado);
            stmt.setString(10, tipoAnimal);
            stmt.setInt(11, totalAnimales);
            stmt.setInt(12, alNum);
            stmt.setInt(13, delNum);
            stmt.setInt(14, totalKgBrutos);
            stmt.setInt(15, porcenOreo);
            stmt.setInt(16, totalKgNetos);
            stmt.setInt(17, importeTotalCosto);
            stmt.setString(18, notas);

            stmt.executeUpdate();  // Añade el usuario.
            stmt.close();
            CreateDatabase.c.commit();
        }
    }
    
    public static boolean partidaExists(int numPartida) throws SQLException {  // Comprobar si la partida existe.
        String query = "SELECT numPartida FROM partidas WHERE numPartida LIKE ?;";
        if (CreateDatabase.c != null) {
            PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
            stmt.setInt(1, numPartida);

            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            rs.close();
            stmt.close();
            return exists;
        }
        return false;
    }
}