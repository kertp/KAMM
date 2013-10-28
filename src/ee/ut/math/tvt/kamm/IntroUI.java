package ee.ut.math.tvt.kamm;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class IntroUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame raam = this;

	public JFrame display() {
		// load a properties file from class path, inside static method
		Properties prop = new Properties();
		Properties vers = new Properties();
		try {
			prop.load(new FileInputStream("application.properties"));
			vers.load(new FileInputStream("version.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width = 700;
		int height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);
		raam.setLayout(new GridLayout(6, 1));
		raam.setSize(width, height);
		JLabel versioon = new JLabel("Version: "
				+ vers.getProperty("build.major.number") + "."
				+ vers.getProperty("build.minor.number") + "."
				+ vers.getProperty("build.revision.number"));
		JLabel tiimi_nimi = new JLabel("Team name: "
				+ prop.getProperty("Team.name"));
		JLabel liider = new JLabel("Team leader: "
				+ prop.getProperty("Team.leader"));
		JLabel liidri_email = new JLabel("Team leader email: "
				+ prop.getProperty("Team.leader.email"));
		JLabel tiim = new JLabel("Team members:" + " "
				+ prop.getProperty("Team.member.1") + ", "
				+ prop.getProperty("Team.member.2") + ", "
				+ prop.getProperty("Team.member.3"));
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
		return raam;
	}
}