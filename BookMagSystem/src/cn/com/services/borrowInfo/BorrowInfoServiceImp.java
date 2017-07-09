package cn.com.services.borrowInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.advanceBorrInfo.AdvanceBorrInfoViewBean;
import cn.com.beans.borrowInfo.BorrowInfoViewBean;
import cn.com.daos.borrowInfo.BorrowInfoDAOImp;
import cn.com.daos.borrowInfo.BorrowInfoDAOInf;
import cn.com.global.Global;

public class BorrowInfoServiceImp implements BorrowInfoServiceInf{
	private BorrowInfoDAOInf dao;
	public BorrowInfoServiceImp(){
		dao=new BorrowInfoDAOImp();
	}
	@Override
	public boolean borrowOneBook(BorrowInfoViewBean biv) {
		// TODO Auto-generated method stub
		return dao.borrowOneBook(biv);
	}
	@Override
	public List<BorrowInfoViewBean> getExtendedBorrowInfo() {
		// TODO Auto-generated method stub
		List<BorrowInfoViewBean> biv=dao.getAllBorrowInfoView("");
		List<BorrowInfoViewBean> list=new ArrayList<BorrowInfoViewBean>();		
		for(BorrowInfoViewBean b:biv){
			if(Global.getTimeBetweenNow(b.getBorrow().getNormalBackDate())>0&&b.getBorrow().getIsBack()==0){
				list.add(b);
			}
		}
		return list;
	}
	@Override
	public List<BorrowInfoViewBean> getExtendedBorrowInfoByRequire(int days,
			String content) {
		// TODO Auto-generated method stub
		List<BorrowInfoViewBean> biv=dao.getAllBorrowInfoView(content);
		List<BorrowInfoViewBean> list=new ArrayList<BorrowInfoViewBean>();		
		for(BorrowInfoViewBean b:biv){
			if(Global.getTimeBetweenNow(b.getBorrow().getNormalBackDate())>=days&&b.getBorrow().getIsBack()==0){
				list.add(b);
			}
		}
		return list;
	}
	@Override
	public List<BorrowInfoViewBean> getHistoryBorrowInfo() {
		// TODO Auto-generated method stub
		return dao.getAllBorrowInfoView("");
	}
	@Override
	public List<BorrowInfoViewBean> getHistoryBorrowInfoByRequire(
			String startDate, String endDate, String content) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<BorrowInfoViewBean> list=dao.getAllBorrowInfoView(content);
		List<BorrowInfoViewBean> newList=new ArrayList<BorrowInfoViewBean>();
		for(BorrowInfoViewBean biv:list){
			try {
				boolean boolDate=!(sdf.parse(startDate).after(sdf.parse(biv.getBorrow().getBorrowDate())))&&(!sdf.parse(endDate).before(sdf.parse(biv.getBorrow().getBorrowDate())));
				if(boolDate){
					newList.add(biv);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newList;
	}

}
