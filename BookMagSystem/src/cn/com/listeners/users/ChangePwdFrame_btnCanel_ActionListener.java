package cn.com.listeners.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.user.ChangePwdFrame;

public class ChangePwdFrame_btnCanel_ActionListener implements ActionListener {
	private ChangePwdFrame cpf;
	//@Override
	public ChangePwdFrame_btnCanel_ActionListener(ChangePwdFrame cpf){
		this.cpf = cpf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.cpf.btnCanel_actionPerformed(e);
	}

}
