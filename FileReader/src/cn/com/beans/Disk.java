package cn.com.beans;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Disk {	
	String fileUrl;
	String imageUrl;
	public Disk(String fileUrl,String imageUrl){
		this.fileUrl=fileUrl;
		this.imageUrl=imageUrl;
	}
	
	
	
	public String getFileUrl() {
		return fileUrl;
	}



	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public JLabel getDisk(){
		ImageIcon image=new ImageIcon(imageUrl);
		fileUrl="±æµÿ¥≈≈Ã("+fileUrl+")";
		JLabel lblDisk=new JLabel(fileUrl,image,SwingConstants.LEFT);
		return lblDisk;
	}
}
