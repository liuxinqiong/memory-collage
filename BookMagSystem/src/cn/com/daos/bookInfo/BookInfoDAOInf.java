package cn.com.daos.bookInfo;

import java.util.List;

import cn.com.beans.bookInfo.BookInfoBean;
import cn.com.beans.bookInfo.BookInfoViewBean;
import cn.com.beans.bookInfo.BookTypeInfoBean;

public interface BookInfoDAOInf {
	boolean addBookInfo(BookInfoBean book);
	boolean updateBookInfo(BookInfoBean book);
	boolean deleteBookInfo(BookInfoBean book);
	List<BookInfoBean> getAllBookInfo();
	
	boolean addBookTypeInfo(BookTypeInfoBean bookType);
	boolean updateBookTypeInfo(BookTypeInfoBean bookType);
	boolean deleteBookTypeInfo(BookTypeInfoBean bookType);
	List<BookTypeInfoBean> getAllBookTypeInfo();
	
	List<BookInfoViewBean> getAllBookViewInfo();
	
	boolean borrowOneBook(BookInfoBean book);
}
