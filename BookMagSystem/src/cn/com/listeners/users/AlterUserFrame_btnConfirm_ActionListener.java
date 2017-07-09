package cn.com.listeners.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.user.AlterUserFrame;

public class AlterUserFrame_btnConfirm_ActionListener implements ActionListener {

		// TODO Auto-generated method stub
	private AlterUserFrame  auf;
//		@Override
	public AlterUserFrame_btnConfirm_ActionListener(AlterUserFrame  auf){
			this.auf = auf;
		}
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			this.auf.btnConfirm_Performed(e);
		}	
}
