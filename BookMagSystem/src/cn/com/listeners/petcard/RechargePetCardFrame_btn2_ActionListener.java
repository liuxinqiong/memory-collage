package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.petcard.RechargePetCardFrame;

public class RechargePetCardFrame_btn2_ActionListener implements ActionListener {
	private RechargePetCardFrame rpcf;
	public RechargePetCardFrame_btn2_ActionListener(RechargePetCardFrame rpcf){
		this.rpcf = rpcf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.rpcf.btn2_Performed(e);
	}

}
