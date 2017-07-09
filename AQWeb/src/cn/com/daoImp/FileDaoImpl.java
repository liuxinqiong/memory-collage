package cn.com.daoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.FileDao;
import cn.com.db.SqlCommand;
import cn.com.db.SqlStatement;
import cn.com.entity.FileShare;
import cn.com.entity.Teacher;

public class FileDaoImpl implements FileDao{

	public List<FileShare> getAllFileShare() {
		// TODO Auto-generated method stub
		Result result;
		List<FileShare> fileShares = new ArrayList<FileShare>();
		String sql = SqlStatement.File.FILE_INFO;//要执行的语句
		
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);//返回的结果
		
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			FileShare fileShare = new FileShare();
			
			fileShare.setFileNo(Integer.parseInt(String.valueOf(row.get("fileNo"))));
			fileShare.setFileName(row.get("fileName").toString());
			fileShare.setProfessionNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
			fileShare.setTeacherNo(Integer.parseInt(String.valueOf(row.get("teacherNo"))));
			fileShare.setFilePath(row.get("filePath").toString());
			fileShare.setDownloadCount(Integer.parseInt(String.valueOf(row.get("downloadCount"))));
			fileShare.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
			fileShares.add(fileShare);
		}
		return fileShares;
	}
	private List<FileShare> wrap(Result result) {
		// TODO Auto-generated method stub
		List<FileShare> fileShares = new ArrayList<FileShare>();
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			FileShare fileShare = new FileShare();
			
			fileShare.setFileNo(Integer.parseInt(String.valueOf(row.get("fileNo"))));
			fileShare.setFileName(row.get("fileName").toString());
			fileShare.setProfessionNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
			fileShare.setTeacherNo(Integer.parseInt(String.valueOf(row.get("teacherNo"))));
			fileShare.setFilePath(row.get("filePath").toString());
			fileShare.setDownloadCount(Integer.parseInt(String.valueOf(row.get("downloadCount"))));
			fileShare.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
			fileShares.add(fileShare);
		}
		return fileShares;
	}

	public List<FileShare> getAllFileShareByTeaNo(Teacher teacher){
		Result result;
		
		String sql = SqlStatement.File.FILE_INFO +"  and teacherno = ? ";//要执行的语句
		int teaNo = teacher.getTeaNo();
		Object args[] = new Object[1];
		args[0] =teaNo;
		SqlCommand command = new SqlCommand(sql, args);
		result = command.getResult(null);//返回的结果	
		return this.wrap(result);
	}
	
}
