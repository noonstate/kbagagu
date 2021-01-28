package shop.kbgagu.www.app.member.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NAME;
import static shop.kbgagu.www.common.RegExp.REGEXP_TELNUMBER;
import static shop.kbgagu.www.common.RegExp.REGEXP_ID;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.member.service.MemberService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.MemberVo;

public class FindPwdProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 체크
		LoginManager lm = LoginManager.getInstance();
		HttpSession session = request.getSession();
		String sessionId = lm.getMemberId(session);
		if (sessionId != null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 데이터 로드
		String id = request.getParameter("id");
		String nm = request.getParameter("nm");
		String tel = request.getParameter("tel");

		// 데이터 체크
		if (!RegExp.isValidExp(id, REGEXP_ID) || !RegExp.isValidExp(nm, REGEXP_NAME)
				|| !RegExp.isValidExp(tel, REGEXP_TELNUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 맞으면 보에 담아서
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setNm(nm);
		vo.setTel(tel);

		// 서비스 객체 생성
		MemberService svc = new MemberService();
		// 서비스에서 DB조회 해서 결과 boolean타입으로 받음
		if (!svc.findpwd(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('일치하는 정보가 없습니다.');history.back();</script>");
			out.close();
			return null;
		}

		request.setAttribute("vo", vo);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/updatepwd.jsp");
		return forward;
	}
}
