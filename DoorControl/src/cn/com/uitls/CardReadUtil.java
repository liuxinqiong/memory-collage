package cn.com.uitls;

import java.io.IOException;
import java.io.InputStream;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
/**
 * @function ʵ�ִ���ͨ�ŵĹ�����
 * @method openComm(String com)  �򿪴���
 * @method getCardId(SerialPort port)  ��ȡ����
 * @method close(SerialPort port)  �رմ���
 * @author ������
 *
 */
public class CardReadUtil {
	
	public static SerialPort openComm(String com){
		SerialPort serialPort=null;
		try {
			CommPortIdentifier port=CommPortIdentifier.getPortIdentifier(com);
			serialPort=(SerialPort) port.open("ReadComm", 2000);
			return serialPort;
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getCardId(SerialPort port){
		InputStream in=null;
		try {
			in=port.getInputStream();		
			byte[] data = new byte[in.available()];
			in.read(data);
			String cardId=bytesToHexString(data);
			return cardId;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void close(SerialPort port){
		port.close();
	}
	
	/**
	 * ���ֽ�����ת����16�����ַ���
	 * @param bArray
	 * @return String
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2){
				sb.append(0);
			}		
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

}
