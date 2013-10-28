package ee.ut.math.tvt.kamm;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemTableModel;

public class SoldItemHistoryModel extends SalesSystemTableModel<SoldItem>{
	private static final long serialVersionUID = 1L;
	
	public SoldItemHistoryModel() {
		super(new String[] {"Name", "Price", "Quantity", "Sum"});
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getName();
		case 1:
			return item.getPrice();
		case 2:
			return item.getQuantity();
		case 3:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	public void addItem(final SoldItem soldItem) {
		rows.add(soldItem);
	}
}
