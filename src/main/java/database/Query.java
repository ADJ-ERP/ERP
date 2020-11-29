package database;

import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    public static void register(String userName, String pass) throws SQLException {
        if (CreateDatabase.c != null) {
            Statement stmt = CreateDatabase.c.createStatement();
            String addUser = String.format("INSERT OR IGNORE INTO usuarios(usuario,pass) VALUES ('%s', '%s')", userName, pass);
            stmt.executeUpdate(addUser);
            stmt.close();
            CreateDatabase.c.commit();
        }
    }
}
