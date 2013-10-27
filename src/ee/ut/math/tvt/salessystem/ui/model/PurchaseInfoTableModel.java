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
    public void addItem(final SoldItem item, final int currentquantity) {
        /**
         * XXX In case such stockItem already exists increase the quantity of the
         * existing stock.
         */
		/*try {
			SoldItem item = getItemById(soldItem.getId());
			item.setQuantity(item.getQuantity() + soldItem.getQuantity());
			log.debug("Found existing item " + soldItem.getName()
					+ " increased quantity by " + soldItem.getQuantity());
		}
		catch (NoSuchElementException e) {
			rows.add(soldItem);
			log.debug("Added " + soldItem.getName()
					+ " quantity of " + soldItem.getQuantity());
		}
		fireTableDataChanged();*/
    	for(int i = 0; i < getRowCount(); i++) {
    		if (getValueAt(i, 0) == (item.getId())) {
    			int a = rows.get(i).getQuantity();
    			int newquantity = item.getQuantity() + a;
    			if (currentquantity < newquantity) {
    				JOptionPane.showMessageDialog(null, "Not enough stock!");
    				return;
    			}
    			item.setQuantity(item.getQuantity() + a);
    			rows.remove(i);
    			break;
    		}
    	}
        rows.add(item);
        log.debug("Added " + item.getName() + " quantity of " + item.getQuantity());
        fireTableDataChanged();
    }
}
