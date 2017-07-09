package cn.com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daoImp.ProfeDaoImpl;
import cn.com.daoImp.StudentDaoImpl;
import cn.com.daoInf.ProfeDao;
import cn.com.daoInf.StudentDao;
import cn.com.entity.Profession;
import cn.com.entity.Student;
import cn.com.util.GetRequestWrap;

public class StudentServlet extends HttpServlet {
	private StudentDao studao;
	private ProfeDao profedao;

	public StudentServlet() {
		studao = new StudentDaoImpl();
		profedao = new ProfeDaoImpl();
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

		if (("deleteStu").equals(command)) {
			this.deleteStu(req, resp);
		} else if (("changeStu").equals(command)) {
			this.changeStu(req, resp);
		} else if (("addStu").equals(command)) {
			this.addStu(req, resp);
		} else if (("allStu").equals(command)) {
			this.allStu(req, resp);
		} else if (("inquiryStu").equals(command)) {
			this.inquiryStu(req, resp);
		}  else if (("save").equals(command)) {
			this.save(req, resp);
		} 

	}

	private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		Student student = this.getStudent(req, resp);
		if(student.getStuName()==null){
			boolean bool = this.studao.addStudent(student);
			if(bool){
				out.print("<script>alert('增加成功！');window.location.href='admin/index.jsp'</script>");
			}else{
				out.print("<script>alert('增加失败！');window.location.href='admin/index.jsp'</script>");
			}
		}else{
			boolean bool = this.studao.changeStu(student);
			if(bool){
				out.print("<script>alert('修改成功！');window.location.href='admin/file/add_student.jsp'</script>");
			}else{
				out.print("<script>alert('修改失败！');window.location.href='admin/file/add_student.jsp'</script>");
			}
		}
	}

	private void inquiryStu(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String stuName = req.getParameter("txtInquiry") != null ? req
				.getParameter("txtInquiry") : null;
		List<Student> students = this.studao.inquiryStuByName(stuName);	
		List<Profession> professions = this.profedao.getAllProfession();
		req.setAttribute("professions", professions);
		req.setAttribute("students", students);
		req.getRequestDispatcher("admin/file/BgStudent.jsp").forward(req, resp);

	}

	private void allStu(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		List<Student> students = this.studao.getAllStudent();
		List<Profession> professions = this.profedao.getAllProfession();
		req.setAttribute("professions", professions);
		req.setAttribute("students", students);
		req.getRequestDispatcher("admin/file/BgStudent.jsp").forward(req, resp);
	}

	private void addStu(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		List<Profession> professions = this.profedao.getAllProfession();
		req.setAttribute("professions", professions);
		req.getRequestDispatcher("admin/file/add_student.jsp").forward(req, resp);
		

	}

	private void changeStu(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	
		String stuName = req.getParameter("stuName") != null ? req
				.getParameter("stuName") : null;
				System.out.println(stuName);
		Student student = new Student();
		student.setStuName(stuName);		
		student	= this.studao.getUserInfo(student);	
		req.setAttribute("student", student);
		List<Profession> professions = this.profedao.getAllProfession();
		req.setAttribute("professions", professions);
				
		req.getRequestDispatcher("admin/file/add_student.jsp").forward(req, resp);
	}

	private void deleteStu(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// TODO Auto-generated method stub
		Student student = this.getStudent(req, resp);
		PrintWriter out = resp.getWriter();
		boolean bool = this.studao.deleteStu(student);
		if (bool) {
			out.print("<script>alert('删除成功！');window.location.href='admin/file/BgStudent.jsp'</script>");

		} else {
			out.print("<script>alert('删除失败！');window.location.href='admin/file/BgStudent.jsp'</script>");
		}
	}

	private Student getStudent(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String name = req.getParameter("stuName") != null ? req
				.getParameter("stuName") : null;
		String pwd = req.getParameter("password") != null ? req
				.getParameter("password") : null;

		String professNo = req.getParameter("professNo") != null ? req
				.getParameter("professNo") : null;

		String stuEmail = req.getParameter("stuEmail") != null ? req
				.getParameter("stuEmail") : null;
		String stuPic = req.getParameter("stuPic") != null ? req
				.getParameter("stuPic") : null;
		String stuNo = req.getParameter("stuNo") != null ? req
				.getParameter("stuNo") : null;
		Student student = new Student();
		student.setStuName(name);
		student.setStuPwd(pwd);
		student.setStuEmail(stuEmail);
		student.setStuPic(stuPic);
		if(professNo!=null){
			student.setProfessNo(Integer.parseInt(professNo));
		}
		if(stuNo!=null){
			student.setStuNo(Integer.parseInt(stuNo));
		}
		return student;
	}
}
