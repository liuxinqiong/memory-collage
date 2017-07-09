package cn.com.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagTest extends SimpleTagSupport{
	private List<String> list;

	public List<String> getList() {
		return list;
	}


	public void setList(List<String> list) {
		this.list = list;
	}


	@Override
	public void setJspContext(JspContext pc) {
		// TODO Auto-generated method stub
		super.setJspContext(pc);
	}


	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspWriter out=this.getJspContext().getOut();
		JspFragment jspBody=this.getJspBody();
		for(String s:list){
			jspBody.invoke(out);
			out.print(s);
		}
	}
}
