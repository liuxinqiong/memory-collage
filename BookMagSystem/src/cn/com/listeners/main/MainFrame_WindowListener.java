package cn.com.listeners.main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import cn.com.views.main.MainFrame;

public class MainFrame_WindowListener implements WindowListener {
	private MainFrame mf;
	public MainFrame_WindowListener(MainFrame mf){
		this.mf=mf;
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.mf.MainFrame_windowClosing(e);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
