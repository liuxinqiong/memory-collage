package cn.com.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GetRequestWrap extends HttpServletRequestWrapper{

	public GetRequestWrap(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String value=super.getParameter(name);
		try {
			return value!=null?new String(value.getBytes("iso-8859-1"),"gbk"):null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
