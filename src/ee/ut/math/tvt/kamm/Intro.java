package ee.ut.math.tvt.kamm;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Intro {
	
	public void display(){
		//load a properties file from class path, inside static method
		Properties prop = new Properties();
		Properties vers = new Properties();
		try{
			prop.load(new FileInputStream("application.properties"));
			vers.load(new FileInputStream("version.properties"));
//		  JPanel panel = new JPanel();
			JFrame raam = new JFrame("JFrame");
		  raam.setLayout(new GridLayout(6,1));//FlowLayout()
		  raam.setSize(700,500);
		  JLabel versioon = new JLabel("Version: "+vers.getProperty("build.revision.number")+"."+
				  vers.getProperty("build.minor.number") + "." + vers.getProperty("build.major.number"));
		  JLabel tiimi_nimi = new JLabel("Team name: "+prop.getProperty("Team.name"));
		  JLabel liider = new JLabel("Team leader: "+prop.getProperty("Team.leader"));
		  JLabel liidri_email = new JLabel("Team leader email: "+prop.getProperty("Team.leader.email"));
		  JLabel tiim = new JLabel("Team members:"+" "+prop.getProperty("Team.member.1")+", "+
				  prop.getProperty("Team.member.2")+", "+prop.getProperty("Team.member.3")+", "+
				  prop.getProperty("Team.member.4"));
		  versioon.setFont(new Font("Serif", Font.PLAIN, 20));
		  tiim.setFont(new Font("Serif", Font.PLAIN, 20));
		  tiimi_nimi.setFont(new Font("Serif", Font.PLAIN, 20));
		  liider.setFont(new Font("Serif", Font.PLAIN, 20));
		  liidri_email.setFont(new Font("Serif", Font.PLAIN, 20));
		  raam.add(new JLabel(new ImageIcon(prop.getProperty("Team.logo"))));
		  raam.add(tiimi_nimi);
		  raam.add(liider);
		  raam.add(liidri_email);
		  raam.add(tiim);
		  raam.add(versioon);
		  raam.setVisible(true);
		}catch(IOException e){
			
		}
	}
}
