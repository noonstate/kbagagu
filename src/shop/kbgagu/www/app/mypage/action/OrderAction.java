package shop.kbgagu.www.app.mypage.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.OrderVo;

public class OrderAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 되어있지 않습니다.');location.href='/member/loginform';</script>");
			out.close();
			return null;
		}
		
		//페이지 번호를 받아 정규식 검사후 정수형변환
		String pagenum = request.getParameter("pn");
		if (!RegExp.isValidExp(pagenum, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mypage/order?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pagenum);
		//첫페이지에서 -1되거나 사용자가 pn을 0이하로 입력해서 접근한 경우
		if (page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mypage/order?pn=1';</script>");
			out.close();
			return null;
		}
		
		MyPageService svc = new MyPageService();
		String query = "";
		Pagenation pagenation = new Pagenation(page, svc.getOrderCount(query));
		
		//최대 페이지보다 큰 페이지 입력한 경우 최대 페이지로
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mypage/order?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}
		
		// 주문정보불러오기
		
		ArrayList<OrderVo> orderList = svc.getOrderList(pagenation, sessionId);
		
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("order", orderList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/orderlist.jsp");
		return forward;
	}
}
