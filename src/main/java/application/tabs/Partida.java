package application.tabs;

import application.tables.PartidaTable;
import application.windows.InsertPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Partida extends Tab {

    private JButton registerButton;
    private JButton editButton;
    private JButton deleteButton;

    private PartidaTable partidaTable;

    private JScrollPane partidaScrollPane;

    public Partida() {
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
        registerButton = (JButton) createJThing(2, "Registrar Partida");
        registerButton.addActionListener(actionEvent -> {
            InsertPart insertPart = new InsertPart(partidaTable);
            insertPart.setVisible(true);
        });

        editButton = (JButton) createJThing(2, "Editar");
        editButton.addActionListener(actionEvent -> System.out.println("a"));

        deleteButton = (JButton) createJThing(2, "Eliminar");
        deleteButton.addActionListener(actionEvent -> System.out.println("b"));
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
