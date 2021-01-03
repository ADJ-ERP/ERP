package buttonActions;

import main.Principal;
import userManagement.Usuarios;

public class PrincipalActions {
    public static void onCloseSession(Principal p) {
        p.dispose();

        Usuarios usuario = new Usuarios();
        usuario.frame.setVisible(true);
    }
}
