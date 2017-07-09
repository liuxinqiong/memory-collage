package cn.com.views.main.pnl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.com.bookInfoSearch.Searchbook;
import cn.com.global.Global;
import cn.com.listeners.main.PnlQuery_btn3_ActionListener;
import cn.com.views.borrowHistorySearch.BorrowHistorySearchFrame;
import cn.com.views.tyb.PayManagedemo;
import cn.com.views.tyb.PresonBookBorrdemo;
import cn.com.views.tyb.bookLosedemo;
import cn.com.views.tyb.keeyMoneydemo;


public class PnlQuery extends JDesktopPane{
	
	private JButton btn1;
//	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	
	private JLabel lbl1;
//	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JLabel lbl6;
	private JLabel lbl7;

		
	public PnlQuery(){
		initObject();
		init();
	}
	
	private void initObject() {
		// TODO Auto-generated method stub
		btn1 = new JButton("�鿯��Ϣ��ѯ");
//		btn2 = new JButton("ͼ����Ĳ�ѯ");
		btn3 = new JButton("������ʷ��ѯ");
		btn4 = new JButton("�տ��¼��ѯ");
		btn5 = new JButton("����Ѻ���ѯ");
		btn6 = new JButton("ͼ�鶪ʧ�嵥");
		btn7 = new JButton("������Ϣͳ��");
		
		lbl1 = new JLabel("��ѯͼ��������");
	//	lbl2 = new JLabel("��ѯ���ĵ���û�й黹�Ľ��ļ�¼");
		lbl3 = new JLabel("��ѯ���ߵĽ�����ʷ");
		lbl4 = new JLabel("��ѯ�����ڡ���ʧ���շѼ�¼");
		lbl5 = new JLabel("��ѯ�йض���Ѻ����Ϣ");
		lbl6 = new JLabel("��ѯͼ�鶪ʧ��¼");
		lbl7 = new JLabel("ͳ��ͼ�����߽���Ƶ�ʱȽϸߵ���Ϣ");
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
			
		this.add(btn1);
	//	this.add(btn2);
		this.add(btn3);
		this.add(btn4);
		this.add(btn5);
		this.add(btn6);
		this.add(btn7);
		
		this.add(lbl1);
//		this.add(lbl2);
		this.add(lbl3);
		this.add(lbl4);
		this.add(lbl5);
		this.add(lbl6);
		this.add(lbl7);
		
		btn1.setBounds(100, 30, 130, 28);
	//	btn2.setBounds(100, 90, 130, 28);
		btn3.setBounds(100, 90, 130, 28);
		btn4.setBounds(100, 150, 130, 28);
		btn5.setBounds(100, 210, 130, 28);
		btn6.setBounds(100, 270, 130, 28);
		btn7.setBounds(100, 330, 130, 28);

		lbl1.setBounds(350, 34, 400, 20);
	//	lbl2.setBounds(350, 94, 400, 20);
		lbl3.setBounds(350, 94, 400, 20);
		lbl4.setBounds(350, 154, 400, 20);
		lbl5.setBounds(350, 214, 400, 20);
		lbl6.setBounds(350, 274, 400, 20);
		lbl7.setBounds(350, 334, 400, 20);
		
		Global.jlableInit(lbl1, Color.WHITE, 18);
	//	Global.jlableInit(lbl2, Color.WHITE, 18);
		Global.jlableInit(lbl3, Color.WHITE, 18);
		Global.jlableInit(lbl4, Color.WHITE, 18);
		Global.jlableInit(lbl5, Color.WHITE, 18);
		Global.jlableInit(lbl6, Color.WHITE, 18);
		Global.jlableInit(lbl7, Color.WHITE, 18);
		
		btn1.setFont(new Font("΢���ź�", Font.PLAIN,16));
//		btn2.setFont(new Font("΢���ź�", Font.PLAIN,16));
		btn3.setFont(new Font("΢���ź�", Font.PLAIN,16));
		btn4.setFont(new Font("΢���ź�", Font.PLAIN,16));
		btn5.setFont(new Font("΢���ź�", Font.PLAIN,16));
		btn6.setFont(new Font("΢���ź�", Font.PLAIN,16));
		btn7.setFont(new Font("΢���ź�", Font.PLAIN,16));
		
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		btn3.addActionListener(new PnlQuery_btn3_ActionListener(this));
		btn4.addActionListener(new PnlQuery_btn3_ActionListener(this));
		btn5.addActionListener(new PnlQuery_btn3_ActionListener(this));
		btn6.addActionListener(new PnlQuery_btn3_ActionListener(this));
		btn7.addActionListener(new PnlQuery_btn3_ActionListener(this));
		btn1.addActionListener(new PnlQuery_btn3_ActionListener(this));
	}

	public void btn3_actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn3)){
		// TODO Auto-generated method stub
			new BorrowHistorySearchFrame();
		}else if(e.getSource().equals(btn4)){
			new PayManagedemo();
		}else if(e.getSource().equals(btn5)){
			new keeyMoneydemo();
		}else if(e.getSource().equals(btn6)){
			new bookLosedemo();
		}else if(e.getSource().equals(btn7)){
			new PresonBookBorrdemo();
		}else if(e.getSource().equals(btn1)){
			new Searchbook();
		}
	}
}
