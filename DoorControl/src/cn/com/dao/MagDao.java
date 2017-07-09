package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.db.DBUtil;
import cn.com.entity.Mag;

public class MagDao {
	public boolean addMag(Mag mag){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into door_mag values(seq_door_mag.nextval,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mag.getMagName());
			pstm.setString(2, mag.getMagPassword());
			pstm.setInt(3, mag.getMagType());
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
	
	public List<Mag>  getAllMag(){
		List<Mag> list=new ArrayList<Mag>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		Mag m=null;
		String sql = "select * from door_mag";
		try {
			pstm=conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				m = new Mag();
				m.setMagId(rs.getInt("magId"));
				m.setMagName(rs.getString("magName"));
				m.setMagPassword(rs.getString("magPassword"));
				m.setMagType(rs.getInt("magType"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return list;
	}
	
	
	
	public Mag volidate(Mag mag){
		Mag m=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		String sql = "select * from door_mag where magName = ? and magPassword = ?";
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, mag.getMagName());
			pstm.setString(2, mag.getMagPassword());
			rs = pstm.executeQuery();
			if(rs.next()){
				m = new Mag();
				m.setMagId(rs.getInt("magId"));
				m.setMagName(rs.getString("magName"));
				m.setMagPassword(rs.getString("magPassword"));
				m.setMagType(rs.getInt("magType"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return m;
	}
	
	public boolean deleteMag(Mag mag){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "delete from  door_mag where magId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, mag.getMagId());
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
	
	public boolean updateMag(Mag mag){
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update door_mag set magName =?,magPassword=?,magType=? where magId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mag.getMagName());
			pstm.setString(2, mag.getMagPassword());
			pstm.setInt(3, mag.getMagType());
			pstm.setInt(4, mag.getMagId());
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
