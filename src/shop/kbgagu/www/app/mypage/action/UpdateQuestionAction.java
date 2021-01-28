package shop.kbgagu.www.app.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;


public class UpdateQuestionAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		
		if(sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 해주세요');location.href='/member/loginform'</script>");
			out.close();
			return null;
		}
		request.setAttribute("sq", request.getParameter("sq"));
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/updatequestion.jsp");
		return forward;

	}
}

