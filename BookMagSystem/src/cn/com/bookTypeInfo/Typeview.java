package cn.com.bookTypeInfo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
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

import cn.com.bookInfo.AddInfo;
import cn.com.bookInfo.DeleteInfo;
import cn.com.bookInfo.JDBC_Util;
import cn.com.bookInfo.UpdateInfo;

public class Typeview extends JFrame{
	JButton btnadd;
	JButton btndelete;
	JButton btnupdate;
	JButton btnsearch;
	JButton btnclose;
	JButton btnfresh;
	JTextField txt;
	JLabel lbl;
	JTable table;
	static JPanel p1;
	Object[][] data;
	public Typeview(){
		super("书刊类型信息维护");
		init();
		this.setLocation(470, 100);
	}
	private void init() {
		// TODO Auto-generated method stub
		this.setSize(400,600);
		this.setResizable(false) ;
		this.setContentPane(createpane());
		this.setVisible(true);
	}
	private JPanel createpane() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new BorderLayout());
		p.add(BorderLayout.NORTH,createNpane());
		p.add(BorderLayout.CENTER,createCpane());
		p.add(BorderLayout.SOUTH,createSpane());
		return p;
	}
	private JPanel createCpane() {
		// TODO Auto-generated method stub
		p1 = new JPanel(new BorderLayout());
		p1.add(createtable());
		return p1;
	}
	private JPanel createNpane() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new BorderLayout());
		p.add(createNCpane());
		return p;
	}

	private JPanel createNCpane() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10)); 
		txt = new JTextField(15);
		lbl = new JLabel("输入类别名：");
		btnsearch = new JButton("查询");	
		p.add(lbl);
		p.add(txt);
		p.add(btnsearch);
		return p;
	}
	private JPanel createSpane() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
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
		AddT(this);
		update(this);
		btndelete.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			DeleteTypeInfo D = new DeleteTypeInfo();
    			D.Delete(table,data);
    			p1.removeAll();
    			p1.add(createtable());
    			p1.validate();
    		}		
    	});
		btnclose.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			dispose();
    		}		
    	});
		fresh();
		search();
	
		return p;
	}
	private void update(final Typeview typeview) {
		// TODO Auto-generated method stub
		btnupdate.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			UpdateTypeInfo A = new UpdateTypeInfo();
    			A.Up(typeview,table,data);

    		}		
    	});
	}
	private void AddT(final Typeview typeview) {
		// TODO Auto-generated method stub
		btnadd.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			AddTypeInfo A = new AddTypeInfo();
    			A.Add(typeview,table,data);
    			A.setVisible(true);

    		}		
    	});
	}
	private void search() {
		// TODO Auto-generated method stub
		btnsearch.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			Connection conn = JDBC_Util.getConnection();
    			String sql1 = "select * from bookTypeInfo where bookTypeIsDel <>1 and bookTypeName like ?";
    			String sql2 = "select bookTypeName,bookTypeId from bookTypeInfo where bookTypeIsDel <>1 and bookTypeName like ? ";
    			PreparedStatement ps2;
    			PreparedStatement ps1;
    			String t = txt.getText();
    			if(null==t||t.equals("")){
    				JOptionPane.showMessageDialog(null,"未输入查询信息","错误",JOptionPane.ERROR_MESSAGE);
    			}
    			else{
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
    			data = new Object[x][2];
    			String[] title ={"类别名称","类别编号"};
    			try {
    				ps2 = conn.prepareStatement(sql2);
					ps2.setString(1, "%"+t+"%");
    				ResultSet rs2 = ps2.executeQuery();
    				int i = 0;
    				while(rs2.next()){
    					data[i][0] = rs2.getString("bookTypeName");
    					data[i][1] = rs2.getInt("bookTypeId");
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
    	});
	}
	private void fresh() {
		// TODO Auto-generated method stub
		btnfresh.addActionListener(new ActionListener() {		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			p1.removeAll();
    			p1.add(createtable());
    			p1.validate();
    		}		
    	});
	}
	private JScrollPane createtable() {
		// TODO Auto-generated method stub
		Connection conn = JDBC_Util.getConnection();
		String sql1 = "select * from bookTypeInfo where bookTypeIsDel <>1";
		String sql2 = "select bookTypeName,bookTypeId from bookTypeInfo where bookTypeIsDel <>1";
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
		data = new Object[x][2];
		String[] title ={"类别名称","类别编号"};
		try {
			ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			int i = 0;
			while(rs2.next()){
				data[i][0] = rs2.getString("bookTypeName");
				data[i][1] = rs2.getInt("bookTypeId");
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
		table.getColumn("类别名称").setMinWidth(200);
		table.getColumn("类别编号").setMinWidth(200);
		table.valueChanged(new ListSelectionEvent(table, table.getColumnCount(), table.getColumnCount(),true));
		JScrollPane sp = new JScrollPane();
		sp.add(table);
		sp.setViewportView(table);
		return sp;
	}
}
