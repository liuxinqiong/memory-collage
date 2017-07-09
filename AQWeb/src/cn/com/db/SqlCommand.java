package cn.com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class SqlCommand extends DBUtil {
	private String sql;
	private Object[] args;// ����

	public SqlCommand(String sql, Object[] args) {
		this.sql = sql;
		this.args = args;
	}

	public Result getResult(Connection conn) {
		Connection connection = conn == null ? DBUtil.getConn() : conn;
		// ���������ȡ������Ҫ�����ݿⱣ������״̬
		ResultSet rs = null;
		// ����Ľ���������ش洢
		Result result = null;
		PreparedStatement pstm = null;
		try {
			pstm = connection.prepareStatement(this.sql);
			// ��������
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

	// ����ɾ��������
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