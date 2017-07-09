package cn.com.daoInf;

import java.util.List;

import cn.com.entity.Student;


public interface StudentDao {
	public List<Student> getAllStudent();
	public Student getUserInfo(Student stu);
	public boolean changeStudentInfo(Student stu);
	public boolean changeStu(Student student);
	public boolean addStudent(Student student);
	public boolean deleteStu(Student student);
	public List<Student> inquiryStuByName(String stuName);
	
}
