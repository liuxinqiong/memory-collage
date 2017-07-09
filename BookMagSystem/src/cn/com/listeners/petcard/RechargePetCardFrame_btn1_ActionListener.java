package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.petcard.RechargePetCardFrame;

public class RechargePetCardFrame_btn1_ActionListener implements ActionListener {
	private RechargePetCardFrame rpcf;
	public  RechargePetCardFrame_btn1_ActionListener(RechargePetCardFrame rpcf){
		this.rpcf = rpcf;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.rpcf.btn1_actionPerformed(e);
	}

}
