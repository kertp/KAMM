package ee.ut.math.tvt.kamm;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

/*import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemTableModel;

public class SoldItemHistoryUI extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;
	//private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	public SoldItemHistoryUI() {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum"});
	}

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
}*/
public class SoldItemHistoryUI {
	private JDialog raam = new JDialog();
	
	public void draw() {
		raam.setModal(true);
		raam.setResizable(false);
		raam.setSize(200, 200);
		raam.add(new JTextField("tere hommikust"));
		//Container parent = raam.getParent();
		//raam.setLocation(parent.getLocationOnScreen().x+(parent.getWidth()-200)/2,
		//		parent.getLocationOnScreen().y+(parent.getHeight()-200)/2);
		raam.setVisible(true);
	}
}



