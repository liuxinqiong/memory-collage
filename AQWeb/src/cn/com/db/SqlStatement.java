package cn.com.db;

public class SqlStatement {
	public static class Academy {
		public static final String ACADEMY_INFO = "select * from aq_academy where isDel =0";
		public static final String ACADEMY_INSERT = "insert into aq_academy(academyno, academyname, isdel) values (seq_academy_academyno.nextval, ?, ?)";
		public static final String ACADEMY_UPDATE = "update aq_academy set academyname=?,isdel=? where academyno=?";
		// public static final String ACADEMY_DELETE =
		// "delete aq_academy where academyno = ?";
	}

	public static class Answer {
		public static final String ANSWER_INFO = "select * from aq_answerInfo where isDel=0";
		public static final String ANSWER_INSERT="insert into aq_answerinfo(answerno, askno, studentno, teacherno, answercontent, answerdate, ischecked, isdel) values(seq_answerinfo_answerno.nextval, ?, ?, ?, ?, sysdate, 0, 0)";
		public static final String ANSWER_UPDATE_ISCHECKED="update aq_answerInfo set isChecked=1 where 1=1";
		public static final String ANSWER_SELECT_TIMES="select count(*) as times from aq_answerInfo where isDel=0";
		public static final String ANSWER_DELETE = "update aq_answerInfo set isdel = 1  where answerno = ?";

	}

	public static class Ask {
		public static final String ASK_INFO = "select * from aq_askInfo where isDel =0";
		public static final String ASK_UPDATE_READERCOUNT = "update aq_askinfo set readercount = ? where 1=1";
		public static final String ASK_INSERT="insert into aq_askinfo(askno, studentno, teacherno, professionno, asktopic, askcontent, askdate, readercount, status, isdel)values(seq_askinfo_askno.nextval, ?, ?, ?, ?, ?, sysdate, 0, 0, 0)";
		public static final String ASK_UPDATE_STATUS="update aq_askinfo set status =1 where 1=1";
		public static final String ASK_DELETE = "update aq_askInfo set isdel=1 where askno=?";
	}

	public static class File {
		public static final String FILE_INFO = "select * from aq_fileShare where isDel =0";
	}

	public static class Profession {
		public static final String PROFESSION_INFO = "select * from aq_profession where isDel=0";
		public static final String PROFESSION_DELETE = "update aq_profession set isdel = 1 where professionno = ?";
		public static final String PROFESSION_UPDATE = "update aq_profession set professionname = ? where professionno = ?";
		public static final String PROFESSION_INSERT = "insert into aq_profession (professionno, professionname, academyno, isdel) values (seq_profession_professionno.nextval, ?, ?, 0)";

	}

	public static class Student {
		public static final String STUDENT_INFO = "select * from aq_student where isDel =0";
	}

	public static class Teacher {
		public static final String TEACHER_INFO = "select * from aq_teacher where isDel =0";
		public static final String TEACHER_UPDATE_POINTS="update aq_teacher set points = ? where 1=1";
		public static final String TEACHER_UPDATE_SATISFA="update aq_teacher set teachersatisfaction = ? where 1=1";
		public static final String TEACHER_INSERT="insert into aq_teacher(teacherno, professionno, teachername, teacherpwd, teachergrade, teacherdescription, points, teachersatisfaction, status, teacherpic, isdel)values(seq_teacher_teacherno.nextval, ?, ?, ?, ?, ?, 0, 0, 0, '1.pic', 0)";
	}

	public static class User {
		public static final String USER_INFO = "select * from aq_user where isDel =0";
		public static final String USER_DELETE = "update aq_user set isdel = 1 where userno = ?";
		public static final String USER_INSERT = "insert into aq_user(userno, username, userpwd, usertype, isdel) values (seq_user.nextval, ?, ?, ?, 0)";
	}

	public static class AskInfoViewBean {
		public static final String ASKINFOVIEWBEAN_SELECT = "select  aq_askinfo.*,aq_student.professionno as stuProfession ,studentname, studentpwd, studentemail, studentpic,professionname, academyno from aq_askinfo,aq_student,aq_profession where aq_askinfo.studentno=aq_student.studentno  and aq_askinfo.professionno=aq_profession.professionno and aq_askinfo.isdel=0";
		public static final String ASKINFOVIEWBEAN_SELECT_TEACHER = "select  aq_askinfo.*,aq_student.professionno as stuProfession ,studentname, studentpwd, studentemail, studentpic,aq_teacher.professionno as teaProfession,teachername, teacherpwd, teachergrade, teacherdescription, points,teachersatisfaction, teacherpic,professionname, academyno from aq_askinfo,aq_student,aq_profession,aq_teacher where aq_askinfo.studentno=aq_student.studentno and  aq_askinfo.teacherno=aq_teacher.teacherno and aq_askinfo.professionno=aq_profession.professionno and aq_askinfo.isdel=0";

	}
	
	public static class AnswerInfoViewBean{
		public static final String ANSWERINFOVIEWBEAN_SELECT="select aq_answerinfo.*,aq_student.professionno as stuPro, studentname, studentpwd, studentemail, studentpic,aq_teacher.professionno as teaPro, teachername, teacherpwd, teachergrade, teacherdescription, points, teachersatisfaction,teacherpic,aq_askinfo.teacherno as askTeacher,aq_askinfo.professionno as askPro,askTopic,askContent,askDate,readerCount,aq_askinfo.status as askStatus from aq_student,aq_teacher,aq_askinfo,aq_answerinfo where  aq_answerinfo.askno=aq_askinfo.askno and aq_answerinfo.studentno=aq_student.studentno and aq_answerinfo.teacherno=aq_teacher.teacherno and aq_answerinfo.isdel=0";
		
	}
}
