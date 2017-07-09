package cn.com.daos.journaInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.tyb.PayManagBean;
import cn.com.db.DBUtil;

public class PayManagDAO {
	/**
	 * 得到收款查询对象并压在list数组中返回
	 */

	public static List<PayManagBean> getallPayManagBean() {
		List<PayManagBean> list = new ArrayList<PayManagBean>();
		PayManagBean p = null;
		Connection conn = DBUtil.getConn();
		String sql = "select b.readerId as readerId,readerName,payWay,payMoney*(select lossTimes from borrowInfo b,readerTypeInfo rt,readerInfo ri where readerIsDel = 0 and rt.readertypeid = ri.readertypeid and b.readerId = ri.readerId) as payMoney,realBackDate,userName from borrowInfo b,readerInfo r,userInfo u where  readerIsDel = 0 and b.readerId = r.readerId and u.userid = b.backuserid";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			getdata(rs, p, list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.free(rs, ps, conn);
		return list;
	}

	public static List<PayManagBean> getPayManagBean(String d, String d2,
			String str) {
		List<PayManagBean> list = new ArrayList<PayManagBean>();
		PayManagBean p = null;
		Connection conn = DBUtil.getConn();
		String sql;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (str.equals("")) {
			sql = "select b.readerId as readerId,readerName,payWay,payMoney*(select lossTimes from borrowInfo b,readerTypeInfo rt,readerInfo ri where readerIsDel = 0 and rt.readertypeid = ri.readertypeid and b.readerId = ri.readerId) as payMoney,realBackDate,userName from borrowInfo b,readerInfo r,userInfo u where readerIsDel = 0 and b.readerId = r.readerId and u.userid = b.backuserid and realBackDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd')";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, d);
				ps.setString(2, d2);
				rs = ps.executeQuery();
				getdata(rs, p, list);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.free(rs, ps, conn);
			return list;
		}

		else {

			try {
				
				try {
					sql = "select b.readerId as readerId,readerName,payWay,payMoney*(select lossTimes from borrowInfo b,readerTypeInfo rt,readerInfo ri where readerIsDel = 0 and rt.readertypeid = ri.readertypeid and b.readerId = ri.readerId) as payMoney,realBackDate,userName from borrowInfo b,readerInfo r,userInfo u where readerIsDel = 0 and b.readerId = r.readerId and u.userid = b.backuserid and realBackDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') and (readerName like ? or r.readerId = ?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, d);
					ps.setString(2, d2);
					ps.setString(3, str );
					int temp = Integer.parseInt(str);
					ps.setInt(4, Integer.parseInt(str));
				} catch (java.lang.NumberFormatException e) {
					
					sql = "select b.readerId as readerId,readerName,payWay,payMoney*(select lossTimes from borrowInfo b,readerTypeInfo rt,readerInfo ri where readerIsDel = 0 and rt.readertypeid = ri.readertypeid and b.readerId = ri.readerId) as payMoney,realBackDate,userName from borrowInfo b,readerInfo r,userInfo u where readerIsDel = 0 and b.readerId = r.readerId and u.userid = b.backuserid and realBackDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') and readerName like ? ";
					ps = conn.prepareStatement(sql);
					ps.setString(1, d);
					ps.setString(2, d2);
					ps.setString(3, "%" + str + "%");
				}

				rs = ps.executeQuery();
				getdata(rs, p, list);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.free(rs, ps, conn);
			return list;
		}

	}

	private static void getdata(ResultSet rs, PayManagBean p,List<PayManagBean> list) {
		// TODO Auto-generated method stub
		try {
			while (rs.next()) {
				p = new PayManagBean();
				p.setReaderId(rs.getInt("readerId"));
				p.setReaderName(rs.getString("readerName"));
				p.setPayWay(rs.getInt("payWay"));
				p.setPayMoney(rs.getDouble("payMoney"));
				p.setrealBackDate(rs.getString("realBackDate"));
				p.setUserName(rs.getString("userName"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
