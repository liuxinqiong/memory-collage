package cn.com.daoInf;

import java.util.List;

import cn.com.entity.Academy;


public interface AcadeDao {
	public List<Academy> getAllAcademy(); 
	public List<Academy> getAcademy(Academy acade);
	public int insert(Academy acade);
	public int update(Academy acade);
	public int delete(Academy acade);
	public Academy getAcademyById(Academy acade);
}
