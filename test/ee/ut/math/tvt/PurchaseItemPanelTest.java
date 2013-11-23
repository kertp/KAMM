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
	private SalesDomainController dc;
	private JComboBox <ComboItem> menu = new JComboBox <ComboItem>();
	private ComboItem product;
	private StockItem item;
	
	@Before
	public void setUp() {
		dc = new SalesDomainControllerImpl();
		model = new SalesSystemModel(dc);
		item = new StockItem(1l, "Lauaviin", "", 5.0, 99);
		product =  new ComboItem(item.getName(), String.valueOf(item.getId()));
		menu.addItem(product);
		panel = new PurchaseItemPanel(model, menu);
	}
	@Test
	public void testAutomaticFieldFilling() {
		menu.setSelectedIndex(1);
		panel.fillDialogFields();
		assertEquals(panel.getBarCodeField(), item.getId());
		assertEquals(panel.getNameField(), item.getName());
		assertEquals(panel.getPriceField(), item.getPrice());
	}
	@Test
	public void testAddItemWithInvalidQuantity() {
		panel.setQuantityField("-1");
		panel.addItemEventHandler();
		assertEquals(panel.getModel().getCurrentPurchaseTableModel().getValueAt(0, 4), 1);
	}
}
