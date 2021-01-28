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

public class JoinProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LoginManager lm = LoginManager.getInstance();
		HttpSession session = request.getSession();
		String sessionId = lm.getMemberId(session);

		// 로그인여부 체크
		if (sessionId != null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 가입 알고리즘
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String pwdc = request.getParameter("pwdc");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");

		// 빈칸체크, 유효성검사
		if (!RegExp.isValidExp(id, REGEXP_ID) || !RegExp.isValidExp(pwd, REGEXP_PWD)
				|| !RegExp.isValidExp(pwdc, REGEXP_PWD) || !RegExp.isValidExp(name, REGEXP_NAME)
				|| !RegExp.isValidExp(tel, REGEXP_TELNUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 받은 비밀번호들 두개 같은지 체크하는 부분
		if (!pwd.equals(pwdc)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 들어온 데이터는 다 정상
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		vo.setNm(name);
		vo.setTel(tel);

		// 서비스객체 선언
		MemberService svc = new MemberService();

		// 서비스가 동작을 하고나면 TF 넘기도록 한다.
		if (!svc.joinMember(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/");
		return forward;
	}
}
