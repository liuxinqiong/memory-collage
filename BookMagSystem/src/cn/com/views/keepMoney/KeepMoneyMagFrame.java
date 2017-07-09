package cn.com.views.keepMoney;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.global.Global;

public class KeepMoneyMagFrame extends JFrame{
	JPanel pnlMain;
	KeepMoneyMagPanel pnlKeepMoneyMag;
	UserInfoBean user;
	
	public KeepMoneyMagFrame(UserInfoBean user){
		this.user=user;
		pnlMain =new JPanel(new GridLayout());
		pnlKeepMoneyMag=new KeepMoneyMagPanel(null,user);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("读者押金管理");
		this.setSize(600,480);
		Global.setCenterByWindow(this);
		pnlMain.add(pnlKeepMoneyMag);
		this.add(pnlMain);
		this.setVisible(true);
	}
}
