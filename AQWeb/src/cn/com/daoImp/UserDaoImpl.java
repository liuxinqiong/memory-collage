package cn.com.daoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.UserDao;
import cn.com.db.SqlCommand;
import cn.com.db.SqlStatement;
import cn.com.entity.Teacher;
import cn.com.entity.User;

public class UserDaoImpl implements UserDao {

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		Result result;
		List<User> users = new ArrayList<User>();
		String sql = SqlStatement.User.USER_INFO;
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			User user = new User();
			user.setUserNo(Integer.parseInt(String.valueOf(row.get("userNo"))));
			user.setUserName(row.get("userName").toString());
			user.setUserPwd(row.get("userPwd").toString());
			user.setUserType(Integer.parseInt(String.valueOf(row
					.get("userType"))));
			user.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
			users.add(user);
		}
		return users;
	}

	public User getUserInfo(User user) {

		Result result;
		User u = new User();
		String name = user.getUserName();
		Object args[] = new Object[1];
		args[0] = name;
	
		String sql = SqlStatement.User.USER_INFO + " and username = ? ";
		
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);

		SortedMap<String, Object>[] rows = result.getRows();
		

		if (rows.length != 0) {
			SortedMap<String, Object> row = rows[0];
			u.setUserNo(Integer.parseInt(String.valueOf(row.get("userNo"))));
			u.setUserName(row.get("userName").toString());
			u.setUserPwd(row.get("userPwd").toString());
			u.setUserType(Integer.parseInt(String.valueOf(row.get("userType"))));
			u.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
		}
		return u;
	}
	
	public User getUserInfoById(User user) {

		Result result;
		User u = new User();
		int userNo = user.getUserNo();
		Object args[] = new Object[1];
		args[0] = userNo;
	
		String sql = SqlStatement.User.USER_INFO + " and userno = ? ";
		
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);

		SortedMap<String, Object>[] rows = result.getRows();
		

		if (rows.length != 0) {
			SortedMap<String, Object> row = rows[0];
			u.setUserNo(Integer.parseInt(String.valueOf(row.get("userNo"))));
			u.setUserName(row.get("userName").toString());
			u.setUserPwd(row.get("userPwd").toString());
			u.setUserType(Integer.parseInt(String.valueOf(row.get("userType"))));
			u.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
		}
		return u;
	}
	
	public int getAnswerNumByTeaNo(Teacher teacher){
		Result result;
		String sql = SqlStatement.Answer.ANSWER_INFO +" and teacherno = ? ";//要执行的语句	
		int teaNo = teacher.getTeaNo();	
		Object args[] = new Object[1];
		args[0] = teaNo;
		SqlCommand command = new SqlCommand(sql, args);		
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		return rows.length;
	}

	public int delete(User user) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.User.USER_DELETE, new Object[]{user.getUserNo()}).execute(null);
	}

	public List<User> getUserByType(User u) {
		// TODO Auto-generated method stub
		Result result;
		List<User> users = new ArrayList<User>();
		String sql = SqlStatement.User.USER_INFO+" and usertype = ?";
		SqlCommand command = new SqlCommand(sql, new Object[]{u.getUserType()});
		result = command.getResult(null);
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			User user = new User();
			user.setUserNo(Integer.parseInt(String.valueOf(row.get("userNo"))));
			user.setUserName(row.get("userName").toString());
			user.setUserPwd(row.get("userPwd").toString());
			user.setUserType(Integer.parseInt(String.valueOf(row
					.get("userType"))));
			user.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
			users.add(user);
		}
		return users;
	}

	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.User.USER_INSERT, new Object[]{user.getUserName(),user.getUserPwd(),user.getUserType()}).execute(null);
	}

}
