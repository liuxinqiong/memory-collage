package cn.com.serviceImp;

import cn.com.daoImp.UserDaoImp;
import cn.com.daoInf.UserDaoInf;
import cn.com.entity.User;
import cn.com.serviceInf.UserServiceInf;

public class UserServiceImp implements UserServiceInf{
	public UserDaoInf userDao;
	
	public UserServiceImp(){
		userDao=new UserDaoImp();
	}

	public User loginValidate(User user) {// 面提交数据

		// 提供一种实现方案:得到输入密码
		String pwd = user.getPwd();
		// 请求数据层
		user = this.userDao.getUserInfo(user);

		if (null != user) {
			if (user.getPwd().equals(pwd)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User save(User user) {

		int uid = user.getId();
		if (uid > 0) {
			return this.userDao.update(user) > 0 ? this.userDao
					.getUserInfo(user) : null;// update insert
					
		} else {
			// 检查用户是否存在
			User u = this.userDao.getUserInfo(user);
			if (null == u) {
				return this.userDao.insert(user) > 0 ? this.userDao
						.getUserInfo(user) : null;
			}
		}
		return null;
	}
}
