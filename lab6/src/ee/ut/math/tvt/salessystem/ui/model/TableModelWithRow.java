package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;

public abstract class TableModelWithRow<T extends DisplayableItem> extends SalesSystemTableModel<T> {
	private static final long serialVersionUID = 1L;

	protected List<T> rows;
	
	public TableModelWithRow(String[] headers) {
		super(headers);
		rows = new ArrayList<T>();
		// TODO Auto-generated constructor stub
	}

	@Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        return getColumnValue(rows.get(rowIndex), columnIndex);
    }

	@Override
	public int getRowCount(){
        return rows.size();
    }
	
	@Override
	public T getItemById(final long id) {
        for (final T item : rows) {
            if (item.getId() == id)
                return item;
        }
        throw new NoSuchElementException();
    }
	
	@Override
    public List<T> getTableRows() {
        return rows;
    }
	
	@Override
    public void clear() {
        rows = new ArrayList<T>();
        fireTableDataChanged();
    }
	
	@Override
    public void populateWithData(final List<T> data) {
        rows.clear();
        rows.addAll(data);
        fireTableDataChanged();
    }
	
	@Override
    public void addRow(T row) {
        rows.add(row);
        fireTableDataChanged();
    }
    
	@Override
    public T getRow(int index) {
        return rows.get(index);
    }	
}
