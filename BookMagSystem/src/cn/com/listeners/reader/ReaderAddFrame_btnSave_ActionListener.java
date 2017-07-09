package cn.com.listeners.reader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.reader.ReaderAddFrame;

public class ReaderAddFrame_btnSave_ActionListener implements ActionListener {
	private ReaderAddFrame raf;
	public ReaderAddFrame_btnSave_ActionListener(ReaderAddFrame raf){
		this.raf=raf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.raf.btnSave_actionPerformed(e);
	}

}
