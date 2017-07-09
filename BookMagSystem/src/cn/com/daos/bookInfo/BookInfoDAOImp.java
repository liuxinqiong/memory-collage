package cn.com.daos.bookInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.bookInfo.BookInfoBean;
import cn.com.beans.bookInfo.BookInfoViewBean;
import cn.com.beans.bookInfo.BookTypeInfoBean;
import cn.com.db.DBUtil;

public class BookInfoDAOImp implements BookInfoDAOInf{

	/*public boolean addBookInfo(BookInfoBean book)
	 * 参数：传入一个图书信息实体
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：新增图书信息
	 * */
	@Override
	public boolean addBookInfo(BookInfoBean book) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "insert into bookInfo values(seq_bookInfo_bookId,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, book.getBookTypeId());
			pstm.setString(2, book.getBookName());
			pstm.setString(3, book.getWriter());
			pstm.setString(4, book.getPublisher());
			pstm.setString(5, book.getPublishDate());
			pstm.setDouble(6, book.getPrice());
			pstm.setString(7,book.getBookRoom());
			pstm.setString(8,book.getBookShelf());
			pstm.setInt(9,book.getBookTotalNum());
			pstm.setInt(10, book.getBookOutNum());
			pstm.setInt(11, book.getBookIsDel());
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

	
	/*public boolean updateBookInfo(BookInfoBean book)
	 * 参数：传入一个图书信息实体
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：修改图书信息
	 * */
	@Override
	public boolean updateBookInfo(BookInfoBean book) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "update bookInfo set bookTypeId=?,bookName=?,writer=?,publisher=?,publishDate=?,price=?,bookRoom=?,bookShelf=?,bookTotalNum=?,bookOutNum=?,bookIsDel=? where bookId=?";
		try {
			pstm = conn.prepareStatement(sql);		
			pstm.setInt(1, book.getBookTypeId());
			pstm.setString(2, book.getBookName());
			pstm.setString(3, book.getWriter());
			pstm.setString(4, book.getPublisher());
			pstm.setString(5, book.getPublishDate());
			pstm.setDouble(6, book.getPrice());
			pstm.setString(7,book.getBookRoom());
			pstm.setString(8,book.getBookShelf());
			pstm.setInt(9, book.getBookTotalNum());
			pstm.setInt(10, book.getBookOutNum());
			pstm.setInt(11, book.getBookIsDel());
			pstm.setInt(12, book.getBookId());
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

	/*public boolean deleteBookInfo(BookInfoBean book)
	 * 形参：传入一个图书信息实体
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：逻辑删除图书信息
	 * */
	@Override
	public boolean deleteBookInfo(BookInfoBean book) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "update bookInfo set bookIsDel=1 where bookId=?";
		try {
			pstm = conn.prepareStatement(sql);		
			pstm.setInt(1, book.getBookId());
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

	/*public List<BookInfoBean> getAllBookInfo()
	 * 参数：无
	 * 返回值：返回一个list数组
	 * 修饰符：public
	 * 功能：获取所有的图书信息
	 * */
	@Override
	public List<BookInfoBean> getAllBookInfo() {
		// TODO Auto-generated method stub
		List<BookInfoBean> list = new ArrayList<BookInfoBean>();
		BookInfoBean b = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from bookInfo where bookIsDel=0";

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				b = new BookInfoBean();
				b.setBookId(rs.getInt("bookId"));
				b.setBookTypeId(rs.getInt("bookTypeId"));
				b.setBookName(rs.getString("BookName"));				
				b.setWriter(rs.getString("Writer"));
				b.setPublisher(rs.getString("Publisher"));
				b.setPublishDate(rs.getString("PublishDate").substring(0, 10));
				b.setPrice(rs.getDouble("Price"));
				b.setBookRoom(rs.getString("BookRoom"));
				b.setBookShelf(rs.getString("BookShelf"));
				b.setBookTotalNum(rs.getInt("bookTotalNum"));
				b.setBookOutNum(rs.getInt("bookOutNum"));
				b.setBookIsDel(rs.getInt("bookIsDel"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}

	/*public boolean addBookTypeInfo(BookTypeInfoBean bookType)
	 * 参数：传入图书类别信息实体
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：新增图书类别信息
	 * */
	@Override
	public boolean addBookTypeInfo(BookTypeInfoBean bookType) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "insert into bookTypeInfo values(seq_bookTypeInfo_bookTypeId,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bookType.getBookTypeName());
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

	/*public boolean updateBookTypeInfo(BookTypeInfoBean bookType)
	 * 参数：传入图书类别信息实体
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：修改图书类别信息
	 * */
	@Override
	public boolean updateBookTypeInfo(BookTypeInfoBean bookType) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "update bookTypeInfo set bookTypeName=? where bookTypeId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bookType.getBookTypeName());
			pstm.setInt(2, bookType.getBookTypeId());
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

	/*public boolean deleteBookTypeInfo(BookTypeInfoBean bookType)
	 * 参数：传入图书类别信息实体
	 * 返回值：返回一个boolean型
	 * 修饰符：public
	 * 功能：删除图书类别信息
	 * */

	@Override
	public boolean deleteBookTypeInfo(BookTypeInfoBean bookType) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "delete from bookTypeInfo where bookTypeId=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bookType.getBookTypeId());
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

	/*public List<BookTypeInfoBean> getAllBookTypeInfo()
	 * 参数：无
	 * 返回值：返回一个list型数组
	 * 修饰符：public
	 * 功能：获取所有的图书类别信息
	 * */

	@Override
	public List<BookTypeInfoBean> getAllBookTypeInfo() {
		// TODO Auto-generated method stub
		List<BookTypeInfoBean> list = new ArrayList<BookTypeInfoBean>();
		BookTypeInfoBean b = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from bookTypeInfo";

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				b = new BookTypeInfoBean();
				b.setBookTypeId(rs.getInt("bookTypeId"));
				b.setBookTypeName(rs.getString("bookTypeName"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}

	
	@Override
	public List<BookInfoViewBean> getAllBookViewInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrowOneBook(BookInfoBean book) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "update bookInfo set bookOutNum=?where bookId=?";
		try {
			pstm = conn.prepareStatement(sql);		
			pstm.setInt(1, book.getBookOutNum());
			pstm.setInt(2, book.getBookId());
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
