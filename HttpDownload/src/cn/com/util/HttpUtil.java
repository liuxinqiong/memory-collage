package cn.com.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpUtil {
	/**
	 * �ж�·���Ƿ��ǿ������ļ�·��
	 * @param urlStr
	 * @return boolean
	 */
	public static boolean isFilePath(String urlStr){
		boolean bool=false;
		InputStream is=null;
		try {
			URL url=new URL(urlStr);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			//�õ�������
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
	 * �õ��ļ��Ĵ�С
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
	 * �õ��ļ�������
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
	 * ʵ������
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
			//�õ�������
			
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
