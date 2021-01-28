package shop.kbgagu.www.app.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.member.service.MemberService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;

public class LogoutProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		//로그아웃 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionSq = lm.getMemberId(session);
		
		if (sessionSq == null) { 
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		} else {
			lm.removeSession(sessionSq);
		}
		
		// 로그아웃 히스토리 저장
		MemberService svc = new MemberService();
		if(!svc.setLogoutHistory(sessionSq)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그저장 실패');location.href='/member/loginform';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
