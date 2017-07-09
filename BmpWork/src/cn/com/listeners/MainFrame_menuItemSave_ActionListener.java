package cn.com.listeners;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import cn.com.view.MainFrame;

public class MainFrame_menuItemSave_ActionListener implements ActionListener {
	private MainFrame mf;
	public MainFrame_menuItemSave_ActionListener(MainFrame mf){
		this.mf=mf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			this.mf.menuItemSave_actionPerformed(e);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
