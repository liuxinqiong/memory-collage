package cn.com.listeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.main.pnl.PnlCard;

public class PnlCard_btnAdd_ActionListener implements ActionListener {
	private PnlCard pc;
	public PnlCard_btnAdd_ActionListener(PnlCard pc){
		this.pc=pc;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.pc.btnAdd_actionPerformed(e);
	}

}
