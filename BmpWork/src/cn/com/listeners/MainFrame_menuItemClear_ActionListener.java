package cn.com.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.view.MainFrame;

public class MainFrame_menuItemClear_ActionListener implements ActionListener {
	private MainFrame mf;
	public MainFrame_menuItemClear_ActionListener(MainFrame mf){
		this.mf=mf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.mf.menuItemClear_actionPerformed(e);
	}	

}
