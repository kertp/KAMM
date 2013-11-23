package ee.ut.math.tvt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
	
	public StockItem stockItem;
	@Before
	public void setUp() {
		stockItem = new StockItem(105l, "Lauaviin", "hea", 5.0, 10);
	}
	
	@Test
	public void testClone() {
		StockItem clone = (StockItem)stockItem.clone();
		assertEquals(stockItem.toString(), clone.toString()); // Natuke imelik
	}
	
	@Test
	public void testGetColumn() {
		assertEquals(stockItem.getColumn(0), 105l);
		assertEquals(stockItem.getColumn(1), "Lauaviin");
		assertEquals(stockItem.getColumn(2), 5.0);
		assertEquals(stockItem.getColumn(3), 10);
	}
	
	@Test(expected= RuntimeException.class)
	public void testGetInvalidColumn() {
		stockItem.getColumn(4);
	}
}
