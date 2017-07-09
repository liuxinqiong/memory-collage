package cn.com.entity;

public class AskInfoViewBean {
	private Student student;
	private Profession profession;
	private AskInfo askInfo;
	private Teacher teacher;
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Profession getProfession() {
		return profession;
	}
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	public AskInfo getAskInfo() {
		return askInfo;
	}
	public void setAskInfo(AskInfo askInfo) {
		this.askInfo = askInfo;
	}
	
	
}
