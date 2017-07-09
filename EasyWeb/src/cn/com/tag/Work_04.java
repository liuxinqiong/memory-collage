package cn.com.tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Work_04 extends SimpleTagSupport{
	private String src;
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		File f=new File(src);
		BufferedReader br=new BufferedReader(new FileReader(f));
		JspWriter out=this.getJspContext().getOut();
		String data=br.readLine();
		while(data!=null){
			out.println(new String(data.getBytes("iso8859-1"),"gbk"));
			data=br.readLine();
		}	
	}
}
