package shop.kbgagu.www.app.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;

public class QuestionRemoveAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		//체크박스 된 문의 글 삭제하는 알고리즘

		//db에서 삭제 후 문의 페이지 메인인 리스트로 보냄
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/questionlist.jsp");
		forward.setRedirect(true);
		return forward;
	}
}