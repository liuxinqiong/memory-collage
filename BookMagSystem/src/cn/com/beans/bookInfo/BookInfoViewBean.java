package cn.com.beans.bookInfo;

public class BookInfoViewBean {
	BookInfoBean book;
	BookTypeInfoBean bookType;
	public BookInfoBean getBook() {
		return book;
	}
	public void setBook(BookInfoBean book) {
		this.book = book;
	}
	public BookTypeInfoBean getBookType() {
		return bookType;
	}
	public void setBookType(BookTypeInfoBean bookType) {
		this.bookType = bookType;
	}
	
	public String toString(){
		return this.book.toString();
	}
	
}
