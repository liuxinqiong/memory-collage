package cn.com.listeners.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.user.MgrUserFrame;

public class MgrUserFrame_btnAdd_ActionListener implements ActionListener {
	private MgrUserFrame muf;
//	@Override
	public MgrUserFrame_btnAdd_ActionListener(MgrUserFrame muf){
		this.muf = muf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.muf.btnAdd_actionPerformed(e);
	}

}
