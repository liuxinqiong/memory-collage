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
		lblUserId=new JLabel("用户编号：");
		txtUserId=new JTextField();
		lblUserName=new JLabel("用户姓名：");
		txtUserName=new JTextField();
		lblUserGrade=new JLabel("用户级别：");
		cmbView=new JComboBox<Grade>();
		dcmView=new DefaultComboBoxModel<Grade>();	
		
		gradeDao=new GradeDao();
		userDao=new UserDao();
		
		if(u==null){
			btnSave=new JButton("新增");
			txtUserId.setText(cardId);
			txtUserId.setEditable(false);
		}else{
			btnSave=new JButton("修改");
			txtUserId.setText(u.getUserId());
			txtUserId.setEnabled(false);
			txtUserName.setText(u.getUserName());
			dcmView.setSelectedItem(gradeDao.getGradeById(u.getUserGradeId()));
		}
		btnCancel=new JButton("取消");
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
		if(type.equals("取消")){
			this.dispose();
			return;
		}
		String userName=txtUserName.getText().trim();	
		String userId=txtUserId.getText().trim();
		if(userId.length()==0){
			JOptionPane.showMessageDialog(null,"<html>用户Id不能为空<br/>点击新增按钮前，刷一次要新增的Ic卡</html>", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(userName.length()==0){
			JOptionPane.showMessageDialog(null,"用户名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Grade grade=(Grade) dcmView.getSelectedItem();
		
		User user=new User(userId, userName, grade.getGradeId());
		if(type.equals("新增")){
			if(userDao.volidate(user)){
				int result=JOptionPane.showConfirmDialog(null, "该用户Id已经存在，是否要对更新此人信息","",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION){
					this.dispose();
					new UserDialog(this.mf,this.ump, "修改", true, userDao.getUserById(user),null);
					
				}
				return;
			}
			boolean bool=userDao.addUser(user);
			showResult(bool, type);	
			this.ump.initTable();
		}else if(type.equals("修改")){
			boolean bool=userDao.updateUser(user);
			showResult(bool, type);	
			this.ump.initTable();
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
