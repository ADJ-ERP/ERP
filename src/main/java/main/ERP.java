package main;

import database.CreateDatabase;
import database.Query;
import userManagement.RegisterSuperUser;
import userManagement.RegistroUsuarios;
import userManagement.Usuarios;
import utils.LanguageUtils;

import java.sql.SQLException;

public class ERP {
    public static final String NAME = "ADJERP";

    public static String lang = "es_es";

    public static void main(String[] args) throws SQLException {
        CreateDatabase.create("db");  // Creamos la base de datos.
        LanguageUtils.trySetLanguage(lang);  // Intento cargar la configuración de idioma.
        if (Query.isEmpty()) {  // Comprueba si la base de datos esta vacía.
            RegisterSuperUser regUsr = new RegisterSuperUser();
            regUsr.frame.setVisible(true);
        } else {
            Usuarios usuario = new Usuarios();
            usuario.frame.setVisible(true);            
        }    
 
    }
}
