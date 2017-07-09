package cn.com.views.borrow;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.ModerateSkin;

import cn.com.beans.bookInfo.BookInfoBean;
import cn.com.beans.bookInfo.BookTypeInfoBean;
import cn.com.beans.borrowInfo.BorrowInfoBean;
import cn.com.beans.borrowInfo.BorrowInfoViewBean;
import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.bookInfo.BookInfoDAOImp;
import cn.com.daos.bookInfo.BookInfoDAOInf;
import cn.com.daos.borrowInfo.BorrowInfoDAOImp;
import cn.com.daos.borrowInfo.BorrowInfoDAOInf;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.global.Global;
import cn.com.views.main.MainFrame;


//ȡ��settabledata����ע��
public class BorrowFrame extends JFrame{
	
	public UserInfoBean uib;
	
	private String type;
	
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JLabel lbl6;
	private JLabel lbl7;
	private JLabel lbl8;
	private JLabel lbl9;
	private JLabel lbl10;
	private JLabel lbl11;
	
	private JLabel lblBookName;
	private JLabel lblBookNum;
	
	private JButton btn1;
	
	public JTextField tfd1;
	private JTextField tfd2;
	
	private JScrollPane spn1;
	private JScrollPane spn2;
	
	private JTable table1;
	private JTable table2;
	
	private DefaultTableModel dtm1;
	private DefaultTableModel dtm2;
	
	private  Vector<String> vecOfTitle1;
	private  Vector<String> vecOfTitle2;
	
	private BookInfoDAOInf bidi;
	public JLabel labelReaderName;
	public JLabel labelSex;
	public JLabel labelType;
	public JLabel labelMaxNum;
	public JLabel labelHasNum;
	private JButton btnQueryR;
	private JButton btnQueryB;
	private BorrowInfoDAOInf dao;	
	public  Vector<Vector> vec;
	public BorrowFrame(String type,UserInfoBean uib){
		this.uib=uib;
		dao=new BorrowInfoDAOImp();
		this.type = type;
		initObject();
		init();
	}

	private void initObject() {
		// TODO Auto-generated method stub
		vec = new Vector<Vector>();
		bidi = new BookInfoDAOImp();
		vecOfTitle1 = new Vector<String>();
		vecOfTitle2 = new Vector<String>();
		String s1 = new String();
		String s2 = new String();
		String s3 = new String();
		if(type.equals("JieShu")){
			s1 = "����ͼ��";
			s2 = "���ν���ͼ��";
			s3 = "����ȷ��";
			lbl11 = new JLabel("ʣ������:");
			lblBookNum = new JLabel();
			setTitle("ͼ����ͨ����(����)");			
			setVector(vecOfTitle1,1);
			setVector(vecOfTitle2,2);
			
			
			
		}else if(type.equals("XuJie")){
			s1 = "�ö�������ͼ��";
			s2 = "��������ͼ��";
			s3 = "����ȷ��";
			setTitle("ͼ����ͨ����(����)");
			setVector(vecOfTitle1,3);
			setVector(vecOfTitle2,2);
		}else if(type.equals("DiuShi")){
			s1 = "�ö�������ͼ��";
			s2 = "���ζ�ʧ�����ͼ��ͼ��";
			s3 = "��ʧȷ��";
			setTitle("ͼ����ͨ����(��ʧ)");
			setVector(vecOfTitle1,3);
			setVector(vecOfTitle2,2);
		}else{
			s1 = "�ö�������ͼ��";
			s2 = "���ι黹ͼ��";
			s3 = "����ȷ��";
			setTitle("ͼ����ͨ����(����)");
			setVector(vecOfTitle1,3);
			setVector(vecOfTitle2,2);
		}	
		
		lbl1 = new JLabel("���߱��:");
		lbl2 = new JLabel("��������:");
		lbl3 = new JLabel("�Ա�:");
		lbl4 = new JLabel("����:");
		lbl5 = new JLabel("�ɽ�����:");
		lbl6 = new JLabel("�ѽ�����:");
		lbl7 = new JLabel("�鿯���:");
		lbl8 = new JLabel("ͼ������:");	
		lbl9 = new JLabel(s1);
		lbl10 = new JLabel(s2);
		
		lblBookName = new JLabel();
		
		labelReaderName = new JLabel();
		labelSex = new JLabel();
		labelMaxNum = new JLabel();
		labelHasNum = new JLabel();
		labelType = new JLabel();
		btnQueryR = new JButton(new ImageIcon(MainFrame.class.getResource("/image/query_16.png")));
		btn1 = new JButton(s3);
		
		tfd1 = new JTextField();
		tfd2 = new JTextField();
		
		btnQueryB = new JButton(new ImageIcon(MainFrame.class.getResource("/image/query_16.png")));
		
		table1 = new JTable(){
			public boolean isCellEditable(int row,int column) {
				return false;
			}	
		};
		
		table2 = new JTable(){
			public boolean isCellEditable(int row,int column) {
				return false;
			}	
		};
		
		spn1 = new JScrollPane();
		spn2 = new JScrollPane();
		
		
		
		//dtm1 = new DefaultTableModel(null,vecOfTitle1);
		//table1.setModel(dtm1);
		
		//dtm2 = new DefaultTableModel(null,vecOfTitle2);
		//table2.setModel(dtm2);
		
		if(type.equals("JieShu")){
			setTableData(bidi.getAllBookInfo());
			btnQueryB.setEnabled(false);
		}else if(type.equals("XuJie")){
			
		}else if(type.equals("DiuShi")){
			
		}else{

		}
	}
	

	private void init() {
		// TODO Auto-generated method stub
		

		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spn1.setViewportView(table1);
		spn2.setViewportView(table2);
		
		
		//������ʽ
		lbl1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lbl2.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lbl3.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lbl4.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lbl5.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lbl6.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lbl7.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lbl8.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lbl9.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lbl10.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblBookName.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		labelReaderName.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		labelSex.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		labelType.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		labelMaxNum.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		labelHasNum.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		getContentPane().add(lbl1);
		getContentPane().add(lbl2);
		getContentPane().add(lbl3);
		getContentPane().add(lbl4);
		getContentPane().add(lbl5);
		getContentPane().add(lbl6);
		getContentPane().add(lbl7);
		getContentPane().add(lbl8);
		getContentPane().add(lbl9);
		getContentPane().add(lbl10);
		getContentPane().add(lblBookName);

		getContentPane().add(tfd1);
		getContentPane().add(tfd2);
		getContentPane().add(btn1);
		getContentPane().add(spn1);
		getContentPane().add(spn2);
		getContentPane().add(labelReaderName);				
		getContentPane().add(labelSex);
		getContentPane().add(labelType);
		getContentPane().add(labelMaxNum);
		getContentPane().add(labelHasNum);
		getContentPane().add(btnQueryR);
		getContentPane().add(btnQueryB);
		if(type.equals("JieShu")){
			getContentPane().add(lbl11);
			getContentPane().add(lblBookNum);
			lbl11.setBounds(490, 60, 70, 20);
			lblBookNum.setBounds(570, 60, 150, 20);
			lbl11.setFont(new Font("΢���ź�", Font.PLAIN, 14));
			lblBookNum.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		}

		lbl1.setBounds(20, 10,70,20);
		lbl2.setBounds(250, 10,64,20);
		lbl3.setBounds(410, 10,41,20);
		lbl4.setBounds(500, 10,41,20);
		lbl5.setBounds(615, 10,70,20);
		lbl6.setBounds(730, 10,64,20);		
		lbl7.setBounds(20, 60,70,20);
		lbl8.setBounds(250, 60,70,20);
		lbl9.setBounds(20, 110,200,20);
		lbl10.setBounds(20, 280,200,20);
		labelReaderName.setBounds(316, 10, 84, 20);
		labelHasNum.setBounds(793, 10, 41, 20);
		labelSex.setBounds(449, 10, 41, 20);
		labelType.setBounds(539, 10, 66, 20);
		labelMaxNum.setBounds(679, 10, 41, 20);
		lblBookName.setBounds(330, 60, 160, 20);
		
		tfd1.setBounds(90, 10, 100, 20);
		tfd2.setBounds(90, 60, 100, 20);
		
		btn1.setBounds(720, 280, 100, 25);
		
		spn1.setBounds(20, 135, 800, 140);
		spn2.setBounds(20, 310, 800, 150);
		btnQueryR.setBounds(200, 10, 20, 20);
		btnQueryB.setBounds(200, 60, 20, 20);
		
		setResizable(false);
		getContentPane().setLayout(null);
			
		setBounds(0, 0, 850, 527);
		Global.setCenterByWindow(this);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(type.equals("JieShu")){
					BookInfoBean b = new BookInfoBean();
					b = (BookInfoBean)table1.getValueAt(table1.getSelectedRow(), 0);
					if(e.getClickCount() == 1){
						tfd2.setText(b.getBookId()+"");
						lblBookName.setText(b.getBookName());
						if(lblBookNum != null){
							lblBookNum.setText(b.getBookTotalNum() - b.getBookOutNum() + "");
						}
					}else if(e.getClickCount() == 2){
						if(!tfd1.getText().isEmpty()){
							if(Integer.parseInt(labelMaxNum.getText()) > Integer.parseInt(labelHasNum.getText())){
								if(b.getBookTotalNum() - b.getBookOutNum() > 0){
									int i = 0;
									for(;i < vec.size();i++){
										int bb = (Integer)(vec.get(i).get(0));
										if(bb == b.getBookId()){
											JOptionPane.showMessageDialog(null, "��ͼ�������", "����", JOptionPane.WARNING_MESSAGE);
											break;
										}
									}
									if(i >= vec.size()){
										setTableData1(b,null);
									}						
								}else{
									JOptionPane.showMessageDialog(null, "�ö�������ͼ���ѳ�������", "����", JOptionPane.WARNING_MESSAGE);
								}
							}							
						}else{
							JOptionPane.showMessageDialog(null, "����ѡ�����", "����", JOptionPane.WARNING_MESSAGE);
						}										
					}
				}else{				
					BorrowInfoViewBean bbb = (BorrowInfoViewBean)table1.getValueAt(table1.getSelectedRow(), 0);
					if(e.getClickCount() == 1){
					
						BookInfoBean b = new BookInfoBean();
						b = bbb.getBookView().getBook();
						tfd2.setText(b.getBookId()+"");
						lblBookName.setText(b.getBookName());
						if(lblBookNum != null){
							lblBookNum.setText(b.getBookTotalNum() - b.getBookOutNum() + "");
						}
					}else if(e.getClickCount() == 2){
						BookInfoBean b = new BookInfoBean();
						b = bbb.getBookView().getBook();
						if(!tfd1.getText().isEmpty()){						
							if(Integer.parseInt(labelMaxNum.getText()) > Integer.parseInt(labelHasNum.getText())){
								if(b.getBookTotalNum() - b.getBookOutNum() > 0){
									int i = 0;
									for(;i < vec.size();i++){
										int bb = (Integer)(vec.get(i).get(0));
										if(bb == b.getBookId()){
											JOptionPane.showMessageDialog(null, "��ͼ�������", "����", JOptionPane.WARNING_MESSAGE);
											break;
										}

									}
							if(i >= vec.size()){

								setTableData1(b,bbb.getBorrow());
								//bbb.getBorrow().getBorrowId()+""
								
							}
						
						}else{
							JOptionPane.showMessageDialog(null, "�ö�������ͼ���ѳ�������", "����", JOptionPane.WARNING_MESSAGE);
						}
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "����ѡ�����", "����", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}
			}
		});
		
		table2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					
					vec.remove(table2.getSelectedRow());
					setTableData1(null,null);
				}
				
			}
		});
		
		btnQueryR.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnQueryR_actionPerformed(e);
			}
		});
		
		btnQueryB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnQueryB_actionPerformed(e);
			}
		});
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn1_actionPerformed(e);
			}
		});
		
		
		
	}
	
	protected void btnQueryB_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!tfd1.getText().isEmpty()){
			ReaderInfoBean readerInfoBean = new ReaderInfoBean();
			readerInfoBean.setReaderId(Integer.parseInt(tfd1.getText()));
			
			List<BorrowInfoViewBean> bean = dao.getBorrowInfoByReaderId(readerInfoBean);
				
			setTableData2(bean);
		}
	}

	protected void tfd1_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	protected void btn1_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i = 0;i < vec.size();i++ ){
			BorrowInfoBean bib1 = null;
			BorrowInfoBean bib = new BorrowInfoBean();
			BookInfoBean b = (BookInfoBean)(vec.get(i).get(1));
			
			bib.setBookId(b.getBookId());
			bib.setBorrowUserId(uib.getUserId());
			bib.setReaderId((Integer.parseInt(tfd1.getText())));
			if(!type.equals("JieShu")){
				bib1 = (BorrowInfoBean)(vec.get(i).get(5));
			}
			if(type.equals("GuiHuan")){
				bib.setIsBack(1);
			}else{
				bib.setIsBack(0);
			}
			Date date = (Date)(vec.get(i).get(3));
			
			bib.setBorrowDate(sdf.format(date));
			
			if(type.equals("DiuShi")){
				bib.setIsLoss(1);
			}else{
				bib.setIsLoss(0);
			}
			
			if(type.equals("XuJie")){
				
				bib.setAddTime(1);
				System.out.println(Global.getDate(bib1.getNormalBackDate(), 30));
				bib.setNormalBackDate(Global.getDate(bib1.getNormalBackDate(), 30));
				System.out.println(bib.getNormalBackDate());
				
			}else{
				bib.setAddTime(0);
			}
			ReaderInfoDAOInf bidi = new ReaderInfoDAOImp();
			List<ReaderTypeInfoBean> list2 = bidi.getAllReaderTypeInfo();
			if(type.equals("JieShu")){
			for(ReaderTypeInfoBean bean : list2){
				if(bean.getReaderTypeName().equals(labelType.getText())){														
					bib.setNormalBackDate(Global.getDate(sdf.format(date),bean.getDays()));
				}
			}}
			if(type.equals("JieShu")){
				dao.addBorrowInfo(bib);
				b.setBookOutNum(b.getBookOutNum() + 1);			
				dao.updateBorrowInfo(bib);
				new BookInfoDAOImp().borrowOneBook(b);
			}else{


				//System.out.println(bib1.getBorrowId());
				System.out.println(bib.getAddTime());
				bib.setBorrowId(bib1.getBorrowId());
				dao.updateBorrowInfo(bib);
				if(type.equals("GuiHuan")){
					b.setBookOutNum(b.getBookOutNum() - 1);			
					dao.updateBorrowInfo(bib);
					new BookInfoDAOImp().borrowOneBook(b);
				}
			}
			
		}
		if (vec.size() > 0) {
			JOptionPane.showMessageDialog(null, "�����ɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
			BorrowFrame.this.dispose();
		}

		
	}

	/**
	 * 
	 * @param vector ���⼯��
	 * @param type	��������
	 */
	private void setVector(Vector<String> vector,int type1) {
		// TODO Auto-generated method stub
		if(type1 == 1) {
			vector.add("ͼ������"); 
			vector.add("����");
			vector.add("������");
			vector.add("�鿯����");
			vector.add("�ܲ���");
			vector.add("�������");
			vector.add("ʣ�����");
			vector.add("�鿯���");
			vector.add("��������");
			vector.add("�������");
			vector.add("�۸�");
		}else if(type1 ==2){
			vector.add("ͼ����");
			vector.add("ͼ������");
			vector.add("������");			
			vector.add("�������");
			vector.add("����Ա");
			if(!type.equals("JieShu")){
				vector.add("���ı��");
			}
		}else{
			vector.add("ͼ������");
			vector.add("ͼ����");
			vector.add("������");
			vector.add("���߱��");
			vector.add("��������");
			vector.add("�������");
			vector.add("Ӧ������");
			vector.add("�Ƿ�����");
			vector.add("�������Ա");			
		}
		

		
	}
	
	
	private void setTableData2(List<BorrowInfoViewBean> list) {
		// TODO Auto-generated method stub


		Vector<Vector> data = new Vector<Vector>();
		Vector<Object> row = null;
		for(BorrowInfoViewBean b : list) {
			if(type.equals("XuJie") && b.getBorrow().getAddTime() == 1){
				
			}else{
			if(b.getBorrow().getIsBack() == 0 && b.getBorrow().getIsLoss() == 0){
				row = new Vector();
				row.add(b);
				row.add(b.getBookView().getBook().getBookId());
				row.add(b.getBookView().getBook().getPublisher());
				row.add(b.getReaderView().getReader().getReaderId());
				row.add(b.getReaderView().getReader().getReaderName());
				row.add(b.getBorrow().getBorrowDate());
				row.add(b.getBorrow().getNormalBackDate());
				if(b.getBorrow().getAddTime() == 1){
					row.add("�Ѿ�����");
				}else{
					row.add("δ����");
				}
				
				row.add(b.getBorrow().getBackUserId());
				
				
				data.add(row);
			}
			}
	
		}
		
		dtm1 = new DefaultTableModel(data,vecOfTitle1);
		table1.setModel(dtm1);
		
		this.table1.getColumn("ͼ������").setMaxWidth(100);
		this.table1.getColumn("ͼ������").setMinWidth(100);
		this.table1.getColumn("������").setMaxWidth(150);
		this.table1.getColumn("������").setMinWidth(150);

	}
	
	private void setTableData(List<BookInfoBean> list) {
		// TODO Auto-generated method stub


		Vector<Vector> data = new Vector<Vector>();
		Vector<Object> row = null;
		for(BookInfoBean b : list) {
			
			row = new Vector();
			row.add(b);
			row.add(b.getWriter());
			row.add(b.getPublisher());
			BookInfoDAOInf bidi = new BookInfoDAOImp();
			List<BookTypeInfoBean> list2 = bidi.getAllBookTypeInfo();
			for(BookTypeInfoBean bean : list2){
				if(bean.getBookTypeId() == b.getBookTypeId()){
					row.add(bean.getBookTypeName());
				}
			}
			row.add(b.getBookTotalNum());
			row.add(b.getBookOutNum());
			row.add(b.getBookTotalNum() - b.getBookOutNum());
			row.add(b.getBookId());
			row.add(b.getBookRoom());
			row.add(b.getBookShelf());
			row.add(b.getPrice());
			
			data.add(row);
		}
		
		dtm1 = new DefaultTableModel(data,vecOfTitle1);
		table1.setModel(dtm1);
		this.table1.getColumn("ͼ������").setMaxWidth(100);
		this.table1.getColumn("ͼ������").setMinWidth(100);
		this.table1.getColumn("������").setMaxWidth(150);
		this.table1.getColumn("������").setMinWidth(150);
	}
	
	private void setTableData1(BookInfoBean bid,BorrowInfoBean id) {
		// TODO Auto-generated method stub

		Vector<Object> row = null;
		if(bid != null){
			row = new Vector();
			row.add(bid.getBookId());
			row.add(bid);
			row.add(bid.getPublisher());			
			java.util.Date now = new java.util.Date(); 
			Date date =  new Date(now.getYear(), now.getMonth(), now.getDate());
			row.add(date);
			row.add(uib);
			if(id != null){

				row.add(id);

			}

			vec.add(row);

		}
		
		
		dtm2 = new DefaultTableModel(vec,vecOfTitle2);
		table2.setModel(dtm2);
		
		this.table1.getColumn("ͼ������").setMaxWidth(100);
		this.table1.getColumn("ͼ������").setMinWidth(100);
		this.table1.getColumn("������").setMaxWidth(150);
		this.table1.getColumn("������").setMinWidth(150);
	}
	
	public void btnQueryR_actionPerformed(ActionEvent e){
		ConInfoDialog c = new ConInfoDialog(this, "", true);
		int frameX = this.getX();
		int frameY = this.getY();
		int txtX = this.tfd1.getX();
		int txtY = this.tfd1.getY();
		int txtH = this.tfd1.getHeight();
		c.setLocation(frameX + txtX + 4, frameY + txtY + txtH + 27);
		c.setVisible(true);
			
	}
	
}
