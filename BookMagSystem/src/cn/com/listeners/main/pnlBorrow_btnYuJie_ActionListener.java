package cn.com.listeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.main.pnl.PnlBorrow;

public class pnlBorrow_btnYuJie_ActionListener implements ActionListener {
	private PnlBorrow pb;
	public pnlBorrow_btnYuJie_ActionListener(PnlBorrow pb){
		this.pb=pb;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.pb.btnYuJie_actionPerformed();
	}

}
