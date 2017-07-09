package cn.com;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.ModerateSkin;

import cn.com.views.MainFrame;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SubstanceLookAndFeel.setSkin(new ModerateSkin());
				new MainFrame();
			}
		});	
	}

}
