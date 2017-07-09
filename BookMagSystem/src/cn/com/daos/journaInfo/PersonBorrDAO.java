package cn.com.daos.journaInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.tyb.PersonBorrBean;
import cn.com.db.DBUtil;

public class PersonBorrDAO {
	/** 
	 * 得到个人借阅信息统计对象并压在list数组中返回
	 */
	
	public static List<PersonBorrBean> getPersonBorrBean(String str,String txt,String txt1){
		List<PersonBorrBean> list = new ArrayList<PersonBorrBean>(); 
		String sql;
		PersonBorrBean p = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = DBUtil.getConn();
		
		if(str==null||txt==null||txt1==null){
			sql = "select count(*) as borrincount,readerInfo.readerId,readerName,readerTypeName,readerTel from borrowInfo,readerInfo,readerTypeInfo rt where readerIsDel = 0 and borrowInfo.readerId=readerInfo.readerId and readerInfo.readerTypeId =rt.readerTypeId group by readerInfo.readerId,readerName,readerTypeName,readerTel order by borrincount desc";
			try {
				ps = conn.prepareStatement(sql);
			    rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
		}
		else if(str.equals("所有类型")){
			sql = "select count(*) as borrincount,readerInfo.readerId,readerName,readerTypeName,readerTel from borrowInfo,readerInfo,readerTypeInfo rt where readerIsDel = 0 and borrowInfo.readerId=readerInfo.readerId and readerInfo.readerTypeId =rt.readerTypeId and borrowDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') group by readerInfo.readerId,readerName,readerTypeName,readerTel order by borrincount desc";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, txt);
				ps.setString(2, txt1);
			    rs = ps.executeQuery();    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			sql = "select count(*) as borrincount,readerInfo.readerId,readerName,readerTypeName,readerTel from borrowInfo,readerInfo,readerTypeInfo rt where readerIsDel = 0 and borrowInfo.readerId=readerInfo.readerId and readerInfo.readerTypeId =rt.readerTypeId and rt.readerTypeName = ? and borrowDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') group by readerInfo.readerId,readerName,readerTypeName,readerTel order by borrincount desc";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, str);
				ps.setString(2, txt);
				ps.setString(3, txt1);
			    rs = ps.executeQuery();    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		getdata(rs,p,list);
		DBUtil.free(rs, ps, conn);
		return list;
	}

	private static void getdata(ResultSet rs,PersonBorrBean p,List<PersonBorrBean> list) {
		// TODO Auto-generated method stub
		try {
			while(rs.next()){
				p = new PersonBorrBean();
				p.setBorrcount(rs.getInt("borrincount"));
				p.setReaderId(rs.getInt("readerId"));
				p.setReaderName(rs.getString("readerName"));
				p.setReaderTypeName(rs.getString("readerTypeName"));
				p.setReaderTel(rs.getString("readerTel"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

