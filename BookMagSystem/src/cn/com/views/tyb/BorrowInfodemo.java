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
		setTitle("ͼ�����ͳ��");
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton button = new JButton("�� ��");
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
		// ������������
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,30,15));
		p.setBackground(Color.cyan);
		JLabel jlb = new JLabel("ͼ������:");
		JLabel jlb1 = new JLabel("ʱ�䷶Χ:");
		
		jlb.setFont(new Font("����", Font.HANGING_BASELINE, 15));
		jlb1.setFont(new Font("����", Font.HANGING_BASELINE, 15));	
		
		final JButton jb = new JButton("ͳ��");
		final JComboBox jt = new JComboBox();
		
		final DateChooser date = new DateChooser();
		final DateChooser date1 = new DateChooser();
		
		GetbookType gt = new GetbookType();
		String[] list = gt.init();
		int j = gt.getlength();//�õ�list��Ч���鳤��
		
	    

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
			     contentPane.add(BorderLayout.CENTER,createTablePane(str,txt,txt1));//���½�����񲼾�
			     contentPane.validate();//ʹ�����Ч��
			}
			
		});
		
		return p;
	}
	
	private  JPanel createTablePane(String str,String txt,String txt1) {

	    pan = new JPanel(new BorderLayout());
		pan.add(createTable(str));//��ӱ�����
		return pan;
	}

	
	private JScrollPane createTable(String str) {
		//һά�����ͷ
		String[] title = {"ͼ������","�������","�ܲ���","����","ͼ������","������"};
		//��ά���������
		
		List<BookBorrBean> 	bb = BookBorrDAO.getBookBorrBean(str,txt,txt1);
		JTable table = new JTable(getBBdata(bb),title);
			
		table.getTableHeader().setReorderingAllowed(false);   //���������ƶ�     
		table.getTableHeader().setResizingAllowed(false);   //�����������
		JScrollPane p = new JScrollPane();
        p.add(table);
		p.setViewportView(table);
		
		return p;
	}
	
	
	private Object[][] getBBdata(List<BookBorrBean> pb) {
		// ͼ�������Ϣ���ݰ�װ
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
