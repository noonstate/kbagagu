package shop.kbgagu.www.app.member.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_ID;
import static shop.kbgagu.www.common.RegExp.REGEXP_PWD;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.member.service.MemberService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.BCrypt;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.MemberVo;

public class LoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		//로그인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionSq = lm.getMemberId(session);
		
		// 로그인 확인
		if (sessionSq != null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인된 상태입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// 입력받은 아이디 비번 검사
		if (!RegExp.isValidExp(id, REGEXP_ID) || !RegExp.isValidExp(pwd, REGEXP_PWD)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		// 서비스에 쿼리문요청
		MemberService svc = new MemberService();
		MemberVo vo = svc.loginInfo(id);
		
		// vo가 null이면 디비조회 실패, 비밀번호검사
		if (vo == null || !BCrypt.checkpw(pwd, vo.getPwd())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보를 다시 확인해주세요.');location.href='/member/loginform';</script>");
			out.close();
			return null;
		}
		
		// 탈퇴아이디 검사
		if (vo.isDel_fl()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('탈퇴한 아이디입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 로그인 히스토리 저장
		if (!svc.setLoginHistory(id)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보 저장에 실패했습니다.');location.href='/member/loginform';</script>");
			out.close();
			return null;
		}

		// 세션에 sq값을 저장
		lm.setSession(session, String.valueOf(vo.getMber_sq()));
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
