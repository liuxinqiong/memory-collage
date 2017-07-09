package cn.com.entity;

import java.io.Serializable;

public class Product implements Serializable{
	private int pid;
	private String pname;
	private String pdescription;
	private double pprice;
	private int pstock;
	private int pcId;
	private int pcChildId;
	private String pfileName;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public double getPprice() {
		return pprice;
	}
	public void setPprice(double pprice) {
		this.pprice = pprice;
	}
	public int getPstock() {
		return pstock;
	}
	public void setPstock(int pstock) {
		this.pstock = pstock;
	}
	public int getPcId() {
		return pcId;
	}
	public void setPcId(int pcId) {
		this.pcId = pcId;
	}
	public int getPcChildId() {
		return pcChildId;
	}
	public void setPcChildId(int pcChildId) {
		this.pcChildId = pcChildId;
	}
	public String getPfileName() {
		return pfileName;
	}
	public void setPfileName(String pfileName) {
		this.pfileName = pfileName;
	}
	
	
}
