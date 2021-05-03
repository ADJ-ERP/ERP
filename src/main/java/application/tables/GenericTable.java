package application.tables;

import helpers.SelectionHelper;
import org.jdesktop.swingx.JXTable;

import javax.swing.table.DefaultTableModel;

public class GenericTable extends JXTable {
    public final DefaultTableModel model;

    public GenericTable() {
        this.setSelectionModel(new SelectionHelper());  // Evitar que el usuario pueda no seleccionar nada o demasiado.
        model = (DefaultTableModel) this.getModel();
        model.addColumn("Conexión no establecida.");
        this.setAutoResizeMode(JXTable.AUTO_RESIZE_OFF);
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    public void onConnect() {
        onClosed();
        this.setSelectionModel(new SelectionHelper());  // Evitar que el usuario pueda no seleccionar nada o demasiado.
    }

    public void onClosed() {
        model.setColumnCount(0);
        model.setRowCount(0);
    }

    public void refresh() {
        onConnect();
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return getPreferredSize().width < getParent().getWidth();
    }
}
