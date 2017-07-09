package cn.com.listeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.main.pnl.PnlSystem;

public class PnlSystem_btnManage_ActionListener implements ActionListener {
	private PnlSystem ps;
	public PnlSystem_btnManage_ActionListener(PnlSystem ps){
		this.ps=ps;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.ps.btnManage_actionPerformed(e);
	}

}
