package cn.com.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.com.views.MagMagDialog;

public class MagMagDialog_tabView_MouseListener implements MouseListener {
	private MagMagDialog mmd;
	
	public MagMagDialog_tabView_MouseListener( MagMagDialog mmd){
		this.mmd=mmd;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mmd.tabView_mouseClicked(e);
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

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
