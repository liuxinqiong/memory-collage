package cn.com.listeners.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.user.AddUserFrame;

public class AddUserFrame_btnCanel_ActionListener implements ActionListener {
	private AddUserFrame auf;
	//@Override
	public AddUserFrame_btnCanel_ActionListener(AddUserFrame auf){
		this.auf = auf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.auf.btnCanel_Performed(e);
	}

}
