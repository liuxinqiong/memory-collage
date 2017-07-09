package cn.com.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpUtil {
	/**
	 * 判断路径是否是可下载文件路径
	 * @param urlStr
	 * @return boolean
	 */
	public static boolean isFilePath(String urlStr){
		boolean bool=false;
		InputStream is=null;
		try {
			URL url=new URL(urlStr);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			//得到网络流
	        is=connection.getInputStream(); 
	        bool=true;
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}
	
	/**
	 * 得到文件的大小
	 * @param urlStr
	 * @return boolean
	 */
	public static String getFileSize(String urlStr){
		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			int responseCode=connection.getResponseCode();
			if(responseCode>=400){
				System.out.println(responseCode);
				return "response error";
			}
			String length=connection.getHeaderField("Content-Length");
			return length;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	/**
	 * 得到文件的类型
	 * @param urlStr
	 * @return boolean
	 */
	public static String getFileType(String urlStr){
		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			int responseCode=connection.getResponseCode();
			if(responseCode>=400){
				System.out.println(responseCode);
				return "response error";
			}
			String length=connection.getHeaderField("Content-Type");
			return length;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	/**
	 * 实现下载
	 * @param urlStr
	 * @param desPath
	 * @return boolean
	 */
	public static boolean downFile(String urlStr,String desPath){
		boolean bool=false;
		InputStream is=null;
		FileOutputStream fos=null;
		try {
			URL url=new URL(urlStr);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			//得到网络流
			
	        is=connection.getInputStream(); 
	        fos = new FileOutputStream(desPath);  
	        byte[] data=new byte[1024]; 
	        while(is.read(data)!=-1){
	        	fos.write(data);
	        }
	        bool=true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {		
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}
}
