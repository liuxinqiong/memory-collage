package cn.com.listeners.advanceBorrInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.advanceBorrInfo.AdvanceBorrFrame;

public class AdvanceBorrFrame_btnSure_ActionListener implements ActionListener {
	private AdvanceBorrFrame abf;
	public AdvanceBorrFrame_btnSure_ActionListener(AdvanceBorrFrame abf){
		this.abf=abf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.abf.btnSure_actionPerformed(e);
	}

}
