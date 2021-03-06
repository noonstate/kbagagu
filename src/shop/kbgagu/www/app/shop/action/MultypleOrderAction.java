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
import shop.kbgagu.www.vo.ItemVo;
import shop.kbgagu.www.vo.MemberVo;
import shop.kbgagu.www.vo.OrderVo;

public class MultypleOrderAction implements Action {
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
		
		String[] basket_sq = request.getParameterValues("basketSq");
		String[] itemSq = request.getParameterValues("order");
		ArrayList<ItemVo> itemList = new ArrayList<>();
		ArrayList<BasketProcVo> basketList = new ArrayList<>();
		ShopService svc = new ShopService();
		for (int i = 0; i < itemSq.length; i++) {
			ItemVo itemVo = new ItemVo(); 
			itemVo = svc.getItemInfo(itemSq[i]);
			itemList.add(itemVo);
		}
		for (int i = 0; i < basket_sq.length; i++) {
			BasketProcVo basketProcVo = new BasketProcVo();
			basketProcVo = svc.getBasket(basket_sq[i]); 
			basketList.add(basketProcVo);
		}

		MemberVo memberVo = svc.getMemberInfo(sessionId);
		OrderVo orderVo = svc.getOrderInfo(sessionId);  // 아이디를 키로 주문서요청

		request.setAttribute("basketList", basketList);
		request.setAttribute("itemVoList", itemList);
		request.setAttribute("memberVo", memberVo);
		request.setAttribute("orderVo", orderVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/shop/orderview.jsp");
		return forward;

	}
}
