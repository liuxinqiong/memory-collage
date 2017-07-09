package cn.com.listeners.petcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cn.com.views.petcard.ManagePetCardFrame;

public class ManagePetCardFrame_rbtnAllUser_ActionListener implements
	ActionListener {
	private ManagePetCardFrame mpcf;
	public ManagePetCardFrame_rbtnAllUser_ActionListener(ManagePetCardFrame mpcf){
		this.mpcf=mpcf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mpcf.rbtnAllUser_actionPerformed(e);
	}

}
