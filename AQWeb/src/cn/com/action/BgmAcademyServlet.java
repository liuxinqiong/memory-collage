package cn.com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daoImp.AcadeDaoImpl;
import cn.com.daoInf.AcadeDao;
import cn.com.entity.Academy;

@SuppressWarnings("serial")
public class BgmAcademyServlet extends HttpServlet {

	AcadeDao acadeDao;

	public BgmAcademyServlet() {
		acadeDao = new AcadeDaoImpl();
	}

	public void init() throws ServletException {
		// Put your code here
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String command = req.getParameter("command") != null ? req
				.getParameter("command") : null;
		if ("academyAllInfo".equals(command)) {
			getAllAcade(req, resp);
		} else if ("deleteAll".equals(command)) {
				deleteAll(req,resp);
		}else if("insert".equals(command)){
			insert(req,resp);
		}else if("updateAcade".equals(command)){
			updateAcade(req,resp);
		}else if("addAcademy".equals(command)){
			addAcademyJsp(req,resp);
		}
		
		else 
			req.getRequestDispatcher("/admin/file/error.html").forward(req,
					resp);
	}

	private void addAcademyJsp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		String acadeName = req.getParameter("academyName");
		
		List<Academy> academies = acadeDao.getAllAcademy();
		boolean bool=true;
		for (Academy academy : academies) {
			if(acadeName.equals(academy.getAcademyName())){
				bool=false;
			}
		}
		
		if(bool){
			out.print(bool);
			insertAcade(acadeName);
		}else{
			out.print(bool);
		}	
	}

	private void updateAcade(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		Academy acade = new Academy();
		String academyNo = req.getParameter("academyNo");
		acade.setAcademyNo(Integer.parseInt(academyNo));
		acadeDao.update(acade);
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String[] acadeName = req.getParameterValues("txtName");
		for (int i = 0; i < acadeName.length; i++) {
			insertAcade(acadeName[i]);
		}
		
	}

	private void insertAcade(String academyName) {
		// TODO Auto-generated method stub
		Academy acade = new Academy();
		acade.setAcademyName(academyName);
		acadeDao.insert(acade);
	}

	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String[] checkbox = req.getParameterValues("delid") != null ? req
				.getParameterValues("delid") : null;
		if (null == checkbox) {
			System.out.println("123");
		} else {
			for (int i = 0; i < checkbox.length; i++) {
				delete(req, resp, checkbox[i]);
			}
		}

		getAllAcade(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp,
			String acadeName) {
		// TODO Auto-generated method stub
		Academy academy = new Academy();
		academy.setAcademyNo(Integer.parseInt(acadeName));
		acadeDao.delete(academy);
	}

	private void getAllAcade(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		List<Academy> academies = acadeDao.getAllAcademy();
		req.setAttribute("academies", academies);
		for (Academy academy : academies) {
			System.out.println(academy.getAcademyName());
		}
		try {
			req.getRequestDispatcher("/admin/file/BgAcademy.jsp").forward(req,
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
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

}
