package application.tables;

import database.Query;
import utils.CustomTableFormat;

import java.sql.SQLException;

public class AlbaranTable extends GenericTable {
    @Override
    public void onConnect() {
        super.onConnect();
        try {
            CustomTableFormat tbl = Query.getAlbaranes();
            if (tbl == null) {
                super.model.addColumn("No hay albaranes");
                return;
            }

            for (String column : tbl.columnNames) {
                super.model.addColumn(column);
            }

            for (String[] row : tbl.rows) {
                super.model.addRow(row);
            }

            if (super.model.getRowCount() > 0) {
                this.setRowSelectionInterval(0, 0);
            } else {
                onClosed();
                super.model.addColumn("No hay albaranes");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
