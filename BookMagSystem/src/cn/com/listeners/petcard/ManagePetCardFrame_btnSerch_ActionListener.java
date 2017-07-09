package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.petcard.ManagePetCardFrame;

public class ManagePetCardFrame_btnSerch_ActionListener implements
		ActionListener {
	private ManagePetCardFrame mpcf;
	public ManagePetCardFrame_btnSerch_ActionListener(ManagePetCardFrame mpcf){
		this.mpcf = mpcf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mpcf.btnSerch_ActionPerformed(e);
	}

}
