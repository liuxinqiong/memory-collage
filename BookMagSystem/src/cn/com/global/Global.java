package cn.com.global;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Window;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

public abstract class Global {
	public final static void setCenterByWindow(Window f) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension ds = tk.getScreenSize();
		Dimension fs = f.getSize();
		if(fs.width > ds.width) {
			fs.width = ds.width;
		}
		if(fs.height > ds.height) {
			fs.height = ds.height;
		}
		f.setLocation((ds.width-fs.width)/2, (ds.height - fs.height)/2);
	}
	
	public final static String getDate(int days){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String date = sdf.format(cal.getTime());
		return date;
	}
	
	public static long getTimeBetweenNow(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate=new Date();
		long timeBetween=0l;
		try {
			Date pastDate=sdf.parse(date);
			timeBetween=(nowDate.getTime()-pastDate.getTime())/(1000*24*60*60);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -timeBetween;
	}
	
	/**
	 * 
	 * @param btn	��ť����
	 * @param colorOfBack	����ɫ
	 * @param colorOfFore	������ɫ
	 * @param size	�����С
	 * @param border	��ť�߿�
	 */
	public static void jbuttonInit(JButton btn,Color colorOfBack,Color colorOfFore,int size,Border border) {
		// TODO Auto-generated method stub
		btn.setBackground(colorOfBack);
		btn.setForeground(colorOfFore);
		btn.setFont(new Font("΢���ź�", Font.PLAIN,size));
		btn.setBorder(border);
	}
	

	/**
	 * 
	 * @param lbl	��ǩ����
	 * @param colorOfBack	����ɫ
	 * @param colorOfFore	����ɫ
	 * @param x	x����
	 * @param y	y����
	 * @param width	���
	 * @param height	�߶�
	 * @param size	�����С
	 * @param isOpaque	�Ƿ�͸��
	 */
	public static void jlableInit(JLabel lbl,Color colorOfBack,Color colorOfFore,int x,int y,int width,int height,int size,Boolean isOpaque) {
		// TODO Auto-generated method stub
		lbl.setBackground(colorOfBack);
		lbl.setForeground(colorOfFore);
		lbl.setFont(new Font("΢���ź�", Font.PLAIN,size));
		lbl.setBounds(x, y, width, height);
		lbl.setOpaque(isOpaque);
	}
	/**
	 * 
	 * @param lbl	��ǩ����
	 * @param colorOfFore	����ɫ
	 * @param size	�����С
	 */
	public static void jlableInit(JLabel lbl,Color colorOfFore,int size) {
		// TODO Auto-generated method stub
		lbl.setForeground(colorOfFore);
		lbl.setFont(new Font("΢���ź�", Font.PLAIN,size));
	}

	public static String getDate(String format, int days) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		String newDate=null;
		try {
			date = sdf.parse(format);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, days);
			newDate = sdf.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return newDate;
	}
}
