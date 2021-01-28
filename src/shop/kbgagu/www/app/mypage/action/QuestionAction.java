package shop.kbgagu.www.app.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.vo.MemberVo;

public class QuestionAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 문의 폼으로 보내는 액션
		// 로그인 상태 확인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 해주세요');location.href='/';</script>");
			out.close();
			return null;
		}
		
        MyPageService svc = new MyPageService();
        int sq = Integer.parseInt(sessionId);
        MemberVo vo = svc.getId(sq);
        request.setAttribute("vo", vo);
        
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/questionform.jsp");
		return forward;
	}
}
