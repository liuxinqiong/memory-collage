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
		
		//卡片选项按钮
		btnQuery = new JButton("查询统计",new ImageIcon(MainFrame.class.getResource("/image/query_32.png")));
		btnReader = new JButton("读者管理",new ImageIcon(MainFrame.class.getResource("/image/reader_32.png")));
		btnBorrow = new JButton("借阅管理",new ImageIcon(MainFrame.class.getResource("/image/borrow_32.png")));
		btnBook = new JButton("书刊管理",new ImageIcon(MainFrame.class.getResource("/image/book_32.png")));
		btnCard = new JButton("储值卡管理",new ImageIcon(MainFrame.class.getResource("/image/card_32.png")));
		btnSystem = new JButton("系统设置",new ImageIcon(MainFrame.class.getResource("/image/system_32.png")));
		
		//最小化和最大化按钮
		lbl_ = new JLabel("-");
		lblx = new JLabel("x");

		
		//四个快捷功能按钮初始化		
		btnJieyue = new JButton("借阅图书",new ImageIcon(MainFrame.class.getResource("/image/borrow_64.png")));
		btnGuihuan = new JButton("归还图书",new ImageIcon(MainFrame.class.getResource("/image/back_64.png")));
		btnXujie = new JButton("续借图书",new ImageIcon(MainFrame.class.getResource("/image/add_64.png")));
		btnJieQuery = new JButton("借阅查询",new ImageIcon(MainFrame.class.getResource("/image/query_64.png")));
		
		//面板初始化
		pnlB = new PnlBook();
		pnlBw = new PnlBorrow(user);
		pnlC = new PnlCard(user);
		pnlQ = new PnlQuery();	
		pnlR = new PnlReader(user);
		pnlS = new PnlSystem(user);
	
		init();
	}
	
	

	/**
	 * 面板布局初始化
	 */
	private void init() {
		// TODO Auto-generated method stub
		getContentPane().add(pnl1);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("欢迎使用图书借阅系统");
		//初始化面板选项按钮
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
		
		
		//最大最小化按钮初始化
		lbl_.setHorizontalAlignment(SwingConstants.CENTER);
		lblx.setHorizontalAlignment(SwingConstants.CENTER);
		Global.jlableInit(lbl_, new Color(123,191,234), Color.WHITE, 40, 0, 30, 30, 26, true);
		Global.jlableInit(lblx, new Color(123,191,234), Color.WHITE, 70, 0, 30, 30, 26, true);
		
		//将面板选项按钮压入北面板
		pnl3.add(btnQuery);
		pnl3.add(btnReader);
		pnl3.add(btnBorrow);
		pnl3.add(btnBook);
		pnl3.add(btnCard);
		pnl3.add(btnSystem);	
		
		//将最大化最小化标签压入面板
		//pnl4.add(lbl_);
		//pnl4.add(lblx);
		
		//将四大功能压入面板
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
		//将面板压入主界面
		pnl1.add(pnl2, BorderLayout.NORTH);				
		pnl2.add(pnl3, BorderLayout.CENTER);
		//pnl2.add(pnl4, BorderLayout.CENTER);
		pnl1.add(pnl5, BorderLayout.CENTER);
		pnl1.add(pnl6, BorderLayout.SOUTH);
		
		pnl5.add(pnlS,"系统设置");
		pnl5.add(pnlB,"书刊管理");
		pnl5.add(pnlBw,"借阅管理");
		pnl5.add(pnlC,"储值卡管理");
		pnl5.add(pnlQ,"查询统计");
		pnl5.add(pnlR,"读者管理");
		
		//设置面板的背景色
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
		
		//拖动窗口
		getContentPane().addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				loginPoint.x=e.getX();
				loginPoint.y=e.getY();
			}
		});
		//当用鼠标拖动窗体时
		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent e) {
				Point point=getLocation();
				setLocation(point.x + e.getX() - loginPoint.x, point.y + e.getY()- loginPoint.y);
			}
		});
		
		//设计窗口为无修饰边框
		this.setBounds(0, 0, 840, 620);//840 520
		Global.setCenterByWindow(this);
		
		//设置面板选项按钮监听器
		btnQuery.addActionListener(new btn_main_ActionListener(btnQuery, btnReader, btnBorrow, btnBook, btnCard, btnSystem,pnl5,cl,"查询统计"));
		btnReader.addActionListener(new btn_main_ActionListener(btnReader, btnQuery, btnBorrow, btnBook, btnCard, btnSystem,pnl5,cl,"读者管理"));
		btnBorrow.addActionListener(new btn_main_ActionListener(btnBorrow, btnReader,btnQuery , btnBook, btnCard, btnSystem,pnl5,cl,"借阅管理"));
		btnBook.addActionListener(new btn_main_ActionListener(btnBook, btnReader, btnBorrow, btnQuery , btnCard, btnSystem,pnl5,cl,"书刊管理"));
		btnCard.addActionListener(new btn_main_ActionListener(btnCard, btnReader, btnBorrow, btnBook, btnQuery , btnSystem,pnl5,cl,"储值卡管理"));
		btnSystem.addActionListener(new btn_main_ActionListener(btnSystem, btnReader, btnBorrow, btnBook, btnCard, btnQuery,pnl5,cl,"系统设置"));
		
		btnJieyue.addMouseListener(new btn_main_MouseListener(btnJieyue,"borrow1_64","borrow_64"));
		btnJieQuery.addMouseListener(new btn_main_MouseListener(btnJieQuery,"query1_64","query_64"));
		btnGuihuan.addMouseListener(new btn_main_MouseListener(btnGuihuan,"back1_64","back_64"));
		btnXujie.addMouseListener(new btn_main_MouseListener(btnXujie,"add1_64","add_64"));
		
		//设置最大化最小化按钮监听器
		//lbl_.addMouseListener(new lbl_main_MouseListener(lbl_,this));
		//lblx.addMouseListener(new lbl_main_MouseListener(lblx,this));
		this.addWindowListener(new MainFrame_WindowListener(this));
		
		//监听器
		
		btnJieyue.addActionListener(new MainFrame_btnJieyue_ActionListener(this));
		btnJieQuery.addActionListener(new MainFrame_btnJieyue_ActionListener(this));
		btnGuihuan.addActionListener(new MainFrame_btnJieyue_ActionListener(this));
		btnXujie.addActionListener(new MainFrame_btnJieyue_ActionListener(this));
		this.setResizable(false);
		this.setVisible(true);	
	}



	public void MainFrame_windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		int i=JOptionPane.showConfirmDialog(null, "确认退出系统？","提示",JOptionPane.YES_NO_OPTION);
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
