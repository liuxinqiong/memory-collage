package cn.com.beans.userInfo;

public class UserInfoBean {
	private int userId;
	private String userName;
	private String userWorkDate;
	private String userTel;
	private String userPwd;
	private int userType;
	private int userState;
	private int userIsDel;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserWorkDate() {
		return userWorkDate;
	}
	public void setUserWorkDate(String userWorkDate) {
		this.userWorkDate = userWorkDate;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public int getUserIsDel() {
		return userIsDel;
	}
	public void setUserIsDel(int userIsDel) {
		this.userIsDel = userIsDel;
	}
	
	public String toString (){
		return this.userName;
	}
}
