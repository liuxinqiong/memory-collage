package cn.com.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cn.com.uitls.CardReadUtil;

public class FunctionButtonPanel extends JPanel implements ActionListener,SerialPortEventListener,Runnable{
	JButton btnOpen;
	JButton btnClose;
	JButton btnUserMag;
	JButton btnMagMag;
	JButton btnRecordMag;
	JButton btnGradeMag;
	MainFrame mf;
	
	SerialPort serialPort;
	Thread readThread;
	CheckFrame cf;
	UserMagDialog umd;
	public FunctionButtonPanel(MainFrame mf){
		this.mf=mf;
		btnOpen=new JButton("打开串口");
		btnClose=new JButton("关闭串口");
		btnUserMag=new JButton("用户管理");
		btnMagMag=new JButton("管理员管理");
		btnRecordMag=new JButton("记录管理");
		btnGradeMag=new JButton("级别管理");
		this.setLayout(new GridLayout(6, 1, 20, 20));
		umd=new UserMagDialog(this.mf,"用户管理",true);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		this.add(btnOpen);
		this.add(btnClose);
		this.add(btnUserMag);
		this.add(btnMagMag);
		this.add(btnRecordMag);
		this.add(btnGradeMag);
		btnClose.setEnabled(false);
		btnOpen.addActionListener(this);
		btnClose.addActionListener(this);
		btnUserMag.addActionListener(this);
		btnMagMag.addActionListener(this);
		btnRecordMag.addActionListener(this);
		btnGradeMag.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type=e.getActionCommand();
		if(type.equals("打开串口")){
			String com=this.mf.mnp.cmbComs.getSelectedItem().toString();
			serialPort=CardReadUtil.openComm(com);
			if(serialPort!=null){
				JOptionPane.showMessageDialog(null, com+"已经打开，系统开始运作", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				btnOpen.setEnabled(false);
				btnClose.setEnabled(true);
				try {
					serialPort.addEventListener(this);
					serialPort.notifyOnDataAvailable(true);
					readThread = new Thread(this);
					readThread.start(); 
				} catch (TooManyListenersException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, com+"打开失败", "错误",
						JOptionPane.ERROR_MESSAGE);
			}
		}else if(type.equals("关闭串口")){
			CardReadUtil.close(serialPort);
			JOptionPane.showMessageDialog(null, "关闭成功", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			btnOpen.setEnabled(true);
			btnClose.setEnabled(false);
		}else if(type.equals("用户管理")){
			umd.setVisible(true);
		}else if(type.equals("管理员管理")){
			new MagMagDialog(mf, "管理员管理", true, this.mf.mag);
		}else if(type.equals("记录管理")){
			new RecordDialog(mf, "记录管理", true);
		}else if(type.equals("级别管理")){
			new GradeMagDialog(mf, "级别管理", true);
		}
	}
	/**
	 * ? 为何换一个usb口，5个字节的数据会分开读取
	 */
	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub
		int rate=Integer.parseInt(this.mf.mnp.cmbRates.getSelectedItem().toString());
		try {
			serialPort.setSerialPortParams(rate, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			String cardId=CardReadUtil.getCardId(serialPort);
			//一旦产生了Jdialog  后面的代码就会阻塞 关闭了才会执行
			if(cf!=null){
				cf.dispose();
			}
			if(this.mf.isFocused()){
				cf=new CheckFrame(this.mf, "检查用户",cardId);
			}else{
				this.umd.ump.cardId=cardId;
			}
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		
		}
	}
}
