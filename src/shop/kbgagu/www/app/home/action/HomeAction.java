package shop.kbgagu.www.app.home.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.home.service.HomeService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.vo.ItemVo;

public class HomeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HomeService svc = new HomeService();
		
		ArrayList<ItemVo> newItemList = svc.getNewItemList();
		ArrayList<ItemVo> upvoteItemList = svc.getUpvoteItemList();
		ArrayList<ItemVo> saleItemList = svc.getSaleItemList();
		
 		
		request.setAttribute("newItemList", newItemList);
		request.setAttribute("upvoteItemList", upvoteItemList);
		request.setAttribute("saleItemList", saleItemList);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/index.jsp");
		return forward;
	}
}
