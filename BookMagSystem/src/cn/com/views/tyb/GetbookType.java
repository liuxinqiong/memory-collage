package cn.com.views.tyb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.com.db.DBUtil;

public class GetbookType {
	String sql;
	ResultSet rs;
	PreparedStatement ps;
	int i = 1;
	int j = 1;
	Connection conn;

	public GetbookType() {

	}

	public String[] init() {
		String[] list = new String[100];
		list[0] = "所有类型";
		conn = DBUtil.getConn();
		sql = "select  distinct bookTypeName from bookTypeInfo where bookTypeIsDel = 1";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list[i] = rs.getString("bookTypeName");
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

	public int getlength() {

		return j;
	}
}
