package cn.com.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.LoginFrame;

public class LoginFrame_btnLogin_ActionListener implements ActionListener {
	private LoginFrame lf;
	public LoginFrame_btnLogin_ActionListener(LoginFrame lf){
		this.lf=lf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.lf.btnLogin_actionPerformed(e);
	}

}
