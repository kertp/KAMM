package ee.ut.math.tvt.kamm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
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

	private JComboBox<ComboItem> productMenu;

	private String price_text = "";
	private boolean kala = false;
	
	SalesDomainController dc;

	public AddStockItemUI(SalesSystemModel model, JFrame parent,
			JComboBox<ComboItem> productMenu, SalesDomainController dc) {
		this.model = model;
		this.parent = parent;
		this.panel = new JDialog(parent);
		this.productMenu = productMenu;
		this.dc = dc;
	}

	public void draw() {

		panel.setLayout(new GridLayout(6, 2));
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

			public void changeValue() {
				try {
					Long idv = Long.valueOf(id.getText());
					for (StockItem item : model.getWarehouseTableModel()
							.getTableRows()) {
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
					price_text = "";
					name.setText("");
					description.setText("");
					price.setText("");
					name.setEditable(true);
					description.setEditable(true);
					price.setEditable(true);
				} catch (NumberFormatException ex) {
					price_text = "";
					name.setText("");
					description.setText("");
					price.setText("");
					name.setEditable(true);
					description.setEditable(true);
					price.setEditable(true);
				}
			}

		});
		price.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				valueChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (price_text.length() == 1)
					price_text = "";
				valueChanged();
			}

			public void valueChanged() {
				try {
					int price_len = price.getText().length();
					int point_index = price_text.indexOf('.');
					if (point_index == price_len - 4 && point_index != -1) {
						throw new NumberFormatException();
					}
					Float.parseFloat(price.getText());
					price_text = price.getText();
				} catch (NumberFormatException ex) {
					if (price_text.length() > 0) {
						price_text = price_text.substring(0,
								price_text.length());
					}
					kala = true;
				}
			}

		});
		price.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				changeValue();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				changeValue();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			public void changeValue() {
				if (kala == true) {
					price.setText(price_text);
					kala = false;
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
		panel.add(productMenu);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Long idv = Long.valueOf(id.getText());
					String namev = name.getText();
					String descriptionv = description.getText();
					double pricev = Math.round(Float.parseFloat(price.getText())*100.0)/100.0;
					int quantityv = Integer.parseInt(quantity.getText());
					if (quantityv < 0)
						throw new NumberFormatException();
					StockItem stockItem = new StockItem(idv, namev,
							descriptionv, pricev, quantityv);
					model.getWarehouseTableModel().addItem(stockItem);
					dc.loadWarehouseState().add(stockItem);
					dc.addStockItem(stockItem);
					boolean olemas = false;
					for (int i = 0; i < productMenu.getItemCount(); i++) {
						if (Long.parseLong(productMenu.getItemAt(i).getId()) == idv) {
							olemas = true;
							break;
						}
					}
					if (!olemas)
						productMenu.addItem(new ComboItem(namev, String.valueOf(idv)));
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
		panel.setSize(300, 200);
	}

	public void setVisible(boolean b) {
		if (b == true) {
			panel.setLocation(
					parent.getLocationOnScreen().x + (parent.getWidth() - 300)
							/ 2,
					parent.getLocationOnScreen().y + (parent.getHeight() - 200)
							/ 2);
		}
		price_text = "";
		price.setText("");
		name.setText("");
		quantity.setText("");
		description.setText("");
		id.setText("");
		panel.setVisible(b);
	}
}
