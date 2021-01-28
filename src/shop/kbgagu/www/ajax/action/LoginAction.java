package shop.kbgagu.www.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.member.service.MemberService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.BCrypt;
import shop.kbgagu.www.vo.MemberVo;

public class LoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		boolean isTrue = false;

		MemberVo vo = new MemberVo();
		vo.setId(id);

		MemberService svc = new MemberService();
		vo = svc.getPwd(id);

		// 받은 비밀번호랑 디비의 비밀번호를 비교
		// true면 isTrue = ture
		// Attribute(isTrue, isTrue)
		// false면 Attribute(isTrue, isTrue)
		if (vo == null) {
			request.setAttribute("isTrue", isTrue);
		}
		if (vo.getPwd() != null && BCrypt.checkpw(pwd, vo.getPwd())) {
			isTrue = true;
			request.setAttribute("isTrue", isTrue);
		} else {
			request.setAttribute("isTrue", isTrue);
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/common.jsp");
		return forward;
	}
}
