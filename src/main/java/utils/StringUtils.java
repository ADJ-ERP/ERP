/*
Esta clase es para manejar todas las cosas que tenga que ver con los jText
*/

package utils;

public class StringUtils {
    public static boolean areNotEmpty(String... list) {  // comprobamos que no este vacío.
        for (String word : list) {
            if (word.trim().equals("")) return false;
        }
        return true;
    }

    public static boolean isNotInt(String... ints) {  // Bueno... eso.
        try {
            for (String s : ints) {
                Integer.parseInt(s);
            }
            return false;
        }
        catch (Exception e) {
            return true;
        }
    }

    public static boolean isNotDouble(String... doubles) {
        try {
            for (String s : doubles) {
                Double.parseDouble(s);
            }
            return false;
        }
        catch (Exception e) {
            return true;
        }
    }
}
