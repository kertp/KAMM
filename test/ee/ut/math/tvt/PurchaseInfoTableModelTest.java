package ee.ut.math.tvt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest {
	
	private StockItem stockItem;
	private StockItem stockItem2;
	PurchaseInfoTableModel table;
	
	@Before
	public void setUp() {
		table = new PurchaseInfoTableModel();
		stockItem = new StockItem(1l, "Lauaviin", "hea", 5.0, 10);
		stockItem2 = new StockItem(2l, "Pannkook", "", 2.5, 100);
	}
	@Test
	public void testAddDifferentItemsToCart() {
		SoldItem soldItem = new SoldItem(stockItem, 2);
		SoldItem soldItem2 = new SoldItem(stockItem2, 1);
		table.addItem(soldItem, stockItem.getQuantity());
		table.addItem(soldItem2, stockItem2.getQuantity());
		assertEquals(table.getRowCount(), 2);
	}
	
	@Test
	public void testAddSameItemsToCart() {
		SoldItem soldItem = new SoldItem(stockItem, 1);
		SoldItem soldItem2 = new SoldItem(stockItem, 1);
		table.addItem(soldItem, stockItem.getQuantity());
		table.addItem(soldItem2, stockItem.getQuantity());
		assertEquals(table.getRowCount(), 1);
	}
	

//	@Test
//	public void testAddItemWithInvalidQuantity() {
//		SoldItem soldItem = new SoldItem(stockItem, -1);
//		table.addItem(soldItem, stockItem.getQuantity());
//		assertEquals(table.getValueAt(1, 4), 1);
//	}
	@Test
	public void testHasEnoughInStock() {
		PurchaseInfoTableModel table = new PurchaseInfoTableModel();
		SoldItem soldItem = new SoldItem(stockItem, 11);
		assertTrue(!table.addItem(soldItem, stockItem.getQuantity()));
	}
}
