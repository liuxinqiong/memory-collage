package cn.com.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.view.MainFrame;

public class MainFrame_menuItemOpen_ActionListener implements ActionListener {
	private MainFrame mf;
	public MainFrame_menuItemOpen_ActionListener(MainFrame mf){
		this.mf=mf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mf.menuItemOpen_actionPerformed(e);
	}

}
