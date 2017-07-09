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

	public User loginValidate(User user) {// ���ύ����

		// �ṩһ��ʵ�ַ���:�õ���������
		String pwd = user.getPwd();
		// �������ݲ�
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
			// ����û��Ƿ����
			User u = this.userDao.getUserInfo(user);
			if (null == u) {
				return this.userDao.insert(user) > 0 ? this.userDao
						.getUserInfo(user) : null;
			}
		}
		return null;
	}
}
