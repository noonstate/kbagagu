package shop.kbgagu.www.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.admin.service.AdminService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;

public class DeleteItemProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);

		if (sessionId == null) { // 비로그인시 로그인 요구
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 하십시오');history.back();</script>");
			out.close();
			return null;
		}

		if (!sessionId.equals("1")) { // 관리자 계정과 일치하지 않을 시 홈으로
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/'</script>");
			out.close();
			return null;
		}
		String sq = request.getParameter("sq");
		
		int sq1 = Integer.parseInt(sq);
		
		AdminService svc = new AdminService();
		if(!svc.deleteItem(sq1)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제에 실패했습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/admin/manageitem?pn=1");
		return forward;
	}
}
