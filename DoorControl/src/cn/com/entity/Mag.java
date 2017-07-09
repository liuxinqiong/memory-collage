package cn.com.entity;

public class Mag {
	private int magId;
	private String magName;
	private String magPassword;
	private int magType;
	
	public Mag(){
		
	}
	public Mag(int magId, String magName, String magPassword,int magType) {
		super();
		this.magId = magId;
		this.magName = magName;
		this.magPassword = magPassword;
		this.magType=magType;
	}
	public int getMagId() {
		return magId;
	}
	public void setMagId(int magId) {
		this.magId = magId;
	}
	public String getMagName() {
		return magName;
	}
	public void setMagName(String magName) {
		this.magName = magName;
	}
	public String getMagPassword() {
		return magPassword;
	}
	public void setMagPassword(String magPassword) {
		this.magPassword = magPassword;
	}
	public int getMagType() {
		return magType;
	}
	public void setMagType(int magType) {
		this.magType = magType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.magName;
	}
}
