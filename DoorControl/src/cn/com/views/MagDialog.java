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
	
		lblName=new JLabel("����Ա����");
		txtName=new JTextField();
		lblPwd=new JLabel("����");
		pwdPwd=new JPasswordField();
		lblPwdAgain=new JLabel("ȷ������");
		pwdPwdAgain=new JPasswordField();
		
		lblType=new JLabel("����Ա����");
		cmbView=new JComboBox<String>();
		dcmView=new DefaultComboBoxModel<String>();

		if(mag==null){
			btnSave=new JButton("����");
		}else{
			btnSave=new JButton("�޸�");
			txtName.setText(mag.getMagName());
			pwdPwd.setText(mag.getMagPassword());
			pwdPwdAgain.setText(mag.getMagPassword());
			if(mag.getMagType()==1){
				dcmView.setSelectedItem("��������Ա");
			}else{
				dcmView.setSelectedItem("��ͨ����Ա");
			}		
		}
		btnCancel=new JButton("ȡ��");
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
		dcmView.addElement("��������Ա");
		dcmView.addElement("��ͨ����Ա");
		cmbView.setModel(dcmView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type=e.getActionCommand();
		if(type.equals("ȡ��")){
			this.dispose();
			return;
		}
		String magName=txtName.getText().trim();	
		String pwd=new String(pwdPwd.getPassword()).trim();
		String pwdAgain=new String(pwdPwdAgain.getPassword()).trim();
		if(magName.length()==0||pwd.length()==0||pwdAgain.length()==0){
			JOptionPane.showMessageDialog(null, "��Ϣ��д�����", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!pwd.equals(pwdAgain)){
			JOptionPane.showMessageDialog(null, "�����������벻һ��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String magType=(String) dcmView.getSelectedItem();
		Mag m=null;
		int typeInt;
		if(magType.equals("��������Ա")){
			typeInt=1;
		}else{
			typeInt=0;
		}	
		m=new Mag(-1, magName, pwd, typeInt);
		if(type.equals("����")){
			boolean bool=magDao.addMag(m);
			showResult(bool, type);	
			this.mmd.initTable();
		}else if(type.equals("�޸�")){
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
			JOptionPane.showMessageDialog(null, operator+"�ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, operator+"ʧ��", "����", JOptionPane.ERROR_MESSAGE);
		}
	}
}
