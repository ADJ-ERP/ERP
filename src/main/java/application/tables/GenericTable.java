package application.tables;

import helpers.SelectionHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GenericTable extends JTable {
    public final DefaultTableModel model;

    public GenericTable() {
        this.setSelectionModel(new SelectionHelper());  // Evitar que el usuario pueda no seleccionar nada o demasiado.
        model = (DefaultTableModel) this.getModel();
        model.addColumn("Conexión no establecida.");
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    public void onConnect() {
        onClosed();
        this.setSelectionModel(new SelectionHelper());  // Evitar que el usuario pueda no seleccionar nada o demasiado.
    }

    private void onClosed() {
        model.setColumnCount(0);
        model.setRowCount(0);
    }

    public void refresh() {
        onConnect();
    }
}
