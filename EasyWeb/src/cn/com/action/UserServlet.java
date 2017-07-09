package cn.com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.entity.User;
import cn.com.serviceImp.UserServiceImp;
import cn.com.serviceInf.UserServiceInf;

public class UserServlet extends HttpServlet {
	private UserServiceInf service;
	private ServletConfig config;

	public UserServlet() {
		service = new UserServiceImp();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("啦啦啦啦拉拉啊");
		this.config = config;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String command = req.getParameter("command") != null ? req
				.getParameter("command") : null;;

		if (command.equals("login")) {
			login(req, resp);
		} else if (command.equals("register")) {
			register(req, resp);
		} else if (command.equals("toRegister")) {
			toRegister(req, resp);
		}else if(command.equals("checkName")){
			checkName(req,resp);
		}
	}

	private void checkName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = this.wrap(req);
		PrintWriter out = resp.getWriter();
		if (user.getUname().equals("sxt")) {
			// 如是用户选择的是ajax请求方式，服务不能直接进行页面跳转.直接把数据写到（网络）流
			out.print(1);
		} else {
			out.print(0);
		}
		out.flush();
		out.close();
	}

	private void toRegister(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

	private void register(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = this.wrap(req);
		user=this.service.save(user);
		resp.setCharacterEncoding("gbk");
		PrintWriter out = resp.getWriter();
		if (user == null) {
			out.println("<div>用户名已经存在，请点击返回按钮，更换用户名重新注册</div>");
		} else {
			req.getRequestDispatcher("reg-result.jsp").forward(req, resp);
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = this.wrap(req);
		HttpSession session = req.getSession();
		ServletContext application = this.config.getServletContext();
		user = this.service.loginValidate(user);
		if (user!=null) {
			session.setAttribute("loginUser", user);
			List<String> allUsers = null;
			if (application.getAttribute("allLoginUser") == null) {
				allUsers = new ArrayList<String>();
				application.setAttribute("allLoginUser", allUsers);
			} else {
				allUsers = (List<String>) application
						.getAttribute("allLoginUser");
			}
			allUsers.add(user.getUname());
			req.getRequestDispatcher("productServlet").forward(req, resp);
		} else {
			resp.sendRedirect("login.jsp");
		}
	}

	private User wrap(HttpServletRequest req) throws ServletException,
			IOException {
		int uid=req.getParameter("uid")!=null?Integer.parseInt(req.getParameter("uid")):0;
		String name = req.getParameter("userName")!=null?req.getParameter("userName"):null;
		String password = req.getParameter("passWord")!=null?req.getParameter("passWord"):null;
		return new User(uid,name, password);
	}
}
