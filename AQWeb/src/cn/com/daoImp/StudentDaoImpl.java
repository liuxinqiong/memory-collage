package cn.com.daoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.StudentDao;
import cn.com.db.SqlCommand;
import cn.com.db.SqlStatement;
import cn.com.entity.Student;

public class StudentDaoImpl implements StudentDao{

	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		Result result;
		List<Student> students = new ArrayList<Student>();
		String sql = SqlStatement.Student.STUDENT_INFO;//要执行的语句
		
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);//返回的结果
		
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			Student student = new Student();
			
			student.setStuNo(Integer.parseInt(String.valueOf(row.get("studentNo"))));
			student.setProfessNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
			student.setStuName(row.get("studentName").toString());
			student.setStuPwd(row.get("studentPwd").toString());
			student.setStuEmail(row.get("studentEmail").toString());
			student.setStuPic(row.get("studentPic").toString());
			student.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
			students.add(student);
		}
		return students;
	}


	public Student getUserInfo(Student stu){
		Result result;
		Student student = new Student();
		String name = stu.getStuName();
		Object args[] = new Object[1];
		args[0] = name;
		
 		String sql = SqlStatement.Student.STUDENT_INFO +" and studentname = ? ";
 		
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);
		
		
		SortedMap<String, Object>[] rows = result.getRows();
		
		
		if(rows.length!=0){
			SortedMap<String, Object> row = rows[0];
			student.setStuNo(Integer.parseInt(String.valueOf(row.get("studentNo"))));
			student.setProfessNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
			student.setStuName(row.get("studentName").toString());
			student.setStuPwd(row.get("studentPwd").toString());
			student.setStuEmail(row.get("studentEmail").toString());
			student.setStuPic(row.get("studentPic").toString());
			student.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
		}
		return student;
		
	}
	
	public boolean changeStudentInfo(Student stu) {
		// TODO Auto-generated method stub
		
		String sql = "update aq_student set    studentpwd = ?,  studentemail = ?, studentpic = ? where studentno = ?";
		int i = new SqlCommand(sql,
				new Object[] {stu.getStuPwd() ,stu.getStuEmail() ,stu.getStuPic(), stu.getStuNo() }).execute(null);
		if(i==1){
			return true;
		}else{
			return false;
		}
	}

	public Student getStudentById(int studentNo) {
		// TODO Auto-generated method stub
		Result result;
		Student student = new Student();
		String sql = SqlStatement.Student.STUDENT_INFO+" and studentno = ?";//要执行的语句
		
		SqlCommand command = new SqlCommand(sql,  new Object[] { studentNo });
		result = command.getResult(null);//返回的结果
		
		SortedMap<String, Object>[] rows = result.getRows();
		if (rows.length != 0) {
			SortedMap<String, Object> row = rows[0];
			wrap(student, row);
		}
		return student;
	}

	private void wrap(Student student, SortedMap<String, Object> row) {
		// TODO Auto-generated method stub
		student.setStuNo(Integer.parseInt(String.valueOf(row.get("studentNo"))));
		student.setProfessNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
		student.setStuName(row.get("studentName").toString());
		student.setStuPwd(row.get("studentPwd").toString());
		student.setStuEmail(row.get("studentEmail").toString());
		student.setStuPic(row.get("studentPic").toString());
		student.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
	}
	
	public boolean addStudent(Student student) {
		// TODO Auto-generated method stub
		String sql = "insert into aq_student (studentno, professionno, studentname, studentpwd, studentemail, studentpic, isdel) values  (seq_student_studentno.nextval, ?, ?, '123', ?, ?, 0)";
		int i = new SqlCommand(sql,
				new Object[] {student.getProfessNo(),student.getStuName(),student.getStuEmail(),student.getStuPic()}).execute(null);
		if(i==1){
			return true;
		}else{
			return false;
		}
	}

	public boolean changeStu(Student student) {
		// TODO Auto-generated method stub
		String sql = "update aq_student set professionno = ?,   studentname = ?,  studentpwd = ?,  studentemail = ?, studentpic = ? where studentno = ?";
		int i = new SqlCommand(sql,
				new Object[] { student.getProfessNo(),student.getStuName(),student.getStuPwd(),student.getStuEmail(),student.getStuPic(),student.getStuNo()}).execute(null);
		if(i==1){
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteStu(Student student) {
		// TODO Auto-generated method stub
		String sql = "update aq_student set isdel = 1 where studentno = ?";
		int i = new SqlCommand(sql,
				new Object[] {student.getStuNo() }).execute(null);
		if(i==1){
			return true;
		}else{
			return false;
		}
	}

	public List<Student> inquiryStuByName(String stuName) {
		// TODO Auto-generated method stub
		Result result;
		List<Student> students = new ArrayList<Student>();
		String sql = SqlStatement.Student.STUDENT_INFO+"and   studentname like '%"+stuName +"%'";//要执行的语句	
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);//返回的结果	
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			Student student = new Student();
			student.setStuNo(Integer.parseInt(String.valueOf(row.get("studentNo"))));
			student.setProfessNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
			student.setStuName(row.get("studentName").toString());
			student.setStuPwd(row.get("studentPwd").toString());
			student.setStuEmail(row.get("studentEmail").toString());
			student.setStuPic(row.get("studentPic").toString());
			student.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));			
			students.add(student);
		} 
		return students;
	}

}
