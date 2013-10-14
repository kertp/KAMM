package ee.ut.math.tvt.kamm;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Intro {
	
	
	public void display(){
	    JFrame raam = new JFrame("JFrame"); // raami loomine
	    raam.setSize(800, 800); // 
	    raam.setLocation(100, 100); // 
	    
	    JLabel tiim = new JLabel("VÕISTKOND KAMM: Anti Ingel, Kert Pjatkin, Merlin Saulep, Harri Saar");
	    tiim.setFont(new Font("Serif", Font.PLAIN, 20));
	    raam.add(tiim);

	    raam.add(tiim);
	    
	    raam.setSize(230, 230);

	    raam.setVisible(true); 
	}
}
