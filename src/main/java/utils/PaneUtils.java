package utils;

import javax.swing.*;

public class PaneUtils {
    public static boolean confirmation(String msg) {
        return JOptionPane.showConfirmDialog(null, msg) == JOptionPane.YES_OPTION;
    }
}
