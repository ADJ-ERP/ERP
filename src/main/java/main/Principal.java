/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import helpers.PrincipalActions;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author angel
 */
public class Principal extends JFrame {

    static {
        WIDTH = 426;
        HEIGHT = 240;
    }

    private static final int WIDTH;
    private static final int HEIGHT;

    public String user;

    /**
     * Creates new form Principal
     */
    public Principal(String user) {
        this.user = user;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        // jMItemRegistrarUser = new javax.swing.JMenuItem();
        jMItemEditarUser = new javax.swing.JMenuItem();
        jMItemCloseSession = new JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMItemRegistrarPart = new javax.swing.JMenuItem();
        jMItemVerPart = new javax.swing.JMenuItem();
        jMItemEditarPart = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Usuarios");

/*
        jMItemRegistrarUser.setText("Registrar");
        jMenu1.add(jMItemRegistrarUser);
*/

        jMItemEditarUser.setText(String.format("Editar %s", user));
        jMenu1.add(jMItemEditarUser);

        jMenu1.addSeparator();

        jMItemCloseSession.setText("Cerrar Sesión");
        jMItemCloseSession.addActionListener(actionEvent -> PrincipalActions.onCloseSession(this));
        jMenu1.add(jMItemCloseSession);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Partidas");

        jMItemRegistrarPart.setText("Registrar");
        jMenu2.add(jMItemRegistrarPart);

        jMItemVerPart.setText("Ver");
        jMenu2.add(jMItemVerPart);

        jMItemEditarPart.setText("Editar");
        jMenu2.add(jMItemEditarPart);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Clientes");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Albaranes");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Facturas");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Principal("test").setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMItemEditarPart;
    private javax.swing.JMenuItem jMItemEditarUser;
    private JMenuItem jMItemCloseSession;
    private javax.swing.JMenuItem jMItemRegistrarPart;
    // private javax.swing.JMenuItem jMItemRegistrarUser;
    private javax.swing.JMenuItem jMItemVerPart;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
