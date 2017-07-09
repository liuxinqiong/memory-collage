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
		btnOpen=new JButton("�򿪴���");
		btnClose=new JButton("�رմ���");
		btnUserMag=new JButton("�û�����");
		btnMagMag=new JButton("����Ա����");
		btnRecordMag=new JButton("��¼����");
		btnGradeMag=new JButton("�������");
		this.setLayout(new GridLayout(6, 1, 20, 20));
		umd=new UserMagDialog(this.mf,"�û�����",true);
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
		if(type.equals("�򿪴���")){
			String com=this.mf.mnp.cmbComs.getSelectedItem().toString();
			serialPort=CardReadUtil.openComm(com);
			if(serialPort!=null){
				JOptionPane.showMessageDialog(null, com+"�Ѿ��򿪣�ϵͳ��ʼ����", "��ʾ",
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
				JOptionPane.showMessageDialog(null, com+"��ʧ��", "����",
						JOptionPane.ERROR_MESSAGE);
			}
		}else if(type.equals("�رմ���")){
			CardReadUtil.close(serialPort);
			JOptionPane.showMessageDialog(null, "�رճɹ�", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			btnOpen.setEnabled(true);
			btnClose.setEnabled(false);
		}else if(type.equals("�û�����")){
			umd.setVisible(true);
		}else if(type.equals("����Ա����")){
			new MagMagDialog(mf, "����Ա����", true, this.mf.mag);
		}else if(type.equals("��¼����")){
			new RecordDialog(mf, "��¼����", true);
		}else if(type.equals("�������")){
			new GradeMagDialog(mf, "�������", true);
		}
	}
	/**
	 * ? Ϊ�λ�һ��usb�ڣ�5���ֽڵ����ݻ�ֿ���ȡ
	 */
	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub
		int rate=Integer.parseInt(this.mf.mnp.cmbRates.getSelectedItem().toString());
		try {
			serialPort.setSerialPortParams(rate, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			String cardId=CardReadUtil.getCardId(serialPort);
			//һ��������Jdialog  ����Ĵ���ͻ����� �ر��˲Ż�ִ��
			if(cf!=null){
				cf.dispose();
			}
			if(this.mf.isFocused()){
				cf=new CheckFrame(this.mf, "����û�",cardId);
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
