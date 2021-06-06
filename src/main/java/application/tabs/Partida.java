package application.tabs;

import application.tables.PartidaTable;
import application.windows.partidas.EditPart;
import application.windows.partidas.InsertPart;
import database.Query;
import database.tables.PartidaDB;
import utils.PaneUtils;
import utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

public class Partida extends Tab {

    private JButton registerButton;
    private JButton editButton;
    private JButton deleteButton;

    private PartidaTable partidaTable;

    private JScrollPane partidaScrollPane;

    public Partida(Boolean admin) {
        addStuffs(registerButton , editButton, deleteButton ,partidaScrollPane);
        if (!admin); registerButton.setEnabled(false);editButton.setEnabled(false);deleteButton.setEnabled(false);partidaScrollPane.setEnabled(false);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                setComponentBounds();
                revalidate();
                repaint();
            }
        });
    }

    @Override
    public void createJButton() {
        super.createJButton();
        registerButton = (JButton) createJThing(2, "Registrar Partida");
        registerButton.addActionListener(actionEvent -> {
            InsertPart insertPart = new InsertPart(partidaTable);
            insertPart.setVisible(true);
        });

        editButton = (JButton) createJThing(2, "Editar");
        editButton.addActionListener(actionEvent -> {
            String sNPartida;
            try {
                sNPartida = (String) partidaTable.getValueAt(
                        partidaTable.getSelectedRow(),
                        partidaTable.getColumn("numPartida").getModelIndex()
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (sNPartida == null || StringUtils.isNotInt(sNPartida)) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int nPartida = Integer.parseInt(sNPartida);

            try {
                PartidaDB partidaDB = Query.getPartida(nPartida);
                EditPart editPart = new EditPart(partidaTable, partidaDB);
                editPart.setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        deleteButton = (JButton) createJThing(2, "Eliminar");
        deleteButton.addActionListener(actionEvent -> {
            String sNPartida;
            try {
                sNPartida = (String) partidaTable.getValueAt(
                        partidaTable.getSelectedRow(),
                        partidaTable.getColumn("numPartida").getModelIndex()
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (sNPartida == null || StringUtils.isNotInt(sNPartida)) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int nPartida = Integer.parseInt(sNPartida);
            if (PaneUtils.confirmation(String.format("Vas a eliminar la partida número %d\nSeguro que quieres continuar?", nPartida))) {
                if (Query.deletePartida(nPartida)) {
                    partidaTable.refresh();
                }
            }
        });
    }

    @Override
    public void createJTable() {
        super.createJTable();
        partidaTable = new PartidaTable();
    }

    @Override
    public void createScrollPane() {
        super.createScrollPane();
        partidaScrollPane = new JScrollPane(partidaTable);
        partidaTable.onConnect();
    }

    private void setComponentBounds() {  // Las dimensiones de los components.
        float width = this.getWidth();
        float height = this.getHeight();

        int fontSize = height / 30 > 16 ? 16 : (int) (height / 30);
        int buttonWidth = width / 4.5 > 200 ? 200 : (int) (width / 4.5);
        int buttonHeight = height / 14.15 > 35 ? 35 : (int) (height / 14.15);
        int horMargin = (int) (width / 38.15);
        int verMargin = (int) (height / 21.2);

        registerButton.setBounds(horMargin, verMargin, buttonWidth, buttonHeight);
        registerButton.setFont(new Font("Arial", Font.BOLD, fontSize));

        editButton.setBounds(horMargin + buttonWidth + 30, verMargin, buttonWidth, buttonHeight);
        editButton.setFont(new Font("Arial", Font.BOLD, fontSize));

        deleteButton.setBounds((int) (width - (horMargin + buttonWidth)), verMargin, buttonWidth, buttonHeight);
        deleteButton.setFont(new Font("Arial", Font.BOLD, fontSize));

        partidaScrollPane.setBounds(horMargin, (int) (height / 7), (int) (width - (horMargin * 2)), (int) (height - ((height / 21.2 * 3) + (height / 14.15))));
        partidaTable.setFont(new Font("Arial", Font.PLAIN, fontSize));
        partidaTable.packAll();
    }
}
