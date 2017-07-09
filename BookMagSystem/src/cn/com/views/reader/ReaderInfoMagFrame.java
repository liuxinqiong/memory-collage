package cn.com.views.reader;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.listeners.reader.ReaderInfoMagFrame_btnAdd_ActionListener;
import cn.com.listeners.reader.ReaderInfoMagFrame_btnDelete_ActionListener;
import cn.com.listeners.reader.ReaderInfoMagFrame_btnModify_ActionListener;
import cn.com.listeners.reader.ReaderInfoMagFrame_btnSearch_ActionListener;

public class ReaderInfoMagFrame extends JFrame{
	JPanel pnlMain;
	JTable tabView;
	DefaultTableModel dtmView;
	JScrollPane snpView;
	JLabel lblSearchWay;
	JLabel lblSearchInfo;
	JTextField txtSearchInfo;
	JComboBox cmbSearchWay;
	DefaultComboBoxModel dcmSearchWay;
	JButton btnAdd;
	JButton btnModify;
	JButton btnDelete;
	JButton btnSearch;
	ReaderInfoDAOInf dao;
	UserInfoBean u;
	ReaderInfoViewBean altR;
	public ReaderInfoMagFrame(UserInfoBean u) {
		this.u=u;
		dao = new ReaderInfoDAOImp();
		pnlMain = new JPanel(null);
		tabView = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		snpView = new JScrollPane();
		btnAdd = new JButton("����");
		btnModify = new JButton("�޸�");
		btnDelete = new JButton("ɾ��");
		btnSearch = new JButton("��ѯ");
		lblSearchWay = new JLabel("��ѡ���ѯ��ʽ��");
		lblSearchInfo = new JLabel("�������ѯ��Ϣ��");
		txtSearchInfo = new JTextField();
		cmbSearchWay = new JComboBox();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("������Ϣά��");
		Toolkit tool = Toolkit.getDefaultToolkit(); // ���Ĭ�ϵĹ�����
		Dimension screenSize = tool.getScreenSize(); // �����Ļ�Ĵ�С
		this.setSize(600, 480); // ���ô����С
		this.setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		lblSearchWay.setBounds(20, 10, 150, 25);
		pnlMain.add(lblSearchWay);
		cmbSearchWay.setBounds(120, 10, 100, 25);
		setComboBoxData();
		pnlMain.add(cmbSearchWay);
		lblSearchInfo.setBounds(250, 10, 150, 25);
		pnlMain.add(lblSearchInfo);
		txtSearchInfo.setBounds(350, 10, 150, 25);
		pnlMain.add(txtSearchInfo);
		btnSearch.setBounds(510, 10, 75, 25);
		pnlMain.add(btnSearch);
		setTableData(dao.getAllReaderViewInfo());
		setTableStandard();
		snpView.getViewport().add(tabView);
		snpView.setBounds(0, 50, 600, 340);
		pnlMain.add(snpView);
		btnAdd.setBounds(40, 410, 75, 25);
		pnlMain.add(btnAdd);
		btnModify.setBounds(155, 410, 75, 25);
		pnlMain.add(btnModify);
		btnDelete.setBounds(365, 410, 75, 25);
		pnlMain.add(btnDelete);

		btnSearch
				.addActionListener(new ReaderInfoMagFrame_btnSearch_ActionListener(
						this));
		btnDelete
				.addActionListener(new ReaderInfoMagFrame_btnDelete_ActionListener(
						this));
		btnAdd.addActionListener(new ReaderInfoMagFrame_btnAdd_ActionListener(
				this));
		btnModify
				.addActionListener(new ReaderInfoMagFrame_btnModify_ActionListener(
						this));

		this.add(pnlMain);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void setComboBoxData() {
		// TODO Auto-generated method stub
		dcmSearchWay = new DefaultComboBoxModel();
		dcmSearchWay.addElement("���߱��");
		dcmSearchWay.addElement("��������");
		cmbSearchWay.setModel(dcmSearchWay);
	}

	public void setTableStandard() {
		// TODO Auto-generated method stub
		tabView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabView.getTableHeader().setReorderingAllowed(false); // ���������ƶ�
		tabView.getTableHeader().setResizingAllowed(false); // �����������
		tabView.getColumn("֤������").setMinWidth(150);
		tabView.getColumn("�绰").setMinWidth(100);
	}

	public void setTableData(List<ReaderInfoViewBean> allReadersInfo) {
		// TODO Auto-generated method stub
		Vector<String> title = new Vector<String>();
		title.add("���߱��");
		title.add("����");
		title.add("�Ա�");
		title.add("��������");
		title.add("ע������");
		title.add("֤������");
		title.add("֤������");
		title.add("�绰");
		title.add("��������");	
		title.add("Ѻ��");
		title.add("�ɽ�����");
		title.add("��ʧ״̬");

		Vector<Vector> data = new Vector<Vector>();
		Vector row = null;
		for (ReaderInfoViewBean rr : allReadersInfo) {
			row = new Vector();
			row.add(rr.getReader().getReaderId());
			row.add(rr);
			row.add(rr.getReader().getReaderSex());
			row.add(rr.getReaderType().getReaderTypeName());
			row.add(rr.getReader().getReaderRegistDate());
			row.add(rr.getReader().getReaderCardType());
			row.add(rr.getReader().getReaderIdentifyCard());
			row.add(rr.getReader().getReaderTel());
			row.add(rr.getReaderType().getMaxNum());
			row.add(rr.getReaderType().getKeepMoney());
			row.add(rr.getReaderType().getDays());
			if(rr.getReader().getReaderState()==1){
				row.add("����");
			}else{
				row.add("��ʧ");
			}
			
			data.add(row);
		}
		dtmView = new DefaultTableModel(data, title);
		this.tabView.setModel(dtmView);
	}

	public void btnSearch_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (dcmSearchWay.getSelectedItem().equals("��������")) {
			setTableData(dao.getPossibleReadersInfoByName(txtSearchInfo
					.getText().trim()));
			setTableStandard();
		} else {
			setTableData(dao
					.getPossibleReadersInfoById(txtSearchInfo.getText().trim()));
			setTableStandard();
		}
	}

	public void btnDelete_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ReaderInfoViewBean r = null;
		if(tabView.getSelectedRow()==-1){
			JOptionPane.showMessageDialog(null, "û��ѡ��Ҫɾ���Ķ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else{
			r = (ReaderInfoViewBean) tabView.getValueAt(tabView.getSelectedRow(), 1);
			if (dao.delReader(r.getReader())) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				JournaInfoDAO.writeJournalInfo(u.getUserName(), "ɾ���˱��Ϊ"+r.getReader().getReaderId()+"�Ķ�����Ϣ", JournaInfoDAO.TYPE_RM);
				setTableData(dao.getAllReaderViewInfo());
				setTableStandard();
			} else {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ����ܲ�����", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void btnAdd_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ReaderAddFrame(altR,this,u);
	}

	public void btnModify_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ReaderInfoViewBean  r = null;
		if(tabView.getSelectedRow()==-1){
			JOptionPane.showMessageDialog(null, "û��ѡ��Ҫ�޸ĵĶ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}else{
			r = (ReaderInfoViewBean) tabView.getValueAt(tabView.getSelectedRow(), 1);
			new ReaderAddFrame(r, this, u);
		}
	}
	
}
