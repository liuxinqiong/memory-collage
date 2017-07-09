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

import cn.com.bookInfo.AddInfo;
import cn.com.bookInfo.JDBC_Util;

public class AddTypeInfo extends JFrame {
	JLabel lbl1;
	JLabel lbl2;
	JLabel lbl3;
	JTextField tfd1;
	JTextField tfd2;
	JTextField tfd3;
	JPanel p;
	JButton btnadd;
	JButton btncancle;
	int x;
	public void Add(Typeview typeview, JTable table, Object[][] data) {
		// TODO Auto-generated method stub
        Connection conn=JDBC_Util.getConnection();
		String sql ="select * from bookTypeInfo";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				x++;
			}
			JDBC_Util.closeConnection(conn, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		typeview.setEnabled(false);
		this.setResizable(false) ;
		this.setSize(200,200);
		this.setLocation(570, 300);
		p = new JPanel();
		lbl1 = new JLabel("书刊类别"); 
		lbl2 = new JLabel("书刊编号");
		tfd1 = new JTextField(15);
		tfd2 = new JTextField(String.valueOf(x),15);
		tfd2.setEditable(false);
		lbl3 = new JLabel("是否删除");
		tfd3 = new JTextField("0",15);
		tfd3.setEditable(false);
		btnadd = new JButton("保存");
		btncancle = new JButton("取消");
		p.add(lbl1);
		p.add(tfd1);
		p.add(lbl2);
		p.add(tfd2);
//		p.add(lbl3);
//		p.add(tfd3);
		p.add(btnadd);
		p.add(btncancle);
		this.add(p);
		close(typeview,this);
		windows(typeview);
		save(typeview,this);
	}
	private void save(final JFrame frame, final AddTypeInfo addTypeInfo) {
		// TODO Auto-generated method stub
		btnadd.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			if(null==tfd1.getText()||tfd1.getText().equals("")){
    				JOptionPane.showMessageDialog(null,"未输入正确书刊类别名称","错误",JOptionPane.ERROR_MESSAGE);
    			}
    			else{
    			Connection conn=JDBC_Util.getConnection();
    			String sql2 = "insert  into bookTypeInfo(bookTypeName,bookTypeId,bookTypeIsDel) values (?,?,?)";
    			try {
					PreparedStatement ps2 = conn.prepareStatement(sql2);
					ps2.setString(1, tfd1.getText());
					ps2.setLong(2, Integer.valueOf(tfd2.getText()));
					ps2.setLong(3, Integer.valueOf(tfd3.getText()));
					ps2.executeUpdate();
					JDBC_Util.closeConnection(conn, ps2);
					frame.setEnabled(true);
					addTypeInfo.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    
    		} 		
    		}
    	});
	}	
	private void close(final Typeview typeview, final AddTypeInfo addTypeInfo) {
		// TODO Auto-generated method stub
		btncancle.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub)
    			typeview.setEnabled(true);
    			addTypeInfo.setVisible(false);
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
