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
		btnEdit = new JButton("�鿯��Ϣά��");
	//	btnPress = new JButton("���浥λ����");
		btnType = new JButton("�鿯�������");
		
		lblE = new JLabel("��Ӽ��������е�ͼ�顢�ڿ�����־����Ϣ");
	//	lblP = new JLabel("ά��������ȳ��浥λ��Ϣ");
		lblT = new JLabel("�����鿯���");

		
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
		
		btnEdit.setFont(new Font("΢���ź�", Font.PLAIN,16));
	//	btnPress.setFont(new Font("΢���ź�", Font.PLAIN,16));
		btnType.setFont(new Font("΢���ź�", Font.PLAIN,16));
		
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
