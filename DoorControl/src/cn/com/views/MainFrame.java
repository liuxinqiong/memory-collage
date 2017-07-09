package cn.com.views;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.com.entity.Mag;
import cn.com.global.Global;

public class MainFrame extends JFrame{
	JPanel pnlMain;
	FunctionButtonPanel fbp;
	MainNorthPanel mnp;
	JPanel pnlCenter;
	Mag mag;
	public MainFrame(Mag mag){
		this.mag=mag;
		pnlMain=new JPanel(new BorderLayout());
		fbp=new FunctionButtonPanel(this);
		mnp=new MainNorthPanel();
		pnlCenter=new JPanel(){
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				g.drawImage(new ImageIcon("images/index.png").getImage(), 0, 0, pnlCenter.getWidth(), pnlCenter.getHeight(), null);
			}
		};
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650, 400);
		this.setTitle("门禁系统的设计与模拟");
		Global.setCenterByWindow(this);
		pnlMain.add(fbp,BorderLayout.WEST);
		pnlMain.add(mnp,BorderLayout.NORTH);
		pnlMain.add(pnlCenter,BorderLayout.CENTER);
		this.add(pnlMain);
//		this.setResizable(false);
		this.setVisible(true);
	}
}
