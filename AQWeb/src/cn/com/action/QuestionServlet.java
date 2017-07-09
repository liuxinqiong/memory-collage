package cn.com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daoImp.AcadeDaoImpl;
import cn.com.daoImp.AnswerDaoImpl;
import cn.com.daoImp.AskDaoImpl;
import cn.com.daoImp.ProfeDaoImpl;
import cn.com.daoImp.StudentDaoImpl;
import cn.com.daoImp.TeacherDaoImpl;
import cn.com.daoInf.AcadeDao;
import cn.com.daoInf.AnswerDao;
import cn.com.daoInf.AskDao;
import cn.com.daoInf.ProfeDao;
import cn.com.daoInf.StudentDao;
import cn.com.daoInf.TeacherDao;
import cn.com.entity.Academy;
import cn.com.entity.AnswerInfo;
import cn.com.entity.AskInfo;
import cn.com.entity.AskInfoViewBean;
import cn.com.entity.Profession;
import cn.com.entity.Student;
import cn.com.entity.Teacher;
import cn.com.util.ObjectToJson;

public class QuestionServlet extends HttpServlet {
	private AskDao askDao;
	private AnswerDao answerDao;
	private StudentDao studentDao;
	private AcadeDao acadeDao;
	private ProfeDao profeDao;
	private TeacherDao teacherDao;

	public QuestionServlet() {
		askDao = new AskDaoImpl();
		answerDao = new AnswerDaoImpl();
		studentDao = new StudentDaoImpl();
		acadeDao = new AcadeDaoImpl();
		profeDao = new ProfeDaoImpl();
		teacherDao = new TeacherDaoImpl();

	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String command = req.getParameter("command") != null ? req
				.getParameter("command") : null;
		if (("search").equals(command)) {
			search(req, resp);
		} else if (("finished").equals(command)) {
			finished(req, resp);
		} else if (("unfinished").equals(command)) {
			unfinished(req, resp);
		} else if (("questionView").equals(command)) {
			questionView(req, resp);
		} else if (("ask").equals(command)) {
			ask(req, resp);
		} else if (("getProfession").equals(command)) {
			getProfession(req, resp);
		} else if ("addAskInfo".equals(command)) {
			addAskInfo(req, resp);
		} else if("addAnswer".equals(command)){
			addAnswer(req,resp);
		}else if("myUnfinishedQuestion".equals(command)){
			myUnfinishedQuestion(req,resp);
		}else if("myFinishedQuestion".equals(command)){
			myFinishedQuestion(req,resp);
		}else if("myAllQuestion".equals(command)){
			myAllQuestion(req,resp);
		}else if("chooseAnswer".equals(command)){
			chooseAnswer(req,resp);
		}else if("acceptAnswer".equals(command)){
			acceptAnswer(req,resp);
		}else if("questionList".equals(command)){
			questionList(req,resp);
		}else if("finishedQuestionList".equals(command)){
			finishedQuestionList(req,resp);
		}else if("unfinishedQuestionList".equals(command)){
			unfinishedQuestionList(req,resp);
		}
		else {
			list(req, resp);
		}
	}

	private void unfinishedQuestionList(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int academyNo=Integer.parseInt(req.getParameter("academyNo"));
		String professionNo=req.getParameter("professionNo");
		if(professionNo!=null){
			Profession profession=new Profession();
			profession.setProfessionNo(Integer.parseInt(professionNo));
			profession=profeDao.getProfessionById(profession);
			req.setAttribute("profession", profession);
			List<AskInfo> askInfos=askDao.getUnfinishedAskInfoByProfessId(profession.getProfessionNo());
			req.setAttribute("askInfos", askInfos);
		}
		Academy temp=new Academy();
		temp.setAcademyNo(academyNo);
		Academy academy=acadeDao.getAcademyById(temp);
		req.setAttribute("academy", academy);
		List<Profession> professions=profeDao.getProfessionByAcademyId(academyNo);
		req.setAttribute("professions", professions);
		req.getRequestDispatcher("question_list_unfinished.jsp").forward(req, resp);
	}

	private void finishedQuestionList(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int academyNo=Integer.parseInt(req.getParameter("academyNo"));
		String professionNo=req.getParameter("professionNo");
		if(professionNo!=null){
			Profession profession=new Profession();
			profession.setProfessionNo(Integer.parseInt(professionNo));
			profession=profeDao.getProfessionById(profession);
			req.setAttribute("profession", profession);
			List<AskInfo> askInfos=askDao.getFinishedAskInfoByProfessId(profession.getProfessionNo());
			req.setAttribute("askInfos", askInfos);
		}
		Academy temp=new Academy();
		temp.setAcademyNo(academyNo);
		Academy academy=acadeDao.getAcademyById(temp);
		req.setAttribute("academy", academy);
		List<Profession> professions=profeDao.getProfessionByAcademyId(academyNo);
		req.setAttribute("professions", professions);
		req.getRequestDispatcher("question_list_finished.jsp").forward(req, resp);
	}

	private void questionList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int academyNo=Integer.parseInt(req.getParameter("academyNo"));
		String professionNo=req.getParameter("professionNo");
		if(professionNo!=null){
			Profession profession=new Profession();
			profession.setProfessionNo(Integer.parseInt(professionNo));
			profession=profeDao.getProfessionById(profession);
			req.setAttribute("profession", profession);
			List<AskInfo> askInfos=askDao.getAskInfoByProfessId(profession.getProfessionNo());
			req.setAttribute("askInfos", askInfos);
		}
		Academy temp=new Academy();
		temp.setAcademyNo(academyNo);
		Academy academy=acadeDao.getAcademyById(temp);
		req.setAttribute("academy", academy);
		List<Profession> professions=profeDao.getProfessionByAcademyId(academyNo);
		req.setAttribute("professions", professions);
		
		
		req.getRequestDispatcher("question_list.jsp").forward(req, resp);
	}

	private void acceptAnswer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		int answerNo=Integer.parseInt(req.getParameter("answerNo"));
		AnswerInfo answerInfo=new AnswerInfo();
		
		answerInfo.setAnswerNo(answerNo);
		int askNo=Integer.parseInt(req.getParameter("askNo"));
		AskInfo askInfo=new AskInfo();
		askInfo.setAskNo(askNo);
		boolean bool=true;
		if(answerDao.acceptAnswerById(answerInfo)>0&&askDao.updateStatusById(askInfo)>0){
			out.print(bool);
		}else{
			bool=false;
			out.print(bool);
		}
	}

	private void chooseAnswer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int askNo=Integer.parseInt(req.getParameter("askNo"));
		AskInfo askInfo=new AskInfo();
		askInfo.setAskNo(askNo);
		List<AskInfoViewBean> askInfoViewBeans=askDao.getAskInfoViewBeans(askInfo);
		req.setAttribute("askInfoViewBean", askInfoViewBeans.get(0));
		List<AnswerInfo> answerInfos=answerDao.getAnswerInfoByAskId(askNo);
		req.setAttribute("answerInfos", answerInfos);
		req.getRequestDispatcher("readQuestionDetail.jsp").forward(req, resp);
	}

	private void myAllQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Student student=(Student) req.getSession().getAttribute("loginUser");
		List<AskInfo> allAskInfos=askDao.getAskInfoByStudentId(student);
		req.setAttribute("allAskInfos", allAskInfos);
		req.getRequestDispatcher("student_allQuestion.jsp").forward(req, resp);
	}

	private void myFinishedQuestion(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Student student=(Student) req.getSession().getAttribute("loginUser");
		List<AskInfo> finishedAskInfos=askDao.getFinishedAskInfoByStudentId(student);
		req.setAttribute("finishedAskInfos", finishedAskInfos);
		req.getRequestDispatcher("student_finishedQuestion.jsp").forward(req, resp);
	}

	private void myUnfinishedQuestion(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Student student=(Student) req.getSession().getAttribute("loginUser");
		List<AskInfo> unfinishedAskInfos=askDao.getUnfinishedAskInfoByStudentId(student);
		req.setAttribute("unfinishedAskInfos", unfinishedAskInfos);
		req.getRequestDispatcher("student_unfinishedQuestion.jsp").forward(req, resp);
	}

	private void addAnswer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int studentNo=Integer.parseInt(req.getParameter("studentNo"));
		int askNo=Integer.parseInt(req.getParameter("askNo"));
		Teacher teacher=(Teacher)req.getSession().getAttribute("loginUser");
		teacherDao.updatePoints(teacher);
		int teacherNo=teacher.getTeaNo();
		String answerContent=req.getParameter("answerContent");
		AnswerInfo answerInfo=new AnswerInfo();
		answerInfo.setStudentNo(studentNo);
		answerInfo.setAskNo(askNo);
		answerInfo.setTeacherNo(teacherNo);
		answerInfo.setAnswerContent(answerContent);
		int flag=answerDao.addAnswerInfo(answerInfo);
		if(flag>0){
			req.getRequestDispatcher("addAnswer_result.jsp").forward(req, resp);
		}
	}

	private void addAskInfo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String askTopic = req.getParameter("title");
		String askContent = req.getParameter("askContent");
		int professionNo = Integer.valueOf(req.getParameter("classlevel2"));
		Student student = (Student) req.getSession().getAttribute("loginUser");
		AskInfo askInfo = new AskInfo();
		askInfo.setAskContent(askContent);
		askInfo.setAskTopic(askTopic);
		askInfo.setProfessionNo(professionNo);
		askInfo.setStudentNo(student.getStuNo());
		askInfo.setTeacherNo(-1);
		if (askDao.addAskInfo(askInfo) > 0) {
			req.getRequestDispatcher("addAsk_result.jsp").forward(req, resp);
		}
	}

	private void getProfession(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// TODO Auto-generated method stub
		// resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		int academyId = Integer.parseInt(req.getParameter("academyNo"));
		List<Profession> professions = profeDao
				.getProfessionByAcademyId(academyId);
		String sb = null;
		ObjectToJson<Profession> objectToJson = new ObjectToJson<Profession>();
		try {
			sb = objectToJson.ListToJson(professions);
			System.out.println(sb.toString());
			out.print(sb.toString());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	private void ask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		initTop(req, resp);
		initType(req, resp);
		req.getRequestDispatcher("ask.jsp").forward(req, resp);
	}

	private void questionView(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		initTop(req, resp);
		int askId = Integer.parseInt(req.getParameter("askId"));
		AskInfo askInfo = askDao.getAskInfoByAskId(askId);
		askDao.updateAskReaderCount(askInfo);

		List<AskInfoViewBean> askInfoViewBean = askDao
				.getAskInfoViewBeans(askInfo);
		req.setAttribute("askInfoViewBean", askInfoViewBean.get(0));
		AnswerInfo checkedAnswerInfo = answerDao.getCheckedAnswerInfo(askId);
		List<AnswerInfo> uncheckedAnswerInfos = answerDao
				.getUncheckedAnswerInfo(askId);
		req.setAttribute("checkedAnswerInfo", checkedAnswerInfo);
		req.setAttribute("uncheckedAnswerInfos", uncheckedAnswerInfos);

		List<AskInfo> aboutAskInfos = askDao.getAskInfoByProfessId(askInfo
				.getProfessionNo());
		req.setAttribute("aboutAskInfos", aboutAskInfos);

		req.getRequestDispatcher("question_view.jsp").forward(req, resp);
	}

	private void unfinished(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		initTop(req, resp);
		String key = req.getParameter("key") != null ? req.getParameter("key")
				: null;
		req.setAttribute("key", key);
		List<AskInfo> searchUnfinished = askDao.getUnfinishedQuestion(key);
		req.setAttribute("searchAskInfos", searchUnfinished);
		req.setAttribute("size", searchUnfinished.size());
		req.getRequestDispatcher("search_result_0.jsp").forward(req, resp);
	}

	private void finished(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		initTop(req, resp);
		String key = req.getParameter("key") != null ? req.getParameter("key")
				: null;
		req.setAttribute("key", key);
		List<AskInfo> searchFinished = askDao.getFinishedQuestion(key);
		req.setAttribute("searchAskInfos", searchFinished);
		req.setAttribute("size", searchFinished.size());
		req.getRequestDispatcher("search_result_1.jsp").forward(req, resp);
	}

	private void search(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		initTop(req, resp);
		String word = null;
		String key = req.getParameter("key") != null ? req.getParameter("key")
				: null;
		if (key == null) {
			word = req.getParameter("word");
		} else {
			word = key;
		}

		req.setAttribute("key", word);
		List<AskInfo> searchAskInfos = askDao.searchAskInfoByInfo(word);
		// Student stu=new Student();
		// stu.setStuNo(stuNo)
		// studentDao.getUserInfo(stu)
		req.setAttribute("searchAskInfos", searchAskInfos);
		req.setAttribute("size", searchAskInfos.size());
		req.getRequestDispatcher("search_result.jsp").forward(req, resp);
	}

	private void list(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		teacherDao.setSatifaction();
		List<AskInfo> askInfos = askDao.getAllAskInfo();
		req.setAttribute("askInfos", askInfos);
		List<AskInfo> askInfosChecked = askDao.getAskInfoByFlag(1);
		req.setAttribute("askInfosChecked", askInfosChecked);
		req.setAttribute("askInfosCheckedSize", askInfosChecked.size());
		req.getSession().setAttribute("askInfosCheckedSize", askInfosChecked.size());
		List<AskInfo> askInfosUnChecked = askDao.getAskInfoByFlag(0);
		req.setAttribute("askInfosUnChecked", askInfosUnChecked);
		req.setAttribute("askInfosUnCheckedSize", askInfosUnChecked.size());
		req.getSession().setAttribute("askInfosUnCheckedSize", askInfosUnChecked.size());

		initType(req, resp);
		List<Teacher> teacherPoint = teacherDao.getTeacherTop10ByPoint();
		List<Teacher> teacherSati = teacherDao.getTeacherTop10BySati();
		req.setAttribute("teacherPoint", teacherPoint);
		req.setAttribute("teacherSati", teacherSati);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	private void initTop(HttpServletRequest req, HttpServletResponse resp) {
		// // TODO Auto-generated method stub
		List<AskInfo> askInfosChecked = askDao.getAskInfoByFlag(1);
		req.setAttribute("askInfosCheckedSize", askInfosChecked.size());
		List<AskInfo> askInfosUnChecked = askDao.getAskInfoByFlag(0);
		req.setAttribute("askInfosUnCheckedSize", askInfosUnChecked.size());
	}

	private void initType(HttpServletRequest req, HttpServletResponse resp) {

		List<Academy> academyInfos = acadeDao.getAllAcademy();
		List<Profession> professionInfos = profeDao.getAllProfession();
		req.setAttribute("academyInfos", academyInfos);
		req.setAttribute("professionInfos", professionInfos);
	}
}
