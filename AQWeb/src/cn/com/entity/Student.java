package cn.com.entity;

/**
 * 学生对象
 * 
 * @author TYB
 * 
 */
public class Student {
	private int stuNo;
	private int professNo;
	private String stuName;
	private String stuPwd;
	private String stuEmail;
	private String stuPic;
	private int isDel;
	public int getStuNo() {
		return stuNo;
	}
	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}
	public int getProfessNo() {
		return professNo;
	}
	public void setProfessNo(int professNo) {
		this.professNo = professNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuPwd() {
		return stuPwd;
	}
	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}
	public String getStuEmail() {
		return stuEmail;
	}
	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}
	public String getStuPic() {
		return stuPic;
	}
	public void setStuPic(String stuPic) {
		this.stuPic = stuPic;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

}
