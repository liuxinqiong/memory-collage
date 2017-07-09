package cn.com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daoImp.UserDaoImpl;
import cn.com.daoInf.UserDao;
import cn.com.entity.User;

@SuppressWarnings("serial")
public class BgmUserServlet extends HttpServlet {
	UserDao userDao;
	User userLogin;
	public BgmUserServlet(){
		userDao = new UserDaoImpl();
		userLogin = new User();
	}
	
	public void init() throws ServletException {
		// Put your code here
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = req.getParameter("command");
		
		String userNo = req.getParameter("userNo");
		if(userNo==null){
			if("addUser".equals(command)){
				addUser(req,resp);
			}
		}
		else{
			userLogin.setUserNo(Integer.parseInt(userNo));
			userLogin = userDao.getUserInfoById(userLogin);
			if(userLogin.getUserType()==1){
				if("userAllInfo".equals(command)){
					userAllInfo(req,resp);
				}
				else if("deleteUser".equals(command)){
					deleteUser(req,resp);
					userAllInfo(req,resp);
				}
				
			}
			
			else {
				userinfo(req,resp);
			}
		}
		
		}
		
	private void userinfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userType = 0;//普通用户
		User user = new User();
		user.setUserType(userType);
		List<User> users = userDao.getUserByType(user);
		req.setAttribute("users", users);
		req.setAttribute("user", userLogin);
		req.getRequestDispatcher("/admin/file/BgUser.jsp").forward(req, resp);
	}

	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		String userName = req.getParameter("textName").replace(" ", "");
		String pwd = req.getParameter("textPassword").replace(" ", "");
		if("".equals(userName)||"".equals(pwd)){
			System.out.println("新增失败");
			out.print("<script>alert('新增失败,不能为空');window.location.href='bgmUserServlet?command=addUser';</script>");
		}
		else{
			String userType = req.getParameter("select2");
			
			User user = new User();
			List<User> users = userDao.getAllUser();
			boolean bool = true;
			for (User user1 : users) {
				if(userName.equals(user1.getUserName())){
					bool = false;
				}
			}
			if(bool){
				user.setUserName(userName);
				user.setUserPwd(pwd);
				user.setUserType(Integer.parseInt(userType));
				System.out.println(userName+pwd+Integer.parseInt(userType));
				userDao.insertUser(user);
				System.out.println("新增成功");
				out.print("<script>alert('新增成功');window.location.href='bgmUserServlet?command=addUser';</script>");
			}
			else{
				System.out.println("新增失败");
				out.print("<script>alert('新增失败,用户名重复');window.location.href='bgmUserServlet?command=addUser';</script>");
			}
		}
		
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String userNo = req.getParameter("bid");
		User user = new User();
		user.setUserNo(Integer.parseInt(userNo));
		userDao.delete(user);
	}

	private void userAllInfo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		List<User> users = userDao.getAllUser();
		req.setAttribute("users", users);
		req.setAttribute("user", userLogin);
		try {
			req.getRequestDispatcher("/admin/file/BgUser.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

}
