package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.petcard.ActPetCardFrame;

public class ActPetCardFrame_btn1_ActionListener implements ActionListener {
	private ActPetCardFrame acf;
	public ActPetCardFrame_btn1_ActionListener(ActPetCardFrame acf){
		this.acf = acf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.acf.btn1_Performed(e);
	}

}
