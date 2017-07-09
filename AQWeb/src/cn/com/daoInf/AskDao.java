package cn.com.daoInf;

import java.util.List;

import cn.com.entity.AskInfo;
import cn.com.entity.AskInfoViewBean;
import cn.com.entity.Student;


public interface AskDao {
	public List<AskInfo> getAllAskInfo(); 
	public List<AskInfo> getAskInfoByFlag(int flag);
	public List<AskInfo> searchAskInfoByInfo(String info);
	public AskInfo getAskInfoByAskId(int askId);
	public List<AskInfo> getAskInfoByProfessId(int professId);
	public List<AskInfo> getFinishedAskInfoByProfessId(int professId);
	public List<AskInfo> getUnfinishedAskInfoByProfessId(int professId);
	public int updateAskReaderCount(AskInfo askInfo);
	public List<AskInfoViewBean> getAskInfoViewBeans(AskInfo askInfo);
	public List<AskInfo> getUnfinishedQuestion(String key);
	public List<AskInfo> getFinishedQuestion(String key);
	public int addAskInfo(AskInfo askInfo);
	public List<AskInfo> getAskInfoByStudentId(Student student);
	public List<AskInfo> getFinishedAskInfoByStudentId(Student student);
	public List<AskInfo> getUnfinishedAskInfoByStudentId(Student student);
	public int updateStatusById(AskInfo askInfo);
}
