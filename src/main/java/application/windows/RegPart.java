package application.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegPart extends JFrame {

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


    public RegPart() {
        createJLabel();

        addStuffs(numeroPartidaLabel, fechaAltaLabel, tipoLabel, centroVentaLabel, numeroMataderoLabel,
                proveedorLabel, numeroExplLabel, paisNacimientoLabel, paisSacrificioLabel, tipoAnimalLabel,
                totalAnimalesLabel, delNumLabel, alNumLabel, totalKGBrutoLabel, porOreoLabel, totalKGNetoLabel,
                costoTotalLabel, notasLabel);

        createJFrame();
    }

    private void createJFrame() {
        setBounds(100, 100, 840, 550);
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

    public void addStuffs(Component... components) {  // Añadir components al frame.
        for (Component component : components) {
            this.add(component);
        }
    }

    private void close() {
        dispose();
    }

    private JLabel labels(String text) {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setText(text);
        return label;
    }
}
