package ee.ut.math.tvt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	
	private StockItem stockItem;
	
	@Before
	public void setUp() {
		stockItem = new StockItem(104l, "Lauaviin", "hea", 5.0);
	}
	
	@Test
	public void testGetSum() {
		SoldItem soldItem = new SoldItem(stockItem, 5);
		assertEquals(soldItem.getSum(), 25.0, 0.001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		SoldItem soldItem = new SoldItem(stockItem, 0);
		assertEquals(soldItem.getSum(), 0.0, 0.001);
	}
}
