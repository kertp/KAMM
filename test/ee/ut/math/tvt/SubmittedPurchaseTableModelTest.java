package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kamm.SubmittedPurchaseTableModel;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.SubmittedPurchase;

public class SubmittedPurchaseTableModelTest {
	
	SubmittedPurchaseTableModel table;
	@Before
	public void setUp() {
		table = new SubmittedPurchaseTableModel();
		
	}
	
	@Test
	public void testAddOnePurchase() {
		Date date = new Date();
		StockItem stockItem = new StockItem(1l, "Lauaviin", "", 5.0, 12);
		SoldItem soldItem = new SoldItem(stockItem, 1);
		List <SoldItem>list = new ArrayList<SoldItem>();
		list.add(soldItem);
		SubmittedPurchase item = new SubmittedPurchase(date, list);
		table.addItem(item);
		assertEquals(table.getRowCount(), 1);
	}
	
	@Test
	public void testCorrectDate() {
		Date date = new Date();
		StockItem stockItem = new StockItem(1l, "Lauaviin", "", 5.0, 12);
		SoldItem soldItem = new SoldItem(stockItem, 1);
		List <SoldItem>list = new ArrayList<SoldItem>();
		list.add(soldItem);
		SubmittedPurchase item = new SubmittedPurchase(date, list);
		table.addItem(item);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String datestr = dateFormat.format(date);
		assertEquals(table.getValueAt(0, 0), datestr);
	}
	@Test
	public void testCorrectTime() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		StockItem stockItem = new StockItem(1l, "Lauaviin", "", 5.0, 12);
		SoldItem soldItem = new SoldItem(stockItem, 1);
		List <SoldItem>list = new ArrayList<SoldItem>();
		list.add(soldItem);
		SubmittedPurchase item = new SubmittedPurchase(date, list);
		table.addItem(item);
		String timestr = timeFormat.format(date);
		assertEquals(table.getValueAt(0, 1), timestr);
	}
}
