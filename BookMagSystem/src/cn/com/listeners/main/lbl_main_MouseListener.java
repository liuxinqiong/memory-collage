package cn.com.listeners.main;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class lbl_main_MouseListener extends MouseAdapter {

	private JLabel lbl;
	private JFrame frame;

	public lbl_main_MouseListener(JLabel lbl,JFrame frame) {
		this.lbl = lbl;
		this.frame = frame;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.lbl.setOpaque(true);
		this.lbl.setBackground(new Color(176, 206, 236));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.lbl.setBackground(new Color(240, 240, 240));
		this.lbl.setOpaque(false);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(lbl.getText().equals("-")){
			frame.setExtendedState(Frame.ICONIFIED);
		}else{
			frame.dispose();	
		}

	}
	
	
}