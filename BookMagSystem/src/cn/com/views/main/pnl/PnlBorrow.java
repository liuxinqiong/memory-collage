package cn.com.views.main.pnl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.ModerateSkin;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.global.Global;
//import cn.com.view.borrow.BorrowFrame;
//import cn.com.view.borrow.ChooseFrame;
import cn.com.listeners.main.pnlBorrow_btnYuJie_ActionListener;
import cn.com.listeners.main.pnlBorrow_btnYuQi_ActionListener;
import cn.com.views.advanceBorrInfo.AdvanceBorrFrame;
import cn.com.views.borrow.ChooseFrame;
import cn.com.views.extendedMag.ExtendedMagFrame;

public class PnlBorrow extends JDesktopPane{
	
	private JButton btnLiuT;
	private JButton btnYuJie;
	private JButton btnYuQi;
	
	private JLabel lblLT;
	private JLabel lblYJ;
	private JLabel lblYQ;
		
	private UserInfoBean user;
	public PnlBorrow(UserInfoBean user){
		this.user=user;
		initObject();
		init();
	}
	
	private void initObject() {
		// TODO Auto-generated method stub
		btnLiuT = new JButton("ͼ����ͨ����");
		btnYuJie = new JButton("Ԥ����Ϣ����");
		btnYuQi = new JButton("���ڽ��Ĺ���");
		
		lblLT = new JLabel("���н��顢���顢���衢��ʧ�Ȳ���");
		lblYJ = new JLabel("��Ԥ����Ϣ��������ɾ���ȹ���");
		lblYQ = new JLabel("�Ե���û�й黹��ͼ����в�ѯ�͹���");

	}

	private void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		btnLiuT.setBounds(100, 75, 130, 28);
		btnYuJie.setBounds(100, 135, 130, 28);
		btnYuQi.setBounds(100, 195, 130, 28);

		
		lblLT.setBounds(350, 79, 400, 20);
		lblYJ.setBounds(350, 139, 400, 20);
		lblYQ.setBounds(350, 199, 400, 20);

		
		this.add(btnLiuT);
		this.add(btnYuJie);
		this.add(btnYuQi);
		
		this.add(lblLT);
		this.add(lblYJ);
		this.add(lblYQ);
		
		Global.jlableInit(lblLT, Color.WHITE, 18);
		Global.jlableInit(lblYJ, Color.WHITE, 18);
		Global.jlableInit(lblYQ, Color.WHITE, 18);
		
		btnLiuT.setFont(new Font("΢���ź�", Font.PLAIN,16));
		btnYuJie.setFont(new Font("΢���ź�", Font.PLAIN,16));
		btnYuQi.setFont(new Font("΢���ź�", Font.PLAIN,16));
		
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		btnYuJie.addActionListener(new pnlBorrow_btnYuJie_ActionListener(this));
		btnYuQi.addActionListener(new pnlBorrow_btnYuQi_ActionListener(this));
		
		btnLiuT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChooseFrame cf = new ChooseFrame(user);
				cf.setVisible(true);
			}
		});
	}

	public void btnYuJie_actionPerformed() {
		// TODO Auto-generated method stub
		new AdvanceBorrFrame();
	}

	public void btnYuQi_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ExtendedMagFrame();
	}
}
