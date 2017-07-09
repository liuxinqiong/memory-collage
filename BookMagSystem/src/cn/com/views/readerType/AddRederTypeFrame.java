package cn.com.views.readerType;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.daos.journaInfo.JournaInfoDAO;
import cn.com.daos.readerInfo.ReaderInfoDAOImp;
import cn.com.daos.readerInfo.ReaderInfoDAOInf;
import cn.com.global.Check;
import cn.com.global.Global;
import cn.com.listeners.readerType.AddRederTypeFrame_btncanel_ActionListener;
import cn.com.listeners.readerType.AddRederTypeFrame_btnsave_ActionListener;

public class AddRederTypeFrame extends JDialog{
	JPanel pnlMain;
	JPanel pnlInfo;
	JLabel lbl1;
	JLabel lbl2;
	JLabel lbl3;
	JLabel lbl4;
	JLabel lbl5;
	JLabel lbl6;
	JLabel lbl7;
	JTextField txtname;
	JTextField txtcount;
	JTextField txtdate;
	JTextField txtmtp;
	JTextField txtstcharge;
	JTextField txtoutcharge;
	JTextField txtqty;
	JButton btnsave;
	JButton btncanel;
	SetReaderTypeFrame srtf;
	ReaderInfoDAOInf dao;
	UserInfoBean user;
	
	public  AddRederTypeFrame (SetReaderTypeFrame srtf,UserInfoBean user){
		this.user=user;
		pnlMain = new JPanel(null);
		pnlInfo = new JPanel(new GridLayout(7,2,10,15));
		lbl1 = new JLabel("�������ƣ�");
		lbl2 = new JLabel("Ѻ���Ԫ����");	
		lbl3 = new JLabel("��Чʱ�䣨�죩��");
		lbl4 = new JLabel("��ʧ��������");
		lbl5 = new JLabel("�շѱ�׼��Ԫ/�죩��");
		lbl6 = new JLabel("�����շѱ�׼��Ԫ/�죩��");
		lbl7 = new JLabel("�ɽ�������");
		txtname = new JTextField();
		txtcount = new JTextField();
		txtdate = new JTextField();
		txtmtp = new JTextField();
		txtstcharge = new JTextField();
		txtoutcharge = new JTextField();
		txtqty = new JTextField();
		btnsave = new JButton("����");
		btncanel = new JButton("ȡ��");
		this.srtf = srtf;
		dao = new ReaderInfoDAOImp();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(350, 300);
		this.setResizable(false);
		lbl1.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lbl2.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lbl3.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lbl4.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lbl5.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lbl6.setFont(new Font("΢���ź�",Font.PLAIN,14));
		lbl7.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btncanel.setFont(new Font("΢���ź�",Font.PLAIN,14));
		btnsave.setFont(new Font("΢���ź�",Font.PLAIN,14));
		
		pnlInfo.setBounds(10,5,320,220);
//		pnlInfo.setBackground(Color.cyan);
//		pnlMain.setBackground(Color.green);
//		btnsave.setBackground(Color.pink);
//		btncanel.setBackground(Color.yellow);
		btnsave.setBounds(120, 230, 70, 25);
		btncanel.setBounds(210, 230, 70, 25);
		
		btncanel.addActionListener(new AddRederTypeFrame_btncanel_ActionListener(this));
		btnsave.addActionListener(new AddRederTypeFrame_btnsave_ActionListener(this));
		
//		pnlInfo.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.cyan));
		
		pnlInfo.add(lbl1);
		pnlInfo.add(txtname);
		pnlInfo.add(lbl2);
		pnlInfo.add(txtcount);
		pnlInfo.add(lbl3);
		pnlInfo.add(txtdate);
		pnlInfo.add(lbl4);
		pnlInfo.add(txtmtp);
		pnlInfo.add(lbl5);
		pnlInfo.add(txtstcharge);
		pnlInfo.add(lbl6);
		pnlInfo.add(txtoutcharge);
		pnlInfo.add(lbl7);
		pnlInfo.add(txtqty);
		
		pnlMain.add(pnlInfo);
		pnlMain.add(btnsave);
		pnlMain.add(btncanel);
		this.add(pnlMain);
		this.setTitle("�����û�");
		Global.setCenterByWindow(this);
		this.setVisible(true);
	}

	public void btncanel_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	public void btnsave_ActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ReaderTypeInfoBean r = new ReaderTypeInfoBean();
		String name = txtname.getText().trim();
		if (!name.equals("")) {	
			r.setReaderTypeName(name);
			if (dao.checkReaderTypeByName(r)) {
				JOptionPane.showMessageDialog(null, "�������Ѵ���!", "����",
						JOptionPane.ERROR_MESSAGE);
				
			} else {
			
				String count = txtcount.getText();
				if (!count.equals("")) {
					if (Check.isDouble(count)) {
						if (Double.parseDouble(count) < 0) {
							JOptionPane.showMessageDialog(null, "�����ڵ���0!",
									"����", JOptionPane.ERROR_MESSAGE);
							
						} else {
							r.setKeepMoney(Double.parseDouble(count));
							String date = txtdate.getText();
							if (!date.equals("")) {
								if (Check.isInt(date)) {
									if (Integer.parseInt(date) < 0) {
										JOptionPane.showMessageDialog(null,
												"��Ч���ڴ��ڵ���0!", "����",
												JOptionPane.ERROR_MESSAGE);
										
									} else {
										r.setDays(Integer.parseInt(date));
										String mtp = txtmtp.getText();
										if (!mtp.equals("")) {
											if (Check.isInt(mtp)) {
												if (Integer.parseInt(mtp) < 0) {
													JOptionPane
															.showMessageDialog(
																	null,
																	"��������ڵ���0!",
																	"����",
																	JOptionPane.ERROR_MESSAGE);
													
												} else {
													r.setLossTimes(Integer
															.parseInt(mtp));
													String st = txtstcharge
															.getText();
													if (!st.equals("")) {
														if (Check.isDouble(st)) {
															if (Double
																	.parseDouble(st) < 0) {
																JOptionPane
																		.showMessageDialog(
																				null,
																				"�շѱ�׼���ڵ���0!",
																				"����",
																				JOptionPane.ERROR_MESSAGE);
																
															} else {
																r.setNormalRent(Double
																		.parseDouble(st));
																String out = txtoutcharge
																		.getText();
																if (!out.equals("")) {
																	if (Check
																			.isDouble(out)) {
																		if (Double
																				.parseDouble(out) < 0) {
																			JOptionPane
																					.showMessageDialog(
																							null,
																							"�����շѱ�׼���ڵ���0!",
																							"����",
																							JOptionPane.ERROR_MESSAGE);
																			
																		} else {
																			r.setExtendedRent(Double
																					.parseDouble(out));
																			String qty = txtqty
																					.getText();
																			if (!qty.equals("")) {
																				if (Check
																						.isInt(qty)) {
																					if (Integer
																							.parseInt(qty) < 0) {
																						JOptionPane
																								.showMessageDialog(
																										null,
																										"�ɽ豾�����ڵ���0!",
																										"����",
																										JOptionPane.ERROR_MESSAGE);
																						
																					} else if (Integer
																							.parseInt(qty) > 999) {
																						JOptionPane
																								.showMessageDialog(
																										null,
																										"�ɽ豾�����ܳ���999!",
																										"����",
																										JOptionPane.ERROR_MESSAGE);
																						
																					} else {
																						r.setMaxNum(Integer
																								.parseInt(qty));
																						r.setIsDel(0);
																						if (dao.addReaderType(r)) {
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"���ӳɹ�",
																											"��ʾ",
																											JOptionPane.INFORMATION_MESSAGE);
																							JournaInfoDAO.writeJournalInfo(user.getUserName(), "������"+r.getReaderTypeName()+"��������", JournaInfoDAO.TYPE_RM);
																							srtf.setTabViewData(dao.getAllReaderTypeInfo());
																							srtf.setTabViewStandard();
																							this.dispose();
																						} else {
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"����ʧ��!",
																											"����",
																											JOptionPane.ERROR_MESSAGE);
																							
																						}
																					}
																				} else {
																					JOptionPane
																							.showMessageDialog(
																									null,
																									"�ɽ豾��Ϊ���֣������������ַ���",
																									"����",
																									JOptionPane.ERROR_MESSAGE);
																					
																				}
																			} else {
																				JOptionPane
																						.showMessageDialog(
																								null,
																								"�ɽ豾������Ϊ��",
																								"����",
																								JOptionPane.ERROR_MESSAGE);
																				
																			}

																		}
																	} else {
																		JOptionPane
																				.showMessageDialog(
																						null,
																						"�����շѱ�׼Ϊ���֣������������ַ���",
																						"����",
																						JOptionPane.ERROR_MESSAGE);
																		
																	}
																} else {
																	JOptionPane
																			.showMessageDialog(
																					null,
																					"�����շѱ�׼����Ϊ��",
																					"����",
																					JOptionPane.ERROR_MESSAGE);
																	
																}
															}
														} else {
															JOptionPane
																	.showMessageDialog(
																			null,
																			"�շѱ�׼Ϊ���֣������������ַ���",
																			"����",
																			JOptionPane.ERROR_MESSAGE);
															
														}
													} else {
														JOptionPane
																.showMessageDialog(
																		null,
																		"�շѱ�׼����Ϊ��",
																		"����",
																		JOptionPane.ERROR_MESSAGE);
														
													}
												}
											} else {
												JOptionPane
														.showMessageDialog(
																null,
																"����������Ϊ��",
																"����",
																JOptionPane.ERROR_MESSAGE);
												
											}
										} else {
											JOptionPane.showMessageDialog(null,
													"�����Ϊ���֣������������ַ���", "����",
													JOptionPane.ERROR_MESSAGE);
											
										}
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"��Ч����Ϊ���֣������������ַ���", "����",
											JOptionPane.ERROR_MESSAGE);
									
								}
							} else {
								JOptionPane.showMessageDialog(null, "�ɽ���������Ϊ��",
										"����", JOptionPane.ERROR_MESSAGE);
								
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ѻ����Ϊ���֣������������ַ���",
								"����", JOptionPane.ERROR_MESSAGE);
						
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ѻ�����Ϊ��", "����",
							JOptionPane.ERROR_MESSAGE);
					
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "�������Ͳ���Ϊ��", "����",
					JOptionPane.ERROR_MESSAGE);
			
		}
	}

}
