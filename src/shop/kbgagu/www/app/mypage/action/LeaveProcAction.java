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

public class LeaveProcAction implements Action {
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
		
		int sq = Integer.parseInt(sessionId); // 세션의 sq를 정수형으로 변환
		MyPageService svc = new MyPageService(); // 마이페이지서비스 객체 생성
		MemberVo vo = svc.getPwd(sq); // vo라는 객체에 서비스의 getPwd메소드의 실행결과를 담는다.

		String pwd = request.getParameter("pwd"); // 사용자에게서 받은 패스워드
		
		if (!RegExp.isValidExp(pwd, REGEXP_PWD)) { // 정규식 검사
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		if (!BCrypt.checkpw(pwd, vo.getPwd())) { // 비밀번호가 일치하는지 확인
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 일치하지 않습니다.');history.back();</script>");
			out.close();
			return null;
		}
		if (!svc.leaveMember(sq)) { // 
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원탈퇴에 실패했습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		// 탈퇴후 세션삭제
		lm.removeSession(sessionId);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
