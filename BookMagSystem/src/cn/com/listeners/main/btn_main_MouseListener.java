package cn.com.listeners.main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import cn.com.views.main.MainFrame;


public class btn_main_MouseListener extends MouseAdapter {

	private JButton btn;
	private Icon icon1;
	private Icon icon2;
	public btn_main_MouseListener(JButton btn,String enteredIcon,String exitedIcon) {
		this.btn = btn;
		icon1 = new ImageIcon(MainFrame.class.getResource("/image/"+enteredIcon+".png"));
		icon2 = new ImageIcon(MainFrame.class.getResource("/image/"+exitedIcon+".png"));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//btn.setIcon(icon1);
		//btn.setBorder(BorderFactory.createEtchedBorder());
		btn.setForeground(new Color(81, 142, 48));
		btn.setIcon(icon1);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//btn.setIcon(icon2);
		//btn.setBorder(BorderFactory.createLineBorder(new Color(123,191,234), 2));
		btn.setForeground(Color.WHITE);
		btn.setIcon(icon2);
	}
	
}