package cn.com.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import cn.com.dao.GradeDao;
import cn.com.dao.UserDao;
import cn.com.dao.ViewBeanDao;
import cn.com.entity.User;
import cn.com.entity.ViewBean;
import cn.com.listeners.UserMagPanel_tabView_MouseListener;

public class UserMagPanel extends JPanel implements ActionListener{
	JButton btnAdd;
	JButton btnDelete;
	JTable tabView;
	DefaultTableModel dtmView;
	JPanel pnlButton;
	JScrollPane snpView;
	
	GradeDao gradeDao;
	UserDao userDao;
	ViewBeanDao viewBeanDao;
	
	MainFrame mf;
	String cardId;
	
	public UserMagPanel(MainFrame mf){
		this.mf=mf;
		btnAdd=new JButton("����");
		btnDelete=new JButton("ɾ��");
		tabView=new JTable(){
			public boolean isCellEditable(int row, int column) {	
				return false;
			}
		};
		pnlButton=new JPanel();
		snpView=new JScrollPane();
		gradeDao=new GradeDao();
		userDao=new UserDao();
		viewBeanDao=new ViewBeanDao();
		this.setLayout(new BorderLayout());
		init();
	}


	private void init() {
		// TODO Auto-generated method stub
		snpView.getViewport().add(tabView);
		pnlButton.add(btnAdd);
		pnlButton.add(btnDelete);	
		initTable();
		this.add(pnlButton,BorderLayout.NORTH);
		this.add(snpView,BorderLayout.CENTER);
		
		tabView.addMouseListener(new UserMagPanel_tabView_MouseListener(this));
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
	}
	
	public void initTable(){
		setTableData(viewBeanDao.getUGBeans());
		setTableStandard();
	}
	
	private void setTableStandard() {
		// TODO Auto-generated method stub
//		tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // ���������ƶ�
		tabView.getTableHeader().setResizingAllowed(false); // �����������
		tabView.getColumn("�û����").setMinWidth(150);
		tabView.getColumn("�û���").setMinWidth(150);
		tabView.getColumn("�û�����").setMinWidth(150);
	}

	private void setTableData(List<ViewBean> viewBeans) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("�û����");
		title.add("�û���");
		title.add("�û�����");
		
		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		for (ViewBean v : viewBeans) {
			row = new Vector();
			row.add(v.getUser().getUserId());
			row.add(v.getUser());
			row.add(v.getGrade());
			data.add(row);
		}
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}


	public void tabView_mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//tabView.getSelectedRow()
		if(e.getClickCount()==2){
			User user=(User)tabView.getValueAt(tabView.getSelectedRow(), 1);
			new UserDialog(this.mf,this, "�޸�", true, user,null);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type=e.getActionCommand();
		if(type.equals("����")){
			new UserDialog(this.mf,this, "����", true, null,cardId);
		}else if(type.equals("ɾ��")){
			if(tabView.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "û��ѡ��ɾ������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}else{
				User user=(User)tabView.getValueAt(tabView.getSelectedRow(), 1);
				int info=JOptionPane.showConfirmDialog(null, "���Ҫɾ����","��ʾ",JOptionPane.YES_NO_OPTION);
				if(info==JOptionPane.YES_OPTION){
					boolean bool=userDao.deleteUser(user);
					if(bool){
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						initTable();
					}else{
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "����", JOptionPane.ERROR_MESSAGE);
					}
				}
			}	
		}
	}
}
