package cn.com.daos.journaInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.tyb.BookBorrBean;
import cn.com.db.DBUtil;

public class BookBorrDAO {
	/**
	 * �õ�ͼ����Ĳ�ѯ����ѹ��list�����з���
	 */

	public static List<BookBorrBean> getBookBorrBean(String str,String date,String date1){
		List<BookBorrBean> list = new ArrayList<BookBorrBean>(); 
		BookBorrBean p = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(str==null||date==null||date1==null)
		{
			String sql = "select bookName,count(*) as borroutcount,bookTotalNum,writer,bookTypeName,publisher from borrowInfo bri,bookInfo bi,bookTypeInfo bti where bookIsDel = 0 and bri.bookId=bi.bookId and bti.bookTypeId = bi.bookTypeId group by bookName,bookTotalNum,writer,bookTypeName,publisher";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
			        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(str.equals("��������")){
			String sql = "select bookName,count(*) as borroutcount,bookTotalNum,writer,bookTypeName,publisher from borrowInfo bri,bookInfo bi,bookTypeInfo bti where bookIsDel = 0 and bri.bookId=bi.bookId and bti.bookTypeId = bi.bookTypeId and borrowDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') group by bookName,bookTotalNum,writer,bookTypeName,publisher";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, date);
				ps.setString(2, date1);
				rs = ps.executeQuery();
		    }
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			String sql = "select bookName,count(*) as borroutcount,bookTotalNum,writer,bookTypeName,publisher from borrowInfo bri,bookInfo bi,bookTypeInfo bti where bookIsDel = 0 and bri.bookId=bi.bookId and bookTypeName = ? and bti.bookTypeId = bi.bookTypeId and borrowDate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd') group by bookName,bookTotalNum,writer,bookTypeName,publisher";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, str);
				ps.setString(2, date);
				ps.setString(3, date1);
				rs = ps.executeQuery();
		    }
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		getdata(rs,p,list);
		DBUtil.free(rs, ps, conn);
		return list;
	}

	/*private static void getdata(ResultSet rs, BookBorrBean p,List<BookBorrBean> list) 
     * ����������һ��ResultSet��������Ϣʵ�壬list����
     * ����ֵ��void
     * ���η���public
     * ���ܣ��õ�����ͼ���йص�����
     * */
	private static void getdata(ResultSet rs, BookBorrBean p,List<BookBorrBean> list) {
		 try {
			while(rs.next()){
			    	p = new BookBorrBean();
			    	p.setBookName(rs.getString("bookName"));
			    	p.setBorroutcount(rs.getInt("borroutcount"));
			    	p.setBookTotalNum(rs.getInt("bookTotalNum"));
			    	p.setWriter(rs.getString("writer"));
			    	p.setBookTypeName(rs.getString("bookTypeName"));
			    	p.setPublisher(rs.getString("publisher"));
			    	list.add(p);
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

