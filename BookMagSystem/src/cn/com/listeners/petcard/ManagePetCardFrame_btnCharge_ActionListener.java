package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.petcard.ManagePetCardFrame;

public class ManagePetCardFrame_btnCharge_ActionListener implements	ActionListener {

	private ManagePetCardFrame mpcf;
	public ManagePetCardFrame_btnCharge_ActionListener (ManagePetCardFrame mpcf){
		this.mpcf = mpcf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mpcf.btnCharge_Performed(e);
	}

}
