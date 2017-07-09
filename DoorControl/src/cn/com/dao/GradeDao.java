package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.db.DBUtil;
import cn.com.entity.Grade;;

public class GradeDao {
	public boolean addGrade(Grade g){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into door_grade values(seq_door_grade.nextval,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, g.getGradeName());
			pstm.setInt(2, g.getGradeCount());
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
	
	public List<Grade>  getAllGrade(){
		List<Grade> list=new ArrayList<Grade>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Grade g=null;
		String sql = "select * from door_grade";
		try {
			pstm=conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				g = new Grade();
				g.setGradeId(rs.getInt("gradeId"));
				g.setGradeName(rs.getString("gradeName"));
				g.setGradeCount(rs.getInt("gradeCount"));
				list.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return list;
	}
	
	public boolean deleteGrade(Grade g){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "delete from  door_grade where gradeId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, g.getGradeId());
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
	
	public boolean updateGrade(Grade g){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update door_grade set gradeName =?,gradeCount=? where gradeId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, g.getGradeName());
			pstm.setInt(2, g.getGradeCount());
			pstm.setInt(3, g.getGradeId());
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
	
	public Grade getGradeById(int id){
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Grade g=null;
		String sql = "select * from door_grade where gradeId = ?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				g = new Grade();
				g.setGradeId(rs.getInt("gradeId"));
				g.setGradeName(rs.getString("gradeName"));
				g.setGradeCount(rs.getInt("gradeCount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return g;
	}
			
}
