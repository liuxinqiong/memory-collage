package cn.com.beans.advanceBorrInfo;

import cn.com.beans.bookInfo.BookInfoViewBean;
import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.userInfo.UserInfoBean;

public class AdvanceBorrInfoViewBean {
	private UserInfoBean user;
	private AdvanceBorrInfoBean AdvanceBorrInfo;
	private BookInfoViewBean bookView;
	private ReaderInfoViewBean readerView;
	
	public UserInfoBean getUser() {
		return user;
	}

	public void setUser(UserInfoBean user) {
		this.user = user;
	}

	public AdvanceBorrInfoBean getAdvanceBorrInfo() {
		return AdvanceBorrInfo;
	}

	public void setAdvanceBorrInfo(AdvanceBorrInfoBean advanceBorrInfo) {
		AdvanceBorrInfo = advanceBorrInfo;
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

	public String toString(){
		return this.AdvanceBorrInfo.toString();
	}
}
