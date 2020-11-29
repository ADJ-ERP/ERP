package main;

import database.CreateDatabase;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CreateDatabase.create("db");
    }
}
