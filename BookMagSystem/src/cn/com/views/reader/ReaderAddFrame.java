package cn.com.views.reader;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.global.Global;
import cn.com.listeners.reader.ReaderAddFrame_btnSave_ActionListener;
import cn.com.listeners.reader.ReaderAddFrame_cmbReaderType_ItemListener;

public class ReaderAddFrame extends JFrame {
	JPanel pnlMain;
	JPanel pnlCenter;
	JPanel pnlSouth;
	JPanel pnlNorth;

	JButton btnSave;

	JLabel lblReaderType;
	JComboBox cmbReaderType;
	DefaultComboBoxModel dcmReaderType;
	JLabel lblMaxNum;
	JLabel lblMaxNumIn;
	JLabel lblDays;
	JLabel lblDaysIn;
	JLabel lblKeepMoney;
	JLabel lblKeepMoneyIn;
	JLabel lblNormalRent;
	JLabel lblNormalRentIn;
	JLabel lblExtendedRent;
	JLabel lblExtendedRentIn;
	JLabel lblLossTimes;
	JLabel lblLossTimesIn;

	JLabel lblReaderName;
	JTextField txtReaderName;
	JLabel lblSex;
	JComboBox cmbSexView;
	DefaultComboBoxModel dcmSexView;
	JLabel lblReaderCardType;
	JComboBox cmbCardTypeView;
	DefaultComboBoxModel dcmCardTypeView;
	JLabel lblIdentifyCard;
	JTextField txtIdentifyCard;
	JLabel lblReaderTel;
	JTextField txtReaderTel;
	JLabel lblReaderState;
	JComboBox cmbReaderStateView;
	DefaultComboBoxModel dcmReaderStateView;
	JLabel lblPwd;
	JPasswordField txtPwd;
	JLabel lblPwdAgain;
	JPasswordField txtPwdAgain;

	ReaderInfoDAOInf dao;
	UserInfoBean u;
	ReaderInfoMagFrame rimf;
	ReaderInfoViewBean rr;

	public ReaderAddFrame(ReaderInfoViewBean rr, ReaderInfoMagFrame rimf,
			UserInfoBean u) {
		this.rimf = rimf;
		this.u = u;
		this.rr = rr;
		dao = new ReaderInfoDAOImp();
		pnlMain = new JPanel(new BorderLayout());
		pnlCenter = new JPanel(new GridLayout(4, 4, 10, 15));
		pnlSouth = new JPanel();
		pnlNorth = new JPanel(new GridLayout(4, 4, 10, 15));

		btnSave = new JButton("保存");

		lblReaderType = new JLabel("读者类型");
		cmbReaderType = new JComboBox();
		dcmReaderType = new DefaultComboBoxModel();
		lblMaxNum = new JLabel("最大借数");
		lblMaxNumIn = new JLabel();
		lblDays = new JLabel("最大天数");
		lblDaysIn = new JLabel();
		lblKeepMoney = new JLabel("押金");
		lblKeepMoneyIn = new JLabel();
		lblNormalRent = new JLabel("正常租金");
		lblNormalRentIn = new JLabel();
		lblExtendedRent = new JLabel("超期租金");
		lblExtendedRentIn = new JLabel();
		lblLossTimes = new JLabel("赔款倍数");
		lblLossTimesIn = new JLabel();

		lblReaderName = new JLabel("姓名：");
		txtReaderName = new JTextField();
		lblSex = new JLabel("性别：");
		cmbSexView = new JComboBox();
		dcmSexView = new DefaultComboBoxModel();

		lblReaderCardType = new JLabel("证件类型");
		cmbCardTypeView = new JComboBox();

		dcmCardTypeView = new DefaultComboBoxModel();
		lblIdentifyCard = new JLabel("证件号码");
		txtIdentifyCard = new JTextField();
		lblReaderTel = new JLabel("电话");
		txtReaderTel = new JTextField();
		lblReaderState = new JLabel("用户状态");

		cmbReaderStateView = new JComboBox();

		dcmReaderStateView = new DefaultComboBoxModel();

		lblPwd = new JLabel("密码");
		txtPwd = new JPasswordField();
		lblPwdAgain = new JLabel("确认密码");
		txtPwdAgain = new JPasswordField();

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		if(rr==null){
			this.setTitle("读者信息添加");
		}else{
			this.setTitle("读者信息修改");
		}
		
		this.setSize(400, 400);
		Global.setCenterByWindow(this);
		pnlSouth.add(btnSave);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);

		pnlCenter.add(lblReaderName);
		pnlCenter.add(txtReaderName);
		pnlCenter.add(lblSex);
		pnlCenter.add(cmbSexView);
		pnlCenter.add(lblReaderCardType);
		pnlCenter.add(cmbCardTypeView);
		pnlCenter.add(lblIdentifyCard);
		pnlCenter.add(txtIdentifyCard);
		pnlCenter.add(lblReaderTel);
		pnlCenter.add(txtReaderTel);
		pnlCenter.add(lblReaderState);
		pnlCenter.add(cmbReaderStateView);
		pnlCenter.add(lblPwd);
		pnlCenter.add(txtPwd);
		pnlCenter.add(lblPwdAgain);
		pnlCenter.add(txtPwdAgain);
		if (rr != null) {
			txtReaderName.setText(rr.getReader().getReaderName());
			dcmSexView.setSelectedItem(rr.getReader().getReaderSex());
			dcmCardTypeView.setSelectedItem(rr.getReader().getReaderCardType());
			txtIdentifyCard.setText(rr.getReader().getReaderIdentifyCard());
			txtReaderTel.setText(rr.getReader().getReaderTel());
			dcmReaderStateView.setSelectedItem(rr.getReader().getReaderState());
			txtPwd.setText(rr.getReader().getReaderPwd());
			txtPwdAgain.setText(rr.getReader().getReaderPwd());
			dcmReaderType.setSelectedItem(rr.getReaderType());
		}
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setBorder(new TitledBorder(null, "基本信息",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));

		pnlNorth.add(lblReaderType);
		pnlNorth.add(cmbReaderType);
		pnlNorth.add(lblMaxNum);
		pnlNorth.add(lblMaxNumIn);
		pnlNorth.add(lblDays);
		pnlNorth.add(lblDaysIn);
		pnlNorth.add(lblKeepMoney);
		pnlNorth.add(lblKeepMoneyIn);
		pnlNorth.add(lblNormalRent);
		pnlNorth.add(lblNormalRentIn);
		pnlNorth.add(lblExtendedRent);
		pnlNorth.add(lblExtendedRentIn);
		pnlNorth.add(lblLossTimes);
		pnlNorth.add(lblLossTimesIn);
		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setBorder(new TitledBorder(null, "读者类型",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));

		setCmbViewData();
		initReaderTypeInfo();

		btnSave.addActionListener(new ReaderAddFrame_btnSave_ActionListener(
				this));
		cmbReaderType
				.addItemListener(new ReaderAddFrame_cmbReaderType_ItemListener(
						this));

		this.add(pnlMain);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void initReaderTypeInfo() {
		// TODO Auto-generated method stub
		ReaderTypeInfoBean r = (ReaderTypeInfoBean) cmbReaderType
				.getSelectedItem();
		lblMaxNumIn.setText(r.getMaxNum() + "");
		lblDaysIn.setText(r.getDays() + "");
		lblKeepMoneyIn.setText(r.getKeepMoney() + "");
		lblNormalRentIn.setText(r.getNormalRent() + "");
		lblExtendedRentIn.setText(r.getExtendedRent() + "");
		lblLossTimesIn.setText(r.getLossTimes() + "");
	}

	private void setCmbViewData() {
		// TODO Auto-generated method stub
		List<ReaderTypeInfoBean> list = dao.getAllReaderTypeInfo();
		for (ReaderTypeInfoBean r : list) {
			dcmReaderType.addElement(r);
		}
		cmbReaderType.setModel(dcmReaderType);

		dcmSexView.addElement("男");
		dcmSexView.addElement("女");
		cmbSexView.setModel(dcmSexView);

		dcmCardTypeView.addElement("身份证");
		dcmCardTypeView.addElement("学生证");
		dcmCardTypeView.addElement("军人证");
		dcmCardTypeView.addElement("其他");
		cmbCardTypeView.setModel(dcmCardTypeView);

		dcmReaderStateView.addElement("正常");
		dcmReaderStateView.addElement("挂失");
		cmbReaderStateView.setModel(dcmReaderStateView);

	}

	public void btnSave_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (txtReaderName.getText().trim().length() == 0
				|| txtIdentifyCard.getText().trim().length() == 0
				|| txtReaderTel.getText().trim().length() == 0
				|| new String(txtPwd.getPassword()).trim().length() == 0
				|| new String(txtPwdAgain.getPassword()).trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "信息填写不完全", "错误",
					JOptionPane.ERROR_MESSAGE);
		} else if (!new String(txtPwd.getPassword()).trim().endsWith(
				new String(txtPwdAgain.getPassword()).trim())) {
			JOptionPane.showMessageDialog(null, "两次密码输入不一致", "错误",
					JOptionPane.ERROR_MESSAGE);
		} else {
			ReaderInfoBean r = new ReaderInfoBean();
			r.setReaderName(txtReaderName.getText().trim());
			r.setReaderSex((String) cmbSexView.getSelectedItem());
			r.setReaderCardType((String) cmbCardTypeView.getSelectedItem());
			r.setReaderIdentifyCard(txtIdentifyCard.getText().trim());
			r.setReaderTel(txtReaderTel.getText().trim());
			r.setReaderTypeId(((ReaderTypeInfoBean) cmbReaderType
					.getSelectedItem()).getReaderTypeId());
			if (cmbReaderStateView.getSelectedItem().equals("正常")) {
				r.setReaderState(1);
			} else {
				r.setReaderState(0);
			}
			r.setReaderPwd(new String(txtPwd.getPassword()).trim());
			if(rr==null){
				if (dao.addReader(r)) {
					JOptionPane.showMessageDialog(null, "新增成功", "提示",
							JOptionPane.INFORMATION_MESSAGE);
					rimf.setTableData(dao.getAllReaderViewInfo());
					rimf.setTableStandard();
					this.dispose();
					JournaInfoDAO.writeJournalInfo(u.getUserName(),
							"新增了编号为" + r.getReaderId() + "的读者信息",
							JournaInfoDAO.TYPE_RM);
				} else {
					JOptionPane.showMessageDialog(null, "新增失败", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}else{
				r.setReaderId(rr.getReader().getReaderId());
				r.setReaderRegistDate(rr.getReader().getReaderRegistDate());
				if (dao.updateReader(r)) {
					JOptionPane.showMessageDialog(null, "修改成功", "提示",
							JOptionPane.INFORMATION_MESSAGE);
					rimf.setTableData(dao.getAllReaderViewInfo());
					rimf.setTableStandard();
					this.dispose();
					JournaInfoDAO.writeJournalInfo(u.getUserName(),
							"修改了编号为" + r.getReaderId() + "的读者信息",
							JournaInfoDAO.TYPE_RM);
				} else {
					JOptionPane.showMessageDialog(null, "修改失败", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		
		}
	}

	public void cmbReaderType_itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		initReaderTypeInfo();
	}
}
