package cn.com.bookTypeInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.com.bookInfo.JDBC_Util;

public class UpdateTypeInfo extends JFrame {
	JLabel lbl1;
	JLabel lbl2;
	JLabel lbl3;
	JTextField tfd1;
	JTextField tfd2;
	JTextField tfd3;
	JPanel p;
	JButton btnadd;
	JButton btncancle;
	String[] data = new String[2];
	int x;
	String y;
	public void Up(Typeview typeview, JTable table, Object[][] data2) {
		// TODO Auto-generated method stub
		x = table.getSelectedRow();
		if(x==-1){
			JOptionPane.showMessageDialog(null,"未选定行","错误",JOptionPane.ERROR_MESSAGE);
		}
		else{
		y =  (String) data2[x][0]; 
		Connection conn = JDBC_Util.getConnection();
		String sql2 = "select bookTypeId,bookTypeName from bookTypeInfo where bookTypeName = ? and bookTypeIsDel<>1";
		try {
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, y);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
				data[0] = rs2.getString("bookTypeName");
				data[1] = rs2.getString("bookTypeId");
			}
			JDBC_Util.closeConnection(conn, ps2, rs2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		typeview.setEnabled(false);
		this.setResizable(false) ;
		this.setSize(200,250);
		this.setLocation(570, 300);
		p = new JPanel();
		lbl1 = new JLabel("书刊类别"); 
		lbl2 = new JLabel("书刊编号");
		tfd1 = new JTextField(data[0],15);
		tfd2 = new JTextField(data[1],15);
		tfd2.setEditable(false);
		btnadd = new JButton("修改");
		btncancle = new JButton("取消");
		p.add(lbl1);
		p.add(tfd1);
		p.add(lbl2);
		p.add(tfd2);
		p.add(btnadd);
		p.add(btncancle);
		this.add(p);
		setVisible(true);
		close(typeview,this);
		windows(typeview);
		save(typeview,this);
	}
	}
	private void save(final Typeview typeview, final UpdateTypeInfo updateTypeInfo) {
		// TODO Auto-generated method stub
		btnadd.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			Connection conn=JDBC_Util.getConnection();
    			String sql2 = "update bookTypeInfo set bookTypeName = ? where bookTypeId = ?";
    			try {
					PreparedStatement ps2 = conn.prepareStatement(sql2);
					ps2.setString(1, tfd1.getText());
					ps2.setLong(2, Integer.valueOf(tfd2.getText()));
					ps2.executeUpdate();
					JDBC_Util.closeConnection(conn, ps2);
					typeview.setEnabled(true);
					updateTypeInfo.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    
    		} 			
    	});
	}	
	private void close( final Typeview typeview, final UpdateTypeInfo updateTypeInfo) {
		// TODO Auto-generated method stub
		btncancle.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			typeview.setEnabled(true);
    			updateTypeInfo.setVisible(false);
    		}		
    	});
	}
	private void windows(final JFrame frame){
		this.addWindowListener(new WindowAdapter(){
		//添加处理窗口关闭事件的方法
		@Override
		public void windowClosing(WindowEvent e) {
				frame.setEnabled(true);
			}
	});
}

}
