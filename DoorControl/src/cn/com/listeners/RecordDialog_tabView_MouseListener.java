package cn.com.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.com.views.RecordDialog;

public class RecordDialog_tabView_MouseListener implements MouseListener {
	private RecordDialog rd;
	public RecordDialog_tabView_MouseListener(RecordDialog rd){
		this.rd=rd;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.rd.tabView_mouseClicked(e);
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
