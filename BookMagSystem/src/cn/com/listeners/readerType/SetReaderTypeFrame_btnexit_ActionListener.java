package cn.com.listeners.readerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.readerType.SetReaderTypeFrame;

public class SetReaderTypeFrame_btnexit_ActionListener implements
		ActionListener {
	private SetReaderTypeFrame srtf;
	public SetReaderTypeFrame_btnexit_ActionListener(SetReaderTypeFrame srtf){
		this.srtf = srtf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.srtf.btnexit_ActionPerformed(e);
	}
}
