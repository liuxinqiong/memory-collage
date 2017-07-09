package cn.com.views.borrow;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.global.Global;

public class ChooseFrame extends JFrame{
	private JButton btnJieShu;
	private JButton btnXuJie;
	private JButton btnGuiHuan;
	private JButton btnDiuShi;
	private UserInfoBean user;
	public ChooseFrame(UserInfoBean user) {
		this.user=user;
		btnJieShu = new JButton("½èÊé");
		btnXuJie= new JButton("Ðø½è");
		btnGuiHuan = new JButton("¹é»¹");
		btnDiuShi = new JButton("¶ªÊ§");
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		add(btnJieShu);
		add(btnGuiHuan);
		add(btnXuJie);
		add(btnDiuShi);
		
		btnJieShu.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN,20));
		btnGuiHuan.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN,20));
		btnXuJie.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN,20));
		btnDiuShi.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN,20));
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(515, 111);
		Global.setCenterByWindow(this);
		btnJieShu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BorrowFrame bf = new BorrowFrame("JieShu",user);
				bf.setVisible(true);
			}
		});
		
		btnDiuShi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BorrowFrame bf = new BorrowFrame("DiuShi",user);
				bf.setVisible(true);
			}
		});
		
		btnGuiHuan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BorrowFrame bf = new BorrowFrame("GuiHuan",user);
				bf.setVisible(true);
			}
		});
		
		btnXuJie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BorrowFrame bf = new BorrowFrame("XuJie",user);
				bf.setVisible(true);
			}
		});
	}
}
