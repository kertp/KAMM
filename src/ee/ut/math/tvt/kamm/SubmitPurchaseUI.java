package ee.ut.math.tvt.kamm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

public class SubmitPurchaseUI{
	private static final Logger log = Logger.getLogger(SubmitPurchaseUI.class);
	private JDialog raam;
	private JTextField total = new JTextField();
	private JTextField paid = new JTextField();
	private JTextField change = new JTextField();
	private JButton confirm = new JButton("Confirm");
	private JButton cancel = new JButton("Cancel");
	private JFrame parent;
	private boolean confirmed;

	public SubmitPurchaseUI (JFrame parent) {
		this.parent = parent;
		this.raam = new JDialog(parent);
	}
	public void setTotal(float sum) {
		total.setText(String.valueOf(sum));
		if (sum > 0)
			confirm.setEnabled(false);
	}
	public void setPaid (String text) {
		paid.setText(text);
	}
	public void setChange(String text) {
		change.setText(text);
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setVisible(boolean b) {
		if (b == true) {
			raam.setLocation(parent.getLocationOnScreen().x+(parent.getWidth()-200)/2,
					parent.getLocationOnScreen().y+(parent.getHeight()-200)/2);
			//paid.grabFocus();
			//paid.requestFocus();
		}
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
		paid.addKeyListener(new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				String paid_text = paid.getText();
				int paid_len = paid_text.length();
				char last_input;
				try {
					Float.parseFloat(paid_text);
					if (paid_len > 1) {
						last_input = paid_text.charAt(paid_len-1);
						if (last_input == 'f' || last_input == 'd')
							throw new NumberFormatException();
					}
					float change_float = Float.parseFloat(paid_text)-Float.parseFloat(total.getText());
					change_float = (float)(Math.round(change_float*100)/100.0);
					change.setText(String.valueOf(change_float));
					if (change_float >= 0) {
						confirm.setEnabled(true);
					}
					else
						confirm.setEnabled(false);
				} catch (NumberFormatException ex) {
					if (paid_text.equals("."))
						change.setText("");
					else if (paid_len == 0)
						change.setText("");
					else
						paid.setText(paid_text.substring(0, paid_len-1));
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				String paid_text = paid.getText();
				int paid_len = paid_text.length();
				char last_input;
				int point_index = paid_text.indexOf('.');
				try {
					Float.parseFloat(paid_text);
					if (paid_len > 1) {
						last_input = paid_text.charAt(paid_len-1);
						if (last_input == 'f' || last_input == 'd')
							throw new NumberFormatException();
					}
					if (point_index == paid_len - 3 && point_index != -1)
						throw new NumberFormatException();
				} catch (NumberFormatException ex) {
					if (paid_text.equals("."))
						change.setText("");
					else if (paid_len == 0)
						change.setText("");
					else
						paid.setText(paid_text.substring(0, paid_len-1));
				}
			}
		});
		raam.add(confirm);
		raam.add(cancel);
		return raam;
	}
}
