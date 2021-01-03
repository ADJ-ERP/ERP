/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttonActions;

import crypt.Pass;
import database.Query;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import main.RegistroPartida;
import utils.LanguageUtils;
import utils.StringUtils;

/**
 *
 * @author angel
 */
public class AddPartidaAction {
    public static void registerPartida(int numPartida, String fechaAlta, String tipo, String centroVenta, int numMatadero,
            String proveedor, int numExplotacion, String paisNacido, String paisSacrificado, String tipoAnimal, int totalAnimales, int delNum, 
            int alNum, int totalKgBrutos, int porcenOreo, int totalKgNetos, int importeTotalCosto, String notas, RegistroPartida aThis) throws SQLException {
        fechaAlta = fechaAlta.trim();
        tipo = tipo.trim();
        centroVenta = centroVenta.trim();
        proveedor = proveedor.trim();
        paisNacido = paisNacido.trim();
        paisSacrificado = paisSacrificado.trim();
        tipoAnimal = tipoAnimal.trim();
        notas = notas.trim();       

//        if (!pass.equals(verification)) {  // Comprueba que la contraseña es igual que la de verificación.
//            JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.passNoMatch", "La contraseña y su verificación no coinciden."), "ERROR", JOptionPane.ERROR_MESSAGE);
//            regInstance.error();
//            return;
//        }
//
//        if (Query.userExists(user)) {  // Check de si el usuario que se intenta registrar existe.
//            JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.userExists", "Ya existe un usuario con ese nombre de usuario."), "ERROR", JOptionPane.ERROR_MESSAGE);
//            regInstance.error();
//            return;
//        }
//
//        if (!StringUtils.rEmpty(user, pass)) {
//            String hash = Pass.hashPass(pass);  // Hasheamos la contraseña.
//            if (hash != null) {
//                Query.register(user, hash);  // Registramos el usuario.
//                regInstance.success();
//                Usuarios usuario = new Usuarios();
//                usuario.frame.setVisible(true);  // Mostramos la pantalla usuario
//            } else {
//                JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.registerError", "Error al registrar usuario."), "ERROR", JOptionPane.ERROR_MESSAGE);
//                regInstance.error();
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, LanguageUtils.getTranslation("error.notFilled", "Debes completar todos los campos"), "ERROR", JOptionPane.ERROR_MESSAGE);
//            regInstance.error();
//        }
    }
}
