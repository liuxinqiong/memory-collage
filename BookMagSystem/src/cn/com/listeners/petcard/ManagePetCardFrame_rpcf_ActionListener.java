package cn.com.listeners.petcard;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import cn.com.views.petcard.ManagePetCardFrame;

public class ManagePetCardFrame_rpcf_ActionListener implements WindowListener {
	private ManagePetCardFrame mpcf;
	public ManagePetCardFrame_rpcf_ActionListener(ManagePetCardFrame mpcf){
		this.mpcf=mpcf;
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.mpcf.rpcf_windowClosing(e);
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
