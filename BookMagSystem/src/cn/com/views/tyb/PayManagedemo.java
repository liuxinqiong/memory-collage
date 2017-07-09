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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.com.beans.tyb.PayManagBean;
import cn.com.daos.journaInfo.PayManagDAO;
import cn.com.global.DateChooser;


public class PayManagedemo extends JFrame {
	private JPanel contentPane;
	PayManagedemo frame;
	static String d;
	static String d2;
	static String str;
	static JPanel pan;

	/**
	 * Create the frame.
	 */
	public PayManagedemo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("�տ��ѯ");
		setBounds(100, 100, 600, 500);
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
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,30,15));
		p.setBackground(Color.cyan);
		JLabel jlb = new JLabel("ʱ�䷶Χ:");
		
		final DateChooser date = new DateChooser();
		final DateChooser date1 = new DateChooser();
		

		
		final JButton jb = new JButton("�� ѯ");
		
		final JTextField jt = new JTextField("�������ѯ��Ϣ",10);	
		
		p.add(jlb);
		p.add(date);
		p.add(date1);
		p.add(jt);
		p.add(jb);

		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				  d = date.showDate.getText();
				 d2 = date1.showDate.getText();
			     str = jt.getText();
			     pan.removeAll();
			     contentPane.add(BorderLayout.CENTER,createTablePane(d,d2,str));//���½�����񲼾� 
			     contentPane.validate();//ʹ�����Ч��
			}
			
		});
		
		return p;
	}
	
	
	
	private  JPanel createTablePane(String d,String d2,String str) {

	    pan = new JPanel(new BorderLayout());
		pan.add(createTable(d,d2,str));//��ӱ�����
		return pan;
	}

	
	private JScrollPane createTable(String d,String d2,String str) {
		//һά�����ͷ
		String[] title ={"���߱��","��������","�տʽ","���ս��","�տ�ʱ��","����Ա"};
		//��ά���������
		JTable table;
		if(d==null&&d2==null&&str==null){
			List<PayManagBean> pmb = PayManagDAO.getallPayManagBean();
			 table = new JTable(getdata(pmb),title);
		}
		else{
			List<PayManagBean> pmb = PayManagDAO.getPayManagBean(d,d2,str);
		    table = new JTable(getdata(pmb),title);
		}	
		table.getTableHeader().setReorderingAllowed(false);   //���������ƶ�     
		table.getTableHeader().setResizingAllowed(false);   //�����������
		JScrollPane p = new JScrollPane();
        p.add(table);
		p.setViewportView(table);
		
		return p;
	}
	
	private Object[][] getdata(List<PayManagBean> pmb) {
		// TODO Auto-generated method stub
		Object[][] data =new Object[pmb.size()][6];
		for(int i=0;i<pmb.size();i++){
			Object emp[] = new Object[6];
			emp[0]=((PayManagBean) pmb.get(i)).getReaderId();
			emp[1]=((PayManagBean) pmb.get(i)).getReaderName();
			emp[2]=((PayManagBean) pmb.get(i)).getPayWay();
			emp[3]=((PayManagBean) pmb.get(i)).getPayMoney();
			emp[4]=((PayManagBean) pmb.get(i)).getrealBackDate();
			emp[5]=((PayManagBean) pmb.get(i)).getUserName();
			data[i]=emp;
		}	
		return data;
	}

}
