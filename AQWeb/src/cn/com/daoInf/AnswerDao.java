package cn.com.daoInf;

import java.util.List;

import cn.com.entity.AnswerInfo;
import cn.com.entity.AnswerInfoViewBean;
import cn.com.entity.Teacher;


public interface AnswerDao {
	public List<AnswerInfo> getAllAnswerInfo();
	public List<AnswerInfo> getAnswerInfoByAskId(int askId);
	public List<AnswerInfoViewBean> getAnswerInfoViewBeans(AnswerInfo answerInfo);
	public List<AnswerInfo> getUncheckedAnswerInfo(int askId);
	public AnswerInfo getCheckedAnswerInfo(int askId);
	public int getAnswerNumByTeaNo(Teacher teacher);
	public int addAnswerInfo(AnswerInfo answerInfo);
	public int acceptAnswerById(AnswerInfo answerInfo);
}
