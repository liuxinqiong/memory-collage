package cn.com.listeners.extendedBorrInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.extendedMag.ExtendedMagFrame;

public class ExtendedMagFrame_btnSure_ActionListener implements ActionListener {
	private ExtendedMagFrame emf;
	public ExtendedMagFrame_btnSure_ActionListener(ExtendedMagFrame emf){
		this.emf=emf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.emf.btnSure_actionPerformed(e);
	}

}
