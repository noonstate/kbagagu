package shop.kbgagu.www.app.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;

public class LeaveAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//로그인 여부 체크
		LoginManager lm = LoginManager.getInstance();
		HttpSession session = request.getSession();
		String sessionId = lm.getMemberId(session);
		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		//로그인 여부 검증됬으니 밑 경로로 이동
		forward.setPath("/views/mypage/leaveform.jsp");
		forward.setRedirect(true);
		return forward;
	}
}
