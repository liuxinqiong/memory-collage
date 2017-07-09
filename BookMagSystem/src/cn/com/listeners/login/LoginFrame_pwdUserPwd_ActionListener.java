package cn.com.listeners.login;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cn.com.views.login.LoginFrame;

public class LoginFrame_pwdUserPwd_ActionListener implements KeyListener {
	private LoginFrame lf;
	public LoginFrame_pwdUserPwd_ActionListener(LoginFrame lf){
		this.lf=lf;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		this.lf.pwdUserPwd_keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
