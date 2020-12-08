/*
Esta clase es para manejar las Querys realizadas en la Base de Datos
*/

package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    public static void register(String userName, String pass) throws SQLException { // registra ususarios
        if (CreateDatabase.c != null) {// comprueba que la Base de Datos este creada
            Statement stmt = CreateDatabase.c.createStatement();
            String addUser = String.format("INSERT OR IGNORE INTO usuarios(usuario,pass) VALUES ('%s', '%s')", userName, pass);// añade el ususario
            stmt.executeUpdate(addUser);
            stmt.close();
            CreateDatabase.c.commit();
        }
    }
    
    public static boolean isEmpty() throws SQLException { // comprueba si esta vacia la Base de Datos
        if (CreateDatabase.c != null) {
            Statement stmt = CreateDatabase.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS usuario FROM usuarios");// le pedimos la cantidad de usuarios que hay
            Integer user = rs.getInt("usuario");
            rs.close();
            stmt.close();
            return user == 0;
        }
        return false;
    }
}
