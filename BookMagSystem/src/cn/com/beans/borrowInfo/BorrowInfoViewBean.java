package cn.com.beans.borrowInfo;

import cn.com.beans.bookInfo.BookInfoViewBean;
import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.userInfo.UserInfoBean;

public class BorrowInfoViewBean {
	private BorrowInfoBean borrow;
	private UserInfoBean borrowUser;
	private UserInfoBean backUser;
	private BookInfoViewBean bookView;
	private ReaderInfoViewBean readerView;
	public BorrowInfoBean getBorrow() {
		return borrow;
	}
	public void setBorrow(BorrowInfoBean borrow) {
		this.borrow = borrow;
	}
	public UserInfoBean getBorrowUser() {
		return borrowUser;
	}
	public void setBorrowUser(UserInfoBean borrowUser) {
		this.borrowUser = borrowUser;
	}
	public UserInfoBean getBackUser() {
		return backUser;
	}
	public void setBackUser(UserInfoBean backUser) {
		this.backUser = backUser;
	}
	public BookInfoViewBean getBookView() {
		return bookView;
	}
	public void setBookView(BookInfoViewBean bookView) {
		this.bookView = bookView;
	}
	public ReaderInfoViewBean getReaderView() {
		return readerView;
	}
	public void setReaderView(ReaderInfoViewBean readerView) {
		this.readerView = readerView;
	}	
	
	public  String toString(){
		return this.bookView.toString();
	}
}
