package cn.com.listeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.main.pnl.PnlQuery;

public class PnlQuery_btn3_ActionListener implements ActionListener {
	private PnlQuery pq;
	public PnlQuery_btn3_ActionListener(PnlQuery pq){
		this.pq=pq;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.pq.btn3_actionPerformed(e);
	}

}
