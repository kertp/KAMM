package ee.ut.math.tvt.kamm;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class Intro {
	
	
	public void display(){
		//load a properties file from class path, inside static method
		Properties prop = new Properties();
		try{
		InputStream in = getClass().getResourceAsStream("/version.properties");
		prop.load(in);
		in.close();
		System.out.println(prop.get("major"));
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
		}catch(IOException e){
			
		}
	}
}
