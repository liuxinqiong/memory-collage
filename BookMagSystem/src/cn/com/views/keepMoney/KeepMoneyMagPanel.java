package cn.com.views.keepMoney;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.borrowInfo.BorrowInfoViewBean;
import cn.com.beans.keepMoneyInfo.KeeyMoneyInfoViewBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.keepMoneyInfo.KeepMoneyInfoDAOImp;
import cn.com.daos.keepMoneyInfo.KeepMoneyInfoDAOInf;
import cn.com.global.Global;
import cn.com.listeners.keepMoney.KeepMoneyMagPanel_btnGetMoney_ActionListener;

public class KeepMoneyMagPanel extends JPanel{
	JButton btnGetMoney;
	JButton btnBackMoney;
	JButton btnClear;
	JLabel lblSearchInfo;
	JTextField txtSearchInfo;
	JButton BtnSearch;
	JTable tabView;
	JScrollPane snpView;
	DefaultTableModel dtmView;
	KeepMoneyInfoDAOInf dao;
	UserInfoBean user;
	
	public KeepMoneyMagPanel(UserInfoBean user){
		this.user=user;
		initObject();
		init();
	}
	
	public KeepMoneyMagPanel(LayoutManager lm,UserInfoBean user){
		super(lm);
		this.user=user;
		initObject();
		init();
	}
	
	
	private void init() {
		// TODO Auto-generated method stub
		btnGetMoney.setBounds(10, 10, 75, 25);
		this.add(btnGetMoney);
		btnBackMoney.setBounds(105, 10, 75, 25);
		this.add(btnBackMoney);
		btnClear.setBounds(200, 10, 75, 25);
		this.add(btnClear);
		lblSearchInfo.setBounds(10, 40, 150, 25);
		this.add(lblSearchInfo);
		txtSearchInfo.setBounds(160, 40, 120, 25);
		this.add(txtSearchInfo);
		ImageIcon image=new ImageIcon("images/query_16.png");
		BtnSearch.setIcon(image);
		BtnSearch.setBounds(290, 40, 25, 25);
		this.add(BtnSearch);
		snpView.getViewport().add(tabView);
		snpView.setBounds(0, 75, 600, 320);
		this.add(snpView);
		setTableViewData(dao.getAllKeepMoneyInfoView(""));
		setTabViewStandard();

		btnGetMoney.addActionListener(new KeepMoneyMagPanel_btnGetMoney_ActionListener(this)); 
		btnBackMoney.addActionListener(new KeepMoneyMagPanel_btnGetMoney_ActionListener(this)); 
		btnClear.addActionListener(new KeepMoneyMagPanel_btnGetMoney_ActionListener(this)); 
		BtnSearch.addActionListener(new KeepMoneyMagPanel_btnGetMoney_ActionListener(this)); 
	}
	public  void setTableViewData(
			List<KeeyMoneyInfoViewBean> allKeepMoneyInfoView) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("���߱��");
		title.add("��������");
		title.add("���");
		title.add("��������");
		title.add("����Ա");
		title.add("��������");

		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		for (KeeyMoneyInfoViewBean bb : allKeepMoneyInfoView) {
			row = new Vector();
			row.add(bb.getReaderView().getReader().getReaderId());
			row.add(bb.getReaderView().getReader().getReaderName());
			row.add(bb);
			row.add(bb.getKeeyMoney().getOperateTime());
			row.add(bb.getUser().getUserName());
			if(bb.getKeeyMoney().getUseWay()==0){
				row.add("û��");
			}else if(bb.getKeeyMoney().getUseWay()==1){
				row.add("�˻�");
			}else if(bb.getKeeyMoney().getUseWay()==2){
				row.add("��ȡ");
			}
			data.add(row);
		}
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}

	private void initObject() {
		// TODO Auto-generated method stub
		dao=new KeepMoneyInfoDAOImp();
		btnGetMoney=new JButton("��ȡ");
		btnBackMoney=new JButton("�˻�");
		btnClear=new JButton("û��");
		lblSearchInfo=new JLabel("�����������Ϣ���в�ѯ");
		txtSearchInfo=new JTextField();
		BtnSearch=new JButton();
		tabView=new JTable(){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		snpView=new JScrollPane();
	}
	
	
	public  void setTabViewStandard() {
		// TODO Auto-generated method stub
		tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // ���������ƶ�
		tabView.getTableHeader().setResizingAllowed(false); // �����������
		tabView.getColumn("���߱��").setMinWidth(100);
		tabView.getColumn("��������").setMinWidth(100);
		tabView.getColumn("���").setMinWidth(100);
		tabView.getColumn("��������").setMinWidth(100);
		tabView.getColumn("����Ա").setMinWidth(100);
		
	}
	public void btnGetMoney_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnGetMoney)){
			new GetKeepMoneyFrame(user, this,2);
		}else if(e.getSource().equals(btnBackMoney)){
			new GetKeepMoneyFrame(user, this,1);
		}else if(e.getSource().equals(btnClear)){
			new GetKeepMoneyFrame(user, this,0);
		}else if(e.getSource().equals(BtnSearch)){
			setTableViewData(dao.getAllKeepMoneyInfoView(txtSearchInfo.getText().trim()));
			setTabViewStandard();
		}
	}	
}
