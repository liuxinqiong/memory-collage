package cn.com.listeners.login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.com.views.login.LoginFrame;

public class LoginFrame_cmbView_MouseListener implements MouseListener {
	private LoginFrame lf;
	public LoginFrame_cmbView_MouseListener(LoginFrame lf){
		this.lf=lf;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.lf.cmbView_mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.lf.cmbView_mouseExited(e);
	}

}
