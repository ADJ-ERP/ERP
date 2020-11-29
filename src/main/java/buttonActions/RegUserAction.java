package buttonActions;

import database.Query;
import utils.StringUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.Base64;

public class RegUserAction {
    public static void registerUser(String user, String pass, String verification) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (!pass.equals(verification)) {
            JOptionPane.showMessageDialog(null, "La contraseña y su verificación no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!StringUtils.rEmpty(user, pass)) {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            Query.register(user, enc.encodeToString(hash));
        }
        else JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
