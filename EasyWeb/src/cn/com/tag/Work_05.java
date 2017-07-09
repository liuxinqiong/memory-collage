package cn.com.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Work_05 extends SimpleTagSupport{
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspFragment jspBody=this.getJspBody();

		JspWriter out=this.getJspContext().getOut();
		StringWriter sw=new StringWriter();	
		jspBody.invoke(sw);//理解一下这个
		String content=sw.getBuffer().toString();
		out.print(content.toUpperCase());
	}
}
