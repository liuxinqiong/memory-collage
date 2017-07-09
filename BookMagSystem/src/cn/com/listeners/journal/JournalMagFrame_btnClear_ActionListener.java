package cn.com.listeners.journal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.journal.JournalMagFrame;

public class JournalMagFrame_btnClear_ActionListener implements ActionListener {
	private JournalMagFrame jmf;
	public JournalMagFrame_btnClear_ActionListener(JournalMagFrame jmf){
		this.jmf=jmf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.jmf.btnClear_actionPerformed(e);
	}

}
