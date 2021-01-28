package shop.kbgagu.www.app.shop.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.shop.service.ShopService;
import shop.kbgagu.www.app.shop.vo.BasketProcVo;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;

public class BasketViewAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);

		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인해주세요');location.href='/member/loginform';</script>");
			out.close();
			return null;
		}

		ShopService svc = new ShopService();
		
		ArrayList<BasketProcVo> basket = new ArrayList<BasketProcVo>();

		basket = svc.getBasketInfo(sessionId);

		request.setAttribute("basket", basket);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/shop/basketview.jsp");
		return forward;

	}
}
