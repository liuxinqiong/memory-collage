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
	 * @param btn	按钮对象
	 * @param colorOfBack	背景色
	 * @param colorOfFore	字体颜色
	 * @param size	字体大小
	 * @param border	按钮边框
	 */
	public static void jbuttonInit(JButton btn,Color colorOfBack,Color colorOfFore,int size,Border border) {
		// TODO Auto-generated method stub
		btn.setBackground(colorOfBack);
		btn.setForeground(colorOfFore);
		btn.setFont(new Font("微软雅黑", Font.PLAIN,size));
		btn.setBorder(border);
	}
	

	/**
	 * 
	 * @param lbl	标签对象
	 * @param colorOfBack	背景色
	 * @param colorOfFore	字体色
	 * @param x	x坐标
	 * @param y	y坐标
	 * @param width	宽度
	 * @param height	高度
	 * @param size	字体大小
	 * @param isOpaque	是否透明
	 */
	public static void jlableInit(JLabel lbl,Color colorOfBack,Color colorOfFore,int x,int y,int width,int height,int size,Boolean isOpaque) {
		// TODO Auto-generated method stub
		lbl.setBackground(colorOfBack);
		lbl.setForeground(colorOfFore);
		lbl.setFont(new Font("微软雅黑", Font.PLAIN,size));
		lbl.setBounds(x, y, width, height);
		lbl.setOpaque(isOpaque);
	}
	/**
	 * 
	 * @param lbl	标签对象
	 * @param colorOfFore	字体色
	 * @param size	字体大小
	 */
	public static void jlableInit(JLabel lbl,Color colorOfFore,int size) {
		// TODO Auto-generated method stub
		lbl.setForeground(colorOfFore);
		lbl.setFont(new Font("微软雅黑", Font.PLAIN,size));
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
