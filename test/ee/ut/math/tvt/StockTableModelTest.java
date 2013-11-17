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

	private PurchaseInfoTableModel table;
	private StockTableModel table2;
	private StockItem stockItem;
	
	@Before
	public void setUp() {
		table = new PurchaseInfoTableModel();
		table2= new StockTableModel();
		stockItem = new StockItem(1l, "Lauaviin", "hea", 5.0, 10);
		BasicConfigurator.configure();
		
	}
	
	@Test
	public void testValidateNameUniqueness() {
		
	}
	
	@Test
	public void testHasEnoughInStock() {
//		purchaseTab.submitPurchaseButtonClicked();
		SoldItem soldItem = new SoldItem(stockItem, 11);
		table.addItem(soldItem, stockItem.getQuantity());
		assertEquals(table.getRowCount(), 0);
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		table2.addItem(stockItem);
		assertEquals(table2.getItemById(1l).toString(), stockItem.toString());
	}
	@Test(expected= NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		table2.getItemById(1l);
	}
}
