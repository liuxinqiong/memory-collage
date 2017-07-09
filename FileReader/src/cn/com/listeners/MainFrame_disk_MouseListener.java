package cn.com.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.com.views.MainFrame;

public class MainFrame_disk_MouseListener implements MouseListener {
	private MainFrame mf;
	public MainFrame_disk_MouseListener(MainFrame mf){
		this.mf=mf;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mf.disk_mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mf.disk_mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mf.disk_mouseExited(e);
	}

}
