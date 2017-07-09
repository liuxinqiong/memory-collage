package cn.com.beans.readerInfo;

public class ReaderTypeInfoBean {
	private int readerTypeId;
	private String readerTypeName;
	private int maxNum;
	private int days;
	private double keepMoney;
	private double normalRent;
	private double extendedRent;
	private int lossTimes;
	private int IsDel;
	public int getIsDel() {
		return IsDel;
	}
	public void setIsDel(int isDel) {
		IsDel = isDel;
	}
	public int getReaderTypeId() {
		return readerTypeId;
	}
	public void setReaderTypeId(int readerTypeId) {
		this.readerTypeId = readerTypeId;
	}
	public String getReaderTypeName() {
		return readerTypeName;
	}
	public void setReaderTypeName(String readerTypeName) {
		this.readerTypeName = readerTypeName;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getKeepMoney() {
		return keepMoney;
	}
	public void setKeepMoney(double keepMoney) {
		this.keepMoney = keepMoney;
	}
	public double getNormalRent() {
		return normalRent;
	}
	public void setNormalRent(double normalRent) {
		this.normalRent = normalRent;
	}
	public double getExtendedRent() {
		return extendedRent;
	}
	public void setExtendedRent(double extendedRent) {
		this.extendedRent = extendedRent;
	}
	public int getLossTimes() {
		return lossTimes;
	}
	public void setLossTimes(int lossTimes) {
		this.lossTimes = lossTimes;
	}
	public String toString(){
		return this.readerTypeName;
	}
	
}
