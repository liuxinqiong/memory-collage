package cn.com.views.petcard;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.listeners.petcard.ReaderInfoDialog_tabView_MouseListener;
import cn.com.views.keepMoney.GetKeepMoneyFrame;

public class ReaderInfoDialog extends JDialog{
	private JPanel pnlMain;
	private JTable tabView;
	private JScrollPane snpView;
	private DefaultTableModel dtm;
	private ReaderInfoDAOInf dao;
	public String msg;
	ActPetCardFrame apcf;
	RechargePetCardFrame rpcf;
	LogOffPetCardFrame lpcf;
	ManagePetCardFrame mpcf;
	GetKeepMoneyFrame gkmf;
	
	public ReaderInfoDialog(Frame frame , String title ,boolean modl,ActPetCardFrame apcf,RechargePetCardFrame rpcf,LogOffPetCardFrame lpcf,ManagePetCardFrame mpcf,GetKeepMoneyFrame gkmf){
		super(frame,title,modl);
		this.apcf = apcf;
		this.rpcf = rpcf;
		this.lpcf = lpcf;
		this.mpcf = mpcf;
		this.gkmf = gkmf;
		initObject();
		init();
	
	}
	

	private void initObject() {
		// TODO Auto-generated method stub
		pnlMain = new JPanel(null);
		snpView = new JScrollPane();
		tabView = new JTable(){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		dao = new ReaderInfoDAOImp();
	}


	private void init() {
		// TODO Auto-generated method stub
		this.setSize(150, 200);
		if(this.apcf != null){
		
			int x=this.apcf.getX()+this.apcf.tfdUserId.getX()+3;
			int y=this.apcf.getY()+this.apcf.tfdUserId.getY()+50;
			this.setLocation(x, y);
		}
		if(this.rpcf != null){
			int x=this.rpcf.getX()+this.rpcf.tfdUserId.getX()+3;
			int y=this.rpcf.getY()+this.rpcf.tfdUserId.getY()+50;
			this.setLocation(x, y);
		}
		
		if(this.lpcf != null){
			int x=this.lpcf.getX()+this.lpcf.tfdUserId.getX()+3;
			int y=this.lpcf.getY()+this.lpcf.tfdUserId.getY()+50;
			this.setLocation(x, y);
		}
		
		if(this.mpcf != null){
			int x=this.mpcf.getX()+this.mpcf.tfdUserInfo.getX()+3;
			int y=this.mpcf.getY()+this.mpcf.tfdUserInfo.getY()+50;
			this.setLocation(x, y);
		}
		if(this.gkmf != null){
			int x=this.gkmf.getX()+this.gkmf.txtReaderId.getX()+3;
			int y=this.gkmf.getY()+this.gkmf.txtReaderId.getY()+50;
			this.setLocation(x, y);
		}
		
		tabView.addMouseListener(new ReaderInfoDialog_tabView_MouseListener(this));
		this.setUndecorated(true);
		snpView.setBounds(0, 0, 150, 200);
		snpView.getViewport().add(tabView);
		pnlMain.add(snpView);
		setTabData(dao.getAllReaderInfo());
		this.add(pnlMain);
		this.setVisible(true);
	}


	private void setTabData(List<ReaderInfoBean> allReaderInfo) {
		// TODO Auto-generated method stub
		Vector<String> tit = new Vector<String>();
		String[] st = {"读者编号","姓名"};
		for(String s : st){
			tit.add(s);
		}
		dtm = new DefaultTableModel(null,tit);
		Vector data=null;
		for(ReaderInfoBean reader:allReaderInfo){
			data = new Vector();
			data.add(reader.getReaderId());
			data.add(reader.getReaderName());
			dtm.addRow(data);
		}
		
		this.tabView.setModel(dtm);
	}


	public void tabView_mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
			if(tabView.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(null, "请选择读者","提示",JOptionPane.OK_OPTION);
			}
			else{
				int rowIndex = tabView.getSelectedRow();
				int id = (Integer)tabView.getValueAt(rowIndex, 0);
				if(this.apcf != null){
					this.apcf.tfdUserId.setText(id+"");
				}
				if(this.rpcf != null){
					this.rpcf.tfdUserId.setText(id+"");
				}
				if(this.lpcf != null){
					this.lpcf.tfdUserId.setText(id+"");
				}
				if(this.mpcf != null){
					this.mpcf.tfdUserInfo.setText(id+"");
				}if(this.gkmf != null){
					this.gkmf.txtReaderId.setText(id+"");
					this.gkmf.lblReaderNameIn.setText((String)tabView.getValueAt(rowIndex, 1));
				}
				this.dispose();		
			}
		}
	}
}
