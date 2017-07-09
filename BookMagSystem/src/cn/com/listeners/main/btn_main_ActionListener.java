package cn.com.listeners.main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class btn_main_ActionListener implements ActionListener{
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	
	private JPanel pnl;
	private CardLayout cl;
	private String pnlName;
	
	public btn_main_ActionListener(JButton btn1,JButton btn2,JButton btn3,JButton btn4,JButton btn5,JButton btn6,JPanel pnl,CardLayout cl,String pnlName){
		this.btn1 = btn1;
		this.btn2 = btn2;
		this.btn3 = btn3;
		this.btn4 = btn4;
		this.btn5 = btn5;
		this.btn6 = btn6;
		
		this.pnl = pnl;
		this.cl = cl;
		this.pnlName = pnlName;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		btn1.setBackground(Color.WHITE);
		btn2.setBackground(new Color(123,191,234));
		btn3.setBackground(new Color(123,191,234));
		btn4.setBackground(new Color(123,191,234));
		btn5.setBackground(new Color(123,191,234));
		btn6.setBackground(new Color(123,191,234));
		
		btn1.setForeground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		btn3.setForeground(Color.WHITE);
		btn4.setForeground(Color.WHITE);
		btn5.setForeground(Color.WHITE);
		btn6.setForeground(Color.WHITE);
		
		
		cl.show(pnl, pnlName);
	}
}
