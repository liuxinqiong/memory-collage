package cn.com.daos.userInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.db.DBUtil;

public class UserInfoDAOImp implements UserInfoDAOInf {

	/*public boolean addUser(UserInfoBean u)
     * 参数：用户信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：新增用户信息
     * */
	@Override
	public boolean addUser(UserInfoBean u) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into userInfo values(seq_userInfo_userId.Nextval,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, u.getUserName());
			pstm.setString(2, u.getUserWorkDate());
			pstm.setString(3, u.getUserTel());
			pstm.setString(4, u.getUserPwd());
			pstm.setInt(5, u.getUserType());
			pstm.setInt(6, u.getUserState());
			pstm.setInt(7, u.getUserIsDel());
			if (pstm.executeUpdate() == 0) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
		return bool;
	}

	/*public boolean updateUser(UserInfoBean u) 
     * 参数：用户信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：修改用户信息
     * */
	@Override
	public boolean updateUser(UserInfoBean u) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update userInfo set userName= ? ,userWorkDate=to_date(?,'yyyy-mm-dd'),userTel= ?,userPwd= ?,userType= ?,userState= ?,userIsDel=? where userId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, u.getUserName());
			pstm.setString(2, u.getUserWorkDate());
			pstm.setString(3, u.getUserTel());
			pstm.setString(4, u.getUserPwd());
			pstm.setInt(5, u.getUserType());
			pstm.setInt(6, u.getUserState());
			pstm.setInt(7, u.getUserIsDel());
			pstm.setInt(8, u.getUserId());
			if (pstm.executeUpdate() == 0) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
		return bool;
	}

	/*public boolean deleteUser(UserInfoBean u)
     * 参数：用户信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：删除用户信息
     * */
	@Override
	public boolean deleteUser(UserInfoBean u) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update userInfo set userIsDel=1 where userId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, u.getUserId());
			if (pstm.executeUpdate() == 0) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}

		return bool;
	}

	/*public boolean changePwdUser(UserInfoBean u)
     * 参数：用户信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：根据用户ID修改用户密码
     * */
	@Override
	public boolean changePwdUser(UserInfoBean u) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update userInfo set userPwd=? where userId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, u.getUserPwd());
			pstm.setInt(2, u.getUserId());
			if (pstm.executeUpdate() == 0) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
		return bool;
	}

	/*public List<UserInfoBean> getAllUserInfo()
     * 参数：用户信息实体
     * 返回值：list
     * 修饰符：public
     * 功能：获取所有的用户信息
     * */
	@Override
	public List<UserInfoBean> getAllUserInfo() {
		// TODO Auto-generated method stub
		List<UserInfoBean> list = new ArrayList<UserInfoBean>();
		UserInfoBean user = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from userInfo where userIsDel=0";
		try {
			pstm = conn.prepareStatement(sql);

			rs = pstm.executeQuery();
			while (rs.next()) {
				user = new UserInfoBean();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserWorkDate(rs.getString("userWorkDate").substring(0,10));
				user.setUserTel(rs.getString("userTel"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserType(rs.getInt("userType"));
				user.setUserState(rs.getInt("userState"));
				user.setUserIsDel(rs.getInt("userIsDel"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}

	/*public boolean validateByNameAndPwd(UserInfoBean u)
     * 参数：用户信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：通过用户名和密码查询所有的用户信息
     * */
	@Override
	public boolean validateByNameAndPwd(UserInfoBean u) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from userInfo where userName = ? and userPwd=? and userIsDel=0";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, u.getUserName());
			pstm.setString(2, u.getUserPwd());
			rs = pstm.executeQuery();
			bool = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return bool;
	}

	@Override
	public boolean checkUserName(UserInfoBean u) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from userInfo where userName = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, u.getUserName());
			rs = pstm.executeQuery();
			bool=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return bool;
	}

	/*public boolean checkUserName(UserInfoBean u)
     * 参数：用户信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：通过用户ID查询所有的用户信息
     * */
	@Override
	public UserInfoBean getUserInfoByUserId(UserInfoBean u) {
		// TODO Auto-generated method stub
		UserInfoBean user = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from userInfo where userId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, u.getUserId());
			rs = pstm.executeQuery();
			while (rs.next()) {
				user = new UserInfoBean();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserWorkDate(rs.getString("userWorkDate").substring(0,10));
				user.setUserTel(rs.getString("userTel"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserType(rs.getInt("userType"));
				user.setUserState(rs.getInt("userState"));
				user.setUserIsDel(rs.getInt("userIsDel"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return user;
	}

}
