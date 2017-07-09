package cn.com.beans.borrowInfo;

public class BorrowInfoBean {
	private int borrowId;
	private int bookId;
	private int borrowUserId;
	private int readerId;
	private int isBack;
	private String borrowDate;
	private String normalBackDate;
	private String realBackDate;
	private int backUserId;
	private double payMoney;
	private int payWay;
	private int isLoss;
	private int addTime;
	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBorrowUserId() {
		return borrowUserId;
	}
	public void setBorrowUserId(int borrowUserId) {
		this.borrowUserId = borrowUserId;
	}
	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	public int getIsBack() {
		return isBack;
	}
	public void setIsBack(int isBack) {
		this.isBack = isBack;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getNormalBackDate() {
		return normalBackDate;
	}
	public void setNormalBackDate(String normalBackDate) {
		this.normalBackDate = normalBackDate;
	}
	public String getRealBackDate() {
		return realBackDate;
	}
	public void setRealBackDate(String realBackDate) {
		this.realBackDate = realBackDate;
	}
	public int getBackUserId() {
		return backUserId;
	}
	public void setBackUserId(int backUserId) {
		this.backUserId = backUserId;
	}
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
	public int getPayWay() {
		return payWay;
	}
	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}
	public int getIsLoss() {
		return isLoss;
	}
	public void setIsLoss(int isLoss) {
		this.isLoss = isLoss;
	}
	public int getAddTime() {
		return addTime;
	}
	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}
	
	public String toString(){
		return this.borrowId+"";
	}
}
