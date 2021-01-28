package shop.kbgagu.www.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.shop.service.ShopService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;

public class DeleteBasketAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int basket_sq = Integer.parseInt(request.getParameter("basket_sq"));
		
		ShopService svc = new ShopService();
		
		request.setAttribute("isTrue", svc.deleteBasket(basket_sq));

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/common.jsp");
		return forward;
	}
}
