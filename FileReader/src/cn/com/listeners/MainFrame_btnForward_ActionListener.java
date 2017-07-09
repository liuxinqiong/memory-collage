package cn.com.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.MainFrame;

public class MainFrame_btnForward_ActionListener implements ActionListener {
	private MainFrame mf;
	public MainFrame_btnForward_ActionListener(MainFrame mf){
		this.mf=mf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mf.btnForward_actionPerformed(e);
	}
}
