package shop.kbgagu.www.app.shop.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.shop.service.ShopService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.ItemVo;

public class ProductListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 페이지 번호를 받아 정규식 검사후 정수형변환
		String pagenum = request.getParameter("pn");
		if (!RegExp.isValidExp(pagenum, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/shop/list?pn=1&ctgry=0';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pagenum);
		// 첫페이지에서 -1되거나 사용자가 pn을 0이하로 입력해서 접근한 경우
		if (page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/shop/list?pn=1&ctgry=0';</script>");
			out.close();
			return null;
		}

		// 검색 위해 남겨둠
		String query = "";
		
		//카테고리 분류하기
		//숫자문자열인지 체크
		String ctgry = request.getParameter("ctgry");
		if (!RegExp.isValidExp(ctgry, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/shop/list?pn=1&ctgry=0';</script>");
			out.close();
			return null;
		}
		//형변환
		int ctgryNumber = Integer.parseInt(ctgry);
		//카테고리 번호에 따라 쿼리문 셋팅
		if(ctgryNumber<=0) {
			query= "";
		}else if (ctgryNumber>=1 && ctgryNumber<=4) {
			query = "and ctgry = " + ctgry;
		}else if(ctgryNumber>4) {
			query= "";
		}
		
		ShopService svc = new ShopService();
		Pagenation pagenation = new Pagenation(page, svc.getItemCount(query,ctgryNumber));
		
		// 최대 페이지보다 큰 페이지 입력한 경우 최대 페이지로
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(
					"<script>location.href='/shop/list?pn=" + pagenation.getTotalPageCount() + "&ctgry="+ ctgryNumber + "';</script>");
			out.close();
			return null;
		}

		ArrayList<ItemVo> itemList = new ArrayList<ItemVo>();

		itemList = svc.getItemList(pagenation,query,ctgryNumber);
		
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("list", itemList);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/shop/productlist.jsp");
		return forward;

	}
}
