package cn.com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daoImp.AcadeDaoImpl;
import cn.com.daoImp.ProfeDaoImpl;
import cn.com.daoImp.TeacherDaoImpl;
import cn.com.daoInf.AcadeDao;
import cn.com.daoInf.ProfeDao;
import cn.com.daoInf.TeacherDao;
import cn.com.entity.Academy;
import cn.com.entity.Profession;
import cn.com.entity.Teacher;

public class BgmTeacherServlet extends HttpServlet{
	TeacherDao teacherDao;
	ProfeDao profeDao;
	AcadeDao acadeDao;
	
	public BgmTeacherServlet(){
		teacherDao=new TeacherDaoImpl();
		profeDao=new ProfeDaoImpl();
		acadeDao=new AcadeDaoImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command=req.getParameter("command");
		if("teacherMag".equals(command)){
			teacherMag(req,resp);
		}else if("addTeacher".equals(command)){
			addTeacher(req,resp);
		}else if("add".equals(command)){
			add(req,resp);
		}
		else{
			
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		String teaName=req.getParameter("teaName");
		String teaPwd=req.getParameter("teaPwd");
		String teaGrade=req.getParameter("teaGrade");
		String select2=req.getParameter("select2");
		String deacrip=req.getParameter("deacrip");
		Teacher teacher=new Teacher();
		teacher.setTeaName(teaName);
		teacher.setTeaPwd(teaPwd);
		teacher.setTeaGrade(teaGrade);
		teacher.setProfeNo(Integer.parseInt(select2));
		teacher.setTeaDescription(deacrip);
		if(teacherDao.addTeacher(teacher)>0){
			out.print("<script>alert('新增成功');window.location.href='admin/index.jsp';</script>");
		}
	}

	private void addTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Profession> professions=profeDao.getAllProfession();
		req.setAttribute("professionInfos", professions);
		List<Academy> academyInfos=acadeDao.getAcademy(new Academy());
		req.setAttribute("academyInfos", academyInfos);
		req.getRequestDispatcher("/admin/file/addTeacher.jsp").forward(req, resp);
	}

	private void teacherMag(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Teacher> teacherInfos=teacherDao.getAllTeacher();
		req.setAttribute("teacherInfos", teacherInfos);
		List<Profession> professions=profeDao.getAllProfession();
		req.setAttribute("professionInfos", professions);
		req.getRequestDispatcher("/admin/file/BgTeacher.jsp").forward(req, resp);
	}
}
