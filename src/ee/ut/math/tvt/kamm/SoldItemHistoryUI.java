package ee.ut.math.tvt.kamm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;


public class SoldItemHistoryUI {
	private JDialog panel;
	private SalesSystemModel model;
	private JFrame parent;

	  public SoldItemHistoryUI(SalesSystemModel model, JFrame parent) {
	    this.model = model;
	    this.parent = parent;
	    this.panel = new JDialog(parent);
	  }
	  public void draw() {
		  /*raam.setModal(true);
			raam.setResizable(false);
			raam.setSize(200, 200);
			raam.add(new JTextField("tere hommikust"));
			//Container parent = raam.getParent();
			//raam.setLocation(parent.getLocationOnScreen().x+(parent.getWidth()-200)/2,
			//		parent.getLocationOnScreen().y+(parent.getHeight()-200)/2);
			raam.setVisible(true);*/
	    //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
	    panel.add(drawStockMainPane(), gc);
	    panel.setModal(true);
	  }


	  // table of the wareshouse stock
	  private Component drawStockMainPane() {
	    JPanel panel = new JPanel();

	    JTable table = new JTable(model.getSoldItemHistoryModel());

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

	    panel.setBorder(BorderFactory.createTitledBorder("Sold items"));
	    return panel;
	  }
		public void setVisible(boolean b) {
			if (b == true) {
				panel.setLocation(parent.getLocationOnScreen().x+(parent.getWidth()-200)/2,
						parent.getLocationOnScreen().y+(parent.getHeight()-200)/2);
				//paid.grabFocus();
				//paid.requestFocus();
			}
			panel.setVisible(b);
		}
	

}



