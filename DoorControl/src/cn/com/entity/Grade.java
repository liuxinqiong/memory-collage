package cn.com.entity;

public class Grade {
	private int gradeId;
	private String gradeName;
	private int gradeCount;
	
	public Grade(){
		
	}

	public Grade(int gradeId, String gradeName, int gradeCount) {
		super();
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.gradeCount = gradeCount;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getGradeCount() {
		return gradeCount;
	}

	public void setGradeCount(int gradeCount) {
		this.gradeCount = gradeCount;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.gradeName;
	}

}
