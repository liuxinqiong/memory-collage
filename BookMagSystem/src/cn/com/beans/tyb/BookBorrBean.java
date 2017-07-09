package cn.com.beans.tyb;

public class BookBorrBean {
	/**
	 * 图书借阅查询对象
	 */
	private String bookName;
	private int borroutcount;
	private int bookTotalNum;
	private String writer;
	private String bookTypeName;
	private String publisher;
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBorroutcount() {
		return borroutcount;
	}
	public void setBorroutcount(int borroutcount) {
		this.borroutcount = borroutcount;
	}
	public int getBookTotalNum() {
		return bookTotalNum;
	}
	public void setBookTotalNum(int bookTotalNum) {
		this.bookTotalNum = bookTotalNum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
}
