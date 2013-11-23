package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {

	private StockTableModel table;
	private StockItem stockItem;
	
	@Before
	public void setUp() {
		table= new StockTableModel();
		stockItem = new StockItem(106l, "Lauaviin", "hea", 5.0, 10);
		BasicConfigurator.configure();
		
	}
	
	// testHasEnoughInStock on klassis PurchaseInfoTableModel
	
	@Test
	public void testCanHaveSameNames() {  		// testValidateNameUniqueness asemel, sest meil on lubatud
												// mitu toodet sama nimega.
		StockItem stockItem2 = new StockItem(107l, "Lauaviin", "jee", 6.0, 15);
		table.addItem(stockItem);
		table.addItem(stockItem2);
		assertEquals(table.getValueAt(0, 1), table.getValueAt(1, 1));
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		table.addItem(stockItem);
		assertEquals(table.getItemById(106l).toString(), stockItem.toString());
	}
	@Test(expected= NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		table.getItemById(106l);
	}
}
