package cn.com.daoInf;

import java.util.List;

import cn.com.entity.Teacher;

public interface TeacherDao {
	public List<Teacher> getAllTeacher(); 
	public Teacher getTeacherInfo(Teacher teacher);
	public List<Teacher> getTeacherTop10BySati();
	public List<Teacher> getTeacherTop10ByPoint();
	public int updatePoints(Teacher teacher);
	public int setSatifaction();
	public int getAnswerTimes(Teacher teacher,int flag);
	public boolean changeTeacherInfo(Teacher tea);
	public int addTeacher(Teacher tea);
}
