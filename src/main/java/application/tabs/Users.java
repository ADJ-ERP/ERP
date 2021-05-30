package application.tabs;

import application.tables.UsersTable;
import application.windows.InsertPart;
import userManagement.RegistroUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Users extends Tab{
    private JButton registerButton;
    private JButton editButton;
    private JButton deleteButton;

    private UsersTable usersTable;

    private JScrollPane partidaScrollPane;

    public Users() {
        addStuffs(registerButton, partidaScrollPane, editButton, deleteButton);
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

        editButton = (JButton) createJThing(2, "Editar usuario");
        editButton.addActionListener(actionEvent -> System.out.println("a"));

        deleteButton = (JButton) createJThing(2, "Eliminar usuario");
        deleteButton.addActionListener(actionEvent -> System.out.println("b"));
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

        editButton.setBounds(horMargin + buttonWidth + 30, verMargin, buttonWidth, buttonHeight);
        editButton.setFont(new Font("Arial", Font.BOLD, fontSize));

        deleteButton.setBounds((int) (width - (horMargin + buttonWidth)), verMargin, buttonWidth, buttonHeight);
        deleteButton.setFont(new Font("Arial", Font.BOLD, fontSize));

        partidaScrollPane.setBounds(horMargin, (int) (height / 7), (int) (width - (horMargin * 2)), (int) (height - ((height / 21.2 * 3) + (height / 14.15))));
        usersTable.setFont(new Font("Arial", Font.PLAIN, fontSize));
        usersTable.packAll();
    }
}
