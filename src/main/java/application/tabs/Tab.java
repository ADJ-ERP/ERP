package application.tabs;

import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel {
    public Tab() {
        setLayout(null);
        createJButton();
        createJField();
        createJTable();
        createScrollPane();
    }

    public void createJField() {
    }

    public void createJButton() {
    }

    public void createJTable() {
    }

    public void createScrollPane() {
    }

    public Object createJThing(int type, String text) {
        Object thing;
        switch (type) {
            case 0:
                thing = new JTextField();
                ((JTextField) thing).setFont(new Font("Arial", Font.PLAIN, 14));
                ((JTextField) thing).setMargin(new Insets(1, 1, 1, 1));
                break;
            case 1:
                thing = new JLabel();
                ((JLabel) thing).setFont(new Font("Arial", Font.PLAIN, 14));
                ((JLabel) thing).setText(text);
                break;
            case 2:
                thing = new JButton();
                ((JButton) thing).setFont(new Font("Arial", Font.PLAIN, 14));
                ((JButton) thing).setText(text);
                break;
            default:
                throw new IllegalStateException(String.format("Unexpected value %d", type));
        }
        return thing;
    }

    public void addStuffs(Component... components) {  // Añadir components al frame.
        for (Component component : components) {
            this.add(component);
        }
    }
}
