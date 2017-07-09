package cn.com.daoImp;

import java.util.SortedMap;
import javax.servlet.jsp.jstl.sql.Result;
import cn.com.daoInf.UserDaoInf;
import cn.com.db.PreSql;
import cn.com.db.SqlCommand;
import cn.com.entity.User;

public class UserDaoImp implements UserDaoInf{
	@Override
	public User getUserInfo(User user) {
		// TODO Auto-generated method stub
		User u = null;	
		Object[] args={user.getUname()};
		SqlCommand command = new SqlCommand(PreSql.SqlUser.SQLQUERY,args);
		Result result = command.getResult(null);
		// ��װ���ݣ�ӳ�䣨��ϵ������--������
		int count = result != null ? result.getRowCount() : 0;
		if (count != 0) {
			u = new User();
			SortedMap<String, Object> row = result.getRows()[0];
			u.setId(Integer.parseInt(String.valueOf(row.get("eu_user_id"))));
			u.setPwd(row.get("eu_password").toString());
			u.setUname(row.get("eu_user_name").toString());
		}
		return u;// ���ݿ⼶���û��������˱���������Ĵ��û���������Ϣ������
	}

	public int insert(User user) {
		return new SqlCommand(PreSql.SqlUser.INSERTSQL, new Object[] {
				user.getUname(), user.getPwd() })
				.execute(null);
	}
	
	public int update(User user) {
		return new SqlCommand(PreSql.SqlUser.UPDATESQL, new Object[] {
				user.getUname(), user.getPwd(),user.getId() })
				.execute(null);
	}
}
