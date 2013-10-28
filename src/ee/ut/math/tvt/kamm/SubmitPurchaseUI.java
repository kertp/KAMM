package ee.ut.math.tvt.kamm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//import org.apache.log4j.Logger;

public class SubmitPurchaseUI {
	//private static final Logger log = Logger.getLogger(SubmitPurchaseUI.class);
	private JDialog raam;
	private JTextField total = new JTextField();
	private JTextField paid = new JTextField();
	private JTextField change = new JTextField();
	private JButton confirm = new JButton("Confirm");
	private JButton cancel = new JButton("Cancel");
	private JFrame parent;
	private boolean confirmed;

	private String paid_text = "";
	private boolean kala = false;

	public SubmitPurchaseUI(JFrame parent) {
		this.parent = parent;
		this.raam = new JDialog(parent);
	}

	public void setTotal(float sum) {
		total.setText(String.format(Locale.US, "%.2f", sum));
		if (sum > 0)
			confirm.setEnabled(false);
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setVisible(boolean b) {
		if (b == true) {
			raam.setLocation(
					parent.getLocationOnScreen().x + (parent.getWidth() - 200)
							/ 2,
					parent.getLocationOnScreen().y + (parent.getHeight() - 200)
							/ 2);
		}
		paid_text = "";
		paid.setText("");
		change.setText(String.format(Locale.US, "%.2f",
				-Float.parseFloat(total.getText())));
		raam.setVisible(b);
	}

	public JDialog drawSubmitWindow() {
		raam.setModal(true);
		raam.setResizable(false);
		raam.setLayout(new GridLayout(4, 2));
		raam.setSize(200, 200);
		raam.add(new JLabel("Total:"));
		raam.add(total);
		raam.add(new JLabel("Paid:"));
		raam.add(paid);
		raam.add(new JLabel("Change:"));
		raam.add(change);
		total.setEditable(false);
		change.setEditable(false);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmed = true;
				setVisible(false);
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmed = false;
				setVisible(false);
			}
		});
		paid.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				valueChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (paid_text.length() == 1)
					paid_text = "";
				valueChanged();
			}

			public void valueChanged() {
				try {
					int paid_len = paid.getText().length();
					int point_index = paid_text.indexOf('.');
					if (point_index == paid_len - 4 && point_index != -1) {
						throw new NumberFormatException();
					}
					float change_float = Float.parseFloat(paid.getText())
							- Float.parseFloat(total.getText());
					change.setText(String.format(Locale.US, "%.2f",
							change_float));
					paid_text = paid.getText();
				} catch (NumberFormatException ex) {
					change.setText(String.format(Locale.US, "%.2f",
							-Float.parseFloat(total.getText())));
					if (paid_text.length() > 0) {
						paid_text = paid_text.substring(0, paid_text.length());
					}
					kala = true;
				}
			}

		});
		paid.addKeyListener(new KeyListener() {

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
				if (Float.parseFloat(change.getText()) >= 0)
					confirm.setEnabled(true);
				else
					confirm.setEnabled(false);
				if (kala == true) {
					paid.setText(paid_text);
					kala = false;
				}
			}

		});
		raam.add(confirm);
		raam.add(cancel);
		return raam;
	}
}
