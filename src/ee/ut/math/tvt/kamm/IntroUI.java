package ee.ut.math.tvt.kamm;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.*;
import javax.swing.*;

public class IntroUI{

	  public static void main(String[] args) {
		  ImageIcon guy = new ImageIcon("lib/pictures/kamm.png");
		  JLabel pn = new JLabel(guy);
		  JPanel panel = new JPanel();
		  JFrame raam = new JFrame("JFrame");
		  raam.setLayout(new FlowLayout());
		  raam.setSize(700,500);
		  raam.add(new JLabel(new ImageIcon("lib/pictures/kamm.png")));
		  JLabel tiim = new JLabel("VÕISTKOND KAMM: Anti Ingel, Kert Pjatkin, Merlin Saulep, Harri Saar");
		  tiim.setFont(new Font("Serif", Font.PLAIN, 20));
		  raam.add(tiim);
		  raam.setVisible(true);
	  }
}
