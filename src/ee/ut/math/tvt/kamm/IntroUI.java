package ee.ut.math.tvt.kamm;

import java.awt.*;
import javax.swing.*;


public class IntroUI {

	  public static void main(String[] args) {
	    JFrame raam = new JFrame("JFrame"); // raami loomine
	    raam.setSize(300, 300); // 
	    raam.setLocation(100, 100); // 

	    Container sisu = raam.getContentPane(); // konteineri loomine
	    sisu.setLayout(new FlowLayout()); // paigutushalduri kasutamine

	    JLabel silt = new JLabel("JLabel"); // 
	    JCheckBox linnuke = new JCheckBox("JCheckBox"); //

	// 
	// 
	          JPanel raadiopaneel = new JPanel(new GridLayout(2, 1));
	    JRadioButton raadionupp1 =
	                   new JRadioButton("JRadioButton 1");
	    JRadioButton raadionupp2 =
	                   new JRadioButton("JRadioButton 2");
	    ButtonGroup nupugrupp = new ButtonGroup();
	    nupugrupp.add(raadionupp1);
	    nupugrupp.add(raadionupp2);
	    raadiopaneel.add(raadionupp1);
	    raadiopaneel.add(raadionupp2);

	// 
//	         

	    JComboBox <String> valikukast = new JComboBox <String> ();
	    valikukast.addItem("esimene");
	    valikukast.addItem("teine");
	    valikukast.addItem("kolmas");

	// 
	// 
	          JButton nupp = new JButton("JButton");
	    nupp.setToolTipText("Head vajutamist!");
	    nupp.setMnemonic(java.awt.event.KeyEvent.VK_B);

	    sisu.add(silt);
	    sisu.add(linnuke);
	    sisu.add(raadiopaneel);
	    sisu.add(valikukast);
	    sisu.add(nupp);
	    raam.setVisible(true); 
	  }
}
