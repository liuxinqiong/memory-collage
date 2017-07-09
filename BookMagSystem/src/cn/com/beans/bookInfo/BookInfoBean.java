package cn.com.beans.bookInfo;

public class BookInfoBean {
	private int bookId;
	private int bookTypeId;
	private String bookName;
	private String writer;
	private String publisher;
	private String publishDate;
	private double price;
	private String bookRoom;
	private String bookShelf;
	private int bookTotalNum;
	private int bookOutNum;
	private int bookIsDel;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(int bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBookRoom() {
		return bookRoom;
	}
	public void setBookRoom(String bookRoom) {
		this.bookRoom = bookRoom;
	}
	public String getBookShelf() {
		return bookShelf;
	}
	public void setBookShelf(String bookShelf) {
		this.bookShelf = bookShelf;
	}
	public int getBookTotalNum() {
		return bookTotalNum;
	}
	public void setBookTotalNum(int bookTotalNum) {
		this.bookTotalNum = bookTotalNum;
	}
	public int getBookOutNum() {
		return bookOutNum;
	}
	public void setBookOutNum(int bookOutNum) {
		this.bookOutNum = bookOutNum;
	}
	public int getBookIsDel() {
		return bookIsDel;
	}
	public void setBookIsDel(int bookIsDel) {
		this.bookIsDel = bookIsDel;
	}
	
	public String toString(){
		return this.bookName;
	}
}
