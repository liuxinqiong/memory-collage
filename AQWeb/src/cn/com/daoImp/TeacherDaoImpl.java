package cn.com.daoImp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import sun.awt.SunHints.Value;

import cn.com.daoInf.TeacherDao;
import cn.com.db.SqlCommand;
import cn.com.db.SqlStatement;
import cn.com.entity.Teacher;

public class TeacherDaoImpl implements TeacherDao{

	public List<Teacher> getAllTeacher() {
		// TODO Auto-generated method stub
		Result result;
		List<Teacher> teachers = new ArrayList<Teacher>();
		String sql = SqlStatement.Teacher.TEACHER_INFO;//要执行的语句
		
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);//返回的结果
		
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			Teacher teacher = new Teacher();
			
			teacher.setTeaNo(Integer.parseInt(String.valueOf(row.get("teacherNo"))));
			teacher.setProfeNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
			teacher.setTeaName(row.get("teacherName").toString());
			teacher.setTeaPwd(row.get("teacherPwd").toString());
			teacher.setTeaGrade(row.get("teacherGrade").toString());
			teacher.setTeaDescription(row.get("teacherDescription").toString());
			teacher.setPoints(Integer.parseInt(String.valueOf(row.get("points"))));
			teacher.setTeaSatisfaction(Integer.parseInt(String.valueOf(row.get("teacherSatisfaction"))));
			teacher.setStatus(Integer.parseInt(String.valueOf(row.get("status"))));
			teacher.setTeaPic(row.get("teacherPic").toString());
			teacher.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
			teachers.add(teacher);
		}
		return teachers;
	
	}
	
	public Teacher getTeacherInfo(Teacher teacher){
		Result result;
		Teacher tea = new Teacher();
		String name =teacher.getTeaName();
		Object args[] = new Object[1];
		args[0] = name;
		
 		String sql = SqlStatement.Teacher.TEACHER_INFO +" and teachername = ? ";
 		
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);
		
		
		SortedMap<String, Object>[] rows = result.getRows();
	
		
		if(rows.length!=0){
			SortedMap<String, Object> row = rows[0];
			tea.setTeaNo(Integer.parseInt(String.valueOf(row.get("teacherNo"))));
			tea.setProfeNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
			tea.setTeaName(row.get("teacherName").toString());
			tea.setTeaPwd(row.get("teacherPwd").toString());
			tea.setTeaGrade(row.get("teacherGrade").toString());
			tea.setTeaDescription(row.get("teacherDescription").toString());
			tea.setPoints(Integer.parseInt(String.valueOf(row.get("points"))));
			tea.setTeaSatisfaction(Integer.parseInt(String.valueOf(row.get("teacherSatisfaction"))));
			tea.setStatus(Integer.parseInt(String.valueOf(row.get("status"))));
			tea.setTeaPic(row.get("teacherPic").toString());
			tea.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
		}
		return tea;
		
	}
	
	public List<Teacher> getTeacherTop10BySati(){
		Result result;
		String sql = SqlStatement.Teacher.TEACHER_INFO +"  and rownum <=10 order by teachersatisfaction desc  ";//要执行的语句
		
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);//返回的结果
		
		SortedMap<String, Object>[] rows = result.getRows();
		
		return  this.wrap(rows);
		
		
	}
	
	private List<Teacher> wrap(SortedMap<String, Object>[] rows) {
		// TODO Auto-generated method stub
		List<Teacher> teachers = new ArrayList<Teacher>();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			Teacher teacher = new Teacher();
			
			teacher.setTeaNo(Integer.parseInt(String.valueOf(row.get("teacherNo"))));
			teacher.setProfeNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
			teacher.setTeaName(row.get("teacherName").toString());
			teacher.setTeaPwd(row.get("teacherPwd").toString());
			teacher.setTeaGrade(row.get("teacherGrade").toString());
			teacher.setTeaDescription(row.get("teacherDescription").toString());
			teacher.setPoints(Integer.parseInt(String.valueOf(row.get("points"))));
			teacher.setTeaSatisfaction(Integer.parseInt(String.valueOf(row.get("teacherSatisfaction"))));
			teacher.setStatus(Integer.parseInt(String.valueOf(row.get("status"))));
			teacher.setTeaPic(row.get("teacherPic").toString());
			teacher.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
			teachers.add(teacher);
		}
		return teachers;
	}

	public List<Teacher> getTeacherTop10ByPoint(){
		Result result;
		String sql = SqlStatement.Teacher.TEACHER_INFO +"  and rownum <=10 order by points desc  ";//要执行的语句
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		return  this.wrap(rows);
	}

	public int updatePoints(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql = SqlStatement.Teacher.TEACHER_UPDATE_POINTS + "and teacherNo = ?";
		return new SqlCommand(sql, new Object[] { teacher.getPoints() + 2,
				teacher.getTeaNo() }).execute(null);
	}

	public int setSatifaction() {
		// TODO Auto-generated method stub
		List<Teacher> teachers=getAllTeacher();  
		int times=0;
		int checkTimes=0;
		float satifa=0;
		for (Teacher teacher : teachers) {
			times=getAnswerTimes(teacher, 0);
			checkTimes=getAnswerTimes(teacher, 1);
			if(times==0){
				satifa=0;
			}else{
				satifa=((float)checkTimes/times)*5;
			}
			new SqlCommand(SqlStatement.Teacher.TEACHER_UPDATE_SATISFA+"and teacherNo = ?", new Object[]{satifa,teacher.getTeaNo()}).execute(null);
		}
		return 1;
	}

	public int getAnswerTimes(Teacher teacher,int flag) {
		// TODO Auto-generated method stub
		int times=0;
		String sql="";
		Result result=null;
		if(flag==0){
			sql=SqlStatement.Answer.ANSWER_SELECT_TIMES+" and teacherNo = ?";
			
		}else{
			sql=SqlStatement.Answer.ANSWER_SELECT_TIMES+" and teacherNo = ? and isChecked=1";
		}
		
		result=new SqlCommand(sql, new Object[]{teacher.getTeaNo()}).getResult(null);
		SortedMap<String, Object>[] rows = result.getRows();
		if(rows.length!=0){
			SortedMap<String, Object> row = rows[0];
			times=Integer.parseInt(String.valueOf(row.get("times")));
		}
		return times;
	}
	
	public boolean changeTeacherInfo(Teacher tea) {
		// TODO Auto-generated method stub

		String sql = " update aq_teacher  set  teacherpwd = ?, teacherdescription = ?,teacherpic = ? where teacherno = ? ";
		int i = new SqlCommand(sql, new Object[] { tea.getTeaPwd(),tea.getTeaDescription(),tea.getTeaPic(),tea.getProfeNo() })
				.execute(null);
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}


	public Teacher getTeacherById(int teacherNo) {
		// TODO Auto-generated method stub
		Result result;
		List<Teacher> teachers = new ArrayList<Teacher>();
		String sql = SqlStatement.Teacher.TEACHER_INFO+" and teacherno = ? ";//要执行的语句
		
		SqlCommand command = new SqlCommand(sql, new Object[]{teacherNo});
		result = command.getResult(null);//返回的结果
		Teacher teacher = new Teacher();
		
		SortedMap<String, Object>[] rows = result.getRows();
		if (rows.length != 0) {
			SortedMap<String, Object> row = rows[0];
			wrap(teacher,row);
		}
		return teacher;
	
	}
	
	private void wrap(Teacher teacher, SortedMap<String, Object> row) {
		// TODO Auto-generated method stub
		teacher.setTeaNo(Integer.parseInt(String.valueOf(row.get("teacherNo"))));
		teacher.setProfeNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
		teacher.setTeaName(row.get("teacherName").toString());
		teacher.setTeaPwd(row.get("teacherPwd").toString());
		teacher.setTeaGrade(row.get("teacherGrade").toString());
		teacher.setTeaDescription(row.get("teacherDescription").toString());
		teacher.setPoints(Integer.parseInt(String.valueOf(row.get("points"))));
		teacher.setTeaSatisfaction(Integer.parseInt(String.valueOf(row.get("teacherSatisfaction"))));
		teacher.setStatus(Integer.parseInt(String.valueOf(row.get("status"))));
		teacher.setTeaPic(row.get("teacherPic").toString());
		teacher.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
	}

	public int addTeacher(Teacher tea) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Teacher.TEACHER_INSERT, new Object[]{tea.getProfeNo(),tea.getTeaName(),tea.getTeaPwd(),tea.getTeaGrade(),tea.getTeaDescription()}).execute(null);
	}
}
