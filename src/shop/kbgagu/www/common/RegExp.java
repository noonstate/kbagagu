package shop.kbgagu.www.common;

import java.util.regex.Pattern;

public class RegExp {
	public static final int REGEXP_ID = 1;
	public static final int REGEXP_PWD = 2;
	public static final int REGEXP_NAME = 3;
	public static final int REGEXP_NUMBER = 4;
	public static final int REGEXP_TELNUMBER = 5;
	
	public static boolean isValidExp(String data,int type) {
		boolean isValid = false;
		if(isEmpty(data)) {
			return isValid;
		}
		switch (type) {
		case REGEXP_ID :
			isValid = Pattern.matches("^[0-9a-zA-Z][0-9a-zA-Z\\_\\-\\.\\+]"
					+ "+[0-9a-zA-Z]@[0-9a-zA-Z][0-9a-zA-Z\\_\\-]*"
					+ "[0-9a-zA-Z](\\.[a-zA-Z]{2,6}){1,2}$", data);
			break; 
		case REGEXP_PWD :
			isValid = Pattern.matches("^[a-z0-9A-Z!@#$%^&*]{4,20}$", data);
			break;
		case REGEXP_NAME :
			isValid = Pattern.matches("^[가-힣]{2,8}$", data);
			break;
		case REGEXP_NUMBER :
			isValid = Pattern.matches("^[0-9]*$", data);
			break;
		case REGEXP_TELNUMBER :  // [0-1]{2}[016789]+[0-9]{7,8}
			isValid = Pattern.matches("[0-1]{2}[016789]+[0-9]{7,8}", data);
		}
		
		return isValid;
	}
	
	public static boolean isEmpty(String data) {
		if(data == null || data.equals("")) {
			return true;
		}else {
			return false;
		}
	}
	
}
