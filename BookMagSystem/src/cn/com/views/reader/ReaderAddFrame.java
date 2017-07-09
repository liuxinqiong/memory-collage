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

		btnSave = new JButton("����");

		lblReaderType = new JLabel("��������");
		cmbReaderType = new JComboBox();
		dcmReaderType = new DefaultComboBoxModel();
		lblMaxNum = new JLabel("������");
		lblMaxNumIn = new JLabel();
		lblDays = new JLabel("�������");
		lblDaysIn = new JLabel();
		lblKeepMoney = new JLabel("Ѻ��");
		lblKeepMoneyIn = new JLabel();
		lblNormalRent = new JLabel("�������");
		lblNormalRentIn = new JLabel();
		lblExtendedRent = new JLabel("�������");
		lblExtendedRentIn = new JLabel();
		lblLossTimes = new JLabel("����");
		lblLossTimesIn = new JLabel();

		lblReaderName = new JLabel("������");
		txtReaderName = new JTextField();
		lblSex = new JLabel("�Ա�");
		cmbSexView = new JComboBox();
		dcmSexView = new DefaultComboBoxModel();

		lblReaderCardType = new JLabel("֤������");
		cmbCardTypeView = new JComboBox();

		dcmCardTypeView = new DefaultComboBoxModel();
		lblIdentifyCard = new JLabel("֤������");
		txtIdentifyCard = new JTextField();
		lblReaderTel = new JLabel("�绰");
		txtReaderTel = new JTextField();
		lblReaderState = new JLabel("�û�״̬");

		cmbReaderStateView = new JComboBox();

		dcmReaderStateView = new DefaultComboBoxModel();

		lblPwd = new JLabel("����");
		txtPwd = new JPasswordField();
		lblPwdAgain = new JLabel("ȷ������");
		txtPwdAgain = new JPasswordField();

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		if(rr==null){
			this.setTitle("������Ϣ���");
		}else{
			this.setTitle("������Ϣ�޸�");
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
		pnlCenter.setBorder(new TitledBorder(null, "������Ϣ",
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
		pnlNorth.setBorder(new TitledBorder(null, "��������",
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

		dcmSexView.addElement("��");
		dcmSexView.addElement("Ů");
		cmbSexView.setModel(dcmSexView);

		dcmCardTypeView.addElement("���֤");
		dcmCardTypeView.addElement("ѧ��֤");
		dcmCardTypeView.addElement("����֤");
		dcmCardTypeView.addElement("����");
		cmbCardTypeView.setModel(dcmCardTypeView);

		dcmReaderStateView.addElement("����");
		dcmReaderStateView.addElement("��ʧ");
		cmbReaderStateView.setModel(dcmReaderStateView);

	}

	public void btnSave_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (txtReaderName.getText().trim().length() == 0
				|| txtIdentifyCard.getText().trim().length() == 0
				|| txtReaderTel.getText().trim().length() == 0
				|| new String(txtPwd.getPassword()).trim().length() == 0
				|| new String(txtPwdAgain.getPassword()).trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "��Ϣ��д����ȫ", "����",
					JOptionPane.ERROR_MESSAGE);
		} else if (!new String(txtPwd.getPassword()).trim().endsWith(
				new String(txtPwdAgain.getPassword()).trim())) {
			JOptionPane.showMessageDialog(null, "�����������벻һ��", "����",
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
			if (cmbReaderStateView.getSelectedItem().equals("����")) {
				r.setReaderState(1);
			} else {
				r.setReaderState(0);
			}
			r.setReaderPwd(new String(txtPwd.getPassword()).trim());
			if(rr==null){
				if (dao.addReader(r)) {
					JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
					rimf.setTableData(dao.getAllReaderViewInfo());
					rimf.setTableStandard();
					this.dispose();
					JournaInfoDAO.writeJournalInfo(u.getUserName(),
							"�����˱��Ϊ" + r.getReaderId() + "�Ķ�����Ϣ",
							JournaInfoDAO.TYPE_RM);
				} else {
					JOptionPane.showMessageDialog(null, "����ʧ��", "����",
							JOptionPane.ERROR_MESSAGE);
				}
			}else{
				r.setReaderId(rr.getReader().getReaderId());
				r.setReaderRegistDate(rr.getReader().getReaderRegistDate());
				if (dao.updateReader(r)) {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
					rimf.setTableData(dao.getAllReaderViewInfo());
					rimf.setTableStandard();
					this.dispose();
					JournaInfoDAO.writeJournalInfo(u.getUserName(),
							"�޸��˱��Ϊ" + r.getReaderId() + "�Ķ�����Ϣ",
							JournaInfoDAO.TYPE_RM);
				} else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��", "����",
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
