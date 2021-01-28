package shop.kbgagu.www.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.admin.service.AdminService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.Paser;
import shop.kbgagu.www.vo.NoticeVo;


public class UpdateReviewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionSq = lm.getMemberId(session);
		int reviewSq = Integer.parseInt(request.getParameter("sq"));
		
		if(sessionSq == null || !sessionSq.equals("1")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/'</script>");
			out.close();
			return null;
		}
		
		AdminService svc = new AdminService();
		
		if(!svc.deleteReivew(reviewSq)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰삭제에 실패했습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/admin/managereview?pn=1");
		return forward;

	}
}

