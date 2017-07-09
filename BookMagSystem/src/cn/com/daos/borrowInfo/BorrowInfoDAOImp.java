package cn.com.daos.borrowInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.bookInfo.BookInfoBean;
import cn.com.beans.bookInfo.BookInfoViewBean;
import cn.com.beans.bookInfo.BookTypeInfoBean;
import cn.com.beans.borrowInfo.BorrowInfoBean;
import cn.com.beans.borrowInfo.BorrowInfoViewBean;
import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.db.DBUtil;
import cn.com.global.Global;

public class BorrowInfoDAOImp implements BorrowInfoDAOInf {

	/*public boolean borrowOneBook(BorrowInfoViewBean biv)
	 * 参数：传入一个图书借阅信息实体
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：新增图书借阅信息
	 * */
	@Override
	public boolean borrowOneBook(BorrowInfoViewBean biv) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "insert into borrowInfo values(seq_borrowInfo_borrowId.Nextval, ?, ?, ?,0,sysdate,to_date(?,'yyyy-mm-dd'),null,null,null,null,0,0)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, biv.getBookView().getBook().getBookId());
			pstm.setInt(2, biv.getBorrowUser().getUserId());
			pstm.setInt(3, biv.getReaderView().getReader().getReaderId());
			pstm.setString(
					4,
					Global.getDate(biv.getReaderView().getReaderType()
							.getDays()));
			rs = pstm.executeUpdate();
			if (rs != 0) {
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

	/*public List<BorrowInfoViewBean> getAllBorrowInfoView(String content)
	 * 参数：传入要查询的内容
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：查询所有借阅信息，支持模糊查询，传入空时查询全部
	 * */
	@Override
	public List<BorrowInfoViewBean> getAllBorrowInfoView(String content) {
		// TODO Auto-generated method stub
		List<BorrowInfoViewBean> list = new ArrayList<BorrowInfoViewBean>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		BorrowInfoViewBean view = null;
		BorrowInfoBean borrow = null;
		UserInfoBean borrowUser = null;
		UserInfoBean backUser = null;
		BookInfoViewBean bookView = null;
		ReaderInfoViewBean readerView = null;
		BookInfoBean book = null;
		BookTypeInfoBean bookType = null;
		ReaderInfoBean reader = null;
		ReaderTypeInfoBean readerType = null;
		System.out.println(content);
		String sql = "select readerInfo.*,maxNum,days,keepMoney,normalRent,extendedRent,lossTimes,readerTypeName,userInfo.*,bookInfo.*,bookTypeName,borrowId,isBack,borrowUserId,borrowDate,normalBackDate ,realBackDate,backUserId,payMoney,payWay,isLoss,addTime    from readerInfo,readerTypeInfo,userInfo,bookInfo,bookTypeInfo,borrowInfo where borrowInfo.borrowUserId=userInfo.userId and borrowInfo.readerId=readerInfo.readerId and borrowInfo.bookId=bookInfo.bookId  and  readerInfo.readerTypeId=readerTypeInfo.readerTypeId and bookInfo.bookTypeId=bookTypeInfo.bookTypeId ";
		if (!content.equals("")) {
			sql += " and  bookName like ? or readerName like ?";
		}
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			if (!content.equals("")) {
				pstm.setString(1, "%" + content + "%");
				pstm.setString(2, "%" + content + "%");
			}
			rs = pstm.executeQuery();
			while (rs.next()) {
				view = new BorrowInfoViewBean();
				borrow = new BorrowInfoBean();
				bookView = new BookInfoViewBean();
				readerView = new ReaderInfoViewBean();
				borrowUser = new UserInfoBean();
				backUser = new UserInfoBean();
				book = new BookInfoBean();
				bookType = new BookTypeInfoBean();
				reader = new ReaderInfoBean();
				readerType = new ReaderTypeInfoBean();

				borrow.setBorrowId(rs.getInt("borrowId"));
				int borrowUserId = rs.getInt("borrowUserId");
				borrow.setBorrowUserId(borrowUserId);
				borrowUser.setUserId(borrowUserId);
				int backUserId = rs.getInt("backUserId");
				borrow.setBackUserId(backUserId);
				backUser.setUserId(backUserId);
				int readerId = rs.getInt("readerId");
				borrow.setReaderId(readerId);
				reader.setReaderId(readerId);
				int bookId = rs.getInt("bookId");
				borrow.setBookId(bookId);
				book.setBookId(bookId);
				borrow.setIsBack(rs.getInt("isBack"));
				borrow.setBorrowDate(rs.getString("borrowDate")
						.substring(0, 10));
				borrow.setNormalBackDate(rs.getString("normalBackDate")
						.substring(0, 10));
				borrow.setRealBackDate(rs.getString("realBackDate"));
				borrow.setPayMoney(rs.getDouble("payMoney"));
				borrow.setPayWay(rs.getInt("payWay"));
				borrow.setIsLoss(rs.getInt("isLoss"));
				borrow.setAddTime(rs.getInt("addTime"));

				int bookTypeId = rs.getInt("bookTypeId");
				book.setBookTypeId(bookTypeId);
				bookType.setBookTypeId(bookTypeId);
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				book.setBookName(rs.getString("bookName"));
				book.setWriter(rs.getString("writer"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublishDate(rs.getString("publishDate")
						.substring(0, 10));
				book.setPrice(rs.getDouble("price"));
				book.setBookRoom(rs.getString("bookRoom"));
				book.setBookShelf(rs.getString("bookShelf"));
				book.setBookTotalNum(rs.getInt("bookTotalNum"));
				book.setBookOutNum(rs.getInt("bookOutNum"));
				book.setBookIsDel(rs.getInt("bookIsDel"));

				int readerTypeId = rs.getInt("readerTypeId");
				reader.setReaderTypeId(readerTypeId);
				readerType.setReaderTypeId(readerTypeId);
				reader.setReaderName(rs.getString("readerName"));
				reader.setReaderSex(rs.getString("readerSex"));
				reader.setReaderCardType(rs.getString("readerCardType"));
				reader.setReaderIdentifyCard(rs.getString("readerIdentifyCard"));
				reader.setReaderTel(rs.getString("readerTel"));
				reader.setReaderRegistDate(rs.getString("readerRegistDate")
						.substring(0, 10));

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

				view.setBorrow(borrow);
				view.setBookView(bookView);
				view.setReaderView(readerView);
				view.setBorrowUser(borrowUser);
				view.setBackUser(backUser);

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

	/*public int getBorrowedNum(ReaderInfoBean r)
	 * 参数：传入一个读者信息实体
	 * 返回值：返回一个int型
	 * 修饰符：public
	 * 功能：查询此读者已经借了多少本书
	 * */
	@Override
	public int getBorrowedNum(ReaderInfoBean r) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "select count(*) from borrowInfo where readerId= ? and isBack=0";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, r.getReaderId());
			rs = pstm.executeQuery();
			if (rs.next()) {
				num = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
		return num;
	}

	/* 参数：传入一个读者信息实体
	 * 返回值：返回一个list型数组
	 * 修饰符：public
	 * 功能：查询该读者借阅图书信息
	 * */
	@Override
	public List<BorrowInfoViewBean> getBorrowInfoByReaderId(ReaderInfoBean r) {
		// TODO Auto-generated method stub
		List<BorrowInfoViewBean> list = getAllBorrowInfoView("");
		List<BorrowInfoViewBean> bb = new ArrayList<BorrowInfoViewBean>();
		for (BorrowInfoViewBean b : list) {
			if (b.getReaderView().getReader().getReaderId() == r.getReaderId()) {
				bb.add(b);
			}
		}
		return bb;
	}

	
	@Override
	public int borrowBooks(List<BookInfoBean> list, ReaderInfoBean reader,
			UserInfoBean user, String borrowDate, String normalBackDate) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		int count = 0;
		String sql = "insert into borrowInfo values(seq_borrowInfo_borrowId.Nextval,?,?,?,0,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),null,null,null,null,0,0)";
		try {
			pstm = conn.prepareStatement(sql);
			for (int j = 0; j < list.size(); j++) {
				pstm.setInt(1, list.get(j).getBookId());
				pstm.setInt(2, user.getUserId());
				pstm.setInt(3, reader.getReaderId());
				pstm.setString(4, borrowDate);
				pstm.setString(5, normalBackDate);
				rs = pstm.executeUpdate();
				if (rs != 0) {
					count++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
		return count;
	}

	@Override
	public boolean updateBorrowInfo(BorrowInfoBean bInfoBean) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update borrowInfo set isBack=?,realBackDate=?,payMoney=?,payWay=?,isLoss=?,addTime=? where borrowId=?";
		int rs;
		
		System.out.println(bInfoBean.getNormalBackDate());
		try {
			pstm = conn.prepareStatement(sql);		
			pstm.setInt(1, bInfoBean.getIsBack());
			pstm.setString(2, bInfoBean.getRealBackDate());
			pstm.setDouble(3,bInfoBean.getPayMoney());
			pstm.setInt(4, bInfoBean.getPayWay());
			pstm.setInt(5,bInfoBean.getIsLoss());
			pstm.setInt(6, bInfoBean.getAddTime());
			pstm.setInt(7,bInfoBean.getBorrowId());
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

	@Override
	public List<BorrowInfoBean> getAllBorrowInfo() {
		// TODO Auto-generated method stub
		return null;
	}

//	public boolean addBorrowInfo(borrowInfoBean bInfoBean) {
//		// TODO Auto-generated method stub
//		boolean bool = false;
//		Connection conn = DBUtil.getConn();
//		PreparedStatement pstm = null;
//		int rs = 0;
//		String sql = "insert into borrowInfo(borrowId,bookId,borrowUserId,readerId,isBack,borrowDate,normalBackDate,isLoss,addTime) values(seq_bookInfo_bookId.nextval,?,?,?,?,?,?,?,?)";
//		try {
//			pstm = conn.prepareStatement(sql);
//			pstm.setInt(1,bInfoBean.getBookId());
//			pstm.setInt(2, bInfoBean.getBorrowUserId());
//			pstm.setInt(3, bInfoBean.getReaderId());
//			pstm.setInt(4, bInfoBean.getIsBack());
//			pstm.setDate(5, bInfoBean.getBorrowDate());
//			pstm.setDate(6, bInfoBean.getNormalBackDate());
//			pstm.setInt(7, bInfoBean.getIsLoss());
//			pstm.setInt(8, bInfoBean.getAddTime());
//			rs = pstm.executeUpdate();
//			if (rs == 0) {
//				bool = false;
//			} else {
//				bool = true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.free(pstm, conn);
//		}
//		
//		return bool;
//	}
	@Override
	public boolean addBorrowInfo(BorrowInfoBean bInfoBean) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				boolean bool = false;
				Connection conn = DBUtil.getConn();
				PreparedStatement pstm = null;
				int rs = 0;
				String sql = "insert into borrowInfo(borrowId,bookId,borrowUserId,readerId,isBack,borrowDate,normalBackDate,isLoss,addTime) values(seq_adBorrInfo_advanceBorrId.nextval,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?)";
				try {
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1,bInfoBean.getBookId());
					pstm.setInt(2, bInfoBean.getBorrowUserId());
					pstm.setInt(3, bInfoBean.getReaderId());
					pstm.setInt(4, bInfoBean.getIsBack());
					pstm.setString(5, bInfoBean.getBorrowDate());
					pstm.setString(6, bInfoBean.getNormalBackDate());
					pstm.setInt(7, bInfoBean.getIsLoss());
					pstm.setInt(8, bInfoBean.getAddTime());
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

}
