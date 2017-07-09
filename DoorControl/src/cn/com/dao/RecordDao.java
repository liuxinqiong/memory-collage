package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.db.DBUtil;
import cn.com.entity.Record;
import cn.com.entity.User;

public class RecordDao {
	public boolean addRecord(Record r){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into door_record values(seq_door_record.nextval,?,sysdate)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, r.getUserId());
			int len = pstm.executeUpdate();
			if(len != 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.free(pstm, conn);
		}
		return bool;
	}
	
	//select *  from aq_askinfo where trunc(askdate) = trunc(to_date('2015-3-13','yyyy-mm-dd'));
	public int getOpenCount(User user,String time){
		int count=0;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		String sql = "select count(*) as count from door_record where userId = ? and trunc(openTime)=trunc(sysdate)";
		if(time!=null){
			sql="select count(*) as count from door_record where userId = ? and trunc(openTime)=trunc(to_date(?,'yyyy-mm-dd'))";
		}
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, user.getUserId());
			if(time!=null){
				pstm.setString(2, time);
			}
			rs = pstm.executeQuery();
			if(rs.next()){
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return count;
	}
	
	public List<Record> getAllRecord(){
		List<Record> list=new ArrayList<Record>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Record r=null;
		String sql = "select * from door_record";
		try {
			pstm=conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				r = new Record();
				r.setRecordId(rs.getInt("recordId"));
				r.setUserId(rs.getString("userId"));
				r.setOpenTime(rs.getString("openTime"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return list;
	}
}
