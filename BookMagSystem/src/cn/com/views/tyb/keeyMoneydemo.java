package cn.com.views.tyb;

import java.awt.BorderLayout;
import java.awt.Color;
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

import cn.com.beans.tyb.keeyMoneyBean;
import cn.com.daos.journaInfo.keeyMoneyDAO;
import cn.com.global.DateChooser;



public class keeyMoneydemo extends JFrame {

	private JPanel contentPane;
	private keeyMoneydemo frame;
	static String d;
	static String d2;
	static String str;
	static JPanel pan;


	public keeyMoneydemo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("����Ѻ���ѯ");
		setBounds(100, 100, 700, 550);
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
		
		contentPane.add(BorderLayout.NORTH,createNorthPane());
		
		
		contentPane.add(BorderLayout.CENTER,createTablePane(null,null,null));
		setVisible(true);
	}
	
	
	private Component createNorthPane() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,30,15));
		p.setBackground(Color.cyan);
		
		JLabel jlb = new JLabel("ʱ�䷶Χ��");
		
		JButton jbn = new JButton("��ѯ");
		
		final JTextField jtxt = new JTextField("�������ѯ��Ϣ",15);
		final DateChooser date = new DateChooser();
		final DateChooser date2 = new DateChooser();
		
		p.add(jlb);
		p.add(date);
		p.add(date2);
		p.add(jtxt);
		p.add(jbn);
		
		jbn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 str = jtxt.getText();
				 d = date.showDate.getText();
				 d2 = date2.showDate.getText();
				 
				 pan.removeAll();
				 contentPane.add(BorderLayout.CENTER,createTablePane(d,d2,str));//���½�����񲼾�
			     contentPane.validate();//ʹ�����Ч��
			}
		});
		return p;
	}


	private JPanel createTablePane(String d,String d2,String str) {
//    	JScrollPane p = new JScrollPane();//
	    pan = new JPanel(new BorderLayout());
		pan.add(createTable(d,d2,str));//��ӱ�����
		return pan;
	}
	private JScrollPane createTable(String d,String d2,String str) {
		JTable table;
		//һά�����ͷ
		String[] title ={"���߱��","��������","Ѻ����","�յ�����","����Ա"};
		//��ά���������
		if(d==null&&d2==null&&str==null){
	    	List<keeyMoneyBean> kmb = keeyMoneyDAO.getallkeeymoney();
		    table = new JTable(getdata(kmb),title);
	    	
		}
		else{
			List<keeyMoneyBean> kmb = keeyMoneyDAO.getqurykeeymoney(d,d2,str);
            table = new JTable(getdata(kmb),title);
			
		}
		
		table.getTableHeader().setReorderingAllowed(false);   //���������ƶ�     
		table.getTableHeader().setResizingAllowed(false);   //�����������
		JScrollPane p = new JScrollPane();
		p.add(table);
		p.setViewportView(table);
		return p;
	}

	private Object[][] getdata(List<keeyMoneyBean> kmb) {
		// TODO Auto-generated method stub
		Object[][] data =new Object[kmb.size()][5];
		for(int i=0;i<kmb.size();i++){
			Object emp[] = new Object[6];
			emp[0]=((keeyMoneyBean) kmb.get(i)).getReaderId();
			emp[1]=((keeyMoneyBean) kmb.get(i)).getReaderName();
			emp[2]=((keeyMoneyBean) kmb.get(i)).getMoney();
			emp[3]=((keeyMoneyBean) kmb.get(i)).getOperateTime();
			emp[4]=((keeyMoneyBean) kmb.get(i)).getUserName();
			data[i]=emp;
		}	
		return data;
	}

}

