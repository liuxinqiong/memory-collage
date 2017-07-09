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
		btn1 = new JButton("确定");
		btn2 = new JButton("取消");
		btn3 = new JButton();
		lblUserId = new JLabel("输入读者编号");
		lblWaring = new JLabel("<html>* 注销后，储值卡将处于禁用状态，若想删除或重新启用，请从主界面储值卡管理界面操作");
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
		btn1.setFont(new Font("微软雅黑",Font.PLAIN,14));
		btn2.setFont(new Font("微软雅黑",Font.PLAIN,14));
		lblUserId.setFont(new Font("微软雅黑",Font.PLAIN,14));
		lblWaring.setFont(new Font("微软雅黑",Font.PLAIN,10));
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
		this.setTitle("储值卡注销");
		this.add(pnlMain);
		this.setVisible(true);
	}
	public void btn1_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		int i = JOptionPane.showConfirmDialog(null, "确认注销？","提示",JOptionPane.YES_NO_OPTION) ;
		if(i == JOptionPane.YES_OPTION){
			if(tfdUserId.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "请输入读者编号","提示",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			ReaderInfoBean rib = new ReaderInfoBean();
			String id = tfdUserId.getText().trim();
			if(!Check.isInt(id)){
				JOptionPane.showMessageDialog(null, "用户编号为纯数字，不能有特殊字符","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			rib.setReaderId(Integer.parseInt(id));
			ReaderInfoBean rib2 = new ReaderInfoBean();
			if(!dao.checkReaderInfoByReaderId(rib)){	
				JOptionPane.showMessageDialog(null, "用户不存在或已挂失","错误",JOptionPane.ERROR_MESSAGE);
				return;
			} 
			rib2 = dao.getReaderInfoByRId(rib);
			rib2.setReaderRegistDate(rib2.getReaderRegistDate().substring(0, 19));
			if(rib2.getReaderVcState() == 0){ 
				rib2.setReaderVcState(2);
				if(dao.updateReader(rib2)){
					JOptionPane.showMessageDialog(null, "注销成功","提示",JOptionPane.INFORMATION_MESSAGE);
					JournaInfoDAO.writeJournalInfo(user.getUserName(), "注销"+rib2.getReaderName(), JournaInfoDAO.TYPE_RM);
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "注销失败","错误",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "该用户未激活","提示",JOptionPane.INFORMATION_MESSAGE);
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
