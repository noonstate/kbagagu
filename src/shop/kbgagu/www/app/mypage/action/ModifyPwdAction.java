package shop.kbgagu.www.app.mypage.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;

public class ModifyPwdAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 상태 확인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 되어있지 않습니다.');location.href='/';</script>");
			out.close();
			return null;
			// 비로그인상태. -> 홈으로
		}
		

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/views/mypage/modifypwd.jsp");
		return forward;
	}
}
