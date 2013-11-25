package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class DetailedSale extends TableModelWithRow<SoldItem> {
	
	private Sale sale;

	public DetailedSale(String[] headers) {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum"});
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		return null;
	}
	
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem soldItem: rows) {
			buffer.append(soldItem.getId() + "\t");
			buffer.append(soldItem.getName() + "\t");
			buffer.append(soldItem.getPrice() + "\t");
			buffer.append(soldItem.getQuantity() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

}
