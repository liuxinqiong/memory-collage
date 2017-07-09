package cn.com.view;

import java.awt.AWTException;
import java.awt.FileDialog;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import cn.com.listeners.MainFrame_menuItemClear_ActionListener;
import cn.com.listeners.MainFrame_menuItemOpen_ActionListener;
import cn.com.listeners.MainFrame_menuItemSave_ActionListener;

public class MainFrame extends JFrame{

	MainPanel pnlMain;
	JMenuBar mbMain;
	JMenu menuFile;
	JMenuItem menuItemSave;
	JMenuItem menuItemOpen;
	JMenuItem menuItemClear;
	Image  image=new ImageIcon("images/img1.jpg").getImage();
	
	public MainFrame(){
		pnlMain=new MainPanel(image,new GridLayout());
		mbMain=new JMenuBar();
		menuFile=new JMenu("File");
		menuItemSave=new JMenuItem("Save");
		menuItemOpen=new JMenuItem("open");
		menuItemClear=new JMenuItem("clear");
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("位图");
		this.setSize(800, 450);
		initMenu();
		this.add(pnlMain);
		
		menuItemSave.addActionListener(new MainFrame_menuItemSave_ActionListener(this));
		menuItemOpen.addActionListener(new MainFrame_menuItemOpen_ActionListener(this));
		menuItemClear.addActionListener(new MainFrame_menuItemClear_ActionListener(this));
		this.setVisible(true);
	}

	private void initMenu() {
		// TODO Auto-generated method stub
		mbMain.add(menuFile);
		menuFile.add(menuItemSave);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemClear);
		this.setJMenuBar(mbMain);
	}

	public void menuItemSave_actionPerformed(ActionEvent e) throws AWTException, IOException {
		// TODO Auto-generated method stub
		FileDialog fd=new FileDialog(this,"另存为",FileDialog.SAVE);
		fd.setVisible(true);
		String fileName=fd.getFile();
		String filePath=fd.getDirectory();
		if(fileName!=null){
			String name =filePath+fileName;
			File file=new File(name);
			String format=fileName.substring(fileName.lastIndexOf(".")+1);
			BufferedImage image = new BufferedImage(pnlMain.getWidth(),pnlMain.getHeight(), BufferedImage.TYPE_INT_RGB); 
			Graphics2D g2 =(Graphics2D) image.getGraphics(); 
			pnlMain.paint(g2);
			ImageIO.write(image, format, file);
		}
		
	}

	public void menuItemOpen_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FileDialog fd=new FileDialog(this,"请选择要打开文件",FileDialog.LOAD);
		fd.setVisible(true);
		String fileName=fd.getFile();
		String filePath=fd.getDirectory();
		if(fileName!=null){
			String name =filePath+fileName;
			image=new ImageIcon(name).getImage();
			this.remove(pnlMain);
			pnlMain=new MainPanel(image,new GridLayout());
			this.add(pnlMain);
			pnlMain.updateUI();
		}
	}

	public void menuItemClear_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.remove(pnlMain);
		pnlMain=new MainPanel(image,new GridLayout());
		this.add(pnlMain);
		pnlMain.updateUI();
	}
}
