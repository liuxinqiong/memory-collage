package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.petcard.ActPetCardFrame;

public class ActPetCardFrame_btn3_ActionListener implements ActionListener {

	private ActPetCardFrame acf;
	public ActPetCardFrame_btn3_ActionListener(ActPetCardFrame acf){
		this.acf = acf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.acf.btn3_Performed(e);
	}

}
