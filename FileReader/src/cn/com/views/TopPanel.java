package cn.com.views;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import cn.com.listeners.MainFrame_btnBack_ActionListener;
import cn.com.listeners.MainFrame_btnForward_ActionListener;

public class TopPanel extends JPanel{
	JComboBox cmbView;
	DefaultComboBoxModel dcmView;
	JButton btnBack;
	JButton btnForward;
	MainFrame mf;
	
	public TopPanel(MainFrame mf){
		this.mf=mf;
		this.setLayout(null);
		cmbView=new JComboBox();
		dcmView=new DefaultComboBoxModel();
		btnBack=new JButton(new ImageIcon("images/back.png"));
		btnForward=new JButton(new ImageIcon("images/forward.png"));
		init();
	}

	private void init() {
		btnBack.setBounds(5, 5, 24, 24);
		this.add(btnBack);
		cmbView.setBounds(30, 5, 720, 24);
		cmbView.setEditable(true);
		this.add(cmbView);
		btnForward.setBounds(760, 5, 24, 24);
		this.add(btnForward);
		this.setBounds(0, 0, 800, 30);	
		
		btnBack.addActionListener(new MainFrame_btnBack_ActionListener(this.mf));
		btnForward.addActionListener(new MainFrame_btnForward_ActionListener(this.mf));
	}
}
