package cn.com.action;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daoImp.AnswerDaoImpl;
import cn.com.daoImp.AskDaoImpl;
import cn.com.daoImp.StudentDaoImpl;
import cn.com.daoImp.TeacherDaoImpl;
import cn.com.daoInf.AskDao;
import cn.com.entity.AnswerInfo;
import cn.com.entity.AskInfo;

import cn.com.entity.Student;
import cn.com.entity.Teacher;

public class BgmQuesServlet extends HttpServlet {
	private static final long serialVersionUID = 5952689219411916553L;

	AskDao askDao;
	AskDaoImpl askDaoImpl;
	StudentDaoImpl studentDaoImpl;
	TeacherDaoImpl teacherDaoImpl;
	AnswerDaoImpl answerDaoImpl;
	public BgmQuesServlet() {
		super();
		askDao = new AskDaoImpl();
		studentDaoImpl = new StudentDaoImpl();
		teacherDaoImpl = new TeacherDaoImpl();
		answerDaoImpl = new AnswerDaoImpl();
	}
	
	public void init() throws ServletException {
		System.out.println("----��ʼ����ʼ----");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String command = req.getParameter("command") != null ? req
				.getParameter("command") : null;
		System.out.println("cmd-one:" + command);
		if ("askinfo".equals(command)) {
			askinfo(req, resp);
		} else if ("askall".equals(command)) {
			askview(req, resp); // ��ת��ϸҳ��
		} else if ("deleteAsk".equals(command)) {
			deleteAsk(req, resp);
		} else if ("deleteAll".equals(command)) {
			deleteAll(req, resp);
		} else if ("select".equals(command)) {
			select(req, resp);
		}else if("deleteAnswer".equals(command)){
			deleteAnswer(req,resp);
		} 
		
		else
			req.getRequestDispatcher("/admin/file/error.html").forward(req,
					resp);
	}

	private void deleteAnswer(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		AnswerInfo answerInfo = new AnswerInfo();
		answerInfo.setAnswerNo(Integer.parseInt(req.getParameter("ansId")));
		answerDaoImpl.delete(answerInfo);
		askview(req, resp);
	}

	// ��ѯ
	private void select(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String textSelect = req.getParameter("textSelect");
		if (textSelect.equals("")) {
			askinfo(req, resp);
		} 
		else {
			askDaoImpl = new AskDaoImpl();
			List<AskInfo> askInfos = askDaoImpl.getAskInfoByAskTop(textSelect
					.toUpperCase());
			List<Student> students = studentDaoImpl.getAllStudent();
			req.setAttribute("askinfos", askInfos);
			req.setAttribute("students", students);
			try {
				req.getRequestDispatcher("/admin/file/BgAskinfo.jsp").forward(
						req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ����ɾ��
	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String[] checkbox = req.getParameterValues("delid") != null ? req
				.getParameterValues("delid") : null;
		System.out.println(req.getParameter("textfield"));
		if (null == checkbox) {
			System.out.println("ѡ��ֵΪ��");
		} else {
			for (int i = 0; i < checkbox.length; i++) {
				delete(req, resp, checkbox[i]);
			}
		}

		askinfo(req, resp);
	}

	// ����ɾ��
	private void deleteAsk(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String bid = req.getParameter("bid");
		System.out.println("bid:" + bid);
		delete(req, resp, bid);
		askinfo(req, resp);
	}

	// ɾ��������Ϣ
	private void delete(HttpServletRequest req, HttpServletResponse resp,
			String bid) {
		// TODO Auto-generated method stub
		askDaoImpl = new AskDaoImpl();
		askDaoImpl.delete(Integer.parseInt(bid));

	}

	// ��ת��ϸҳ��
	private void askview(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String bid = req.getParameter("bid");
		System.out.println("bid:" + bid);
		askDaoImpl = new AskDaoImpl();
		answerDaoImpl = new AnswerDaoImpl();
		
		AskInfo askInfo = askDaoImpl.getAskInfoByAskId(Integer.parseInt(bid));
		Student student = studentDaoImpl.getStudentById(askInfo.getStudentNo());
		List<AnswerInfo> answerInfos = answerDaoImpl.getAnswerInfoByAskNo(askInfo.getAskNo());
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Teacher> teachersX = new ArrayList<Teacher>();    //����װ�ظ��Ļظ���ʦ����
		
		int i=1;
		for (AnswerInfo answerInfo : answerInfos) {
			Teacher teacher = teacherDaoImpl.getTeacherById(answerInfo.getTeacherNo());
			if(i==1) {teachers.add(teacher);i++;}
			else{
				for (Teacher teacher2 : teachers) {
					if (teacher.getTeaNo() == teacher2.getTeaNo()) {
						teachersX.add(teacher);
					} else
						System.out.println("δ�ظ�");
				}
				teachers.add(teacher);
			}
			teachers.removeAll(teachersX);
		}
		
		for (Teacher teacher2 : teachers) {
			System.out.println(teacher2.getTeaName()+":");
		}
		req.setAttribute("student", student);
		req.setAttribute("askinfo", askInfo);
		req.setAttribute("teachers", teachers);
		req.setAttribute("answerInfos", answerInfos);
		try {
			req.getRequestDispatcher("/admin/file/BgAskMingxi.jsp").forward(
					req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ��תbgaskҳ��
	private void askinfo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		List<AskInfo> askInfos = askDao.getAllAskInfo();
		List<Student> students = studentDaoImpl.getAllStudent();
		req.setAttribute("askinfos", askInfos);
		req.setAttribute("students", students);
		try {
			req.getRequestDispatcher("/admin/file/BgAskinfo.jsp").forward(req,
					resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void destroy() {
		super.destroy();
		System.out.println("----��������----");
	}

}
