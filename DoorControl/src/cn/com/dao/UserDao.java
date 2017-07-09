package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.db.DBUtil;
import cn.com.entity.User;

public class UserDao {
	public boolean addUser(User user){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into door_user values(?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserId());
			pstm.setString(2, user.getUserName());
			pstm.setInt(3, user.getUserGradeId());
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
	
	public int existNum(int gradeId){
		int count=0;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		String sql = "select count(*) as count from door_user where userGradeId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, gradeId);
			rs=pstm.executeQuery();
			if(rs.next()){
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.free(pstm, conn);
		}
		return count;
	}
	
	public User getUserById(User user){
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		User u=null;
		String sql = "select * from door_user where userId = ?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, user.getUserId());
			rs = pstm.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getString("userId"));
				u.setUserName(rs.getString("userName"));
				u.setUserGradeId(rs.getInt("userGradeId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return u;
	}
	
	public List<User>  getAllUser(){
		List<User> list=new ArrayList<User>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		User u=null;
		String sql = "select * from door_user";
		try {
			pstm=conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setUserId(rs.getString("userId"));
				u.setUserName(rs.getString("userName"));
				u.setUserGradeId(rs.getInt("userGradeId"));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return list;
	}
	
	public boolean volidate(User user){
		boolean bool=false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		String sql = "select * from door_user where userId = ?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, user.getUserId());
			rs = pstm.executeQuery();
			bool=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return bool;
	}
	
	public boolean deleteUser(User user){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "delete from  door_user where userId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserId());
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
	
	public boolean updateUser(User user){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update door_user set userName =?,userGradeId=? where userId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserName());
			pstm.setInt(2, user.getUserGradeId());
			pstm.setString(3, user.getUserId());
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
	
}
