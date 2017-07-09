package cn.com.daos.advanceBorrInfo;

import java.util.List;

import cn.com.beans.advanceBorrInfo.AdvanceBorrInfoBean;
import cn.com.beans.advanceBorrInfo.AdvanceBorrInfoViewBean;

public interface AdvanceBorrInfoDAOInf {
	public List<AdvanceBorrInfoViewBean> getAllAdvanceBorrInfo(String content);
	public boolean deleteAdvanceBorrInfo(AdvanceBorrInfoBean abiv);
}
