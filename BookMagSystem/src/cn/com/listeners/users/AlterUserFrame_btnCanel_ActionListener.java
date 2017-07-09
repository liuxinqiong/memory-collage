package cn.com.listeners.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.user.AlterUserFrame;

public class AlterUserFrame_btnCanel_ActionListener implements ActionListener {
	private AlterUserFrame auf;
	public AlterUserFrame_btnCanel_ActionListener(AlterUserFrame auf){
		this.auf = auf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.auf.btnCanel_Performed();
	}

}
