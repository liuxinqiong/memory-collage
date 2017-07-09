package cn.com.entity;

public class User {
	private String userId;
	private String userName;
	private int userGradeId;
	
	public User(){
		
	}
	public User(String userId, String userName, int userGradeId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userGradeId = userGradeId;
	}
	public User(String userId) {
		this.userId=userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserGradeId() {
		return userGradeId;
	}
	public void setUserGradeId(int userGradeId) {
		this.userGradeId = userGradeId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.userName;
	}
}
