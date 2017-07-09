package cn.com.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import cn.com.beans.Disk;
import cn.com.global.Global;

public class MainFrame extends JFrame {
	JPanel pnlMain;
	diskCenterPanel dcp;
	FloderCenterPanel fcp;
	TopPanel tp;
	ButtomPanel bp;

	String currentPath;
	int preNodeLevel;

	public MainFrame() {
		pnlMain = new JPanel(null);
		init();
	}

	private void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		Global.setCenterByWindow(this);
		tp = new TopPanel(this);
		pnlMain.add(tp);
		dcp = new diskCenterPanel(this);
		pnlMain.add(dcp);
		bp = new ButtomPanel("", null, null);
		pnlMain.add(bp);
		pnlMain.add(new LeftPanel(this));
		this.add(pnlMain);
		this.setVisible(true);
	}

	public void disk_mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			for (Disk disk : dcp.diskes) {
				if (String.valueOf(e.getComponent())
						.contains(disk.getFileUrl())) {
					File file = new File(disk.getFileUrl().substring(5, 7)
							+ "\\");
					if(dcp!=null){
						pnlMain.remove(dcp);
					}			
					fcp = new FloderCenterPanel(tp, file, this);
					pnlMain.add(fcp);
					pnlMain.repaint();
				}
			}
		}
	}

	public void disk_mouseEntered(MouseEvent e) {
		for (int i = 0; i < dcp.lblDiskes.size(); i++) {
			if (dcp.lblDiskes.get(i) == e.getComponent()) {
				dcp.lblDiskes.get(i).setBorder(
						BorderFactory.createLineBorder(Color.black));
				if(bp!=null){
					pnlMain.remove(bp);
				}	
				String fileUrl = dcp.diskes.get(i).getFileUrl().substring(5, 7)
						+ "\\";
				File file = new File(fileUrl);
				bp = new ButtomPanel(dcp.lblDiskes.get(i).getText(), file
						.getTotalSpace(), file.getUsableSpace());
				pnlMain.add(bp);
				pnlMain.repaint();
				pnlMain.updateUI();
			}
		}
	}

	public void disk_mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		for (JLabel temp : dcp.lblDiskes) {
			if (temp == e.getComponent()) {
				temp.setBorder(new EmptyBorder(0, 0, 0, 0));
			}
		}
	}

	public void tabView_mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = ((JTable) e.getSource()).getSelectedRow();
			if (((JTable) e.getSource()).getValueAt(row, 2).toString().equals(
					"文件夹")) {
				File file=new File(fcp.currentPath
						+ "\\"
						+ ((JTable) e.getSource()).getValueAt(row, 0)
								.toString());;
				fcp.setTableData(file);
				fcp.setTableStandard();
			} else if (((JTable) e.getSource()).getValueAt(row, 0).toString()
					.equals("返回上级")) {
				fcp.setTableData(new File(fcp.currentPath).getParentFile());
				fcp.setTableStandard();
			}
		}
	}

	public void btnBack_actionPerformed(ActionEvent e) {
		if (fcp != null) {
			if (null != fcp.currentPath) {
				File file = new File(fcp.currentPath);
				if (file.getParentFile() != null) {
					fcp.setTableData(file.getParentFile());
					fcp.setTableStandard();
				} else {
					pnlMain.remove(fcp);
					dcp = new diskCenterPanel(this);
					pnlMain.add(dcp);
					pnlMain.repaint();
					pnlMain.updateUI();
				}
			}
		}
	}

	public void treeView_valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
				.getLastPathComponent();
		String value = node.toString();// 得到所选节点的值
		if (!node.isRoot()) {
			if (((DefaultMutableTreeNode)node.getParent()).isRoot()) {
				currentPath = String.valueOf(e.getPath()).substring(11, 13)
						+ "\\";
			} else {
				// 判断是否是同一级别
				int nowNodeLevel = node.getLevel();
				if (nowNodeLevel == preNodeLevel) {
					String parentPath = new File(currentPath).getParentFile()
							.getAbsolutePath();
					currentPath = parentPath + "\\" + value;
				} else {
					currentPath = currentPath + "\\" + value;
					preNodeLevel = nowNodeLevel;
				}
			}
			File f = new File(currentPath);
			addDataToTree(node, f);
		}else{
			if(fcp!=null){
				pnlMain.remove(fcp);
			}	
			dcp = new diskCenterPanel(this);
			pnlMain.add(dcp);
			pnlMain.repaint();
			pnlMain.updateUI();
		}
	}

	public void addDataToTree(DefaultMutableTreeNode node, File f) {
		if (f.isDirectory()) {
			if(dcp!=null){
				pnlMain.remove(dcp);
			}	
			if(fcp!=null){
				pnlMain.remove(fcp);
			}
			fcp = new FloderCenterPanel(tp, f, this);
			pnlMain.add(fcp);
			pnlMain.repaint();
			pnlMain.updateUI();
			
			fcp.setTableData(f);
			fcp.setTableStandard();
			currentPath = f.getAbsolutePath();// 很关键 置回
			
			File[] files = f.listFiles();
			for (File file : files) {
				//判断是否为系统隐藏文件
				if(!file.isHidden()){
					DefaultMutableTreeNode temp = new DefaultMutableTreeNode(file
							.getName());
					node.add(temp);
				}		
			}
		}
	}

	public void btnForward_actionPerformed(ActionEvent e) {
		String path=(String)tp.cmbView.getSelectedItem();
		if(path.equals("")){
			if(fcp!=null){
				pnlMain.remove(fcp);
				dcp = new diskCenterPanel(this);
				pnlMain.add(dcp);
				pnlMain.repaint();
				pnlMain.updateUI();
			}	
		}else{
			try{
				File file=new File(path);
				if(file.isDirectory()){
					if(fcp==null){
						pnlMain.remove(dcp);
						fcp = new FloderCenterPanel(tp, file, this);
						pnlMain.add(fcp);
						pnlMain.repaint();
						pnlMain.updateUI();
						fcp.setTableData(file);
						fcp.setTableStandard();
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "路径不存在或者为不可打开文件", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, "路径非法", "错误",
						JOptionPane.ERROR_MESSAGE);
			}		
		}	
	}
}
