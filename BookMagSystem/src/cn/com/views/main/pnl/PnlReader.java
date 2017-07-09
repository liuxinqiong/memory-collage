package cn.com.views.main.pnl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.global.Global;
import cn.com.views.keepMoney.KeepMoneyMagFrame;
import cn.com.views.reader.ReaderInfoMagFrame;
import cn.com.views.readerType.SetReaderTypeFrame;



public class PnlReader extends JDesktopPane{
	
	private JButton btnEdit;
	private JButton btnMoney;
	private JButton btnType;
	
	private JLabel lblE;
	private JLabel lblM;
	private JLabel lblT;
		
	private UserInfoBean user;
	public PnlReader( UserInfoBean user){
		this.user=user;
		initObject();
		init();
	}
	
	private void initObject() {
		// TODO Auto-generated method stub
		btnEdit = new JButton("读者信息维护");
		btnMoney = new JButton("读者押金管理");
		btnType = new JButton("读者类型设置");
		
		lblE = new JLabel("管理所有的读者信息");
		lblM = new JLabel("管理读者的押金信息，进行收取、没收、退还押金等操作");
		lblT = new JLabel("设置读者类型，方便对读者信息的管理");

	}

	private void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		btnEdit.setBounds(100, 75, 130, 28);
		btnMoney.setBounds(100, 135, 130, 28);
		btnType.setBounds(100, 195, 130, 28);

		
		lblE.setBounds(350, 79, 400, 20);
		lblM.setBounds(350, 139, 500, 20);
		lblT.setBounds(350, 199, 400, 20);

		
		this.add(btnEdit);
		this.add(btnMoney);
		this.add(btnType);
		
		this.add(lblE);
		this.add(lblM);
		this.add(lblT);
		
		Global.jlableInit(lblE, Color.WHITE, 18);
		Global.jlableInit(lblM, Color.WHITE, 18);
		Global.jlableInit(lblT, Color.WHITE, 18);
		
		btnEdit.setFont(new Font("微软雅黑", Font.PLAIN,16));
		btnMoney.setFont(new Font("微软雅黑", Font.PLAIN,16));
		btnType.setFont(new Font("微软雅黑", Font.PLAIN,16));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		btnType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SetReaderTypeFrame(user);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ReaderInfoMagFrame(user);
			}
		});
		
		btnMoney.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new KeepMoneyMagFrame(user);
			}
		});
	}
}
