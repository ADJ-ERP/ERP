package main;

import buttonActions.RegUserAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class RegistroUsuarios {
    public JFrame frame = new JFrame("Registro de Usuarios");

    private JTextField userInput;
    private JTextField passwordInput;
    private JTextField rePasswordInput;

    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel rePasswordLabel;

    private JButton submitButton;

    public RegistroUsuarios() {
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
        userLabel = (JLabel) createJThing(1, "Nombre de usuario");
        passwordLabel = (JLabel) createJThing(1, "Contraseña");
        rePasswordLabel = (JLabel) createJThing(1, "Repetir contraseña");
    }

    private void createJButton() {  // Crear el botón de registro.
        submitButton = (JButton) createJThing(2, "Registrar");
        submitButton.addActionListener(actionEvent -> {
            try {
                RegUserAction.registerUser(userInput.getText(), passwordInput.getText(), rePasswordInput.getText(), this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void createJFrame() {  // Crear el Frame principal.
        frame.setBounds(100, 100, 300, 400);  // Dimensiones por defecto.
        frame.setLayout(new GroupLayout(frame.getContentPane()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 400));  // Dimensiones mínimas.
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
                ((JLabel) thing).setFont(new Font("Arial", Font.PLAIN, 15));
                ((JLabel) thing).setText(text);
                break;
            case 2:
                thing = new JButton();
                ((JButton) thing).setFont(new Font("Arial", Font.PLAIN, 15));
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

        int marginLeft = (int) width / 7;
        int marginRight = (int) width - marginLeft;
        int horSize = marginRight - marginLeft;

        int verSize = (int) height / 12;
        int verPos = verSize;

        userLabel.setBounds(marginLeft, verPos, horSize, verSize);
        userInput.setBounds(marginLeft, verPos + verSize, horSize, verSize);

        verPos = verPos + (2 * verSize) + 20;

        passwordLabel.setBounds(marginLeft, verPos, horSize, verSize);
        passwordInput.setBounds(marginLeft, verPos + verSize, horSize, verSize);

        verPos = verPos + (2 * verSize) + 20;

        rePasswordLabel.setBounds(marginLeft, verPos, horSize, verSize);
        rePasswordInput.setBounds(marginLeft, verPos + verSize, horSize, verSize);

        submitButton.setBounds((int) ((width / 2) - submitButton.getWidth() / 2), (int) (9.2 * verSize), 150, verSize);
    }

    private void addStuffs() {  // Añadir components al frame.
        frame.add(userInput);
        frame.add(passwordInput);
        frame.add(rePasswordInput);

        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(rePasswordLabel);

        frame.add(submitButton);
    }

    public void success() {  // acciones onSuccess.
        frame.dispose();
    }

    public void error() {  // acciones onError.
        passwordInput.setText("");
        rePasswordInput.setText("");
    }
}
