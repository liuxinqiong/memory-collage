package cn.com.bookTypeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cn.com.bookInfo.JDBC_Util;

public class DeleteTypeInfo {

	public void Delete(JTable table, Object[][] data) {
		// TODO Auto-generated method stub
		int x;
		x = table.getSelectedRow();
		if(x==-1){
			JOptionPane.showMessageDialog(null,"Î´Ñ¡¶¨ÐÐ","´íÎó",JOptionPane.ERROR_MESSAGE);
		}
		else{
			int y =  (Integer) data[x][1]; 
		Connection conn = JDBC_Util.getConnection();
		String sql4 = "update bookTypeInfo set bookTypeIsDel = 1 where bookTypeId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql4);
			ps.setLong(1, y);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	}

}
