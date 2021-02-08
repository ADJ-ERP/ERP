package application.tabs;

import application.tables.PartidaTable;
import application.windows.RegPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Partida extends Tab {

    private JButton registerButton;

    private PartidaTable partidaTable;

    private JScrollPane partidaScrollPane;

    public Partida() {
        addStuffs(registerButton, partidaScrollPane);
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

        if (width / 4.5 > 200 && height / 14.15 > 35) {
            registerButton.setBounds((int) (width / 38.15), (int) (height / 21.2), 200, 35);
        } else if (height / 14.15 > 35) {
            registerButton.setBounds((int) (width / 38.15), (int) (height / 21.2), (int) (width / 4.5), 35);
        } else if (width / 4.5 > 200) {
            registerButton.setBounds((int) (width / 38.15), (int) (height / 21.2), 200, (int) (height / 14.15));
        } else {
            registerButton.setBounds((int) (width / 38.15), (int) (height / 21.2), (int) (width / 4.5), (int) (height / 14.15));
        }
        registerButton.setFont(new Font("Arial", Font.BOLD, height / 30 > 16 ? 16 : (int) (height / 30)));

        partidaScrollPane.setBounds((int) (width / 38.15), (int) (height / 7), (int) (width - ((int) (width / 38.15) * 2)), (int) (height - ((height / 21.2 * 3) + (height / 14.15))));
    }
}
