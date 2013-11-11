package ee.ut.math.tvt.salessystem.ui;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.kamm.AddStockItemUI;
import ee.ut.math.tvt.kamm.SoldItemHistoryUI;
import ee.ut.math.tvt.kamm.SubmitPurchaseUI;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.HistoryTab;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;
import ee.ut.math.tvt.salessystem.ui.tabs.StockTab;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

/**
 * Graphical user interface of the sales system.
 */
public class SalesSystemUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SalesSystemUI.class);

	private final SalesDomainController domainController;

	// Warehouse model
	private SalesSystemModel model;

	// Instances of tab classes
	private PurchaseTab purchaseTab;
	private HistoryTab historyTab;
	private StockTab stockTab;
	private SubmitPurchaseUI submitWindow;
	private SoldItemHistoryUI soldItemsWindow;
	private AddStockItemUI addItemWindow;

	private JComboBox<String> productMenu;

	/**
	 * Constructs sales system GUI.
	 * 
	 * @param domainController
	 *            Sales domain controller.
	 */
	public SalesSystemUI(final SalesDomainController domainController) {
		this.domainController = domainController;
		this.model = new SalesSystemModel(domainController);

		productMenu = new JComboBox<String>();

		submitWindow = new SubmitPurchaseUI(this);
		soldItemsWindow = new SoldItemHistoryUI(model, this);
		addItemWindow = new AddStockItemUI(model, this, productMenu, domainController);

		// Create singleton instances of the tab classes
		historyTab = new HistoryTab(model, soldItemsWindow, domainController);
		stockTab = new StockTab(model, addItemWindow);
		purchaseTab = new PurchaseTab(domainController, model, submitWindow,
				productMenu);

		setTitle("Sales system");

		// set L&F to the nice Windows style
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

		} catch (UnsupportedLookAndFeelException e1) {
			log.warn(e1.getMessage());
		}

		drawWidgets();

		// size & location
		int width = 600;
		int height = 400;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				domainController.endSession();
				System.exit(0);
			}
		});
	}

	private void drawWidgets() {
		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.add("Point-of-sale", purchaseTab.draw());
		tabbedPane.add("Warehouse", stockTab.draw());
		tabbedPane.add("History", historyTab.draw());

		getContentPane().add(tabbedPane);
	}

}
