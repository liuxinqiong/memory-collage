package cn.com.views.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.ModerateSkin;

import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.userInfo.UserInfoDAOImp;
import cn.com.daos.userInfo.UserInfoDAOInf;
import cn.com.global.Global;
import cn.com.listeners.login.LoginFrame_btnLogin_ActionListener;
import cn.com.listeners.login.LoginFrame_btnLogin_MouseListener;
import cn.com.listeners.login.LoginFrame_btnReset_ActionListener;
import cn.com.listeners.login.LoginFrame_cmbView_MouseListener;
import cn.com.listeners.login.LoginFrame_pwdUserPwd_ActionListener;
import cn.com.listeners.login.LoginFrame_pwdUserPwd_MouseListener;
import cn.com.views.main.MainFrame;

public class LoginFrame extends JFrame{
	private JPanel pnlMain;
	private JLabel lblUserName;
	private JLabel lblUserPwd; 
	private JComboBox cmbView;
	private DefaultComboBoxModel dcmView;
	private JPasswordField pwdUserPwd;
	private UserInfoDAOInf dao;
	private JButton btnLogin;
	private JButton btnReset;
	static Point loginPoint;

	public LoginFrame() {	
		pnlMain=new JPanel();
		lblUserName=new JLabel("用户名");
		lblUserPwd=new JLabel("密码"); 
		cmbView=new JComboBox();
		dcmView=new DefaultComboBoxModel();
		pwdUserPwd=new JPasswordField();;
		dao=new UserInfoDAOImp();
		btnLogin=new JButton("登录");
		btnReset=new JButton("重置");
		loginPoint=new Point();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 358, 260);
		this.setTitle("欢迎");
		Global.setCenterByWindow(this);
		pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlMain.setLayout(null);
		
		lblUserName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblUserName.setBounds(79, 108, 54, 21);
		pnlMain.add(lblUserName);

		lblUserPwd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblUserPwd.setBounds(79, 152, 54, 21);
		pnlMain.add(lblUserPwd);
		
		cmbView.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		cmbView.setBounds(175, 108, 100, 21);
		pnlMain.add(cmbView);
		
		pwdUserPwd.setBounds(175, 152, 100, 21);
		pnlMain.add(pwdUserPwd);
	
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(60,195,245));
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnLogin.setBounds(90, 190, 75, 25);		
		btnLogin.setBorder(BorderFactory.createEmptyBorder());
		pnlMain.add(btnLogin);
		
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(new Color(60,195,245));
		btnReset.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnReset.setBounds(190, 190, 75, 25);		
		btnReset.setBorder(BorderFactory.createEmptyBorder());
		pnlMain.add(btnReset);
		
		btnLogin.addMouseListener(new LoginFrame_btnLogin_MouseListener(this));	
		cmbView.addMouseListener(new LoginFrame_cmbView_MouseListener(this));	
		pwdUserPwd.addMouseListener(new LoginFrame_pwdUserPwd_MouseListener(this));
		btnLogin.addActionListener(new LoginFrame_btnLogin_ActionListener(this));
		btnReset.addActionListener(new LoginFrame_btnReset_ActionListener(this));
		pwdUserPwd.addKeyListener(new LoginFrame_pwdUserPwd_ActionListener(this));
		
		setCmbViewData();
		beautifyLoginFrame();
		this.add(pnlMain);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void setCmbViewData() {
		// TODO Auto-generated method stub
		List<UserInfoBean> list=dao.getAllUserInfo();
		for(UserInfoBean u:list){
			dcmView.addElement(u);
		}
		cmbView.setModel(dcmView);
	}

	private void beautifyLoginFrame() {
		// TODO Auto-generated method stub	
		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u501F\u9605\u7CFB\u7EDF");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(123, 37, 128, 29);
		pnlMain.add(lblNewLabel_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(new Color(60,195,245));
		label_1.setBounds(0, 0, 368, 85);
		pnlMain.add(label_1);
		
		//拖动窗口
		pnlMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				loginPoint.x=e.getX();
				loginPoint.y=e.getY();
			}
		});
		//当用鼠标拖动窗体时
		pnlMain.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point point=getLocation();
				setLocation(point.x + e.getX() - loginPoint.x, point.y + e.getY()- loginPoint.y);
			}
		});
	}

	public void btnLogin_mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		btnLogin.setBackground(new Color(135, 206, 235));
	}

	public void btnLogin_mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		btnLogin.setBackground(new Color(60,195,245));
	}

	public void cmbView_mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		cmbView.setBorder(BorderFactory.createLineBorder(new Color(21,131,221)));
	}

	public void cmbView_mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		cmbView.setBorder(BorderFactory.createLineBorder(new Color(122,138,153)));
	}

	public void pwdUserPwd_mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		pwdUserPwd.setBorder(BorderFactory.createLineBorder(new Color(21,131,221)));
	}

	public void pwdUserPwd_mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		pwdUserPwd.setBorder(BorderFactory.createLineBorder(new Color(122,138,153)));
	}

	public void btnLogin_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(pwdUserPwd.getPassword().length == 0){
			JOptionPane.showMessageDialog(null,"密码不能为空！","提示",JOptionPane.WARNING_MESSAGE);
		}else{
			UserInfoBean user = (UserInfoBean)cmbView.getSelectedItem();
			String pwd =  new String(pwdUserPwd.getPassword()).trim();
			user.setUserPwd(pwd);
			if(dao.validateByNameAndPwd(user)){			
				new MainFrame(user);
				JournaInfoDAO.writeJournalInfo(user.getUserName(), "登录系统", JournaInfoDAO.TYPE_LG);
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null,"账号或密码错误！","提示",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void btnReset_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		pwdUserPwd.setText("");
	}
	
	public void pwdUserPwd_keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==10){
			btnLogin.doClick();
		}
	}
}
