package Model.Classes.Autres;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MonDefaultTableModel extends DefaultTableModel {

	public MonDefaultTableModel() {
		// TODO Auto-generated constructor stub
	}

	public MonDefaultTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		// TODO Auto-generated constructor stub
	}

	public MonDefaultTableModel(Vector<?> columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public MonDefaultTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public MonDefaultTableModel(Vector<? extends Vector> data, Vector<?> columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public MonDefaultTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isCellEditable(int row, int column){  
        return false;  
    }

}
