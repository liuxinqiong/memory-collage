package cn.com.views.readerType;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.global.Global;
import cn.com.listeners.readerType.SetReaderTypeFrame_btnadd_ActionListeners;
import cn.com.listeners.readerType.SetReaderTypeFrame_btnalt_ActionListeners;
import cn.com.listeners.readerType.SetReaderTypeFrame_btndel_ActionListeners;
import cn.com.listeners.readerType.SetReaderTypeFrame_btnexit_ActionListener;

public class SetReaderTypeFrame extends JFrame{
	JPanel pnlMain;
	JButton btnadd;
	JButton btnalt;
	JButton btndel;
	JButton btnexit;
	JTable tabUserType;
	DefaultTableModel dtm;
	JScrollPane snpView;
	ReaderInfoDAOInf dao;
	UserInfoBean user;
	
	public SetReaderTypeFrame(UserInfoBean user){
		this.user=user;
		pnlMain = new JPanel(null);
		btnadd = new JButton("增加");
		btnalt = new JButton("修改");
		btndel = new JButton("删除");
		btnexit = new JButton("退出");
		tabUserType = new JTable(){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		snpView = new JScrollPane();
		dao = new ReaderInfoDAOImp();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 400);
		this.setResizable(false);
//		pnlMain.setBackground(Color.cyan);
//		btnadd.setBackground(Color.pink);
//		btnalt.setBackground(Color.magenta);
//		btndel.setBackground(Color.red);
//		btnexit.setBackground(Color.yellow);
		btnadd.setFont(new Font("微软雅黑",Font.PLAIN,14));
		btnalt.setFont(new Font("微软雅黑",Font.PLAIN,14));
		btndel.setFont(new Font("微软雅黑",Font.PLAIN,14));
		btnexit.setFont(new Font("微软雅黑",Font.PLAIN,14));
		btnadd.setBounds(10, 10, 65, 65);
		btnalt.setBounds(100, 10, 65, 65);
		btndel.setBounds(190, 10, 65, 65);
		btnexit.setBounds(280, 10, 65, 65);
		snpView.setBounds(0, 80, 700, 280);
		
		btnexit.addActionListener(new SetReaderTypeFrame_btnexit_ActionListener(this));
		btndel.addActionListener(new SetReaderTypeFrame_btndel_ActionListeners(this));
		btnadd.addActionListener(new SetReaderTypeFrame_btnadd_ActionListeners(this));
		btnalt.addActionListener(new SetReaderTypeFrame_btnalt_ActionListeners(this));
		
		setTabViewData(dao.getAllReaderTypeInfo());
		setTabViewStandard();
		snpView.getViewport().add(tabUserType);
		pnlMain.add(btnexit);
		pnlMain.add(snpView);
		pnlMain.add(btnadd);
		pnlMain.add(btnalt);
		pnlMain.add(btndel);
		this.add(pnlMain);
		Global.setCenterByWindow(this);
		this.setTitle("设置读者类别");
		this.setVisible(true);
	}

	public void setTabViewStandard() {
		// TODO Auto-generated method stub
		tabUserType.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabUserType.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tabUserType.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tabUserType.getColumn("超期收费标准（元/天）").setMinWidth(135);
		tabUserType.getColumn("有效时间（天）").setMinWidth(110);
		tabUserType.getColumn("收费标准（元/天）").setMinWidth(115);
		tabUserType.getColumn("押金金额（元）").setMinWidth(110);
		tabUserType.getColumn("丢失罚金倍数").setMinWidth(110);
		
	}

	public void setTabViewData(List<ReaderTypeInfoBean> allReaderTypeInfo) {
		// TODO Auto-generated method stub
		Vector<String> tit = new Vector<String>();
		String[] st = {"类型名称","有效时间（天）","押金金额（元）","收费标准（元/天）","超期收费标准（元/天）","可借数量","丢失罚金倍数"};
		for(String s : st){
			tit.add(s);
		}	
		dtm = new DefaultTableModel(null,tit);
		Vector data = null;
		for(ReaderTypeInfoBean rtib : allReaderTypeInfo){
			data = new Vector();
			data.add(rtib);
			data.add(rtib.getDays());
			data.add(rtib.getKeepMoney());
			data.add(rtib.getNormalRent());
			data.add(rtib.getExtendedRent());
			data.add(rtib.getMaxNum());
			data.add(rtib.getLossTimes());
			dtm.addRow(data);
		}
		
	
		this.tabUserType.setModel(dtm);
	}

	public void btnexit_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	public void btndel_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tabUserType.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(null, "请选择用户","错误",JOptionPane.ERROR_MESSAGE);
		}else{
			int i = JOptionPane.showConfirmDialog(null, "确认删除？","提示",JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION){
				ReaderTypeInfoBean r =(ReaderTypeInfoBean) tabUserType.getValueAt(tabUserType.getSelectedRow(),0 );
				if(dao.delReaderType(r)){
					JOptionPane.showMessageDialog(null, "删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
					setTabViewData(dao.getAllReaderTypeInfo());
					setTabViewStandard();
				}else{
					JOptionPane.showMessageDialog(null, "删除失败","错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void btnadd_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AddRederTypeFrame(this,user);
	}

	public void btnalt_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tabUserType.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null, "请选择用户","错误",JOptionPane.ERROR_MESSAGE);
		}else{
			new AlterReaderTypeFrame(this,user);
		}
	}
}
