package cn.com.global;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Window;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	
	public static List<Date> getDates(String startTime, String endTime) {
        List<Date> result = new ArrayList<Date>();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
        Date eDate = null;
		try {
			sDate = sdf.parse(startTime);
			eDate = sdf.parse(endTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//因为date类没有add()方法，故转成Calendar进行处理
		Calendar sc=Calendar.getInstance();
		sc.setTime(sDate);
		Calendar ec=Calendar.getInstance();
		ec.setTime(eDate);
        while (!sc.after(ec)) {
        	result.add(sc.getTime());
        	sc.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }
}
