package cn.com.serviceInf;

import cn.com.entity.User;

public interface UserServiceInf {
	
	public User loginValidate(User user);
	public User save(User user);
}
