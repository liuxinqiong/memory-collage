package cn.com.views.tyb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import cn.com.db.DBUtil;

public class GetreaderType {
	Connection conn;
	String sql;
	PreparedStatement ps;
	ResultSet rs;
	 int j = 1;
	 int i = 1;

	public String[] init() {
		// TODO Auto-generated method stub
		String[] list = new String[20];
		list[0] = "所有类型";
		conn = DBUtil.getConn();
		sql = "select  distinct readerTypeName from readerTypeInfo where readerTypeIsDel = 0";
		
		 try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				list[i] = rs.getString("readerTypeName");
		        i++;
		        j++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);

		return list;
	}
	public int getlength(){
		
		return j;
	}
}
