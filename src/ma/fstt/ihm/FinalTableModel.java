package ma.fstt.ihm;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;



public class FinalTableModel extends AbstractTableModel{
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ma.fstt.model.Client> li = new ArrayList<>();
	    private String[] columnNames ;
	    
	    
	    

	public FinalTableModel(List<ma.fstt.model.Client> li, String[] columnNames) {
			super();
			this.li = li;
			this.setColumnNames(columnNames);
		}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.li.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return Client.class.getDeclaredFields().length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 ma.fstt.model.Client si = this.li.get(rowIndex);
		 
		 switch (columnIndex) {
         case 0: 
             return si.getId_client();
         case 1:
             return si.getNom();
         case 2:
             return si.getEmail();
         case 3:
             return si.getGenre();
        }
		 
		return null;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

}
