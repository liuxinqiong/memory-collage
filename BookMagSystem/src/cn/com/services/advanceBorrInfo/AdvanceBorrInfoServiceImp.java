package cn.com.services.advanceBorrInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.advanceBorrInfo.AdvanceBorrInfoViewBean;
import cn.com.daos.advanceBorrInfo.AdvanceBorrInfoDAOImp;
import cn.com.daos.advanceBorrInfo.AdvanceBorrInfoDAOInf;

public class AdvanceBorrInfoServiceImp implements AdvanceBorrInfoServiceInf {
	private AdvanceBorrInfoDAOInf dao;

	public AdvanceBorrInfoServiceImp() {
		dao = new AdvanceBorrInfoDAOImp();
	}

	@Override
	public List<AdvanceBorrInfoViewBean> getAllAdvanceBorrInfo() {
		// TODO Auto-generated method stub
		return dao.getAllAdvanceBorrInfo("");
	}

	@Override
	public List<AdvanceBorrInfoViewBean> getAdvanceBorrInfoByRequire(int index,
			String startDate, String endDate, String content) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<AdvanceBorrInfoViewBean> list=dao.getAllAdvanceBorrInfo(content);
		List<AdvanceBorrInfoViewBean> newList=new ArrayList<AdvanceBorrInfoViewBean>();
		for(AdvanceBorrInfoViewBean abiv:list){
			try {
				boolean boolDate=!(sdf.parse(startDate).after(sdf.parse(abiv.getAdvanceBorrInfo().getAdvanceBorrDate())))&&(!sdf.parse(endDate).before(sdf.parse(abiv.getAdvanceBorrInfo().getAdvanceBorrDate())));
				boolean boolYN=abiv.getBookView().getBook().getBookTotalNum()-abiv.getBookView().getBook().getBookOutNum()>0;
				if(index==0&&boolDate){
					newList.add(abiv);
				}else if(index==1&&(!boolYN)&&boolDate){
					newList.add(abiv);
				}else if(index==2&&boolYN&&boolDate){
					newList.add(abiv);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newList;
	}

	@Override
	public boolean deleteAdvanceBorrInfo(AdvanceBorrInfoViewBean abiv) {
		// TODO Auto-generated method stub
		return dao.deleteAdvanceBorrInfo(abiv.getAdvanceBorrInfo());
	}
}
