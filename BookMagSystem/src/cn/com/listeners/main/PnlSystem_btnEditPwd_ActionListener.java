package cn.com.listeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.main.pnl.PnlSystem;

public class PnlSystem_btnEditPwd_ActionListener implements ActionListener {
	private PnlSystem ps;
	public PnlSystem_btnEditPwd_ActionListener(PnlSystem ps){
		this.ps=ps;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.ps.btnEditPwd_actionPerformed(e);
	}

}
