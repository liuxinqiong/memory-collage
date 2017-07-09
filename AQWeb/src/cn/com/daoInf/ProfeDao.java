package cn.com.daoInf;

import java.util.List;

import cn.com.entity.Academy;
import cn.com.entity.Profession;

public interface ProfeDao {
	public List<Profession> getAllProfession();
	public List<Profession> getProfessionByAcademyId(int AcademyId);
	public Profession getProfessionById(Profession profession);
	public List<Profession> getProfeByAcadeId(Academy academy);
	public int insert(Profession profession);
	public int delete(Profession profession);
}
