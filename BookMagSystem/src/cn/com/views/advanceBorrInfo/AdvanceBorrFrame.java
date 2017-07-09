package cn.com.views.advanceBorrInfo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.advanceBorrInfo.AdvanceBorrInfoViewBean;
import cn.com.beans.borrowInfo.BorrowInfoViewBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.global.DateChooser;
import cn.com.global.Global;
import cn.com.listeners.advanceBorrInfo.AdvanceBorrFrame_btnBorrow_ActionListener;
import cn.com.listeners.advanceBorrInfo.AdvanceBorrFrame_btnDelete_ActionListener;
import cn.com.listeners.advanceBorrInfo.AdvanceBorrFrame_btnSure_ActionListener;
import cn.com.services.advanceBorrInfo.AdvanceBorrInfoServiceImp;
import cn.com.services.advanceBorrInfo.AdvanceBorrInfoServiceInf;
import cn.com.services.borrowInfo.BorrowInfoServiceImp;
import cn.com.services.borrowInfo.BorrowInfoServiceInf;

public class AdvanceBorrFrame extends JFrame{
	JPanel pnlMain;
	JLabel lblSearchWay;
	JComboBox cmbView;
	DefaultComboBoxModel dcmView;
	JLabel lblAdvanceBorrDate;
	DateChooser dateStart;
	DateChooser dateEnd;
	JLabel lblLine;
	JLabel lblSearchContent;
	JTextField txtSearchContent;
	JButton btnSure;
	JTable tabView;
	JScrollPane snpView;
	DefaultTableModel dtmView;
	JButton btnBorrow;
	JButton btnDelete;
	AdvanceBorrInfoServiceInf service;
	BorrowInfoServiceInf service2;
	UserInfoBean user;
	public AdvanceBorrFrame(){
		this.user=user;
		service=new AdvanceBorrInfoServiceImp();
		service2=new BorrowInfoServiceImp();
		pnlMain=new JPanel(null);
		lblSearchWay=new JLabel("查询方式：");
		cmbView=new JComboBox();
		dcmView=new DefaultComboBoxModel();
		lblAdvanceBorrDate=new JLabel("预借日期：");
		dateStart=new DateChooser();
		dateEnd=new DateChooser();
		lblLine=new JLabel("-");
		lblSearchContent=new JLabel("查询内容：");
		txtSearchContent=new JTextField();
		btnSure=new JButton();
		tabView=new JTable(){
			public boolean isCellEditable(int row,int colomn){
				return false;
			}
		};
		snpView=new JScrollPane();
		btnBorrow=new JButton("借出");
		btnDelete=new JButton("删除");
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("预借信息管理");
		this.setSize(680, 480);
		Global.setCenterByWindow(this);
		lblSearchWay.setBounds(20, 10, 80, 30);
		lblSearchWay.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		pnlMain.add(lblSearchWay);
		cmbView.setBounds(90, 10, 120, 30);
		cmbView.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		pnlMain.add(cmbView);
		lblAdvanceBorrDate.setBounds(220, 10, 120, 30);	
		lblAdvanceBorrDate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		pnlMain.add(lblAdvanceBorrDate);
		dateStart.setBounds(290, 10, 70, 30);
		pnlMain.add(dateStart);
		lblLine.setBounds(365, 10, 10, 30);
		pnlMain.add(lblLine);
		dateEnd.setBounds(380, 10, 70, 30);
		pnlMain.add(dateEnd);
		lblSearchContent.setBounds(460, 10, 80, 30);
		pnlMain.add(lblSearchContent);
		lblSearchContent.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtSearchContent.setBounds(530, 10, 80, 25);
		pnlMain.add(txtSearchContent);
		ImageIcon image=new ImageIcon("images/sure.png");
		btnSure.setIcon(image);
		btnSure.setBounds(620, 10, 25, 25);
		pnlMain.add(btnSure);
		snpView.getViewport().add(tabView);
		snpView.setBounds(0, 50, 680, 340);
		pnlMain.add(snpView);
		btnBorrow.setBounds(160, 400, 75, 25);
		btnBorrow.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnBorrow.setVisible(false);
		pnlMain.add(btnBorrow);
		btnDelete.setBounds(380, 400, 75, 25);
		btnDelete.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		pnlMain.add(btnDelete);
		
		setCmbViewData();
		setTabViewData(service.getAllAdvanceBorrInfo());
		setTabViewStandard();
		
		btnSure.addActionListener(new AdvanceBorrFrame_btnSure_ActionListener(this));
		btnDelete.addActionListener(new AdvanceBorrFrame_btnDelete_ActionListener(this));
		btnBorrow.addActionListener(new AdvanceBorrFrame_btnBorrow_ActionListener(this));
		
		this.add(pnlMain);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void setTabViewStandard() {
		// TODO Auto-generated method stub
		tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tabView.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tabView.getColumn("电话").setMinWidth(100);
		tabView.getColumn("图书名称").setMinWidth(100);
		tabView.getColumn("出版社").setMinWidth(100);
		tabView.getColumn("预借时间").setMinWidth(150);
	}

	private void setTabViewData(List<AdvanceBorrInfoViewBean> allAdvanceBorrInfo) {
		// TODO Auto-generated method stub
		Vector<String> title=new Vector<String>();
		String[] titles={"是否满足","可借数量","读者编号","读者姓名","性别","电话","图书编号","图书名称","出版社","预借时间","操作员"};
		for(String s:titles){
			title.add(s);
		}
		dtmView=new DefaultTableModel(null,title);
		Vector row=null;
		for(AdvanceBorrInfoViewBean abiv:allAdvanceBorrInfo){
			row=new Vector();
			if(abiv.getBookView().getBook().getBookTotalNum()-abiv.getBookView().getBook().getBookOutNum()>0){
				row.add("Y");
			}else{
				row.add("N");
			}
			row.add(abiv.getBookView().getBook().getBookTotalNum()-abiv.getBookView().getBook().getBookOutNum());
			row.add(abiv.getReaderView().getReader().getReaderId());
			row.add(abiv.getReaderView().getReader().getReaderName());
			row.add(abiv.getReaderView().getReader().getReaderSex());
			row.add(abiv.getReaderView().getReader().getReaderTel());
			row.add(abiv.getBookView().getBook().getBookId());
			row.add(abiv.getBookView().getBook().getBookName());
			row.add(abiv.getBookView().getBook().getPublisher());
			row.add(abiv);
			row.add(abiv.getUser().getUserName());
			dtmView.addRow(row);
		}
		tabView.setModel(dtmView);
	}

	private void setCmbViewData() {
		// TODO Auto-generated method stub
		dcmView.addElement("所有预借记录");
		dcmView.addElement("未满足的预借");
		dcmView.addElement("已满足的预借");
		cmbView.setModel(dcmView);
	}

	public void btnSure_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setTabViewData(service.getAdvanceBorrInfoByRequire(cmbView.getSelectedIndex(), dateStart.showDate.getText(), dateEnd.showDate.getText(), txtSearchContent.getText().trim()));
		setTabViewStandard();
	}

	public void btnDelete_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AdvanceBorrInfoViewBean abiv=null;		
		int row=tabView.getSelectedRow();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "没有选定删除目标","提示",JOptionPane.INFORMATION_MESSAGE);
		}else{
			abiv=(AdvanceBorrInfoViewBean)tabView.getValueAt(row, 9);
			if(service.deleteAdvanceBorrInfo(abiv)){
				JOptionPane.showMessageDialog(null, "删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
		//		JournaInfoDAO.writeJournalInfo(user.getUserName(), "删除了"+abiv.getReader().getReaderId()+"号读者关于图书编号为"+abiv.getBook().getBookId()+"的预借信息", JournaInfoDAO.TYPE_BB);
			}else{
				JOptionPane.showMessageDialog(null, "删除失败","错误",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

	public void btnBorrow_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AdvanceBorrInfoViewBean abiv=null;		
		int row=tabView.getSelectedRow();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "没有选定借出书籍","提示",JOptionPane.INFORMATION_MESSAGE);
		}else{
			abiv=(AdvanceBorrInfoViewBean)tabView.getValueAt(row, 9);
			BorrowInfoViewBean biv=new BorrowInfoViewBean();
			biv.setBookView(abiv.getBookView());
			biv.setReaderView(abiv.getReaderView());
			biv.setBorrowUser(abiv.getUser());
			if(service2.borrowOneBook(biv)){
				JOptionPane.showMessageDialog(null, "成功借出书籍","提示",JOptionPane.INFORMATION_MESSAGE);
//				JournaInfoDAO.writeJournalInfo(user.getUserName(), "为"+abiv.getReader().getReaderId()+"号读者借出图书编号为"+abiv.getBook().getBookId()+"的图书", JournaInfoDAO.TYPE_BB);
			}else{
				JOptionPane.showMessageDialog(null, "借出失败","错误",JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
}
