package shop.kbgagu.www.app.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;

public class ReviewListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 현재 로그인한 계정의 리뷰들 불러오는 알고리즘

		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/reviewlist.jsp");
		forward.setRedirect(true);
		return forward;
	}
}
