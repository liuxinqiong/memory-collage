package cn.com.listeners.reader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.reader.ReaderInfoMagFrame;

public class ReaderInfoMagFrame_btnAdd_ActionListener implements ActionListener {
	private ReaderInfoMagFrame rimf;
	public ReaderInfoMagFrame_btnAdd_ActionListener(ReaderInfoMagFrame rimf){
		this.rimf=rimf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.rimf.btnAdd_actionPerformed(e);
	}

}
