package cn.com.beans.tyb;

public class PayManagBean {
	/**
	 * 收款管理对象
	 */
	private int readerId;
	private String readerName;
	private int payWay;
	private double payMoney;
	private String  realBackDate;
	private String userName;
	
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
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
	public String getrealBackDate() {
		return realBackDate;
	}
	public void setrealBackDate(String realBackDate) {
		this.realBackDate = realBackDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPayWay() {
		return payWay;
	}
	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}
	
}
