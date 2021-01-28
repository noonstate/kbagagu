package shop.kbgagu.www.common;

public class ImegeProcess {
	
	// 이미지를 제한하기 위한 메소드
	public static boolean isImege(String fileName) {
		boolean isImege = false;
		fileName = fileName.toLowerCase();
		if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
			isImege = true;
		}
		
		return isImege;
	}
}
