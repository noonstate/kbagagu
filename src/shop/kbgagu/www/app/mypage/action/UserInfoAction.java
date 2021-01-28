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

public class UserInfoAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 상태 확인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근.');location.href='/';</script>");
			out.close();
			return null;
		}

		int mber_sq = Integer.parseInt(sessionId); //String으로 저장되어있는 id를 int로 변환

		MemberService svc = new MemberService(); //서비스 객체 생성
		MemberVo vo = svc.userInfo(mber_sq); 

		request.setAttribute("vo", vo); 
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/mypageDetail.jsp");
		return forward;
	}
}
