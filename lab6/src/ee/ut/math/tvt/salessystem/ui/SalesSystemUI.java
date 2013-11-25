package ee.ut.math.tvt.salessystem.ui;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.ClientTab;
import ee.ut.math.tvt.salessystem.ui.tabs.HistoryTab;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;
import ee.ut.math.tvt.salessystem.ui.tabs.StockTab;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

  // Warehouse model
  private SalesSystemModel model;

  // Instances of tab classes
  private JTabbedPane tabbedPane;
  private PurchaseTab purchaseTab;
  private HistoryTab historyTab;
  private StockTab stockTab;
  private ClientTab clientTab;
  private SalesDomainController domainController;

  /**
   * Constructs sales system GUI.
   * @param domainController Sales domain controller.
   */
  public SalesSystemUI(SalesDomainController domainController) {
    this.domainController = domainController;
    this.model = new SalesSystemModel(domainController);
    this.tabbedPane = new JTabbedPane();
    domainController.setModel(model);

    // Create singleton instances of the tab classes
    historyTab = new HistoryTab(model);
    stockTab = new StockTab(model, domainController);
    purchaseTab = new PurchaseTab(domainController, model, this);
    clientTab = new ClientTab(model);

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
            SalesSystemUI.this.domainController.endSession();
            log.info("SalesSystem closed");
            System.exit(0);
        }
    });
  }

  private void drawWidgets() {
    tabbedPane.add("Point-of-sale", purchaseTab.draw());
    tabbedPane.add("Warehouse", stockTab.draw());
    tabbedPane.add("History", historyTab.draw());
    tabbedPane.add("Clients", clientTab.draw());

    getContentPane().add(tabbedPane);
    tabbedPane.addChangeListener(new TabChangeListener(this));
  }
  
  public void getActiveTabAndRefresh(){
	  System.out.println(tabbedPane.getSelectedIndex());
	  if(1 == tabbedPane.getSelectedIndex()){
		  stockTab.refresh(domainController);
	  }
	  else if(2 == tabbedPane.getSelectedIndex()){
		  historyTab.refresh(domainController);
	  }
	  else if(3 == tabbedPane.getSelectedIndex()){
		  clientTab.refresh(domainController);
	  }
  }

}


