package utils;

public class StringUtils {
    public static boolean rEmpty(String... list) {
        for (String word : list) {
            if (word.trim().equals("")) return true;
        }
        return false;
    }
}
