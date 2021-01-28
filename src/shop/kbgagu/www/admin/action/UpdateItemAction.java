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
import shop.kbgagu.www.vo.ItemVo;


public class UpdateItemAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		String itemSq = request.getParameter("sq");
		
		if(sessionId == null || !sessionId.equals("1")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/'</script>");
			out.close();
			return null;
		}
		AdminService svc = new AdminService();
		ItemVo vo = svc.getItemInfo(itemSq);
		
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('정보 불러오기에 실패했습니다.');history.back();</script>");
			out.close();
			return null;
		}
		vo.setDetail(Paser.chgToHTML(vo.getDetail()));
		request.setAttribute("vo", vo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/updateitem.jsp");
		return forward;

	}
}

