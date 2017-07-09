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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.com.beans.tyb.PersonBorrBean;
import cn.com.daos.journaInfo.PersonBorrDAO;
import cn.com.global.DateChooser;
import cn.com.global.Global;


public class PresonBorrinfodemo extends JFrame {
	private JPanel contentPane;
	  
	static JPanel pan;
	static String txt;
	static String txt1;
	static String str;

	/**
	 * Create the frame.
	 */
	public PresonBorrinfodemo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("个人借阅信息统计");
		setBounds(100, 100, 600, 450);
		Global.setCenterByWindow(this);
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
		// TODO Auto-generated method stub
		 
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
		p.setBackground(Color.cyan);

		
	    JButton jb = new JButton("统计");
		JLabel jl = new JLabel("读者类型:");
		jl.setFont(new Font("宋体", Font.HANGING_BASELINE, 15));
		final DateChooser date = new DateChooser();
		final DateChooser date1 = new DateChooser();
		
		GetreaderType gt = new GetreaderType();
		String[] list = gt.init();
		int j = gt.getlength();//得到list有效数组长度
		
	    final JComboBox jt = new JComboBox();

	    for(int i = 0; i < j; i++){
	    	jt.addItem(list[i]);
	    }
		
	    p.add(jl);
		p.add(jt);
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
		String[] title = {"借阅次数","读者编号","读者姓名","读者类型","联系电话"};
		//二维数组表数据
		List<PersonBorrBean> pb = PersonBorrDAO.getPersonBorrBean(str,txt,txt1);
		JTable table = new JTable(getPBdata(pb),title);
		
		
		table.getTableHeader().setReorderingAllowed(false);   //不可整列移动     
		table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
		JScrollPane p = new JScrollPane();
        p.add(table);
		p.setViewportView(table);
		
		return p;
	}
	
	private Object[][] getPBdata(List<PersonBorrBean> pb) {
		// 个人借阅信息数据包装
		Object[][] data =new Object[pb.size()][5];
		for(int i=0;i<pb.size();i++){
			Object emp[] = new Object[6];
			emp[0]=((PersonBorrBean) pb.get(i)).getBorrcount();
			emp[1]=((PersonBorrBean) pb.get(i)).getReaderId();
			emp[2]=((PersonBorrBean) pb.get(i)).getReaderName();
			emp[3]=((PersonBorrBean) pb.get(i)).getReaderTypeName();
			emp[4]=((PersonBorrBean) pb.get(i)).getReaderTel();
			data[i]=emp;
		}	
		return data;
	}

}


