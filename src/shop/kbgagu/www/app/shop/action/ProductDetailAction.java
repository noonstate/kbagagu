package shop.kbgagu.www.app.shop.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.app.shop.service.ShopService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.Paser;
import shop.kbgagu.www.vo.ItemVo;
import shop.kbgagu.www.vo.ReviewVo;

public class ProductDetailAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pd = request.getParameter("pd");
		ShopService shopSvc = new ShopService();
		MyPageService mypageSvc = new MyPageService();
		
		ItemVo itemVo = shopSvc.getItemInfo(pd);
		ArrayList<ReviewVo> review = mypageSvc.getReview(pd);
		
		
		itemVo.setDetail(Paser.chgToHTML(itemVo.getDetail()));
		
		request.setAttribute("item", itemVo);
		request.setAttribute("review", review);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/shop/productdetail.jsp");
		return forward;

	}
}
