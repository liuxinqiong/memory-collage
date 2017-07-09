package cn.com.views.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.userInfo.UserInfoDAOImp;
import cn.com.daos.userInfo.UserInfoDAOInf;
import cn.com.global.Global;
import cn.com.listeners.users.AlterUserFrame_btnCanel_ActionListener;
import cn.com.listeners.users.AlterUserFrame_btnConfirm_ActionListener;

public class AlterUserFrame extends JDialog{
	JPanel pnlMain;
	JLabel lblTel;
	JLabel lblUserName;
	JLabel lblUserNewPwd;
	JLabel lblUserAgainPwd;
	JLabel lblOpt;
	JLabel lblAut;
	JLabel lblState;
	JTextField tfdTel;
	JTextField tfdUserName;
	JPasswordField pwdNewPwd;
	JPasswordField pwdAgainPwd;
	JComboBox cbOpt;
	JComboBox cbState;
	JButton btnConfirm;
	JButton btnCanel;
	DefaultComboBoxModel dcbmOpt;
	DefaultComboBoxModel dcbmState;
	MgrUserFrame muf;
	UserInfoDAOInf dao;
	UserInfoBean u;
	
	public AlterUserFrame(Frame owner, String title, boolean modal,MgrUserFrame muf,UserInfoBean u){
		super(owner,title,modal);
		pnlMain = new JPanel(null);
		lblTel = new JLabel("电话号码：");
		lblUserName = new JLabel("用户姓名：");
		lblUserNewPwd = new JLabel("新密码：");
		lblUserAgainPwd = new JLabel("确认密码：");
		lblOpt = new JLabel("操作员信息");
		lblAut = new JLabel("权限组：");
		lblState = new JLabel("使用状态：");
		btnCanel = new JButton("取消");
		btnConfirm = new JButton("确定");
		pwdNewPwd = new JPasswordField();
		pwdAgainPwd = new JPasswordField();
		cbOpt = new JComboBox();
		cbState = new JComboBox();
		tfdTel = new JTextField();
		tfdUserName = new JTextField();
		dao = new UserInfoDAOImp();
		this.u =u;
		this.muf=muf;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
				this.setSize(400,250);
				this.setResizable(false);
				lblState.setFont(new Font("微软雅黑",Font.PLAIN,14));
				lblAut.setFont(new Font("微软雅黑",Font.PLAIN,14));
				lblOpt.setFont(new Font("微软雅黑",Font.PLAIN,14));
				lblTel.setFont(new Font("微软雅黑",Font.PLAIN,14));
				lblUserName.setFont(new Font("微软雅黑",Font.PLAIN,14));
				lblUserAgainPwd.setFont(new Font("微软雅黑",Font.PLAIN,14));
				lblUserNewPwd.setFont(new Font("微软雅黑",Font.PLAIN,14));
				btnCanel.setFont(new Font("微软雅黑",Font.PLAIN,14));
				btnConfirm.setFont(new Font("微软雅黑",Font.PLAIN,14));
				lblOpt.setBounds(150,10,100,25);
				tfdUserName.setBounds(80, 40, 100, 25);
				lblUserName.setBounds(10, 40, 100, 25);
				lblTel.setBounds(190, 40, 100, 25);
				tfdTel.setBounds(270, 40, 100, 25);
				lblUserNewPwd.setBounds(10, 80, 100, 25);
				pwdNewPwd.setBounds(80, 80, 100, 25);
				lblUserAgainPwd.setBounds(190,80, 100, 25);
				pwdAgainPwd.setBounds(270,80, 100, 25);
				lblAut.setBounds(10, 120, 100, 25);
				lblState.setBounds(190, 120, 100, 25);
				cbOpt.setBounds(80, 120, 100, 25);
				cbState.setBounds(270, 120, 100, 25);
				btnConfirm.setBounds(190, 170, 70, 25);
				btnCanel.setBounds(280, 170, 70, 25);
				
				btnConfirm.addActionListener(new AlterUserFrame_btnConfirm_ActionListener(this));
				btnCanel.addActionListener(new AlterUserFrame_btnCanel_ActionListener(this));
				
				UserInfoBean u = new UserInfoBean();
				u = (UserInfoBean)muf.tbUser.getValueAt(muf.tbUser.getSelectedRow(),4);
				tfdUserName.setText(u.getUserName());
				tfdTel.setText(u.getUserTel());
				
				pnlMain.add(lblAut);
				pnlMain.add(lblOpt);
				pnlMain.add(lblState);
				pnlMain.add(lblUserAgainPwd);
				pnlMain.add(lblTel);
				pnlMain.add(lblUserName);
				pnlMain.add(lblUserNewPwd);
				pnlMain.add(btnConfirm);
				pnlMain.add(btnCanel);
				pnlMain.add(pwdNewPwd);
				pnlMain.add(pwdAgainPwd);
				pnlMain.add(tfdTel);
				pnlMain.add(tfdUserName);
				pnlMain.add(cbOpt);
				pnlMain.add(cbState);
				
				setcbOptDate();
				setcbStateDate();
				Global.setCenterByWindow(this);
				this.add(pnlMain);
				this.setVisible(true);
	}

	private void setcbStateDate() {
		// TODO Auto-generated method stub
		Vector<String> tit = new Vector<String>();
		String[] st = {"使用","停用"};
		for(String s : st){
			tit.add(s);
		}
		dcbmState = new DefaultComboBoxModel(tit);
		this.cbState.setModel(dcbmState);
	}

	private void setcbOptDate() {
		// TODO Auto-generated method stub
		Vector<String> tit = new Vector<String>();
		String[] st = {"超级管理员", "普通用户"};
		for(String s : st){
			tit.add(s);
		}
		dcbmOpt = new DefaultComboBoxModel(tit);
		this.cbOpt.setModel(dcbmOpt);
	}

	public void btnConfirm_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		int i=JOptionPane.showConfirmDialog(null, "确认修改？","提示",JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION){
			UserInfoBean uib = dao.getUserInfoByUserId(u);
			if(!tfdUserName.getText().equals("")){
				
				if(!tfdUserName.getText().equals(uib.getUserName())){
					uib.setUserName(tfdUserName.getText().trim());
					if(dao.checkUserName(uib)){
						JOptionPane.showMessageDialog(null, "用户名已存在","错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}	
			String newpwd = new String(pwdNewPwd.getPassword()).trim();
			String againpwd = new String(pwdAgainPwd.getPassword()).trim();
			if(!newpwd.equals(againpwd)){
				JOptionPane.showMessageDialog(null, "两次输入不相同","错误",JOptionPane.ERROR_MESSAGE);
				return;
			} 
			if(!newpwd.equals("")){
				if(newpwd.length()<6){
					JOptionPane.showMessageDialog(null, "密码长度大于等于6","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					uib.setUserPwd(newpwd);
				}
			}
			if(!tfdTel.getText().equals("")){
			uib.setUserTel(tfdTel.getText());
			}
			String opt = (String) cbOpt.getSelectedItem();
			if(opt.equals("超级管理员")){
				uib.setUserType(0);
			}else{
				uib.setUserType(1);
			}
			String state = (String) cbState.getSelectedItem();
			if(state.equals("使用")){
				uib.setUserState(1);
			}else{
				uib.setUserState(0);
			}
			if(dao.updateUser(uib)){
				JOptionPane.showMessageDialog(null,"修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
				JournaInfoDAO.writeJournalInfo(u.getUserName(), "为"+uib.getUserName()+"修改信息", JournaInfoDAO.TYPE_RM);
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null,"修改失败","错误",JOptionPane.ERROR_MESSAGE);
			}
		
		
			this.muf.setTabViewDate(dao.getAllUserInfo());
		}
}

	public void btnCanel_Performed() {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
