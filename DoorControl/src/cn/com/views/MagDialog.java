package cn.com.views;

import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.com.dao.MagDao;
import cn.com.entity.Mag;
import cn.com.global.Global;

public class MagDialog extends JDialog implements ActionListener{
	JPanel pnlMain;
	Mag loginMag;
	JLabel lblName;
	JTextField txtName;
	JLabel lblPwd;
	JPasswordField pwdPwd;
	JLabel lblPwdAgain;
	JPasswordField pwdPwdAgain;
	
	JLabel lblType;
	JComboBox<String> cmbView;
	DefaultComboBoxModel<String> dcmView;
	JButton btnSave;
	JButton btnCancel;
	Mag mag;
	MagMagDialog mmd;
	
	MagDao magDao;
	
	public MagDialog(MagMagDialog mmd, String title, boolean modal,Mag loginMag,Mag mag) {
		super(mmd, title, modal);
		this.loginMag=loginMag;
		this.mag=mag;
		this.mmd=mmd;
		magDao=new MagDao();
	
		lblName=new JLabel("管理员名称");
		txtName=new JTextField();
		lblPwd=new JLabel("密码");
		pwdPwd=new JPasswordField();
		lblPwdAgain=new JLabel("确认密码");
		pwdPwdAgain=new JPasswordField();
		
		lblType=new JLabel("管理员类型");
		cmbView=new JComboBox<String>();
		dcmView=new DefaultComboBoxModel<String>();

		if(mag==null){
			btnSave=new JButton("新增");
		}else{
			btnSave=new JButton("修改");
			txtName.setText(mag.getMagName());
			pwdPwd.setText(mag.getMagPassword());
			pwdPwdAgain.setText(mag.getMagPassword());
			if(mag.getMagType()==1){
				dcmView.setSelectedItem("超级管理员");
			}else{
				dcmView.setSelectedItem("普通管理员");
			}		
		}
		btnCancel=new JButton("取消");
		pnlMain=new JPanel(new GridLayout(5, 2, 10, 20));
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		Global.setCenterByWindow(this);
		pnlMain.add(lblName);
		pnlMain.add(txtName);
		pnlMain.add(lblPwd);
		pnlMain.add(pwdPwd);
		pnlMain.add(lblPwdAgain);
		pnlMain.add(pwdPwdAgain);
		if(loginMag.getMagType()==1){
			pnlMain.add(lblType);
			pnlMain.add(cmbView);
		}	
		pnlMain.add(btnSave);
		pnlMain.add(btnCancel);	
		initCmbViewData();
		this.add(pnlMain);
		
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		
		this.setVisible(true);
	}

	private void initCmbViewData() {
		// TODO Auto-generated method stub
		dcmView.addElement("超级管理员");
		dcmView.addElement("普通管理员");
		cmbView.setModel(dcmView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type=e.getActionCommand();
		if(type.equals("取消")){
			this.dispose();
			return;
		}
		String magName=txtName.getText().trim();	
		String pwd=new String(pwdPwd.getPassword()).trim();
		String pwdAgain=new String(pwdPwdAgain.getPassword()).trim();
		if(magName.length()==0||pwd.length()==0||pwdAgain.length()==0){
			JOptionPane.showMessageDialog(null, "信息填写不完成", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!pwd.equals(pwdAgain)){
			JOptionPane.showMessageDialog(null, "两次密码输入不一致", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String magType=(String) dcmView.getSelectedItem();
		Mag m=null;
		int typeInt;
		if(magType.equals("超级管理员")){
			typeInt=1;
		}else{
			typeInt=0;
		}	
		m=new Mag(-1, magName, pwd, typeInt);
		if(type.equals("新增")){
			boolean bool=magDao.addMag(m);
			showResult(bool, type);	
			this.mmd.initTable();
		}else if(type.equals("修改")){
			mag.setMagName(magName);
			mag.setMagPassword(pwd);
			if(loginMag.getMagType()==1){
				mag.setMagType(typeInt);
			}
			boolean bool=magDao.updateMag(mag);
			showResult(bool, type);	
			this.mmd.initTable();
		}
	}
	private void showResult(boolean bool,String operator){
		if(bool){
			JOptionPane.showMessageDialog(null, operator+"成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, operator+"失败", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}
}
