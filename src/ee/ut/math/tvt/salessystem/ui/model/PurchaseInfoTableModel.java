package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	public PurchaseInfoTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum"});
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
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
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
    /**
     * Add new StockItem to table.
     */
    public void addItem(final SoldItem soldItem, final int stockquantity) {
        /**
         * XXX In case such stockItem already exists increase the quantity of the
         * existing stock.
         */
		try {
			SoldItem item = getItemById(soldItem.getId());
			int newquantity = item.getQuantity() + soldItem.getQuantity();
			if (stockquantity < newquantity) {
				JOptionPane.showMessageDialog(null, "Not enough stock!");
				log.debug("Stock of " + soldItem.getName() + " is " + stockquantity +
						". Cannot increase quantity to " + newquantity );
			}
			else {
				item.setQuantity(item.getQuantity() + soldItem.getQuantity());
				log.debug("Found existing item " + soldItem.getName()
						+ " increased quantity by " + soldItem.getQuantity());
				fireTableDataChanged();
			}
		}
		catch (NoSuchElementException e) {
			if (stockquantity < soldItem.getQuantity()) {
				JOptionPane.showMessageDialog(null, "Not enough stock!");
				log.debug("Stock of " + soldItem.getName() + " is " + stockquantity +
						". Cannot add " + soldItem.getQuantity() + " items");
			}
			else {
				rows.add(soldItem);
				log.debug("Added " + soldItem.getName()
						+ " quantity of " + soldItem.getQuantity());
				fireTableDataChanged();
			}
		}
    }
}
