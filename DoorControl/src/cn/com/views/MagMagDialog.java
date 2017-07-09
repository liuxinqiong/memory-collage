package cn.com.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.dao.MagDao;
import cn.com.entity.Mag;
import cn.com.entity.User;
import cn.com.global.Global;
import cn.com.listeners.MagMagDialog_tabView_MouseListener;

public class MagMagDialog extends JDialog implements ActionListener{
	JPanel pnlMain;
	JPanel pnlNorth;
	JTable tabView;
	DefaultTableModel dtmView;
	JButton btnAdd;
	JButton btnDelete;
	JLabel lblMag;
	JScrollPane snpView;
	Mag mag;
	MagDao magDao;
	public MagMagDialog(MainFrame mf,String title,boolean model,Mag mag){
		super(mf, title, model);
		pnlMain=new JPanel(new BorderLayout());
		pnlNorth=new JPanel();
		tabView=new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		btnAdd=new JButton("新增");
		btnDelete=new JButton("删除");
		lblMag=new JLabel();
		snpView=new JScrollPane();
		this.mag=mag;
		magDao=new MagDao();
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(500,300);
		Global.setCenterByWindow(this);
		if(mag.getMagType()==1){
			lblMag.setText("您好，你是超级管理员，您具有对普通管理员的增加，删除，更新功能");
			pnlNorth.add(btnAdd);
			pnlNorth.add(btnDelete);
		}else{
			lblMag.setText("您好，你是普通管理员，您可以修改自己的个人资料");
		}
		pnlNorth.add(lblMag);
		pnlMain.add(pnlNorth,BorderLayout.NORTH);
		snpView.getViewport().add(tabView);
		pnlMain.add(snpView,BorderLayout.CENTER);
		this.add(pnlMain);
		initTable();
		
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		tabView.addMouseListener(new MagMagDialog_tabView_MouseListener(this));
		
		this.setVisible(true);
	}
	
	public void initTable(){
		setTableData(magDao.getAllMag());
		setTableStandard();
	}
	
	private void setTableStandard() {
		// TODO Auto-generated method stub
//		tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tabView.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tabView.getColumn("管理员编号").setMinWidth(150);
		tabView.getColumn("管理员名").setMinWidth(150);
		tabView.getColumn("管理员级别").setMinWidth(150);
	}

	private void setTableData(List<Mag> mags) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("管理员编号");
		title.add("管理员名");
		title.add("管理员级别");
		
		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		if(mag.getMagType()==1){
			for (Mag m : mags) {
				row = new Vector();
				row.add(m.getMagId());
				row.add(m);
				if(m.getMagType()==1){
					row.add("超级管理员");
				}else{
					row.add("普通管理员");
				}
				data.add(row);
			}
		}else{
			row = new Vector();
			row.add(mag.getMagId());
			row.add(mag);
			if(mag.getMagType()==1){
				row.add("超级管理员");
			}else{
				row.add("普通管理员");
			}
			data.add(row);
		}
		
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type=e.getActionCommand();
		if(type.equals("新增")){
			new MagDialog(this, "新增", true, mag, null);
		}else if(type.equals("删除")){
			if(tabView.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null,"请选择删除对象","错误",JOptionPane.ERROR_MESSAGE);
			}else{
				Mag mag=(Mag)tabView.getValueAt(tabView.getSelectedRow(), 1);
				int info=JOptionPane.showConfirmDialog(null, "真的要删除吗","提示",JOptionPane.YES_NO_OPTION);
				if(info==JOptionPane.YES_OPTION){
					boolean bool=magDao.deleteMag(mag);
					if(bool){
						JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						initTable();
					}else{
						JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	public void tabView_mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
			Mag m=(Mag)tabView.getValueAt(tabView.getSelectedRow(), 1);
			new MagDialog(this, "修改", true, mag, m);
		}
	}
}
