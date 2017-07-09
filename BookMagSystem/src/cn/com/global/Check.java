package cn.com.global;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.com.db.DBUtil;

public abstract class Check {	
	public final static boolean isInt(String s) {
		boolean bool = false;
		try {
			Integer.valueOf(s);
			bool = true;
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}
	
	
	public final static boolean isDouble(String s) {
		boolean bool = false;
		try {
			Double.valueOf(s);
			bool = true;
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}
	
	public final static boolean checkMaxNum(int maxNum){
		boolean bool=false;
		if(maxNum>0&&maxNum<1000){
			bool=true;
		}else{
			bool=false;
		}
		return bool;
	}
	
	public final static boolean checkDays(int days){
		boolean bool=false;
		if(days>0&&days<1000){
			bool=true;
		}else{
			bool=false;
		}
		return bool;
	}
	
	public final static boolean checkAge(int age){
		boolean bool=false;
		if(age>0&&age<150){
			bool=true;
		}else{
			bool=false;
		}
		return bool;
	}
	
	public final static boolean checkMoney(double money){
		boolean bool=false;
		if(money>0&&money<100000000){
			bool=true;
		}else{
			bool=false;
		}
		return bool;
	}
	
	public final static boolean checkBookNum(int num){
		boolean bool=false;
		if(num>=0&&num<=1000){
			bool=true;
		}else{
			bool=false;
		}
		return bool;
	}
	
	public final static boolean isDate(String s) {
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into checkDate values(to_date('" + s
				+ "','yyyy-mm-dd'))";
		try {
			pstm = conn.prepareStatement(sql);
			try {
				pstm.executeUpdate();
				deleteCheckDate();
				bool = true;
			} catch (Exception e) {
				bool = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
		return bool;
	}

	public final static void deleteCheckDate() {
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "delete from checkDate";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
	}
}
