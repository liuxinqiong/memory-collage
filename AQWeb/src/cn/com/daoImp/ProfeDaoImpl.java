package cn.com.daoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.ProfeDao;
import cn.com.db.SqlCommand;
import cn.com.db.SqlStatement;
import cn.com.entity.Academy;
import cn.com.entity.Profession;

public class ProfeDaoImpl implements ProfeDao{

	public List<Profession> getAllProfession() {
		// TODO Auto-generated method stub
		Result result;
		List<Profession> professions= new ArrayList<Profession>();
		String sql = SqlStatement.Profession.PROFESSION_INFO;//要执行的语句
		SqlCommand command = new SqlCommand(sql, null);
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			Profession profession = new Profession();
			wrap(profession,row);
			professions.add(profession);
		}
		return professions;
	}

	public List<Profession> getProfessionByAcademyId(int AcademyId) {
		// TODO Auto-generated method stub
		Result result;
		List<Profession> professions= new ArrayList<Profession>();
		String sql = SqlStatement.Profession.PROFESSION_INFO+" and academyNo = ?";//要执行的语句
		SqlCommand command = new SqlCommand(sql, new Object[]{AcademyId});
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			Profession profession = new Profession();
			wrap(profession,row);
			professions.add(profession);
		}
		return professions;
	}

	private void wrap(Profession profession, SortedMap<String, Object> row) {
		// TODO Auto-generated method stub
		profession.setProfessionNo(Integer.parseInt(String.valueOf(row.get("professionNo"))));
		profession.setProfessionName(row.get("professionName").toString());
		profession.setAcademyNo(Integer.parseInt(String.valueOf(row.get("academyNo"))));
		profession.setIsDel(Integer.parseInt(String.valueOf(row.get("isDel"))));
	}

	public Profession getProfessionById(Profession profession) {
		// TODO Auto-generated method stub
		Result result;
		Profession profession2= new Profession();
		String sql = SqlStatement.Profession.PROFESSION_INFO+" and professionNo = ?";//要执行的语句
		SqlCommand command = new SqlCommand(sql, new Object[]{profession.getProfessionNo()});
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		if(rows.length!=0){
			SortedMap<String, Object> row=rows[0];
			wrap(profession2,row);
		}
		return profession2;
	}

	public List<Profession> getProfeByAcadeId(Academy academy) {
		// TODO Auto-generated method stub
		Result result;
		List<Profession> professions= new ArrayList<Profession>();
		String sql = SqlStatement.Profession.PROFESSION_INFO+" and academyno = ?";//要执行的语句
		
		SqlCommand command = new SqlCommand(sql, new Object[]{academy.getAcademyNo()});
		result = command.getResult(null);//返回的结果
		SortedMap<String, Object>[] rows = result.getRows();
		for (int i = 0; i < rows.length; i++) {
			SortedMap<String, Object> row = rows[i];
			Profession profession = new Profession();
			wrap(profession,row);
			professions.add(profession);
		}
		return professions;
	}
	
	public int insert(Profession profession) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Profession.PROFESSION_INSERT, new Object[]{profession.getProfessionName(),profession.getAcademyNo()}).execute(null);
	}
	
	public int delete(Profession profession) {
		// TODO Auto-generated method stub
		return new SqlCommand(SqlStatement.Profession.PROFESSION_DELETE, new Object[]{profession.getProfessionNo()}).execute(null);
	}
}
