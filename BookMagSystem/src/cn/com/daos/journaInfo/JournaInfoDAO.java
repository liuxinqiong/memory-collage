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
	public static final int TYPE_LG = 0; // ����Ա��¼
	public static final int TYPE_BB = 1; // ͼ�����
	public static final int TYPE_RM = 2; // ���߹���
	public static final int TYPE_BM = 3; // �鿯����
	public static final int TYPE_UM = 4; // �û�����
	public static final int TYPE_JM = 5; // ��־����
	public static String brief[] = { "����Ա��¼", "ͼ�����", "���߹���", "�鿯����", "�û�����",
			"��־����" };

	public static String getNowDTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/*public static boolean writeJournalInfo(String user, String content,
	int infoIndex)
	 * ����������String,һ��int
	 * ����ֵ������һ��boolean��
	 * ���η���public
	 * ���ܣ�������־ ��Ϣ
	 * */
	public static boolean writeJournalInfo(String user, String content,
			int infoIndex) {
		boolean bool = false;
		String dt = getNowDTime(); // ��õ�ǰʱ��
		content = user + " �� " + dt + " " + content; // ��������
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
     * ��������
     * ����ֵ������һ��list������
     * ���η���public
     * ���ܣ���ȡ���е���־��Ϣ
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
     * ��������
     * ����ֵ������һ��boolean��
     * ���η���public
     * ���ܣ�ɾ�����е���־��Ϣ
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
     * �����������ѯ��Ϣ
     * ����ֵ������һ��list������
     * ���η���public
     * ���ܣ�������־��Ҫ��ȡ���е���־��Ϣ
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
		if (selectedItem.equals("ȫ������")) {
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
