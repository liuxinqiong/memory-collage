package cn.com.views.petcard;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.global.Check;
import cn.com.global.Global;
import cn.com.listeners.petcard.ManagePetCardFrame_btnAdd_ActionListener;
import cn.com.listeners.petcard.ManagePetCardFrame_btnCharge_ActionListener;
import cn.com.listeners.petcard.ManagePetCardFrame_btnFind_ActionListener;
import cn.com.listeners.petcard.ManagePetCardFrame_btnLogOff_ActionListener;
import cn.com.listeners.petcard.ManagePetCardFrame_btnRev_ActionListener;
import cn.com.listeners.petcard.ManagePetCardFrame_btnSerch_ActionListener;
import cn.com.listeners.petcard.ManagePetCardFrame_rbtnAllUser_ActionListener;
import cn.com.listeners.petcard.ManagePetCardFrame_rpcf_ActionListener;

public class ManagePetCardFrame extends JFrame{
	JPanel pnlMain;
	JButton btnAdd;
	JButton btnCharge;
	JButton btnRev;
	JButton btnLogOff;
	JButton btnFind;
	JButton btnSerch;
	JScrollPane snpView;
	JLabel lblUserInfo;
	JTextField tfdUserInfo;
	JRadioButton rbtnCanUser;
	JRadioButton rbtnAllUser;
	ButtonGroup btg;
	JTable tbPetCard;
	DefaultTableModel dtm;
	ReaderInfoDAOInf dao;
	UserInfoBean user;
	
	public ManagePetCardFrame(UserInfoBean user){
		this.user=user;
		pnlMain = new JPanel(null);
		btnAdd = new JButton("����");
		btnCharge = new JButton("��ֵ");
		btnRev = new JButton("����");
		btnLogOff = new JButton("ע��");
		btnFind = new JButton();
		snpView = new JScrollPane();
		lblUserInfo = new JLabel("���������Ϣ��ѯ��");
		tfdUserInfo = new JTextField();
		rbtnCanUser = new JRadioButton("��ʾ�����û�");
		rbtnAllUser = new JRadioButton("��ʾ�����û�");
		btnSerch = new JButton("����");
		btg = new ButtonGroup();
		tbPetCard = new JTable(){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		dao = new ReaderInfoDAOImp();
		init();	
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600,480);
		pnlMain.setBackground(Color.green);
		btnSerch.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnAdd.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnCharge.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnRev.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnLogOff.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		lblUserInfo.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		rbtnAllUser.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		rbtnCanUser.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnAdd.setBackground(Color.CYAN);
		btnCharge.setBackground(Color.CYAN);
		btnRev.setBackground(Color.CYAN);
		btnLogOff.setBackground(Color.CYAN);
		rbtnAllUser.setBackground(Color.pink);
		rbtnCanUser.setBackground(Color.pink);
		btnSerch.setBackground(Color.CYAN);
		btg.add(rbtnAllUser);
		btg.add(rbtnCanUser);
		
		btnAdd.addActionListener(new ManagePetCardFrame_btnAdd_ActionListener(this));
		btnCharge.addActionListener(new ManagePetCardFrame_btnCharge_ActionListener(this));
		btnRev.addActionListener(new ManagePetCardFrame_btnRev_ActionListener(this));
		btnLogOff.addActionListener(new ManagePetCardFrame_btnLogOff_ActionListener(this));
		btnFind.addActionListener(new ManagePetCardFrame_btnFind_ActionListener(this));
		rbtnAllUser.addActionListener(new ManagePetCardFrame_rbtnAllUser_ActionListener(this));
		rbtnCanUser.addActionListener(new ManagePetCardFrame_rbtnAllUser_ActionListener(this));
		btnSerch.addActionListener(new ManagePetCardFrame_btnSerch_ActionListener(this));
		
		rbtnAllUser.setSelected(true);
		
		snpView.setBounds(0,120 , 800, 440);
		rbtnAllUser.setBounds(340, 90, 120, 25);
		rbtnCanUser.setBounds(470, 90, 120, 25);
		lblUserInfo.setBounds(10,90,120,25);
		tfdUserInfo.setBounds(140, 90, 100, 25);
		btnAdd.setBounds(10, 10, 60, 60);
		btnCharge.setBounds(90,10,60,60);
		btnRev.setBounds(170,10,60,60);
		btnLogOff.setBounds(260,10,60,60);
		ImageIcon image=new ImageIcon("images/query_16.png");
		btnFind.setIcon(image);
		btnFind.setBounds(245,90,25,25);
		btnSerch.setBounds(275,90,60,25);
		snpView.getViewport().add(tbPetCard);
		pnlMain.add(tfdUserInfo);
		pnlMain.add(lblUserInfo);
		pnlMain.add(btnAdd);
		pnlMain.add(btnCharge);
		pnlMain.add(btnRev);
		pnlMain.add(btnLogOff);
		pnlMain.add(btnFind);
		pnlMain.add(rbtnAllUser);
		pnlMain.add(rbtnCanUser);
		pnlMain.add(snpView);
		pnlMain.add(btnSerch);
		
		setTabViewData(dao.getAllReaderInfo());
		setTabViewStandard();
		
		
		
		Global.setCenterByWindow(this);
		this.setTitle("��ֵ������");
		this.add(pnlMain);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void setTabViewStandard() {
		// TODO Auto-generated method stub
		tbPetCard.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbPetCard.getTableHeader().setReorderingAllowed(false); // ���������ƶ�
		tbPetCard.getTableHeader().setResizingAllowed(false); // �����������
		tbPetCard.getColumn("���߱��").setMinWidth(150);
		tbPetCard.getColumn("��������").setMinWidth(150);
		tbPetCard.getColumn("��ֵ�����").setMinWidth(150);
		tbPetCard.getColumn("����״̬").setMinWidth(150);
	}
	
	private void setTabViewTitle(){
		Vector<String> titles=new Vector<String>();	
		String[] title={"���߱��","��������","��ֵ�����","����״̬"};
		for(String s: title){
			titles.add(s);
		}
		dtm = new DefaultTableModel(null,titles);
		this.tbPetCard.setModel(dtm);
	}
	
	private void setTabViewData(List<ReaderInfoBean> allReaderInfo) {
		// TODO Auto-generated method stub
		setTabViewTitle();
		Vector data=null;
		for(ReaderInfoBean reader: allReaderInfo ){
			data = new Vector();
			data.add(reader.getReaderId());
			data.add(reader.getReaderName());
			data.add(reader.getReaderVcBalance());
			if(reader.getReaderVcState()==0){
				data.add("����");
			}else{
				data.add("������");
			}
			dtm.addRow(data);
		}
	}

	public void btnAdd_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ActPetCardFrame(user);
	}

	public void btnCharge_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		RechargePetCardFrame rpcf=new RechargePetCardFrame(user);
		
		rpcf.addWindowListener(new ManagePetCardFrame_rpcf_ActionListener(this));
		
	}


	public void btnLogOff_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		new LogOffPetCardFrame(user);
	}

	public void btnFind_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ReaderInfoDialog(null , "" ,true,null,null,null,this,null);
		
		String s = tfdUserInfo.getText();
		int id = Integer.parseInt(s);
		tfdUserInfo.setText(id+"");
		ReaderInfoBean u = new ReaderInfoBean();
		u.setReaderId(id);
		if(rbtnCanUser.isSelected()){
			if(dao.getReaderInfoByRId(u).getReaderVcState() == 0)
			{
				List<ReaderInfoBean> list = new ArrayList<ReaderInfoBean>();
				list.add(u);
				setTabViewData(list);
				setTabViewStandard();
			}
			
		}
	}

	public void btnbtnRev_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tbPetCard.getSelectedRow() == -1){
			System.out.println("vvvv");
		}else{
			int id = (Integer)tbPetCard.getValueAt(tbPetCard.getSelectedRow(), 0);
			tfdUserInfo.setText(id+"");
		}
			int i = JOptionPane.showConfirmDialog(null, "ȷ�����ã�","��ʾ",JOptionPane.YES_NO_OPTION) ;
			if(i == JOptionPane.YES_OPTION){
				if(tfdUserInfo.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "��������߱��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				ReaderInfoBean rib = new ReaderInfoBean();
				String id = tfdUserInfo.getText().trim();
				if(!Check.isInt(id)){
					JOptionPane.showMessageDialog(null, "�û����Ϊ�����֣������������ַ�","����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				rib.setReaderId(Integer.parseInt(id));
				ReaderInfoBean rib2 = new ReaderInfoBean();
				if(!dao.checkReaderInfoByReaderId(rib)){	
					JOptionPane.showMessageDialog(null, "�û������ڻ��ѹ�ʧ","����",JOptionPane.ERROR_MESSAGE);
					return;
				} 
				rib2 = dao.getReaderInfoByRId(rib);
				rib2.setReaderRegistDate(rib2.getReaderRegistDate().substring(0, 19));
				if(rib2.getReaderVcState() == 2 ){ 
					rib2.setReaderVcState(0);
					if(dao.updateReader(rib2)){
						JOptionPane.showMessageDialog(null, "���óɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						JournaInfoDAO.writeJournalInfo(user.getUserName(), "Ϊ"+rib2.getReaderName()+"���ô�ֵ��", JournaInfoDAO.TYPE_RM);
						setTabViewData(dao.getAllReaderInfo());
						setTabViewStandard();
						rbtnAllUser.setSelected(true);
					}else{
						JOptionPane.showMessageDialog(null, "����ʧ��","����",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "���û�δ��ͨ��������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

	public void rbtnAllUser_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(rbtnAllUser)){
			setTabViewData(dao.getAllReaderInfo());
			setTabViewStandard();
		}else{
			setTabViewData(dao.getCanUseReaderInfo());
			setTabViewStandard();
		}
	}

	public void rpcf_windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		setTabViewData(dao.getAllReaderInfo());
		setTabViewStandard();
		rbtnAllUser.setSelected(true);
	}

	public void btnSerch_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tfdUserInfo.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "��������߱��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		ReaderInfoBean rib = new ReaderInfoBean();
		ReaderInfoBean rib2 = new ReaderInfoBean();
		String id = tfdUserInfo.getText().trim();
	
		if(!Check.isInt(id)){
			JOptionPane.showMessageDialog(null, "�û����Ϊ�����֣������������ַ�","����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		rib.setReaderId(Integer.parseInt(id));
//		System.out.println(rib.getReaderId());
		if(!dao.checkReaderInfoByReaderId(rib)){	
			JOptionPane.showMessageDialog(null, "�û�������","����",JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		if(rbtnCanUser.isSelected()){
			rib2 = dao.getReaderInfoByRId(rib);
			if(rib2.getReaderVcState() == 0)
			{	
				List<ReaderInfoBean> list = new ArrayList<ReaderInfoBean>();
				list.add(rib2);
				setTabViewData(list);
				setTabViewStandard();
			}else{
				List<ReaderInfoBean> list = new ArrayList<ReaderInfoBean>();
				setTabViewData(list);
				setTabViewStandard();
			}
			
		}else if(rbtnAllUser.isSelected()){
			rib2 = dao.getReaderInfoByRId(rib);
			List<ReaderInfoBean> list = new ArrayList<ReaderInfoBean>();
			list.add(rib2);
			setTabViewData(list);
			setTabViewStandard();
		}
	}
}
