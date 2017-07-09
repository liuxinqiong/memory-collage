package cn.com.views.borrowHistorySearch;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import cn.com.beans.borrowInfo.BorrowInfoViewBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.borrowInfo.BorrowInfoDAOImp;
import cn.com.daos.borrowInfo.BorrowInfoDAOInf;
import cn.com.daos.userInfo.UserInfoDAOImp;
import cn.com.daos.userInfo.UserInfoDAOInf;
import cn.com.global.DateChooser;
import cn.com.global.Global;
import cn.com.listeners.borrowHistorySearch.BorrowHistorySearchFrame_btnSure_ActionListener;
import cn.com.services.borrowInfo.BorrowInfoServiceImp;
import cn.com.services.borrowInfo.BorrowInfoServiceInf;

public class BorrowHistorySearchFrame extends JFrame {
	JPanel pnlMain;
	JLabel lblDateScope;
	JLabel lblLine;
	JLabel lblKeyWords;
	DateChooser startDate;
	DateChooser endDate;
	JTextField txtSearchInfo;
	JButton btnSure;
	JTable tabView;
	JScrollPane snpView;
	DefaultTableModel dtmView;
	UserInfoDAOInf dao;
	BorrowInfoServiceInf service;
	BorrowInfoDAOInf dao1;

	public BorrowHistorySearchFrame() {
		dao=new UserInfoDAOImp();
		dao1=new BorrowInfoDAOImp();
		service=new BorrowInfoServiceImp();
		pnlMain = new JPanel(null);
		lblDateScope = new JLabel("借书时间范围");
		lblLine = new JLabel("~");
		lblKeyWords = new JLabel("关键字");
		startDate = new DateChooser();
		endDate = new DateChooser();
		txtSearchInfo = new JTextField();
		btnSure = new JButton();
		tabView = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		snpView = new JScrollPane();
		dtmView = new DefaultTableModel();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("借书历史查询");
		this.setSize(600, 480); // 设置窗体大小
		Global.setCenterByWindow(this);
		lblDateScope.setBounds(20, 10, 120, 20);
		pnlMain.add(lblDateScope);
		startDate.setBounds(110, 10, 90, 20);
		pnlMain.add(startDate);
		lblLine.setBounds(200, 10, 10, 20);
		pnlMain.add(lblLine);
		endDate.setBounds(210, 10, 90, 20);
		pnlMain.add(endDate);
		lblKeyWords.setBounds(350, 10, 60, 20);
		pnlMain.add(lblKeyWords);
		txtSearchInfo.setBounds(410, 10, 120, 20);
		pnlMain.add(txtSearchInfo);
		ImageIcon image = new ImageIcon("images/sure.png");
		btnSure.setIcon(image);
		btnSure.setBounds(540, 10, 20, 20);
		pnlMain.add(btnSure);
		snpView.setBounds(0, 40, 600, 400);
		snpView.getViewport().add(tabView);
		setTabViewData(dao1.getAllBorrowInfoView(""));
		setTabViewStandard();
		pnlMain.add(snpView);

		btnSure.addActionListener(new BorrowHistorySearchFrame_btnSure_ActionListener(
				this));

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
	}

	private void setTabViewData(List<BorrowInfoViewBean> allBorrowViewInfo) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("读者编号");
		title.add("读者姓名");
		title.add("性别");
		title.add("图书编号");
		title.add("图书名称");
		title.add("借出日期");
		title.add("借出操作人");
		title.add("应还日期");
		title.add("是否归还");
		title.add("实还日期");
		title.add("归还操作人");

		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		for (BorrowInfoViewBean bb : allBorrowViewInfo) {
			UserInfoBean borrowUser = dao.getUserInfoByUserId(bb
					.getBorrowUser());
			UserInfoBean backUser = dao.getUserInfoByUserId(bb
					.getBorrowUser());
			row = new Vector();
			row.add(bb.getReaderView().getReader().getReaderId());
			row.add(bb.getReaderView().getReader().getReaderName());
			row.add(bb.getReaderView().getReader().getReaderSex());
			row.add(bb.getBookView().getBook().getBookId());
			row.add(bb);
			row.add(bb.getBorrow().getBorrowDate());
			row.add(borrowUser.getUserName());
			row.add(bb.getBorrow().getNormalBackDate());
			if (bb.getBorrow().getIsBack() == 0) {
				row.add("否");
			} else {
				row.add("是");
			}
			if (bb.getBorrow().getRealBackDate() == null) {
				row.add(bb.getBorrow().getRealBackDate());
			} else {
				row.add(bb.getBorrow().getRealBackDate().substring(0, 10));
			}
			row.add(backUser.getUserName());
			data.add(row);
		}
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}


	public void btnSure_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setTabViewData(service.getHistoryBorrowInfoByRequire(startDate.showDate.getText(), endDate.showDate.getText(), txtSearchInfo.getText().trim()));
		setTabViewStandard();
	}
}
