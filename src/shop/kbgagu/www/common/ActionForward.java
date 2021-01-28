package shop.kbgagu.www.common;

public class ActionForward {
	// 서블릿에서 요청 처리 후 포워딩 될 뷰 페이지 url받음
	private String path;
	// 포워딩 방식 true 일때 redirect(주소 바뀜), false 는 dispatch(주소 안바뀜,setattr로 데이터 보냄)
	private boolean redirect;

	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	public ActionForward() {
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

}
