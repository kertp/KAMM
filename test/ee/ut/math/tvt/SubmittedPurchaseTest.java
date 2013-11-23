package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.SubmittedPurchase;

public class SubmittedPurchaseTest {
	
	private StockItem stockItem;
	private StockItem stockItem2;
	
	@Before
	public void setUp() {
		stockItem = new StockItem(111l, "Lauaviin", "hea", 5.0, 10);
		stockItem2 = new StockItem (112l, "Kartul", "paha", 1.0, 100);
	}
	
	@Test
	public void testAddSoldItem() {
		SubmittedPurchase sb = new SubmittedPurchase(new Date(), new ArrayList<SoldItem>());
		SoldItem soldItem = new SoldItem(stockItem, 1);
		sb.getSoldItems().add(soldItem);
		assertFalse(new Integer(-1).equals(sb.getSoldItems().indexOf(soldItem)));
	}
	
	//@Test
	public void testGetSumWithNoItems() {
		SubmittedPurchase sb = new SubmittedPurchase(new Date(), new ArrayList<SoldItem>());
		assertEquals(sb.getTotal(), 0.0, 0.001);
	}
	
	@Test
	public void testGetSumWithOneItem() {
		List<SoldItem> list = new ArrayList<SoldItem>();
		SubmittedPurchase sb = new SubmittedPurchase(new Date(), new ArrayList<SoldItem>());
		SoldItem soldItem = new SoldItem(stockItem, 2);
		list.add(soldItem);
		sb.setSoldItems(list);
		assertEquals(sb.getTotal(), 10.0, 0.0001);
	}
	@Test
	public void testGetSumWithMultipleItems() {
		List<SoldItem> list = new ArrayList<SoldItem>();
		SubmittedPurchase sb = new SubmittedPurchase(new Date(), new ArrayList<SoldItem>());
		SoldItem soldItem = new SoldItem(stockItem, 2);
		SoldItem soldItem2 = new SoldItem(stockItem2, 2);
		list.add(soldItem);
		list.add(soldItem2);
		sb.setSoldItems(list);
		assertEquals(sb.getTotal(), 12.0, 0.001);
	}
}
