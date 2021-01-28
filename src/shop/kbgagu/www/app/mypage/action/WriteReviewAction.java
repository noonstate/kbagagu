package shop.kbgagu.www.app.mypage.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;

public class WriteReviewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 확인 알고리즘

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);

		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		String item_sq = request.getParameter("sq");
		request.setAttribute("sq", item_sq);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/writereview.jsp");
		return forward;
	}
}
