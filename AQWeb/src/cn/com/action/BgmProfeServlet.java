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
import cn.com.daoInf.AcadeDao;
import cn.com.daoInf.ProfeDao;
import cn.com.entity.Academy;
import cn.com.entity.Profession;


@SuppressWarnings("serial")
public class BgmProfeServlet extends HttpServlet {

	ProfeDao profeDao;
	AcadeDao acadeDao;
	public BgmProfeServlet() {
		profeDao = new ProfeDaoImpl();
		acadeDao = new AcadeDaoImpl();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String command = req.getParameter("command")!= null ? req.getParameter("command") : null;
		System.out.println("BgmProcmd:"+req.getParameter("command"));
		
		if("professionAllInfo".equals(command)){
			profeAllInfo(req,resp);
		}else if("profeByAcadeNo".equals(command)){
			profeByAcadeNo(req,resp);
		}
		else if("delete".equals(command)){
			deleteProfe(req,resp);
		}
		else if("addProfess".equals(command)){
			addProfessjsp(req,resp);
		}
		else if("addProfession".equals(command)){
			addProfession(req,resp);
		}
		else req.getRequestDispatcher("/admin/file/error.html").forward(req,
				resp);
	}
	
	private void addProfession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		String professName = req.getParameter("professName").replace(" ", "");
		String acadeNo = req.getParameter("academy");
		if("".endsWith(professName)){
			out.print("<script>alert('新增失败,不能为空');window.location.href='bgmProfeServlet?command=addProfess';</script>");
			
		}
		else{
			
			Academy academy = new Academy();
			Profession profession = new Profession();
			
			academy.setAcademyNo(Integer.parseInt(acadeNo));
			
			System.out.println("acadeNo:"+acadeNo);
			System.out.println("professName:"+professName);
			
			List<Profession> professions = profeDao.getProfeByAcadeId(academy);
			Boolean bool=true;
			for (Profession profession3 : professions) {
				if(professName.equals(profession3.getProfessionName())){
					bool=false;
				}
			}
			if(bool){
				profession.setProfessionName(professName);
				profession.setAcademyNo(Integer.parseInt(acadeNo));
				profeDao.insert(profession);
				System.out.println("成功");
				out.print("<script>alert('新增成功');window.location.href='bgmProfeServlet?command=addProfess';</script>");
				
			}
			else{
				System.out.println("新增失败");
				out.print("<script>alert('新增失败，本专业已存在');window.location.href='bgmProfeServlet?command=addProfess';</script>");
			}
		}
		
		out.close();
		
	}

	private void addProfessjsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Academy> academies = acadeDao.getAllAcademy();
		req.setAttribute("academies",academies);
		
		req.getRequestDispatcher("/admin/file/addProfess.jsp").forward(req, resp);
		
	}

	private void deleteProfe(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String profeNo = req.getParameter("profeDeletId");
		Profession profession = new Profession();
		profession.setProfessionNo(Integer.parseInt(profeNo));
		profeDao.delete(profession);
		profeByAcadeNo(req,resp);
	}

	private void profeByAcadeNo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String AcadeNo = req.getParameter("profeByAcadeNo");
		System.out.println("profeByAcadeNo:"+AcadeNo);
		Academy academy = new Academy();
		academy.setAcademyNo(Integer.parseInt(AcadeNo));
		academy = acadeDao.getAcademyById(academy);
		List<Profession> professions = profeDao.getProfeByAcadeId(academy);
		List<Academy> academies = acadeDao.getAllAcademy();

//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("professions", professions);
//		try {
//			resp.getWriter().print(jsonObj);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		req.setAttribute("professions", professions);
		req.setAttribute("academies",academies);
		req.setAttribute("academie", academy);
		try {
 			req.getRequestDispatcher("/admin/file/BgProfession.jsp").forward(req,resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void profeAllInfo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		List<Profession> professions = profeDao.getAllProfession();
		List<Academy> academies = acadeDao.getAllAcademy();
		for(Profession profession : professions){
			System.out.println("profeName:"+profession.getProfessionName());
		}
		req.setAttribute("academies", academies);
		try {
			req.getRequestDispatcher("/admin/file/BgProfession.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
}
