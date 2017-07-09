package cn.com.daoInf;

import java.util.List;


import cn.com.entity.FileShare;
import cn.com.entity.Teacher;

public interface FileDao {
	public List<FileShare> getAllFileShare();
	List<FileShare> getAllFileShareByTeaNo(Teacher teacher);
	
}
