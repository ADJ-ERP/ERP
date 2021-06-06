package application.windows.clientes;

import application.tables.ClienteTable;
import application.tables.PartidaTable;
import database.Query;
import database.tables.ClientDB;
import utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertClient extends JFrame {
    private final JPanel container;

    private final ClienteTable clienteTable;

    private JLabel nombreLabel;
    private JLabel cifLabel;
    private JLabel telefonoLabel;
    private JLabel correoLabel;
    private JLabel direccionLabel;

    private JTextField nombreField;
    private JTextField cifField;
    private JTextField telefonoField;
    private JTextField correoField;
    private JTextField direccionField;

    private JButton submitButton;

    public InsertClient(ClienteTable clienteTable) {
        this.clienteTable = clienteTable;

        container = new JPanel();
        container.setLayout(null);

        createJLabel();

        createJTextField();

        createJButton();

        addStuffs(
                nombreLabel,
                cifLabel,
                telefonoLabel,
                correoLabel,
                direccionLabel,
                nombreField,
                cifField,
                telefonoField,
                correoField,
                direccionField,
                submitButton
        );

        createJFrame();
    }

    private void createJFrame() {
        setBounds(100, 100, 380, 370);
        setTitle("Registrar Cliente");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                close();
            }
        });
    }

    public void addStuffs(Component... components) {  // Añadir components al frame.
        for (Component component : components) {
            container.add(component);
        }
        this.add(container);
    }

    private void close() {
        clienteTable.refresh();
        dispose();
    }

    private void createJButton() {
        submitButton = new JButton();
        submitButton.setText("Aceptar");
        submitButton.setBounds(100, 280, 150, 30);
        submitButton.addActionListener(actionEvent -> {
            if (inputCheck()) {
                ClientDB clientDB = new ClientDB(
                        nombreField.getText(),
                        cifField.getText(),
                        telefonoField.getText(),
                        correoField.getText(),
                        direccionField.getText()
                );
                boolean executed = Query.registerClient(clientDB);
                if (!executed) JOptionPane.showMessageDialog(null, "Error al insertar!", "ERROR", JOptionPane.ERROR_MESSAGE);
                close();
            }
        });
    }

    private void createJTextField() {
        nombreField = field();
        nombreField.setBounds(170, 20, 180, 30);

        cifField = field();
        cifField.setBounds(170, 70, 180, 30);

        telefonoField = field();
        telefonoField.setBounds(170, 120, 180, 30);

        correoField = field();
        correoField.setBounds(170, 170, 180, 30);

        direccionField = field();
        direccionField.setBounds(170, 220, 180, 30);
    }

    private void createJLabel() {
        nombreLabel = labels("Nombre");
        nombreLabel.setBounds(20, 20, 150, 30);

        cifLabel = labels("CIF/DNI");
        cifLabel.setBounds(20, 70, 150, 30);

        telefonoLabel = labels("Teléfono");
        telefonoLabel.setBounds(20, 120, 150, 30);

        correoLabel = labels("Correo");
        correoLabel.setBounds(20, 170, 150, 30);

        direccionLabel = labels("Dirección");
        direccionLabel.setBounds(20, 220, 150, 30);
    }

    private JLabel labels(String text) {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setText(text);
        return label;
    }

    private JTextField field() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setMargin(new Insets(1, 1, 1, 1));
        return field;
    }

    private boolean inputCheck() {
        if (!StringUtils.areNotEmpty(
                nombreField.getText(),
                cifField.getText(),
                telefonoField.getText(),
                correoField.getText(),
                direccionField.getText()
        )) {
            JOptionPane.showMessageDialog(null, "Debes completar todos los campos!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
