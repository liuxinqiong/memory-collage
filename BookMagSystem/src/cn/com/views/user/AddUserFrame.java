package cn.com.views.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import cn.com.listeners.users.AddUserFrame_btnCanel_ActionListener;
import cn.com.listeners.users.AddUserFrame_btnConfirm_ActionListener;
import cn.com.listeners.users.AddUserFrame_tfdUserName_KeyListener;

public class AddUserFrame extends JDialog {
	JPanel pnlMain;
	JLabel lblUserName;
	JLabel lblUserNewPwd;
	JLabel lblUserAgainPwd;
	JLabel lblOpt;
	JLabel lblAut;
	JLabel lblState;
	JLabel lblTips;
	JLabel lblTel;
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
	UserInfoDAOInf dao;
	MgrUserFrame muf;
	UserInfoBean user;

	public AddUserFrame(Frame owner, String title, boolean modal,
			MgrUserFrame muf,UserInfoBean user) {
		super(owner, title, modal);
		this.muf = muf;
		pnlMain = new JPanel(null);
		dao = new UserInfoDAOImp();
		lblTel = new JLabel("电话号码：");
		lblUserName = new JLabel("用户姓名：");
		lblUserNewPwd = new JLabel("用户密码：");
		lblUserAgainPwd = new JLabel("确认密码：");
		lblOpt = new JLabel("操作员信息");
		lblAut = new JLabel("权限组：");
		lblState = new JLabel("使用状态：");
		btnCanel = new JButton("取消");
		btnConfirm = new JButton("确定");
		tfdTel = new JTextField();
		pwdNewPwd = new JPasswordField();
		pwdAgainPwd = new JPasswordField();
		cbOpt = new JComboBox();
		cbState = new JComboBox();
		tfdUserName = new JTextField();
		lblTips = new JLabel();
		this.user = user;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setSize(400, 290);
		this.setResizable(false);
		lblState.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblAut.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblOpt.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblUserName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblUserAgainPwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblUserNewPwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnCanel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblTel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblOpt.setBounds(150, 10, 100, 25);
		lblUserName.setBounds(10, 40, 100, 25);
		tfdUserName.setBounds(80, 40, 100, 25);
		lblTips.setBounds(200, 40, 100, 25);
		lblTel.setBounds(10, 80, 100, 25);
		tfdTel.setBounds(80, 80, 100, 25);
		lblUserNewPwd.setBounds(10, 120, 100, 25);
		pwdNewPwd.setBounds(80, 120, 100, 25);
		lblUserAgainPwd.setBounds(190, 120, 100, 25);
		pwdAgainPwd.setBounds(270, 120, 100, 25);
		lblAut.setBounds(10, 160, 100, 25);
		lblState.setBounds(190, 160, 100, 25);
		cbOpt.setBounds(80, 160, 100, 25);
		cbState.setBounds(270, 160, 100, 25);
		btnConfirm.setBounds(190, 210, 70, 25);
		btnCanel.setBounds(280, 210, 70, 25);

		tfdUserName.addKeyListener(new AddUserFrame_tfdUserName_KeyListener(
				this));
		btnConfirm
				.addActionListener(new AddUserFrame_btnConfirm_ActionListener(
						this));
		btnCanel.addActionListener(new AddUserFrame_btnCanel_ActionListener(
				this));

		pnlMain.add(lblTel);
		pnlMain.add(tfdTel);
		pnlMain.add(lblAut);
		pnlMain.add(lblOpt);
		pnlMain.add(lblState);
		pnlMain.add(lblUserAgainPwd);
		pnlMain.add(lblUserName);
		pnlMain.add(lblUserNewPwd);
		pnlMain.add(btnConfirm);
		pnlMain.add(btnCanel);
		pnlMain.add(pwdNewPwd);
		pnlMain.add(pwdAgainPwd);
		pnlMain.add(tfdUserName);
		pnlMain.add(cbOpt);
		pnlMain.add(cbState);
		pnlMain.add(lblTips);

		setcbOptDate();
		setcbStateDate();
		Global.setCenterByWindow(this);
		this.add(pnlMain);
		this.setVisible(true);
	}

	private void setcbStateDate() {
		// TODO Auto-generated method stub
		Vector<String> tit = new Vector<String>();
		String[] st = { "使用", "停用" };
		for (String s : st) {
			tit.add(s);
		}
		dcbmState = new DefaultComboBoxModel(tit);
		this.cbState.setModel(dcbmState);
	}

	private void setcbOptDate() {
		// TODO Auto-generated method stub
		Vector<String> tit = new Vector<String>();
		String[] st = { "超级管理员", "管理员" };
		for (String s : st) {
			tit.add(s);
		}
		dcbmOpt = new DefaultComboBoxModel(tit);
		this.cbOpt.setModel(dcbmOpt);
	}

	public void tfdUserName_keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		String username = tfdUserName.getText() + e.getKeyChar();
		UserInfoBean uib = new UserInfoBean();
		uib.setUserName(username);
		if (dao.checkUserName(uib)) {
			lblTips.setText("该用户名不可用");
			lblTips.setForeground(Color.red);
		} else {
			lblTips.setText("该用户名可用");
			lblTips.setForeground(Color.green);
		}
	}

	public void btnConfirm_Performed() {
		// TODO Auto-generated method stub
		UserInfoBean u = new UserInfoBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!tfdUserName.getText().equals("")) {
			u.setUserName(tfdUserName.getText());
			u.setUserWorkDate(sdf.format(new Date()));
			String newpwd = new String(pwdNewPwd.getPassword()).trim();
			String againpwd = new String(pwdAgainPwd.getPassword()).trim();
			if (!newpwd.equals(againpwd)) {
				JOptionPane.showMessageDialog(null, "两次输入不相同", "错误",
						JOptionPane.ERROR_MESSAGE);
			} else if (!newpwd .equals("")) {
				if (newpwd.length() < 6) {
					JOptionPane.showMessageDialog(null, "密码大于等于六位数", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					u.setUserPwd(newpwd);
					if (!tfdTel.getText().equals("")) {
						u.setUserTel(tfdTel.getText());
						String opt = (String) cbOpt.getSelectedItem();
						if (opt.equals("超级管理员")) {
							u.setUserType(0);
						} else {
							u.setUserType(1);
						}
						String state = (String) cbState.getSelectedItem();
						if (state.equals("使用")) {
							u.setUserState(1);
						} else {
							u.setUserState(0);
						}
						if (dao.addUser(u)) {
							u.setUserIsDel(0);
							JOptionPane.showMessageDialog(null, "增加成功", "成功",
									JOptionPane.INFORMATION_MESSAGE);
							JournaInfoDAO.writeJournalInfo(user.getUserName(), "增加了新用户", JournaInfoDAO.TYPE_RM);
							this.dispose();
							this.muf.setTabViewDate(dao.getAllUserInfo());
						}
					} else {
						JOptionPane.showMessageDialog(null, "电话号码不能为空", "错误",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			} else {
				JOptionPane.showMessageDialog(null, "用户名不能为空", "错误",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void btnCanel_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
