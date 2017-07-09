package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.petcard.ManagePetCardFrame;

public class ManagePetCardFrame_btnFind_ActionListener implements	ActionListener {

	private ManagePetCardFrame mpcf;
	public ManagePetCardFrame_btnFind_ActionListener (ManagePetCardFrame mpcf){
		this.mpcf = mpcf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mpcf.btnFind_Performed(e);
	}

}
