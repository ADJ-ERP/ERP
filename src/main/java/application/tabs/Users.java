package application.tabs;

import application.tables.UsersTable;
import database.Query;
import userManagement.RegistroUsuarios;
import utils.PaneUtils;
import utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

public class Users extends Tab{
    private JButton registerButton;
    private JButton deleteButton;

    private UsersTable usersTable;

    private JScrollPane partidaScrollPane;

    public Users() {
        addStuffs(registerButton, partidaScrollPane, deleteButton);
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
        registerButton = (JButton) createJThing(2, "Registrar usuario");
        registerButton.addActionListener(actionEvent -> {
            RegistroUsuarios regUsr = new RegistroUsuarios(usersTable);
            regUsr.frame.setVisible(true);
        });


        deleteButton = (JButton) createJThing(2, "Eliminar usuario");
        deleteButton.addActionListener(actionEvent -> {
            String sNUsuario;
            String RolUsuario;
            try {
                sNUsuario = (String) usersTable.getValueAt(usersTable.getSelectedRow(),usersTable.getColumn("usuario").getModelIndex());
                RolUsuario=(String) usersTable.getValueAt(usersTable.getSelectedRow(),usersTable.getColumn("rol").getModelIndex());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionadof.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (sNUsuario == null) {
                JOptionPane.showMessageDialog(null, "No hay nada seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (PaneUtils.confirmation(String.format("Vas a eliminar al usuario %s\nSeguro que quieres continuar?", sNUsuario))) {
                if(RolUsuario.equals("admin")) {
                    if (PaneUtils.confirmation("Vas a eliminar a un  administrador\n¿Seguro que quieres continuar?")) {
                        if (Query.deleteUser(sNUsuario)) {
                            usersTable.refresh();
                        }
                    }
                } else {
                    if (Query.deleteUser(sNUsuario)) {
                        usersTable.refresh();
                    }
                }

            }
        });
    }

    @Override
    public void createJTable() {
        super.createJTable();
        usersTable = new UsersTable();
    }

    @Override
    public void createScrollPane() {
        super.createScrollPane();
        partidaScrollPane = new JScrollPane(usersTable);
        usersTable.onConnect();
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


        deleteButton.setBounds((int) (width - (horMargin + buttonWidth)), verMargin, buttonWidth, buttonHeight);
        deleteButton.setFont(new Font("Arial", Font.BOLD, fontSize));

        partidaScrollPane.setBounds(horMargin, (int) (height / 7), (int) (width - (horMargin * 2)), (int) (height - ((height / 21.2 * 3) + (height / 14.15))));
        usersTable.setFont(new Font("Arial", Font.PLAIN, fontSize));
        usersTable.packAll();
    }
}
