package cn.com.beans.keepMoneyInfo;

public class KeeyMoneyInfoBean {
	private int recordId;
	private int userId;
	private int readerId;
	private String operateTime;
	private double money;
	private int useWay;
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
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
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getUseWay() {
		return useWay;
	}
	public void setUseWay(int useWay) {
		this.useWay = useWay;
	}
	
	public String toString(){
		return this.money+"";
	}
	
}
