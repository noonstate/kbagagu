package shop.kbgagu.www.app.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.vo.OrderVo;


public class OrderSttusChangeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		
		if(sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 해주세요');location.href='/member/loginform'</script>");
			out.close();
			return null;
		}
		String order_sq = request.getParameter("order_sq");
		String sttus = request.getParameter("sttus");
		
		OrderVo vo = new OrderVo();
		vo.setOrder_sq(Integer.parseInt(order_sq));
		vo.setSttus(Integer.parseInt(sttus));
		
		MyPageService svc = new MyPageService();
		
		if(!svc.updateSttus(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('교환/환불신청사항을 저장하는데 실패했습니다.');location.href='/mypage/order'</script>");
			out.close();
			return null;
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/mypage/order?pn=1");
		forward.setRedirect(true);
		return forward;

	}
}

