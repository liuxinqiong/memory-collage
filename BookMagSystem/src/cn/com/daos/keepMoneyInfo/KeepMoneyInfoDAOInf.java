package cn.com.daos.keepMoneyInfo;

import java.util.List;

import cn.com.beans.keepMoneyInfo.KeeyMoneyInfoViewBean;
import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.userInfo.UserInfoBean;

public interface KeepMoneyInfoDAOInf {
	List<KeeyMoneyInfoViewBean> getAllKeepMoneyInfoView(String content);
	boolean getKeepMoney(ReaderInfoBean reader,UserInfoBean user,String money,int type);
}
