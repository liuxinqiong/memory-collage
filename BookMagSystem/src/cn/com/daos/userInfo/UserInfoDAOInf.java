package cn.com.daos.userInfo;

import java.util.List;

import cn.com.beans.userInfo.UserInfoBean;

public interface UserInfoDAOInf {
	boolean addUser(UserInfoBean u);
	boolean updateUser(UserInfoBean u);
	boolean deleteUser(UserInfoBean u);
	boolean changePwdUser(UserInfoBean u);
	List<UserInfoBean> getAllUserInfo();
	public boolean validateByNameAndPwd(UserInfoBean u);
	public boolean checkUserName(UserInfoBean u);
	public UserInfoBean getUserInfoByUserId(UserInfoBean u);
}
