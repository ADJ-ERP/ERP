/*
Esta clase es para manejar el boton de Registrar Usuarios
*/

package buttonActions;

import crypt.Pass;
import database.Query;
import utils.StringUtils;

import javax.swing.*;
import java.sql.SQLException;
import main.RegistroUsuario;
import main.Usuarios;

public class RegUserAction {
    public static void registerUser(String user, String pass, String verification) throws SQLException {
        if (!pass.equals(verification)) {// comprueba que la contraseña es igual que la de verificacion
            JOptionPane.showMessageDialog(null, "La contraseña y su verificación no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!StringUtils.rEmpty(user, pass)) {
            String hash = Pass.hashPass(pass);// hasheamos la contraseña 
            if (hash != null) {
                Query.register(user, hash);// resgistramos el ususario
                Usuarios usuario = new Usuarios();     
                usuario.setVisible(true);// mostramos la pantalla usuario
                RegistroUsuario reg = new RegistroUsuario();
                reg.setVisible(false);// escondemos la pantalla resgistro
            }
            else JOptionPane.showMessageDialog(null, "Error al registrar usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
