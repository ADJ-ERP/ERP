/*
Esta clase es para manejar todas las cosas que tenga que ver con los jText
*/

package utils;

public class StringUtils {
    public static boolean rEmpty(String... list) {  // comprobamos que no este vacio.
        for (String word : list) {
            if (word.trim().equals("")) return true;
        }
        return false;
    }
}
