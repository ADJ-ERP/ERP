package application.tables;

import database.Query;
import utils.CustomTableFormat;

import java.sql.SQLException;

public class PartidaTable extends GenericTable {
    @Override
    public void onConnect() {
        super.onConnect();
        try {
            CustomTableFormat tbl = Query.getPartidas();
            if (tbl == null) {
                super.model.addColumn("No hay partidas");
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
                super.model.addColumn("No hay partidas");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
