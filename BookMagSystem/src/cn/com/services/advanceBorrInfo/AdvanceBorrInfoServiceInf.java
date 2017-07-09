package cn.com.services.advanceBorrInfo;

import java.util.List;

import cn.com.beans.advanceBorrInfo.AdvanceBorrInfoViewBean;


public interface AdvanceBorrInfoServiceInf {
	public List<AdvanceBorrInfoViewBean> getAllAdvanceBorrInfo();
	public List<AdvanceBorrInfoViewBean> getAdvanceBorrInfoByRequire(int index,String startDate,String endDate,String content);
	public boolean deleteAdvanceBorrInfo(AdvanceBorrInfoViewBean abiv);
}
