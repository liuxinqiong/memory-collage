package cn.com.beans.readerInfo;

public class ReaderInfoViewBean {
	ReaderInfoBean reader;
	ReaderTypeInfoBean readerType;
	public ReaderInfoBean getReader() {
		return reader;
	}
	public void setReader(ReaderInfoBean reaader) {
		this.reader = reaader;
	}
	public ReaderTypeInfoBean getReaderType() {
		return readerType;
	}
	public void setReaderType(ReaderTypeInfoBean readerType) {
		this.readerType = readerType;
	}
	public String toString(){
		return this.reader.toString();
	}
}
