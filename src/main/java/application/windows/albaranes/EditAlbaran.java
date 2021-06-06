package application.windows.albaranes;

import application.tables.AlbaranTable;
import database.Query;
import database.tables.AlbaranDB;
import utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditAlbaran extends JFrame {
    private final AlbaranDB albaranDB;

    private final JPanel container;

    private final AlbaranTable albaranTable;

    private JLabel codigoLabel;
    private JLabel fechaLabel;
    private JLabel cantidadKGLabel;
    private JLabel descripcionLabel;
    private JLabel precioKGLabel;
    private JLabel precioTotalLabel;

    private JTextField codigoField;
    private JTextField fechaField;
    private JTextField cantidadField;
    private JTextField descripcionField;
    private JTextField precioField;
    private JTextField precioTotalField;

    private JButton submitButton;

    public EditAlbaran(AlbaranTable albaranTable, AlbaranDB albaranDB) {
        this.albaranTable = albaranTable;

        this.albaranDB = albaranDB;

        container = new JPanel();
        container.setLayout(null);

        createJLabel();

        createJTextField();

        createJButton();

        addStuffs(
                codigoLabel,
                fechaLabel,
                cantidadKGLabel,
                descripcionLabel,
                precioKGLabel,
                precioTotalLabel,
                codigoField,
                fechaField,
                cantidadField,
                descripcionField,
                precioField,
                precioTotalField,
                submitButton
        );

        createJFrame();
    }

    private void createJButton() {
        submitButton = new JButton();
        submitButton.setText("Aceptar");
        submitButton.setBounds(100, 280, 150, 30);
        submitButton.addActionListener(actionEvent -> {
            if (inputCheck()) {
                AlbaranDB albaranDB = new AlbaranDB(
                        codigoField.getText(),
                        fechaField.getText(),
                        Double.parseDouble(cantidadField.getText()),
                        descripcionField.getText(),
                        Double.parseDouble(precioField.getText()),
                        Double.parseDouble(precioTotalField.getText())
                );
                boolean executed = Query.editAlbaran(albaranDB);
                if (!executed) JOptionPane.showMessageDialog(null, "Error al editar!", "ERROR", JOptionPane.ERROR_MESSAGE);
                close();
            }
        });
    }

    private void createJFrame() {
        setBounds(100, 100, 380, 400);
        setTitle("Registrar Albaran");
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

    private void close() {
        albaranTable.refresh();
        dispose();
    }

    public void addStuffs(Component... components) {  // Añadir components al frame.
        for (Component component : components) {
            container.add(component);
        }
        this.add(container);
    }

    private void createJTextField() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
                try {
                    Double cantidad = Double.parseDouble(cantidadField.getText());
                    Double precio = Double.parseDouble(precioField.getText());
                    precioTotalField.setText(String.valueOf(cantidad * precio));
                } catch (NumberFormatException formatException) {
                    precioTotalField.setText("");
                }
            }
        };

        codigoField = field();
        codigoField.setText(albaranDB.getCodigo());
        codigoField.setEditable(false);
        codigoField.setBounds(170, 20, 180, 30);

        fechaField = field();
        fechaField.setText(albaranDB.getFecha());
        fechaField.setEditable(false);
        fechaField.setBounds(170, 70, 180, 30);

        cantidadField = field();
        cantidadField.setText(String.valueOf(albaranDB.getCantidadKG()));
        cantidadField.addKeyListener(keyListener);
        cantidadField.setBounds(170, 120, 180, 30);

        descripcionField = field();
        descripcionField.setText(albaranDB.getDescripcion());
        descripcionField.setBounds(170, 170, 180, 30);

        precioField = field();
        precioField.setText(String.valueOf(albaranDB.getPrecioKG()));
        precioField.addKeyListener(keyListener);
        precioField.setBounds(170, 220, 180, 30);

        precioTotalField = field();
        precioTotalField.setText(String.valueOf(albaranDB.getPrecioTotal()));
        precioTotalField.setEditable(false);
        precioTotalField.setBounds(170, 270, 180, 30);
    }

    private void createJLabel() {
        codigoLabel = labels("Código");
        codigoLabel.setBounds(20, 20, 180, 30);

        fechaLabel = labels("Fecha");
        fechaLabel.setBounds(20, 70, 180, 30);

        cantidadKGLabel = labels("Cantidad(KG)");
        cantidadKGLabel.setBounds(20, 120, 180, 30);

        descripcionLabel = labels("Descripción");
        descripcionLabel.setBounds(20, 170, 180, 30);

        precioKGLabel = labels("Precio/KG");
        precioKGLabel.setBounds(20, 220, 180, 30);

        precioTotalLabel = labels("Precio Total");
        precioTotalLabel.setBounds(20, 270, 180, 30);
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
                codigoField.getText(),
                cantidadField.getText(),
                descripcionField.getText(),
                precioField.getText(),
                precioTotalField.getText()
        )) {
            JOptionPane.showMessageDialog(null, "Debes completar todos los campos!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!StringUtils.isNotDouble(
                cantidadField.getText(),
                precioField.getText(),
                precioField.getText()
        )) {
            JOptionPane.showMessageDialog(null, "No has puesto bien KG o precios.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
