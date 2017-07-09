package cn.com.views.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.userInfo.UserInfoDAOImp;
import cn.com.daos.userInfo.UserInfoDAOInf;
import cn.com.global.Global;
import cn.com.listeners.users.MgrUserFrame_btnAdd_ActionListener;
import cn.com.listeners.users.MgrUserFrame_btnAlt_Actionlistener;
import cn.com.listeners.users.MgrUserFrame_btnDelete_Actionlistener;

public class MgrUserFrame extends JFrame {
	JPanel pnlMain;
	JLabel lblOpt;
	JLabel lblWar;
	JButton btnAdd;
	JButton btnAlt;
	JButton btnDelete;
	JTable tbUser;
	JScrollPane snpView;
	DefaultTableModel dtm;
	UserInfoDAOInf dao;
	UserInfoBean user;
	
	
	public MgrUserFrame(UserInfoBean user){
		pnlMain = new JPanel(null);
		dao =new UserInfoDAOImp();
		lblOpt = new JLabel("����Ա");
		lblWar = new JLabel("<html>1:���� <br>0:ͣ��");
		btnAdd = new JButton("���");
		btnAlt = new JButton("�޸�");
		btnDelete = new JButton("ɾ��");
		this.user = user;
		
		tbUser = new JTable(){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		snpView = new JScrollPane();
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500,400);
		
		btnAdd.addActionListener(new MgrUserFrame_btnAdd_ActionListener(this));
		btnAlt.addActionListener(new MgrUserFrame_btnAlt_Actionlistener(this));
		btnDelete.addActionListener(new MgrUserFrame_btnDelete_Actionlistener(this));
		
		lblOpt.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btnAdd.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btnAlt.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btnDelete.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lblOpt.setBounds(210, 10, 50, 25);
		snpView.setBounds(0, 50, 500, 260);
		lblWar.setBounds(20, 320, 100, 50);
		lblWar.setForeground(Color.red);
		btnAdd.setBounds(130, 330, 70,25);
		btnAlt.setBounds(230, 330,70, 25);
		btnDelete.setBounds(330, 330,70, 25);
		snpView.getViewport().add(tbUser);
		
		pnlMain.add(snpView);
		pnlMain.add(lblWar);
		pnlMain.add(lblOpt);
		pnlMain.add(btnAdd);
		pnlMain.add(btnAlt);
		pnlMain.add(btnDelete);
		
		setTabViewDate(dao.getAllUserInfo());
		this.add(pnlMain);
		Global.setCenterByWindow(this);
		this.setTitle("����Ա����");
		this.setVisible(true);
		
	}

	public void setTabViewDate(List<UserInfoBean> allUserInfo) {
		// TODO Auto-generated method stub
		Vector<String> tit = new Vector<String>();
		String[] st = {"����Ȩ����","�û����","��ְʱ��","��ϵ�绰","�û�����","�Ƿ�ͣ��"};
		for(String s : st){
			tit.add(s);
		}
		dtm = new DefaultTableModel(null,tit);
		Vector data=null;
		for(UserInfoBean user:allUserInfo){
			data = new Vector();
			if(user.getUserType()==0){
				data.add("��������Ա");
			}else{
				data.add("��ͨ�û�");
			}
			data.add(user.getUserId());
			data.add(user.getUserWorkDate());
			data.add(user.getUserTel());
			data.add(user);
			data.add(user.getUserState());
			dtm.addRow(data);
		}
		this.tbUser.setModel(dtm);
	}

	public void btnAdd_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AddUserFrame(null,"�û�����",true,this,user);
		
	}

	public void btnAlt_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tbUser.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null,"��ѡ���û�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else{
			UserInfoBean u= (UserInfoBean)tbUser.getValueAt(tbUser.getSelectedRow(), 4);
			new AlterUserFrame(null, "�û��޸�", true,this,u);
			JournaInfoDAO.writeJournalInfo(user.getUserName(), "Ϊ"+u.getUserName()+"�޸���Ϣ", JournaInfoDAO.TYPE_RM);
		}
		
		
	}

	public void btnDelete_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tbUser.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null,"��ѡ���û�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else{
			int i = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����","��ʾ",JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION){
				UserInfoBean u= (UserInfoBean)tbUser.getValueAt(tbUser.getSelectedRow(), 4);
				if(dao.deleteUser(u)){
					JOptionPane.showMessageDialog(null,"ɾ���ɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					JournaInfoDAO.writeJournalInfo(user.getUserName(), "ɾ�����û�"+u.getUserName(), JournaInfoDAO.TYPE_RM);
					this.setTabViewDate(dao.getAllUserInfo());
				}else{
					JOptionPane.showMessageDialog(null,"ɾ��ʧ��","����",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
}
