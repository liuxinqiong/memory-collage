package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.petcard.LogOffPetCardFrame;

public class LogOffPetCardFrame_btn3_ActionListener implements ActionListener {

	private LogOffPetCardFrame lpcf;
	public LogOffPetCardFrame_btn3_ActionListener(LogOffPetCardFrame lpcf){
		this.lpcf = lpcf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.lpcf.btn3_Performed(e);
	}
}
