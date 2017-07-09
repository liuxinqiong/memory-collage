package cn.com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.daoImp.AnswerDaoImpl;
import cn.com.daoImp.FileDaoImpl;
import cn.com.daoImp.ProfeDaoImpl;
import cn.com.daoImp.StudentDaoImpl;
import cn.com.daoImp.TeacherDaoImpl;
import cn.com.daoImp.UserDaoImpl;
import cn.com.daoInf.AnswerDao;
import cn.com.daoInf.FileDao;
import cn.com.daoInf.ProfeDao;
import cn.com.daoInf.StudentDao;
import cn.com.daoInf.TeacherDao;
import cn.com.daoInf.UserDao;
import cn.com.entity.FileShare;
import cn.com.entity.Profession;
import cn.com.entity.Student;
import cn.com.entity.Teacher;
import cn.com.entity.User;
import cn.com.util.GetRequestWrap;



public class UserServlet extends HttpServlet{
	
	private StudentDao studao ;
	private TeacherDao teadao ;
	private UserDao userdao ;
	private ProfeDao profedao;
	private AnswerDao answerdao;
	private FileDao filedao;

	
	public UserServlet(){
		studao = new StudentDaoImpl();
		teadao = new TeacherDaoImpl();
		userdao = new UserDaoImpl();
		profedao = new ProfeDaoImpl();
		answerdao = new AnswerDaoImpl();
		filedao = new FileDaoImpl();
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub`
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String command = req.getParameter("command");
			
		if(("login").equals(command)){
			this.login(req, resp);
		}
		else if(("exit").equals(command)){
			this.exit(req,resp);
		}
		else if(("teacherGroup").equals(command)){
			this.teacherGroup(req,resp);
		}
		else if(("lookTeacherInfo").equals(command)){
			this.lookTeacherInfo(req,resp);
		}
		else if(("studentInfo").equals(command)){
			this.studentInfo(req,resp);
		}
		else if(("teacherInfo").equals(command)){
			this.teacherInfo(req,resp);
		}
		else if(("teacherFile").equals(command)){
			this.teacherFile(req,resp);
		}
		else if(("studentchangeinfo").equals(command)){
			this.studentchangeinfo(req,resp);
		}
		else if(("teacherchangeinfo").equals(command)){
			this.teacherchangeinfo(req,resp);
		}
		
	}

	private void teacherchangeinfo(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Teacher tea = (Teacher)req.getSession().getAttribute("loginUser");
		String pwd = req.getParameter("pwdNew") != null ? req
				.getParameter("pwdNew") : null;
		String describe = req.getParameter("txtDescribe") != null ? req
				.getParameter("txtDescribe") : null;
		String img = req.getParameter("img") != null ? req
				.getParameter("img") : null;			
		if(pwd!=null){
			tea.setTeaPwd(pwd);
		}
		if(describe!=null){
			tea.setTeaDescription(describe);
		}
		if(img!=null){
			tea.setTeaPic(img);
		}
		boolean bool = this.teadao.changeTeacherInfo(tea);
		if(bool){
			
			tea = this.teadao.getTeacherInfo(tea);
			req.getSession().setAttribute("loginUser", tea);
			req.setAttribute("result", "success");
			if(pwd!=null){
				req.setAttribute("nextpage", "changeteapwd");
				
			}else{
				
				req.setAttribute("nextpage", "changeteainfo");
			}
			req.getRequestDispatcher("jump.jsp").forward(req, resp);
			
		}else{
			req.setAttribute("result", "defeat");
			if(pwd!=null){
				req.setAttribute("nextpage", "changeteapwd");
			}else{
				req.setAttribute("nextpage", "changeteainfo");
			}
			req.getRequestDispatcher("jump.jsp").forward(req, resp);
		}
						
	}

	private void studentchangeinfo(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Student stu = (Student)req.getSession().getAttribute("loginUser");
		String pwd = req.getParameter("pwdNew") != null ? req
				.getParameter("pwdNew") : null;
		String mail = req.getParameter("txtMail") != null ? req
				.getParameter("txtMail") : null;
		String img = req.getParameter("img") != null ? req
				.getParameter("img") : null;		
		if(pwd!=null){
			stu.setStuPwd(pwd);
		}
		if(mail!=null){
			stu.setStuEmail(mail);
		}
		if(img!=null){
			stu.setStuEmail(img);
		}
		boolean bool = this.studao.changeStudentInfo(stu);		
		if(bool){
			
			stu = this.studao.getUserInfo(stu);
			req.getSession().setAttribute("loginUser", stu);
			req.setAttribute("result", "success");
			if(pwd!=null){
				req.setAttribute("nextpage", "changestupwd");
				
			}else{
				
				req.setAttribute("nextpage", "changestuinfo");
			}
			req.getRequestDispatcher("jump.jsp").forward(req, resp);
			
		}else{
			req.setAttribute("result", "defeat");
			if(pwd!=null){
				req.setAttribute("nextpage", "changestupwd");
			}else{
				
				req.setAttribute("nextpage", "changestuinfo");
				
			}
			req.getRequestDispatcher("jump.jsp").forward(req, resp);
		}
		
	}

	private void teacherFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		req = new GetRequestWrap(req);
		String teaName = req.getParameter("teaName");
		Teacher teacher = new Teacher();
		teacher.setTeaName(teaName);
		List<FileShare> files = this.filedao.getAllFileShareByTeaNo(teacher);
		req.setAttribute("files", files);
		req.getRequestDispatcher("teacher_file.jsp").forward(req, resp);
		
	}

	private void teacherInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Profession> professions = this.profedao.getAllProfession();
		req.setAttribute("professions", professions);
		req.getRequestDispatcher("teacher_personinfo.jsp").forward(req, resp);
	}

	private void studentInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Profession> professions = this.profedao.getAllProfession();
		req.setAttribute("professions", professions);
		
		
		req.getRequestDispatcher("student_personinfo.jsp").forward(req, resp);
	}

	private void lookTeacherInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		if(session.getAttribute("loginUser")==null){
			resp.sendRedirect("login.jsp");
		}else{
			req = new GetRequestWrap(req);
			String teaName = req.getParameter("teaName");
			Teacher teacher = new Teacher();
			teacher.setTeaName(teaName);
			teacher = this.teadao.getTeacherInfo(teacher);		
			List<Profession> professions = this.profedao.getAllProfession();
			int answerNum = this.answerdao.getAnswerNumByTeaNo(teacher);
			List<FileShare> files = this.filedao.getAllFileShareByTeaNo(teacher);
			req.setAttribute("files", files);
			req.setAttribute("answerNum", answerNum);
			req.setAttribute("teacher", teacher);
			req.setAttribute("professions", professions);
			
			
			req.getRequestDispatcher("look_teacherinfo.jsp").forward(req, resp);
		}
		
	}

	private void teacherGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Teacher> teachers = this.teadao.getAllTeacher();
		List<Profession> professions = this.profedao.getAllProfession();
		req.setAttribute("teachers", teachers);
		req.setAttribute("professions", professions);
		req.getRequestDispatcher("teacher_group.jsp").forward(req, resp);
		
	}

	private void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		req.setAttribute("loginUser", null);
		session.setAttribute("loginUser", null);
		
		req.setAttribute("idType", null);
		session.setAttribute("idType", null);
		
		resp.sendRedirect("questionServlet");
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			String selType = req.getParameter("selType") != null ? req
				.getParameter("selType") : null;
			if(selType.equals("学生登录")){
				
				this.studentLogin(req,resp);
			}else if(selType.equals("教师登录")){
				
				this.teacherLogin(req,resp);
			}else if(selType.equals("管理员登录")){
				
				this.userLogin(req,resp);
			}
			
	}

	private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User user = this.getUser(req, resp);
		User dbuser = this.userdao.getUserInfo(user);
		
		if(user.getUserPwd().equals(dbuser.getUserPwd())){
			user = dbuser;
		}else{
			user = null;
		}
	
		if (user!=null) {
			
		
			req.setAttribute("loginUser", user);
			session.setAttribute("loginUser", user);
			
			req.setAttribute("idType", "User");
			session.setAttribute("idType", "User");
			// 转发
			resp.sendRedirect("admin/index.jsp");
//			req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);

		} else {
			// 重定向：客户端需要请求两次
			resp.sendRedirect("errorJump.jsp");

		}		
	}

	private void teacherLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		Teacher teacher = this.getTeacher(req,resp);	
		Teacher dbtea = this.teadao.getTeacherInfo(teacher);
		if(teacher.getTeaPwd().equals(dbtea.getTeaPwd())){
			teacher = dbtea;
		}else{
			teacher = null;	}	
		if (teacher!=null) {
		
			req.setAttribute("loginUser", teacher);
			session.setAttribute("loginUser", teacher);
			req.setAttribute("idType", "Teacher");
			session.setAttribute("idType", "Teacher");
			
			// 转发
			req.getRequestDispatcher("questionServlet").forward(req, resp);

		} else {
			// 重定向：客户端需要请求两次
			resp.sendRedirect("errorJump.jsp");

		}	
	}

	private void studentLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		Student student = this.getStudent(req,resp);	
		Student dbstu =  this.studao.getUserInfo(student);	
		if(student.getStuPwd().equals(dbstu.getStuPwd())){
			student = dbstu;
		}else{
			student = null;
		}
		
		
	
		if (student!=null) {
			
			req.setAttribute("loginUser", student);
			session.setAttribute("loginUser", student);
			
			req.setAttribute("idType", "Student");
			session.setAttribute("idType", "Student");
			// 转发
			req.getRequestDispatcher("questionServlet").forward(req, resp);

		} else {
			// 重定向：客户端需要请求两次
			resp.sendRedirect("errorJump.jsp");

		}	
		
	}

	private User getUser(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String name = req.getParameter("username") != null ? req
				.getParameter("username") : null;
		String pwd = req.getParameter("password") != null ? req
				.getParameter("password") : null;
			
		User user = new User();
		user.setUserName(name);
		user.setUserPwd(pwd);
		return user;
	}

	private Teacher getTeacher(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String name = req.getParameter("username") != null ? req
				.getParameter("username") : null;
		String pwd = req.getParameter("password") != null ? req
				.getParameter("password") : null;
		Teacher teacher = new Teacher();
		teacher.setTeaName(name);
		teacher.setTeaPwd(pwd);
		return teacher;
	}

	private Student getStudent(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String name = req.getParameter("username") != null ? req
				.getParameter("username") : null;
		String pwd = req.getParameter("password") != null ? req
				.getParameter("password") : null;
		Student student = new Student();
		student.setStuName(name);
		student.setStuPwd(pwd);
		return student;
	}
}
