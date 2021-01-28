package shop.kbgagu.www.common;


public class Paser {
	public static String chgToStr(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("'", "&#039;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
	
	public static String chgToHTML(String str) {
		str = str.replaceAll("&amp;","&");
		str = str.replaceAll("&lt;","<");
		str = str.replaceAll( "&gt;",">");
		str = str.replaceAll( "&quot;","\"");
		return str;
	}
	
	public static String chgToSttus(int type) {
		String str = null;
		switch (type) {
		case 1:
			str = "결제중";
			break;
		case 2:
			str = "결제완료";
			break;
		case 3:
			str = "배송준비중";
			break;
		case 4:
			str = "배송중";
			break;
		case 5:
			str = "배송완료";
			break;
		case -1:
			str = "결제취소";
			break;
		case -2:
			str = "환불요청";
			break;
		case -3:
			str = "환불완료";
			break;
		case -4:
			str = "교환신청";
			break;
		case -5:
			str = "교환완료";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}
	public static String chgToPymnt(int type) {
		String str = null;
		switch (type) {
		case 1:
			str = "무통장 입금";
			break;
		case 2:
			str = "계좌이체";
			break;
		case 3:
			str = "카드결제";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}
	
	public static String boolToStr(boolean bool) {
		String str = null;
		if(bool) {
			str="Y";
		}else {
			str="X";
		}
		return str;
	}
	
	public static String reviewToStar(int star) {
		String str = null;
		if (star > 5) {
			star = 5;
		} else if (star < 1) {
			star = 1;
		}
		switch (star) {
		case 1:
			str = "&#xf005";
			break;
		case 2:
			str = "&#xf005 &#xf005";
			break;
		case 3:
			str = "&#xf005 &#xf005 &#xf005";
			break;
		case 4:
			str = "&#xf005 &#xf005 &#xf005 &#xf005";
			break;
		case 5:
			str = "&#xf005 &#xf005 &#xf005 &#xf005 &#xf005";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}
}
