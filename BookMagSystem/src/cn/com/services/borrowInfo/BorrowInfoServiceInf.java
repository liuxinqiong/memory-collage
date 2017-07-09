package cn.com.services.borrowInfo;

import java.util.List;

import cn.com.beans.borrowInfo.BorrowInfoViewBean;


public interface BorrowInfoServiceInf {
	public boolean borrowOneBook(BorrowInfoViewBean biv);
	public List<BorrowInfoViewBean> getExtendedBorrowInfo();
	public List<BorrowInfoViewBean> getExtendedBorrowInfoByRequire(int days,String content);
	public List<BorrowInfoViewBean> getHistoryBorrowInfo();
	public List<BorrowInfoViewBean> getHistoryBorrowInfoByRequire(String startDate, String endDate, String content);

}
