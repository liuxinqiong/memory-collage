package cn.com.views.readerType;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.global.Global;
import cn.com.listeners.readerType.SetReaderTypeFrame_btnadd_ActionListeners;
import cn.com.listeners.readerType.SetReaderTypeFrame_btnalt_ActionListeners;
import cn.com.listeners.readerType.SetReaderTypeFrame_btndel_ActionListeners;
import cn.com.listeners.readerType.SetReaderTypeFrame_btnexit_ActionListener;

public class SetReaderTypeFrame extends JFrame{
	JPanel pnlMain;
	JButton btnadd;
	JButton btnalt;
	JButton btndel;
	JButton btnexit;
	JTable tabUserType;
	DefaultTableModel dtm;
	JScrollPane snpView;
	ReaderInfoDAOInf dao;
	UserInfoBean user;
	
	public SetReaderTypeFrame(UserInfoBean user){
		this.user=user;
		pnlMain = new JPanel(null);
		btnadd = new JButton("����");
		btnalt = new JButton("�޸�");
		btndel = new JButton("ɾ��");
		btnexit = new JButton("�˳�");
		tabUserType = new JTable(){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		snpView = new JScrollPane();
		dao = new ReaderInfoDAOImp();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 400);
		this.setResizable(false);
//		pnlMain.setBackground(Color.cyan);
//		btnadd.setBackground(Color.pink);
//		btnalt.setBackground(Color.magenta);
//		btndel.setBackground(Color.red);
//		btnexit.setBackground(Color.yellow);
		btnadd.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btnalt.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btndel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btnexit.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btnadd.setBounds(10, 10, 65, 65);
		btnalt.setBounds(100, 10, 65, 65);
		btndel.setBounds(190, 10, 65, 65);
		btnexit.setBounds(280, 10, 65, 65);
		snpView.setBounds(0, 80, 700, 280);
		
		btnexit.addActionListener(new SetReaderTypeFrame_btnexit_ActionListener(this));
		btndel.addActionListener(new SetReaderTypeFrame_btndel_ActionListeners(this));
		btnadd.addActionListener(new SetReaderTypeFrame_btnadd_ActionListeners(this));
		btnalt.addActionListener(new SetReaderTypeFrame_btnalt_ActionListeners(this));
		
		setTabViewData(dao.getAllReaderTypeInfo());
		setTabViewStandard();
		snpView.getViewport().add(tabUserType);
		pnlMain.add(btnexit);
		pnlMain.add(snpView);
		pnlMain.add(btnadd);
		pnlMain.add(btnalt);
		pnlMain.add(btndel);
		this.add(pnlMain);
		Global.setCenterByWindow(this);
		this.setTitle("���ö������");
		this.setVisible(true);
	}

	public void setTabViewStandard() {
		// TODO Auto-generated method stub
		tabUserType.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabUserType.getTableHeader().setReorderingAllowed(false); // ���������ƶ�
		tabUserType.getTableHeader().setResizingAllowed(false); // �����������
		tabUserType.getColumn("�����շѱ�׼��Ԫ/�죩").setMinWidth(135);
		tabUserType.getColumn("��Чʱ�䣨�죩").setMinWidth(110);
		tabUserType.getColumn("�շѱ�׼��Ԫ/�죩").setMinWidth(115);
		tabUserType.getColumn("Ѻ���Ԫ��").setMinWidth(110);
		tabUserType.getColumn("��ʧ������").setMinWidth(110);
		
	}

	public void setTabViewData(List<ReaderTypeInfoBean> allReaderTypeInfo) {
		// TODO Auto-generated method stub
		Vector<String> tit = new Vector<String>();
		String[] st = {"��������","��Чʱ�䣨�죩","Ѻ���Ԫ��","�շѱ�׼��Ԫ/�죩","�����շѱ�׼��Ԫ/�죩","�ɽ�����","��ʧ������"};
		for(String s : st){
			tit.add(s);
		}	
		dtm = new DefaultTableModel(null,tit);
		Vector data = null;
		for(ReaderTypeInfoBean rtib : allReaderTypeInfo){
			data = new Vector();
			data.add(rtib);
			data.add(rtib.getDays());
			data.add(rtib.getKeepMoney());
			data.add(rtib.getNormalRent());
			data.add(rtib.getExtendedRent());
			data.add(rtib.getMaxNum());
			data.add(rtib.getLossTimes());
			dtm.addRow(data);
		}
		
	
		this.tabUserType.setModel(dtm);
	}

	public void btnexit_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	public void btndel_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tabUserType.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(null, "��ѡ���û�","����",JOptionPane.ERROR_MESSAGE);
		}else{
			int i = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����","��ʾ",JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION){
				ReaderTypeInfoBean r =(ReaderTypeInfoBean) tabUserType.getValueAt(tabUserType.getSelectedRow(),0 );
				if(dao.delReaderType(r)){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					setTabViewData(dao.getAllReaderTypeInfo());
					setTabViewStandard();
				}else{
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��","����",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void btnadd_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AddRederTypeFrame(this,user);
	}

	public void btnalt_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tabUserType.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null, "��ѡ���û�","����",JOptionPane.ERROR_MESSAGE);
		}else{
			new AlterReaderTypeFrame(this,user);
		}
	}
}
