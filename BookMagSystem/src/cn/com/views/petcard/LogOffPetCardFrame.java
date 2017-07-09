package cn.com.views.petcard;

import java.awt.Color;
import java.awt.Font;
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
import cn.com.listeners.petcard.LogOffPetCardFrame_btn1_ActionListener;
import cn.com.listeners.petcard.LogOffPetCardFrame_btn2_ActionListener;
import cn.com.listeners.petcard.LogOffPetCardFrame_btn3_ActionListener;

public class LogOffPetCardFrame extends JFrame{
	JPanel pnlMain;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JLabel lblUserId;
	JLabel lblWaring;
	JTextField tfdUserId;
	ReaderInfoDAOInf dao;
	UserInfoBean user;
	
	public LogOffPetCardFrame(UserInfoBean user){
		pnlMain = new JPanel(null);
		btn1 = new JButton("ȷ��");
		btn2 = new JButton("ȡ��");
		btn3 = new JButton();
		lblUserId = new JLabel("������߱��");
		lblWaring = new JLabel("<html>* ע���󣬴�ֵ�������ڽ���״̬������ɾ�����������ã���������洢ֵ������������");
		tfdUserId = new JTextField();
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
		lblWaring.setFont(new Font("΢���ź�",Font.PLAIN,10));
		lblWaring.setForeground(Color.red);;
		lblUserId.setBounds(40, 40, 100, 25);
		lblWaring.setBounds(70,55,150,75);
		tfdUserId.setBounds(130, 40,100, 25);
		btn1.setBounds(70, 120, 75, 25);
		btn2.setBounds(160, 120, 75, 25);
		
		ImageIcon image=new ImageIcon("images/query_16.png");
		btn3.setIcon(image);
		btn3.setBounds(240, 40, 25,25);
		
		btn1.addActionListener(new LogOffPetCardFrame_btn1_ActionListener(this));
		btn2.addActionListener(new LogOffPetCardFrame_btn2_ActionListener(this));
		btn3.addActionListener(new LogOffPetCardFrame_btn3_ActionListener(this));
		
		pnlMain.add(tfdUserId);
		pnlMain.add(btn1);
		pnlMain.add(btn2);
		pnlMain.add(btn3);
		pnlMain.add(lblUserId);
		pnlMain.add(lblWaring);
		Global.setCenterByWindow(this);
		this.setTitle("��ֵ��ע��");
		this.add(pnlMain);
		this.setVisible(true);
	}
	public void btn1_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		int i = JOptionPane.showConfirmDialog(null, "ȷ��ע����","��ʾ",JOptionPane.YES_NO_OPTION) ;
		if(i == JOptionPane.YES_OPTION){
			if(tfdUserId.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "��������߱��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			ReaderInfoBean rib = new ReaderInfoBean();
			String id = tfdUserId.getText().trim();
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
			if(rib2.getReaderVcState() == 0){ 
				rib2.setReaderVcState(2);
				if(dao.updateReader(rib2)){
					JOptionPane.showMessageDialog(null, "ע���ɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					JournaInfoDAO.writeJournalInfo(user.getUserName(), "ע��"+rib2.getReaderName(), JournaInfoDAO.TYPE_RM);
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "ע��ʧ��","����",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "���û�δ����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	public void btn2_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		
	}
	public void btn3_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ReaderInfoDialog(null , "" ,true,null,null,this,null,null);
	}
	
}
