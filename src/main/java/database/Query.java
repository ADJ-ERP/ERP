/*
Esta clase es para manejar las Queries realizadas en la Base de Datos
*/

package database;

import utils.CustomTableFormat;

import java.sql.*;
import java.util.ArrayList;

public class Query {
    public static void register(String userName, String pass ,String rol) throws SQLException {  // Registra usuarios.
        String query = "INSERT OR IGNORE INTO usuarios(usuario,pass,rol) VALUES (?, ?,?);";
        if (CreateDatabase.c != null) {  // Comprueba que la Base de Datos este creada.
            PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);  // Evitar que pongan inputs no deseados para acceder a información privada o tirarla.
            stmt.setString(1, userName);
            stmt.setString(2, pass);
            stmt.setString(3, rol);

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
    public static Boolean isAdmin (String user) throws SQLException {
        String query = "SELECT rol FROM usuarios WHERE usuario LIKE ?;";
        PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
        stmt.setString(1, user);
        ResultSet rs = stmt.executeQuery();
        String rol = rs.getString("rol");
        System.out.println(rs.getString("rol"));
        return rol.equals("admin");

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

    public static boolean registerPartida(int numPartida, String fechaAlta, String tipo, String centroVenta, int numMatadero,
            String proveedor, int numExplotacion, String paisNacido, String paisSacrificado, String tipoAnimal, int totalAnimales, int delNum, 
            int alNum, int totalKgBrutos, int porcenOreo, int totalKgNetos, int importeTotalCosto, String notas) {  // Registra usuarios.
        String query = "INSERT OR IGNORE INTO partidas(numPartida, fechaAlta, tipo, centroVenta, numMatadero, "
                + "proveedor, numExplotacion, paisNacido, paisSacrificado, tipoAnimal, totalAnimales, "
                + "delNum, alNum, totalKgBrutos, porcenOreo, totalkgNetos, importeTotalCosto, notas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        if (CreateDatabase.c != null) {  // Comprueba que la Base de Datos este creada.
            PreparedStatement stmt = null;  // Evitar que pongan inputs no deseados para acceder a información privada o tirarla.
            try {
                stmt = CreateDatabase.c.prepareStatement(query);
                stmt.setInt(1, numPartida);
                stmt.setString(2, fechaAlta);
                stmt.setString(3, tipo);
                stmt.setString(4, centroVenta);
                stmt.setInt(5, numMatadero);
                stmt.setString(6, proveedor);
                stmt.setInt(7, numExplotacion);
                stmt.setString(8, paisNacido);
                stmt.setString(9, paisSacrificado);
                stmt.setString(10, tipoAnimal);
                stmt.setInt(11, totalAnimales);
                stmt.setInt(12, delNum);
                stmt.setInt(13, alNum);
                stmt.setInt(14, totalKgBrutos);
                stmt.setInt(15, porcenOreo);
                stmt.setInt(16, totalKgNetos);
                stmt.setInt(17, importeTotalCosto);
                stmt.setString(18, notas);
                stmt.executeUpdate();  // Añade el usuario.
                stmt.close();
                CreateDatabase.c.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static CustomTableFormat getPartidas() throws SQLException {
        if (CreateDatabase.c != null) {
            Statement stmt = CreateDatabase.c.createStatement();
            String getPColumns = "PRAGMA table_info('partidas');";
            ResultSet rs = stmt.executeQuery(getPColumns);

            ArrayList<String> columns = new ArrayList<>();
            while (rs.next()) {
                columns.add(rs.getString("name"));
            }

            String getRows = "SELECT * FROM partidas;";
            rs = stmt.executeQuery(getRows);
            String[] row = new String[columns.size()];
            ArrayList<String[]> rows = new ArrayList<>();
            while (rs.next()) {
                for (int i = 0; i < columns.size(); i++) {
                    String placeHolder = rs.getString(columns.get(i));
                    // Si está incompleto, añade un NULL.
                    row[i] = placeHolder == null ? "NULL" : placeHolder;
                }
                rows.add(row);
                row = new String[columns.size()];
            }
            rs.close();
            stmt.close();
            return new CustomTableFormat(columns, rows);
        }
        return null;
    }

    public static CustomTableFormat getUsers() throws SQLException {
        if (CreateDatabase.c != null) {
            Statement stmt = CreateDatabase.c.createStatement();
            String getPColumns = "PRAGMA table_info('usuarios');";
            ResultSet rs = stmt.executeQuery(getPColumns);

            ArrayList<String> columns = new ArrayList<>();
            while (rs.next()) {
                columns.add(rs.getString("name"));
            }

            String getRows = "SELECT * FROM usuarios;";
            rs = stmt.executeQuery(getRows);
            String[] row = new String[columns.size()];
            ArrayList<String[]> rows = new ArrayList<>();
            while (rs.next()) {
                for (int i = 0; i < columns.size(); i++) {
                    String placeHolder = rs.getString(columns.get(i));
                    // Si está incompleto, añade un NULL.
                    row[i] = placeHolder == null ? "NULL" : placeHolder;
                }
                rows.add(row);
                row = new String[columns.size()];
            }
            rs.close();
            stmt.close();
            return new CustomTableFormat(columns, rows);
        }
        return null;
    }

    public static boolean deleteUser(String nUser) {
        String query = String.format("DELETE FROM usuarios WHERE usuario = '%s';", nUser);
        if (CreateDatabase.c != null) {
            try {
                Statement stmt = CreateDatabase.c.createStatement();
                stmt.executeUpdate(query);
                stmt.close();
                CreateDatabase.c.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}