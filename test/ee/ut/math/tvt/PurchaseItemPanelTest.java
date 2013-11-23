package ee.ut.math.tvt;

import static org.junit.Assert.*;

import javax.swing.JComboBox;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.kamm.ComboItem;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

public class PurchaseItemPanelTest {

	private PurchaseItemPanel panel;
	private SalesSystemModel model;
	private SalesDomainController dc = new SalesDomainControllerImpl();;
	private JComboBox <ComboItem> menu = new JComboBox <ComboItem>();
	private ComboItem product;
	private StockItem item;
	
	@Before
	public void setUp(){
		model = new SalesSystemModel(dc);
		item = new StockItem(103l, "Lauaviin", "", 5.0, 99);
		model.getWarehouseTableModel().addItem(item);
		product =  new ComboItem(item.getName(), String.valueOf(item.getId()));
		menu.addItem(product);
		panel = new PurchaseItemPanel(model, menu);
	}
	@Test
	public void testAutomaticFieldFilling() {
		menu.setSelectedIndex(1);
		panel.fillDialogFields();
		assertEquals(panel.getBarCodeField(), String.valueOf(item.getId()));
		assertEquals(panel.getNameField(), String.valueOf(item.getName()));
		assertEquals(panel.getPriceField(), String.valueOf(item.getPrice()));
	}
	@Test
	public void testAddItemWithInvalidQuantity() {
		panel.setQuantityField("a");
		menu.setSelectedIndex(1);
		panel.fillDialogFields();
		panel.addItemEventHandler();
		assertEquals(model.getCurrentPurchaseTableModel().getValueAt(0, 3), 1);
	}
	@Test
	public void testAddItemWithNegativeQuantity() {
		panel.setQuantityField("-1");
		menu.setSelectedIndex(1);
		panel.fillDialogFields();
		panel.addItemEventHandler();
		assertEquals(model.getCurrentPurchaseTableModel().getRowCount(), 0);
	}
}
