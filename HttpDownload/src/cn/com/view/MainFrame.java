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
		btnSure=new JButton("ȷ��");
		pnlResInfo=new JPanel(new GridLayout(2, 2, 10, 10));
		lblSize=new JLabel("�ļ���С��");
		lblSizeInfo=new JLabel();
		lblFormatInfo=new JLabel();
		lblFormat=new JLabel("�ļ���ʽ��");
		btnPath=new JButton("ѡ��·��");
		lblPath=new JLabel();
		btnDownload=new JButton("����");
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
		pnlResInfo.setBorder(BorderFactory.createTitledBorder("��Դ��Ϣ"));
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
				JOptionPane.showMessageDialog(this, "�������ļ����������������ص�ַ","����",JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnDownload) {
			if(lblPath.getText()==null||lblPath.getText().equals("")){
				btnSure.doClick();
				JOptionPane.showMessageDialog(this, "����ѡ���ļ�����λ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			}else{
				btnSure.doClick();
				String desPath=lblPath.getText();
				String urlStr=txtURL.getText();
				if(HttpUtil.downFile(urlStr, desPath)){
					JOptionPane.showMessageDialog(this, "���سɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(this, "����ʧ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getSource()==btnPath){
			FileDialog fd=new FileDialog(this,"����",FileDialog.SAVE);
			fd.setVisible(true);
			String fileName=fd.getFile();
			String filePath=fd.getDirectory();
			if(fileName!=null&&filePath!=null){
				lblPath.setText(filePath+fileName);			
			}
		}
	}
}
