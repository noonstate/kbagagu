package shop.kbgagu.www.ajax.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.ajax.action.AddBasketAction;
import shop.kbgagu.www.ajax.action.CheckMailIdAction;
import shop.kbgagu.www.ajax.action.DeleteBasketAction;
import shop.kbgagu.www.ajax.action.LoginAction;
import shop.kbgagu.www.ajax.action.UpdateQyAction;
import shop.kbgagu.www.common.Action;

@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 도메인 뒤의 경로를 문자열로 저장하는 내장 매서드들
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;

		if (command.equals("/checkMailId.ajax")) { // 가입버튼 클릭시 약관 페이지 이동
			Action action = new CheckMailIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/login.ajax")) { // 로그인시 비밀번호 체크
			Action action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/addbasket.ajax")) { // 장바구니 추가
			Action action = new AddBasketAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deletebasket.ajax")) { // 장바구니 추가
			Action action = new DeleteBasketAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updateQy.ajax")) { // 장바구니 추가
			Action action = new UpdateQyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				// 리다이렉스
				response.sendRedirect(forward.getPath());
			} else {
				// 디스패치
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
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
