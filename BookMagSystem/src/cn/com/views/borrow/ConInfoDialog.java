package cn.com.views.borrow;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.daos.borrowInfo.BorrowInfoDAOImp;
import cn.com.daos.borrowInfo.BorrowInfoDAOInf;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;




public class ConInfoDialog extends JDialog {
	private JPanel pnlMian;
	private JTable tabView;
	private JScrollPane snpView;
	private ReaderInfoDAOInf ridi;
	private DefaultTableModel dtmView;
	public static ReaderInfoBean rib;
	public BorrowFrame bf;
	public JTextField tfd;
	BorrowInfoDAOInf dao;
	
	public ConInfoDialog(Frame frame,String title,boolean modl){
		super(frame,title,modl);
		this.bf = (BorrowFrame)frame;
		tfd = new JTextField();
		initObject();
		dao=new BorrowInfoDAOImp();
		init();
	}
	private void initObject() {
		// TODO Auto-generated method stub
		
		rib = new ReaderInfoBean();
		ridi = new ReaderInfoDAOImp();
		pnlMian = new JPanel(new GridLayout());
		snpView = new JScrollPane();
		tabView = new JTable(){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};	
	}
	private void init() {
		// TODO Auto-generated method stub
		this.setSize(350, 200);
		this.setResizable(false);
		this.setUndecorated(true);
		snpView.getViewport().add(tabView);
		tabView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tabView_mouseClicked(e);
			}
		});		
		pnlMian.add(snpView);	
		this.add(pnlMian);
		setTableDate();
	}
	
	protected void tabView_mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2){
			int row = tabView.getSelectedRow();
			ReaderInfoBean rib = (ReaderInfoBean)tabView.getValueAt(row, 0); 
			this.bf.tfd1.setText(rib.getReaderId() + "");
			this.bf.labelReaderName.setText(rib.getReaderName());
			this.bf.labelSex.setText(rib.getReaderSex());
			ReaderInfoDAOInf r = new ReaderInfoDAOImp();
		//	borrowInfoDaoInf rd = new borrowInfoImp();
			List<ReaderTypeInfoBean> list = r.getAllReaderTypeInfo();
			for(ReaderTypeInfoBean rtib : list){
				if(rtib.getReaderTypeId() == rib.getReaderTypeId()){
					this.bf.labelType.setText(rtib.getReaderTypeName());
					this.bf.labelMaxNum.setText(rtib.getMaxNum()+"");
					this.bf.labelHasNum.setText(dao.getBorrowedNum(rib)+"");
				}
			}
			
			
			this.dispose();					
		}
	}
	public void setTableDate(){
		Vector<String> title = new Vector<String>();
		title.add("读者姓名");
		title.add("读者编号");		
		title.add("联系电话");
		title.add("性别");
		Vector<Vector> data = new Vector<Vector>();
		Vector<Object> row = null;
		for(ReaderInfoBean hrb : ridi.getAllReaderInfo()) {
			row = new Vector<Object>();
			row.add(hrb);
			row.add(hrb.getReaderId());
			row.add(hrb.getReaderTel());
			row.add(hrb.getReaderSex());
			data.add(row);
		}
		dtmView = new DefaultTableModel(data,title);
		tabView.setModel(dtmView);
	}
}
