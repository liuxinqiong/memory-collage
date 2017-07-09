package cn.com.views.tyb;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.com.beans.tyb.bookLoseBean;
import cn.com.daos.journaInfo.bookLoseDAO;
import cn.com.global.DateChooser;


public class bookLosedemo extends JFrame {

	private JPanel contentPane;
	private bookLosedemo frame;
	static String d;
	static String d2;
	static String str;
	static JPanel pan;

	
	public bookLosedemo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("图书丢失查询");
		setBounds(100, 100, 600, 400);
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(BorderLayout.CENTER,createTablePane(null,null,null));
		setVisible(true);
	}
	
	//北部布局
	private Component createNorthTablePane() {

		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,20,15));
		JLabel jlb = new JLabel("时间范围：");
		final DateChooser date = new DateChooser();
		final DateChooser date1 = new DateChooser();
		
		JButton jb = new JButton("查 询");
		
		final JTextField jtxt = new JTextField("请输入信息",10);
		
		p.add(jlb);
		p.add(date);
		p.add(date1);
		p.add(jtxt);
		p.add(jb);
		
		jb.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			     str = jtxt.getText();
				 d = date.showDate.getText();
				 d2 = date1.showDate.getText();
				 pan.removeAll();
				 contentPane.add(BorderLayout.CENTER,createTablePane(d,d2,str));//重新建立表格布局
			     contentPane.validate();//使表格有效化
				
			}
		});
		return p;
	}


	//中部的面板
	private JPanel createTablePane(String d,String d2,String str) {

	    pan = new JPanel(new BorderLayout());
		pan.add(createTable(d,d2,str));//添加表格组件
		return pan;
	}
	
	//中部面板添加组件
	private JScrollPane createTable(String d,String d2,String str) {
		//一维数组表头
		String[] title ={"图书编号","图书名称","出版社","图书单价","读者编号","读者姓名","操作员"};
		//二维数组表数据
		List<bookLoseBean> blb;
		JTable table;
		if(d==null&&d2==null&&str==null){
	    	 blb = bookLoseDAO.getallbookLoseinfo();
			 table = new JTable(getdata(blb),title);
		}
		else{
			  blb = bookLoseDAO.getqurybookLose(d,d2,str);
           	  table = new JTable(getdata(blb),title);
           	}  	
		
		table.getTableHeader().setReorderingAllowed(false);   //不可整列移动     
		table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
		JScrollPane p = new JScrollPane();
		p.add(table);
		p.setViewportView(table);
		return p;
	}
	
	//
	private Object[][] getdata(List<bookLoseBean> blb) {
		// TODO Auto-generated method stub
		Object[][] data =new Object[blb.size()][7];
		for(int i=0;i<blb.size();i++){
			Object emp[] = new Object[7];
			emp[0]=((bookLoseBean) blb.get(i)).getBookId();
			emp[1]=((bookLoseBean) blb.get(i)).getBookName();
			emp[2]=((bookLoseBean) blb.get(i)).getPublisher();
			emp[3]=((bookLoseBean) blb.get(i)).getPrice();
			emp[4]=((bookLoseBean) blb.get(i)).getReaderId();
			emp[5]=((bookLoseBean) blb.get(i)).getReaderName();
			emp[6]=((bookLoseBean) blb.get(i)).getUserName();			
			data[i]=emp;
		}	
		return data;
	}

}

