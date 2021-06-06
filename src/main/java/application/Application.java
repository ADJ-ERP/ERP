package application;

import application.tabs.Cliente;
import application.tabs.Partida;
import application.tabs.Users;
import database.CreateDatabase;
import database.Query;
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
    Query query=new Query();
    public Application(String user) {
        this.user = user;
        try {
            query.isAdmin(user);
        } catch (Exception e){

        }

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

        try {
            Boolean isAdmin=query.isAdmin(user);
            if(isAdmin){
                Users u = new Users();
                tabs.addTab("Usuarios", u);
            }

            Partida p = new Partida(isAdmin);
            tabs.add("Partidas", p);

            Cliente c = new Cliente(isAdmin);
            tabs.add("Clientes", c);
        } catch (Exception e){

        }
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
