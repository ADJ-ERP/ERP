/*
Esta clase es para manejar las Queries realizadas en la Base de Datos
*/

package database;

import database.tables.AlbaranDB;
import database.tables.ClientDB;
import database.tables.PartidaDB;
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

    public static boolean registerPartida(PartidaDB partidaDB) {  // Registra usuarios.
        String query = "INSERT OR IGNORE INTO partidas(numPartida, fechaAlta, tipo, centroVenta, numMatadero, "
                + "proveedor, numExplotacion, paisNacido, paisSacrificado, tipoAnimal, totalAnimales, "
                + "delNum, alNum, totalKgBrutos, porcenOreo, totalkgNetos, importeTotalCosto, notas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        if (CreateDatabase.c != null) {  // Comprueba que la Base de Datos este creada.
            PreparedStatement stmt;  // Evitar que pongan inputs no deseados para acceder a información privada o tirarla.
            try {
                stmt = CreateDatabase.c.prepareStatement(query);
                stmt.setInt(1, partidaDB.getNumPartida());
                stmt.setString(2, partidaDB.getFechaAlta());
                stmt.setString(3, partidaDB.getTipo());
                stmt.setString(4, partidaDB.getCentroVenta());
                stmt.setInt(5, partidaDB.getNumMatadero());
                stmt.setString(6, partidaDB.getProveedor());
                stmt.setInt(7, partidaDB.getNumExplotacion());
                stmt.setString(8, partidaDB.getPaisNacido());
                stmt.setString(9, partidaDB.getPaisSacrificado());
                stmt.setString(10, partidaDB.getTipoAnimal());
                stmt.setInt(11, partidaDB.getTotalAnimales());
                stmt.setInt(12, partidaDB.getDelNum());
                stmt.setInt(13, partidaDB.getAlNum());
                stmt.setInt(14, partidaDB.getTotalKgBrutos());
                stmt.setInt(15, partidaDB.getPorcenOreo());
                stmt.setInt(16, partidaDB.getTotalKgNetos());
                stmt.setInt(17, partidaDB.getImporteTotalCosto());
                stmt.setString(18, partidaDB.getNotas());
                stmt.executeUpdate();  // Añade el usuario.
                stmt.close();
                CreateDatabase.c.commit();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean registerClient(ClientDB clientDB) {
        String query = "INSERT OR IGNORE INTO clientes(nombre, CIF, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?)";
        if (CreateDatabase.c != null) {
            try {
                PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
                stmt.setString(1, clientDB.getNombre());
                stmt.setString(2, clientDB.getCIF());
                stmt.setString(3, clientDB.getTelefono());
                stmt.setString(4, clientDB.getCorreo());
                stmt.setString(5, clientDB.getDireccion());
                stmt.executeUpdate();
                stmt.close();
                CreateDatabase.c.commit();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean registerAlbaran(AlbaranDB albaranDB) {
        String query = "INSERT OR IGNORE INTO clientes(codigo, fecha, cantidadKG, descripcion, precioKG, precioTotal) VALUES (?, ?, ?, ?, ?, ?)";
        if (CreateDatabase.c != null) {
            try {
                PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
                stmt.setString(1, albaranDB.getCodigo());
                stmt.setString(2, albaranDB.getFecha());
                stmt.setDouble(3, albaranDB.getCantidadKG());
                stmt.setString(4, albaranDB.getDescripcion());
                stmt.setDouble(5, albaranDB.getPrecioKG());
                stmt.setDouble(6, albaranDB.getPrecioTotal());
                stmt.executeUpdate();
                stmt.close();
                CreateDatabase.c.commit();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean editPartida(PartidaDB partidaDB) {
        String query = "UPDATE partidas SET fechaAlta = ?, tipo = ?, centroVenta = ?, numMatadero = ?, proveedor = ?, " +
                "numExplotacion = ?, paisNacido = ?, paisSacrificado = ?, tipoAnimal = ?, totalAnimales = ?, " +
                "delNum = ?, alNum = ?, totalKgBrutos = ?, porcenOreo = ?, totalkgNetos = ?, importeTotalCosto = ?, " +
                "notas = ? WHERE numPartida = ?";
        if (CreateDatabase.c != null) {
            try {
                PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
                stmt.setString(1, partidaDB.getFechaAlta());
                stmt.setString(2, partidaDB.getTipo());
                stmt.setString(3, partidaDB.getCentroVenta());
                stmt.setInt(4, partidaDB.getNumMatadero());
                stmt.setString(5, partidaDB.getProveedor());
                stmt.setInt(6, partidaDB.getNumExplotacion());
                stmt.setString(7, partidaDB.getPaisNacido());
                stmt.setString(8, partidaDB.getPaisSacrificado());
                stmt.setString(9, partidaDB.getTipoAnimal());
                stmt.setInt(10, partidaDB.getTotalAnimales());
                stmt.setInt(11, partidaDB.getDelNum());
                stmt.setInt(12, partidaDB.getAlNum());
                stmt.setInt(13, partidaDB.getTotalKgBrutos());
                stmt.setInt(14, partidaDB.getPorcenOreo());
                stmt.setInt(15, partidaDB.getTotalKgNetos());
                stmt.setInt(16, partidaDB.getImporteTotalCosto());
                stmt.setString(17, partidaDB.getNotas());

                stmt.setInt(18, partidaDB.getNumPartida());

                stmt.executeUpdate();
                stmt.close();
                CreateDatabase.c.commit();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean editClient(ClientDB clientDB) {
        String query = "UPDATE clientes SET nombre = ?, telefono = ?, correo = ?, direccion = ? WHERE CIF = ?";
        if (CreateDatabase.c != null) {
            try {
                PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
                stmt.setString(1, clientDB.getNombre());
                stmt.setString(2, clientDB.getTelefono());
                stmt.setString(3, clientDB.getCorreo());
                stmt.setString(4, clientDB.getDireccion());

                stmt.setString(5, clientDB.getCIF());
                stmt.executeUpdate();
                stmt.close();
                CreateDatabase.c.commit();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean editAlbaran(AlbaranDB albaranDB) {
        String query = "UPDATE albaranes SET fecha = ?, cantidadKG = ?, descripcion = ?, precioKG = ?, precioTotal = ? WHERE codigo = ?";
        if (CreateDatabase.c != null) {
            try {
                PreparedStatement stmt = CreateDatabase.c.prepareStatement(query);
                stmt.setString(1, albaranDB.getFecha());
                stmt.setDouble(2, albaranDB.getCantidadKG());
                stmt.setString(3, albaranDB.getDescripcion());
                stmt.setDouble(4, albaranDB.getPrecioKG());
                stmt.setDouble(5, albaranDB.getPrecioTotal());

                stmt.setString(6, albaranDB.getCodigo());
                stmt.executeUpdate();
                stmt.close();
                CreateDatabase.c.commit();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
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


    public static CustomTableFormat getClientes() throws SQLException {
        if (CreateDatabase.c != null) {
            Statement stmt = CreateDatabase.c.createStatement();
            String getPColumns = "PRAGMA table_info('clientes');";
            ResultSet rs = stmt.executeQuery(getPColumns);

            ArrayList<String> columns = new ArrayList<>();
            while (rs.next()) {
                columns.add(rs.getString("name"));
            }
            String getRows = "SELECT * FROM clientes;";
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

    public static CustomTableFormat getAlbaranes() throws SQLException {
        if (CreateDatabase.c != null) {
            Statement stmt = CreateDatabase.c.createStatement();
            String getPColumns = "PRAGMA table_info('albaranes');";
            ResultSet rs = stmt.executeQuery(getPColumns);

            ArrayList<String> columns = new ArrayList<>();
            while (rs.next()) {
                columns.add(rs.getString("name"));
            }
            String getRows = "SELECT * FROM albaranes;";
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

    public static PartidaDB getPartida(int nPartida) throws SQLException {
        if (CreateDatabase.c != null) {
            String getPart = String.format("SELECT * FROM partidas WHERE numPartida = %d", nPartida);
            Statement stmt = CreateDatabase.c.createStatement();
            ResultSet rs = stmt.executeQuery(getPart);
            PartidaDB partidaDB = new PartidaDB(
                    rs.getInt("numPartida"),
                    rs.getString("fechaAlta"),
                    rs.getString("tipo"),
                    rs.getString("centroVenta"),
                    rs.getInt("numMatadero"),
                    rs.getString("proveedor"),
                    rs.getInt("numExplotacion"),
                    rs.getString("paisNacido"),
                    rs.getString("paisSacrificado"),
                    rs.getString("tipoAnimal"),
                    rs.getInt("totalAnimales"),
                    rs.getInt("delNum"),
                    rs.getInt("alNum"),
                    rs.getInt("totalKgBrutos"),
                    rs.getInt("porcenOreo"),
                    rs.getInt("totalkgNetos"),
                    rs.getInt("importeTotalCosto"),
                    rs.getString("notas")
            );
            rs.close();
            stmt.close();
            return partidaDB;
        }
        return null;
    }

    public static ClientDB getCliente(String cif) throws SQLException {
        if (CreateDatabase.c != null) {
            String getPart = String.format("SELECT * FROM clientes WHERE CIF = %s", cif);
            Statement stmt = CreateDatabase.c.createStatement();
            ResultSet rs = stmt.executeQuery(getPart);
            ClientDB clientDB = new ClientDB(
                    rs.getString("nombre"),
                    rs.getString("CIF"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("direccion")
            );
            rs.close();
            stmt.close();
            return clientDB;
        }
        return null;
    }

    public static AlbaranDB getAlbaran(String codigo) throws SQLException {
        if (CreateDatabase.c != null) {
            String getPart = String.format("SELECT * FROM albaranes WHERE codigo = %s", codigo);
            Statement stmt = CreateDatabase.c.createStatement();
            ResultSet rs = stmt.executeQuery(getPart);
            AlbaranDB albaranDB = new AlbaranDB(
                    rs.getString("codigo"),
                    rs.getString("fecha"),
                    rs.getDouble("cantidadKG"),
                    rs.getString("descripcion"),
                    rs.getDouble("precioKG"),
                    rs.getDouble("precioTotal")
            );
            rs.close();
            stmt.close();
            return albaranDB;
        }
        return null;
    }

    public static boolean deletePartida(int nPartida) {
        String query = String.format("DELETE FROM partidas WHERE numPartida = %d;", nPartida);
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

    public static boolean deleteClient(String cif) {
        String query = String.format("DELETE FROM clientes WHERE CIF = %s;", cif);
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