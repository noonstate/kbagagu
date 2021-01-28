package shop.kbgagu.www.app.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;

public class MypageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 주문내역 불러오는 알고리즘

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/views/mypage/mypage.jsp");
		return forward;
	}
}
