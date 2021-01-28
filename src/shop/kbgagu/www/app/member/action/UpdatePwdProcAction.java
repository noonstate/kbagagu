package shop.kbgagu.www.app.member.action;

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

import static shop.kbgagu.www.common.RegExp.REGEXP_ID; // 아이디검사
import static shop.kbgagu.www.common.RegExp.REGEXP_PWD; // 비번검사
import static shop.kbgagu.www.common.RegExp.REGEXP_NAME; // 이름검사
import static shop.kbgagu.www.common.RegExp.REGEXP_TELNUMBER;// 전화번호검사

public class UpdatePwdProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LoginManager lm = LoginManager.getInstance();
		HttpSession session = request.getSession();
		String sessionId = lm.getMemberId(session);
		// 가입 알고리즘

		String pwd = request.getParameter("pwd");
		String id = request.getParameter("id");
		String nm = request.getParameter("nm");
		String tel = request.getParameter("tel");

		if (sessionId != null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 빈칸체크, 유효성검사
		if (!RegExp.isValidExp(pwd, REGEXP_PWD) || !RegExp.isValidExp(id, REGEXP_ID)
				|| !RegExp.isValidExp(nm, REGEXP_NAME) || !RegExp.isValidExp(tel, REGEXP_TELNUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// vo에 사용자에게 받은 모든 정보 set하기 비밀번호는 암호화
		MemberVo vo = new MemberVo();
		vo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		vo.setId(id);
		vo.setNm(nm);
		vo.setTel(tel);

		// 서비스 객체 생성
		MemberService svc = new MemberService();

		if (!svc.updatePwd(vo)) { // DB에서 비밀번호 변경 후 결과 boolean타입으로 리턴받음
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 재설정에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 비번 변경 후 리다이렉트 방식으로 홈으로 보내기
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/");
		return forward;
	}
}
