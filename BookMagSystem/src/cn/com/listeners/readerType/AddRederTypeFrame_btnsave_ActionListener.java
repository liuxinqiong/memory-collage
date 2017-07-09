package cn.com.listeners.readerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.views.readerType.AddRederTypeFrame;

public class AddRederTypeFrame_btnsave_ActionListener implements ActionListener {

	private AddRederTypeFrame artf ;
	public AddRederTypeFrame_btnsave_ActionListener(AddRederTypeFrame artf ){
		this.artf = artf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.artf.btnsave_ActionPerformed(e);
	}

}
