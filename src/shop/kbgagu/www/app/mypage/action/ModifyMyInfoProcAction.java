package shop.kbgagu.www.app.mypage.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NAME;
import static shop.kbgagu.www.common.RegExp.REGEXP_TELNUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.MemberVo;

public class ModifyMyInfoProcAction implements Action {
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

		int sq = Integer.parseInt(sessionId);
		String tel = request.getParameter("tel"); //사용자가 입력한 전화번호, 이름
		String nm = request.getParameter("nm");

		// 이름, 전화번호 정규식 검사
		if (!RegExp.isValidExp(nm, REGEXP_NAME) || !RegExp.isValidExp(tel, REGEXP_TELNUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		MemberVo vo = new MemberVo();
		vo.setMber_sq(sq);
		vo.setNm(nm);
		vo.setTel(tel);

		MyPageService svc = new MyPageService();
		
		if (!svc.modifyMember(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 정보 변경에 실패했습니다.');history.back();</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/mypage.jsp");
		forward.setRedirect(true);
		return forward;
	}
}
