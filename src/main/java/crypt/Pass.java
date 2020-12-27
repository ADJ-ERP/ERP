package crypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Pass {
    public static final String ALGORITHM = "PBKDF2WithHmacSHA512";  // Algoritmo usado para hashear.

    public static String hashPass(String pass) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        try {
            return hash(salt, pass);
        }
        catch (InvalidKeySpecException | NoSuchAlgorithmException ignored) {
        }
        return null;
    }

    private static String hash(byte[] salt, String passwd) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeySpec spec = new PBEKeySpec(passwd.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        return String.format("%s:%s:%s", ALGORITHM, enc.encodeToString(salt), enc.encodeToString(hash));  // El output final tiene este formato: algoritmo:salt:hash.
    }

    public static boolean authenticate(String passwd, String hashed) {
        if (hashed == null) {
            return false;
        }

        String[] res = hashed.split(":");

        if (res.length != 3) {
            return false;
        }

        Base64.Decoder dec = Base64.getDecoder();
        byte[] salt = dec.decode(res[1]);  // Sacamos el salt y hasheamos la nueva contraseña con ese mismo hash para comprobar si son iguales.

        try {
            return hash(salt, passwd).equals(hashed);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ignored) {
        }

        return false;
    }
}
