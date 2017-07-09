package cn.com.views.tyb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.com.beans.tyb.BookBorrBean;
import cn.com.daos.journaInfo.BookBorrDAO;
import cn.com.global.DateChooser;


public class BorrowInfodemo extends JFrame {
	private JPanel contentPane;
	PayManagedemo frame;
	static String str;
	static String txt;
	static String txt1;
	static JPanel pan;

	/**
	 * Create the frame.
	 */
	public BorrowInfodemo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("图书借阅统计");
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton button = new JButton("关 闭");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(button);
		
		contentPane.add(BorderLayout.NORTH,createNorthTablePane());
		
		contentPane.add(BorderLayout.CENTER,createTablePane(null,null,null));
		setVisible(true);
	}
	
	private Component createNorthTablePane() {
		// 构建北部布局
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,30,15));
		p.setBackground(Color.cyan);
		JLabel jlb = new JLabel("图书类型:");
		JLabel jlb1 = new JLabel("时间范围:");
		
		jlb.setFont(new Font("宋体", Font.HANGING_BASELINE, 15));
		jlb1.setFont(new Font("宋体", Font.HANGING_BASELINE, 15));	
		
		final JButton jb = new JButton("统计");
		final JComboBox jt = new JComboBox();
		
		final DateChooser date = new DateChooser();
		final DateChooser date1 = new DateChooser();
		
		GetbookType gt = new GetbookType();
		String[] list = gt.init();
		int j = gt.getlength();//得到list有效数组长度
		
	    

	    for(int i = 0; i < j; i++){
	    	jt.addItem(list[i]);
	    }
		
		p.add(jlb);
		p.add(jt);
		p.add(jlb1);
		p.add(date);
		p.add(date1);
		p.add(jb);

		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			     str = (String) jt.getSelectedItem();
			     txt = date.showDate.getText();
			     txt1 = date1.showDate.getText();
			     pan.removeAll();
			     contentPane.add(BorderLayout.CENTER,createTablePane(str,txt,txt1));//重新建立表格布局
			     contentPane.validate();//使表格有效化
			}
			
		});
		
		return p;
	}
	
	private  JPanel createTablePane(String str,String txt,String txt1) {

	    pan = new JPanel(new BorderLayout());
		pan.add(createTable(str));//添加表格组件
		return pan;
	}

	
	private JScrollPane createTable(String str) {
		//一维数组表头
		String[] title = {"图书名称","借出次数","总册数","作者","图书类型","出版商"};
		//二维数组表数据
		
		List<BookBorrBean> 	bb = BookBorrDAO.getBookBorrBean(str,txt,txt1);
		JTable table = new JTable(getBBdata(bb),title);
			
		table.getTableHeader().setReorderingAllowed(false);   //不可整列移动     
		table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
		JScrollPane p = new JScrollPane();
        p.add(table);
		p.setViewportView(table);
		
		return p;
	}
	
	
	private Object[][] getBBdata(List<BookBorrBean> pb) {
		// 图书借阅信息数据包装
		Object[][] data =new Object[pb.size()][6];
		for(int i=0;i<pb.size();i++){
			Object emp[] = new Object[6];
			
			emp[0]=((BookBorrBean) pb.get(i)).getBookName();
			emp[1]=((BookBorrBean) pb.get(i)).getBorroutcount();
			emp[2]=((BookBorrBean) pb.get(i)).getBookTotalNum();
			emp[3]=((BookBorrBean) pb.get(i)).getWriter();
			emp[4]=((BookBorrBean) pb.get(i)).getBookTypeName();
			emp[5]=((BookBorrBean) pb.get(i)).getPublisher();
			data[i]=emp;
		}	
		return data;
	}

}
