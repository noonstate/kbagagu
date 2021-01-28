package shop.kbgagu.www.app.mypage.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.member.service.MemberService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.vo.MemberVo;

public class ModifyMyInfoAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);

		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		String id = lm.getMemberId(session);
		int mber_sq = Integer.parseInt(id);

		MemberService svc = new MemberService();
		MemberVo vo = svc.userInfo(mber_sq);
		
		request.setAttribute("vo", vo);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/modifyform.jsp");
		return forward;
	}
}
