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

import cn.com.bookInfo.Mview;
import cn.com.bookTypeInfo.Typeview;
import cn.com.global.Global;

public class PnlBook extends JDesktopPane{
	
	private JButton btnEdit;
//	private JButton btnPress;
	private JButton btnType;
	
	private JLabel lblE;
//	private JLabel lblP;
	private JLabel lblT;

		
	public PnlBook(){
		initObject();
		init();
	}
	
	private void initObject() {
		// TODO Auto-generated method stub
		btnEdit = new JButton("书刊信息维护");
	//	btnPress = new JButton("出版单位设置");
		btnType = new JButton("书刊类别设置");
		
		lblE = new JLabel("添加及管理所有的图书、期刊、杂志等信息");
	//	lblP = new JLabel("维护出版社等出版单位信息");
		lblT = new JLabel("设置书刊类别");

		
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		btnEdit.setBounds(100, 75, 130, 28);
	//	btnPress.setBounds(100, 135, 130,28);
		btnType.setBounds(100, 135, 130,28);

		
		lblE.setBounds(350, 79, 400, 20);
	//	lblP.setBounds(350, 139, 400, 20);
		lblT.setBounds(350, 139, 400, 20);

		
		this.add(btnEdit);
	//	this.add(btnPress);
		this.add(btnType);
		
		this.add(lblE);
	//	this.add(lblP);
		this.add(lblT);
		
		Global.jlableInit(lblE, Color.WHITE, 18);
	//	Global.jlableInit(lblP, Color.WHITE, 18);
		Global.jlableInit(lblT, Color.WHITE, 18);
		
		btnEdit.setFont(new Font("微软雅黑", Font.PLAIN,16));
	//	btnPress.setFont(new Font("微软雅黑", Font.PLAIN,16));
		btnType.setFont(new Font("微软雅黑", Font.PLAIN,16));
		
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Mview();
			}
		});
		btnType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Typeview();
			}
		});
	}
}
