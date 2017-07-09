package cn.com.views.main;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.global.Global;
import cn.com.listeners.borrowHistorySearch.BorrowHistorySearchFrame_btnSure_ActionListener;
import cn.com.listeners.main.MainFrame_WindowListener;
import cn.com.listeners.main.MainFrame_btnJieyue_ActionListener;
import cn.com.listeners.main.btn_main_ActionListener;
import cn.com.listeners.main.btn_main_MouseListener;
import cn.com.listeners.main.lbl_main_MouseListener;
import cn.com.views.borrow.BorrowFrame;
import cn.com.views.borrowHistorySearch.BorrowHistorySearchFrame;
import cn.com.views.main.pnl.PnlBook;
import cn.com.views.main.pnl.PnlBorrow;
import cn.com.views.main.pnl.PnlCard;
import cn.com.views.main.pnl.PnlQuery;
import cn.com.views.main.pnl.PnlReader;
import cn.com.views.main.pnl.PnlSystem;

import javax.swing.SwingConstants;

public class MainFrame extends JFrame{
	private JPanel pnl1;
	private JPanel pnl2;
	private JPanel pnl3;
	private JPanel pnl4;
	private JPanel pnl5;
	private JPanel pnl6;
	
	private JButton btnQuery;
	private JButton btnReader;
	private JButton btnBorrow;
	private JButton btnBook;
	private JButton btnCard;
	private JButton btnSystem;
	
	private JButton btnJieyue;
	private JButton btnGuihuan;
	private JButton btnXujie;
	private JButton btnJieQuery;
	
	private JLabel lbl_;
	private JLabel lblx;
		
	private PnlBook pnlB;
	private PnlBorrow pnlBw;
	private PnlCard pnlC;
	private PnlQuery pnlQ;
	private PnlReader pnlR;
	private PnlSystem pnlS;
	
	private CardLayout cl = new CardLayout();
	static Point loginPoint;
	
	UserInfoBean user;
	
	public MainFrame(UserInfoBean user){
		this.user=user;
		
		loginPoint = new Point();
		
		pnl1 = new JPanel(new BorderLayout());
		pnl2 = new JPanel(new BorderLayout());
		pnl3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//pnl4 = new JPanel(null);
		pnl5 = new JPanel(cl);
		pnl6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
		
		//��Ƭѡ�ť
		btnQuery = new JButton("��ѯͳ��",new ImageIcon(MainFrame.class.getResource("/image/query_32.png")));
		btnReader = new JButton("���߹���",new ImageIcon(MainFrame.class.getResource("/image/reader_32.png")));
		btnBorrow = new JButton("���Ĺ���",new ImageIcon(MainFrame.class.getResource("/image/borrow_32.png")));
		btnBook = new JButton("�鿯����",new ImageIcon(MainFrame.class.getResource("/image/book_32.png")));
		btnCard = new JButton("��ֵ������",new ImageIcon(MainFrame.class.getResource("/image/card_32.png")));
		btnSystem = new JButton("ϵͳ����",new ImageIcon(MainFrame.class.getResource("/image/system_32.png")));
		
		//��С������󻯰�ť
		lbl_ = new JLabel("-");
		lblx = new JLabel("x");

		
		//�ĸ���ݹ��ܰ�ť��ʼ��		
		btnJieyue = new JButton("����ͼ��",new ImageIcon(MainFrame.class.getResource("/image/borrow_64.png")));
		btnGuihuan = new JButton("�黹ͼ��",new ImageIcon(MainFrame.class.getResource("/image/back_64.png")));
		btnXujie = new JButton("����ͼ��",new ImageIcon(MainFrame.class.getResource("/image/add_64.png")));
		btnJieQuery = new JButton("���Ĳ�ѯ",new ImageIcon(MainFrame.class.getResource("/image/query_64.png")));
		
		//����ʼ��
		pnlB = new PnlBook();
		pnlBw = new PnlBorrow(user);
		pnlC = new PnlCard(user);
		pnlQ = new PnlQuery();	
		pnlR = new PnlReader(user);
		pnlS = new PnlSystem(user);
	
		init();
	}
	
	

	/**
	 * ��岼�ֳ�ʼ��
	 */
	private void init() {
		// TODO Auto-generated method stub
		getContentPane().add(pnl1);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("��ӭʹ��ͼ�����ϵͳ");
		//��ʼ�����ѡ�ť
		Global.jbuttonInit(btnQuery,new Color(123,191,234),Color.WHITE,20,BorderFactory.createLineBorder(Color.WHITE, 2));
		Global.jbuttonInit(btnReader,new Color(123,191,234),Color.WHITE,20,BorderFactory.createLineBorder(Color.WHITE, 2));
		Global.jbuttonInit(btnBorrow,new Color(123,191,234),Color.WHITE,20,BorderFactory.createLineBorder(Color.WHITE, 2));
		Global.jbuttonInit(btnBook,new Color(123,191,234),Color.WHITE,20,BorderFactory.createLineBorder(Color.WHITE, 2));
		Global.jbuttonInit(btnCard,new Color(123,191,234),Color.WHITE,20,BorderFactory.createLineBorder(Color.WHITE, 2));
		Global.jbuttonInit(btnSystem,new Color(123,191,234),Color.WHITE,20,BorderFactory.createLineBorder(Color.WHITE, 2));
		
		Global.jbuttonInit(btnXujie,new Color(123,191,234),Color.WHITE,16,BorderFactory.createLineBorder(new Color(123,191,234), 2));
		Global.jbuttonInit(btnJieQuery,new Color(123,191,234),Color.WHITE,16,BorderFactory.createLineBorder(new Color(123,191,234), 2));
		Global.jbuttonInit(btnJieyue,new Color(123,191,234),Color.WHITE,16,BorderFactory.createLineBorder(new Color(123,191,234), 2));
		Global.jbuttonInit(btnGuihuan,new Color(123,191,234),Color.WHITE,16,BorderFactory.createLineBorder(new Color(123,191,234), 2));
		
		
		//�����С����ť��ʼ��
		lbl_.setHorizontalAlignment(SwingConstants.CENTER);
		lblx.setHorizontalAlignment(SwingConstants.CENTER);
		Global.jlableInit(lbl_, new Color(123,191,234), Color.WHITE, 40, 0, 30, 30, 26, true);
		Global.jlableInit(lblx, new Color(123,191,234), Color.WHITE, 70, 0, 30, 30, 26, true);
		
		//�����ѡ�ťѹ�뱱���
		pnl3.add(btnQuery);
		pnl3.add(btnReader);
		pnl3.add(btnBorrow);
		pnl3.add(btnBook);
		pnl3.add(btnCard);
		pnl3.add(btnSystem);	
		
		//�������С����ǩѹ�����
		//pnl4.add(lbl_);
		//pnl4.add(lblx);
		
		//���Ĵ���ѹ�����
		pnl6.add(btnJieyue);
		pnl6.add(btnGuihuan);
		pnl6.add(btnXujie);
		pnl6.add(btnJieQuery);
		
		btnJieyue.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnJieyue.setHorizontalTextPosition(SwingConstants.CENTER);
		btnJieQuery.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnJieQuery.setHorizontalTextPosition(SwingConstants.CENTER);
		btnXujie.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnXujie.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGuihuan.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGuihuan.setHorizontalTextPosition(SwingConstants.CENTER);
		//�����ѹ��������
		pnl1.add(pnl2, BorderLayout.NORTH);				
		pnl2.add(pnl3, BorderLayout.CENTER);
		//pnl2.add(pnl4, BorderLayout.CENTER);
		pnl1.add(pnl5, BorderLayout.CENTER);
		pnl1.add(pnl6, BorderLayout.SOUTH);
		
		pnl5.add(pnlS,"ϵͳ����");
		pnl5.add(pnlB,"�鿯����");
		pnl5.add(pnlBw,"���Ĺ���");
		pnl5.add(pnlC,"��ֵ������");
		pnl5.add(pnlQ,"��ѯͳ��");
		pnl5.add(pnlR,"���߹���");
		
		//�������ı���ɫ
		pnl1.setBackground(new Color(123,191,234));
		pnl2.setBackground(new Color(123,191,234));
		pnl3.setBackground(new Color(123,191,234));
		//pnl4.setBackground(new Color(123,191,234));
		pnl5.setBackground(new Color(123,191,234));
		pnlS.setBackground(new Color(123,191,234));
		pnlB.setBackground(new Color(123,191,234));
		pnlBw.setBackground(new Color(123,191,234));
		pnlC.setBackground(new Color(123,191,234));
		pnlQ.setBackground(new Color(123,191,234));
		pnlR.setBackground(new Color(123,191,234));
		pnl6.setBackground(new Color(123,191,234));
		
		//�϶�����
		getContentPane().addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
				loginPoint.x=e.getX();
				loginPoint.y=e.getY();
			}
		});
		//��������϶�����ʱ
		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent e) {
				Point point=getLocation();
				setLocation(point.x + e.getX() - loginPoint.x, point.y + e.getY()- loginPoint.y);
			}
		});
		
		//��ƴ���Ϊ�����α߿�
		this.setBounds(0, 0, 840, 620);//840 520
		Global.setCenterByWindow(this);
		
		//�������ѡ�ť������
		btnQuery.addActionListener(new btn_main_ActionListener(btnQuery, btnReader, btnBorrow, btnBook, btnCard, btnSystem,pnl5,cl,"��ѯͳ��"));
		btnReader.addActionListener(new btn_main_ActionListener(btnReader, btnQuery, btnBorrow, btnBook, btnCard, btnSystem,pnl5,cl,"���߹���"));
		btnBorrow.addActionListener(new btn_main_ActionListener(btnBorrow, btnReader,btnQuery , btnBook, btnCard, btnSystem,pnl5,cl,"���Ĺ���"));
		btnBook.addActionListener(new btn_main_ActionListener(btnBook, btnReader, btnBorrow, btnQuery , btnCard, btnSystem,pnl5,cl,"�鿯����"));
		btnCard.addActionListener(new btn_main_ActionListener(btnCard, btnReader, btnBorrow, btnBook, btnQuery , btnSystem,pnl5,cl,"��ֵ������"));
		btnSystem.addActionListener(new btn_main_ActionListener(btnSystem, btnReader, btnBorrow, btnBook, btnCard, btnQuery,pnl5,cl,"ϵͳ����"));
		
		btnJieyue.addMouseListener(new btn_main_MouseListener(btnJieyue,"borrow1_64","borrow_64"));
		btnJieQuery.addMouseListener(new btn_main_MouseListener(btnJieQuery,"query1_64","query_64"));
		btnGuihuan.addMouseListener(new btn_main_MouseListener(btnGuihuan,"back1_64","back_64"));
		btnXujie.addMouseListener(new btn_main_MouseListener(btnXujie,"add1_64","add_64"));
		
		//���������С����ť������
		//lbl_.addMouseListener(new lbl_main_MouseListener(lbl_,this));
		//lblx.addMouseListener(new lbl_main_MouseListener(lblx,this));
		this.addWindowListener(new MainFrame_WindowListener(this));
		
		//������
		
		btnJieyue.addActionListener(new MainFrame_btnJieyue_ActionListener(this));
		btnJieQuery.addActionListener(new MainFrame_btnJieyue_ActionListener(this));
		btnGuihuan.addActionListener(new MainFrame_btnJieyue_ActionListener(this));
		btnXujie.addActionListener(new MainFrame_btnJieyue_ActionListener(this));
		this.setResizable(false);
		this.setVisible(true);	
	}



	public void MainFrame_windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		int i=JOptionPane.showConfirmDialog(null, "ȷ���˳�ϵͳ��","��ʾ",JOptionPane.YES_NO_OPTION);
		if(i==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}



	public void btnJieyue_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnJieQuery)){
			new BorrowHistorySearchFrame();
		}else if(e.getSource().equals(btnGuihuan)){
			new BorrowFrame("GuiHuan", user);
		}else if(e.getSource().equals(btnXujie)){
			new BorrowFrame("XuJie", user);
		}else if(e.getSource().equals(btnJieyue)){
			new BorrowFrame("JieShu", user);
		}
	}
}
