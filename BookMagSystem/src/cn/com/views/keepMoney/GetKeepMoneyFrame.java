package cn.com.views.keepMoney;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.keepMoneyInfo.KeepMoneyInfoDAOImp;
import cn.com.daos.keepMoneyInfo.KeepMoneyInfoDAOInf;
import cn.com.global.Check;
import cn.com.global.Global;
import cn.com.listeners.keepMoney.GetKeepMoneyFrame_btnSave_ActionListener;
import cn.com.listeners.keepMoney.GetKeepMoneyFrame_btnSearch_ActionListener;
import cn.com.views.petcard.ReaderInfoDialog;

public class GetKeepMoneyFrame extends JFrame {
	JPanel pnlMain;
	JPanel pnlCenter;
	JPanel pnlSouth;
	JLabel lblReaderId;
	public JTextField txtReaderId;
	JLabel lblReaderName;
	public JLabel lblReaderNameIn;
	JLabel lblMoney;
	JTextField txtMoney;
	JButton btnSave;
	JButton btnCancel;
	JButton btnSearch;
	KeepMoneyInfoDAOInf dao;
	UserInfoBean user;
	KeepMoneyMagPanel kmmp;
	int type;

	public GetKeepMoneyFrame(UserInfoBean user, KeepMoneyMagPanel kmmp, int type) {
		this.user = user;
		this.kmmp = kmmp;
		this.type = type;
		pnlMain = new JPanel(new BorderLayout());
		pnlCenter = new JPanel(null);
		pnlSouth = new JPanel();
		lblReaderId = new JLabel("读者编号");
		txtReaderId = new JTextField();
		lblReaderName = new JLabel("读者姓名");
		lblReaderNameIn = new JLabel();
		lblMoney = new JLabel("收取金额");
		txtMoney = new JTextField();
		btnSave = new JButton("保存");
		btnCancel = new JButton("取消");
		btnSearch = new JButton();
		dao = new KeepMoneyInfoDAOImp();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(320, 200);
		if (type == 2) {
			pnlCenter.setBorder(new TitledBorder(null, "收取押金",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, null, null));
		} else if (type == 1) {
			pnlCenter.setBorder(new TitledBorder(null, "退还押金",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, null, null));
		} else if (type == 0) {
			pnlCenter.setBorder(new TitledBorder(null, "没收押金",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, null, null));
		}
		Global.setCenterByWindow(this);

		lblReaderId.setBounds(10, 20, 60, 25);
		pnlCenter.add(lblReaderId);
		txtReaderId.setBounds(60, 20, 80, 25);
		txtReaderId.setEditable(false);
		pnlCenter.add(txtReaderId);
		ImageIcon image = new ImageIcon("images/query_16.png");
		btnSearch.setIcon(image);
		btnSearch.setBounds(150, 20, 25, 25);
		pnlCenter.add(btnSearch);
		lblReaderName.setBounds(185, 20, 60, 25);
		pnlCenter.add(lblReaderName);
		lblReaderNameIn.setBounds(250, 20, 60, 25);
		pnlCenter.add(lblReaderNameIn);
		lblMoney.setBounds(10, 60, 60, 25);
		pnlCenter.add(lblMoney);
		txtMoney.setBounds(70, 60, 60, 25);
		pnlCenter.add(txtMoney);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);

		btnSearch
				.addActionListener(new GetKeepMoneyFrame_btnSearch_ActionListener(
						this));
		btnSave.addActionListener(new GetKeepMoneyFrame_btnSave_ActionListener(
				this));
		btnCancel
				.addActionListener(new GetKeepMoneyFrame_btnSave_ActionListener(
						this));

		pnlSouth.add(btnSave);
		pnlSouth.add(btnCancel);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		this.add(pnlMain);
		this.setVisible(true);
	}

	public void btnSearch_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ReaderInfoDialog(null, "", true, null, null, null, null, this);
	}

	public void btnSave_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnSave)) {
			if (!Check.isDouble(txtMoney.getText().trim())) {
				JOptionPane.showMessageDialog(null, "所输金额非法", "错误",
						JOptionPane.ERROR_MESSAGE);
			} else {
				ReaderInfoBean reader = new ReaderInfoBean();
				reader.setReaderId(new Integer(txtReaderId.getText().trim()));
				String msg="";
				if(type==2){
					msg="收取";
				}else if(type==1){
					msg="退还";
				}else if(type==0){
					msg="没收";
				}
				if (dao.getKeepMoney(reader, user, txtMoney.getText().trim(),
						type)) {
					JOptionPane.showMessageDialog(null, msg+"成功", "提示",
							JOptionPane.INFORMATION_MESSAGE);
					this.kmmp.setTableViewData(dao.getAllKeepMoneyInfoView(""));
					this.kmmp.setTabViewStandard();
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, msg+"失败", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}else{
			this.dispose();
		}
	}
}
