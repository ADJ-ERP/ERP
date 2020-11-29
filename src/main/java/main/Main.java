package main;

import database.CreateDatabase;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CreateDatabase.create("db");
        Usuarios usuario = new Usuarios();
        usuario.setVisible(true);// hacemos que salga la pantalla 
    }
}
