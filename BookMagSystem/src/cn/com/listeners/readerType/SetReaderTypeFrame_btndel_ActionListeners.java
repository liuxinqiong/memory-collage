package cn.com.listeners.readerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.readerType.SetReaderTypeFrame;

public class SetReaderTypeFrame_btndel_ActionListeners implements
		ActionListener {

	private SetReaderTypeFrame srtf;
	public SetReaderTypeFrame_btndel_ActionListeners(SetReaderTypeFrame srtf){
		this.srtf = srtf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.srtf.btndel_ActionPerformed(e);
	}
}
