package cn.com.beans.tyb;

public class bookLoseBean {
	/**
	 * 图书丢失查询对象
	 */
	private int bookId;
	private String bookName;
	private String publisher;
	private double price;
	private int readerId;
	private String readerName;
	private String userName;
	private String realBackDate;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealBackDate() {
		return realBackDate;
	}
	public void setRealBackDate(String realBackDate) {
		this.realBackDate = realBackDate;
	}
	 
}
