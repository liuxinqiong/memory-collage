package cn.com.bookInfoSearch;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import cn.com.bookInfo.JDBC_Util;

public class Searchbook extends JFrame{
	JButton btnclose;
	JButton btnsearch;
	JButton btnfresh;
	JLabel lbl1;
	JLabel lbl2;
	JTextField txt;
	JTable table;
	Object[][] data;
	Choice c;
	static JPanel p1;
	public Searchbook(){
		super("�鿯��Ϣά��");
		init();
		this.setLocation(200, 100);
	} 
	private void init(){
		this.setSize(800,600);
		this.setResizable(false) ;
		this.setContentPane(createcontentPane());
		this.setVisible(true);
	}
	private JPanel createcontentPane(){
	    JPanel p = new JPanel(new BorderLayout());
		p.add(BorderLayout.NORTH,createNpane());
		p.add(BorderLayout.CENTER, createCpane());
		return p;
	}
	private JPanel createNpane(){
		JPanel p = new JPanel(new BorderLayout());
		p.add(BorderLayout.NORTH, createbtn());
		return p;	
	}
	private JPanel createbtn(){
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
		btnclose = new JButton("�ر�");
		btnfresh = new JButton("ˢ��");
		p.add(btnfresh);
		p.add(btnclose);
		btnfresh.addActionListener(new ActionListener() {		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			p1.removeAll();
    			p1.add(createtable());
    			p1.validate();
    		}		
    	});
		close(this);
		return p;
	}
	
	
	private void close(final JFrame frame) {
		// TODO Auto-generated method stub
		btnclose.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			frame.dispose();
    		}		
    	});
	}
	private JPanel createCpane(){
		JPanel p = new JPanel(new BorderLayout());
		p.add(BorderLayout.NORTH,createSearchpane());
		p.add(createviewpane());
		return p;
	}
	private JPanel createSearchpane(){
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
		lbl1 = new JLabel("ѡ���ѯ��ʽ��");
		c =new Choice();
		c.add("���ⷽʽ ");
		c.add("ͼ������");
		c.add("��������");
		lbl2 = new JLabel("����ͼ����Ϣ��ѯ��");
		txt = new JTextField("", 15);
		btnsearch = new JButton("��ѯ");
		p.add(lbl1);
		p.add(c);
		p.add(lbl2);
		p.add(txt);
		p.add(btnsearch);
		search(this);
		return p;
	}
	private JPanel createviewpane(){
		p1 = new JPanel(new BorderLayout());
		p1.add(createtable());
		return p1;
	}
	
	
	public  JScrollPane createtable(){
		Connection conn = JDBC_Util.getConnection();
		String sql1 = "select * from bookInfo where bookIsDel <>1";
		String sql2 = "select bookId,bookName,writer,publisher,publishDate,price,bookRoom,bookShelf,bookTotalNum,bookOutNum,bookTypeName from bookInfo,bookTypeInfo where bookInfo.bookTypeId = bookTypeInfo.bookTypeId and  bookIsDel <>1";
		PreparedStatement ps2;
		PreparedStatement ps1;
		int x=0;
		try {
			ps1 = conn.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				x++;
			}
			JDBC_Util.closeConnection(null, ps1, rs1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = new Object[x][11];
		String[] title ={"ͼ����","����","����","������","��������","�۸�","��������","�������","�ܲ���","�������","�������"};
		try {
			ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			int i = 0;
			while(rs2.next()){
				data[i][0] = rs2.getInt("bookId");
				data[i][1] = rs2.getString("bookName");
				data[i][2] = rs2.getString("writer");
				data[i][3] = rs2.getString("publisher");
				data[i][4] = rs2.getDate("publishDate");
				data[i][5] = rs2.getFloat("price");
				data[i][6] = rs2.getString("bookRoom");
				data[i][7] = rs2.getString("bookShelf");
				data[i][8] = rs2.getInt("bookTotalNum");
				data[i][9] = rs2.getInt("bookOutNum");
				data[i][10] = rs2.getString("bookTypeName");
				i++;
			}
			JDBC_Util.closeConnection(conn, ps2, rs2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createtable2(data,title);
	}
	public  JScrollPane createtable2( Object[][] data, String[] title) {
		// TODO Auto-generated method stub
		table = new JTable(data,title);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false); // ���������ƶ�
		table.getTableHeader().setResizingAllowed(false);
		table.getColumn("����").setMinWidth(120);
		table.getColumn("������").setMinWidth(120);
		table.valueChanged(new ListSelectionEvent(table, table.getColumnCount(), table.getColumnCount(),true));
		JScrollPane sp = new JScrollPane();
		sp.add(table);
		sp.setViewportView(table);
		return sp;
	}
	private void search(final JFrame frame) {
		// TODO Auto-generated method stub
		btnsearch.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			Connection conn = JDBC_Util.getConnection();
    			String s = c.getSelectedItem();
    			String t =txt.getText();
    			if(null==t||t.equals("")){
    				JOptionPane.showMessageDialog(null,"δ�����ѯ��Ϣ","����",JOptionPane.ERROR_MESSAGE);
    			}
    			else{
    			
    			 if(s.equals("ͼ������")){
    				String sql1 = "select * from bookInfo where bookName like ? and  bookIsDel <>1";
    				String sql2 = "select bookId,bookName,writer,publisher,publishDate,price,bookRoom,bookShelf,bookTotalNum,bookOutNum,bookTypeName from bookInfo,bookTypeInfo where bookInfo.bookTypeId = bookTypeInfo.bookTypeId and bookName like ? and bookIsDel <>1";
    				PreparedStatement ps2;
    				PreparedStatement ps1;
    				int x=0;
    				try {
    					ps1 = conn.prepareStatement(sql1);
    					ps1.setString(1, "%"+t+"%");
    					ResultSet rs1 = ps1.executeQuery();
    					while(rs1.next()){
    						x++;
    					}
    					JDBC_Util.closeConnection(null, ps1, rs1);
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				data = new Object[x][11];
    				String[] title ={"ͼ����","����","����","������","��������","�۸�","��������","�������","�ܲ���","�������","�������"};
    				try {
    					ps2 = conn.prepareStatement(sql2);
    					ps2.setString(1, "%"+t+"%");
    					ResultSet rs2 = ps2.executeQuery();
    					int i = 0;
    					while(rs2.next()){
    						data[i][0] = rs2.getInt("bookId");
    						data[i][1] = rs2.getString("bookName");
    						data[i][2] = rs2.getString("writer");
    						data[i][3] = rs2.getString("publisher");
    						data[i][4] = rs2.getDate("publishDate");
    						data[i][5] = rs2.getFloat("price");
    						data[i][6] = rs2.getString("bookRoom");
    						data[i][7] = rs2.getString("bookShelf");
    						data[i][8] = rs2.getInt("bookTotalNum");
    						data[i][9] = rs2.getInt("bookOutNum");
    						data[i][10] = rs2.getString("bookTypeName");
    						i++;
    					}
    					JDBC_Util.closeConnection(conn, ps2, rs2);
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				p1.removeAll();
    				p1.add(createtable2(data,title));
    				p1.validate();
    			}
    			else if(s.equals("��������")){
    				String sql1 = "select * from bookInfo where writer like ? and  bookIsDel <>1";
    				String sql2 = "select bookId,bookName,writer,publisher,publishDate,price,bookRoom,bookShelf,bookTotalNum,bookOutNum,bookTypeName from bookInfo,bookTypeInfo where bookInfo.bookTypeId = bookTypeInfo.bookTypeId and writer like ? and  bookIsDel <>1";
    				PreparedStatement ps2;
    				PreparedStatement ps1;
    				int x=0;
    				try {
    					ps1 = conn.prepareStatement(sql1);
    					ps1.setString(1, "%"+t+"%");
    					ResultSet rs1 = ps1.executeQuery();
    					while(rs1.next()){
    						x++;
    					}
    					JDBC_Util.closeConnection(null, ps1, rs1);
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				data = new Object[x][11];
    				String[] title ={"ͼ����","����","����","������","��������","�۸�","��������","�������","�ܲ���","�������","�������"};
    				try {
    					ps2 = conn.prepareStatement(sql2);
    					ps2.setString(1, "%"+t+"%");
    					ResultSet rs2 = ps2.executeQuery();
    					int i = 0;
    					while(rs2.next()){
    						data[i][0] = rs2.getInt("bookId");
    						data[i][1] = rs2.getString("bookName");
    						data[i][2] = rs2.getString("writer");
    						data[i][3] = rs2.getString("publisher");
    						data[i][4] = rs2.getDate("publishDate");
    						data[i][5] = rs2.getFloat("price");
    						data[i][6] = rs2.getString("bookRoom");
    						data[i][7] = rs2.getString("bookShelf");
    						data[i][8] = rs2.getInt("bookTotalNum");
    						data[i][9] = rs2.getInt("bookOutNum");
    						data[i][10] = rs2.getString("bookTypeName");
    						i++;
    					}
    					JDBC_Util.closeConnection(conn, ps2, rs2);
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				p1.removeAll();
    				p1.add(createtable2(data,title));
    				p1.validate();
    			}
    			else{
    				String sql1 = "select * from bookInfo where  bookIsDel <>1 and( bookName like ? or writer like ?)";
    				String sql2 = "select bookId,bookName,writer,publisher,publishDate,price,bookRoom,bookShelf,bookTotalNum,bookOutNum,bookTypeName from bookInfo,bookTypeInfo where bookInfo.bookTypeId = bookTypeInfo.bookTypeId and ( bookName like ? or writer like ?) and  bookIsDel <>1";
    				PreparedStatement ps2;
    				PreparedStatement ps1;
    				int x=0;
    				try {
    					ps1 = conn.prepareStatement(sql1);
    					ps1.setString(1, "%"+t+"%");
    					ps1.setString(2, "%"+t+"%");
    					ResultSet rs1 = ps1.executeQuery();
    					while(rs1.next()){
    						x++;
    					}
    					JDBC_Util.closeConnection(null, ps1, rs1);
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				data = new Object[x][11];
    				String[] title ={"ͼ����","����","����","������","��������","�۸�","��������","�������","�ܲ���","�������","�������"};
    				try {
    					ps2 = conn.prepareStatement(sql2);
    					ps2.setString(1, "%"+t+"%");
    					ps2.setString(2, "%"+t+"%");
    					ResultSet rs2 = ps2.executeQuery();
    					int i = 0;
    					while(rs2.next()){
    						data[i][0] = rs2.getInt("bookId");
    						data[i][1] = rs2.getString("bookName");
    						data[i][2] = rs2.getString("writer");
    						data[i][3] = rs2.getString("publisher");
    						data[i][4] = rs2.getDate("publishDate");
    						data[i][5] = rs2.getFloat("price");
    						data[i][6] = rs2.getString("bookRoom");
    						data[i][7] = rs2.getString("bookShelf");
    						data[i][8] = rs2.getInt("bookTotalNum");
    						data[i][9] = rs2.getInt("bookOutNum");
    						data[i][10] = rs2.getString("bookTypeName");
    						i++;
    					}
    					JDBC_Util.closeConnection(conn, ps2, rs2);
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				p1.removeAll();
    				p1.add(createtable2(data,title));
    				p1.validate();
    			}
    			}
    		}		
    	});
	}
}
