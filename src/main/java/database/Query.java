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
}
