package shop.kbgagu.www.app.shop.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.shop.service.ShopService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.ItemVo;
import shop.kbgagu.www.vo.OrderVo;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER; // 숫자검사
import static shop.kbgagu.www.common.RegExp.REGEXP_TELNUMBER; // 전화번호검사

public class OrderResultAction implements Action {
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
		ArrayList<OrderVo> orderList = new ArrayList<>();
		ArrayList<ItemVo> itemList = new ArrayList<>();
		String[] basket_sq = request.getParameterValues("basket_sq");
		String[] item_sq = request.getParameterValues("item_sq");
		String orderNm = request.getParameter("orderNm");
		String[] qy = request.getParameterValues("qy");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String payment = request.getParameter("payment");
		String tel = request.getParameter("tel");
		String requst = request.getParameter("request");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();

		if (basket_sq == null) {
			if (qy == null) {
				qy = new String[0];
				qy[0] = "1";
			}

			if (payment == null || !RegExp.isValidExp(zip, REGEXP_NUMBER)
					|| !RegExp.isValidExp(tel, REGEXP_TELNUMBER)) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
				out.close();
				return null;
			}
			OrderVo orderVo = new OrderVo();
			orderVo.setItem_sq(Integer.parseInt(item_sq[0]));
			orderVo.setMber_sq(Integer.parseInt(sessionId));
			orderVo.setOrder_nm(orderNm);
			orderVo.setSttus(2);
			orderVo.setQy(Integer.parseInt(qy[0]));
			orderVo.setPymnt(Integer.parseInt(payment));
			orderVo.setOrder_tel(tel);
			orderVo.setZip(zip);
			orderVo.setAdres(address);
			if (detailAddress != null) {
				orderVo.setDetail_adres(detailAddress);
			}
			if (extraAddress != null) {
				orderVo.setExtra_adres(extraAddress);
			}
			orderVo.setRequst(requst);
			orderVo.setReg_date(format.format(time));

			ShopService svc = new ShopService();
			if (svc.getInvntry(orderVo) > 0) {
				if (!svc.updateQy(orderVo) || !svc.makeOrder(orderVo)) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('주문과정에 오류가 발생했습니다.');history.back();</script>");
					out.close();
					return null;
				}

				ItemVo itemVo = new ItemVo();
				itemVo = svc.getItemInfo(item_sq[0]);
				itemList.add(itemVo);
				orderList.add(orderVo);
				request.setAttribute("itemList", itemList);
				request.setAttribute("orderList", orderList);
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('재고가 없습니다.');history.back();</script>");
				out.close();
				return null;
			}
		} else {
			// 장바구니랑 상품번호 풀어서 쿼리쓰고 해당하는장바구니 번호로 delete실행(잇는거 쓰면될듯)
			OrderVo orderVo = null;
			for (int i = 0; i < basket_sq.length; i++) {
				orderVo = new OrderVo();
				orderVo.setMber_sq(Integer.parseInt(sessionId));
				orderVo.setItem_sq(Integer.parseInt(item_sq[i]));
				orderVo.setSttus(2);
				orderVo.setPymnt(Integer.parseInt(payment));
				orderVo.setOrder_tel(tel);
				orderVo.setOrder_nm(orderNm);
				orderVo.setZip(zip);
				orderVo.setAdres(address);
				if (detailAddress != null) {
					orderVo.setDetail_adres(detailAddress);
				}
				if (extraAddress != null) {
					orderVo.setExtra_adres(extraAddress);
				}
				orderVo.setRequst(requst);
				orderVo.setQy(Integer.parseInt(qy[i]));
				orderVo.setReg_date(format.format(time));

				ShopService svc = new ShopService();
				if (svc.getInvntry(orderVo) > 0) {
					if (!svc.updateQy(orderVo) ||!svc.makeOrder(orderVo) 
							|| !svc.deleteBasket(Integer.parseInt(basket_sq[i]))) {
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>alert('주문과정에 오류가 발생했습니다.');history.back();</script>");
						out.close();
						return null;
					}
					ItemVo itemVo = new ItemVo();
					itemVo = svc.getItemInfo(item_sq[i]);
					itemList.add(itemVo);
					orderList.add(orderVo);
				} else {
					String item_nm = svc.getItemNm(orderVo.getItem_sq());
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('"+ item_nm +"의 재고가 없습니다.');history.back();</script>");
					out.close();
					return null;
				}
			}

			request.setAttribute("itemList", itemList);
			request.setAttribute("orderList", orderList);
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/views/shop/orderresult.jsp");
		return forward;

	}
}
