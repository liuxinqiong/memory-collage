package cn.com.daos.journaInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.beans.journaInfo.JournaInfoBean;
import cn.com.db.DBUtil;

public class JournaInfoDAO {
	public static final int TYPE_LG = 0; // 操作员登录
	public static final int TYPE_BB = 1; // 图书借阅
	public static final int TYPE_RM = 2; // 读者管理
	public static final int TYPE_BM = 3; // 书刊管理
	public static final int TYPE_UM = 4; // 用户管理
	public static final int TYPE_JM = 5; // 日志管理
	public static String brief[] = { "操作员登录", "图书借阅", "读者管理", "书刊管理", "用户管理",
			"日志管理" };

	public static String getNowDTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/*public static boolean writeJournalInfo(String user, String content,
	int infoIndex)
	 * 参数：两个String,一个int
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：新增日志 信息
	 * */
	public static boolean writeJournalInfo(String user, String content,
			int infoIndex) {
		boolean bool = false;
		String dt = getNowDTime(); // 获得当前时间
		content = user + " 在 " + dt + " " + content; // 操作内容
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into journaInfo values(seq_journaInfo_journalId.Nextval,to_date('"
				+ dt
				+ "','yyyy-mm-dd HH24:mi:ss'),'"
				+ user
				+ "','"
				+ brief[infoIndex] + "','" + content + "')";
		try {
			pstm = conn.prepareStatement(sql);
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


	/*public List<JournaInfoBean> getAllJournalInfo()
     * 参数：无
     * 返回值：返回一个list型数组
     * 修饰符：public
     * 功能：获取所有的日志信息
     * */
	public List<JournaInfoBean> getAllJournalInfo() {
		// TODO Auto-generated method stub
		List<JournaInfoBean> list = new ArrayList<JournaInfoBean>();
		JournaInfoBean j = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from journaInfo";

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				j = new JournaInfoBean();
				j.setJournalId(rs.getInt("journalId"));
				j.setOperateTime(rs.getString("operateTime").substring(0, 19));
				j.setUserName(rs.getString("userName"));
				j.setBrief(rs.getString("brief"));
				j.setContent(rs.getString("content"));
				list.add(j);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}

	/*public List<JournaInfoBean> getAllJournalInfo()
     * 参数：无
     * 返回值：返回一个boolean型
     * 修饰符：public
     * 功能：删除所有的日志信息
     * */
	public boolean deleteAllInfo() {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "delete from journaInfo";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeUpdate();
			if (rs == 0) {
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

	/*public List<JournaInfoBean> getJournalInfoByBrief(String selectedItem)
     * 参数：传入查询信息
     * 返回值：返回一个list型数组
     * 修饰符：public
     * 功能：根据日志简要获取所有的日志信息
     * */
	public List<JournaInfoBean> getJournalInfoByBrief(String selectedItem) {
		// TODO Auto-generated method stub
		List<JournaInfoBean> list = new ArrayList<JournaInfoBean>();
		JournaInfoBean j = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from journaInfo where brief = ?";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, selectedItem);
			rs = pstm.executeQuery();
			while (rs.next()) {
				j = new JournaInfoBean();
				j.setJournalId(rs.getInt("journalId"));
				j.setOperateTime(rs.getString("operateTime").substring(0, 19));
				j.setUserName(rs.getString("userName"));
				j.setBrief(rs.getString("brief"));
				j.setContent(rs.getString("content"));
				list.add(j);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}

	public List<JournaInfoBean> getJournalInfoByBrief(String startTime,
			String endTime, String selectedItem) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<JournaInfoBean> list = null;
		List<JournaInfoBean> list2 = new ArrayList<JournaInfoBean>();
		if (selectedItem.equals("全部操作")) {
			list = getAllJournalInfo();
		} else {
			list = getJournalInfoByBrief(selectedItem);
		}
		for (JournaInfoBean j : list) {
			try {
				if (!(sdf.parse(j.getOperateTime()).before(sdf.parse(startTime)))
						&& !(sdf.parse(j.getOperateTime())
								.after(sdf.parse(endTime)))) {
					list2.add(j);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list2;
	}
}
