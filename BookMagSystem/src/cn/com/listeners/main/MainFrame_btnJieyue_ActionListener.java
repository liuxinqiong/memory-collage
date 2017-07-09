package cn.com.listeners.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.main.MainFrame;

public class MainFrame_btnJieyue_ActionListener implements ActionListener {
	private MainFrame mf;
	public MainFrame_btnJieyue_ActionListener(MainFrame mf){
		this.mf=mf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mf.btnJieyue_actionPerformed(e);
	}

}
