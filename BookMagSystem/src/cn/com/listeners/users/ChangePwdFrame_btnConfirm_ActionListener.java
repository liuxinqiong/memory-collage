package cn.com.listeners.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.user.ChangePwdFrame;

public class ChangePwdFrame_btnConfirm_ActionListener implements ActionListener {
	private ChangePwdFrame cpf;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.cpf.btnConfirm_actionPerformed(e);
	}
	public ChangePwdFrame_btnConfirm_ActionListener(ChangePwdFrame cpf){
		this.cpf = cpf;
	}

}
