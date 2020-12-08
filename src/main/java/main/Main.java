/*
Esta clase es para manejar todo el programa
*/

package main;

import database.CreateDatabase;
import database.Query;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CreateDatabase.create("db");// creamos la base de datos
        if(Query.isEmpty()){// comprueba si la base de datos esta vacia 
            RegistroUsuario reg = new RegistroUsuario(); // si esta vacia hace que registres un usuario
            reg.setVisible(true);// hacemos que salga la pantalla
        }
        else{
            Usuarios usuario = new Usuarios();     
            usuario.setVisible(true);
        }    
 
    }
}
