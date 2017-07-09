package cn.com.views;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtomPanel extends JPanel{
	String diskName;
	Long totalSpace;
	Long freeSpace;
	
	
	public ButtomPanel(String diskName,Long totalSpace,Long freeSpace){
		this.diskName=diskName;
		this.totalSpace=totalSpace;
		this.freeSpace=freeSpace;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setLayout((new FlowLayout(FlowLayout.LEFT)));	
		this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.setBounds(0, 515, 800, 60);
		if(!diskName.equals((""))){
			this.add(new JLabel(new ImageIcon("images/disk.png")));
			this.add(new JLabel(diskName));
		}
		if(totalSpace!=null){
			this.add(new JLabel("总大小："));
			totalSpace=totalSpace/(1024*1024*1024);
			this.add(new JLabel(totalSpace+"GB"));
		}
		if(freeSpace!=null){
			this.add(new JLabel("可用大小："));
			freeSpace=freeSpace/(1024*1024*1024);
			this.add(new JLabel(freeSpace+"GB"));
			this.add(new JLabel("已用大小："));
			this.add(new JLabel(totalSpace-freeSpace+"GB"));
		}
	}
}
