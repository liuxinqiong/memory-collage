package cn.com.global;

public abstract class Check {	
	public final static boolean isInt(String s) {
		boolean bool = false;
		try {
			Integer.valueOf(s);
			bool = true;
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}
	
	public final static boolean checkMaxNum(int maxNum){
		boolean bool=false;
		if(maxNum>0&&maxNum<1000){
			bool=true;
		}else{
			bool=false;
		}
		return bool;
	}
}
