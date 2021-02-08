package application.tabs;

import application.tables.PartidaTable;
import application.windows.RegPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Partida extends Tab {

    private JButton registerButton;
    private JButton editButton;

    private PartidaTable partidaTable;

    private JScrollPane partidaScrollPane;

    public Partida() {
        addStuffs(registerButton, partidaScrollPane, editButton);
        setComponentBounds();
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
            RegPart regPart = new RegPart(partidaTable);
            regPart.setVisible(true);
        });

        editButton = (JButton) createJThing(2, "Editar");
        editButton.addActionListener(actionEvent -> System.out.println("a"));
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
        partidaTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    private void setComponentBounds() {  // Las dimensiones de los components.
        float width = this.getWidth();
        float height = this.getHeight();

        registerButton.setBounds((int) (width / 38.15), (int) (height / 21.2), width / 4.5 > 200 ? 200 : (int) (width / 4.5), height / 14.15 > 35 ? 35 : (int) (height / 14.15));
        registerButton.setFont(new Font("Arial", Font.BOLD, height / 30 > 16 ? 16 : (int) (height / 30)));

        editButton.setBounds((int) (width / 38.15) * 10, (int) (height / 21.2), width / 4.5 > 200 ? 200 : (int) (width / 4.5), height / 14.15 > 35 ? 35 : (int) (height / 14.15));
        editButton.setFont(new Font("Arial", Font.BOLD, height / 30 > 16 ? 16 : (int) (height / 30)));

        partidaScrollPane.setBounds((int) (width / 38.15), (int) (height / 7), (int) (width - ((int) (width / 38.15) * 2)), (int) (height - ((height / 21.2 * 3) + (height / 14.15))));
        partidaTable.packAll();
    }
}
