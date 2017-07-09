package cn.com.listeners.readerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.readerType.AlterReaderTypeFrame;

public class AlterReaderTypeFrame_btncanel_ActionListener implements
		ActionListener {
	private AlterReaderTypeFrame artf;
	public AlterReaderTypeFrame_btncanel_ActionListener(AlterReaderTypeFrame artf){
		this.artf = artf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.artf.btncanel_ActionPerformed(e);
	}

}
