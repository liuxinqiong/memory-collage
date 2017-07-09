package cn.com.listeners.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.user.MgrUserFrame;

public class MgrUserFrame_btnDelete_Actionlistener implements ActionListener {
	private MgrUserFrame muf;
	
	public MgrUserFrame_btnDelete_Actionlistener( MgrUserFrame muf){
		this.muf = muf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.muf.btnDelete_actionPerformed(e);
	}

}
