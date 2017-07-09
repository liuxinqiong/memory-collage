package cn.com.views;

import java.awt.BorderLayout;
import java.awt.Dialog;
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

import cn.com.dao.GradeDao;
import cn.com.dao.UserDao;
import cn.com.entity.Grade;
import cn.com.entity.Mag;
import cn.com.global.Global;
import cn.com.listeners.GradeMagDialog_tabView_MouseListener;
import cn.com.listeners.MagMagDialog_tabView_MouseListener;

public class GradeMagDialog extends JDialog implements ActionListener {
	JPanel pnlMain;
	JPanel pnlNorth;
	JTable tabView;
	DefaultTableModel dtmView;
	JButton btnAdd;
	JButton btnDelete;
	JScrollPane snpView;
	GradeDao gradeDao;
	UserDao userDao;

	public GradeMagDialog(MainFrame mf, String title, boolean modal) {
		super(mf, title, modal);
		pnlMain = new JPanel(new BorderLayout());
		pnlNorth = new JPanel();
		tabView = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		btnAdd = new JButton("新增");
		btnDelete = new JButton("删除");
		snpView = new JScrollPane();
		gradeDao = new GradeDao();
		userDao=new UserDao();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(500, 300);
		Global.setCenterByWindow(this);
		pnlNorth.add(btnAdd);
		pnlNorth.add(btnDelete);

		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		snpView.getViewport().add(tabView);
		pnlMain.add(snpView, BorderLayout.CENTER);
		this.add(pnlMain);
		initTable();
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		tabView.addMouseListener(new GradeMagDialog_tabView_MouseListener(this));

		this.setVisible(true);
	}

	public void initTable() {
		setTableData(gradeDao.getAllGrade());
		setTableStandard();
	}

	private void setTableStandard() {
		// TODO Auto-generated method stub
		// tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tabView.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tabView.getColumn("级别编号").setMinWidth(150);
		tabView.getColumn("级别名称").setMinWidth(150);
		tabView.getColumn("开门次数").setMinWidth(150);
	}

	private void setTableData(List<Grade> grades) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("级别编号");
		title.add("级别名称");
		title.add("开门次数");
		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		for (Grade g : grades) {
			row = new Vector();
			row.add(g.getGradeId());
			row.add(g);
			row.add(g.getGradeCount());
			data.add(row);
		}
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type = e.getActionCommand();
		if (type.equals("新增")) {
			new GradeDialog(this, "新增", true, null);
		} else if (type.equals("删除")) {
			if (tabView.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "请选择删除对象", "错误",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Grade grade = (Grade) tabView.getValueAt(tabView.getSelectedRow(), 1);
				int info = JOptionPane.showConfirmDialog(null, "真的要删除吗", "提示",
						JOptionPane.YES_NO_OPTION);
				if (info == JOptionPane.YES_OPTION) {
					//需要判断下面存在用户
					int count=userDao.existNum(grade.getGradeId());
					if(count>0){
						JOptionPane.showMessageDialog(null, "该级别下存在"+count+"个用户，不能删除", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					boolean bool = gradeDao.deleteGrade(grade);
					if (bool) {
						JOptionPane.showMessageDialog(null, "删除成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						initTable();
					} else {
						JOptionPane.showMessageDialog(null, "删除失败", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	public void tabView_mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			Grade grade = (Grade) tabView.getValueAt(tabView.getSelectedRow(), 1);
			new GradeDialog(this, "修改", true, grade);
		}
	}
}
