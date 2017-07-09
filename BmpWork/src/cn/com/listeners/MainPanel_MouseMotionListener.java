package cn.com.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import cn.com.view.MainPanel;

public class MainPanel_MouseMotionListener implements MouseMotionListener {
	public MainPanel mp;
	
	public MainPanel_MouseMotionListener(MainPanel mp){
		this.mp=mp;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mp.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
