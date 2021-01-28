package shop.kbgagu.www.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;


public class LogoutProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
				
		// 로그인 확인
		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 하십시오');history.back();</script>");
			out.close();
			return null;
		}
		
		// 세션 비우기
		lm.removeSession(sessionId);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/");
		return forward;
	}
}
