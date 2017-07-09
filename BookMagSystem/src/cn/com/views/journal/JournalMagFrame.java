package cn.com.views.journal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.journaInfo.JournaInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.global.DateChooser;
import cn.com.global.Global;
import cn.com.listeners.journal.JournalMagFrame_btnClear_ActionListener;
import cn.com.listeners.journal.JournalMagFrame_btnSearch_ActionListener;

public class JournalMagFrame extends JFrame {
	JPanel pnlMain;
	JLabel lblTimeRange;
	DateChooser startTime;
	DateChooser endTime;
	JLabel lblLine;
	JLabel lblOperateType;
	JComboBox cmbView;
	DefaultComboBoxModel dcmView;
	JButton btnSearch;
	JButton btnClear;
	JTable tabView;
	JScrollPane snpView;
	DefaultTableModel dtmView;
	JournaInfoDAO dao;
	UserInfoBean u;

	public JournalMagFrame(UserInfoBean u) {
		this.u=u;
		dao = new JournaInfoDAO();
		pnlMain = new JPanel(null);
		lblTimeRange = new JLabel("查询时间范围");
		startTime = new DateChooser();
		endTime = new DateChooser();
		lblLine = new JLabel("~");
		lblOperateType = new JLabel("操作类型");
		cmbView = new JComboBox();
		dcmView = new DefaultComboBoxModel();
		btnSearch = new JButton("查询");
		btnClear = new JButton("清空");
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
		this.setTitle("操作日志");
		this.setSize(600, 480); // 设置窗体大小
		Global.setCenterByWindow(this);
		lblTimeRange.setBounds(20, 10, 90, 20);
		pnlMain.add(lblTimeRange);
		startTime.setBounds(110, 10, 80, 20);
		pnlMain.add(startTime);
		lblLine.setBounds(190, 10, 10, 20);
		pnlMain.add(lblLine);
		endTime.setBounds(200, 10, 80, 20);
		pnlMain.add(endTime);
		lblOperateType.setBounds(300, 10, 60, 20);
		pnlMain.add(lblOperateType);
		cmbView.setBounds(360, 10, 110, 20);
		pnlMain.add(cmbView);
		btnSearch.setBounds(480, 10, 70, 20);
		setComboBoxData();
		pnlMain.add(btnSearch);
		btnClear.setBounds(500, 420, 70, 25);
		btnClear.setBackground(Color.YELLOW);
		pnlMain.add(btnClear);
		snpView.getViewport().add(tabView);
		snpView.setBounds(0, 40, 600, 380);
		pnlMain.add(snpView);
		setTabViewData(dao.getAllJournalInfo());
		setTabViewStandard();

		btnSearch
				.addActionListener(new JournalMagFrame_btnSearch_ActionListener(
						this));
		btnClear.addActionListener(new JournalMagFrame_btnClear_ActionListener(
						this));
		
		this.add(pnlMain);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void setComboBoxData() {
		// TODO Auto-generated method stub
		String brief[] = { "全部操作","操作员登录", "图书借阅", "读者管理", 
				 "书刊管理", "用户管理","日志管理"};
		for(int i=0;i<brief.length;i++){
			dcmView.addElement(brief[i]);
		}
		this.cmbView.setModel(dcmView);
	}

	private void setTabViewData(List<JournaInfoBean> allJournalInfo) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("操作时间");
		title.add("操作员");
		title.add("内容摘要");
		title.add("操作内容");

		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		for (JournaInfoBean jj : allJournalInfo) {
			row = new Vector();
			row.add(jj.getOperateTime());
			row.add(jj);
			row.add(jj.getBrief());
			row.add(jj.getContent());
			data.add(row);
		}
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}

	private void setTabViewStandard() {
		// TODO Auto-generated method stub
		tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tabView.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tabView.getColumn("操作时间").setMinWidth(130);
		tabView.getColumn("操作内容").setMinWidth(500);
	}

	public void btnSearch_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setTabViewData(dao.getJournalInfoByBrief(startTime.showDate.getText(), endTime.showDate.getText(), (String)cmbView.getSelectedItem()));
		setTabViewStandard();
	}

	public void btnClear_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int i = JOptionPane.showConfirmDialog(null, "是否清空？","提示",JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION){
		if(dao.deleteAllInfo()){
			JOptionPane.showMessageDialog(null, "清楚日志成功","提示",JOptionPane.INFORMATION_MESSAGE);
			JournaInfoDAO.writeJournalInfo(u.getUserName(), "清楚所有日志记录", JournaInfoDAO.TYPE_JM);
			setTabViewData(dao.getAllJournalInfo());
			setTabViewStandard();
		}else{
			JOptionPane.showMessageDialog(null, "清除日志失败","提示",JOptionPane.INFORMATION_MESSAGE);
		}
	}}
}
