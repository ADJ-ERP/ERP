package userManagement;

import helpers.userAction;
import utils.LanguageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

public class RegisterSuperUser {
    static {
        WIDTH = 350;
        HEIGHT = 500;
    }
    public JFrame frame = new JFrame(LanguageUtils.getTranslation("users.register", "Registrar"));
    private JTextField userInput;
    private JTextField passwordInput;
    private JTextField rePasswordInput;

    private JLabel welcomeLabel;
    private JLabel userLabel;
    private JLabel passwordLabel;

    private JLabel rePasswordLabel;

    private JButton submitButton;
    private JButton loginButton;

    private static final int WIDTH;
    private static final int HEIGHT;

    public RegisterSuperUser() {
        createJField();
        createJLabel();
        createJButton();

        addStuffs();

        createJFrame();

        setComponentBounds();
    }

    private void createJField() {  // Crear todos los Fields.
        userInput = (JTextField) createJThing(0, "");
        passwordInput = (JTextField) createJThing(0, "");
        rePasswordInput = (JTextField) createJThing(0, "");
    }

    private void createJLabel() {  // Crear todos los Labels.
        welcomeLabel = (JLabel) createJThing(1, LanguageUtils.getTranslation("users.Superwelcome", "Bienvenido crea el Superusuario!"));
        userLabel = (JLabel) createJThing(1, LanguageUtils.getTranslation("users.Superusername", "Nombre de Superusuario"));
        passwordLabel = (JLabel) createJThing(1, LanguageUtils.getTranslation("users.Superpassword", "Contraseña de Superusuario"));
        rePasswordLabel = (JLabel) createJThing(1, LanguageUtils.getTranslation("users.repassword", "Repetir contraseña"));
    }

    private void createJButton() {  // Crear el botón de registro.
        submitButton = (JButton) createJThing(2, LanguageUtils.getTranslation("users.register", "Registrar"));
        submitButton.setBackground(Color.WHITE);
        submitButton.addActionListener(actionEvent -> {
            try {
                userAction.registerSuperUser(userInput.getText(), passwordInput.getText(), rePasswordInput.getText(),"admin",this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        loginButton = (JButton) createJThing(2, LanguageUtils.getTranslation("users.alreadyAcc", "Ya tengo cuenta"));  // Iniciar sesión si ya tienes cuenta.
        loginButton.setBackground(Color.WHITE);
        loginButton.addActionListener(actionEvent -> {
            frame.dispose();
            Usuarios usr = new Usuarios();
            usr.frame.setVisible(true);
        });
    }

    private void createJFrame() {  // Crear el Frame principal.
        frame.setBounds(100, 100, WIDTH, HEIGHT);  // Dimensiones por defecto.
        frame.setLayout(new GroupLayout(frame.getContentPane()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));  // Dimensiones mínimas.
        frame.getContentPane().setBackground(Color.WHITE);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                setComponentBounds();  // Actualizar de forma dinámica las dimensiones según se re-dimensione la ventana.
            }
        });
    }

    private Object createJThing(int type, String text) {
        Object thing;
        switch (type) {
            case 0:
                thing = new JTextField();
                ((JTextField) thing).setFont(new Font("Arial", Font.PLAIN, 15));
                ((JTextField) thing).setMargin(new Insets(1, 1, 1, 1));
                break;
            case 1:
                thing = new JLabel();
                ((JLabel) thing).setFont(new Font("Arial", Font.BOLD, 15));
                ((JLabel) thing).setText(text);
                break;
            case 2:
                thing = new JButton();
                ((JButton) thing).setFont(new Font("Arial", Font.BOLD, 15));
                ((JButton) thing).setText(text);
                break;
            default:
                throw new IllegalStateException(String.format("Unexpected value %d", type));
        }
        return thing;
    }

    private void setComponentBounds() {  // Las dimensiones de los components se crean según las dimensiones de la ventana principal.
        float width = frame.getWidth();
        float height = frame.getHeight();

        int marginLeft = (int) width / 6;
        int marginRight = (int) width - marginLeft;
        int horSize = marginRight - marginLeft;

        int verSize = (int) height / 14;
        int verPos = verSize + 20;

        welcomeLabel.setBounds((int) ((width / 2) - welcomeLabel.getWidth() / 2), 15, 95, verSize);

        userLabel.setBounds(marginLeft, verPos, horSize, verSize);
        userInput.setBounds(marginLeft, verPos + verSize, horSize, verSize);

        verPos = verPos + (2 * verSize) + 15;

        passwordLabel.setBounds(marginLeft, verPos, horSize, verSize);
        passwordInput.setBounds(marginLeft, verPos + verSize, horSize, verSize);

        verPos = verPos + (2 * verSize) + 15;

        rePasswordLabel.setBounds(marginLeft, verPos, horSize, verSize);
        rePasswordInput.setBounds(marginLeft, verPos + verSize, horSize, verSize);

        verPos = verPos + (2 * verSize) + 15;


        submitButton.setBounds((int) ((width / 2) - submitButton.getWidth() / 2), (int) (11.5 * verSize), 150, verSize);
        loginButton.setBounds((int) ((width / 2) - loginButton.getWidth() / 2), (int) (13.3 * verSize), 200, verSize);
    }

    private void addStuffs() {  // Añadir components al frame.
        frame.add(welcomeLabel);

        frame.add(userInput);
        frame.add(passwordInput);
        frame.add(rePasswordInput);


        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(rePasswordLabel);



        frame.add(submitButton);
        frame.add(loginButton);
    }

    public void success() {  // acciones onSuccess.
        frame.dispose();
    }

    public void error() {  // acciones onError.
        passwordInput.setText("");
        rePasswordInput.setText("");

    }
}
