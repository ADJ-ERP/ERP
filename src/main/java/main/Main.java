package main;

import database.CreateDatabase;
import database.Query;
import userManagement.RegistroUsuarios;
import userManagement.Usuarios;
import utils.LanguageUtils;

import java.sql.SQLException;

public class Main {
    public static final String NAME = "ADJERP";

    public static String lang = "es_es";

    public static void main(String[] args) throws SQLException {
        CreateDatabase.create("db");  // Creamos la base de datos.
        LanguageUtils.trySetLanguage(lang);  // Intento cargar la configuración de idioma.
        if (Query.isEmpty()) {  // Comprueba si la base de datos esta vacía.
            RegistroUsuarios regUsr = new RegistroUsuarios();
            regUsr.frame.setVisible(true);
        } else {
            Usuarios usuario = new Usuarios();
            usuario.frame.setVisible(true);            
        }    
 
    }
}
=======
package main;



public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World");

    }

}

