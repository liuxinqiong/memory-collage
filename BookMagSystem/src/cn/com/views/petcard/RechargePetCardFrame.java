package cn.com.views.petcard;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.global.Check;
import cn.com.global.Global;
import cn.com.listeners.petcard.ActPetCardFrame_btn1_ActionListener;
import cn.com.listeners.petcard.ActPetCardFrame_btn2_ActionListener;
import cn.com.listeners.petcard.ActPetCardFrame_btn3_ActionListener;
import cn.com.listeners.petcard.RechargePetCardFrame_btn1_ActionListener;
import cn.com.listeners.petcard.RechargePetCardFrame_btn2_ActionListener;
import cn.com.listeners.petcard.RechargePetCardFrame_btn3_ActionListener;

public class RechargePetCardFrame extends JFrame{
	JPanel pnlMain;
	JTable tabView;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JLabel lblUserId;
	JLabel lblRechargeCount;
	JTextField tfdUserId;
	JTextField tfdAddCount;
	ReaderInfoDAOInf dao;
	UserInfoBean user;
	
	public RechargePetCardFrame (UserInfoBean user){
		pnlMain = new JPanel(null);
		tabView = new JTable();
		btn1 = new JButton("ȷ��");
		btn2 = new JButton("ȡ��");
		btn3 = new JButton();
		lblUserId = new JLabel("������߱��");
		lblRechargeCount = new JLabel("�����ֵ���");
		tfdUserId = new JTextField();
		tfdAddCount = new JTextField();
		dao = new ReaderInfoDAOImp();
		this.user = user;
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		
		this.setResizable(false);
		btn1.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btn2.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lblUserId.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lblRechargeCount.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lblUserId.setBounds(40, 40, 100, 25);
		lblRechargeCount.setBounds(40, 70, 100, 25);
		tfdUserId.setBounds(130, 40,100, 25);
		tfdAddCount.setBounds(130, 70, 100, 25);
		btn1.setBounds(70, 120, 75, 25);
		btn2.setBounds(160, 120, 75, 25);
		ImageIcon image=new ImageIcon("images/query_16.png");
		btn3.setIcon(image);
		btn3.setBounds(240, 40, 25,25);
		
		btn1.addActionListener(new RechargePetCardFrame_btn1_ActionListener(this));
		btn2.addActionListener(new RechargePetCardFrame_btn2_ActionListener(this));
		btn3.addActionListener(new RechargePetCardFrame_btn3_ActionListener(this));
		
		pnlMain.add(tfdUserId);
		pnlMain.add(tfdAddCount);
		pnlMain.add(btn1);
		pnlMain.add(btn2);
		pnlMain.add(btn3);
		pnlMain.add(lblUserId);
		pnlMain.add(lblRechargeCount);
		Global.setCenterByWindow(this);
		this.setTitle("��ֵ����ֵ");
		this.add(pnlMain);
		this.setVisible(true);
	}
	public void btn1_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tfdUserId.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "��������߱��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		ReaderInfoBean rib = new ReaderInfoBean();
		ReaderInfoBean rib2 = new ReaderInfoBean();
		String id = tfdUserId.getText().trim();
		if(!Check.isInt(id)){
			JOptionPane.showMessageDialog(null, "�û����Ϊ�����֣������������ַ�","����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		rib.setReaderId(Integer.parseInt(id));
		if(!dao.checkReaderInfoByReaderId(rib)){	
			JOptionPane.showMessageDialog(null, "�û������ڻ��ѹ�ʧ","����",JOptionPane.ERROR_MESSAGE);
			return;
		} 
		rib2 = dao.getReaderInfoByRId(rib);
		rib2.setReaderRegistDate(rib2.getReaderRegistDate().substring(0, 19));
		double dcount = dao.getReaderInfoByRId(rib).getReaderVcBalance();
		String count = tfdAddCount.getText().trim();
		if(!Check.isDouble(count)){
			JOptionPane.showMessageDialog(null, "���Ϊ����0������","����",JOptionPane.ERROR_MESSAGE);
			return;
		}else if(Double.parseDouble(count)<0){
			JOptionPane.showMessageDialog(null, "���Ϊ����0������","����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		rib2.setReaderVcBalance(Double.parseDouble(count)+dcount);
		if(dao.getReaderInfoByRId(rib2).getReaderVcState()!=0){ 
			JOptionPane.showMessageDialog(null, "���û�δ�������ע��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else{
			if(dao.updateReader(rib2)){
				JOptionPane.showMessageDialog(null, "��ֵ�ɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				JournaInfoDAO.writeJournalInfo(user.getUserName(), "Ϊ"+rib2.getReaderName()+"��ֵ"+Double.parseDouble(count)+"RMB", JournaInfoDAO.TYPE_RM);
			}else{
				JOptionPane.showMessageDialog(null, "��ֵʧ��","����",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void btn2_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		
	}
	public void btn3_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ReaderInfoDialog(null , "" ,true,null,this,null,null,null);
	}
}
