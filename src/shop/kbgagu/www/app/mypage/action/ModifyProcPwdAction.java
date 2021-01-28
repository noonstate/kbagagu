package shop.kbgagu.www.app.mypage.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_PWD;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.BCrypt;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.MemberVo;

public class ModifyProcPwdAction implements Action {
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
		String pwdc = request.getParameter("pwdc");
		String pwd = request.getParameter("pwd");
		if (!RegExp.isValidExp(pwd, REGEXP_PWD) || !RegExp.isValidExp(pwdc, REGEXP_PWD)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		MyPageService svc = new MyPageService();
		int mber_sq = Integer.parseInt(sessionId);
		
		MemberVo vo = new MemberVo();
		vo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		vo.setMber_sq(mber_sq);
		
		if(!svc.modifyPwd(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('변경실패');history.back();</script>");
			out.close();
			return null;
		}
		
		lm.removeSession(sessionId);

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/");
		return forward;
	}
}
