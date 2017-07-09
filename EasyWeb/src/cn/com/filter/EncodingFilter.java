package cn.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.com.util.GetRequestWrap;

public class EncodingFilter implements Filter{
	private FilterConfig filterConfig;
	
	public EncodingFilter(){
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
//		System.out.println(this.filterConfig.getInitParameter("filterTest"));
		HttpServletRequest req=(HttpServletRequest)request;
		String method=req.getMethod();
		if(method.equalsIgnoreCase("get")){
			req=new GetRequestWrap(req);
		}else{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		}	
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
//		System.out.println("filterEnter");
		this.filterConfig=filterConfig;
	}

}
