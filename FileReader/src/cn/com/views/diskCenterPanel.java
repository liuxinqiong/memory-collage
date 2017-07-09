package cn.com.views;

import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cn.com.beans.Disk;
import cn.com.listeners.MainFrame_disk_MouseListener;

public class diskCenterPanel extends JPanel{
	JScrollPane snpView;
	List<JLabel> lblDiskes;
	List<Disk> diskes;
	MainFrame mf;
	
	public static String imageUrl="images/disk.png";
	
	public diskCenterPanel(MainFrame mf){
		this.mf=mf;
		this.setLayout((new FlowLayout(FlowLayout.LEFT,25,10)));	
		snpView=new JScrollPane();
		lblDiskes=new ArrayList<JLabel>();
		diskes=new ArrayList<Disk>();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setBounds(200, 35, 600, 480);
		File[] files=File.listRoots();
		for (File file : files) {
			if(file.isDirectory()){
				String fileUrl=file.getAbsolutePath().substring(0,file.getPath().length()-1);
				Disk disk=new Disk(fileUrl, imageUrl);
				diskes.add(disk);
				JLabel label=disk.getDisk();
				lblDiskes.add(label);
			}		
		}
		
		for (JLabel lblDisk : lblDiskes) {
			this.add(lblDisk);
			lblDisk.addMouseListener(new MainFrame_disk_MouseListener(this.mf));
		}
	}
}
