package cn.com.views;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.com.dao.GradeDao;
import cn.com.dao.RecordDao;
import cn.com.dao.UserDao;
import cn.com.entity.Record;
import cn.com.entity.User;

public class CheckFrame extends JFrame{
	JLabel lblId;
	MainFrame mf;
	JPanel pnlMain;
	JLabel lblImage;
	
	String cardId;
	UserDao userDao;
	RecordDao recordDao;
	GradeDao gradeDao;
	public CheckFrame(MainFrame mf,String title,String cardId){
		super(title);
		pnlMain=new JPanel();
		lblId=new JLabel();
		this.mf=mf;
		this.cardId=cardId;
		lblImage=new JLabel();
		userDao=new UserDao();
		recordDao=new RecordDao();
		gradeDao=new GradeDao();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		if(mf!=null){
			int x=this.mf.getX()+3;
			int y=this.mf.getY()+50;
			this.setLocation(x, y);
		}
		User user=new User();
		user.setUserId(cardId);
		if(userDao.volidate(user)){	
			int time=recordDao.getOpenCount(user,null);
			user=userDao.getUserById(user);
			int count=gradeDao.getGradeById(user.getUserGradeId()).getGradeCount();
			if(time<count){
				int off=count-time-1;
				Record r=new Record(0, user.getUserId(), null);
				recordDao.addRecord(r);
				lblId.setText("<html>�û���ţ�"+cardId+"<br/> �û�������"+user.getUserName()+"<br/> �����ѿ��Ŵ�����"+(time+1)+"�� <br/>"+"ʣ�࿪�Ŵ�����"+off+"��</html>");
				lblImage.setIcon(new ImageIcon("images/open.gif"));
			}else{
				lblId.setText("<html>�û���ţ�"+cardId+"<br/> �û�������"+user.getUserName()+"<br/> ���Ŵ������࣬���ʿ���</html>");
			}
		}else{
			lblId.setText("�Ƿ������ʿ���");
		}
		pnlMain.add(lblId);
		pnlMain.add(lblImage);
		this.add(pnlMain);
		this.setVisible(true);
	}
}
