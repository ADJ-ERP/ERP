package application;

import main.Main;

import javax.swing.*;
import java.awt.*;
import main.RegistroPartida;

public class Application extends JFrame {
    static {
        WIDTH = 426;
        HEIGHT = 240;
    }

    private static final int WIDTH;
    private static final int HEIGHT;

    public String user;

    public Application(String user) {
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        this.setBounds(100, 100, 768, 480);
        this.setTitle(String.format("%s - %s", Main.NAME, this.user));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        JTabbedPane tabs = new JTabbedPane();

        JFrame f2 = new JFrame();
        f2.add(new JButton("b1"));
        // La idea es que cada frame es una clase de estas de extends JFrame y simplemente las llamamos desde las tabs, más fácil de usar.
        tabs.addTab("Usuarios", f2.getContentPane());

        RegistroPartida f3 = new RegistroPartida(); // pone la vista de Registro de Partidas
        tabs.addTab("Partidas", f3.getContentPane());

        JFrame f4 = new JFrame();
        f4.add(new JButton("b3"));
        tabs.addTab("Clientes", f4.getContentPane());

        JFrame f5 = new JFrame();
        f5.add(new JButton("b4"));
        tabs.addTab("Albaranes", f5.getContentPane());

        JFrame f6 = new JFrame();
        f6.add(new JButton("b5"));
        tabs.addTab("Facturas", f6.getContentPane());

        this.add(tabs);
    }
}
