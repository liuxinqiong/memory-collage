package cn.com.bookInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class DeleteInfo extends JFrame{

	public void Delete(JFrame frame, JTable table ,Object[][] data) {
		// TODO Auto-generated method stub
		frame.setEnabled(false);
		int x;
		x = table.getSelectedRow();
		if(x==-1){
			JOptionPane.showMessageDialog(null,"Î´Ñ¡¶¨ÐÐ","´íÎó",JOptionPane.ERROR_MESSAGE);
			frame.setEnabled(true);
		}
		else{
			frame.setEnabled(true);
			int y = (Integer) data[x][0]; 
			Connection conn = JDBC_Util.getConnection();
			String sql4 = "update bookInfo set bookIsDel = 1 where bookId = ?";
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
