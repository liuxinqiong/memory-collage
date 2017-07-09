package cn.com;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import cn.com.view.MainFrame;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.
                                             getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                new MainFrame();
            }
        });
	}

}
