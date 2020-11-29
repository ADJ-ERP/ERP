package buttonActions;

import database.Query;
import utils.StringUtils;

import javax.swing.*;
import java.sql.SQLException;

public class RegUserAction {
    public static void registerUser(String user, String pass, String verification) throws SQLException {
        if (!pass.equals(verification)) {
            JOptionPane.showMessageDialog(null, "La contraseña y su verificación no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!StringUtils.rEmpty(user, pass)) {
            Query.register(user, pass);
        }
        else JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
