package cn.com.views;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.listeners.MainFrame_tabView_MouseListener;

public class FloderCenterPanel extends JPanel {
	TopPanel tp;
	JTable tabView;
	DefaultTableModel dtmView;
	JScrollPane snpView;
	MainFrame mf;

	File path;
	String currentPath;// 至关重要的一点 用来记录当前路径

	public FloderCenterPanel(TopPanel tp, File path, MainFrame mf) {
		this.mf = mf;
		this.tp = tp;
		tabView = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		snpView = new JScrollPane();
		this.path = path;
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(200, 35, 600, 480);
		setTableData(path);
		setTableStandard();
		tabView.setShowGrid(false);//取消表格边界
		snpView.getViewport().add(tabView);
		snpView.setBounds(0, 0, 600, 480);

		tabView.addMouseListener(new MainFrame_tabView_MouseListener(this.mf));

		this.add(snpView);
	}

	public void setTableStandard() {
		tabView.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tabView.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tabView.getColumn("修改日期").setMinWidth(200);
		tabView.getColumn("名称").setMinWidth(200);
		tabView.getColumn("大小").setMinWidth(100);
		tabView.getColumn("类型").setMinWidth(100);
	}

	public boolean setTableData(File path) {
		Vector<String> titles = new Vector<String>();
		titles.add("名称");
		titles.add("大小");
		titles.add("类型");
		titles.add("修改日期");
		dtmView = new DefaultTableModel(null, titles);
		String strPath = path.getAbsolutePath();
		if (path.isDirectory() == false) {
			JOptionPane.showMessageDialog(this, "此路径不存在，或无此文件");
			return false;
		}
		currentPath = path.getAbsolutePath();
		tp.cmbView.removeAllItems();
		File[] roots = File.listRoots();
		for (int i = 0; i < roots.length; i++) {
			if(roots[i].isDirectory()){
				String rootPath = roots[i].getAbsolutePath();
				tp.cmbView.addItem(rootPath);
			}		
		}
		tp.cmbView.setSelectedItem(path.toString());
		dtmView.setRowCount(0);
		// 如果判断为非分区根目录，则添加 返回上级 一行
		if (strPath.split("\\\\").length > 1) {
			dtmView.addRow(new String[] { "返回上级", "", "", "" });
		}
		// 列出当前目录所有目录及文件
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isHidden()) {
				String name = files[i].getName();
				if (files[i].isDirectory()) {
					dtmView.addRow(new Object[] { name, "", "文件夹", "" });
				} else {
					if (name.lastIndexOf(".") != -1) {
						dtmView.addRow(new String[] {
								name.substring(0, name.lastIndexOf(".")),
								sizeFormat(files[i].length()),
								name.substring(name.lastIndexOf(".") + 1),
								new SimpleDateFormat("yyyy-MM-dd hh:mm")
										.format(new Date(files[i]
												.lastModified())) });
					} else {
						dtmView.addRow(new String[] {
								name,
								sizeFormat(files[i].length()),
								"",
								new SimpleDateFormat("yyyy-MM-dd hh:mm")
										.format(new Date(files[i]
												.lastModified())) });
					}
				}
			}
		}
		this.tabView.setModel(dtmView);
		return true;
	}

	private String sizeFormat(long length) {
		long kb;
		if (length < 1024) {
			return String.valueOf(length + "B");
		} else if ((kb = length / 1024) < 1024) {
			return (String.valueOf(kb) + "kB");
		} else if ((kb = length / 1024 / 1024) < 1024) {
			return (String.valueOf(kb) + "MB");
		} else {
			return length / 1024 / 1024 / 1024 + "GB";
		}
	}
}
