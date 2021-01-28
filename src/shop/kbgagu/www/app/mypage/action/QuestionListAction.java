package shop.kbgagu.www.app.mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.vo.QuestionVo;

public class QuestionListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 현재 로그인한 계정의 문의 내역 불러오는 알고리즘
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
        ArrayList<QuestionVo> que = svc.getQuestionList(sessionId);
		
		request.setAttribute("que", que );
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/questionlist.jsp");
		return forward;
	}
}
