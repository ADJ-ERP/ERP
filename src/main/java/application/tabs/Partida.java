package application.tabs;

import application.windows.RegPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Partida extends Tab {

    private JButton registerButton;

    public Partida() {
        addStuffs(registerButton);
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
            RegPart regPart = new RegPart();
            regPart.setVisible(true);
        });
    }

    private void setComponentBounds() {  // Las dimensiones de los components.
        float width = this.getWidth();
        float height = this.getHeight();

        registerButton.setBounds((int) (width/38.15), (int) (height / 21.2), (int) (width / 4.5), (int) (height / 14.15));
        registerButton.setFont(new Font("Arial", Font.BOLD, (int) (width / 60)));

    }
}
