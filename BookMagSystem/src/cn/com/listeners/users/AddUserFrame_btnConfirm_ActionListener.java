package cn.com.listeners.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.user.AddUserFrame;

public class AddUserFrame_btnConfirm_ActionListener implements ActionListener {
	private AddUserFrame  auf;
//	@Override
	public AddUserFrame_btnConfirm_ActionListener(AddUserFrame  auf){
		this.auf = auf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.auf.btnConfirm_Performed();
	}

}
