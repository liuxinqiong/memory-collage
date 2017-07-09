package cn.com.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.com.dao.GradeDao;
import cn.com.dao.UserDao;
import cn.com.entity.Grade;
import cn.com.entity.User;
import cn.com.global.Global;

public class UserDialog extends JDialog implements ActionListener{
	
	JLabel lblUserId;
	JTextField txtUserId;
	JLabel lblUserName;
	JTextField txtUserName;
	JLabel lblUserGrade;
	JComboBox<Grade> cmbView;
	DefaultComboBoxModel<Grade> dcmView;
	JButton btnSave;
	JButton btnCancel;
	JPanel pnlMain;
	
	MainFrame mf;
	GradeDao gradeDao;
	UserDao userDao;
	String cardId;
	UserMagPanel ump;
	public UserDialog(MainFrame mf,UserMagPanel ump,String title,boolean model,User u,String cardId){
		super(mf,title,model);
		this.mf=mf;
		this.ump=ump;
		this.cardId=cardId;
		lblUserId=new JLabel("�û���ţ�");
		txtUserId=new JTextField();
		lblUserName=new JLabel("�û�������");
		txtUserName=new JTextField();
		lblUserGrade=new JLabel("�û�����");
		cmbView=new JComboBox<Grade>();
		dcmView=new DefaultComboBoxModel<Grade>();	
		
		gradeDao=new GradeDao();
		userDao=new UserDao();
		
		if(u==null){
			btnSave=new JButton("����");
			txtUserId.setText(cardId);
			txtUserId.setEditable(false);
		}else{
			btnSave=new JButton("�޸�");
			txtUserId.setText(u.getUserId());
			txtUserId.setEnabled(false);
			txtUserName.setText(u.getUserName());
			dcmView.setSelectedItem(gradeDao.getGradeById(u.getUserGradeId()));
		}
		btnCancel=new JButton("ȡ��");
		pnlMain=new JPanel(new GridLayout(4, 2, 10, 20));
		init();
	}


	private void init() {
		// TODO Auto-generated method stub
		pnlMain.add(lblUserId);
		pnlMain.add(txtUserId);
		pnlMain.add(lblUserName);
		pnlMain.add(txtUserName);
		pnlMain.add(lblUserGrade);
		pnlMain.add(cmbView);
		pnlMain.add(btnSave);
		pnlMain.add(btnCancel);	
		initCmbViewData();
		this.add(pnlMain);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		Global.setCenterByWindow(this);
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		
		this.setVisible(true);
	}


	private void initCmbViewData() {
		// TODO Auto-generated method stub
		List<Grade> grades=gradeDao.getAllGrade();
		for (Grade grade : grades) {
			dcmView.addElement(grade);
		}
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
		String userName=txtUserName.getText().trim();	
		String userId=txtUserId.getText().trim();
		if(userId.length()==0){
			JOptionPane.showMessageDialog(null,"<html>�û�Id����Ϊ��<br/>���������ťǰ��ˢһ��Ҫ������Ic��</html>", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(userName.length()==0){
			JOptionPane.showMessageDialog(null,"�û�������Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Grade grade=(Grade) dcmView.getSelectedItem();
		
		User user=new User(userId, userName, grade.getGradeId());
		if(type.equals("����")){
			if(userDao.volidate(user)){
				int result=JOptionPane.showConfirmDialog(null, "���û�Id�Ѿ����ڣ��Ƿ�Ҫ�Ը��´�����Ϣ","",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION){
					this.dispose();
					new UserDialog(this.mf,this.ump, "�޸�", true, userDao.getUserById(user),null);
					
				}
				return;
			}
			boolean bool=userDao.addUser(user);
			showResult(bool, type);	
			this.ump.initTable();
		}else if(type.equals("�޸�")){
			boolean bool=userDao.updateUser(user);
			showResult(bool, type);	
			this.ump.initTable();
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
