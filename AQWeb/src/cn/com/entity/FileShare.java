package cn.com.entity;

/**
 * 文件对象
 * 
 * @author TYB
 * 
 */
public class FileShare {
	private int fileNo;
	private String fileName;
	private int professionNo;
	private String filePath;
	private int teacherNo;
	private int downloadCount;
	private int isDel;
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getProfessionNo() {
		return professionNo;
	}
	public void setProfessionNo(int professionNo) {
		this.professionNo = professionNo;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getTeacherNo() {
		return teacherNo;
	}
	public void setTeacherNo(int teacherNo) {
		this.teacherNo = teacherNo;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
}
