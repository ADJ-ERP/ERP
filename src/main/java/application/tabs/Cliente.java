package application.tabs;

import application.tables.ClienteTable;
import application.windows.clientes.EditClient;
import application.windows.clientes.InsertClient;
import database.Query;
import database.tables.ClientDB;
import utils.PaneUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

public class Cliente extends Tab {
    private JButton registerButton;
    private JButton editButton;
    private JButton deleteButton;

    private ClienteTable clienteTable;

    private JScrollPane clienteScrollPane;

    public Cliente(boolean admin) {
        addStuffs(registerButton, clienteScrollPane, editButton, deleteButton);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                setComponentBounds();
                revalidate();
                repaint();
            }
        });

        editButton.setEnabled(admin);
        deleteButton.setEnabled(admin);
    }

    @Override
    public void createJButton() {
        super.createJButton();
        registerButton = (JButton) createJThing(2, "Registrar Cliente");
        registerButton.addActionListener(actionEvent -> {
            InsertClient insertClient = new InsertClient(clienteTable);
            insertClient.setVisible(true);
        });

        editButton = (JButton) createJThing(2, "Editar");
        editButton.addActionListener(actionEvent -> {
            String cif;
            try {
                cif = (String) clienteTable.getValueAt(
                        clienteTable.getSelectedRow(),
                        clienteTable.getColumn("CIF").getModelIndex()
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cif == null) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                ClientDB clientDB = Query.getCliente(cif);
                EditClient editClient = new EditClient(clienteTable, clientDB);
                editClient.setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        deleteButton = (JButton) createJThing(2, "Eliminar");
        deleteButton.addActionListener(actionEvent -> {
            String cif;
            try {
                cif = (String) clienteTable.getValueAt(
                        clienteTable.getSelectedRow(),
                        clienteTable.getColumn("CIF").getModelIndex()
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cif == null) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (PaneUtils.confirmation(String.format("Vas a eliminar cliente con CIF %s\nSeguro que quieres continuar?", cif))) {
                if (Query.deleteClient(cif)) {
                    clienteTable.refresh();
                }
            }
        });
    }

    @Override
    public void createJTable() {
        super.createJTable();
        clienteTable = new ClienteTable();
    }

    @Override
    public void createScrollPane() {
        super.createScrollPane();
        clienteScrollPane = new JScrollPane(clienteTable);
        clienteTable.onConnect();
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

        clienteScrollPane.setBounds(horMargin, (int) (height / 7), (int) (width - (horMargin * 2)), (int) (height - ((height / 21.2 * 3) + (height / 14.15))));
        clienteTable.setFont(new Font("Arial", Font.PLAIN, fontSize));
        clienteTable.packAll();
    }
}
