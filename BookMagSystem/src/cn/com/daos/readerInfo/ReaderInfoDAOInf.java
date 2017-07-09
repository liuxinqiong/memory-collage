package cn.com.daos.readerInfo;

import java.util.List;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.readerInfo.ReaderInfoViewBean;

public interface ReaderInfoDAOInf {
	boolean addReader(ReaderInfoBean reader);
	boolean updateReader(ReaderInfoBean reader);
	boolean delReader(ReaderInfoBean reader);
	List<ReaderInfoBean> getAllReaderInfo();
	
	boolean addReaderType(ReaderTypeInfoBean readerType);
	boolean updateReaderType(ReaderTypeInfoBean readerType);
	boolean delReaderType(ReaderTypeInfoBean readerType);
	List<ReaderTypeInfoBean> getAllReaderTypeInfo();
	
	List<ReaderInfoViewBean> getAllReaderViewInfo();
	ReaderInfoViewBean getReaderViewInfoById(ReaderInfoBean r);
	
	ReaderInfoBean getReaderInfoByRId(ReaderInfoBean reader);
	List<ReaderInfoBean> getCanUseReaderInfo();
	boolean checkReaderInfoByReaderId(ReaderInfoBean reader);
	
	ReaderTypeInfoBean getReaderTypeByName(ReaderTypeInfoBean readerType);
	boolean checkReaderTypeByName(ReaderTypeInfoBean readerType);
	List<ReaderInfoViewBean> getPossibleReadersInfoByName(String trim);
	List<ReaderInfoViewBean> getPossibleReadersInfoById(String trim);
}
