package cn.com.views;

import javax.swing.JDialog;
import javax.swing.JFrame;

import cn.com.global.Global;

public class UserMagDialog extends JDialog{
	UserMagPanel ump;
	public UserMagDialog(MainFrame mf,String title,boolean model){
		super(mf, title, model);
		ump=new UserMagPanel(mf);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(500,300);
		Global.setCenterByWindow(this);
		this.add(ump);
	}
	
}
