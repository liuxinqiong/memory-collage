package cn.com.bookInfo;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cn.com.global.Check;




public class AddInfo extends JFrame {
	JLabel lbl1;
	JLabel lbl2;
	JLabel lbl3;
	JLabel lbl4;
	JLabel lbl5;
	JLabel lbl6;
	JLabel lbl7;
	JLabel lbl8;
	JLabel lbl9;
	JLabel lbl10;
	JLabel lbl12;
	JLabel lbl13;
	JTextField tfd1;
	JTextField tfd2;
	JTextField tfd3;
	JTextField tfd4;
	JTextField tfd5;
	JTextField tfd6;
	JTextField tfd7;
	JTextField tfd8;
	JTextField tfd9;
	JTextField tfd10;
	Choice tfd12;
	JTextField tfd13;
	JButton btnsave;
	JButton btndelete;
	JPanel p;
	int x=0;
	public void Add(JFrame frame) {
		// TODO Auto-generated method stub
        Connection conn=JDBC_Util.getConnection();
		String sql ="select * from bookInfo";
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
		frame.setEnabled(false);
		this.setResizable(false) ;
		this.setSize(600,320);
		this.setLocation(380, 200);
		p = new JPanel(null);
		lbl1 = new JLabel("图书编号"); 
		lbl2 = new JLabel("书名"); 
		lbl3 = new JLabel("作者"); 
		lbl4 = new JLabel("出版社"); 
		lbl5 = new JLabel("出版日期"); 
		lbl6 = new JLabel("价格"); 
		lbl7 = new JLabel("所在书室"); 
		lbl8 = new JLabel("所在书架"); 
		lbl9 = new JLabel("总册数"); 
		lbl10 = new JLabel("借出册数"); 
		lbl12 = new JLabel("类别名称");
		lbl13 = new JLabel("是否删除");
		tfd1 = new JTextField(String.valueOf(x+1));
		tfd1.setEditable(false);
		tfd2 = new JTextField();
		tfd3 = new JTextField();
		tfd4 = new JTextField();
		tfd5 = new JTextField("格式:yyyy-mm-dd");
		tfd6 = new JTextField();
		tfd7 = new JTextField();
		tfd8 = new JTextField();
		tfd9 = new JTextField();
		tfd10 = new JTextField();
		tfd12 = new Choice();
		Connection conn1 = JDBC_Util.getConnection();
		String[] x = new String[100];
		String sql1 = "select bookTypeName from bookTypeInfo where bookTypeIsDel <>1";
		try {
			PreparedStatement ps1 = conn1.prepareStatement(sql1);
			ResultSet rs = ps1.executeQuery();
			int i = 0;
			while(rs.next()){
				x[i] = rs.getString("bookTypeName");
				i++;
			}
			for(int j=0;j<100;j++){
				if(null!=x[j]&&!x[j].equals("")){
					tfd12.add(String.valueOf(x[j]));
				}
				JDBC_Util.closeConnection(conn1, ps1,rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfd13 = new JTextField("0");
		tfd13.setEditable(false);
		btnsave = new JButton("保存");
		btndelete = new JButton("取消");
		lbl1.setBounds(20, 10, 80, 20);
		tfd1.setBounds(110, 10, 150, 20);
		lbl2.setBounds(320, 10, 80, 20);
		tfd2.setBounds(410, 10, 150, 20);
		lbl3.setBounds(20, 40, 80, 20);
		tfd3.setBounds(110, 40, 150, 20);
		lbl4.setBounds(320, 40, 80, 20);
		tfd4.setBounds(410, 40, 150, 20);
		lbl5.setBounds(20, 70, 80, 20);
		tfd5.setBounds(110, 70, 150, 20);
		lbl6.setBounds(320, 70, 80, 20);
		tfd6.setBounds(410, 70, 150, 20);
		lbl7.setBounds(20, 100, 80, 20);
		tfd7.setBounds(110, 100, 150, 20);
		lbl8.setBounds(320, 100, 80, 20);
		tfd8.setBounds(410, 100, 150, 20);
		lbl9.setBounds(20, 130, 80, 20);
		tfd9.setBounds(110, 130, 150, 20);
		lbl10.setBounds(320, 130, 80, 20);
		tfd10.setBounds(410, 130, 150, 20);
		lbl12.setBounds(20, 160, 80, 20);
		tfd12.setBounds(110, 160, 150, 20);
		lbl13.setBounds(20, 160, 80, 20);
		tfd13.setBounds(110, 160, 150, 20);
		btnsave.setBounds(150, 220, 80, 30);
		btndelete.setBounds(300, 220, 80, 30);
		p.add(lbl1);
		p.add(tfd1);
		p.add(lbl2);
		p.add(tfd2);
		p.add(lbl3);
		p.add(tfd3);
		p.add(lbl4);
		p.add(tfd4);
		p.add(lbl5);
		p.add(tfd5);
		p.add(lbl6);
		p.add(tfd6);
		p.add(lbl7);
		p.add(tfd7);
		p.add(lbl8);
		p.add(tfd8);
		p.add(lbl9);
		p.add(tfd9);
		p.add(lbl10);
		p.add(tfd10);
		p.add(lbl12);
		p.add(tfd12);
		p.add(btnsave);
		p.add(btndelete);
		this.add(p);
		close(frame,this);
		windows(frame);
        save(frame,this);
	}
	private void save(final JFrame frame, final AddInfo addInfo) {
		// TODO Auto-generated method stub
		btnsave.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			boolean b = Check.isInt(tfd9.getText());
    			boolean a = Check.isInt(tfd10.getText());
    			if(null==tfd2.getText()||tfd2.getText().equals("")||null==tfd3.getText()||tfd3.getText().equals("")||null==tfd4.getText()||tfd4.getText().equals("")||null==tfd5.getText()||tfd5.getText().equals("")||null==tfd6.getText()||tfd6.getText().equals("")||null==tfd7.getText()||tfd7.getText().equals("")||null==tfd8.getText()||tfd8.getText().equals("")||null==tfd9.getText()||tfd9.getText().equals("")||null==tfd10.getText()||tfd10.getText().equals("")){
    				JOptionPane.showMessageDialog(null,"含有未输入的数据","错误",JOptionPane.ERROR_MESSAGE);
    			}
    			else if(!a||!b){
    				JOptionPane.showMessageDialog(null,"借出册数或总册数越界或输入的不为整数","错误",JOptionPane.ERROR_MESSAGE);
    			}
    			else if(Integer.valueOf(tfd9.getText())>=10000||Integer.valueOf(tfd10.getText())>=10000){
    				JOptionPane.showMessageDialog(null,"借出册数或总册数越界或输入的不为整数","错误",JOptionPane.ERROR_MESSAGE);
    			}
    			else if(!Check.isDate(tfd5.getText())){
    				JOptionPane.showMessageDialog(null,"日期非法","错误",JOptionPane.ERROR_MESSAGE);

    			}
    			else if(!Check.isDouble(tfd6.getText())){
    				JOptionPane.showMessageDialog(null,"价格输入有误","错误",JOptionPane.ERROR_MESSAGE);

    			}
    			else{
    			Connection conn=JDBC_Util.getConnection();
    			String sql3 = "select bookTypeId from bookTypeInfo where  bookTypeName =?";
    			String sql2 = "insert  into bookInfo(bookId,bookTypeId,bookName,writer,publisher,publishDate,price,bookRoom,bookShelf,bookTotalNum,bookOutNum,bookIsDel) values (?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?)";
    			try {
    				int x=0;
    				PreparedStatement ps3 = conn.prepareStatement(sql3);
    				ps3.setString(1, tfd12.getSelectedItem());
    				ResultSet rs = ps3.executeQuery();
    				while(rs.next()){
    					x = rs.getInt("bookTypeId");
    				}
					PreparedStatement ps2 = conn.prepareStatement(sql2);
		     		ps2.setLong(1, Integer.valueOf(tfd1.getText()));
					ps2.setLong(2,x);
					ps2.setString(3, tfd2.getText());
					ps2.setString(4, tfd3.getText());
					ps2.setString(5, tfd4.getText());					
					ps2.setString(6,tfd5.getText());
					ps2.setFloat(7, Float.parseFloat(tfd6.getText()));
					ps2.setString(8, tfd7.getText());
					ps2.setString(9, tfd8.getText());
					ps2.setLong(10, Integer.valueOf(tfd9.getText()));
					ps2.setLong(11, Integer.valueOf(tfd10.getText()));
					ps2.setLong(12, Integer.valueOf(tfd13.getText()));
					ps2.executeUpdate();
					JDBC_Util.closeConnection(conn, ps2);
					frame.setEnabled(true);
					addInfo.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    
    		} 		
    		}
    	});
	}	
	private void close(final JFrame frame, final JFrame addInfo) {
		// TODO Auto-generated method stub
		btndelete.addActionListener(new ActionListener() {
        		
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			// TODO Auto-generated method stub)
        			frame.setEnabled(true);
        			addInfo.setVisible(false);
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
