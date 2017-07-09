package cn.com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class SqlCommand extends DBUtil {
	private String sql;
	private Object[] args;// 参数

	public SqlCommand(String sql, Object[] args) {
		this.sql = sql;
		this.args = args;
	}

	public Result getResult(Connection conn) {
		Connection connection = conn == null ? DBUtil.getConn() : conn;
		// 结果集：读取数据需要和数据库保持连接状态
		ResultSet rs = null;
		// 缓存的结果集：本地存储
		Result result = null;
		PreparedStatement pstm = null;
		try {
			pstm = connection.prepareStatement(this.sql);
			// 参数处理
			this.proArgs(pstm);
			rs = pstm.executeQuery();
			result = ResultSupport.toResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, connection);
		}
		return result;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	private void proArgs(PreparedStatement pState) {
		if (this.args == null)
			return;
		for (int i = 0; i < this.args.length; i++) {
			try {
				pState.setObject(i + 1, this.args[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	// 增、删除、更新
	public int execute(Connection conn) {
		Connection connection = conn == null ? DBUtil.getConn() : conn;
		PreparedStatement pstm = null;
		try {
			pstm = connection.prepareStatement(this.sql);
			this.proArgs(pstm);
			return pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn==null){
				DBUtil.free(pstm, connection);
			}	
		}
		return 0;
	}
}