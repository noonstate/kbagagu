package shop.kbgagu.www.app.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;

public class LoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 상태 확인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		if (id != null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인된 상태입니다.');location.href='/';</script>");
			out.close();
			return null;
			// 로그인된상태입니다. -> 홈으로
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/loginform.jsp");
		forward.setRedirect(true);
		return forward;
	}
}
