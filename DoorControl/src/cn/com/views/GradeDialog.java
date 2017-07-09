package cn.com.views;

import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.com.dao.GradeDao;
import cn.com.entity.Grade;
import cn.com.global.Check;
import cn.com.global.Global;

public class GradeDialog extends JDialog implements ActionListener{
	JPanel pnlMain;
	JLabel lblName;
	JTextField txtName;
	JLabel lblCount;
	JTextField txtCount;
	JButton btnSave;
	JButton btnCancel;
	
	Grade g;
	GradeMagDialog gmd;
	GradeDao gradeDao;
	public GradeDialog(GradeMagDialog gmd, String title, boolean modal,Grade g) {
		super(gmd, title, modal);
		this.g=g;
		this.gmd=gmd;
		gradeDao=new GradeDao();
		lblName=new JLabel("��������");
		txtName=new JTextField();
		lblCount=new JLabel("���Ŵ���");
		txtCount=new JTextField();
		if(g==null){
			btnSave=new JButton("����");
		}else{
			btnSave=new JButton("�޸�");
			txtName.setText(g.getGradeName());
			txtCount.setText(g.getGradeCount()+"");	
		}
		btnCancel=new JButton("ȡ��");
		pnlMain=new JPanel(new GridLayout(3, 2, 10, 20));
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		Global.setCenterByWindow(this);
		pnlMain.add(lblName);
		pnlMain.add(txtName);
		pnlMain.add(lblCount);
		pnlMain.add(txtCount);
		pnlMain.add(btnSave);
		pnlMain.add(btnCancel);
		this.add(pnlMain);
		
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type=e.getActionCommand();
		if(type.equals("ȡ��")){
			this.dispose();
			return;
		}
		String gradeName=txtName.getText().trim();	
		String count=txtCount.getText().trim();
		if(gradeName.length()==0||count.length()==0){
			JOptionPane.showMessageDialog(null, "��Ϣ��д�����", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!Check.isInt(count)){
			JOptionPane.showMessageDialog(null, "����Ӧ��Ϊ����", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!Check.checkMaxNum(Integer.parseInt(count))){
			JOptionPane.showMessageDialog(null, "����ֻ��Ϊ0-999", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(type.equals("����")){
			Grade grade=new Grade(-1, gradeName, Integer.parseInt(count));
			boolean bool=gradeDao.addGrade(grade);
			showResult(bool, type);	
			this.gmd.initTable();
		}else if(type.equals("�޸�")){
			g.setGradeName(gradeName);
			g.setGradeCount(Integer.parseInt(count));
			boolean bool=gradeDao.updateGrade(g);
			showResult(bool, type);	
			this.gmd.initTable();
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
