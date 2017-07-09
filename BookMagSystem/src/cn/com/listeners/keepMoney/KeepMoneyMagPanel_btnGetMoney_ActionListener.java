package cn.com.listeners.keepMoney;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.keepMoney.GetKeepMoneyFrame;
import cn.com.views.keepMoney.KeepMoneyMagPanel;

public class KeepMoneyMagPanel_btnGetMoney_ActionListener implements
		ActionListener {
	private KeepMoneyMagPanel kmmp;
	public KeepMoneyMagPanel_btnGetMoney_ActionListener( KeepMoneyMagPanel kmmp){
		this.kmmp = kmmp;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.kmmp.btnGetMoney_ActionPerformed(e);
	}


}
