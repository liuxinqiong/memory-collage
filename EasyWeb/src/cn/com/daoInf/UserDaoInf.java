package cn.com.daoInf;

import cn.com.entity.User;

public interface UserDaoInf {
	public User getUserInfo(User user);
	public int insert(User user);

	public int update(User user);
}
