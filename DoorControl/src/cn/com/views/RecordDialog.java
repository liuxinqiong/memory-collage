package cn.com.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.dao.RecordDao;
import cn.com.dao.UserDao;
import cn.com.dao.ViewBeanDao;
import cn.com.entity.Record;
import cn.com.entity.User;
import cn.com.entity.ViewBean;
import cn.com.global.DateChooser;
import cn.com.global.Global;
import cn.com.listeners.RecordDialog_tabView_MouseListener;

public class RecordDialog extends JDialog implements ActionListener{
	JPanel pnlMain;
	JPanel pnlNorth;
	JButton btnSearch;
	JLabel lblKey;
	JTextField txtKey;
	JLabel lblDate;
	JLabel lblLine;
	//获得值 name.showDate.getText()
	DateChooser dcStart;
	DateChooser dcEnd;
	JTable tabView;
	JScrollPane snpView;
	DefaultTableModel dtmView;
	RecordDao recordDao;
	UserDao userDao;
	ViewBeanDao viewBeanDao;
	
	final String START=" 00:00:00";
	final String END=" 23:59:59";
	
	public RecordDialog(MainFrame mf,String title,boolean model){
		super(mf, title, model);
		pnlMain=new JPanel(new BorderLayout());
		pnlNorth=new JPanel();
		btnSearch=new JButton("查询");
		lblDate=new JLabel("起止时间");
		lblLine=new JLabel("~");
		dcStart=new DateChooser();
		dcEnd=new DateChooser();
		lblKey=new JLabel("关键字");
		txtKey=new JTextField(8);
 		tabView=new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		snpView=new JScrollPane();
		
		recordDao=new RecordDao();
		userDao=new UserDao();
		viewBeanDao=new ViewBeanDao() ;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(500, 300);
		Global.setCenterByWindow(this);
		pnlNorth.add(lblKey);
		pnlNorth.add(txtKey);
		pnlNorth.add(lblDate);
		pnlNorth.add(dcStart);
		pnlNorth.add(lblLine);
		pnlNorth.add(dcEnd);
		pnlNorth.add(btnSearch);
		pnlMain.add(pnlNorth,BorderLayout.NORTH);
		snpView.getViewport().add(tabView);
		pnlMain.add(snpView,BorderLayout.CENTER);
		this.add(pnlMain);
		
		initTable(viewBeanDao.getAllViewBeans(null,null,null));
		
		btnSearch.addActionListener(this);
		tabView.addMouseListener(new RecordDialog_tabView_MouseListener(this));
		this.setVisible(true);
	}
	
	public void initTable(List<ViewBean> viewBeans){
		setTableData(viewBeans);
		setTableStandard();
	}
	
	private void setTableStandard() {
		// TODO Auto-generated method stub
//		tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tabView.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tabView.getColumn("用户编号").setMinWidth(150);
		tabView.getColumn("用户名").setMinWidth(150);
		tabView.getColumn("开门时间").setMinWidth(150);
	}

	private void setTableData(List<ViewBean> viewBeans) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("用户编号");
		title.add("用户名");
		title.add("开门时间");
		
		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		for (ViewBean v : viewBeans) {
			row = new Vector();
			row.add(v.getUser().getUserId());
			row.add(v.getUser());
			row.add(v.getRecord().getOpenTime());
			//循环里再次调用dao层  会导致访问速度变慢
			data.add(row);
		}
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String startTime=dcStart.showDate.getText()+START;
		String endTime=dcEnd.showDate.getText()+END;
		String keyWord=txtKey.getText().trim().toUpperCase();
		initTable(viewBeanDao.getAllViewBeans(keyWord,startTime,endTime));
	}

	public void tabView_mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
			User user=(User)tabView.getValueAt(tabView.getSelectedRow(), 1);
			String startTime=dcStart.showDate.getText();
			String endTime=dcEnd.showDate.getText();
			new UserChartFrame(this,"人员开门次数统计",true, user, startTime, endTime);
		}
	}
}
