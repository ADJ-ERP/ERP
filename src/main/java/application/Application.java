package application;

import application.tabs.Partida;
import database.CreateDatabase;
import main.ERP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Application extends JFrame {
    static {
        WIDTH = 480;
        HEIGHT = 300;
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
        this.setTitle(String.format("%s - %s", ERP.NAME, this.user));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        JTabbedPane tabs = new JTabbedPane();

        JPanel p1 = new JPanel();
        p1.add(new JButton("b1"));
        // La idea es que cada frame es una clase de estas de extends JFrame y simplemente las llamamos desde las tabs, más fácil de usar.
        tabs.addTab("Usuarios", p1);

        Partida p = new Partida();
        tabs.add("Partidas", p);

        JPanel p3 = new JPanel();
        p3.add(new JButton("b3"));
        tabs.addTab("Clientes", p3);

        JPanel p4 = new JPanel();
        p4.add(new JButton("b4"));
        tabs.addTab("Albaranes", p4);

        JPanel p5 = new JPanel();
        p5.add(new JButton("b5"));
        tabs.addTab("Facturas", p5);

        this.add(tabs);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                try {
                    if (CreateDatabase.c != null && !CreateDatabase.c.isClosed()) {
                        CreateDatabase.c.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dispose();
                Runtime.getRuntime().halt(0);
            }
        });
    }
}
