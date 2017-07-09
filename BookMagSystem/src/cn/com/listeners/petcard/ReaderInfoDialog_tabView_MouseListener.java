package cn.com.listeners.petcard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.com.views.petcard.ReaderInfoDialog;

public class ReaderInfoDialog_tabView_MouseListener implements MouseListener {
	private ReaderInfoDialog  rif;
	public ReaderInfoDialog_tabView_MouseListener(ReaderInfoDialog  rif){
		this.rif = rif;
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.rif.tabView_mouseClicked(e);
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
