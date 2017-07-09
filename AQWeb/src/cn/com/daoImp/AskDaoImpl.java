package cn.com.daoImp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.AskDao;
import cn.com.db.SqlCommand;
import cn.com.db.SqlStatement;
import cn.com.entity.AskInfo;
import cn.com.entity.AskInfoViewBean;
import cn.com.entity.Profession;
import cn.com.entity.Student;
import cn.com.entity.Teacher;

public class AskDaoImpl implements AskDao {

	public List<AskInfo> getAllAskInfo() {
		// TODO Auto-generated method stub
		Result result;
		List<AskInfo> askInfos = new ArrayList<AskInfo>();
		String sql = SqlStatement.Ask.ASK_INFO;// 要执行的语句
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);// 返回的结果

		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			AskInfo askInfo = new AskInfo();
			wrap(askInfo, row);
			askInfos.add(askInfo);
		}
		return askInfos;
	}

	public List<AskInfo> getAskInfoByFlag(int flag) {
		// TODO Auto-generated method stub
		Result result;
		List<AskInfo> askInfos = new ArrayList<AskInfo>();
		String sql = SqlStatement.Ask.ASK_INFO + " and status =" + flag;// 要执行的语句
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);// 返回的结果

		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			AskInfo askInfo = new AskInfo();
			wrap(askInfo, row);
			askInfos.add(askInfo);
		}
		return askInfos;
	}

	public List<AskInfo> searchAskInfoByInfo(String info) {
		// TODO Auto-generated method stub
		Result result;
		List<AskInfo> askInfos = new ArrayList<AskInfo>();
		String info2 = info.toUpperCase();
		String sql = SqlStatement.Ask.ASK_INFO
				+ " and upper(askContent) like '%" + info2 + "%'";// 要执行的语句
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);// 返回的结果

		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			AskInfo askInfo = new AskInfo();
			wrap(askInfo, row);
			askInfos.add(askInfo);
		}
		return askInfos;
	}

	public void wrap(AskInfo askInfo, SortedMap<String, Object> row) {
		askInfo.setAskNo(Integer.parseInt(String.valueOf(row.get("askNo"))));
		askInfo.setStudentNo(Integer.parseInt(String.valueOf(row
				.get("studentNo"))));
		askInfo.setTeacherNo(Integer.parseInt(String.valueOf(row
				.get("teacherNo"))));
		askInfo.setProfessionNo(Integer.parseInt(String.valueOf(row
				.get("professionNo"))));
		askInfo.setAskTopic(row.get("askTopic").toString());
		askInfo.setAskContent(row.get("askContent").toString());
		askInfo.setAskDate((Date) row.get("askDate"));
		askInfo.setReaderCount(Integer.parseInt(String.valueOf(row
				.get("readerCount"))));
		askInfo.setStatus(Integer.parseInt(String.valueOf(row.get("status"))));
		askInfo.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
	}

	public AskInfo getAskInfoByAskId(int askId) {
		// TODO Auto-generated method stub
		Result result;
		AskInfo askInfo = new AskInfo();
		;
		String sql = SqlStatement.Ask.ASK_INFO + " and askNo = ?";// 要执行的语句
		Object[] args = new Object[] { askId };
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);// 返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		if (rows.length != 0) {
			SortedMap<String, Object> row = rows[0];
			wrap(askInfo, row);
		}
		return askInfo;
	}

	public List<AskInfo> getAskInfoByProfessId(int professId) {
		// TODO Auto-generated method stub
		Result result;
		List<AskInfo> askInfos = new ArrayList<AskInfo>();
		String sql = SqlStatement.Ask.ASK_INFO + " and professionNo = ?";// 要执行的语句
		Object[] args = new Object[] { professId };
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);// 返回的结果

		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			AskInfo askInfo = new AskInfo();
			wrap(askInfo, row);
			askInfos.add(askInfo);
		}
		return askInfos;
	}

	public int updateAskReaderCount(AskInfo askInfo) {
		// TODO Auto-generated method stub
		String sql = SqlStatement.Ask.ASK_UPDATE_READERCOUNT + "and askNo = ?";
		return new SqlCommand(sql, new Object[] { askInfo.getReaderCount() + 1,
				askInfo.getAskNo()}).execute(null);
	}

	public List<AskInfoViewBean> getAskInfoViewBeans(AskInfo askInfo) {
		// TODO Auto-generated method stub
		String sql = null;
		askInfo=getAskInfoByAskId(askInfo.getAskNo());
		int flag = askInfo.getTeacherNo();
		if (flag == -1) {
			sql = SqlStatement.AskInfoViewBean.ASKINFOVIEWBEAN_SELECT
					+ " and askNo = ?";
		} else {
			sql = SqlStatement.AskInfoViewBean.ASKINFOVIEWBEAN_SELECT_TEACHER
					+ " and askNo = ?";
		}

		Result result;
		List<AskInfoViewBean> list = new ArrayList<AskInfoViewBean>();
		AskInfoViewBean view = null;
		Student student = null;
		Profession profession = null;
		Teacher teacher = null;
		AskInfo askInfo2 = null;

		Object[] args = new Object[] { askInfo.getAskNo() };
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);// 返回的结果

		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			view = new AskInfoViewBean();
			student = new Student();
			teacher = new Teacher();
			profession = new Profession();
			askInfo2 = new AskInfo();

			// 公有信息处理
			int professionNo = Integer.parseInt(String.valueOf(row
					.get("professionNo")));
			askInfo2.setProfessionNo(professionNo);
			profession.setProfessionNo(professionNo);
			int teacherNo = Integer.parseInt(String.valueOf(row
					.get("teacherNo")));
			askInfo2.setTeacherNo(teacherNo);

			int studentNo = Integer.parseInt(String.valueOf(row
					.get("studentNo")));
			askInfo2.setStudentNo(studentNo);
			student.setStuNo(studentNo);

			// askInfo部分
			askInfo2.setAskNo(Integer.parseInt(String.valueOf(row.get("askNo"))));
			askInfo2.setAskTopic(String.valueOf(row.get("askTopic")));
			askInfo2.setAskContent(String.valueOf(row.get("askContent")));
			askInfo2.setAskDate((Date) row.get("askDate"));
			askInfo2.setReaderCount(Integer.parseInt(String.valueOf(row
					.get("readerCount"))));
			askInfo2.setStatus(Integer.parseInt(String.valueOf(row
					.get("status"))));

			// student部分
			student.setProfessNo(Integer.parseInt(String.valueOf(row
					.get("stuProfession"))));
			student.setStuName(String.valueOf(row.get("studentName")));
			student.setStuPwd(String.valueOf(row.get("studentPwd")));
			student.setStuEmail(String.valueOf(row.get("studentEmail")));
			student.setStuPic(String.valueOf(row.get("studentPic")));

			// teacher部分
			if (flag != -1) {
				teacher.setTeaNo(teacherNo);
				teacher.setProfeNo(Integer.parseInt(String.valueOf(row
						.get("teaProfession"))));
				teacher.setTeaName(String.valueOf(row.get("teacherName")));
				teacher.setTeaPwd(String.valueOf(row.get("teacherPwd")));
				teacher.setTeaGrade(String.valueOf(row.get("teacherGrade")));
				teacher.setTeaDescription(String.valueOf(row
						.get("teacherDescription")));
				teacher.setPoints(Integer.parseInt(String.valueOf(row
						.get("points"))));
				teacher.setTeaSatisfaction(Integer.parseInt(String.valueOf(row
						.get("teacherSatisfaction"))));
				teacher.setTeaPic(String.valueOf(row.get("teacherPic")));

				profession.setProfessionName(String.valueOf(row
						.get("professionName")));
				profession.setAcademyNo(Integer.parseInt(String.valueOf(row
						.get("academyNo"))));
			}

			view.setAskInfo(askInfo2);
			view.setProfession(profession);
			view.setStudent(student);
			view.setTeacher(teacher);

			list.add(view);
		}
		return list;
	}

	public List<AskInfo> getUnfinishedQuestion(String key) {
		// TODO Auto-generated method stub
		List<AskInfo> searchAskInfos = searchAskInfoByInfo(key);
		List<AskInfo> searchUnfinished = new ArrayList<AskInfo>();
		for (AskInfo askInfo : searchAskInfos) {
			if (askInfo.getStatus() == 0) {
				searchUnfinished.add(askInfo);
			}
		}
		return searchUnfinished;
	}

	public List<AskInfo> getFinishedQuestion(String key) {
		// TODO Auto-generated method stub
		List<AskInfo> searchAskInfos = searchAskInfoByInfo(key);
		List<AskInfo> searchFinished = new ArrayList<AskInfo>();
		for (AskInfo askInfo : searchAskInfos) {
			if (askInfo.getStatus() == 1) {
				searchFinished.add(askInfo);
			}
		}
		return searchFinished;
	}

	public int addAskInfo(AskInfo askInfo) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Ask.ASK_INSERT, new Object[] {
				askInfo.getStudentNo(), askInfo.getTeacherNo(),
				askInfo.getProfessionNo(), askInfo.getAskTopic(),
				askInfo.getAskContent() }).execute(null);
	}

	public List<AskInfo> getAskInfoByStudentId(Student student) {
		// TODO Auto-generated method stub
		Result result;
		List<AskInfo> askInfos = new ArrayList<AskInfo>();
		String sql = SqlStatement.Ask.ASK_INFO+" and studentNo = ?";// 要执行的语句
		SqlCommand command = new SqlCommand(sql, new Object[]{student.getStuNo()});
		result = command.getResult(null);// 返回的结果

		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			AskInfo askInfo = new AskInfo();
			wrap(askInfo, row);
			askInfos.add(askInfo);
		}
		return askInfos;
	}

	public List<AskInfo> getFinishedAskInfoByStudentId(Student student) {
		// TODO Auto-generated method stub
		List<AskInfo> finishedAskInfos=new ArrayList<AskInfo>();
		List<AskInfo> askInfos=getAskInfoByStudentId(student);
		for (AskInfo askInfo : askInfos) {
			if(askInfo.getStatus()==1){
				finishedAskInfos.add(askInfo);
			}
		}
		return finishedAskInfos;
	}

	public List<AskInfo> getUnfinishedAskInfoByStudentId(Student student) {
		// TODO Auto-generated method stub
		List<AskInfo> unfinishedAskInfos=new ArrayList<AskInfo>();
		List<AskInfo> askInfos=	getAskInfoByStudentId(student);
		for (AskInfo askInfo : askInfos) {
			if(askInfo.getStatus()==0){
				unfinishedAskInfos.add(askInfo);
			}
		}
		return unfinishedAskInfos;
	}

	public int updateStatusById(AskInfo askInfo) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Ask.ASK_UPDATE_STATUS+" and askNo = ?", new Object[]{askInfo.getAskNo()}).execute(null);
	}

	public List<AskInfo> getFinishedAskInfoByProfessId(int professId) {
		// TODO Auto-generated method stub
		List<AskInfo> finishedAskInfos=new ArrayList<AskInfo>();
		List<AskInfo> askInfos=getAskInfoByProfessId(professId);
		for (AskInfo askInfo : askInfos) {
			if(askInfo.getStatus()==1){
				finishedAskInfos.add(askInfo);
			}
		}
		return finishedAskInfos;
	}

	public List<AskInfo> getUnfinishedAskInfoByProfessId(int professId) {
		// TODO Auto-generated method stub
		List<AskInfo> unfinishedAskInfos=new ArrayList<AskInfo>();
		List<AskInfo> askInfos=getAskInfoByProfessId(professId);
		for (AskInfo askInfo : askInfos) {
			if(askInfo.getStatus()==0){
				unfinishedAskInfos.add(askInfo);
			}
		}
		return unfinishedAskInfos;
	}
	
	public List<AskInfo> getAskInfoByAskTop(String askTop) {
		// TODO Auto-generated method stub
		Result result;
		List<AskInfo> askInfos = new ArrayList<AskInfo>();
		AskInfo askInfo = new AskInfo();
		
		String sql = SqlStatement.Ask.ASK_INFO + " and upper(askTopic) like '%"+askTop+"%'";// 要执行的语句
		Object[] args = null;
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);// 返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[0];
			wrap(askInfo, row);
			askInfos.add(askInfo);
		}
		return askInfos;
	}
	
	public int delete(int askNo){
		return new SqlCommand(SqlStatement.Ask.ASK_DELETE, new Object[]{askNo}).execute(null);
	}
}
