package cn.com.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.com.dao.MagDao;
import cn.com.entity.Mag;
import cn.com.global.Global;
import cn.com.listeners.LoginFrame_btnLogin_ActionListener;
import cn.com.listeners.LoginFrame_btnLogin_MouseListener;
import cn.com.listeners.LoginFrame_btnReset_ActionListener;
import cn.com.listeners.LoginFrame_pwdUserPwd_ActionListener;
import cn.com.listeners.LoginFrame_pwdUserPwd_MouseListener;


public class LoginFrame extends JFrame{
	private JPanel pnlMain;
	private JLabel lblUserName;
	private JLabel lblUserPwd; 
	private JTextField txtUserName;
	private JPasswordField pwdUserPwd;
	private JButton btnLogin;
	private JButton btnReset;
	static Point loginPoint;
	
	MagDao magDao;

	public LoginFrame() {	
		pnlMain=new JPanel(null);
		lblUserName=new JLabel("�û���");
		lblUserPwd=new JLabel("����"); 
		txtUserName=new JTextField();
		pwdUserPwd=new JPasswordField();;
		btnLogin=new JButton("��¼");
		btnReset=new JButton("����");
		loginPoint=new Point();
		
		magDao=new MagDao();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 358, 260);
		this.setTitle("��ӭ");
		Global.setCenterByWindow(this);
		pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		lblUserName.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblUserName.setBounds(79, 108, 54, 21);
		pnlMain.add(lblUserName);

		lblUserPwd.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblUserPwd.setBounds(79, 152, 54, 21);
		pnlMain.add(lblUserPwd);
		
		txtUserName.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		txtUserName.setBounds(175, 108, 100, 21);
		pnlMain.add(txtUserName);
		
		pwdUserPwd.setBounds(175, 152, 100, 21);
		pnlMain.add(pwdUserPwd);
	
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(60,195,245));
		btnLogin.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		btnLogin.setBounds(90, 190, 75, 25);		
		btnLogin.setBorder(BorderFactory.createEmptyBorder());
		pnlMain.add(btnLogin);
		
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(new Color(60,195,245));
		btnReset.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		btnReset.setBounds(190, 190, 75, 25);		
		btnReset.setBorder(BorderFactory.createEmptyBorder());
		pnlMain.add(btnReset);
		
		btnLogin.addMouseListener(new LoginFrame_btnLogin_MouseListener(this));	
		pwdUserPwd.addMouseListener(new LoginFrame_pwdUserPwd_MouseListener(this));
		btnLogin.addActionListener(new LoginFrame_btnLogin_ActionListener(this));
		btnReset.addActionListener(new LoginFrame_btnReset_ActionListener(this));
		pwdUserPwd.addKeyListener(new LoginFrame_pwdUserPwd_ActionListener(this));
		
		beautifyLoginFrame();
		this.add(pnlMain);
		this.setResizable(false);
		this.setVisible(true);
	}

	

	private void beautifyLoginFrame() {
		// TODO Auto-generated method stub	
		JLabel lblNewLabel_2 = new JLabel("�Ž�ϵͳ��¼");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(123, 37, 128, 29);
		pnlMain.add(lblNewLabel_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(new Color(60,195,245));
		label_1.setBounds(0, 0, 368, 85);
		pnlMain.add(label_1);
		
		//�϶�����
		pnlMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
				loginPoint.x=e.getX();
				loginPoint.y=e.getY();
			}
		});
		//��������϶�����ʱ
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
			JOptionPane.showMessageDialog(null,"���벻��Ϊ�գ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else{
			String magName=txtUserName.getText().trim();
			String magPassword =  new String(pwdUserPwd.getPassword()).trim();
			Mag mag=new Mag(0, magName, magPassword,1);
			mag=magDao.volidate(mag);
			if(mag!=null){			
				new MainFrame(mag);
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null,"�˺Ż��������","��ʾ",JOptionPane.ERROR_MESSAGE);
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
