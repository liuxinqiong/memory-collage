package cn.com.daoInf;

import java.util.List;

import cn.com.entity.User;

public interface UserDao {
	public List<User> getAllUser(); 
	public User getUserInfo(User user);
	public User getUserInfoById(User user);
	public int delete(User user);
	public List<User> getUserByType(User user);
	public int insertUser(User user);
}
