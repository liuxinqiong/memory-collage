package cn.com.daoImp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.AnswerDao;
import cn.com.db.SqlCommand;
import cn.com.db.SqlStatement;
import cn.com.entity.AnswerInfo;
import cn.com.entity.AnswerInfoViewBean;
import cn.com.entity.AskInfo;
import cn.com.entity.Student;
import cn.com.entity.Teacher;

public class AnswerDaoImpl implements AnswerDao{

	public List<AnswerInfo> getAllAnswerInfo() {
		// TODO Auto-generated method stub
		Result result;
		List<AnswerInfo> answerInfos = new ArrayList<AnswerInfo>();
		String sql = SqlStatement.Answer.ANSWER_INFO;//要执行的语句
		
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			AnswerInfo answerInfo = new AnswerInfo();
			wrap(answerInfo, row);
			answerInfos.add(answerInfo);
		}
		return answerInfos;
	}

	public List<AnswerInfo> getAnswerInfoByAskId(int askId) {
		// TODO Auto-generated method stub
		Result result;
		List<AnswerInfo> answerInfos = new ArrayList<AnswerInfo>();
		String sql = SqlStatement.Answer.ANSWER_INFO + " and askNo = ?";//要执行的语句
		Object[] args=new Object[]{ askId};
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			AnswerInfo answerInfo = new AnswerInfo();
			wrap(answerInfo, row);
			answerInfos.add(answerInfo);
		}
		return answerInfos;
	}
	
	public void wrap(AnswerInfo answerInfo,SortedMap<String, Object> row){
		answerInfo.setAnswerNo(Integer.parseInt(String.valueOf(row.get("answerNo"))));
		answerInfo.setAskNo(Integer.parseInt(String.valueOf(row.get("askNo"))));
		answerInfo.setStudentNo(Integer.parseInt(String.valueOf(row.get("studentNo"))));
		answerInfo.setTeacherNo(Integer.parseInt(String.valueOf(row.get("teacherNo"))));
		answerInfo.setAnswerContent(row.get("answerContent").toString());
		answerInfo.setAnswerDate((Date)row.get("answerDate"));
		answerInfo.setIsChecked(Integer.parseInt(String.valueOf(row.get("isChecked"))));
		answerInfo.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
	}

	public List<AnswerInfoViewBean> getAnswerInfoViewBeans(AnswerInfo answerInfo) {
		// TODO Auto-generated method stub
		String sql=SqlStatement.AnswerInfoViewBean.ANSWERINFOVIEWBEAN_SELECT+" and answerNo = ?";
		Result result;
		List<AnswerInfoViewBean> list = new ArrayList<AnswerInfoViewBean>();
		AnswerInfoViewBean view=null;
		Student student=null;
		Teacher teacher=null;
		AnswerInfo answerInfo2=null;
		AskInfo askInfo=null;
		
		Object[] args = new Object[] { answerInfo.getAnswerNo() };
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);// 返回的结果

		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			view=new AnswerInfoViewBean();
			student=new Student();
			teacher=new Teacher();
			answerInfo2=new AnswerInfo();
			askInfo = new AskInfo();
			
			//公有信息处理
			int askNo=Integer.parseInt(String.valueOf(row.get("askNo")));
			askInfo.setAskNo(askNo);
			answerInfo2.setAskNo(askNo);
			int teacherNo=Integer.parseInt(String.valueOf(row.get("teacherNo")));
			answerInfo2.setTeacherNo(teacherNo);
			teacher.setTeaNo(teacherNo);
			int studentNo=Integer.parseInt(String.valueOf(row.get("studentNo")));
			answerInfo2.setStudentNo(studentNo);
			student.setStuNo(studentNo);
			
			//askInfo部分
			askInfo.setStudentNo(studentNo);
			askInfo.setTeacherNo(Integer.parseInt(String.valueOf(row.get("askTeacher"))));
			int askProfessionNo=Integer.parseInt(String.valueOf(row.get("askPro")));
			askInfo.setProfessionNo(askProfessionNo);
			askInfo.setAskTopic(String.valueOf(row.get("askTopic")));
			askInfo.setAskContent(String.valueOf(row.get("askContent")));
			askInfo.setAskDate((Date)row.get("askDate"));
			askInfo.setReaderCount(Integer.parseInt(String.valueOf(row.get("readerCount"))));
			askInfo.setStatus(Integer.parseInt(String.valueOf(row.get("askStatus"))));
			
			//student部分
			student.setProfessNo(Integer.parseInt(String.valueOf(row.get("stuPro"))));
			student.setStuName(String.valueOf(row.get("studentName")));
			student.setStuPwd(String.valueOf(row.get("studentPwd")));
			student.setStuEmail(String.valueOf(row.get("studentEmail")));
			student.setStuPic(String.valueOf(row.get("studentPic")));
			
			//teacher部分
			teacher.setProfeNo(Integer.parseInt(String.valueOf(row.get("teaPro"))));
			teacher.setTeaName(String.valueOf(row.get("teacherName")));
			teacher.setTeaPwd(String.valueOf(row.get("teacherPwd")));
			teacher.setTeaGrade(String.valueOf(row.get("teacherGrade")));
			teacher.setTeaDescription(String.valueOf(row.get("teacherDescription")));
			teacher.setPoints(Integer.parseInt(String.valueOf(row.get("points"))));
			teacher.setTeaSatisfaction(Integer.parseInt(String.valueOf(row.get("teacherSatisfaction"))));
			teacher.setTeaPic(String.valueOf(row.get("teacherPic")));
			
			//answer部分
			answerInfo2.setAnswerNo(Integer.parseInt(String.valueOf(row.get("answerNo"))));
			answerInfo2.setAnswerContent(String.valueOf(row.get("answerContent")));
			answerInfo2.setAnswerDate((Date)row.get("answerDate"));
			answerInfo2.setIsChecked(Integer.parseInt(String.valueOf(row.get("isChecked"))));
			
			
			
			view.setAskInfo(askInfo);
			view.setAnswerInfo(answerInfo2);
			view.setStudent(student);
			view.setTeacher(teacher);
			
			list.add(view);
		}
		return list;
	}

	public List<AnswerInfo> getUncheckedAnswerInfo(int askId) {
		// TODO Auto-generated method stub
		List<AnswerInfo> answerInfos=getAnswerInfoByAskId(askId);
		List<AnswerInfo> uncheackedAnswerInfos = answerInfos;
		for (AnswerInfo answerInfo : answerInfos) {
			if (answerInfo.getIsChecked() == 1) {
				uncheackedAnswerInfos.remove(answerInfo);
				break;
			}
		}
		return uncheackedAnswerInfos;
	}

	public AnswerInfo getCheckedAnswerInfo(int askId) {
		// TODO Auto-generated method stub
		List<AnswerInfo> answerInfos=getAnswerInfoByAskId(askId);
		AnswerInfo cheackedAnswerInfo = null;
		for (AnswerInfo answerInfo : answerInfos) {
			if (answerInfo.getIsChecked() == 1) {
				cheackedAnswerInfo = answerInfo;
				break;
			}
		}
		return cheackedAnswerInfo;
	}
	
	public int getAnswerNumByTeaNo(Teacher teacher){
		Result result;
		String sql = SqlStatement.Answer.ANSWER_INFO +" and teacherno = ? ";//要执行的语句	
		int teaNo = teacher.getTeaNo();	
		Object args[] = new Object[1];
		args[0] = teaNo;
		SqlCommand command = new SqlCommand(sql, args);		
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		return rows.length;
	}

	public int addAnswerInfo(AnswerInfo answerInfo) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Answer.ANSWER_INSERT, new Object[]{answerInfo.getAskNo(),answerInfo.getStudentNo(),answerInfo.getTeacherNo(),answerInfo.getAnswerContent()}).execute(null);
	}

	public int acceptAnswerById(AnswerInfo answerInfo) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Answer.ANSWER_UPDATE_ISCHECKED+" and answerNo = ?", new Object[]{answerInfo.getAnswerNo()}).execute(null);
	}
	
	public int delete(AnswerInfo answerInfo){
		return new SqlCommand(SqlStatement.Answer.ANSWER_DELETE,
				new Object[] { answerInfo.getAnswerNo()}).execute(null);
	}
	
	public List<AnswerInfo> getAnswerInfoByAskNo(int askNo) {
		// TODO Auto-generated method stub
		Result result;
		List<AnswerInfo> answerInfos = new ArrayList<AnswerInfo>();
		String sql = SqlStatement.Answer.ANSWER_INFO+" and askno = ? ";//要执行的语句
		
		SqlCommand command = new SqlCommand(sql, new Object[]{askNo});
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			AnswerInfo answerInfo = new AnswerInfo();
			wrap(answerInfo,row);
			answerInfos.add(answerInfo);
		}
		return answerInfos;
	}
	
}
