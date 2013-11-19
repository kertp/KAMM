package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest {
	
	private PurchaseInfoTableModel table;
	private StockItem stockItem;
	
	@Before
	public void setUp() {
		table = new PurchaseInfoTableModel();
		stockItem = new StockItem(1l, "Lauaviin", "hea", 5.0, 10);
	}
	
	@Test
	public void addOneItemToCartTest() {
		
	}
	@Test
	public void addMultibleItemsToCartTest() {
		
	}
	@Test
	public void addItemWithInvalidQuantity() {
		
	}
	@Test
	public void testHasEnoughInStock() {
		SoldItem soldItem = new SoldItem(stockItem, 11);
		table.addItem(soldItem, stockItem.getQuantity());
		assertEquals(table.getRowCount(), 0);
	}
}
