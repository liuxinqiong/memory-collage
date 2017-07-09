package cn.com.views;

import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import cn.com.listeners.MainFrame_treeView_SelectionListener;

public class LeftPanel extends JPanel {
	MainFrame mf;
	JScrollPane snpView;
	JTree treeView;
	DefaultTreeModel dtmView;
	public LeftPanel(MainFrame mf) {
		this.mf=mf;
		snpView=new JScrollPane();
		treeView=new JTree();
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(5, 35, 190, 480);
		setTreeData();
		snpView.getViewport().add(treeView);
		snpView.setBounds(0, 0, 190, 480);
		treeView.addTreeSelectionListener(new MainFrame_treeView_SelectionListener(this.mf));
		this.add(snpView);
	}

	private void setTreeData() {
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("计算机");
		dtmView=new DefaultTreeModel(root);
		File[] file=File.listRoots();
    	for (File file2 : file) {
    		//得到盘符
    		if(file2.isDirectory()){
    			String fileName=file2.getPath().substring(0, file2.getPath().length()-1);
    			fileName="本地磁盘("+fileName+")";
    			DefaultMutableTreeNode temp=new DefaultMutableTreeNode(fileName);
    			root.add(temp);
    		}		
		}
    	treeView.setModel(dtmView);
	}
}
