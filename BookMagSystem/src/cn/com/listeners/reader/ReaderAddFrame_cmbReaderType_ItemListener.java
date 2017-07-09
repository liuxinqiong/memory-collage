package cn.com.listeners.reader;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import cn.com.views.reader.ReaderAddFrame;

public class ReaderAddFrame_cmbReaderType_ItemListener implements ItemListener {
	private ReaderAddFrame raf;
	public ReaderAddFrame_cmbReaderType_ItemListener(ReaderAddFrame raf){
		this.raf=raf;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		this.raf.cmbReaderType_itemStateChanged(e);
	}

}
