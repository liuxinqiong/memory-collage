package cn.com.listeners;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import cn.com.views.MainFrame;

public class MainFrame_treeView_SelectionListener implements
		TreeSelectionListener {
	private MainFrame mf;
	public MainFrame_treeView_SelectionListener(MainFrame mf){
		this.mf=mf;
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		this.mf.treeView_valueChanged(e);
	}

}
