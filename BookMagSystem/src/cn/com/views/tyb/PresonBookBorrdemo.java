package cn.com.views.tyb;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import cn.com.global.Global;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PresonBookBorrdemo extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public PresonBookBorrdemo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize( 350, 280);
		Global.setCenterByWindow(this);
		setResizable(false);
		contentPane = new JPanel();
	//	contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		JButton button_1 = new JButton("个人借阅信息");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PresonBorrinfodemo();
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("宋体", Font.BOLD, 16));
//		button_1.setBackground(new Color(0, 51, 204));
		button_1.setBounds(100, 30, 160, 40);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("图书借阅查询");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BorrowInfodemo();
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("宋体", Font.BOLD, 16));
//		button_2.setBackground(new Color(0, 51, 204));
		button_2.setBounds(100, 100, 160, 40);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("退 出");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("宋体", Font.BOLD, 16));
//		button_3.setBackground(new Color(0, 51, 204));
		button_3.setBounds(100, 170, 160, 40);
		contentPane.add(button_3);
		
		setVisible(true);
	}
}
