package cn.com.beans.advanceBorrInfo;

public class AdvanceBorrInfoBean {
	private int advanceBorrId;
	private int userId;
	private int readerId;
	private int bookId;
	private String advanceBorrDate;
	public int getAdvanceBorrId() {
		return advanceBorrId;
	}
	public void setAdvanceBorrId(int advanceBorrId) {
		this.advanceBorrId = advanceBorrId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getAdvanceBorrDate() {
		return advanceBorrDate;
	}
	public void setAdvanceBorrDate(String advanceBorrDate) {
		this.advanceBorrDate = advanceBorrDate;
	}
	
	public String toString(){
		return this.advanceBorrDate;
	}
}
