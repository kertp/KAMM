package ee.ut.math.tvt.kamm;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemTableModel;

public class SubmittedPurchaseTableModel extends SalesSystemTableModel<SubmittedPurchase> {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(SubmittedPurchaseTableModel.class);
	
	public SubmittedPurchaseTableModel() {
		super(new String[] {"Date", "Time", "Total"});
	}

	@Override
	protected Object getColumnValue(SubmittedPurchase item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getDate();
		case 1:
			return item.getTime();
		case 2:
			return item.getTotal();
		case 3:
			return item.getSoldItems();
		}
		throw new IllegalArgumentException("Column index out of range");
	}
		
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SubmittedPurchase item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getDate() + "\t");
			buffer.append(item.getTime() + "\t");
			buffer.append(item.getTotal() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
	public void addItem(final SubmittedPurchase item) {
        rows.add(item);
        log.debug("Added " + item.getDate() + " " + item.getTime() + ". Total: " + item.getTotal());
        fireTableDataChanged();
	}

}
