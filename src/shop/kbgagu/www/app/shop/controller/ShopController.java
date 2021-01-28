package shop.kbgagu.www.app.shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.shop.action.BasketViewAction;
import shop.kbgagu.www.app.shop.action.MultypleOrderAction;
import shop.kbgagu.www.app.shop.action.OrderResultAction;
import shop.kbgagu.www.app.shop.action.OrderViewAction;
import shop.kbgagu.www.app.shop.action.ProductDetailAction;
import shop.kbgagu.www.app.shop.action.ProductListAction;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;




@WebServlet("/shop/*")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/shop", "");
		ActionForward forward = null;

		if (command.equals("/list")) { // 상품리스트
			Action action = new ProductListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/productdetail")) {  //상품상세정보페이지
			Action action = new ProductDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/basketview")) {  // 장바구니페이지
			Action action = new BasketViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderview")) {  // 주문정보페이지
			Action action = new OrderViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderresult")) {  // 주문완료페이지
			Action action = new OrderResultAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/multyorder")) {  // 장바구니에서 주문하는경우
			Action action = new MultypleOrderAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		} else if (command.equals("/basketadd")) {  // 장바구니에 담기 기능
//			Action action = new BasketAddAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else if (command.equals("/productqna")) {  // 상품문의 등록 기능
//			Action action = new ProductQnaAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}  else if (command.equals("/basketorder")) {  // 장바구니에서 주문하기 기능
//			Action action = new BasketOrderAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				// 리다이랙트
				response.sendRedirect(forward.getPath()); //그냥 이동 주소값 바뀜 객체내부 리셋
			} else {
				// 디스패치
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response); //주소값 안바뀜
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
