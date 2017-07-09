package cn.com.beans.keepMoneyInfo;

import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.userInfo.UserInfoBean;

public class KeeyMoneyInfoViewBean {
	private KeeyMoneyInfoBean KeeyMoney;
	private UserInfoBean user;
	private ReaderInfoViewBean readerView;
	public KeeyMoneyInfoBean getKeeyMoney() {
		return KeeyMoney;
	}
	public void setKeeyMoney(KeeyMoneyInfoBean keeyMoney) {
		KeeyMoney = keeyMoney;
	}
	public UserInfoBean getUser() {
		return user;
	}
	public void setUser(UserInfoBean user) {
		this.user = user;
	}
	public ReaderInfoViewBean getReaderView() {
		return readerView;
	}
	public void setReaderView(ReaderInfoViewBean readerView) {
		this.readerView = readerView;
	}
	
	public String toString(){
		return this.KeeyMoney.toString();
	}
}
