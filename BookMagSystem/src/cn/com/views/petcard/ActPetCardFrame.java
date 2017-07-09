package cn.com.views.petcard;

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
import cn.com.listeners.petcard.ActPetCardFrame_btn3_ActionListener;
import cn.com.listeners.petcard.ActPetCardFrame_btn2_ActionListener;

public class ActPetCardFrame extends JFrame {
	JPanel pnlMain;
	JTable tabView;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JLabel lblUserId;
	JLabel lblAddCount;
	JTextField tfdUserId;
	JTextField tfdAddCount;
	JTable tbReader;
	ReaderInfoDAOInf dao;
	UserInfoBean user;
	
	public ActPetCardFrame(UserInfoBean user){
		pnlMain = new JPanel(null);
		tabView = new JTable();
		btn1 = new JButton("确定");
		btn2 = new JButton("取消");
		btn3 = new JButton();
		lblUserId = new JLabel("输入读者编号");
		lblAddCount = new JLabel("输入开卡金额");
		tfdUserId = new JTextField();
		tfdAddCount = new JTextField();
		tbReader = new JTable();
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
		lblAddCount.setFont(new Font("微软雅黑",Font.PLAIN,14));
		lblUserId.setBounds(40, 40, 100, 25);
		lblAddCount.setBounds(40, 70, 100, 25);
		tfdUserId.setBounds(130, 40,100, 25);
		tfdAddCount.setBounds(130, 70, 100, 25);
		btn1.setBounds(70, 120, 75, 25);
		btn2.setBounds(160, 120, 75, 25);
		ImageIcon image=new ImageIcon("images/query_16.png");
		btn3.setIcon(image);
		btn3.setBounds(240, 40, 25,25);
		
		btn1.addActionListener(new ActPetCardFrame_btn1_ActionListener(this));
		btn2.addActionListener(new ActPetCardFrame_btn2_ActionListener(this));
		btn3.addActionListener(new ActPetCardFrame_btn3_ActionListener(this));
		
		pnlMain.add(tfdUserId);
		pnlMain.add(tfdAddCount);
		pnlMain.add(btn1);
		pnlMain.add(btn2);
		pnlMain.add(btn3);
		pnlMain.add(lblUserId);
		pnlMain.add(lblAddCount);
		Global.setCenterByWindow(this);
		this.setTitle("添加储值卡");
		this.add(pnlMain);
		this.setVisible(true);
	}
	public void btn1_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tfdUserId.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "请输入读者编号","提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		ReaderInfoBean rib = new ReaderInfoBean();
		ReaderInfoBean rib2 = new ReaderInfoBean();
		String id = tfdUserId.getText().trim();
		if(!Check.isInt(id)){
			JOptionPane.showMessageDialog(null, "用户编号为纯数字，不能有特殊字符","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		rib.setReaderId(Integer.parseInt(id));
		if(!dao.checkReaderInfoByReaderId(rib)){	
			JOptionPane.showMessageDialog(null, "用户不存在或已挂失","错误",JOptionPane.ERROR_MESSAGE);
			return;
		} 
		rib2 = dao.getReaderInfoByRId(rib);
		System.out.println(rib2.getReaderRegistDate());
		rib2.setReaderRegistDate(rib2.getReaderRegistDate().substring(0, 19));
		String count = tfdAddCount.getText().trim();
		if(!Check.isDouble(count)){
			JOptionPane.showMessageDialog(null, "金额为大于0的数字","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}else if(Double.parseDouble(count)<0){
			JOptionPane.showMessageDialog(null, "金额为大于0的数字","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		rib2.setReaderVcBalance(Double.parseDouble(count));
		if(dao.getReaderInfoByRId(rib2).getReaderVcState()==1){ 
			rib2.setReaderVcState(0);
			if(dao.updateReader(rib2)){
				JOptionPane.showMessageDialog(null, "开卡成功","提示",JOptionPane.INFORMATION_MESSAGE);
				JournaInfoDAO.writeJournalInfo(user.getUserName(), "为"+rib2.getReaderName()+"开通储值卡", JournaInfoDAO.TYPE_RM);
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "开卡失败","错误",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "该用户已开卡","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		 
		
			
}
	public void btn2_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}
	public void btn3_Performed(ActionEvent e) {
		// TODO Auto-generated method stub
		ReaderInfoDialog  rid = new ReaderInfoDialog(null, "", true,this,null,null,null,null);
	}
	
	
}
