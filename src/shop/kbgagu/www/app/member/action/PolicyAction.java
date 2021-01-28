package shop.kbgagu.www.app.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;

public class PolicyAction implements Action {
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ActionForward forward = new ActionForward();
	forward.setPath("/views/member/policy.jsp");
	forward.setRedirect(true);
	return forward;
}
}
