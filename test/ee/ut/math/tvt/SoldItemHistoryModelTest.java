package ee.ut.math.tvt;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kamm.SoldItemHistoryModel;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemHistoryModelTest {
	
	private StockItem stockItem;
	private StockItem stockItem2;
	SoldItemHistoryModel table;
	
	@Before
	public void setUp() {
		table = new SoldItemHistoryModel();
		stockItem = new StockItem(1l, "Lauaviin", "", 5.0, 4);
		stockItem2 = new StockItem(2l, "Pannkook pelmeenidega", "", 2.0, 10);
	}
	
	@Test
	public void testAddOneItem() {
		SoldItem soldItem = new SoldItem(stockItem, 1);
		table.addItem(soldItem);
		assertEquals(table.getItemById(stockItem.getId()).toString(), soldItem.toString());
	}
	
	@Test
	public void testBuyMultipleItems() {
		SoldItem soldItem = new SoldItem(stockItem, 1);
		SoldItem soldItem2 = new SoldItem(stockItem2, 1);
		table.addItem(soldItem);
		table.addItem(soldItem2);
		assertEquals(table.getItemById(stockItem.getId()).toString(), soldItem.toString());
		assertEquals(table.getItemById(stockItem2.getId()).toString(), soldItem2.toString());
		assertEquals(table.getRowCount(), 2);
	}
}