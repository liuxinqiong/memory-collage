package cn.com.views.user;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.userInfo.UserInfoDAOImp;
import cn.com.global.Global;
import cn.com.listeners.users.ChangePwdFrame_btnCanel_ActionListener;
import cn.com.listeners.users.ChangePwdFrame_btnConfirm_ActionListener;

public class ChangePwdFrame extends JFrame{
	JPanel pnlMain;
	JButton btnConfirm;
	JButton btnCanel;
	JLabel lblNewPwd;
	JLabel lblAgainPwd;
	JPasswordField pwdNewPwd;
	JPasswordField pwdAgainPwd;
	UserInfoBean user;
	public ChangePwdFrame(UserInfoBean user){
		this.user=user;
		pnlMain = new JPanel(null);
		btnCanel = new JButton("取消");
		btnConfirm = new JButton("确定");
		pwdAgainPwd = new JPasswordField();
		pwdNewPwd = new JPasswordField();
		lblAgainPwd = new JLabel("请再输一次");
		lblNewPwd = new JLabel("请输入新密码");
		init();
	}
	
	public void init(){
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300,200);
		this.setResizable(false);
		btnCanel.addActionListener(new ChangePwdFrame_btnCanel_ActionListener(this));
		btnConfirm.addActionListener(new ChangePwdFrame_btnConfirm_ActionListener(this));
		lblNewPwd.setFont(new Font("微软雅黑",Font.PLAIN,14));
		lblAgainPwd.setFont(new Font("微软雅黑",Font.PLAIN,14));
		btnCanel.setFont(new Font("微软雅黑",Font.PLAIN,14));
		btnConfirm.setFont(new Font("微软雅黑",Font.PLAIN,14));
		lblNewPwd.setBounds(40, 40, 100, 25);
		lblAgainPwd.setBounds(40, 70, 100, 25);
		btnConfirm.setBounds(70, 120, 75, 25);
		btnCanel.setBounds(160, 120, 75, 25);
		pwdNewPwd.setBounds(140, 40, 120, 25);
		pwdAgainPwd.setBounds(140, 70, 120, 25);	
		pnlMain.add(lblNewPwd);
		pnlMain.add(lblAgainPwd);
		pnlMain.add(btnConfirm);
		pnlMain.add(btnCanel);
		pnlMain.add(pwdNewPwd);
		pnlMain.add(pwdAgainPwd);
		Global.setCenterByWindow(this);
		this.setTitle("修改密码");
		this.add(pnlMain);
		this.setVisible(true);	
	}

	public void btnCanel_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	public void btnConfirm_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String newpwd = new String(pwdNewPwd.getPassword()).trim();
		String againpwd = new String(pwdAgainPwd.getPassword()).trim();
		if(!newpwd.equals(againpwd)){
			JOptionPane.showMessageDialog(null, "两次输入不相同","错误",JOptionPane.ERROR_MESSAGE);
		}else if(newpwd != null){
			if(newpwd.length()<6){
				JOptionPane.showMessageDialog(null, "密码大于等于六位数","错误",JOptionPane.ERROR_MESSAGE);
			}else{
				UserInfoBean uib = new UserInfoBean();
				uib.setUserId(user.getUserId());
				uib.setUserPwd(newpwd);
				UserInfoDAOImp uidi = new UserInfoDAOImp();
				uidi.changePwdUser(uib);
				JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
				JournaInfoDAO.writeJournalInfo(user.getUserName(), "修改自己密码", JournaInfoDAO.TYPE_UM);
				this.dispose();
			}
			
		}
	}

	
}
