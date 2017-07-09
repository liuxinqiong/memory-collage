package cn.com.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.com.views.LoginFrame;

public class LoginFrame_btnLogin_MouseListener implements MouseListener {
	private LoginFrame lf;
	public LoginFrame_btnLogin_MouseListener(LoginFrame lf){
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
		this.lf.btnLogin_mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.lf.btnLogin_mouseExited(e);
	}

}
