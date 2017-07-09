package cn.com.views.extendedMag;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.borrowInfo.BorrowInfoViewBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.userInfo.UserInfoDAOImp;
import cn.com.daos.userInfo.UserInfoDAOInf;
import cn.com.global.Check;
import cn.com.global.Global;
import cn.com.listeners.extendedBorrInfo.ExtendedMagFrame_btnSure_ActionListener;
import cn.com.services.borrowInfo.BorrowInfoServiceImp;
import cn.com.services.borrowInfo.BorrowInfoServiceInf;

public class ExtendedMagFrame extends JFrame {
	JPanel pnlMain;
	JLabel lblExtendedDays;
	JTextField txtExtendsDays;
	JLabel lblKeywords;
	JTextField txtKeywords;
	JButton btnSure;
	JTable tabView;
	JScrollPane snpView;
	DefaultTableModel dtmView;
	BorrowInfoServiceInf service;
	UserInfoDAOInf dao;

	public ExtendedMagFrame() {
		pnlMain = new JPanel(null);
		service = new BorrowInfoServiceImp();
		dao = new UserInfoDAOImp();
		lblExtendedDays = new JLabel("超期天数大于等于：");
		txtExtendsDays = new JTextField();
		lblKeywords = new JLabel("关键字：");
		txtKeywords = new JTextField();
		btnSure = new JButton();
		tabView = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		snpView = new JScrollPane();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("逾期借阅管理");
		Toolkit tool = Toolkit.getDefaultToolkit(); // 获得默认的工具箱
		Dimension screenSize = tool.getScreenSize(); // 获得屏幕的大小
		this.setSize(600, 480); // 设置窗体大小
		this.setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		lblExtendedDays.setBounds(20, 10, 120, 20);
		pnlMain.add(lblExtendedDays);
		txtExtendsDays.setBounds(140, 10, 90, 20);
		txtExtendsDays.setText("0");
		pnlMain.add(txtExtendsDays);
		lblKeywords.setBounds(280, 10, 60, 20);
		pnlMain.add(lblKeywords);
		txtKeywords.setBounds(340, 10, 120, 20);
		pnlMain.add(txtKeywords);
		ImageIcon image = new ImageIcon("images/sure.png");
		btnSure.setIcon(image);
		btnSure.setBounds(480, 10, 20, 20);
		pnlMain.add(btnSure);
		snpView.getViewport().add(tabView);
		snpView.setBounds(0, 40, 600, 400);
		pnlMain.add(snpView);

		setTabViewData(service.getExtendedBorrowInfo());
		setTabViewStandard();

		 btnSure.addActionListener(new
		 ExtendedMagFrame_btnSure_ActionListener(this));

		this.add(pnlMain);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void setTabViewStandard() {
		// TODO Auto-generated method stub
		tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tabView.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tabView.getColumn("图书名称").setMinWidth(120);
		tabView.getColumn("出版社").setMinWidth(120);
	}

	private void setTabViewData(List<BorrowInfoViewBean> allBorrowView) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("超期天数");
		title.add("图书编号");
		title.add("图书名称");
		title.add("出版社");
		title.add("读者编号");
		title.add("读者姓名");
		title.add("借出日期");
		title.add("应还日期");
		title.add("借出操作员");

		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		for (BorrowInfoViewBean bb : allBorrowView) {
			UserInfoBean borrowUser = dao.getUserInfoByUserId(bb
					.getBorrowUser());
			row = new Vector();
			row.add(Global.getTimeBetweenNow(bb.getBorrow().getNormalBackDate()));
			row.add(bb.getBookView().getBook().getBookId());
			row.add(bb);
			row.add(bb.getBookView().getBook().getPublisher());
			row.add(bb.getReaderView().getReader().getReaderId());
			row.add(bb.getReaderView().getReader().getReaderName());
			row.add(bb.getBorrow().getBorrowDate());
			row.add(bb.getBorrow().getNormalBackDate());
			row.add(borrowUser.getUserName());
			data.add(row);
		}
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}

	public void btnSure_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!Check.isDouble(txtExtendsDays.getText().trim())){
			 JOptionPane.showMessageDialog(null,
			 "输入的超期天数非法","错误",JOptionPane.ERROR_MESSAGE);
			 }else{
				 System.out.println(txtKeywords.getText().trim());
				 List<BorrowInfoViewBean> list=service.getExtendedBorrowInfoByRequire( Integer.valueOf(txtExtendsDays.getText().trim()), txtKeywords.getText()
	 .trim());
				 System.out.println(list.size());
				 setTabViewData(list);
				 setTabViewStandard();
			 }
	}
}
