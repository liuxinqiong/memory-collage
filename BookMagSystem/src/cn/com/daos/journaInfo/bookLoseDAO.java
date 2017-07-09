package cn.com.daos.journaInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.tyb.bookLoseBean;
import cn.com.db.DBUtil;


public class bookLoseDAO {
	/**
	 * �õ�ͼ�鶪ʧ��ѯ����ѹ��list�����з���
	 */
	
	public static List<bookLoseBean> getallbookLoseinfo(){
		List<bookLoseBean> list = new ArrayList<bookLoseBean>();
		bookLoseBean k;
		Connection conn = DBUtil.getConn();
		String sql = "select bi.bookId as bookId,bookName,publisher,price,r.readerId as readerId,readerName,realBackDate,userName from bookInfo bi,userInfo u,borrowInfo bw,readerInfo r where bookIsDel = 0 and bi.bookId = bw.bookId and bw.readerId = r.readerId and bw.borrowUserId = u.userId and isLoss = 1";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				k = new bookLoseBean();
				k.setBookId(rs.getInt("bookId"));
				k.setBookName(rs.getString("bookName"));
				k.setPublisher(rs.getString("publisher"));
				k.setPrice(rs.getDouble("price"));
				k.setReaderId(rs.getInt("readerId"));
				k.setReaderName(rs.getString("readerName"));
				k.setRealBackDate(rs.getString("realBackDate"));
				k.setUserName(rs.getString("userName"));
				list.add(k);
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);
		return list;
	}
	
	/*public static List<bookLoseBean> getqurybookLose(String d,String d2,String str)
     * ��������������String��
     * ����ֵ������һ��list������
     * ���η���public
     * ���ܣ��õ�ͼ�鶪ʧ�����Ϣ��ѹ��list�����з���
     * */
	public static List<bookLoseBean> getqurybookLose(String d,String d2,String str){
		List<bookLoseBean> list = new ArrayList<bookLoseBean>();
		bookLoseBean k = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		
		if(str.equals("")){
			sql = "select bi.bookId as bookId,bookName,publisher,price,r.readerId as readerId,readerName,realBackDate,userName from bookInfo bi,userInfo u,borrowInfo bw,readerInfo r where bookIsDel = 0 and bi.bookId = bw.bookId and bw.readerId = r.readerId and bw.borrowUserId = u.userId and isLoss = 1 and realBackDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd')";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1,d);
				ps.setString(2,d2);
				
				rs = ps.executeQuery();	
				getdata(rs,k,list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				DBUtil.free(rs, ps, conn);
				return list;
		 }
		
	
		else {
			try {
			try {
				sql = "select bi.bookId as bookId,bookName,publisher,price,r.readerId as readerId,readerName,realBackDate,userName from bookInfo bi,userInfo u,borrowInfo bw,readerInfo r where bookIsDel = 0 and bi.bookId = bw.bookId and bw.readerId = r.readerId and bw.borrowUserId = u.userId and isLoss = 1 and realBackDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') and (bookName like ? or r.readerName like ? or r.readerId = ? or bi.bookId = ?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1,d);
				ps.setString(2,d2);
				ps.setString(3,"%"+str+"%");
				ps.setString(4,"%"+str+"%");
				int temp = Integer.parseInt(str);
				ps.setInt(5,temp);
				ps.setInt(6,temp);
				
			}  catch (java.lang.NumberFormatException e) {
				
				sql = "select bi.bookId as bookId,bookName,publisher,price,r.readerId as readerId,readerName,realBackDate,userName from bookInfo bi,userInfo u,borrowInfo bw,readerInfo r where bookIsDel = 0 and bi.bookId = bw.bookId and bw.readerId = r.readerId and bw.borrowUserId = u.userId and isLoss = 1 and realBackDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') and (bookName like ? or r.readerName like ?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, d);
				ps.setString(2, d2);
				ps.setString(3,"%"+str+"%");
				ps.setString(4,"%"+str+"%");
		
			}
			rs = ps.executeQuery();
			getdata(rs,k,list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				DBUtil.free(rs, ps, conn);
				return list;
		}
  }

	/*private static void getdata(ResultSet rs,bookLoseBean k,List<bookLoseBean> list)
     * �������ֱ���һ��ResultSet��ͼ�鶪ʧ��Ϣʵ�壬list����
     * ����ֵ��void
     * ���η���public
     * ���ܣ��õ�ͼ�鶪ʧ�������
     * */
	private static void getdata(ResultSet rs,bookLoseBean k,List<bookLoseBean> list) {
		// TODO Auto-generated method stub
		try {
			while(rs.next()){
				k = new bookLoseBean();
				k.setBookId(rs.getInt("bookId"));
				k.setBookName(rs.getString("bookName"));
				k.setPublisher(rs.getString("publisher"));
				k.setPrice(rs.getDouble("price"));
				k.setReaderId(rs.getInt("readerId"));
				k.setReaderName(rs.getString("readerName"));
				k.setRealBackDate(rs.getString("realBackDate"));
				k.setUserName(rs.getString("userName"));
				list.add(k);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
