package cn.com.beans.tyb;

public class PersonBorrBean {
	/**
	 * 个人借阅查询对象
	 */
	private int borrincount;
	private int readerId;
	private String readerName;
	private String readerTypeName;
	private String readerTel;
	
	public int getBorrcount() {
		return borrincount;
	}
	public void setBorrcount(int borrincount) {
		this.borrincount = borrincount;
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
	public String getReaderTypeName() {
		return readerTypeName;
	}
	public void setReaderTypeName(String readerTypeName) {
		this.readerTypeName = readerTypeName;
	}
	public String getReaderTel() {
		return readerTel;
	}
	public void setReaderTel(String readerTel) {
		this.readerTel = readerTel;
	}
	
}
