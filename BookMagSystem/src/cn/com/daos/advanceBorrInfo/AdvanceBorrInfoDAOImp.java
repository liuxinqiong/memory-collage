package cn.com.daos.advanceBorrInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.advanceBorrInfo.AdvanceBorrInfoBean;
import cn.com.beans.advanceBorrInfo.AdvanceBorrInfoViewBean;
import cn.com.beans.bookInfo.BookInfoBean;
import cn.com.beans.bookInfo.BookInfoViewBean;
import cn.com.beans.bookInfo.BookTypeInfoBean;
import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.db.DBUtil;

public class AdvanceBorrInfoDAOImp implements AdvanceBorrInfoDAOInf {
	/*
	 * public List<AdvanceBorrInfoViewBean> getAllAdvanceBorrInfo(String content)
	 * 参数：传一个String形参，如果为空，查询全部，不为空时，支持模糊查询
	 * 返回值：一个借阅信息的视图实体，包括读者信息，读者类型信息，图书信息，图书类别信息，借阅信息，用户信息
	 * 修饰符：public
	 * 功能：按照传入信息，做全部查询或模糊查询
	 * 注意出：借出用户实体和归还用户实体只包括了UserId。
	 * */
	@Override
	public List<AdvanceBorrInfoViewBean> getAllAdvanceBorrInfo(String content) {
		// TODO Auto-generated method stub
		List<AdvanceBorrInfoViewBean> list = new ArrayList<AdvanceBorrInfoViewBean>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		AdvanceBorrInfoViewBean view = null;
		AdvanceBorrInfoBean advanceBorr = null;
		BookInfoViewBean bookView = null;
		ReaderInfoViewBean readerView = null;
		UserInfoBean user = null;
		BookInfoBean book=null;
		BookTypeInfoBean bookType=null;
		ReaderInfoBean reader=null;
		ReaderTypeInfoBean readerType=null;
		String sql = "select readerInfo.*,maxNum,days,keepMoney,normalRent,extendedRent,lossTimes,readerTypeName,userInfo.*,bookInfo.*,bookTypeName,advanceBorrId,advanceBorrDate from readerInfo,readerTypeInfo,userInfo,bookInfo,bookTypeInfo,advanceBorrInfo where advanceBorrInfo.userId=userInfo.userId and advanceBorrInfo.readerId=readerInfo.readerId and advanceBorrInfo.bookId=bookInfo.bookId and readerInfo.readerTypeId=readerTypeInfo.readerTypeId and bookInfo.bookTypeId=bookTypeInfo.bookTypeId";
		if (!content.equals("")) {
			sql += " and readerName like ? or bookName like ?";
		}
		try {
			pstm = conn.prepareStatement(sql);
			if (!content.equals("")) {
				pstm.setString(1, "%" + content + "%");
				pstm.setString(2, "%" + content + "%");
			}
			rs = pstm.executeQuery();
			while (rs.next()) {
				view = new AdvanceBorrInfoViewBean();
				advanceBorr = new AdvanceBorrInfoBean();
				bookView = new BookInfoViewBean();
				readerView = new ReaderInfoViewBean();;
				user = new UserInfoBean();
				book=new BookInfoBean();
				bookType=new BookTypeInfoBean();
				reader=new ReaderInfoBean();
				readerType=new ReaderTypeInfoBean();
				
				advanceBorr.setAdvanceBorrDate(rs.getString("advanceBorrDate").substring(0, 19));
				advanceBorr.setAdvanceBorrId(rs.getInt("advanceBorrId"));
				int userId=rs.getInt("userId");
				advanceBorr.setUserId(userId);
				user.setUserId(userId);
				int readerId=rs.getInt("readerId");
				advanceBorr.setReaderId(readerId);
				reader.setReaderId(readerId);
				int bookId=rs.getInt("bookId");
				advanceBorr.setBookId(bookId);
				book.setBookId(bookId);
				
				user.setUserName(rs.getString("userName"));
				user.setUserWorkDate(rs.getString("userWorkDate").substring(0,10));
				user.setUserTel(rs.getString("userTel"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserType(rs.getInt("userType"));
				user.setUserState(rs.getInt("userState"));
				user.setUserIsDel(rs.getInt("userIsDel"));
				
				int bookTypeId=rs.getInt("bookTypeId");
				book.setBookTypeId(bookTypeId);
				bookType.setBookTypeId(bookTypeId);
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				book.setBookName(rs.getString("bookName"));
				book.setWriter(rs.getString("writer"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublishDate(rs.getString("publishDate").substring(0,10));
				book.setPrice(rs.getDouble("price"));
				book.setBookRoom(rs.getString("bookRoom"));
				book.setBookShelf(rs.getString("bookShelf"));
				book.setBookTotalNum(rs.getInt("bookTotalNum"));
				book.setBookOutNum(rs.getInt("bookOutNum"));
				book.setBookIsDel(rs.getInt("bookIsDel"));
				
				int readerTypeId=rs.getInt("readerTypeId");
				reader.setReaderTypeId(readerTypeId);
				readerType.setReaderTypeId(readerTypeId);
				reader.setReaderName(rs.getString("readerName"));			
				reader.setReaderSex(rs.getString("readerSex"));
				reader.setReaderCardType(rs.getString("readerCardType"));			
				reader.setReaderIdentifyCard(rs.getString("readerIdentifyCard"));
				reader.setReaderTel(rs.getString("readerTel"));
				reader.setReaderRegistDate(rs.getString("readerRegistDate").substring(0,10));
				
				reader.setReaderIsDel(rs.getInt("readerIsDel"));
				reader.setReaderState(rs.getInt("readerState"));
				reader.setReaderPwd(rs.getString("readerPwd"));
				reader.setReaderVcState(rs.getInt("readerVcState"));
				reader.setReaderVcBalance(rs.getDouble("readerVcBalance"));

				readerType.setMaxNum(rs.getInt("maxNum"));
				readerType.setDays(rs.getInt("days"));
				readerType.setKeepMoney(rs.getDouble("keepMoney"));
				readerType.setNormalRent(rs.getDouble("normalRent"));
				readerType.setExtendedRent(rs.getInt("extendedRent"));
				readerType.setLossTimes(rs.getInt("lossTimes"));
				readerType.setReaderTypeName(rs.getString("readerTypeName"));
				
				bookView.setBook(book);
				bookView.setBookType(bookType);
				
				readerView.setReader(reader);
				readerView.setReaderType(readerType);
				
				view.setAdvanceBorrInfo(advanceBorr);
				view.setBookView(bookView);
				view.setReaderView(readerView);
				view.setUser(user);
				
				list.add(view);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}

	
	/*	public boolean deleteAdvanceBorrInfo(AdvanceBorrInfoBean abi)
	 * 参数：传入一个预借信息实体
	 * 返回值：返回一个boolean
	 * 修饰符：public
	 * 功能：根据预借ID删除预借信息实体
	 * */
	@Override
	public boolean deleteAdvanceBorrInfo(AdvanceBorrInfoBean abi) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "delete from advanceBorrInfo where advanceBorrId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, abi.getAdvanceBorrId());
			if (pstm.executeUpdate() != 0) {
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

}
