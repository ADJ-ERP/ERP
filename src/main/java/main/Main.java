/*
Esta clase es para manejar todo el programa
*/

package main;

import database.CreateDatabase;
import database.Query;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CreateDatabase.create("db");  // Creamos la base de datos.
        if(Query.isEmpty()) {  // Comprueba si la base de datos esta vacía.
            RegistroUsuarios regUsr = new RegistroUsuarios();
            regUsr.frame.setVisible(true);
        }
        else{
            Usuarios usuario = new Usuarios();     
            usuario.frame.setVisible(true);            
        }    
 
    }
}
