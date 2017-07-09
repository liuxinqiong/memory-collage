package cn.com.daos.borrowInfo;

import java.util.List;

import cn.com.beans.bookInfo.BookInfoBean;
import cn.com.beans.borrowInfo.BorrowInfoBean;
import cn.com.beans.borrowInfo.BorrowInfoViewBean;
import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.userInfo.UserInfoBean;

public interface BorrowInfoDAOInf {
	boolean addBorrowInfo(BorrowInfoBean bInfoBean);
	boolean borrowOneBook(BorrowInfoViewBean biv);
	List<BorrowInfoViewBean> getAllBorrowInfoView(String content);
	int getBorrowedNum(ReaderInfoBean r);
	List<BorrowInfoViewBean> getBorrowInfoByReaderId(ReaderInfoBean r);
	int borrowBooks(List<BookInfoBean> list,ReaderInfoBean reader,UserInfoBean user,String borrowDate,String normalBackDate);
	boolean updateBorrowInfo(BorrowInfoBean bInfoBean);
	List<BorrowInfoBean> getAllBorrowInfo();
}
