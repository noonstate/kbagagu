package shop.kbgagu.www.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.shop.service.ShopService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.vo.BasketVo;

public class UpdateQyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String basketSq = request.getParameter("basket_sq");
		String qy = request.getParameter("qy");
		
		ShopService svc = new ShopService();
		BasketVo vo = new BasketVo();
		vo.setBasket_sq(Integer.parseInt(basketSq));
		vo.setQy(Integer.parseInt(qy));
		
		request.setAttribute("isTrue", svc.updateQy(vo));

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/common.jsp");
		return forward;
	}
}
