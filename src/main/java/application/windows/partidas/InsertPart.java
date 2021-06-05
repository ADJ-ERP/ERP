package application.windows.partidas;

import application.tables.PartidaTable;
import database.Query;
import database.tables.PartidaDB;
import utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertPart extends JFrame {

    private final JPanel container;

    private final PartidaTable partidaTable;

    private JLabel numeroPartidaLabel;
    private JLabel fechaAltaLabel;
    private JLabel tipoLabel;
    private JLabel centroVentaLabel;
    private JLabel numeroMataderoLabel;
    private JLabel proveedorLabel;
    private JLabel numeroExplLabel;
    private JLabel paisNacimientoLabel;
    private JLabel paisSacrificioLabel;
    private JLabel tipoAnimalLabel;
    private JLabel totalAnimalesLabel;
    private JLabel delNumLabel;
    private JLabel alNumLabel;
    private JLabel totalKGBrutoLabel;
    private JLabel porOreoLabel;
    private JLabel totalKGNetoLabel;
    private JLabel costoTotalLabel;
    private JLabel notasLabel;

    private JTextField numeroPartidaTextField;
    private JTextField fechaAltaTextField;
    private JTextField tipoTextField;
    private JTextField centroVentaTextField;
    private JTextField numeroMataderoTextField;
    private JTextField proveedorTextField;
    private JTextField numeroExplTextField;
    private JTextField paisNacimientoTextField;
    private JTextField paisSacrificioTextField;
    private JTextField tipoAnimalTextField;
    private JTextField totalAnimalesTextField;
    private JTextField delNumTextField;
    private JTextField alNumTextField;
    private JTextField totalKGBrutoTextField;
    private JTextField porOreoTextField;
    private JTextField totalKGNetoTextField;
    private JTextField costoTotalTextField;
    private JTextField notasTextField;

    private JButton submitButton;


    public InsertPart(PartidaTable partidaTable) {
        this.partidaTable = partidaTable;

        container = new JPanel();
        container.setLayout(null);

        createJLabel();

        createJTextField();

        createJButton();

        addStuffs(numeroPartidaLabel, fechaAltaLabel, tipoLabel, centroVentaLabel, numeroMataderoLabel,
                proveedorLabel, numeroExplLabel, paisNacimientoLabel, paisSacrificioLabel, tipoAnimalLabel,
                totalAnimalesLabel, delNumLabel, alNumLabel, totalKGBrutoLabel, porOreoLabel, totalKGNetoLabel,
                costoTotalLabel, notasLabel, numeroPartidaTextField, fechaAltaTextField, tipoTextField,
                centroVentaTextField, numeroMataderoTextField, proveedorTextField, numeroExplTextField,
                paisNacimientoTextField, paisSacrificioTextField, tipoAnimalTextField, totalAnimalesTextField,
                delNumTextField, alNumTextField, totalKGBrutoTextField, porOreoTextField, totalKGNetoTextField,
                costoTotalTextField, notasTextField, submitButton);

        createJFrame();
    }

    private void createJButton() {
        submitButton = new JButton();
        submitButton.setText("Aceptar");
        submitButton.setBounds(500, 450, 150, 30);
        submitButton.addActionListener(actionEvent -> {
            if (inputCheck()) {
                PartidaDB partidaDB = new PartidaDB(
                        Integer.parseInt(numeroPartidaTextField.getText()),
                        fechaAltaTextField.getText(),
                        tipoTextField.getText(),
                        centroVentaTextField.getText(),
                        Integer.parseInt(numeroMataderoTextField.getText()),
                        proveedorTextField.getText(),
                        Integer.parseInt(numeroExplTextField.getText()),
                        paisNacimientoTextField.getText(),
                        paisSacrificioTextField.getText(),
                        tipoAnimalTextField.getText(),
                        Integer.parseInt(totalAnimalesTextField.getText()),
                        Integer.parseInt(delNumTextField.getText()),
                        Integer.parseInt(alNumTextField.getText()),
                        Integer.parseInt(totalKGBrutoTextField.getText()),
                        Integer.parseInt(porOreoTextField.getText()),
                        Integer.parseInt(totalKGNetoTextField.getText()),
                        Integer.parseInt(costoTotalTextField.getText()),
                        notasTextField.getText()
                );
                boolean executed = Query.registerPartida(partidaDB);
                if (!executed) JOptionPane.showMessageDialog(null, "Error al insertar!", "ERROR", JOptionPane.ERROR_MESSAGE);
                close();
            }
        });
    }

    private void createJFrame() {
        setBounds(100, 100, 770, 550);
        setTitle("Registrar Partida");
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

    private void createJLabel() {
        numeroPartidaLabel = labels("Número Partida");
        numeroPartidaLabel.setBounds(20, 20, 150, 30);

        fechaAltaLabel = labels("Fecha Alta");
        fechaAltaLabel.setBounds(20, 70, 150, 30);

        tipoLabel = labels("Tipo");
        tipoLabel.setBounds(20, 120, 150, 30);

        centroVentaLabel = labels("Centro Venta");
        centroVentaLabel.setBounds(20, 170, 150, 30);

        numeroMataderoLabel = labels("Número Matadero");
        numeroMataderoLabel.setBounds(20, 220, 150, 30);

        proveedorLabel = labels("Proveedor");
        proveedorLabel.setBounds(20, 270, 150, 30);

        numeroExplLabel = labels("Número Explotación");
        numeroExplLabel.setBounds(20, 320, 150, 30);

        paisNacimientoLabel = labels("País Nacimiento");
        paisNacimientoLabel.setBounds(20, 370, 150, 30);

        paisSacrificioLabel = labels("País Sacrificio");
        paisSacrificioLabel.setBounds(20, 420, 150, 30);

        tipoAnimalLabel = labels("Tipo Animal");
        tipoAnimalLabel.setBounds(20, 470, 150, 30);

        totalAnimalesLabel = labels("Total Animales");
        totalAnimalesLabel.setBounds(400, 20, 150, 30);

        delNumLabel = labels("Del Número");
        delNumLabel.setBounds(400, 70, 150, 30);

        alNumLabel = labels("Al Número");
        alNumLabel.setBounds(400, 120, 150, 30);

        totalKGBrutoLabel = labels("Total KG Brutos");
        totalKGBrutoLabel.setBounds(400, 170, 150, 30);

        porOreoLabel = labels("Porcentaje Oreo");
        porOreoLabel.setBounds(400, 220, 150, 30);

        totalKGNetoLabel = labels("Total KG Netos");
        totalKGNetoLabel.setBounds(400, 270, 150, 30);

        costoTotalLabel = labels("Costo Total");
        costoTotalLabel.setBounds(400, 320, 150, 30);

        notasLabel = labels("Notas");
        notasLabel.setBounds(400, 370, 150, 30);
    }

    private void createJTextField() {
        numeroPartidaTextField = field();
        numeroPartidaTextField.setBounds(170, 20, 180, 30);

        fechaAltaTextField = field();
        fechaAltaTextField.setBounds(170, 70, 180, 30);

        tipoTextField = field();
        tipoTextField.setBounds(170, 120, 180, 30);

        centroVentaTextField = field();
        centroVentaTextField.setBounds(170, 170, 180, 30);

        numeroMataderoTextField = field();
        numeroMataderoTextField.setBounds(170, 220, 180, 30);

        proveedorTextField = field();
        proveedorTextField.setBounds(170, 270, 180, 30);

        numeroExplTextField = field();
        numeroExplTextField.setBounds(170, 320, 180, 30);

        paisNacimientoTextField = field();
        paisNacimientoTextField.setBounds(170, 370, 180, 30);

        paisSacrificioTextField = field();
        paisSacrificioTextField.setBounds(170, 420, 180, 30);

        tipoAnimalTextField = field();
        tipoAnimalTextField.setBounds(170, 470, 180, 30);

        totalAnimalesTextField = field();
        totalAnimalesTextField.setBounds(560, 20, 180, 30);

        delNumTextField = field();
        delNumTextField.setBounds(560, 70, 180, 30);

        alNumTextField = field();
        alNumTextField.setBounds(560, 120, 180, 30);

        totalKGBrutoTextField = field();
        totalKGBrutoTextField.setBounds(560, 170, 180, 30);

        porOreoTextField = field();
        porOreoTextField.setBounds(560, 220, 180, 30);

        totalKGNetoTextField = field();
        totalKGNetoTextField.setBounds(560, 270, 180, 30);

        costoTotalTextField = field();
        costoTotalTextField.setBounds(560, 320, 180, 30);

        notasTextField = field();
        notasTextField.setBounds(560, 370, 180, 30);
    }

    public void addStuffs(Component... components) {  // Añadir components al frame.
        for (Component component : components) {
            container.add(component);
        }
        this.add(container);
    }

    private void close() {
        partidaTable.refresh();
        dispose();
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
        if (!StringUtils.areNotEmpty(numeroPartidaTextField.getText(), fechaAltaTextField.getText(),
                tipoTextField.getText(), centroVentaTextField.getText(), numeroMataderoTextField.getText(),
                proveedorTextField.getText(), numeroExplTextField.getText(), paisNacimientoTextField.getText(),
                paisSacrificioTextField.getText(), tipoAnimalTextField.getText(), totalAnimalesTextField.getText(),
                delNumTextField.getText(), alNumTextField.getText(), totalKGBrutoTextField.getText(),
                porOreoTextField.getText(), totalKGNetoTextField.getText(), costoTotalTextField.getText(),
                notasTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Debes completar todos los campos!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (StringUtils.isNotInt(numeroPartidaTextField.getText(), numeroMataderoTextField.getText(),
                numeroExplTextField.getText(), totalAnimalesTextField.getText(), delNumTextField.getText(),
                alNumTextField.getText(), totalKGBrutoTextField.getText(), porOreoTextField.getText(),
                totalKGNetoTextField.getText(), costoTotalTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Has introducido letras donde van números :(", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
