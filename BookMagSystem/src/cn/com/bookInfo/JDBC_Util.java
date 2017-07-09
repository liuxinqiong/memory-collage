package cn.com.bookInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBC_Util {
	static String driver="oracle.jdbc.OracleDriver";
	static String url="jdbc:oracle:thin:@localhost:1521:orcl";
	static String user="scott";
	static String password="tiger";
	
	//提供连接
	public static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//关闭连接
	public static void closeConnection(
			Connection conn,PreparedStatement ps,ResultSet rs){
		try{
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void closeConnection(
			Connection conn,PreparedStatement ps){
		closeConnection(conn, ps,null);
	}


}
