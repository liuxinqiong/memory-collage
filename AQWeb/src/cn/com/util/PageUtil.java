package cn.com.util;

import java.text.MessageFormat;
import java.util.List;

public class PageUtil<T> {
	private List<T> infos;
	private int pageSize;
	private int page;
	private int prePage;
	private int nextPage;
	private int totalPages;
	private int totalRecords;
	private String url;
	public PageUtil(){
		
	}
	
	public PageUtil(List<T> infos, int pageSize, int page, int totalRecords,String url) {
		this.infos = infos;
		this.pageSize = pageSize;
		this.page = page;
		this.totalRecords = totalRecords;
		this.url=url;
		init();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<T> getInfos() {
		return infos;
	}

	public void setInfos(List<T> infos) {
		this.infos = infos;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public void init(){
		this.totalPages =totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
		this.nextPage=page;
		this.prePage=page;	
		if(page<totalPages){
			nextPage=page+1;
		}
		if(page>1){
			prePage=prePage-1;
		}
	}
	
	public String toString(){
		String pattern="<a href={0}?page={1}>上一页</a> <a href={0}?page={2}>下一页</a>";
		for(int i=1;i<=this.totalPages;i++){
			pattern+="<a href={0}?page="+i+">"+i+"</a>";
		}
		String uri=MessageFormat.format(pattern, new Object[]{url,prePage,nextPage});
		return uri;
		
	}
}
