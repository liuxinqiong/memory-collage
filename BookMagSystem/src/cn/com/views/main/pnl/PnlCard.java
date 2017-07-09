package cn.com.views.main.pnl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.global.Global;
import cn.com.listeners.main.PnlCard_btnAdd_ActionListener;
import cn.com.views.petcard.ActPetCardFrame;
import cn.com.views.petcard.LogOffPetCardFrame;
import cn.com.views.petcard.ManagePetCardFrame;
import cn.com.views.petcard.RechargePetCardFrame;



public class PnlCard extends JDesktopPane{
	
	private JButton btnAdd;
	private JButton btnChongz;
	private JButton btnLogout;
	private JButton btnManage;
	
	private JLabel lblA;
	private JLabel lblC;
	private JLabel lblL;
	private JLabel lblM;
	
	private UserInfoBean user;
	public PnlCard(UserInfoBean user){
		this.user=user;
		initObject();
		init();
	}
	
	private void initObject() {
		// TODO Auto-generated method stub
		btnAdd = new JButton("储值卡添加");
		btnChongz = new JButton("储值卡充值");
		btnLogout = new JButton("储值卡注销");
		btnManage = new JButton("储值卡管理");
		
		lblA = new JLabel("为读者分配储值卡");
		lblC = new JLabel("修改读者储值卡余额信息");
		lblL = new JLabel("注销读者储值卡(退卡)");
		lblM = new JLabel("查看读者储值卡信息");
		
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		btnAdd.setBounds(100, 75, 130, 28);
		btnChongz.setBounds(100, 135, 130, 28);
		btnLogout.setBounds(100, 195, 130, 28);
		btnManage.setBounds(100, 255, 130, 28);
		
		lblA.setBounds(350, 79, 400, 20);
		lblC.setBounds(350, 139, 400, 20);
		lblL.setBounds(350, 199, 400, 20);
		lblM.setBounds(350, 259, 400, 20);
		
		this.add(btnAdd);
		this.add(btnChongz);
		this.add(btnLogout);
		this.add(btnManage);
		
		this.add(lblA);
		this.add(lblC);
		this.add(lblL);
		this.add(lblM);
		
		Global.jlableInit(lblA, Color.WHITE, 18);
		Global.jlableInit(lblC, Color.WHITE, 18);
		Global.jlableInit(lblL, Color.WHITE, 18);
		Global.jlableInit(lblM, Color.WHITE, 18);
		
		btnAdd.setFont(new Font("微软雅黑", Font.PLAIN,16));
		btnChongz.setFont(new Font("微软雅黑", Font.PLAIN,16));
		btnLogout.setFont(new Font("微软雅黑", Font.PLAIN,16));
		btnManage.setFont(new Font("微软雅黑", Font.PLAIN,16));
		
		btnAdd.addActionListener(new PnlCard_btnAdd_ActionListener(this));
		btnChongz.addActionListener(new PnlCard_btnAdd_ActionListener(this));
		btnLogout.addActionListener(new PnlCard_btnAdd_ActionListener(this));
		btnManage.addActionListener(new PnlCard_btnAdd_ActionListener(this));
		
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
	}

	public void btnAdd_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnAdd)){
			new ActPetCardFrame(user);
		}else if(e.getSource().equals(btnChongz)){
			new RechargePetCardFrame(user);
		}else if(e.getSource().equals(btnLogout)){
			new LogOffPetCardFrame(user);
		}else if(e.getSource().equals(btnManage)){
			new ManagePetCardFrame(user);
		}
	}
}
