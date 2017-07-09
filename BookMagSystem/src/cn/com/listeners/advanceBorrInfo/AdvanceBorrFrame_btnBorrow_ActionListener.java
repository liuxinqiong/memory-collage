package cn.com.listeners.advanceBorrInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.advanceBorrInfo.AdvanceBorrFrame;

public class AdvanceBorrFrame_btnBorrow_ActionListener implements
		ActionListener {
	private AdvanceBorrFrame abf;
	public AdvanceBorrFrame_btnBorrow_ActionListener(AdvanceBorrFrame abf){
		this.abf=abf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.abf.btnBorrow_actionPerformed(e);
	}

}
