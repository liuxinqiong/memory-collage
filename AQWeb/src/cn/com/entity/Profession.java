package cn.com.entity;

/**
 * 
 * @author TYB
 * 
 */
public class Profession {
	private int professionNo;
	private String professionName;
	private int academyNo;
	private int isDel;

	public int getProfessionNo() {
		return professionNo;
	}

	public void setProfessionNo(int professionNo) {
		this.professionNo = professionNo;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public int getAcademyNo() {
		return academyNo;
	}

	public void setAcademyNo(int academyNo) {
		this.academyNo = academyNo;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
