package cn.com.daoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.AcadeDao;
import cn.com.db.SqlCommand;
import cn.com.db.SqlStatement;
import cn.com.entity.Academy;



public class AcadeDaoImpl implements AcadeDao {

	public List<Academy> getAllAcademy() {
		// TODO Auto-generated method stub
		return getAcademy(null);
	}

	public List<Academy> getAcademy(Academy acade) {
		// TODO Auto-generated method stub
		Result result;
		List<Academy> academies = new ArrayList<Academy>();
		String sql = SqlStatement.Academy.ACADEMY_INFO;// 要执行的语句
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);// 返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			Academy academy = new Academy();
			academy.setAcademyNo(Integer.parseInt(String.valueOf(row
					.get("academyNo"))));
			academy.setAcademyName(row.get("academyName").toString());
			academy.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
			academies.add(academy);
		}
		return academies;
	}

	public int insert(Academy acade) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Academy.ACADEMY_INSERT,
				new Object[] { acade.getAcademyName(), acade.getIsDel() })
				.execute(null);
	}

	public int update(Academy acade) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Academy.ACADEMY_UPDATE,
				new Object[] { acade.getAcademyName(), acade.getIsDel(),
						acade.getAcademyNo() }).execute(null);
	}

	public int delete(Academy acade) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Academy.ACADEMY_UPDATE,
				new Object[] { acade.getAcademyName(), acade.getIsDel(),
						acade.getAcademyNo() }).execute(null);
	}

	public Academy getAcademyById(Academy acade) {
		// TODO Auto-generated method stub
		Result result;
		Academy academy = new Academy();
		
		String sql = SqlStatement.Academy.ACADEMY_INFO+" and academyNo = ?";// 要执行的语句
		SqlCommand command = new SqlCommand(sql, new Object[]{acade.getAcademyNo()});
		result = command.getResult(null);// 返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		if(rows.length!=0){
			SortedMap<String, Object> row = rows[0];
			academy.setAcademyNo(Integer.parseInt(String.valueOf(row
					.get("academyNo"))));
			academy.setAcademyName(row.get("academyName").toString());
			academy.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
		}
		return academy;
	}
}
