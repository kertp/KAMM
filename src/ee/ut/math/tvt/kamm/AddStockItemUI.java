package ee.ut.math.tvt.kamm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class AddStockItemUI {
	private JDialog panel;
	private SalesSystemModel model;
	private JFrame parent;
	
	private JTextField id = new JTextField();
	private JTextField name = new JTextField();
	private JTextField price = new JTextField();
	private JTextField quantity = new JTextField();
	private JTextField description = new JTextField();
	
	private JButton confirm = new JButton("Confirm");
	private JButton cancel = new JButton("Cancel");
	
	private JComboBox <String> productMenu;

	public AddStockItemUI(SalesSystemModel model, JFrame parent, JComboBox <String> productMenu) {
		this.model = model;
		this.parent = parent;
		this.panel = new JDialog(parent);
		this.productMenu = productMenu;
	}
	  public void draw() {

		    /*GridBagLayout gb = new GridBagLayout();
		    GridBagConstraints gc = new GridBagConstraints();*/
		    panel.setLayout(new GridLayout(6, 2));

		    /*gc.fill = GridBagConstraints.HORIZONTAL;
		    gc.anchor = GridBagConstraints.NORTH;
		    gc.gridwidth = GridBagConstraints.REMAINDER;
		    gc.weightx = 1.0d;
		    gc.weighty = 0d;

		    gc.weighty = 1.0;
		    gc.fill = GridBagConstraints.BOTH;*/
		    id.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					changeValue();
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					changeValue();
				}
				public void changeValue () {
					try {
						Long idv = Long.valueOf(id.getText());
						for (StockItem item : model.getWarehouseTableModel().getTableRows()) {
							if (item.getId() == idv) {
								name.setEditable(false);
								description.setEditable(false);
								price.setEditable(false);
								name.setText(item.getName());
								description.setText(item.getDescription());
								price.setText(String.valueOf(item.getPrice()));
								return;
							}
						}
						name.setText("");
						description.setText("");
						price.setText("");
						name.setEditable(true);
						description.setEditable(true);
						price.setEditable(true);
					} catch (NumberFormatException ex) {
						name.setText("");
						description.setText("");
						price.setText("");
						name.setEditable(true);
						description.setEditable(true);
						price.setEditable(true);
					}
				}
		    	
		    });
			panel.add(new JLabel("Id:"));
			panel.add(id);
			panel.add(new JLabel("Name:"));
			panel.add(name);
			panel.add(new JLabel("Description:"));
			panel.add(description);
			panel.add(new JLabel("Price:"));
			panel.add(price);
			panel.add(new JLabel("Quantity:"));
			panel.add(quantity);
			confirm.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						Long idv = Long.valueOf(id.getText());
						String namev = name.getText();
						String descriptionv = description.getText();
						float pricev = Float.parseFloat(price.getText());
						int quantityv = Integer.parseInt(quantity.getText());
						StockItem stockItem = new StockItem(idv, namev, descriptionv, pricev, quantityv);
						model.getWarehouseTableModel().addItem(stockItem);
						productMenu.addItem(namev);
						System.out.println(namev);
						System.out.println(productMenu);
						setVisible(false);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Numbers incorrect!");
					}
				}
				
			});
			cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
				
			});
			panel.add(confirm);
			panel.add(cancel);
		    panel.setModal(true);
		    panel.setSize(200, 200);
		  }
		public void setVisible(boolean b) {
			if (b == true) {
				panel.setLocation(parent.getLocationOnScreen().x+(parent.getWidth()-200)/2,
						parent.getLocationOnScreen().y+(parent.getHeight()-200)/2);
			}
			panel.setVisible(b);
		}
}
