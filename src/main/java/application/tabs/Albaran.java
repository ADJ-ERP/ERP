package application.tabs;

import application.tables.AlbaranTable;
import application.windows.albaranes.InsertAlbaran;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Albaran extends Tab {
    private JButton registerButton;
    private JButton editButton;
    private JButton deleteButton;

    private AlbaranTable albaranTable;

    private JScrollPane albaranScrollPane;

    public Albaran() {
        addStuffs(registerButton, albaranScrollPane, editButton, deleteButton);
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
        registerButton = (JButton) createJThing(2, "Registrar Albaran");
        registerButton.addActionListener(actionEvent -> {
            InsertAlbaran insertAlbaran = new InsertAlbaran(albaranTable);
            insertAlbaran.setVisible(true);
        });

        editButton = (JButton) createJThing(2, "Editar");
        editButton.addActionListener(actionEvent -> {});

        deleteButton = (JButton) createJThing(2, "Eliminar");
        deleteButton.addActionListener(actionEvent -> {});
    }

    @Override
    public void createJTable() {
        super.createJTable();
        albaranTable = new AlbaranTable();
    }

    @Override
    public void createScrollPane() {
        super.createScrollPane();
        albaranScrollPane = new JScrollPane(albaranTable);
        albaranTable.onConnect();
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

        albaranScrollPane.setBounds(horMargin, (int) (height / 7), (int) (width - (horMargin * 2)), (int) (height - ((height / 21.2 * 3) + (height / 14.15))));
        albaranTable.setFont(new Font("Arial", Font.PLAIN, fontSize));
        albaranTable.packAll();
    }
}
