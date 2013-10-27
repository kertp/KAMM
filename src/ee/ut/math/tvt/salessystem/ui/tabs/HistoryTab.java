package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.kamm.SoldItemHistoryUI;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
    
	private static final Logger log = Logger.getLogger(HistoryTab.class);
	
	private JTable table;
    // TODO - implement!
	private SalesSystemModel model;
	//private final SalesDomainController domainController;

    public HistoryTab(SalesSystemModel model) {
    	this.model = model;
    	//this.domainController = domainController;
    }
    public Component draw() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(gb);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0d;
        gc.weighty = 0d;

        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawHistoryMainPane(), gc);
        return panel;
      }
    public Component drawHistoryMainPane() {
        JPanel panel = new JPanel();
        table = new JTable(model.getHistoryTableModel());
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener () {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				SoldItemHistoryUI aken = new SoldItemHistoryUI();
				// TODO Auto-generated method stub
				int viewRow = table.getSelectedRow();
				//System.out.println(viewRow);
				//System.out.println(arg0.toString());
				if (viewRow != -1 && arg0.getValueIsAdjusting() == false) {
				for (SoldItem item : model.getHistoryTableModel().getTableRows().get(viewRow).getSoldItems()) {
					System.out.println(item.getName() +" "+ item.getPrice() +" "+ item.getId() +" "+
				item.getQuantity()+" "+item.getSum());
					aken.draw();
				}
				}
			}
        	
        });

        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        panel.setLayout(gb);
        panel.add(scrollPane, gc);

        panel.setBorder(BorderFactory.createTitledBorder("History"));
        return panel;
      }
}