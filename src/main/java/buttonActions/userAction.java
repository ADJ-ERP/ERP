/*
Esta clase es para manejar el botón de Registrar Usuarios
*/

package buttonActions;

import crypt.Pass;
import database.Query;
import application.Application;
import userManagement.RegistroUsuarios;
import userManagement.Usuarios;
import utils.LanguageUtils;
import utils.StringUtils;

import javax.swing.*;
import java.sql.SQLException;

public class userAction {
    public static void registerUser(String user, String pass, String verification, RegistroUsuarios regInstance) throws SQLException {
        user = user.trim();
        pass = pass.trim();
        verification = verification.trim();

        if (!pass.equals(verification)) {  // Comprueba que la contraseña es igual que la de verificación.
            JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.passNoMatch", "La contraseña y su verificación no coinciden."), "ERROR", JOptionPane.ERROR_MESSAGE);
            regInstance.error();
            return;
        }

        if (Query.userExists(user)) {  // Check de si el usuario que se intenta registrar existe.
            JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.userExists", "Ya existe un usuario con ese nombre de usuario."), "ERROR", JOptionPane.ERROR_MESSAGE);
            regInstance.error();
            return;
        }

        if (!StringUtils.rEmpty(user, pass)) {
            String hash = Pass.hashPass(pass);  // Hasheamos la contraseña.
            if (hash != null) {
                Query.register(user, hash);  // Registramos el usuario.
                regInstance.success();
                Usuarios usuario = new Usuarios();
                usuario.frame.setVisible(true);  // Mostramos la pantalla usuario
            } else {
                JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.registerError", "Error al registrar usuario."), "ERROR", JOptionPane.ERROR_MESSAGE);
                regInstance.error();
            }
        } else {
            JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.notFilled", "Debes completar todos los campos"), "ERROR", JOptionPane.ERROR_MESSAGE);
            regInstance.error();
        }
    }

    public static void login(String user, String pass, Usuarios usrInstance) throws SQLException {  // Intento de log.
        user = user.trim();
        pass = pass.trim();

        if (!StringUtils.rEmpty(user, pass)) {
            if (!Query.userExists(user)) {  // Check de si el usuario que se intenta registrar existe.
                JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.userNotExists", "El usuario introducido no existe."), "ERROR", JOptionPane.ERROR_MESSAGE);
                usrInstance.error();
                return;
            }

            String hash = Pass.hashPass(pass);
            if (hash == null) {
                JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.loginError", "Error al iniciar sesión"), "ERROR", JOptionPane.ERROR_MESSAGE);
                usrInstance.error();
                return;
            }

            if (Pass.authenticate(pass, Query.getHash(user))) {
                usrInstance.success();

                // Iniciamos la ventana principal del programa.
                Application app = new Application(user);
                app.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.passwordError", "Contraseña incorrecta."), "ERROR", JOptionPane.ERROR_MESSAGE);
                usrInstance.error();
            }
        } else {
            JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.notFilled", "Debes completar todos los campos"), "ERROR", JOptionPane.ERROR_MESSAGE);
            usrInstance.error();
        }
    }
}
