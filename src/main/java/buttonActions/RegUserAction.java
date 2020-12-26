/*
Esta clase es para manejar el botón de Registrar Usuarios
*/

package buttonActions;

import crypt.Pass;
import database.Query;
import main.RegistroUsuarios;
import utils.StringUtils;

import javax.swing.*;
import java.sql.SQLException;

import main.Usuarios;

public class RegUserAction {
    public static void registerUser(String user, String pass, String verification, RegistroUsuarios regInstance) throws SQLException {
        if (!pass.equals(verification)) {  // Comprueba que la contraseña es igual que la de verificación.
            JOptionPane.showMessageDialog(null, "La contraseña y su verificación no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
            regInstance.error();
            return;
        }

        if (!StringUtils.rEmpty(user, pass)) {
            String hash = Pass.hashPass(pass);  // Hasheamos la contraseña.
            if (hash != null) {
                Query.register(user, hash);  // Registramos el usuario.
                regInstance.success();
                Usuarios usuario = new Usuarios();     
                usuario.setVisible(true);  // Mostramos la pantalla usuario
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
                regInstance.error();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
            regInstance.error();
        }
    }
}
