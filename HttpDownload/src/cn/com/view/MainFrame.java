package cn.com.view;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.com.util.Global;
import cn.com.util.HttpUtil;

public class MainFrame extends JFrame implements ActionListener{
	JPanel pnlMain;
	JLabel lblURL;
	JTextField txtURL;
	JButton btnSure;
	JPanel pnlResInfo;
	JLabel lblSize;
	JLabel lblSizeInfo;
	JLabel lblFormat;
	JLabel lblFormatInfo;
	JButton btnPath;
	JLabel lblPath;
	JButton btnDownload;
	public MainFrame(){
		pnlMain=new JPanel(null);
		lblURL=new JLabel("URL");
		txtURL=new JTextField();
		btnSure=new JButton("确定");
		pnlResInfo=new JPanel(new GridLayout(2, 2, 10, 10));
		lblSize=new JLabel("文件大小：");
		lblSizeInfo=new JLabel();
		lblFormatInfo=new JLabel();
		lblFormat=new JLabel("文件格式：");
		btnPath=new JButton("选择路径");
		lblPath=new JLabel();
		btnDownload=new JButton("下载");
		init();
	}


	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(420, 300);
		Global.setCenterByWindow(this);
		lblURL.setBounds(5, 5, 45, 25);
		pnlMain.add(lblURL);
		txtURL.setBounds(30,5,300,25);
		pnlMain.add(txtURL);
		btnSure.setBounds(335, 5, 60, 25);
		pnlMain.add(btnSure);
		pnlResInfo.setBounds(5, 35, 400, 160);
		pnlResInfo.setBorder(BorderFactory.createTitledBorder("资源信息"));
		pnlResInfo.add(lblFormat);
		pnlResInfo.add(lblFormatInfo);
		pnlResInfo.add(lblSize);
		pnlResInfo.add(lblSizeInfo);		
		pnlMain.add(pnlResInfo);
		
		btnPath.setBounds(5, 210, 90, 25);
		pnlMain.add(btnPath);
		lblPath.setBounds(100, 210, 200, 25);
		lblPath.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlMain.add(lblPath);
		btnDownload.setBounds(335, 210, 60, 25);
		pnlMain.add(btnDownload);
		this.add(pnlMain);
		
		btnSure.addActionListener(this);
		btnDownload.addActionListener(this);
		btnPath.addActionListener(this);
		
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSure) {
			String urlStr = txtURL.getText();
			if (HttpUtil.isFilePath(urlStr)) {
				lblSizeInfo.setText(HttpUtil.getFileSize(urlStr)+"byte");
				lblFormatInfo.setText(HttpUtil.getFileType(urlStr));
			} else {
				JOptionPane.showMessageDialog(this, "对象不是文件，请重新输入下载地址","错误",JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnDownload) {
			if(lblPath.getText()==null||lblPath.getText().equals("")){
				btnSure.doClick();
				JOptionPane.showMessageDialog(this, "请先选择文件保存位置","提示",JOptionPane.INFORMATION_MESSAGE);
			}else{
				btnSure.doClick();
				String desPath=lblPath.getText();
				String urlStr=txtURL.getText();
				if(HttpUtil.downFile(urlStr, desPath)){
					JOptionPane.showMessageDialog(this, "下载成功","提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(this, "下载失败","提示",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getSource()==btnPath){
			FileDialog fd=new FileDialog(this,"下载",FileDialog.SAVE);
			fd.setVisible(true);
			String fileName=fd.getFile();
			String filePath=fd.getDirectory();
			if(fileName!=null&&filePath!=null){
				lblPath.setText(filePath+fileName);			
			}
		}
	}
}
