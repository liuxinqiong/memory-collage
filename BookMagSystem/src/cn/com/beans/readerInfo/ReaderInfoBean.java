package cn.com.beans.readerInfo;

public class ReaderInfoBean {
	private int readerId;
	private String readerName;
	private String readerSex;
	private String readerCardType;
	private String readerIdentifyCard;
	private String readerTel;
	private String readerRegistDate;
	private int readerIsDel;
	private int readerState;
	private String readerPwd;
	private int readerVcState;
	private double readerVcBalance;
	private int readerTypeId;
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
	public String getReaderSex() {
		return readerSex;
	}
	public void setReaderSex(String readerSex) {
		this.readerSex = readerSex;
	}

	public String getReaderCardType() {
		return readerCardType;
	}
	public void setReaderCardType(String readerCardType) {
		this.readerCardType = readerCardType;
	}
	public String getReaderIdentifyCard() {
		return readerIdentifyCard;
	}
	public void setReaderIdentifyCard(String readerIdentifyCard) {
		this.readerIdentifyCard = readerIdentifyCard;
	}
	public String getReaderTel() {
		return readerTel;
	}
	public void setReaderTel(String readerTel) {
		this.readerTel = readerTel;
	}
	public String getReaderRegistDate() {
		return readerRegistDate;
	}
	public void setReaderRegistDate(String readerRegistDate) {
		this.readerRegistDate = readerRegistDate;
	}
	public int getReaderIsDel() {
		return readerIsDel;
	}
	public void setReaderIsDel(int readerIsDel) {
		this.readerIsDel = readerIsDel;
	}
	public int getReaderState() {
		return readerState;
	}
	public void setReaderState(int readerState) {
		this.readerState = readerState;
	}
	public String getReaderPwd() {
		return readerPwd;
	}
	public void setReaderPwd(String readerPwd) {
		this.readerPwd = readerPwd;
	}
	public int getReaderVcState() {
		return readerVcState;
	}
	public void setReaderVcState(int readerVcState) {
		this.readerVcState = readerVcState;
	}
	public double getReaderVcBalance() {
		return readerVcBalance;
	}
	public void setReaderVcBalance(double readerVcBalance) {
		this.readerVcBalance = readerVcBalance;
	}
	public int getReaderTypeId() {
		return readerTypeId;
	}
	public void setReaderTypeId(int readerTypeId) {
		this.readerTypeId = readerTypeId;
	}
	
	public String toString(){
		return this.readerName;
	}
}
