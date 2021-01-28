package shop.kbgagu.www.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.member.service.MemberService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.vo.MemberVo;

public class CheckMailIdAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");

		MemberVo vo = new MemberVo();
		vo.setId(id);

		MemberService svc = new MemberService();

		request.setAttribute("isTrue", svc.checkMailId(vo));
		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/common.jsp");
		return forward;
	}
}
