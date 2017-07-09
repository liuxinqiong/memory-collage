package cn.com.listeners.keepMoney;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.keepMoney.GetKeepMoneyFrame;

public class GetKeepMoneyFrame_btnSearch_ActionListener implements
		ActionListener {
	private GetKeepMoneyFrame gkmf;
	public GetKeepMoneyFrame_btnSearch_ActionListener( GetKeepMoneyFrame gkmf){
		this.gkmf = gkmf;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.gkmf.btnSearch_ActionPerformed(e);
	}

}
