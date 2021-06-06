package application;

import application.tabs.Albaran;
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
        this.setBounds(100, 100, 788, 500);
        this.setTitle(String.format("%s - %s", ERP.NAME, this.user));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        JTabbedPane tabs = new JTabbedPane();

        JPanel p1 = new JPanel();
        p1.add(new JButton("b1"));
        // La idea es que cada frame es una clase de estas de extends JFrame y simplemente las llamamos desde las tabs, más fácil de usar.

        try {
            if(query.isAdmin(user)){
                Users u = new Users();
                tabs.addTab("Usuarios", u);
            }
        } catch (Exception e){

        }
        Partida p = new Partida();
        tabs.add("Partidas", p);

        Cliente c = new Cliente();
        tabs.add("Clientes", c);

        Albaran a = new Albaran();
        tabs.add("Albaranes", a);

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
