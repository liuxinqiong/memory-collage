package cn.com.listeners.users;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cn.com.views.user.AddUserFrame;

public class AddUserFrame_tfdUserName_KeyListener implements KeyListener {
	private AddUserFrame auf;
	public AddUserFrame_tfdUserName_KeyListener(AddUserFrame auf){
		this.auf=auf;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		this.auf.tfdUserName_keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
