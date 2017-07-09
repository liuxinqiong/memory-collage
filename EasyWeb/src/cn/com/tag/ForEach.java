package cn.com.tag;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForEach extends SimpleTagSupport {
	private Collection<Object> collection;
	private Object items;
	private String var;

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
		if (items instanceof Collection) {
			collection = (Collection<Object>) items;
		}

		if (items instanceof Map) {
			Map map = (Map) items;
			collection = map.entrySet();
		}

		if (items.getClass().isArray()) {// 这个是最重要的，反射判断
			this.collection = new ArrayList();
			int length = Array.getLength(items);
			for (int i = 0; i < length; i++) {
				Object value = Array.get(items, i);
				collection.add(value);
			}
		}
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		Iterator<Object> it = this.collection.iterator(); 
		PageContext context = (PageContext) this.getJspContext();
		JspFragment jspBody = this.getJspBody();
		while(it.hasNext()){
			Object value=it.next();
			context.setAttribute(var, value);
			jspBody.invoke(null);
		}
	}
}
