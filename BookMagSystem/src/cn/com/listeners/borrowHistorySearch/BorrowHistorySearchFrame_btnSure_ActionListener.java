package cn.com.listeners.borrowHistorySearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.borrowHistorySearch.BorrowHistorySearchFrame;

public class BorrowHistorySearchFrame_btnSure_ActionListener implements
		ActionListener {
	private BorrowHistorySearchFrame bhsf;
	public BorrowHistorySearchFrame_btnSure_ActionListener(BorrowHistorySearchFrame bhsf){
		this.bhsf=bhsf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.bhsf.btnSure_actionPerformed(e);
	}

}
