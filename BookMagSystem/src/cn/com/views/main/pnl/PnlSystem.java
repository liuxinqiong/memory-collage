package cn.com.views.main.pnl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.global.Global;
import cn.com.listeners.main.PnlSystem_btnDiary_ActionListener;
import cn.com.listeners.main.PnlSystem_btnEditPwd_ActionListener;
import cn.com.listeners.main.PnlSystem_btnManage_ActionListener;
import cn.com.views.journal.JournalMagFrame;
import cn.com.views.user.ChangePwdFrame;
import cn.com.views.user.MgrUserFrame;



public class PnlSystem extends JDesktopPane{
	
	private JButton btnEditPwd;
	private JButton btnManage;
	private JButton btnDiary;
	
	private JLabel lblEP;
	private JLabel lblM;
	private JLabel lblbD;
		
	private UserInfoBean u;
	public PnlSystem(UserInfoBean u){
		this.u=u;
		initObject();
		init();
	}
	
	private void initObject() {
		// TODO Auto-generated method stub
		btnEditPwd = new JButton("修改密码");
		btnManage = new JButton("操作员管理");
		btnDiary = new JButton("操作日志管理");
		
		lblEP = new JLabel("修改当前操作员密码");
		lblM = new JLabel("对操作员的信息进行管理");
		lblbD = new JLabel("操作日志管理");
		
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		btnEditPwd.setBounds(100, 75, 130, 28);
		btnManage.setBounds(100, 135, 130, 28);
		btnDiary.setBounds(100, 195, 130, 28);
		
		lblEP.setBounds(350, 79, 400, 20);
		lblM.setBounds(350, 139, 400, 20);
		lblbD.setBounds(350, 199, 400, 20);
		
		if(u.getUserType()==1){
			btnManage.setVisible(false);
			btnDiary.setVisible(false);
			lblM.setVisible(false);
			lblbD.setVisible(false);
		}
		
		this.add(btnEditPwd);
		this.add(btnManage);
		this.add(btnDiary);
		
		this.add(lblEP);
		this.add(lblM);
		this.add(lblbD);
		
		Global.jlableInit(lblEP, Color.WHITE, 18);
		Global.jlableInit(lblM, Color.WHITE, 18);
		Global.jlableInit(lblbD, Color.WHITE, 18);
		
		btnEditPwd.setFont(new Font("微软雅黑", Font.PLAIN,16));
		btnManage.setFont(new Font("微软雅黑", Font.PLAIN,16));
		btnDiary.setFont(new Font("微软雅黑", Font.PLAIN,16));
		
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		btnDiary.addActionListener(new PnlSystem_btnDiary_ActionListener(this));
		btnManage.addActionListener(new PnlSystem_btnManage_ActionListener(this));
		btnEditPwd.addActionListener(new PnlSystem_btnEditPwd_ActionListener(this));
		
	}

	public void btnDiary_actionPerformed() {
		// TODO Auto-generated method stub
		new JournalMagFrame(u);
	}

	public void btnManage_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new MgrUserFrame(u);
	}

	public void btnEditPwd_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ChangePwdFrame(u);
	}
}
