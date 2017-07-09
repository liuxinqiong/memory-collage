package cn.com.daos.journaInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.tyb.keeyMoneyBean;
import cn.com.db.DBUtil;

public class keeyMoneyDAO {
	/**
	 * 
	 * 得到得到押金查询对象并压在list数组中返回
	 */

	public static List<keeyMoneyBean> getallkeeymoney(){
		List<keeyMoneyBean> list = new ArrayList<keeyMoneyBean>();	
		Connection conn = DBUtil.getConn();
		String sql = "select k.readerId as readerId,readerName,money,operateTime,userName from keeyMoneyInfo k,userInfo u,readerInfo r where readerIsDel = 0 and k.userId = u.userId and r.readerid=k.readerid";
		keeyMoneyBean k;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				k = new keeyMoneyBean();
				k.setReaderId(rs.getInt("readerId"));
				k.setReaderName(rs.getString("readerName"));
				k.setMoney(rs.getDouble("money"));
				k.setOperateTime(rs.getString("operateTime"));
				k.setUserName(rs.getString("userName"));
				list.add(k);
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);
		return list;
	}
	
	public static List<keeyMoneyBean> getqurykeeymoney(String d,String d2,String str){
		List<keeyMoneyBean> list = new ArrayList<keeyMoneyBean>();	
		Connection conn = DBUtil.getConn();
		keeyMoneyBean k = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		
		if(str.equals("")){
			try {
				sql = "select k.readerId as readerId,readerName,money,operateTime,userName from keeyMoneyInfo k,userInfo u,readerInfo r where readerIsDel = 0 and k.userId = u.userId and r.readerid=k.readerid and operateTime between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd')";
				ps = conn.prepareStatement(sql);
				ps.setString(1, d);
				ps.setString(2, d2);
				rs = ps.executeQuery();
				getdata(rs,k,list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.free(rs, ps, conn);
			return list;
		}
		else{
			try{
				try {
					sql = "select k.readerId as readerId,readerName,money,operateTime,userName from keeyMoneyInfo k,userInfo u,readerInfo r where readerIsDel = 0 and k.userId = u.userId and r.readerid=k.readerid and operateTime between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') and (readerName like ? or r.readerId = ?)";

					ps = conn.prepareStatement(sql);
					ps.setString(1, d);
					ps.setString(2, d2);
					ps.setString(3, str);
					
					int temp = Integer.parseInt(str);
					ps.setInt(4, Integer.parseInt(str));
				} catch (java.lang.NumberFormatException e) {
					sql = "select k.readerId as readerId,readerName,money,operateTime,userName from keeyMoneyInfo k,userInfo u,readerInfo r where readerIsDel = 0 and k.userId = u.userId and r.readerid=k.readerid and operateTime between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') and readerName like ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, d);
					ps.setString(2, d2);
					ps.setString(3, "%"+str+"%");
				}
				
				rs = ps.executeQuery();
				getdata(rs,k,list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.free(rs, ps, conn);
			return list;
		}
	}
	
	
	private static void getdata(ResultSet rs,keeyMoneyBean k,List<keeyMoneyBean> list) {
		// TODO Auto-generated method stub
		try {
			while(rs.next()){
				k = new keeyMoneyBean();
				k.setReaderId(rs.getInt("readerId"));
				k.setReaderName(rs.getString("readerName"));
				k.setMoney(rs.getDouble("money"));
				k.setOperateTime(rs.getString("operateTime"));
				k.setUserName(rs.getString("userName"));
				list.add(k);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	
	
}
