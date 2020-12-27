package main;

import buttonActions.userAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

public class Usuarios {
    static {
        WIDTH = 300;
        HEIGHT = 370;
    }

    private static final int WIDTH;
    private static final int HEIGHT;

    public JFrame frame = new JFrame("Iniciar sesión");

    private JTextField userInput;
    private JTextField passwordInput;

    private JLabel infoLabel;

    private JLabel userLabel;
    private JLabel passwordLabel;

    private JButton submitButton;
    private JButton createAccButton;

    public Usuarios() {
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
    }

    private void createJLabel() {  // Crear todos los Labels.
        infoLabel = (JLabel) createJThing(1, "Bienvenido!");
        userLabel = (JLabel) createJThing(1, "Nombre de usuario");
        passwordLabel = (JLabel) createJThing(1, "Contraseña");
    }

    private void createJButton() {  // Crear el botón de registro.
        submitButton = (JButton) createJThing(2, "Iniciar");
        submitButton.setBackground(Color.WHITE);
        submitButton.addActionListener(actionEvent -> {
            try {
                userAction.login(userInput.getText(), passwordInput.getText(), this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        createAccButton = (JButton) createJThing(2, "Crear cuenta");
        createAccButton.setBackground(Color.WHITE);
        createAccButton.addActionListener(actionEvent -> {  // registrar usuario nuevo.
            frame.dispose();
            RegistroUsuarios regUsr = new RegistroUsuarios();
            regUsr.frame.setVisible(true);
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

    private void addStuffs() {  // Añadir components al frame.
        frame.add(infoLabel);

        frame.add(userInput);
        frame.add(passwordInput);

        frame.add(userLabel);
        frame.add(passwordLabel);

        frame.add(submitButton);
        frame.add(createAccButton);
    }

    private void setComponentBounds() {  // Las dimensiones de los components se crean según las dimensiones de la ventana principal.
        float width = frame.getWidth();
        float height = frame.getHeight();

        int marginLeft = (int) width / 7;
        int marginRight = (int) width - marginLeft;
        int horSize = marginRight - marginLeft;

        int verSize = (int) height / 12;
        int verPos = verSize + 20;

        infoLabel.setBounds((int) ((width / 2) - infoLabel.getWidth() / 2), 15, 95, verSize);

        userLabel.setBounds(marginLeft, verPos, horSize, verSize);
        userInput.setBounds(marginLeft, verPos + verSize, horSize, verSize);

        verPos = verPos + (2 * verSize) + 15;

        passwordLabel.setBounds(marginLeft, verPos, horSize, verSize);
        passwordInput.setBounds(marginLeft, verPos + verSize, horSize, verSize);

        submitButton.setBounds((int) ((width / 2) - submitButton.getWidth() / 2), (int) (7.1 * verSize), 150, verSize);

        createAccButton.setBounds((int) ((width / 2) - createAccButton.getWidth() / 2), (int) (9.2 * verSize), 200, verSize);
    }

    public void success() {  // acciones onSuccess.
        frame.dispose();
    }

    public void error() {  // acciones onError.
        passwordInput.setText("");
    }
}
