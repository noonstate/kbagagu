package shop.kbgagu.www.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.shop.service.ShopService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.vo.BasketVo;

public class AddBasketAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String itemSq = request.getParameter("itemSq");
		String mberSq = request.getParameter("session");
		String qy = request.getParameter("qy");
		
		ShopService svc = new ShopService();
		BasketVo vo = new BasketVo();
		vo.setItem_sq(Integer.parseInt(itemSq));
		vo.setMber_sq(Integer.parseInt(mberSq));
		vo.setQy(Integer.parseInt(qy));
		
		request.setAttribute("isTrue", svc.addBasket(vo));

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/common.jsp");
		return forward;
	}
}
