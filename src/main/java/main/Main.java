package main;

import database.CreateDatabase;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CreateDatabase.create("db");
        Usuarios usuario = new Usuarios();
        RegistroUsuario reg = new RegistroUsuario();
        reg.setVisible(true);// hacemos que salga la pantalla
    }
}
