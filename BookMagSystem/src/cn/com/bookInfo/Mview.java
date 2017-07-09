package cn.com.bookInfo;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.PresentationDirection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;

import cn.com.db.DBUtil;

	public class Mview extends JFrame{
		JButton btnadd;
		JButton btndelete;
		JButton btnupdate;
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
		public Mview(){
			super("书刊信息维护");
			init();
			this.setLocation(300, 100);
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
			btnadd = new JButton("增加");
			btndelete = new JButton("删除");
			btnupdate = new JButton("修改");
			btnclose = new JButton("关闭");
			btnfresh = new JButton("刷新");
			p.add(btnadd);
			p.add(btndelete);
			p.add(btnupdate);
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
			delete(this);
			Action(this);
			close(this);
			update(this);
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
			lbl1 = new JLabel("选择查询方式：");
			c =new Choice();
			c.add("任意方式 ");
			c.add("图书名称");
			c.add("作者姓名");
			lbl2 = new JLabel("输入图书信息查询：");
			txt = new JTextField("", 15);
			btnsearch = new JButton("查询");
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
			String[] title ={"图书编号","书名","作者","出版社","出版日期","价格","所在书室","所在书架","总册数","借出册数","类别名称"};
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
			table.getTableHeader().setReorderingAllowed(false); // 不可整列移动
			table.getTableHeader().setResizingAllowed(false);
			table.getColumn("书名").setMinWidth(120);
			table.getColumn("出版社").setMinWidth(120);
			table.valueChanged(new ListSelectionEvent(table, table.getColumnCount(), table.getColumnCount(),true));
			JScrollPane sp = new JScrollPane();
			sp.add(table);
			sp.setViewportView(table);
			return sp;
		}
		private void Action(final JFrame frame) {
			// TODO Auto-generated method stub
			btnadd.addActionListener(new ActionListener() {
        		
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			// TODO Auto-generated method stub)
        			AddInfo Action1 = new AddInfo();
        			Action1.Add(frame);
         			Action1.setVisible(true);
        		}		
        	});
		}
		private void delete(final JFrame frame) {
			// TODO Auto-generated method stub
			btndelete.addActionListener(new ActionListener() {
        		
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			// TODO Auto-generated method stub)
        			DeleteInfo Action1 = new DeleteInfo();
        			Action1.Delete(frame,table,data);
        			p1.removeAll();
        			p1.add(createtable());
        			p1.validate();
        		}		
        	});
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
        				JOptionPane.showMessageDialog(null,"未输入查询信息","错误",JOptionPane.ERROR_MESSAGE);
        			}
        			else{
        			 if(s.equals("作者姓名")){
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
        				String[] title ={"图书编号","书名","作者","出版社","出版日期","价格","所在书室","所在书架","总册数","借出册数","类别名称"};
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
        				String[] title ={"图书编号","书名","作者","出版社","出版日期","价格","所在书室","所在书架","总册数","借出册数","类别名称"};
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
		private void update(final JFrame frame) {
			// TODO Auto-generated method stub
			btnupdate.addActionListener(new ActionListener() {
        		
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			// TODO Auto-generated method stub)
        			UpdateInfo update = new UpdateInfo();
        			update.Update(frame,table,data);
        		}		
        	});
		}
	}
